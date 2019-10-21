package entornoGrafico;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import game.Jugador;
import game.Partida;
import game.Personaje;
import objeto.CajaMisteriosa;
import objeto.DadoDorado;
import objeto.PistolaCongelante;
import objeto.GuanteBlanco;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 5146957524880202935L;
	private PanelJuego contentPane;
	// private JTable table;

	public VentanaJuego(Partida prod) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new PanelJuego(prod, this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("MARIO PARTY");
	}

	public static void main(String[] args) {
		int fps = 30;

		// Creo la partida con los jugadores
		ArrayList<Jugador> participantes = new ArrayList<Jugador>();
		Partida prod = new Partida(participantes, 50);

		VentanaJuego ventana = new VentanaJuego(prod);
		Jugador jug1 = new Jugador("Princess Peach");
		jug1.setPersonaje(new Personaje("peach", ventana));
		participantes.add(jug1);
		
		Jugador jug2 = new Jugador("Luigi");
		jug2.setPersonaje(new Personaje("luigi", ventana));
		participantes.add(jug2);
		
		participantes.get(0).addMochila_objetos(new DadoDorado());
		participantes.get(0).addMochila_objetos(new GuanteBlanco());
		participantes.get(0).addMochila_objetos(new PistolaCongelante());
		participantes.get(1).addMochila_objetos(new DadoDorado());

		ventana.setVisible(true);

		HiloActualizador hilo = new HiloActualizador(ventana.contentPane, 1000 / fps); // 1 segundo / 15 FPS = 66,66
																						// milisegundos
		hilo.start();

		prod.registrar(ventana.contentPane);
		prod.iniciarPartida();
	}
}
