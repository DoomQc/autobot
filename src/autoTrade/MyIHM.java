package autoTrade;


import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class MyIHM extends JFrame implements ActionListener, ChangeListener, MouseListener {
	
	
	//cr�ation de composants
	JFrame jfParametrage = new JFrame("Configuration");
	JPanel jpParametrage = new JPanel();
	
	JFrame jf = new JFrame("Automatic Trading");
	JPanel jp = new JPanel();
	
	JLabel jlInterval = new JLabel("Time Interval");
	SpinnerModel sm1 = new SpinnerNumberModel(0, 0, 1000, 1);
	JSpinner jsInterval = new JSpinner(sm1);

	JLabel jlBuyOrders = new JLabel("Buy Orders");
	SpinnerModel sm2 = new SpinnerNumberModel(0, 0, 1000, 1);
	JSpinner jsBuyOrders = new JSpinner(sm2);

	JLabel jlSellOrders = new JLabel("Sell Orders");
	SpinnerModel sm3 = new SpinnerNumberModel(0, 0, 1000, 1);
	JSpinner jsSellOrders = new JSpinner(sm3);

	JLabel jlHub = new JLabel("Hub");
	SpinnerModel sm4 = new SpinnerNumberModel(0, 0, 1000, 1);
	JSpinner jsHub = new JSpinner(sm4);

	JCheckBox jcb = new JCheckBox("Automatique");
	JButton jbLancer = new JButton(autoTrade.BUTTON_LAUNCH);
	JButton jbLog = new JButton(autoTrade.BUTTON_LOG);
	JButton jbQuitter = new JButton(autoTrade.BUTTON_QUIT);
	int iInterval = 0, iBuyOrders = 0, iSellOrders = 0, iHub = 0;

	public MyIHM() {
		createIHM();
	}
	
	public void createIHM()  {
		
		//creation de la frame de parametrage
		createParamFrame();
		
		//création de la frame de travail
		createWorkFrame();
	}

	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == jsInterval) setiInterval((((Integer) jsInterval.getValue()).intValue()));
		if(arg0.getSource() == jsBuyOrders) setiBuyOrders((((Integer) jsBuyOrders.getValue()).intValue()));
		if(arg0.getSource() == jsSellOrders) setiSellOrders((((Integer) jsSellOrders.getValue()).intValue()));
		if(arg0.getSource() == jsHub) setiHub((((Integer) jsHub.getValue()).intValue()));
	}
	
	public void actionPerformed(ActionEvent e) {
		//Action � l'appui du bouton LANCER 
		if(e.getActionCommand().equals(autoTrade.BUTTON_LAUNCH)) {
			System.out.println("Lancer");
		};
		
		//Action � l'appui du bouton QUITTER
		if(e.getActionCommand().equals(autoTrade.BUTTON_QUIT)) {
			System.out.println("Quitter");
            System.out.println(this.getiInterval());
            System.out.println(this.getiBuyOrders());
            System.out.println(this.getiSellOrders());
            System.out.println(this.getiHub());
			System.exit(0);
		};
		
		//Action � l'appui du bouton INDIQUER
		if(e.getActionCommand().equals(autoTrade.BUTTON_LOG)) {
			System.out.println("Indiquer");
		};
	}
	
	public int getiInterval() {
		return iInterval;
	}

	public int getiBuyOrders() {
		return iBuyOrders;
	}

	public int getiSellOrders() {
		return iSellOrders;
	}

	public int getiHub() {
		return iHub;
	}

	private void setiInterval(int iInterval) {
		this.iInterval = iInterval;
	}

	private void setiBuyOrders(int iBuyOrders) {
		this.iBuyOrders = iBuyOrders;
	}

	private void setiSellOrders(int iSellOrders) {
		this.iSellOrders = iSellOrders;
	}

	private void setiHub(int iHub) {
		this.iHub = iHub;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == jbLog){
			try {
				calibration ddd = new calibration();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(arg0.getSource() == jbLancer) {
			try {
				randomMouse test = new randomMouse();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(arg0.getSource() == jbQuitter){
			try {
				calibration ddd = new calibration();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == jbLog) System.out.println("Bouton Log mouseEntered");
		if(arg0.getSource() == jbLancer) System.out.println("Bouton Lancer mouseEntered");
		if(arg0.getSource() == jbQuitter) System.out.println("Bouton Quitter mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == jbLog) System.out.println("Bouton Log mouseExited");
		if(arg0.getSource() == jbLancer) System.out.println("Bouton Lancer mouseExited");
		if(arg0.getSource() == jbQuitter) System.out.println("Bouton Quitter mouseExited");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == jbLog) System.out.println("Bouton Log mousePressed");
		if(arg0.getSource() == jbLancer) System.out.println("Bouton Lancer mousePressed");
		if(arg0.getSource() == jbQuitter) System.out.println("Bouton Quitter mousePressed");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == jbLog) System.out.println("Bouton Log mouseReleased");
		if(arg0.getSource() == jbLancer) System.out.println("Bouton Lancer mouseReleased");
		if(arg0.getSource() == jbQuitter) System.out.println("Bouton Quitter mouseReleased");
	}
	
	public void createParamFrame() {
		
		
		System.out.println("création de la fenêtre de paramétrage");

		jfParametrage.setLayout(new GridLayout());
		jfParametrage.setSize(100,boiteAOutils.tailleEcran()[1]);
		jfParametrage.setAlwaysOnTop(true);
		jfParametrage.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jfParametrage.setDefaultLookAndFeelDecorated(true);
		
		jpParametrage.setVisible(true);
		
		jfParametrage.add(jpParametrage);
				
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();

		//If translucent windows aren't supported, exit.
		if (!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
			System.err.println("Translucency is not supported");
			System.exit(0);
		}
		else jf.setVisible(true);
	}
	
	public void createWorkFrame() {
		jf.setLayout(new GridLayout());
		jf.setSize(300, 350);
		jf.setAlwaysOnTop(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jf.setDefaultLookAndFeelDecorated(true);
		
		jp.setLayout(new GridLayout(7,2));
		//Partie Intervalles de temps
		jp.add(jlInterval);
		//Listener du JSpinner
		jsInterval.addChangeListener(this);
		jp.add(jsInterval);
		
		//Partie Buy Orders
		jp.add(jlBuyOrders);
		//Listener du JSpinner
		jsBuyOrders.addChangeListener(this);
		jp.add(jsBuyOrders);
		
		//Partie Sell Orders
		jp.add(jlSellOrders);
		//Listener du JSpinner
		jsSellOrders.addChangeListener(this);
		jp.add(jsSellOrders);
		
		//Partie Hub
		jp.add(jlHub);
		//Listener du JSpinner
		jsHub.addChangeListener(this);
		jp.add(jsHub);
		
		//Partie CheckBox Automatique
		jp.add(jcb);
		
		//Listener du bouton Indication
		jbLog.addMouseListener(this);
		jbLog.addActionListener(this);
		jp.add(jbLog);
		
		//Listener du bouton Lancer
		jbLancer.addMouseListener(this);
		jbLancer.addActionListener(this);
		jp.add(jbLancer);
		
		//Listener du bouton Quitter
		jbQuitter.addMouseListener(this);
		jbQuitter.addActionListener(this);
		
		jp.add(jbQuitter);
		
		jf.add(jp);		
		jf.setVisible(true);

	}
}
