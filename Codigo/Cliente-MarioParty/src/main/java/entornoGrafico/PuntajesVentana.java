package entornoGrafico;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import game.Jugador;
import mensaje.MsjIngresarLobby;

public class PuntajesVentana extends JFrame {

	private static final int MONEDA_INICIO_X = 650;
	private static final int NOMBRE_INICIO_X = 200;
	private static final int NOMBRE_SEPARACION = 60;
	private static final int TITULO_Y = 180;

	private static final long serialVersionUID = 4095766066350057723L;
	private JPanel contentPane;
	private ImageIcon tabla_puntajes = new ImageIcon(PuntajesVentana.class.getResource("/img/tabla_puntajes.png"));
	private ImageIcon titulo_puntajes = new ImageIcon(PuntajesVentana.class.getResource("/img/titulo_puntajes.png"));
	private JLabel mLabel;
	private JLabel mLabel_tabla;
	private ArrayList<Jugador> jugadores;
	private Jugador ganador;
	
	private Cliente cliente;

	public PuntajesVentana(Cliente client, ArrayList<Jugador> jugadores, Jugador ganador) {
		cliente = client;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 920, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

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

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "¿Desea volver al Lobby?",
						"Salir", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					cliente.enviarMensaje(new MsjIngresarLobby());
				}
			}
		});
		
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.yellow);
		g2d.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 30));
		g2d.drawString("PUNTAJES FINALES", 280, 80);
		g2d.setColor(Color.black);
		g2d.drawString("Nombre", NOMBRE_INICIO_X, TITULO_Y);
		g2d.drawString("Monedas", MONEDA_INICIO_X, TITULO_Y);

		for (int i = 0; i < jugadores.size(); i++) {
			if (ganador.getNombre().equals(jugadores.get(i).getNombre())) {
				g2d.setColor(Color.white);
				g2d.drawString("WINNER", 45, 240 + NOMBRE_SEPARACION * i);
				g2d.setColor(Color.BLACK);
			}
			g2d.drawString(jugadores.get(i).getNombre(), NOMBRE_INICIO_X, 240 + NOMBRE_SEPARACION * i);
			g2d.drawString(jugadores.get(i).getMonedas() + "", MONEDA_INICIO_X, 240 + NOMBRE_SEPARACION * i);
		}

	}

}
