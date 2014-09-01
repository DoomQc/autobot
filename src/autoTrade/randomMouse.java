package autoTrade;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Toolkit; 
import java.awt.datatransfer.DataFlavor; 
import java.awt.datatransfer.StringSelection; 
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Random;

public class randomMouse {
	Robot robot = new Robot();
	Scanner reader = new Scanner(System.in);
	String nextorder = "nextorder";
	String jeup = "jeup";
	String Copydepart = "Copydepart";
	String copyfin = "copyfin";
	String Checkifbox = "Checkifbox";
	String myorderfind = "myorderfind";
	int oSell[] = {360,150};
	int oBuy[] = {360,690};
	int mSell[] = {1280,230};
	int mBuy[] = {1280,666};
	int mBox[] = {950,615};
	Color Onorder =new Color(58,58,58);
	Color Font =new Color(21, 21, 21);
	Color Myorder =new Color(24,24,88);
	Color onMyorder =new Color(52,52,82);
	Color Black =new Color(0,0,0);
	
    public randomMouse() throws AWTException {
    	while(oSell[0]==360){
	        boolean end;
	        end = false;
	        int nPos = oSell[1];
	        
	        while (end == false&&nPos < 800)
	        {
	        	clic(oSell[0],nPos);
	        	robot.delay(random(300,500));
	        	dclic(oSell[0],nPos);
	        	robot.delay(random(500,1500));
	            //do up
	            uporder();
	            robot.delay(random(1000,1500));
	            //System.out.println(robot.getPixelColor(1280, 260));
	            robot.mouseMove(oSell[0],nPos+20);
	            robot.delay(random(300,500));
	            System.out.println(nextorder);
	            //System.out.println(robot.getPixelColor(oSell[0],nPos+20));
	            if (!robot.getPixelColor(oSell[0],nPos+20).equals(Onorder)){
	            	end = true;
	            }
	            nPos += 20;
	        }
	        nPos = 0;
	        robot.delay(random(300000,900000));
	    }
    }
    private void uporder(){
    	//System.out.println(robot.getPixelColor(mSell[0],mSell[1])); 
    	if (!robot.getPixelColor(mSell[0],mSell[1]).equals(Myorder)){
    		System.out.println(jeup);
            boolean mend;
            mend = false;
            int mPos = mSell[1];
            //prend le best price
    		clic(mSell[0],mSell[1]);
    		robot.delay(random(500,800));
        	copy();
        	robot.delay(random(300,500));
        	//get le price - 1
        	sub();
        	robot.delay(random(300,500));
        	//find my order
        	while (mend == false&&mPos<400){
        		//System.out.println(robot.getPixelColor(mSell[0],mPos));

        		if (robot.getPixelColor(mSell[0],mPos).equals(Myorder)){
        			System.out.println(myorderfind);
                	mend = true;
                }
        		mPos+=20;
        	}
        	//put the price
        	if(mPos<300){
	        	rclic(mSell[0],mPos-20);
	        	robot.delay(random(300,800));
	        	clic(mSell[0]+10,mPos);
	        	robot.delay(random(1000,1500));
	        	paste();
	        	robot.delay(random(400,1000));
	        	enter();
	        	robot.delay(random(1000,2000));
	        	//check if box
	        	System.out.println(Checkifbox);
	        	//System.out.println(robot.getPixelColor(1050,480));        	
	        	if (robot.getPixelColor(mBox[0],mBox[1]).equals(Black)){
	        		clic(mBox[0],mBox[1]);
	        	}
        	}
        }
    }

	private void clic(int x, int y){
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

    }
	private void rclic(int x, int y){
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);

    }
    private void dclic(int x, int y){
    	robot.mouseMove(x, y);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(100);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    private void copy(){
    	robot.keyPress(KeyEvent.VK_CONTROL ); 
    	robot.keyPress(KeyEvent.VK_C); 
    	robot.keyRelease(KeyEvent.VK_CONTROL );
    	robot.keyRelease(KeyEvent.VK_C);
    }
    private void paste(){
    	robot.keyPress(KeyEvent.VK_CONTROL ); 
    	robot.keyPress(KeyEvent.VK_V); 
    	robot.keyRelease(KeyEvent.VK_CONTROL );
    	robot.keyRelease(KeyEvent.VK_V);
    }
    private void enter(){
    	robot.keyPress(KeyEvent.VK_ENTER ); 
    	robot.keyPress(KeyEvent.VK_V); 
    	robot.keyRelease(KeyEvent.VK_ENTER );
    	robot.keyRelease(KeyEvent.VK_V);
    }
    private static int random(int Min, int Max){
    	Random rand = new Random();
    	int random = rand.nextInt(Max - Min + 1) + Min;
    	return random;
    }
    
    private void sub(){
    	System.out.println(Copydepart);
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) { 
         String text = null;
         String nb ="";
         long cost = 0;
      try {
       text = (String)t.getTransferData(DataFlavor.stringFlavor);
      } catch (UnsupportedFlavorException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
      } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
      } 
      int c=0;
      int pos=0;
         while(c!=2){
          if(text.charAt(pos)=='\t') c+=1;
          pos+=1;
         }
         StringBuilder sb = new StringBuilder();
         while(text.charAt(pos)!=','){       
          if(text.charAt(pos)!=' '){
           sb.append(text.charAt(pos));
          }
          pos+=1;
          System.out.println(text.charAt(pos+1));
         }
         nb= sb.toString();
         if(nb.length()>3){
          String s = nb.substring(nb.length()-4,nb.length()-3);
          nb=nb.replaceAll(s, "");
         }
         System.out.println(nb);
         cost = Long.parseLong(nb);
         cost--;
         System.out.println(cost);
         DecimalFormat df = new DecimalFormat("############.00");
         nb = df.format(cost);

         StringSelection ss = new StringSelection(nb); 
         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null); }
        	System.out.println(copyfin);
       }
}