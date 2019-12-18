package gameframework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class Game {

	private boolean busy = false;
	private boolean noair = true;
	int[][] playb;
	int[] lastpos = new int[9];
	
	public static BufferedImage empty;
	public static BufferedImage Iblock;
	public static BufferedImage Lblock;
	public static BufferedImage LRblock;
	public static BufferedImage Sblock;
	public static BufferedImage SRblock;
	public static BufferedImage Tblock;
	public static BufferedImage Oblock;
	public static BufferedImage GameOverpic;
	public static BufferedImage visionimg;
	public static BufferedImage GameGroundpic;
	public static BufferedImage empty2;
	public static BufferedImage IBlockcom;
	public static BufferedImage LBlockcom;
	public static BufferedImage LRBlockcom;
	public static BufferedImage SBlockcom;
	public static BufferedImage SRBlockcom;
	public static BufferedImage TBlockcom;
	public static BufferedImage OBlockcom;
	
	Random ran = new Random();
	int nextblock;
	int blocktype;
	public boolean GameOver;
	private int dropspeed = 0;
	boolean test;
	private int Iturn = 1;
	private int Tturn = 1;
	private int Lturn = 1;
	private int LRturn = 1;
	private int Sturn = 1;
	private int SRturn = 1;
	private int counter;
	private int startdif = 20;
	private int difficulty = startdif;
	private boolean moveleft;
	private boolean moveright;
	private int movecount;
	private int shiftedblock = 0;
	private boolean shiftedround;
	private int oldshiftedblock;
	int framewidth;
	int frameheight;
	int x1;
	int x2;
	int x3;
	int x4;
	int y1;
	int y2;
	int y3;
	int y4;
	boolean vision;
	private int tilewidth;
	private int tileheight;
	private int linescleared = 0;
	//private boolean movel;
	//private boolean mover;
	private int movefactorx;
	private int movefactory;
	private int level;
	private int linessafe;
	private int completelines;	
	private boolean one;
	private boolean two;
	private int ablock;
	private boolean first = true;
	private int bblock;
	private int cblock;
	private int dblock;
	public static int loaded;
	//private Sound sound;
	
	public Game(int frameheight, int framewidth) {
		Framework.gameState = Framework.GameState.LOADING;
		this.framewidth = framewidth;
		this.frameheight = frameheight;

		Thread threadForInitGame = new Thread() {

			@Override
			public void run() {
				// Sets variables and objects for the game.
				Initialize();
				// Load game files (images, sounds, ...)
				
			

					LoadContent();
					while (loaded!=19){
				
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				

				Framework.gameState = Framework.GameState.PLAYING;
			}
		};
		threadForInitGame.start();
	}

	/**
	 * Set variables and objects for the game.
	 */
	private void Initialize() {
		noair = true;

		playb = new int[10][20];
		for (int j = 0; j < playb.length; j++) {
			for (int k = 0; k < playb[j].length; k++) {

				playb[j][k] = 0;
		


			}
			
		}
		Framework.loading = 5;
		
	}

	/**
	 * Load game files - images, sounds, ...
	 */
	private void LoadContent(){
		ImageLoader eins = new ImageLoader("Visionblock.png",1);
		ImageLoader zwei = new ImageLoader("empty.png",2);
		ImageLoader drei = new ImageLoader("Iblock.png",3);
		ImageLoader vier = new ImageLoader("Lblock.png",4);
		ImageLoader fünf = new ImageLoader("LRblock.png",5);
		ImageLoader sechs = new ImageLoader("Oblock.png",6);
		ImageLoader sieben = new ImageLoader("Sblock.png",7);
		ImageLoader acht = new ImageLoader("SRblock.png",8);
		ImageLoader neun = new ImageLoader("Tblock.png",9);
		ImageLoader zehn = new ImageLoader("GameOver.png",10);
		ImageLoader elf = new ImageLoader("playground.jpg",11);
		ImageLoader zwölf = new ImageLoader("empty2.png",12);
		ImageLoader dreizehn = new ImageLoader("IBlockcom.png",13);
		ImageLoader vierzehn = new ImageLoader("LBlockcom.png",14);
		ImageLoader fünfzehn = new ImageLoader("LRBlockcom.png",15);
		ImageLoader sechzehn = new ImageLoader("OBlockcom.png",16);
		ImageLoader siebzehn = new ImageLoader("SBlockcom.png",17);
		ImageLoader achtzehn = new ImageLoader("SRBlockcom.png",18);
		ImageLoader neunzehn = new ImageLoader("TBlockcom.png",19);
		
		
		
		Thread one = new Thread(eins);
		Thread two = new Thread(zwei);
		Thread three = new Thread(drei);
		Thread four = new Thread(vier);
		Thread five = new Thread(fünf);
		Thread six = new Thread(sechs);
		Thread seven = new Thread(sieben);
		Thread eight = new Thread(acht);
		Thread nine = new Thread(neun);
		Thread ten = new Thread(zehn);
		Thread eleven = new Thread(elf);
		Thread twelve = new Thread(zwölf);
		Thread thirdteen = new Thread(dreizehn);
		Thread fourteen= new Thread(vierzehn);
		Thread fifteen = new Thread(fünfzehn);
		Thread sixteen = new Thread(sechzehn);
		Thread seventeen = new Thread(siebzehn);
		Thread eightteen = new Thread(achtzehn);
		Thread nineteen = new Thread(neunzehn);
		
		
		
		one.start();
		two.start();
		three.start();
		four.start();
		five.start();
		six.start();
		seven.start();
		eight.start();
		nine.start();
		ten.start();
		eleven.start();
		twelve.start();
		thirdteen.start();
		fourteen.start();
		fifteen.start();
		sixteen.start();
		seventeen.start();
		eightteen.start();
		nineteen.start();

		
	}

	/**
	 * Restart game - reset some variables.
	 */
	public void RestartGame() {
		for (int j = 0; j < playb.length; j++) {
			for (int k = 0; k < playb[j].length; k++) {

				playb[j][k] = 0;
				// befüllen des Spielfelds mit 0

			}

		}
		Framework.gameState = Framework.GameState.PLAYING;
		GameOver = false;
		noair = true;
		shiftedblock = 0;
		level=0;
		difficulty = startdif;
		System.out.println("NEW GAME )()()()()()()()");

	}

	/**
	 * Update game logic.
	 * 
	 * @param gameTime
	 *            gameTime of the game.
	 * @param mousePosition
	 *            current mouse position.
	 */
	public void UpdateGame(long gameTime, Point mousePosition) {
		dropspeed += 1;
		if (linescleared >= 10) {
			linessafe = 0;
			while (linescleared >10){
				linescleared--;
				linessafe++;
			}
			linescleared = linessafe;
			level++;
			difficulty -= 1;
			System.out.println("Level "+ level);
		}

		if (!busy) {
			if (dropspeed == 1) {
				if (!noair) {
					nextposition();
				} else {

					testlines();
					Sound.sound1.play();
					// System.out.println("kurz vor add von block");
					if (!shiftedround) {
						newblock();
					} else {
						if (oldshiftedblock == 0){
							newblock();
						}
						else{
						choosenextblock(oldshiftedblock);
						}
					}
					renderVision();

					// System.out
					// .println("neuer Block geaddded..............................");
					noair = false;

				}
			}
			if (dropspeed >= difficulty) {
				dropspeed = 0;
			}
			if(moveleft||moveright){
			if (movecount == 2) {

				if (moveleft) {
					moveblockleft();
					renderVision();
					

				}
				if (moveright) {
					moveblockright();
					renderVision();

				}
				movecount = 0;
			} else {
				movecount++;
			}
			}
			if (Canvas.keyboardKeyState(KeyEvent.VK_SPACE)) {
				while (!test) {
					nextposition();
				}
			}
		}
	}

	private void renderVision() {
		x1 = lastpos[1];
		y1 = lastpos[2];
		x2 = lastpos[3];
		y2 = lastpos[4];
		x3 = lastpos[5];
		y3 = lastpos[6];
		x4 = lastpos[7];
		y4 = lastpos[8];

		while (!testbottom(y1, y2, y3, y4)
				&& !testnextpos(x1, y1, x2, y2, x3, y3, x4, y4, true)) {
			y1++;
			y2++;
			y3++;
			y4++;

		}

	}

	private void testlines() {

		for (int i = 0; i < 20; i++) {
			counter = 0;
			for (int z = 0; z < playb.length; z++) {
				if (playb[z][i] != 0) {

					counter++;
				}

			}
			if (counter == 10) {
				linescleared++;
				completelines++;
				System.out.println(linescleared + "Lines Cleared");
				// System.out.println("counter gleich 10");
				for (int k = 0; k < 10; k++) {
					playb[k][i] = 0;
				}
				for (int u = i; u > 0; u--) {
					for (int v = 0; v < 10; v++) {
						// System.out.println("I AT LEAST TESTED IT");
						if (playb[v][u] != 0) {
							blocktype = playb[v][u];
							playb[v][u] = 0;
							// System.out.println(u);
							playb[v][u + 1] = blocktype;
						}

					}
				}
			}

		}

	}

	public void turnleft() {
		if (!test) {
			busy = true;
			turn();
			renderVision();
			busy = false;
		}
	}

	private void turn() {
		blocktype = playb[lastpos[1]][lastpos[2]];
		playb[lastpos[1]][lastpos[2]] = 0;
		playb[lastpos[3]][lastpos[4]] = 0;
		playb[lastpos[5]][lastpos[6]] = 0;
		playb[lastpos[7]][lastpos[8]] = 0;

		switch (blocktype) {
		case 1: {

			switch (Iturn) {
			case 1: {
				// System.out.println("ITS 1");

				lastpos[1] -= 2;
				lastpos[2] += 2;

				lastpos[3] -= 1;
				lastpos[4] += 1;

				lastpos[5] += 0;
				lastpos[6] -= 0;

				lastpos[7] += 1;
				lastpos[8] -= 1;

				if (testturn()) {
					lastpos[1] += 2;
					lastpos[2] -= 2;

					lastpos[3] += 1;
					lastpos[4] -= 1;

					lastpos[5] -= 0;
					lastpos[6] += 0;

					lastpos[7] -= 1;
					lastpos[8] += 1;

				} else {

					Iturn = 2;
					// System.out.println("SET TO 2");
				}
				break;
			}
			case 2: {
				// System.out.println("ITS 2");

				lastpos[1] += 2;
				lastpos[2] -= 2;

				lastpos[3] += 1;
				lastpos[4] -= 1;

				lastpos[5] += 0;
				lastpos[6] += 0;

				lastpos[7] -= 1;
				lastpos[8] += 1;
				if (testturn()) {

					lastpos[1] -= 2;
					lastpos[2] += 2;

					lastpos[3] -= 1;
					lastpos[4] += 1;

					lastpos[5] -= 0;
					lastpos[6] -= 0;

					lastpos[7] += 1;
					lastpos[8] -= 1;

				} else {
					Iturn = 1;
					// System.out.println("SET TO 1");
				}
			}
				break;
			}

			// Iblock

			break;
		}

		case 2: { // O
			break;
		}
		case 3: { // T
			switch (Tturn) {
			case 1: {
				lastpos[1] += 1;
				lastpos[2] -= 1;

				lastpos[3] += 1;
				lastpos[4] += 1;

				lastpos[5] += 0;
				lastpos[6] += 0;

				lastpos[7] -= 1;
				lastpos[8] += 1;

				if (testturn()) {
					lastpos[1] -= 1;
					lastpos[2] += 1;

					lastpos[3] -= 1;
					lastpos[4] -= 1;

					lastpos[5] -= 0;
					lastpos[6] -= 0;

					lastpos[7] += 1;
					lastpos[8] -= 1;

				} else {
					Tturn = 2;
				}

				break;
			}
			case 2: {
				lastpos[1] += 1;
				lastpos[2] += 1;

				lastpos[3] -= 1;
				lastpos[4] += 1;

				lastpos[5] -= 0;
				lastpos[6] -= 0;

				lastpos[7] -= 1;
				lastpos[8] -= 1;

				if (testturn()) {
					lastpos[1] -= 1;
					lastpos[2] -= 1;

					lastpos[3] += 1;
					lastpos[4] -= 1;

					lastpos[5] += 0;
					lastpos[6] += 0;

					lastpos[7] += 1;
					lastpos[8] += 1;

				} else {
					Tturn = 3;
				}

				break;
			}
			case 3: {
				lastpos[1] -= 1;
				lastpos[2] += 1;

				lastpos[3] -= 1;
				lastpos[4] -= 1;

				lastpos[5] -= 0;
				lastpos[6] += 0;

				lastpos[7] += 1;
				lastpos[8] -= 1;

				if (testturn()) {
					lastpos[1] += 1;
					lastpos[2] -= 1;

					lastpos[3] += 1;
					lastpos[4] += 1;

					lastpos[5] += 0;
					lastpos[6] -= 0;

					lastpos[7] -= 1;
					lastpos[8] += 1;

				} else {
					Tturn = 4;
				}

				break;
			}
			case 4: {
				lastpos[1] -= 1;
				lastpos[2] -= 1;

				lastpos[3] += 1;
				lastpos[4] -= 1;

				lastpos[5] += 0;
				lastpos[6] += 0;

				lastpos[7] += 1;
				lastpos[8] += 1;

				if (testturn()) {
					lastpos[1] += 1;
					lastpos[2] += 1;

					lastpos[3] -= 1;
					lastpos[4] += 1;

					lastpos[5] -= 0;
					lastpos[6] -= 0;

					lastpos[7] -= 1;
					lastpos[8] -= 1;

				} else {
					Tturn = 1;
				}

				break;
			}

			}
			break;
		}
		case 4: { // L

			switch (Lturn) {

			case 1: {
				lastpos[1] += 1;
				lastpos[2] -= 1;

				lastpos[3] += 0;
				lastpos[4] -= 0;

				lastpos[5] -= 1;
				lastpos[6] += 1;

				lastpos[7] += 0;
				lastpos[8] += 2;

				if (testturn()) {
					lastpos[1] -= 1;
					lastpos[2] += 1;

					lastpos[3] -= 0;
					lastpos[4] += 0;

					lastpos[5] += 1;
					lastpos[6] -= 1;

					lastpos[7] -= 0;
					lastpos[8] -= 2;

				} else {
					Lturn = 2;
				}
				break;
			}

			case 2: {
				lastpos[1] += 1;
				lastpos[2] += 1;

				lastpos[3] += 0;
				lastpos[4] -= 0;

				lastpos[5] -= 1;
				lastpos[6] -= 1;

				lastpos[7] -= 2;
				lastpos[8] += 0;

				if (testturn()) {
					lastpos[1] -= 1;
					lastpos[2] -= 1;

					lastpos[3] -= 0;
					lastpos[4] += 0;

					lastpos[5] += 1;
					lastpos[6] += 1;

					lastpos[7] += 2;
					lastpos[8] -= 0;

				} else {
					Lturn = 3;
				}
				break;
			}
			case 3: {
				lastpos[1] -= 1;
				lastpos[2] += 1;

				lastpos[3] -= 0;
				lastpos[4] -= 0;

				lastpos[5] += 1;
				lastpos[6] -= 1;

				lastpos[7] -= 0;
				lastpos[8] -= 2;

				if (testturn()) {
					lastpos[1] += 1;
					lastpos[2] -= 1;

					lastpos[3] += 0;
					lastpos[4] += 0;

					lastpos[5] -= 1;
					lastpos[6] += 1;

					lastpos[7] += 0;
					lastpos[8] += 2;

				} else {
					Lturn = 4;
				}
				break;
			}

			case 4: {
				lastpos[1] -= 1;
				lastpos[2] -= 1;

				lastpos[3] -= 0;
				lastpos[4] += 0;

				lastpos[5] += 1;
				lastpos[6] += 1;

				lastpos[7] += 2;
				lastpos[8] += 0;

				if (testturn()) {
					lastpos[1] += 1;
					lastpos[2] += 1;

					lastpos[3] += 0;
					lastpos[4] -= 0;

					lastpos[5] -= 1;
					lastpos[6] -= 1;

					lastpos[7] -= 2;
					lastpos[8] -= 0;

				} else {
					Lturn = 1;
				}
				break;
			}

			}
			break;
		}
		case 5: { // LR

			switch (LRturn) {

			case 1: {
				lastpos[1] -= 1;
				lastpos[2] += 1;

				lastpos[3] -= 0;
				lastpos[4] += 0;

				lastpos[5] += 1;
				lastpos[6] -= 1;

				lastpos[7] += 2;
				lastpos[8] -= 0;

				if (testturn()) {
					lastpos[1] += 1;
					lastpos[2] -= 1;

					lastpos[3] += 0;
					lastpos[4] -= 0;

					lastpos[5] -= 1;
					lastpos[6] += 1;

					lastpos[7] -= 2;
					lastpos[8] += 0;

				} else {
					LRturn = 2;
				}
				break;
			}
			case 2: {
				lastpos[1] -= 1;
				lastpos[2] -= 1;

				lastpos[3] -= 0;
				lastpos[4] -= 0;

				lastpos[5] += 1;
				lastpos[6] += 1;

				lastpos[7] += 0;
				lastpos[8] += 2;

				if (testturn()) {
					lastpos[1] += 1;
					lastpos[2] += 1;

					lastpos[3] += 0;
					lastpos[4] += 0;

					lastpos[5] -= 1;
					lastpos[6] -= 1;

					lastpos[7] -= 0;
					lastpos[8] -= 2;

				} else {
					LRturn = 3;
				}
				break;
			}
			case 3: {
				lastpos[1] += 1;
				lastpos[2] -= 1;

				lastpos[3] += 0;
				lastpos[4] -= 0;

				lastpos[5] -= 1;
				lastpos[6] += 1;

				lastpos[7] -= 2;
				lastpos[8] += 0;

				if (testturn()) {
					lastpos[1] -= 1;
					lastpos[2] += 1;

					lastpos[3] -= 0;
					lastpos[4] += 0;

					lastpos[5] += 1;
					lastpos[6] -= 1;

					lastpos[7] += 2;
					lastpos[8] -= 0;

				} else {
					LRturn = 4;
				}
				break;
			}
			case 4: {
				lastpos[1] += 1;
				lastpos[2] += 1;

				lastpos[3] += 0;
				lastpos[4] += 0;

				lastpos[5] -= 1;
				lastpos[6] -= 1;

				lastpos[7] -= 0;
				lastpos[8] -= 2;

				if (testturn()) {
					lastpos[1] -= 1;
					lastpos[2] -= 1;

					lastpos[3] -= 0;
					lastpos[4] -= 0;

					lastpos[5] += 1;
					lastpos[6] += 1;

					lastpos[7] += 0;
					lastpos[8] += 2;

				} else {
					LRturn = 1;
				}

				break;
			}

			}
			break;
		}
		case 6: { // S
			switch (Sturn) {
			case 1: {
				lastpos[1] += 0;
				lastpos[2] += 1;

				lastpos[3] += 1;
				lastpos[4] += 0;

				lastpos[5] -= 0;
				lastpos[6] -= 1;

				lastpos[7] += 1;
				lastpos[8] -= 2;

				if (testturn()) {
					lastpos[1] -= 0;
					lastpos[2] -= 1;

					lastpos[3] -= 1;
					lastpos[4] -= 0;

					lastpos[5] += 0;
					lastpos[6] += 1;

					lastpos[7] -= 1;
					lastpos[8] += 2;

				} else {
					Sturn = 2;
				}

				break;
			}
			case 2: {
				lastpos[1] -= 0;
				lastpos[2] -= 1;

				lastpos[3] -= 1;
				lastpos[4] -= 0;

				lastpos[5] += 0;
				lastpos[6] += 1;

				lastpos[7] -= 1;
				lastpos[8] += 2;

				if (testturn()) {
					lastpos[1] += 0;
					lastpos[2] += 1;

					lastpos[3] += 1;
					lastpos[4] += 0;

					lastpos[5] -= 0;
					lastpos[6] -= 1;

					lastpos[7] += 1;
					lastpos[8] -= 2;

				} else {
					Sturn = 1;
				}

				break;
			}

			}
			break;
		}
		case 7: { // SR
			switch (SRturn) {
			case 1: {
				lastpos[1] += 0;
				lastpos[2] += 1;

				lastpos[3] += 1;
				lastpos[4] += 0;

				lastpos[5] -= 0;
				lastpos[6] -= 1;

				lastpos[7] += 1;
				lastpos[8] -= 2;

				if (testturn()) {
					lastpos[1] -= 0;
					lastpos[2] -= 1;

					lastpos[3] -= 1;
					lastpos[4] -= 0;

					lastpos[5] += 0;
					lastpos[6] += 1;

					lastpos[7] -= 1;
					lastpos[8] += 2;

				} else {
					SRturn = 2;
				}

				break;
			}
			case 2: {
				lastpos[1] -= 0;
				lastpos[2] -= 1;

				lastpos[3] -= 1;
				lastpos[4] -= 0;

				lastpos[5] += 0;
				lastpos[6] += 1;

				lastpos[7] -= 1;
				lastpos[8] += 2;

				if (testturn()) {
					lastpos[1] += 0;
					lastpos[2] += 1;

					lastpos[3] += 1;
					lastpos[4] += 0;

					lastpos[5] -= 0;
					lastpos[6] -= 1;

					lastpos[7] += 1;
					lastpos[8] -= 2;

				} else {
					SRturn = 1;
				}

				break;
			}
			}

			break;
		}

		}
		playb[lastpos[1]][lastpos[2]] = blocktype;
		playb[lastpos[3]][lastpos[4]] = blocktype;
		playb[lastpos[5]][lastpos[6]] = blocktype;
		playb[lastpos[7]][lastpos[8]] = blocktype;

	}

	private boolean testturn() {

		if (lastpos[2] >= 20
				|| lastpos[4] >= 20
				|| lastpos[6] >= 20
				|| lastpos[8] >= 20
				|| lastpos[1] >= 10
				|| lastpos[3] >= 10
				|| lastpos[5] >= 10
				|| lastpos[7] >= 10
				|| lastpos[1] < 0
				|| lastpos[3] < 0
				|| lastpos[5] < 0
				|| lastpos[7] < 0
				|| (playb[lastpos[1]][lastpos[2]] != 0
						|| playb[lastpos[3]][lastpos[4]] != 0
						|| playb[lastpos[5]][lastpos[6]] != 0 || playb[lastpos[7]][lastpos[8]] != 0)) {
			return true;

		} else {
			return false;
		}
	}

	void moveblockright() {
		if (!test) {
			blocktype = playb[lastpos[1]][lastpos[2]];

			if (lastpos[1] + 1 != 10 && lastpos[3] + 1 != 10
					&& lastpos[5] + 1 != 10 && lastpos[7] + 1 != 10) {
				if (!testrightbet()) {

					playb[lastpos[1]][lastpos[2]] = 0;
					playb[lastpos[3]][lastpos[4]] = 0;
					playb[lastpos[5]][lastpos[6]] = 0;
					playb[lastpos[7]][lastpos[8]] = 0;

					lastpos[1] += 1;
					lastpos[3] += 1;
					lastpos[5] += 1;
					lastpos[7] += 1;

					playb[lastpos[1]][lastpos[2]] = blocktype;
					playb[lastpos[3]][lastpos[4]] = blocktype;
					playb[lastpos[5]][lastpos[6]] = blocktype;
					playb[lastpos[7]][lastpos[8]] = blocktype;

				}
			}
		}
	}

	private boolean testrightbet() {

		if (playb[lastpos[1] + 1][lastpos[2]] != 0
				&& !new Point(lastpos[1] + 1, lastpos[2]).equals(new Point(
						lastpos[3], lastpos[4]))
				&& !new Point(lastpos[1] + 1, lastpos[2]).equals(new Point(
						lastpos[5], lastpos[6]))
				&& !new Point(lastpos[1] + 1, lastpos[2]).equals(new Point(
						lastpos[7], lastpos[8]))) {

			return true;
		}
		if (playb[lastpos[3] + 1][lastpos[4]] != 0
				&& !new Point(lastpos[3] + 1, lastpos[4]).equals(new Point(
						lastpos[1], lastpos[2]))
				&& !new Point(lastpos[3] + 1, lastpos[4]).equals(new Point(
						lastpos[5], lastpos[6]))
				&& !new Point(lastpos[3] + 1, lastpos[4]).equals(new Point(
						lastpos[7], lastpos[8]))) {

			return true;
		}
		if (playb[lastpos[5] + 1][lastpos[6]] != 0
				&& !new Point(lastpos[5] + 1, lastpos[6]).equals(new Point(
						lastpos[3], lastpos[4]))
				&& !new Point(lastpos[5] + 1, lastpos[6]).equals(new Point(
						lastpos[1], lastpos[2]))
				&& !new Point(lastpos[5] + 1, lastpos[6]).equals(new Point(
						lastpos[7], lastpos[8]))) {

			return true;
		}
		if (playb[lastpos[7] + 1][lastpos[8]] != 0
				&& !new Point(lastpos[7] + 1, lastpos[8]).equals(new Point(
						lastpos[3], lastpos[4]))
				&& !new Point(lastpos[7] + 1, lastpos[8]).equals(new Point(
						lastpos[5], lastpos[6]))
				&& !new Point(lastpos[7] + 1, lastpos[8]).equals(new Point(
						lastpos[1], lastpos[2]))) {

			return true;

		}

		return false;

	}

	void moveblockleft() {
		if (!test) {
			blocktype = playb[lastpos[1]][lastpos[2]];
			if (lastpos[1] != 0 && lastpos[3] != 0 && lastpos[5] != 0
					&& lastpos[7] != 0) {
				if (!testleftbeta()) {

					playb[lastpos[1]][lastpos[2]] = 0;
					playb[lastpos[3]][lastpos[4]] = 0;
					playb[lastpos[5]][lastpos[6]] = 0;
					playb[lastpos[7]][lastpos[8]] = 0;

					lastpos[1] -= 1;
					lastpos[3] -= 1;
					lastpos[5] -= 1;
					lastpos[7] -= 1;

					playb[lastpos[1]][lastpos[2]] = blocktype;
					playb[lastpos[3]][lastpos[4]] = blocktype;
					playb[lastpos[5]][lastpos[6]] = blocktype;
					playb[lastpos[7]][lastpos[8]] = blocktype;

				}
			}
		}
	}

	private boolean testleftbeta() {

		if (playb[lastpos[1] - 1][lastpos[2]] != 0
				&& !new Point(lastpos[1] - 1, lastpos[2]).equals(new Point(
						lastpos[3], lastpos[4]))
				&& !new Point(lastpos[1] - 1, lastpos[2]).equals(new Point(
						lastpos[5], lastpos[6]))
				&& !new Point(lastpos[1] - 1, lastpos[2]).equals(new Point(
						lastpos[7], lastpos[8]))) {

			return true;
		}
		if (playb[lastpos[3] - 1][lastpos[4]] != 0
				&& !new Point(lastpos[3] - 1, lastpos[4]).equals(new Point(
						lastpos[1], lastpos[2]))
				&& !new Point(lastpos[3] - 1, lastpos[4]).equals(new Point(
						lastpos[5], lastpos[6]))
				&& !new Point(lastpos[3] - 1, lastpos[4]).equals(new Point(
						lastpos[7], lastpos[8]))) {

			return true;
		}
		if (playb[lastpos[5] - 1][lastpos[6]] != 0
				&& !new Point(lastpos[5] - 1, lastpos[6]).equals(new Point(
						lastpos[3], lastpos[4]))
				&& !new Point(lastpos[5] - 1, lastpos[6]).equals(new Point(
						lastpos[1], lastpos[2]))
				&& !new Point(lastpos[5] - 1, lastpos[6]).equals(new Point(
						lastpos[7], lastpos[8]))) {

			return true;
		}
		if (playb[lastpos[7] - 1][lastpos[8]] != 0
				&& !new Point(lastpos[7] - 1, lastpos[8]).equals(new Point(
						lastpos[3], lastpos[4]))
				&& !new Point(lastpos[7] - 1, lastpos[8]).equals(new Point(
						lastpos[5], lastpos[6]))
				&& !new Point(lastpos[7] - 1, lastpos[8]).equals(new Point(
						lastpos[1], lastpos[2]))) {

			return true;

		}

		return false;

	}

	void nextposition() {
		// System.out.println("1.lastp " + lastpos[1] + "  2. lastp " +
		// lastpos[2]
		// + "  PLAY " + playb[lastpos[1]][lastpos[2]]);
		blocktype = playb[lastpos[1]][lastpos[2]];
		test = false;
		// teste ob die Steine den Boden errreicht haben
		// System.out.println("initiate bottom test");
		if (testbottom(lastpos[2], lastpos[4], lastpos[6], lastpos[8])) {
			test = true;
			resetTurns();
			
			shiftedround = false;
			
			noair = true;
			// System.out.println("bottomtest TRUE");
			;
		}

		if (!test) {
			// System.out.println("initiate nextpos test"); // returned true
			// wenn
			// keine der
			// nächsten
			// positionen
			// besetzt ist.
			if (testnextpos(lastpos[1], lastpos[2], lastpos[3], lastpos[4],
					lastpos[5], lastpos[6], lastpos[7], lastpos[8], false)) {
				noair = true;
				test = true;
				resetTurns();
				shiftedround = false;

				// System.out.println("nextpos test TRUE");

			}
			if (!test) {
				playb[lastpos[1]][lastpos[2]] = 0;
				playb[lastpos[3]][lastpos[4]] = 0;
				playb[lastpos[5]][lastpos[6]] = 0;
				playb[lastpos[7]][lastpos[8]] = 0;

				lastpos[2] += 1;
				lastpos[4] += 1;
				lastpos[6] += 1;
				lastpos[8] += 1;

				playb[lastpos[1]][lastpos[2]] = blocktype;
				playb[lastpos[3]][lastpos[4]] = blocktype;
				playb[lastpos[5]][lastpos[6]] = blocktype;
				playb[lastpos[7]][lastpos[8]] = blocktype;
			}
		}
	
	}

	private void resetTurns() {
		Iturn = 1;
		Tturn = 1;
		Lturn = 1;
		LRturn = 1;
		Sturn = 1;
		SRturn = 1;
	}

	private boolean testnextpos(int x1, int y1, int x2, int y2, int x3, int y3,
			int x4, int y4, boolean vision) {
		if (vision) {
			this.x1 = x1;
			this.x2 = x2;
			this.x3 = x3;
			this.x4 = x4;
			this.y1 = y1;
			this.y2 = y2;
			this.y3 = y3;
			this.y4 = y4;
		}
		if (playb[x1][y1 + 1] != 0
				&& !new Point(x1, y1 + 1).equals(new Point(x2, y2))
				&& !new Point(x1, y1 + 1).equals(new Point(x3, y3))
				&& !new Point(x1, y1 + 1).equals(new Point(x4, y4))) {
			// System.out.println("unter eins");

			return true;
		}
		if (playb[x2][y2 + 1] != 0
				&& !new Point(x2, y2 + 1).equals(new Point(x1, y1))
				&& !new Point(x2, y2 + 1).equals(new Point(x3, y3))
				&& !new Point(x2, y2 + 1).equals(new Point(x4, y4))) {
			// System.out.println("unter zwei");
			return true;
		}
		if (playb[x3][y3 + 1] != 0
				&& !new Point(x3, y3 + 1).equals(new Point(x2, y2))
				&& !new Point(x3, y3 + 1).equals(new Point(x1, y1))
				&& !new Point(x3, y3 + 1).equals(new Point(x4, y4))) {
			// System.out.println("unter drei");
			return true;
		}
		if (playb[x4][y4 + 1] != 0
				&& !new Point(x4, y4 + 1).equals(new Point(x2, y2))
				&& !new Point(x4, y4 + 1).equals(new Point(x3, y3))
				&& !new Point(x4, y4 + 1).equals(new Point(x1, y1))) {
			// System.out.println("unter vier");

			return true;

		}
		return false;
	}

	private boolean testbottom(int eins, int zwei, int drei, int vier) {
		if (eins + 1 == 20) {
			return true;
		}
		if (zwei + 1 == 20) {
			return true;
		}
		if (drei + 1 == 20) {
			return true;
		}
		if (vier + 1 == 20) {
			return true;
		} else {
			// System.out.println("bottom test failed");
			return false;
		}
	}

	private void addSRblock() {

		lastpos[1] = 6;
		lastpos[2] = 0;
		lastpos[3] = 6;
		lastpos[4] = 1;
		lastpos[5] = 5;
		lastpos[6] = 1;
		lastpos[7] = 5;
		lastpos[8] = 2;

		if (playb[lastpos[1]][lastpos[2]] == 0
				&& playb[lastpos[3]][lastpos[4]] == 0
				&& playb[lastpos[5]][lastpos[6]] == 0
				&& playb[lastpos[7]][lastpos[8]] == 0) {
			playb[lastpos[1]][lastpos[2]] = 7;
			playb[lastpos[3]][lastpos[4]] = 7;
			playb[lastpos[5]][lastpos[6]] = 7;
			playb[lastpos[7]][lastpos[8]] = 7;
			// System.out.println("SR-block played");

		} else {
			GameOver = true;
			Framework.gameState = Framework.GameState.GAMEOVER;
		}
	}

	private void addSblock() {

		lastpos[1] = 5;
		lastpos[2] = 0;
		lastpos[3] = 5;
		lastpos[4] = 1;
		lastpos[5] = 6;
		lastpos[6] = 1;
		lastpos[7] = 6;
		lastpos[8] = 2;

		if (playb[lastpos[1]][lastpos[2]] == 0
				&& playb[lastpos[3]][lastpos[4]] == 0
				&& playb[lastpos[5]][lastpos[6]] == 0
				&& playb[lastpos[7]][lastpos[8]] == 0) {
			playb[lastpos[1]][lastpos[2]] = 6;
			playb[lastpos[3]][lastpos[4]] = 6;
			playb[lastpos[5]][lastpos[6]] = 6;
			playb[lastpos[7]][lastpos[8]] = 6;
			// System.out.println("S-block played");
		} else {
			GameOver = true;
			Framework.gameState = Framework.GameState.GAMEOVER;
		}
	}

	private void addLRblock() {

		lastpos[1] = 6;
		lastpos[2] = 1;
		lastpos[3] = 5;
		lastpos[4] = 1;
		lastpos[5] = 4;
		lastpos[6] = 1;
		lastpos[7] = 4;
		lastpos[8] = 0;
		if (playb[lastpos[1]][lastpos[2]] == 0
				&& playb[lastpos[3]][lastpos[4]] == 0
				&& playb[lastpos[5]][lastpos[6]] == 0
				&& playb[lastpos[7]][lastpos[8]] == 0) {
			playb[lastpos[1]][lastpos[2]] = 5;
			playb[lastpos[3]][lastpos[4]] = 5;
			playb[lastpos[5]][lastpos[6]] = 5;
			playb[lastpos[7]][lastpos[8]] = 5;
			// System.out.println("LR-block played");
		} else {
			GameOver = true;
			Framework.gameState = Framework.GameState.GAMEOVER;
		}
	}

	private void addLblock() {

		lastpos[1] = 4;
		lastpos[2] = 1;
		lastpos[3] = 5;
		lastpos[4] = 1;
		lastpos[5] = 6;
		lastpos[6] = 1;
		lastpos[7] = 6;
		lastpos[8] = 0;
		if (playb[lastpos[1]][lastpos[2]] == 0
				&& playb[lastpos[3]][lastpos[4]] == 0
				&& playb[lastpos[5]][lastpos[6]] == 0
				&& playb[lastpos[7]][lastpos[8]] == 0) {
			playb[lastpos[1]][lastpos[2]] = 4;
			playb[lastpos[3]][lastpos[4]] = 4;
			playb[lastpos[5]][lastpos[6]] = 4;
			playb[lastpos[7]][lastpos[8]] = 4;
			// System.out.println("L-block played");
		} else {
			GameOver = true;
			Framework.gameState = Framework.GameState.GAMEOVER;
		}
	}

	private void addTblock() {

		lastpos[1] = 4;
		lastpos[2] = 1;
		lastpos[3] = 5;
		lastpos[4] = 0;
		lastpos[5] = 5;
		lastpos[6] = 1;
		lastpos[7] = 6;
		lastpos[8] = 1;
		if (playb[lastpos[1]][lastpos[2]] == 0
				&& playb[lastpos[3]][lastpos[4]] == 0
				&& playb[lastpos[5]][lastpos[6]] == 0
				&& playb[lastpos[7]][lastpos[8]] == 0) {

			playb[lastpos[1]][lastpos[2]] = 3;
			playb[lastpos[3]][lastpos[4]] = 3;
			playb[lastpos[5]][lastpos[6]] = 3;
			playb[lastpos[7]][lastpos[8]] = 3;
			// System.out.println("T-block played");
		} else {
			GameOver = true;
			Framework.gameState = Framework.GameState.GAMEOVER;
		}
	}

	private void addOblock() {

		lastpos[1] = 5;
		lastpos[2] = 0;
		lastpos[3] = 5;
		lastpos[4] = 1;
		lastpos[5] = 6;
		lastpos[6] = 0;
		lastpos[7] = 6;
		lastpos[8] = 1;
		if (playb[lastpos[1]][lastpos[2]] == 0
				&& playb[lastpos[3]][lastpos[4]] == 0
				&& playb[lastpos[5]][lastpos[6]] == 0
				&& playb[lastpos[7]][lastpos[8]] == 0) {
			playb[lastpos[1]][lastpos[2]] = 2;
			playb[lastpos[3]][lastpos[4]] = 2;
			playb[lastpos[5]][lastpos[6]] = 2;
			playb[lastpos[7]][lastpos[8]] = 2;
			// System.out.println("O-block played");
		} else {
			GameOver = true;
			Framework.gameState = Framework.GameState.GAMEOVER;
		}

	}

	private void addIblock() {
		lastpos[1] = 5;
		lastpos[2] = 0;
		lastpos[3] = 5;
		lastpos[4] = 1;
		lastpos[5] = 5;
		lastpos[6] = 2;
		lastpos[7] = 5;
		lastpos[8] = 3;
		if (playb[lastpos[1]][lastpos[2]] == 0
				&& playb[lastpos[3]][lastpos[4]] == 0
				&& playb[lastpos[5]][lastpos[6]] == 0
				&& playb[lastpos[7]][lastpos[8]] == 0) {
			playb[lastpos[1]][lastpos[2]] = 1;
			playb[lastpos[3]][lastpos[4]] = 1;
			playb[lastpos[5]][lastpos[6]] = 1;
			playb[lastpos[7]][lastpos[8]] = 1;
			// System.out.println("I-block played");

		} else {
			GameOver = true;
			Framework.gameState = Framework.GameState.GAMEOVER;
		}
	}

	private void newblock() {
		if (first){
		ablock = newrandomblock(); 	
		bblock = newrandomblock();
		cblock = newrandomblock();
		dblock = newrandomblock();
		nextblock = newrandomblock();
		first = false;
		}else{
			
			nextblock = ablock;
			ablock = bblock;
			bblock = cblock;
			cblock = dblock;
			dblock = newrandomblock();
			if (cblock == dblock){
				dblock = newrandomblock();
			}
		}

		choosenextblock(nextblock);

	}

	private int newrandomblock() {
		int block;
		block = ran.nextInt(8);
		
		while (block == 3) {
			block = ran.nextInt(8);
		}
		return block;
		
	}

	private void choosenextblock(int nextblock2) {
		switch (nextblock2) {
		case 0:
			// System.out.println("adde T");
			addTblock();
			break;

		case 1:
			addIblock();
			// System.out.println("adde I");
			break;
		case 2:
			addOblock();
			// System.out.println("adde O");
			break;
		case 4:
			addLblock();
			// System.out.println("adde L");
			break;
		case 5:
			addLRblock();
			// System.out.println("adde LR");
			break;
		case 6:
			addSblock();
			// System.out.println("adde S");
			break;
		case 7:
			addSRblock();
			// System.out.println("adde SR");
			break;
		}
	}

	/**
	 * Draw the game to the screen.
	 * 
	 * @param g2d
	 *            Graphics2D
	 * @param mousePosition
	 *            current mouse position.
	 */
	public void Draw(Graphics2D g2d, Point mousePosition) {
		
		g2d.drawImage(GameGroundpic,0,0,framewidth,frameheight,null);
		
		String string = String.valueOf(completelines);
		
		g2d.drawString(string+ " LINES CLEARED",10,10);
		for (int j = 0; j < playb.length; j++) {
			for (int k = 0; k < playb[j].length; k++) {
				// System.out.println(j + " "+ k +" blow");
				if (one){
				
				g2d.drawImage(empty, j * tilewidth + movefactorx, k * tileheight+ movefactory, tilewidth,
						tileheight, null);
				one = false;
				}
				else{g2d.drawImage(empty2, j * tilewidth + movefactorx, k * tileheight+ movefactory, tilewidth,
						tileheight, null);
				one = true;
					
				}


			}
			if(two){
				one = true;	
				two = false;
			}
			else{
				one = false;
			two = true;
			}
			
		}
		

			tileheight =(int)( frameheight / 21.65);
			tilewidth = tileheight;
			movefactorx = (int) ( framewidth / 2.82);
			movefactory = (int) (frameheight /37);
		
			g2d.setColor(Color.WHITE);
			g2d.drawRect(movefactorx-1,movefactory-1,tilewidth*10+1,tileheight *20+1);
			g2d.drawImage(visionimg, x1 * tilewidth+ movefactorx, y1 * tileheight + movefactory,
					tilewidth, tileheight, null);
			g2d.drawImage(visionimg, x2 * tilewidth+ movefactorx, y2 * tileheight+ movefactory,
					tilewidth, tileheight, null);
			g2d.drawImage(visionimg, x3 * tilewidth+ movefactorx, y3 * tileheight+ movefactory,
					tilewidth, tileheight, null);
			g2d.drawImage(visionimg, x4 * tilewidth+ movefactorx, y4 * tileheight+ movefactory,
					tilewidth, tileheight, null);

			for (int g = 0; g < playb.length; g++) {
				for (int h = 0; h < playb[g].length; h++) {
					switch (playb[g][h]) {
					case 0:

						break;
					case 1:
						g2d.drawImage(Iblock, g * tilewidth+ movefactorx, h * tileheight+ movefactory,
								tilewidth, tileheight, null);
						break;
					case 2:
						g2d.drawImage(Oblock, g * tilewidth+ movefactorx, h * tileheight+ movefactory,
								tilewidth, tileheight, null);
						break;
					case 3:
						g2d.drawImage(Tblock, g * tilewidth+ movefactorx, h * tileheight+ movefactory,
								tilewidth, tileheight, null);
						break;
					case 4:
						g2d.drawImage(Lblock, g * tilewidth+ movefactorx, h * tileheight+ movefactory,
								tilewidth, tileheight, null);
						break;
					case 5:
						g2d.drawImage(LRblock, g * tilewidth+ movefactorx, h * tileheight+ movefactory,
								tilewidth, tileheight, null);
						break;
					case 6:
						g2d.drawImage(Sblock, g * tilewidth+ movefactorx, h * tileheight+ movefactory,
								tilewidth, tileheight, null);
						break;
					case 7:
						g2d.drawImage(SRblock, g * tilewidth+ movefactorx, h * tileheight+ movefactory,
								tilewidth, tileheight, null);
						break;

					}
				}
				g2d.setColor(Color.WHITE);
				g2d.drawRect(movefactorx - 150, movefactory+ (tileheight* 3),100,100);
				g2d.setColor(Color.BLACK);
				g2d.fillRect(movefactorx - 149, movefactory+ (tileheight* 3)+1,98,98);

				switch (shiftedblock) {
				case 0:
					break;
				case 1:
					g2d.drawImage(IBlockcom, movefactorx - 149, movefactory+ (tileheight* 3)+1,98,98, null);
					break;
				case 2:
					g2d.drawImage(OBlockcom, movefactorx - 149, movefactory+ (tileheight* 3)+1,98,98, null);
					break;
				case 3:
					g2d.drawImage(TBlockcom,movefactorx - 149, movefactory+ (tileheight* 3)+1,98,98, null);
					break;
				case 4:
					g2d.drawImage(LBlockcom,movefactorx - 149, movefactory+ (tileheight* 3)+1,98,98, null);
					break;
				case 5:
					g2d.drawImage(LRBlockcom,movefactorx - 149, movefactory+ (tileheight* 3)+1,98,98, null);
					break;
				case 6:
					g2d.drawImage(SBlockcom,movefactorx - 149, movefactory+ (tileheight* 3)+1,98,98, null);
					break;
				case 7:
					g2d.drawImage(SRBlockcom,movefactorx - 149, movefactory+ (tileheight* 3)+1,98,98, null);
					break;

				}
				
				
				g2d.setColor(Color.WHITE);
				g2d.drawRect(movefactorx + (10*tilewidth)+30, movefactory+ (tileheight* 3),100,100);
				g2d.setColor(Color.BLACK);
				g2d.fillRect(movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+1,98,98);

				switch (ablock) {
				case 3:
					break;
				case 1:
					g2d.drawImage(IBlockcom, movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+1,98,98, null);
					break;
				case 2:
					g2d.drawImage(OBlockcom, movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+1,98,98, null);
					break;
				case 0:
					g2d.drawImage(TBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+1,98,98, null);
					break;
				case 4:
					g2d.drawImage(LBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+1,98,98, null);
					break;
				case 5:
					g2d.drawImage(LRBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+1,98,98, null);
					break;
				case 6:
					g2d.drawImage(SBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+1,98,98, null);
					break;
				case 7:
					g2d.drawImage(SRBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+1,98,98, null);
					break;

				}
				
				g2d.setColor(Color.WHITE);
				g2d.drawRect(movefactorx + (10*tilewidth)+30, movefactory+ (tileheight* 3)+120,100,100);
				g2d.setColor(Color.BLACK);
				g2d.fillRect(movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+121,98,98);

				switch (bblock) {
				case 3:
					break;
				case 1:
					g2d.drawImage(IBlockcom, movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+121,98,98, null);
					break;
				case 2:
					g2d.drawImage(OBlockcom, movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+121,98,98, null);
					break;
				case 0:
					g2d.drawImage(TBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+121,98,98, null);
					break;
				case 4:
					g2d.drawImage(LBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+121,98,98, null);
					break;
				case 5:
					g2d.drawImage(LRBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+121,98,98, null);
					break;
				case 6:
					g2d.drawImage(SBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+121,98,98, null);
					break;
				case 7:
					g2d.drawImage(SRBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+121,98,98, null);
					break;

				}
				
				g2d.setColor(Color.WHITE);
				g2d.drawRect(movefactorx + (10*tilewidth)+30, movefactory+ (tileheight* 3)+240,100,100);
				g2d.setColor(Color.BLACK);
				g2d.fillRect(movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+241,98,98);

				switch (cblock) {
				case 3:
					break;
				case 1:
					g2d.drawImage(IBlockcom, movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+241,98,98, null);
					break;
				case 2:
					g2d.drawImage(OBlockcom, movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+241,98,98, null);
					break;
				case 0:
					g2d.drawImage(TBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+241,98,98, null);
					break;
				case 4:
					g2d.drawImage(LBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+241,98,98, null);
					break;
				case 5:
					g2d.drawImage(LRBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+241,98,98, null);
					break;
				case 6:
					g2d.drawImage(SBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+241,98,98, null);
					break;
				case 7:
					g2d.drawImage(SRBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+241,98,98, null);
					break;

				}
				g2d.setColor(Color.WHITE);
				g2d.drawRect(movefactorx + (10*tilewidth)+30, movefactory+ (tileheight* 3)+360,100,100);
				g2d.setColor(Color.BLACK);
				g2d.fillRect(movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+361,98,98);

				switch (dblock) {
				case 3:
					break;
				case 1:
					g2d.drawImage(IBlockcom, movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+361,98,98, null);
					break;
				case 2:
					g2d.drawImage(OBlockcom, movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+361,98,98, null);
					break;
				case 0:
					g2d.drawImage(TBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+361,98,98, null);
					break;
				case 4:
					g2d.drawImage(LBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+361,98,98, null);
					break;
				case 5:
					g2d.drawImage(LRBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+361,98,98, null);
					break;
				case 6:
					g2d.drawImage(SBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+361,98,98, null);
					break;
				case 7:
					g2d.drawImage(SRBlockcom,movefactorx+ (10*tilewidth)+31, movefactory+ (tileheight* 3)+361,98,98, null);
					break;

				}
				
				
				if (GameOver) {
					 g2d.drawImage(GameOverpic,0,0,framewidth,frameheight,null);
				}
				

			}
			
		
	
	}

	public void moveleft() {
		moveleft = true;
	}

	public void moveright() {
		moveright = true;
	}

	public void movedown() {
		nextposition();

	}

	public void movenotleft() {
		moveleft = false;
	}

	public void movenotright() {
		moveright = false;
	}

	public void shiftblock() {
		resetTurns();
		if (!shiftedround) {
			
			noair = true;
			oldshiftedblock = shiftedblock;

			shiftedblock = playb[lastpos[1]][lastpos[2]];
			shiftedround = true;
			playb[lastpos[1]][lastpos[2]] = 0;
			playb[lastpos[3]][lastpos[4]] = 0;
			playb[lastpos[5]][lastpos[6]] = 0;
			playb[lastpos[7]][lastpos[8]] = 0;
			resetTurns();

		}

	}

}
