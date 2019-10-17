package entornoGrafico;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;
import javax.swing.Timer;

import casilla.Casilla;
import comunicacionObserver.Consumidor;
import comunicacionObserver.Operacion;
import game.Jugador;
import game.Partida;
import objeto.Objeto;

public class PanelJuego extends JPanel implements Consumidor {

	private static final int SEPARACION_PUNTAJES = 50;
	private static final int TIEMPO_ELEGIR_CAMINO = 5; // en segundos
	private static final int TIEMPO_ELEGIR_ACCION = 5; // en segundos

	private static final long serialVersionUID = 3007758429335180626L;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private Partida partida;
	private boolean botonPresionado = false;
	private int dado;

	public PanelJuego(Partida prod) {
		setBounds(100, 100, 600, 700);
		setLayout(null);
		textArea = new JTextArea();
		textArea.setBounds(10, 202, 154, 85);
		textArea.setEditable(false);

		// AutoScroll
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane = new JScrollPane(textArea);
		this.revalidate();

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + " - " + e.getY());
			}
		});

		scrollPane.setBounds(0, 565, 330, 100);
		add(scrollPane);

		textArea.setText("");

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 560, 600, 50);
		add(separator);

		partida = prod;
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		// Dibujo las casillas
		for (Casilla casilla : partida.getTablero().getCasilleros()) {
			g.setColor(casilla.getTipo().getColor());
			g.fillOval(casilla.getPosX(), casilla.getPosY(), 30, 30);
		}

		// Dibujo los jugadores
		int i = 0;
		for (Jugador jugador : partida.getJugadores()) {
			if (i == 0) {
				g.setColor(Color.WHITE);
			} else {
				g.setColor(Color.GREEN);
			}
			i++;
			g.fillOval(jugador.getPosicionActual().getPosX() + 10, jugador.getPosicionActual().getPosY() + 10, 10, 10);
		}
		imprimirPuntajes(g);
		
		if (dado != 0) {
			g.drawImage(DadoImagen.getDadoImagen(dado), 100, 200, null);
		}
	}
	
	public void actualizar(Operacion operacion, Jugador jugadorActual) { // cambiar por switch?
		if (operacion == Operacion.NUEVA_RONDA) {
			this.textArea.append("*** INICIANDO RONDA " + partida.getRondaActual() + " ***\n");
			return;
		}
		if (operacion == Operacion.MOVIMIENTO) {
			return;
		}
		if (operacion == Operacion.LANZAMIENTO_DADO) {
			this.textArea
					.append(jugadorActual.getNombre() + " avanza " + jugadorActual.getNroPasos() + " casillas" + "\n");
			
			dado = jugadorActual.getNroPasos();
			
			// Es para que deje de mostrar la imagen del dado despues de 1,5 segundos
			AbstractAction paintTimer  = new AbstractAction() {
				private static final long serialVersionUID = -5331589571920302500L;
				public void actionPerformed(ActionEvent e) {
		            dado = 0;
		        }
		    };
		    new Timer(1500, paintTimer).start();
		    
			return;
		}
		if (operacion == Operacion.CASILLA_ACTIVADA) {
			this.textArea.append(
					jugadorActual.getNombre() + " activo una " + jugadorActual.getPosicionActual() + "\n");
			return;
		}
		if (operacion == Operacion.SELECCIONAR_CAMINO) {
			this.textArea.append(jugadorActual.getNombre() + " seleccione un camino\n");

			Casilla caminoElegido = mostrarOpcionesParaElegirCamino(jugadorActual.getPosicionActual());

			partida.setRespuestaDePanel(caminoElegido);
		}
		if (operacion == Operacion.SELECCIONAR_ACCION) {
			this.textArea.append(jugadorActual.getNombre() + " seleccione un objeto\n");

			Objeto objetoElegido = mostrarOpcionesParaElegirAccion(jugadorActual);

			partida.setRespuestaDePanel(objetoElegido);
		}
		this.textArea.append(jugadorActual.getNombre() + " tiene " + jugadorActual.getMonedas() + " monedas.\n");
	}

	private Casilla mostrarOpcionesParaElegirCamino(Casilla casilla) {
		// creo los componentes
		JLabel mensaje = new JLabel("Seleccione una opcion (tiene "+ TIEMPO_ELEGIR_CAMINO +" segundos)");
		mensaje.setBounds(345, 565, 250, 20);

		JComboBox<Casilla> listaOpciones = new JComboBox<Casilla>();
		listaOpciones.setBounds(360, 595, 200, 25);

		for (Casilla casillaSig : casilla.getSiguientesCasillas()) {
			listaOpciones.addItem(casillaSig);
		}

		JButton aceptar = new JButton("Seleccionar");
		aceptar.setBounds(400, 630, 125, 25);
		aceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botonPresionado = true;
			}
		});

		add(listaOpciones);
		add(aceptar);
		add(mensaje);
		revalidate(); // esto lo puse porque al jcombobox no le aparecia la flecha hacia abajo

		// espero a que aprete el boton o pasen 5 segundos
		long tiempo_limite_ini = System.currentTimeMillis();
		long tiempo_limite_fin = System.currentTimeMillis();
		while (botonPresionado == false && (tiempo_limite_fin - tiempo_limite_ini) < TIEMPO_ELEGIR_CAMINO * 1000) {
			try {
				Thread.sleep(100);
				tiempo_limite_fin = System.currentTimeMillis();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Casilla casillaElegida = (Casilla) listaOpciones.getSelectedItem();
		remove(listaOpciones);
		remove(aceptar);
		remove(mensaje);
		revalidate();
		this.botonPresionado = false;

		return casillaElegida;
	}
	
	private Objeto mostrarOpcionesParaElegirAccion(Jugador jugador) {
		// creo los componentes
		JLabel mensaje = new JLabel("Seleccione una opcion (tiene "+ TIEMPO_ELEGIR_ACCION +" segundos)");
		mensaje.setBounds(345, 565, 250, 20);

		JComboBox<Objeto> listaOpciones = new JComboBox<Objeto>();
		listaOpciones.setBounds(360, 595, 200, 25);
/*
		for (Objeto objeto : jugador.getMochila_objetos()) {
			listaOpciones.addItem(objeto);
		}*/
		listaOpciones.addItem(jugador.getMochila_objetos());

		JButton aceptar = new JButton("Seleccionar");
		aceptar.setBounds(400, 630, 125, 25);
		aceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botonPresionado = true;
			}
		});

		add(listaOpciones);
		add(aceptar);
		add(mensaje);
		revalidate(); // esto lo puse porque al jcombobox no le aparecia la flecha hacia abajo

		// espero a que aprete el boton o pasen 5 segundos
		long tiempo_limite_ini = System.currentTimeMillis();
		long tiempo_limite_fin = System.currentTimeMillis();
		while (botonPresionado == false && (tiempo_limite_fin - tiempo_limite_ini) < TIEMPO_ELEGIR_ACCION * 1000) {
			try {
				Thread.sleep(100);
				tiempo_limite_fin = System.currentTimeMillis();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Objeto objetoElegido = (Objeto) listaOpciones.getSelectedItem();
		remove(listaOpciones);
		remove(aceptar);
		remove(mensaje);
		revalidate();
		this.botonPresionado = false;

		return objetoElegido;
	}

	private void imprimirPuntajes(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(450, 0, 150, 550);
		int jugador_nro = 0;
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		for (Jugador jugador : partida.getJugadores()) {

			g.drawString(jugador.getNombre(), 460, 50 + jugador_nro * SEPARACION_PUNTAJES);
			g.drawString("Monedas: ", 460, 70 + jugador_nro * SEPARACION_PUNTAJES);
			g.drawString(jugador.getMonedas() + "", 550, 70 + jugador_nro * SEPARACION_PUNTAJES);
			if (jugador.isPierdeTurno()) {
				g.setColor(Color.RED);
				g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 10));
				g.drawString("Pierde su turno", 520, 50 + jugador_nro * SEPARACION_PUNTAJES);
				g.setColor(Color.BLACK);
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
			}
			jugador_nro++;
		}
	}

}