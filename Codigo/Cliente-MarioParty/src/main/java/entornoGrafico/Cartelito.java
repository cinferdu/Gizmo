package entornoGrafico;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cartelito extends JPanel {
	private static final long serialVersionUID = 1860378282837338890L;

	private static final int POS_X = 20;
	private static final int POS_Y = 25;
	private static final int SEPARACION_Y = 15;

	private JLabel cartel;
	private ArrayList<String> cadenasAmostrar;

	public Cartelito() {
		cadenasAmostrar = new ArrayList<String>();
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		cartel = new JLabel(new ImageIcon(ImgExtra.CUADR_TEXTO));
		cartel.setBounds(0, 5, 200, 62);
		add(cartel);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.drawImage(ImgExtra.CUADR_TEXTO, 0, 0, null);
		for (int i = 0; i < cadenasAmostrar.size(); i++) {
			g.drawString(cadenasAmostrar.get(i), POS_X, POS_Y + i * SEPARACION_Y);
		}
	}

	private void mostrarTexto(String... lineasImprimir) {
		cadenasAmostrar.clear();

		for (String linea : lineasImprimir) {
			cadenasAmostrar.add(linea);
		}

		repaint();
	}

	public void mostrarIniciarTurno() {
		mostrarTexto("Haga clic sobre los dados", "para iniciar su turno");
	}
}
