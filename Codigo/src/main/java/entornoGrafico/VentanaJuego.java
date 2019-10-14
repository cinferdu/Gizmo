package entornoGrafico;

import java.util.ArrayList;

import javax.swing.JFrame;

import game.Jugador;
import game.Partida;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 5146957524880202935L;
	private PanelJuego contentPane;
	//private JTable table;
	
	public VentanaJuego(Partida prod) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new PanelJuego(prod);
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("MARIO PARTY");
		/*
		table = new JTable();
		table.setBounds(444, 11, 147, 321);
		contentPane.add(table);*/
	}


	public static void main(String[] args) {
		int fps = 15;
		
		// Creo la partida con los jugadores
		ArrayList<Jugador> participantes = new ArrayList<Jugador>();
		participantes.add(new Jugador("Mario"));
		participantes.add(new Jugador("Luigi"));
		Partida prod = new Partida(participantes, 50);
		
		VentanaJuego ventana = new VentanaJuego(prod);
		ventana.setVisible(true);
		
		HiloActualizador hilo = new HiloActualizador(ventana.contentPane, 1000/fps); // 1 segundo / 15 FPS = 66,66 milisegundos 
		hilo.start();
		
		prod.registrar(ventana.contentPane);
		prod.iniciarPartida();
	}
}
