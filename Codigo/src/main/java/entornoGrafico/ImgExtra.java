package entornoGrafico;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

// Imagenes Adicionales y funciones
public class ImgExtra {
	
	public final static Image FONDO = modificarTamanio(new ImageIcon("img//background.png").getImage(), 730, 550);
	public final static Image BOTON_DADO = modificarTamanio(new ImageIcon("img//boton_dado_t.png").getImage(),100, 100);
	public final static Image CUADR_TEXTO = modificarTamanio(new ImageIcon("img//tabla_puntajes.png").getImage(),200, 60);
	
	public static Image modificarTamanio( Image imagen, int ancho, int alto) {
		return imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
	}
	 
	public static Image recortarImagen(Image img, Rectangle rect) {
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		return bimage.getSubimage(rect.x, rect.y, rect.width, rect.height);
	}

}
