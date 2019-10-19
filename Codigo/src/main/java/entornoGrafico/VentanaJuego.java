package entornoGrafico;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import game.Jugador;
import game.Partida;
import objeto.CajaMisteriosa;
import objeto.DadoDorado;
import objeto.PistolaCongelante;
import objeto.GuanteBlanco;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 5146957524880202935L;
	private PanelJuego contentPane;
	//private JTable table;
	
	public VentanaJuego(Partida prod) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new PanelJuego(prod,this);
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
		participantes.add(new Jugador("Mario",Color.white));
		participantes.add(new Jugador("Luigi",Color.green));
		participantes.get(0).addMochila_objetos(new DadoDorado());
		participantes.get(0).addMochila_objetos(new GuanteBlanco());
		participantes.get(0).addMochila_objetos(new PistolaCongelante());
		participantes.get(1).addMochila_objetos(new DadoDorado());
		Partida prod = new Partida(participantes, 50);
		
		VentanaJuego ventana = new VentanaJuego(prod);
		ventana.setVisible(true);
		
		HiloActualizador hilo = new HiloActualizador(ventana.contentPane, 1000/fps); // 1 segundo / 15 FPS = 66,66 milisegundos 
		hilo.start();
		
		prod.registrar(ventana.contentPane);
		prod.iniciarPartida();
	}
}
