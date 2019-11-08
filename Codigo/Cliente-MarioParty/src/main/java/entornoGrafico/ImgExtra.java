package entornoGrafico;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

// Imagenes Adicionales y funciones
public class ImgExtra {
	
	public final static Image FONDO = new ImageIcon("img//background.png").getImage();
	public final static Image BOTON_DADO = new ImageIcon("img//boton_dado.png").getImage();
	public final static Image CUADR_TEXTO = modificarTamanio(new ImageIcon("img//tabla_puntajes.png").getImage(),200, 60);
	
	public static Image modificarTamanio( Image imagen, int ancho, int alto) {
		return imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
	}
	
	public static Image modificarTamanioRapido( Image imagen, int ancho, int alto) {
		return imagen.getScaledInstance(ancho, alto, Image.SCALE_FAST);
	}
	 
	public static Image recortarImagen(Image img, Rectangle rect) {
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		return bimage.getSubimage(rect.x, rect.y, rect.width, rect.height);
	}

}
