package entornoGrafico;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import game.Jugador;
import java.awt.Font;

public class PuntajesVentana extends JFrame {

	private static final long serialVersionUID = 4095766066350057723L;
	private JPanel contentPane;
	private ImageIcon tabla_puntajes = new ImageIcon("img//tabla_puntajes.png");
	private ImageIcon titulo_puntajes = new ImageIcon("img//titulo_puntajes.png");
	private JLabel mLabel;
	private JLabel mLabel_tabla;
	private ArrayList<Jugador> jugadores;
	private Jugador ganador;

	public PuntajesVentana(ArrayList<Jugador> jugadores, Jugador ganador) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mLabel = new JLabel();
		mLabel.setBounds(10, 10, 900, 65);
		contentPane.add(mLabel);
		mLabel.setIcon(titulo_puntajes);
		mLabel.setVisible(true);
		mLabel_tabla = new JLabel();
		mLabel_tabla.setBounds(10, 70, 900, 600);
		contentPane.add(mLabel_tabla);
		mLabel_tabla.setIcon(tabla_puntajes);
		mLabel_tabla.setVisible(true);
		getContentPane().setBackground(Color.LIGHT_GRAY);

		this.jugadores = jugadores;
		this.ganador = ganador;

		repaint();
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.yellow);
		g2d.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 30));
		g2d.drawString("PUNTAJES", 350, 80);
		g2d.setColor(Color.orange);
		g2d.drawString("Nombre", 80, 180);
		g2d.drawString("Monedas", 700, 180);

		for (int i = 0; i < jugadores.size(); i++) {
			if (ganador.getNombre() == jugadores.get(i).getNombre()) {
				g2d.drawString("*", 55, 240 + 40 * i);
			}
			g2d.drawString(jugadores.get(i).getNombre(), 80, 240 + 40 * i);
			g2d.drawString(jugadores.get(i).getMonedas()+"", 700, 240 + 40 * i);
		}

	}

}
