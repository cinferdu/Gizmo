package entornoGrafico;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DadoImagen {
	private static BufferedImage bufferedImage = null;
	private final static int WIDTH_DADO = 90;
	private final static int HEIGHT_DADO = 80;
	private final static int DADO1_X = 8;
	private final static int DADO1_Y = 93;
	private final static int DADO2_X = 103;
	private final static int DADO2_Y = 93;
	private final static int DADO3_X = 199;
	private final static int DADO3_Y = 93;
	private final static int DADO4_X = 296;
	private final static int DADO4_Y = 93;
	private final static int DADO5_X = 8;
	private final static int DADO5_Y = 172;
	private final static int DADO6_X = 103;
	private final static int DADO6_Y = 172;
	
	public static Image getDadoImagen(int cara_del_dado) {
		
		switch (cara_del_dado) {
		case 1:
			return new ImageIcon(cortarImagen(getBufferedImage(), new Rectangle(DADO1_X, DADO1_Y, WIDTH_DADO, HEIGHT_DADO))).getImage();
		case 2:
			return new ImageIcon(cortarImagen(getBufferedImage(), new Rectangle(DADO2_X, DADO2_Y, WIDTH_DADO, HEIGHT_DADO))).getImage();
		case 3:
			return new ImageIcon(cortarImagen(getBufferedImage(), new Rectangle(DADO3_X, DADO3_Y, WIDTH_DADO, HEIGHT_DADO))).getImage();
		case 4:
			return new ImageIcon(cortarImagen(getBufferedImage(), new Rectangle(DADO4_X, DADO4_Y, WIDTH_DADO, HEIGHT_DADO))).getImage();
		case 5:
			return new ImageIcon(cortarImagen(getBufferedImage(), new Rectangle(DADO5_X, DADO5_Y, WIDTH_DADO, HEIGHT_DADO))).getImage();
		case 6:
			return new ImageIcon(cortarImagen(getBufferedImage(), new Rectangle(DADO6_X, DADO6_Y, WIDTH_DADO, HEIGHT_DADO))).getImage();
		default:
			return null;
		}
	}
	
	private static BufferedImage getBufferedImage() {
		if (bufferedImage == null) {
			try {
				bufferedImage  = ImageIO.read(new File(".\\src\\img\\dados_transparentes.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bufferedImage;
	}
	
	private static BufferedImage cortarImagen(BufferedImage src, Rectangle rect) {
	      BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
	      return dest; 
	}
}
