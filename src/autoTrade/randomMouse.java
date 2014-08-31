package autoTrade;

import java.awt.Point;
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
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.Scanner;

public class randomMouse {
	Robot robot = new Robot();
	Scanner reader = new Scanner(System.in);
	int oSell[] = {360,160};
	int oBuy[] = {360,700};
	int mSell[] = {1280,240};
	int mBuy[] = {1280,660};
	Color Onorder =new Color(89, 89, 89);
	Color Onorder1 =new Color(88, 88, 88);
	Color Order =new Color(21, 21, 21);
	Color Myorder =new Color(25,25,89);
	Color Black =new Color(0,0,0,0);
    public randomMouse() throws AWTException {
        robot.setAutoDelay(50);
        robot.setAutoWaitForIdle(false);
        /**
         * Déplacement en forme de Z
         */
        boolean end;
        end = false;
        int nPos = oSell[1];
        while (end == false)
        {
        	robot.delay(500);
        	dclic(oSell[0],nPos);
            robot.delay(500);
            //do up
            uporder();
            //System.out.println(robot.getPixelColor(1280, 260));
            robot.mouseMove(oSell[0],nPos+20);
            robot.delay(500);
            System.out.println(robot.getPixelColor(oSell[0],nPos+20));
            if (!robot.getPixelColor(oSell[0],nPos+20).equals(Onorder)&&!robot.getPixelColor(oSell[0],nPos+20).equals(Onorder1)){
            	end = true;
            }
            nPos += 20;
        }
        nPos = 0;
    }
    
    private void uporder(){
    	if (!robot.getPixelColor(mSell[0],mSell[1]).equals(Myorder)){
            boolean mend;
            mend = false;
            int mPos = mSell[1];
            //prend le best price
    		clic(mSell[0],mSell[1]);
        	copy();
        	//get le price - 1
        	sub();
        	//find my order
        	while (mend == false){
        		if (robot.getPixelColor(oSell[0],mPos).equals(Myorder)){
                	dclic(oSell[0],mPos);
                	mend = true;
                }
        		mPos+=20;
        	}
        	//put the price
        	rclic(oSell[0],mPos);
        	clic(oSell[0]+10,mPos+20);
        	paste();
        	enter();
        	//check if box
        	if (robot.getPixelColor(1050,480).equals(Black)){
        		clic(950,640);
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
		robot.mousePress(InputEvent.BUTTON2_MASK);
        robot.mouseRelease(InputEvent.BUTTON2_MASK);

    }
    private void dclic(int x, int y){
    	robot.mouseMove(x, y);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
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
    private void sub(){
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
       }
}