package autoTrade;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

public class boiteAOutils {
	
	/**
	 * 
	 * @return un tableau de 2 int repr�sentant la taille de l'�cran
	 */
	public static int[] tailleEcran() {
		int i[] = new int[2];
        //récupérer la taille de l'écran
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        i[0]  = (int)dimension.getWidth();
        i[1] = (int)dimension.getHeight();
        return i;
	}
	
	
	/*
	 * g�n�re un int al�atoire entre 0 et iRandomNum
	 * 
	 * @param iRandomNum
	 * @return renvoie un nombre al�atoire entre 0 et le param�tre 
	 */
	public static int randInt(int iRandomNum) {
		return new Random().nextInt(iRandomNum);
	}
	
	
	/*
	 * g�n�re un int al�atoire entre 2 nombres pass�s en param�tre
	 * 
	 * @param min limite minimale de la plage
	 * @param max limite maximale de la plage 
	 * @return renvoie un nombre al�atoire entre min et max 
	 */	
	public static int randInt(int min, int max) {
	    return new Random().nextInt((max - min) + 1) + min;
	}
}
