package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Dado {
	public static final Image CARA_UNO = new ImageIcon(Dado.class.getResource("/img/cara1.png")).getImage();
	public static final Image CARA_DOS = new ImageIcon(Dado.class.getResource("/img/cara2.png")).getImage();
	public static final Image CARA_TRES = new ImageIcon(Dado.class.getResource("/img/cara3.png")).getImage();
	public static final Image CARA_CUATRO = new ImageIcon(Dado.class.getResource("/img/cara4.png")).getImage();
	public static final Image CARA_CINCO = new ImageIcon(Dado.class.getResource("/img/cara5.png")).getImage();
	public static final Image CARA_SEIS = new ImageIcon(Dado.class.getResource("/img/cara6.png")).getImage();

	public static int lanzarDado() {
		return (int) (Math.random() * 6 + 1);
	}

	public static int lanzarDado(int caras) {
		return (int) (Math.random() * caras + 1);
	}

	public static Image getImgCara(int nroPasos) {
		switch (nroPasos) {
		case 1:
			return CARA_UNO;
		case 2:
			return CARA_DOS;
		case 3:
			return CARA_TRES;
		case 4:
			return CARA_CUATRO;
		case 5:
			return CARA_CINCO;
		case 6:
			return CARA_SEIS;
		default:
			return null;
		}
	}

}
