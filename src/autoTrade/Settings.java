package autoTrade;
import java.awt.Color;


public class Settings {
	static int timeItems = 1000;
	static int timeOk = 300;
	static int delayOkPosX = 970;
	static int delayOkPosYStart = 619;
	static int delayOkPosYEnd = 632;
	static Color couleurBoutton = new Color(20,20,20);
	static int timeDoubleClic = 150;
	static int timeClic = 150;
	static int timeMove = 150;
	static int timeClicDroit = 250;
	
	public static void setTimeLent(){
		timeItems = 1500;
		timeOk = 500;
		timeDoubleClic = 300;
		timeClic = 500;
		timeMove = 200;
		timeClicDroit = 300;
	}
	
	public static void setTimeNormal(){
		timeItems = 1250;
		timeOk = 400;
		timeDoubleClic = 200;
		timeClic = 200;
		timeMove = 200;
		timeClicDroit = 250;
	}
	
	public static void setTimeRapide(){
		timeItems = 1000;
		timeOk = 300;
		timeDoubleClic = 150;
		timeClic = 150;
		timeMove = 200;
		timeClicDroit = 250;
	}
	
}
