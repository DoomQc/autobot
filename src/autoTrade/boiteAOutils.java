package autoTrade;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

public class boiteAOutils {
	
	/**
	 * 
	 * @return un tableau de 2 int représentant la taille de l'écran
	 */
	public static int[] tailleEcran() {
		int i[] = new int[2];
        //rÃ©cupÃ©rer la taille de l'Ã©cran
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        i[0]  = (int)dimension.getWidth();
        i[1] = (int)dimension.getHeight();
        return i;
	}
	
	
	/*
	 * génère un int aléatoire entre 0 et iRandomNum
	 * 
	 * @param iRandomNum
	 * @return renvoie un nombre aléatoire entre 0 et le paramètre 
	 */
	public static int randInt(int iRandomNum) {
		return new Random().nextInt(iRandomNum);
	}
	
	
	/*
	 * génère un int aléatoire entre 2 nombres passés en paramètre
	 * 
	 * @param min limite minimale de la plage
	 * @param max limite maximale de la plage 
	 * @return renvoie un nombre aléatoire entre min et max 
	 */	
	public static int randInt(int min, int max) {
	    return new Random().nextInt((max - min) + 1) + min;
	}
}
