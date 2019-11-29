package entornoGrafico;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import org.apache.log4j.Logger;

import casilla.Casilla;
import cliente.Cliente;
import game.Dado;
import game.Jugador;
import game.Partida;
import mensaje.MsjIngresarLobby;
import mensaje.MsjPartidaBotonAccion;
import mensaje.MsjPartidaElegirCaminoAccion;
import mensaje.MsjPartidaSelecObjAccion;
import objeto.Objeto;

public class PanelJuego extends JLayeredPane {
	private static final long serialVersionUID = 3007758429335180626L;
	
	private static final int INICIO_PUNTAJES = 730;
	private static final int SEPARACION_PUNTAJES = 50;
	private static final int TAMANIO_CASILLA = 30;
	private static final int TIEMPO_ELEGIR_OPCION = 10000; // en milisegundos

	private final static Logger LOGGER = Logger.getLogger(PanelJuego.class);

	private Image dado;
	private Image dado_boton;

	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JLabel modificadorDelCursor;
	private boolean botonPresionado;
	private JLabel fondoL;

	// para mostrarOpciones(...)
	private Casilla caminoElegido;
	private Jugador jugadorSeleccionado;

	private Partida partida;
	private Cliente cliente;
	private boolean mostrarBoton = true;

	private Cartelito cartel;

