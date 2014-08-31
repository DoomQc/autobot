package autoTrade;
import java.awt.Point;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
public class calibration {
	Robot robotc = new Robot();
	public calibration() throws AWTException {
		
		clic(320,180);
	}
	private void clic(int x, int y){
    	robotc.mouseMove(x, y);
    	robotc.mousePress(InputEvent.BUTTON1_MASK);
        robotc.mouseRelease(InputEvent.BUTTON1_MASK);
        robotc.delay(500);
        robotc.mousePress(InputEvent.BUTTON1_MASK);
        robotc.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}

lolipop
