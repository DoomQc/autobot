package autoTrade;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class Trading {
	
	static Timer t = new Timer();
	static TimerTask autoTop;
	static TimerTask autoBottom;
	static boolean actif = false;
	static Robot r;
	static int item;
	static int nbrSellOrders;
	static int nbrBuyOrders;
	//static int order;
	

	public static void top(){
		int x = 441;
		
				try {
					
					r = new Robot();
					r.setAutoDelay(20);
					String sel;
					int val;
					
					
					for(int order = 0 ; order < 19 ; order++){
						
						//Détecte la mienne
						if(isMineFirst(r, order, "sell") == false){
					
							//Double click sur la première order
							doubleClic(420, 236);
							r.delay(850);
					
							//Click sur advanced
							int i = 430;
							while(i <= 490 && r.getPixelColor(910, i).equals(new Color(25,25,25)) == false){
								i++;
							}			
							clic(910, i);			
					
							//Double click sur le prix pour le sélectionner
							doubleClic(715, 335);
					
							//CTRL+C pour copier le prix
							r.keyPress(KeyEvent.VK_CONTROL);
							r.keyPress(KeyEvent.VK_C);
							r.delay(100);
							r.keyRelease(KeyEvent.VK_CONTROL);
							r.keyRelease(KeyEvent.VK_C);
					
							//Click sur le boutton annuler
							clic(850,640);
					
							//On met le presse papier dans un string puis un int pour prenre la valeur en dessous		
							sel = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
							val = Integer.parseInt(sel.substring(0, sel.length()-3));
							val--;
					
							//On met cette valeur dans le presse parpier
							Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(String.valueOf(val)), null);
					
							//Click droit sur mon order	
							clicDroit(x, 240 + 20 * order);
					
							//Click sur modifier
							clic(x+20, (240 + 20 * order) + 20);
					
							//CTRL+V pour coller ma valeur
							r.keyPress(KeyEvent.VK_CONTROL);
							r.keyPress(KeyEvent.VK_V);
							r.delay(100);
							r.keyRelease(KeyEvent.VK_CONTROL);
							r.keyRelease(KeyEvent.VK_V);
					
							//Click sur OK
							clic(817, 901);
							r.delay(Settings.timeOk);
					
							int y = Settings.delayOkPosYStart;
							
							while(y <= Settings.delayOkPosYEnd){
								if(r.getPixelColor(Settings.delayOkPosX, y).equals(Settings.couleurBoutton)){
									clic(Settings.delayOkPosX, y);
									y = Settings.delayOkPosYEnd;
								}
								y++;
							}
							
							clic(300, 220 + (item +1) * 20);					
						}
					}					
					
					item++;
				} catch (UnsupportedFlavorException | IOException | AWTException e) {
					e.printStackTrace();				
		}	
		
	}
	
	public static void AutoSell(final int nombreItems) {
		
		autoTop = new TimerTask(){
			@Override
			public void run() {
				try {				
					Robot r = new Robot();		
					item = 0;					
					
					//Ouvrir l'onglet vente
					clic(300, 200);
					//Parcours les items
					clic(300, 220);
					r.delay(Settings.timeItems);
					top();
					//item++;
					
					while(item < nombreItems){						
						r.keyPress(KeyEvent.VK_DOWN);
						r.delay(Settings.timeItems);						
						top();
						//item++;	
					}
					
					//Fermer l'onglet vente
					clic(300, 200);
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
		};
		t.schedule(autoTop, 0);
}

	public static void clic(int x, int y){
		
		try {
			Robot r = new Robot();
			r.mouseMove(x, y);
			r.delay(Settings.timeMove);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			r.delay(Settings.timeClic);
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void doubleClic(int x, int y){
		
		try {
			Robot r = new Robot();
			r.mouseMove(x, y);
			r.delay(Settings.timeMove);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			r.delay(Settings.timeDoubleClic);
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void clicDroit(int x, int y){
		try {
			Robot r = new Robot();
			r.mouseMove(x, y);
			r.delay(Settings.timeMove);
			r.mousePress(InputEvent.BUTTON3_MASK);
			r.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			r.delay(Settings.timeClicDroit);
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void stop(){
		actif = false;
	}
	
	public static void sell(){
		
		try { 
			nbrSellOrders = Integer.parseInt(Fenetre.getSellOrders());
			if(nbrSellOrders == 0)
				nbrSellOrders = 1;
		} 
		catch (Exception e) {
			nbrSellOrders = 1;
		}
		
		actif = true;
		AutoSell(nbrSellOrders);
	}

	public static boolean isMineFirst(Robot r, int order, String type){
		
		if(type == "sell"){
			if(r.getPixelColor(420, 236 + order * 20).equals(new Color(24,24,88)) || r.getPixelColor(420, 670 + order * 20).equals(new Color(25,25,89))){
				if(order != 0)
					return false;
				else
					return true;
			}else
				return true;
			
		}else if(type == "buy"){
			if(r.getPixelColor(420, 670 + order * 20).equals(new Color(24,24,88)) || r.getPixelColor(420, 670 + order * 20).equals(new Color(25,25,89))){
				if(order != 0)		
					return false;
				else
					return true;
			}					
			else
				return true;
		}
		
		return false;				
	}

	public static void buy(){
		try { 
			nbrBuyOrders = Integer.parseInt(Fenetre.getBuyOrders());
			if(nbrBuyOrders == 0)
				nbrBuyOrders = 1;
		} 
		catch (Exception e) {
			nbrBuyOrders = 1;
		}
		
		AutoBuy(nbrBuyOrders);
		actif = true;
	}
	
	public static void AutoBuy(final int nombreItems){
		autoBottom = new TimerTask(){
			@Override
			public void run() {
				try {				
					Robot r = new Robot();		
					item = 0;				
					
					//Ouvrir l'onglet achat
					clic(300, 180);
					
					//Parcours les items
					clic(300, 200);
					r.delay(Settings.timeItems);
					bottom();
					//item++;
					
					while(item < nombreItems){						
						r.keyPress(KeyEvent.VK_DOWN);
						r.delay(Settings.timeItems);						
						bottom();
						//item++;												
					}
					
					//Fermer l'onglet achat
					clic(300, 180);
					
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
		};
		t.schedule(autoBottom, 0);
	}
	
	public static void bottom(){
		int x = 441;
		
		try {
			
			r = new Robot();
			r.setAutoDelay(20);
			String sel;
			int val;			
			
			for(int order = 0 ; order < 17 ; order++){
				
				//Détecte la mienne
				if(!isMineFirst(r, order, "buy")){
			
					if(r.getPixelColor(972, 619).equals(new Color(20,20,20)))
						clic(972, 619);					
			
					if(r.getPixelColor(970, 627).equals(new Color(20,20,20)))
						clic(970, 627);
					
			
					//Double click sur la première order
					doubleClic(441, 670);
					r.delay(850);
			
					//Click sur advanced
					int i = 430;
					while(i <= 490 && r.getPixelColor(910, i).equals(new Color(25,25,25)) == false){
						i++;
					}			
					clic(910, i);			
			
					//Double click sur le prix pour le sélectionner
					doubleClic(715, 335);
			
					//CTRL+C pour copier le prix
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_C);
					r.delay(100);
					r.keyRelease(KeyEvent.VK_CONTROL);
					r.keyRelease(KeyEvent.VK_C);
			
					//Click sur le boutton annuler
					clic(850,640);
			
					//On met le presse papier dans un string puis un int pour prenre la valeur en dessous		
					sel = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
					val = Integer.parseInt(sel.substring(0, sel.length()-3));
					val++;
			
					//On met cette valeur dans le presse parpier
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(String.valueOf(val)), null);
			
					//Click droit sur mon order	
					clicDroit(x, 670 + 20*order);
			
					//Click sur modifier
					clic(x + 20, (670 + 20*order) + 10);
			
					//CTRL+V pour coller ma valeur
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_V);
					r.delay(100);
					r.keyRelease(KeyEvent.VK_CONTROL);
					r.keyRelease(KeyEvent.VK_V);
			
					//Click sur OK
					clic(817, 901);
					r.delay(Settings.timeOk);
					
					int y = Settings.delayOkPosYStart;
					
					while(y <= Settings.delayOkPosYEnd){
						if(r.getPixelColor(Settings.delayOkPosX, y).equals(Settings.couleurBoutton)){
							clic(Settings.delayOkPosX, y);
							y = Settings.delayOkPosYEnd;
						}
						y++;
					}
					
					clic(300, 200 + (item) * 20);
					r.delay(Settings.timeItems);
				}
			}					
			
			item++;
		} catch (UnsupportedFlavorException | IOException | AWTException e) {
			e.printStackTrace();
		
		}
		
		
		
	}

}