	public PanelJuego(Cliente client) {
		this.cliente = client;
		this.partida = cliente.getPartidaActual();

		setLayout(null);
		textArea = new JTextArea("");
		textArea.setBounds(10, 202, 154, 85);
		textArea.setEditable(false);

		// AutoScroll
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 565, 330, 100);
		add(scrollPane);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 560, 900, 50);
		add(separator);

		partida = cliente.getPartidaActual();

		LOGGER.info("llego a las imagenes!!!");
		dado_boton = ImgExtra.BOTON_DADO;
		fondoL = new JLabel(ImgExtra.FONDO);
		fondoL.setBounds(0, 0, 733, 550);
		add(fondoL, 0, 0);

		modificadorDelCursor = new JLabel();
		modificadorDelCursor.setBounds(280, 30, 100, 100);
		modificadorDelCursor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modificadorDelCursor.setVisible(false);
		add(modificadorDelCursor, 5, 0);

		cartel = new Cartelito();
		cartel.setVisible(false);
		cartel.setBounds(25, 25, 200, 62);
		add(cartel, 3, 0);

		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// g.drawImage(fondo, 0, 0, null);

		if (dado != null) {
			g.drawImage(dado, 302, 50, null);
			modificadorDelCursor.setVisible(false);
		}
		if (mostrarBoton) {
			g.drawImage(dado_boton, 280, 30, null);
		}

		// Dibujo las casillas
		for (Casilla casilla : partida.getTablero().getCasilleros()) {
			g.setColor(casilla.getTipo().getColor());
			g.fillOval(casilla.getPosX(), casilla.getPosY(), TAMANIO_CASILLA, TAMANIO_CASILLA);
		}

		// Dibujo los jugadores
		for (Jugador jugador : partida.getJugadores()) {
			Image image = new ImageIcon(
					ImgExtra.class.getResource("/Personajes/" + jugador.getPersonaje().getName() + "-body.png"))
							.getImage();
			g.drawImage(image, jugador.getPosicionActual().getPosX(), jugador.getPosicionActual().getPosY() - 12, 30,
					40, null);
		}

		imprimirPuntajes(g);
		// revalidate();
	}

	public void nuevaRonda() {
		this.textArea.append("*** INICIANDO RONDA " + partida.getRondaActual() + " ***\n");
	}

	public void lanzamiento_dado(String nombre, int dadoValor) {
		mostrarBoton = false;

		dado = Dado.getImgCara(dadoValor);
		this.textArea.append(nombre + " avanza " + dadoValor + " casillas" + "\n");
		repaint();
	}

	public void casilla_activada(Jugador jugadorActual) {
		this.textArea.append(jugadorActual.getNombre() + " activo una " + jugadorActual.getPosicionActual() + "\n");
		for (Jugador jugador : partida.getJugadores()) {
			if (jugador.getNombre().equals(jugadorActual.getNombre())) {
				jugador.setPierdeTurno(jugadorActual.isPierdeTurno());
				jugador.setMonedas(jugadorActual.getMonedas());
			}
		}
		repaint();
	}

	public void seleccionar_camino(Jugador jugadorActual) {
		textArea.append(jugadorActual.getNombre() + " te quedan " + jugadorActual.getNroPasos() + " pasos\n");
		textArea.append(jugadorActual.getNombre() + " seleccione un camino\n");
		repaint();
	}

	public void seleccionarAccionMensaje(Jugador jugadorActual) {
		this.textArea.append(jugadorActual.getNombre() + " se encuentra seleccionando un objeto...\n");
	}

	public void informar_SinAccion(Jugador jugadorActual) {
		textArea.append(jugadorActual.getNombre() + " no puede realizar ninguna accion.\n");
	}

	public void informar_PerdioTurno(Jugador jugadorActual) {
		textArea.append(jugadorActual.getNombre() + ", perdiste tu turno.\n");
	}

	public void mostrarVentanaPuntajesFinales(Jugador ganador) {
		JFrame ventanaActual = cliente.getVentanaActual();
		PuntajesVentana ventanaPuntos = new PuntajesVentana(cliente, (ArrayList<Jugador>) partida.getJugadores(), ganador);
		ventanaPuntos.setVisible(true);
		ventanaPuntos.setFocusable(true);
		cliente.setVentanaActual(ventanaPuntos);
		ventanaActual.dispose();
	}

	public void nuevaRonda(int rondaActual) {
		partida.setRondaActual(rondaActual);
		this.textArea.append("*** INICIANDO RONDA " + partida.getRondaActual() + " ***\n");
	}

	public void botonDelDado() {
		dado = null;
		mostrarBoton = true;
		repaint();
	}

	public void esperarLanzamientoDelDado() {
		cartel.mostrarIniciarTurno();
		cartel.setVisible(true);
		modificadorDelCursor.setVisible(true);
		dado = null;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getX() > 280 && e.getX() < 380 && e.getY() > 30 && e.getY() < 380) {
					botonPresionado = true;
				}
			}
		});
		while (!botonPresionado) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		mostrarBoton = false;
		botonPresionado = false;
		cartel.setVisible(false);
		cliente.enviarMensaje(new MsjPartidaBotonAccion(null));
		repaint();
	}

	public void movimiento() {
		repaint();
	}

	public void mostrarOpcionesObjetos(Jugador jugadorActual) {
		ArrayList<Objeto> aListar = jugadorActual.getMochila_objetos();
		ArrayList<JRadioButton> botonesUsados = new ArrayList<JRadioButton>();

		this.textArea.append(jugadorActual.getNombre() + " seleccione un objeto\n");

		// creo los componentes
		JLabel mensaje = new JLabel(
				"Seleccione un objeto para utilizarlo (tiene " + TIEMPO_ELEGIR_OPCION / 1000 + " segundos)");
		mensaje.setBounds(345, 555, 400, 35);
		add(mensaje);

		JLabel mensajeObj = new JLabel("Seleccione un jugador");
		mensajeObj.setBounds(345, 600, 400, 35);
		add(mensajeObj);

		ArrayList<Jugador> posiblesObjetivos = new ArrayList<Jugador>();
		for (Jugador jugador : partida.getJugadores()) {
			if (!jugadorActual.equals(jugador)) {
				posiblesObjetivos.add(jugador);
			}
		}

		// Si es necesario elegir un objetivo
		ButtonGroup grupoJugadores = new ButtonGroup();
		int elementosCargados = 0;
		for (Jugador jugador : posiblesObjetivos) {
			JRadioButton jrPlayer = new JRadioButton(jugador.getNombre());
			jrPlayer.setBounds(350 + elementosCargados * 150, 625, 150, 20);
			jrPlayer.setActionCommand(elementosCargados + "");

			grupoJugadores.add(jrPlayer);
			add(jrPlayer);

			if (elementosCargados == 0)
				jrPlayer.setSelected(true);

			botonesUsados.add(jrPlayer);
			elementosCargados++;
		}

		ButtonGroup grupoObjeto = new ButtonGroup();
		elementosCargados = 0;
		int objetoNumero = 0; // posicion en la mochila

		// cargo los objetos
		for (Objeto elemento : aListar) {

			JRadioButton jrButton = new JRadioButton(elemento.toString());
			jrButton.setActionCommand(objetoNumero + "");
			jrButton.setBounds(350 + elementosCargados * 150, 577, 150, 35);

			if (elemento.isConObjetivo()) {
				jrButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						mostrarObjetivos(grupoJugadores, mensajeObj);
					}
				});
			} else {
				jrButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ocultarObjetivos(grupoJugadores, mensajeObj);
					}
				});

			}

			if (elementosCargados == 0) {
				jrButton.setSelected(true);

				if (elemento.isConObjetivo())
					mostrarObjetivos(grupoJugadores, mensajeObj);
				else
					ocultarObjetivos(grupoJugadores, mensajeObj);
			}

			grupoObjeto.add(jrButton);
			this.add(jrButton);
			elementosCargados++;

			botonesUsados.add(jrButton);

			objetoNumero++;
		}

		JButton aceptar = new JButton("Seleccionar");
		aceptar.setBounds(440, 645, 125, 25);
		add(aceptar);

		// para saber cuando apreto el boton
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPresionado = true;
			}
		});

		revalidate();
		repaint();

		// espero a que aprete el boton o pasen los segundos
		long tiempo_limite_ini = System.currentTimeMillis();

		while (botonPresionado == false && (System.currentTimeMillis() - tiempo_limite_ini) < TIEMPO_ELEGIR_OPCION) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		int objetoElegido = -1;
		if (botonPresionado == true)
			objetoElegido = Integer.valueOf(grupoObjeto.getSelection().getActionCommand());

		remove(aceptar);
		remove(mensaje);
		remove(mensajeObj);

		botonPresionado = false;

		int jg_selec = Integer.valueOf(grupoJugadores.getSelection().getActionCommand());
		if (jugadorActual.getMochila_objetos(objetoElegido).isConObjetivo()) {
			jugadorSeleccionado = posiblesObjetivos.get(jg_selec);
		}

		limpiarGrupo(botonesUsados);
		revalidate();
		repaint();

		MsjPartidaSelecObjAccion msj = new MsjPartidaSelecObjAccion(objetoElegido, jugadorSeleccionado);
		jugadorSeleccionado = null;
		msj.setJugadorAct(jugadorActual);
		cliente.enviarMensaje(msj);

	}

	private void limpiarGrupo(ArrayList<JRadioButton> botonesUsados) {
		for (JRadioButton jRadioButton : botonesUsados) {
			this.remove(jRadioButton);
		}
	}

	private void mostrarObjetivos(ButtonGroup grupoJugadores, JLabel mensaje) {
		for (Enumeration<AbstractButton> buttons = grupoJugadores.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			button.setVisible(true);
		}
		mensaje.setVisible(true);
	}

	private void ocultarObjetivos(ButtonGroup grupoJugadores, JLabel mensaje) {
		for (Enumeration<AbstractButton> buttons = grupoJugadores.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			button.setVisible(false);
		}
		mensaje.setVisible(false);
	}

	public void mostrarOpcionesCamino(final ArrayList<Casilla> aListar) {

		// creo los componentes
		JLabel mensaje = new JLabel(
				"Haga clic la siguiente casilla, para avanzar (tiene " + TIEMPO_ELEGIR_OPCION / 1000 + " segundos)");
		mensaje.setBounds(345, 565, 400, 20);

		add(mensaje);

		// Para elegir camino
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for (Casilla camino : aListar) {

					if (e.getX() > camino.getPosX() && e.getX() < (camino.getPosX() + TAMANIO_CASILLA)
							&& e.getY() > camino.getPosY() && e.getY() < (camino.getPosY() + TAMANIO_CASILLA)) {

						caminoElegido = camino;
						botonPresionado = true; // => "casilla seleccionada"
					}

				}
			}
		});

		repaint();

		// espero a que aprete el boton o pasen 10 segundos
		long tiempo_limite_ini = System.currentTimeMillis();

		while (botonPresionado == false && (System.currentTimeMillis() - tiempo_limite_ini) < TIEMPO_ELEGIR_OPCION) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		remove(mensaje);

		if (botonPresionado) {
			botonPresionado = false;
		} else {
			caminoElegido = aListar.get((int) (Math.random() * aListar.size()));
		}

		cliente.enviarMensaje(new MsjPartidaElegirCaminoAccion(null, caminoElegido));
	}

	private void imprimirPuntajes(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(INICIO_PUNTAJES - 10, 0, 180, 550);
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
		g.drawString("¿Perdera", INICIO_PUNTAJES + 110, 12);
		g.drawString("su", INICIO_PUNTAJES + 120, 22);
		g.drawString("turno?", INICIO_PUNTAJES + 120, 32);

		int jugador_nro = 0;
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		for (Jugador jugador : partida.getJugadores()) {

			g.drawString(jugador.getNombre(), INICIO_PUNTAJES, 50 + jugador_nro * SEPARACION_PUNTAJES);
			g.drawString("Monedas: ", INICIO_PUNTAJES, 70 + jugador_nro * SEPARACION_PUNTAJES);
			g.drawString(jugador.getMonedas() + "", INICIO_PUNTAJES + 135, 70 + jugador_nro * SEPARACION_PUNTAJES);

			// Si pierde o no su proximo turno
			g.setColor(Color.WHITE);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
			if (jugador.isPierdeTurno()) {
				g.drawString("SI", INICIO_PUNTAJES + 130, 50 + jugador_nro * SEPARACION_PUNTAJES);
			} else {
				g.drawString("NO", INICIO_PUNTAJES + 130, 50 + jugador_nro * SEPARACION_PUNTAJES);
			}
			g.setColor(Color.BLACK);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
			jugador_nro++;
		}
	}

	public void informarObjetoUtilizado(String jugador, String nombreobj) {
		this.textArea.append(jugador + " utilizo " + nombreobj + "\n");
	}

}