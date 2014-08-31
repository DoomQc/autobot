package autoTrade;

import java.awt.Dimension;
import java.awt.Point;

public class boiteAOutils {
	
	public static int[] tailleEcran() {
		int i[] = new int[2];
        //récupérer la taille de l'écran
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        i[0]  = (int)dimension.getWidth();
        i[1] = (int)dimension.getHeight();
        return i;
	}
	
	
}
