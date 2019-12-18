package gameframework;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader implements Runnable{

	
	String pic;
	int zahl;
	Image image;
	public ImageLoader(String pic,int zahl)
	{
		this.pic = pic;
		this.zahl = zahl;
	}
	
	@Override
	public void run() {
		 try {
			loadImage();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(" BILD " +zahl +" WURDE NICHT GELADEN");
		}
		 
		 switch (zahl){
		 case 1:{
			 Game.visionimg = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 2:{
			 Game.empty = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 3:{
			 Game.Iblock = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 4:{
			 Game.Lblock = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 5:{
			 Game.LRblock = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 6:{
			 Game.Oblock = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 7:{
			 Game.Sblock = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 8:{
			 Game.SRblock = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 9:{
			 Game.Tblock = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 10:{
			 Game.GameOverpic = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 11:{
			 Game.GameGroundpic = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 12:{
			 Game.empty2 = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 13:{
			 Game.IBlockcom = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 14:{
			 Game.LBlockcom = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 15:{
			 Game.LRBlockcom = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 16:{
			 Game.OBlockcom = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 17:{
			 Game.SBlockcom = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 18:{
			 Game.SRBlockcom = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 19:{
			 Game.TBlockcom = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 case 20:{
			 Game.empty2 = (BufferedImage) image;
			 Game.loaded++;
			 break;
		 }
		 
			 
		 }
		
		
		
		
	}

	private void loadImage() throws IOException{
		 image = ImageIO.read(Game.class.getClassLoader()
				.getResourceAsStream(pic));
		
	}
	

}
