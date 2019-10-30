package entornoGrafico;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import casilla.Casilla;
import comunicacionObserver.Suscriptor;
import comunicacionObserver.Operacion;
import game.Dado;
import game.Jugador;
import game.Partida;
import objeto.Objeto;

public class PanelJuego extends JPanel implements Suscriptor {
	private static final int INICIO_PUNTAJES = 730;
	private static final int SEPARACION_PUNTAJES = 50;
	private static final int TAMANIO_CASILLA = 30;
	private static final int TIEMPO_ELEGIR_OPCION = 10; // en segundos

	private Image fondo;
	private Image dado = null;
	private Image dado_boton;

	private static final long serialVersionUID = 3007758429335180626L;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JLabel modificadorDelCursor;
	private boolean botonPresionado = false;

	// para mostrarOpciones(...)
	private Casilla caminoElegido = null;
	private Jugador jugadorSeleccionado = null;

	private Partida partida;
	private VentanaJuego ventanaJuego;

	public PanelJuego(Partida prod, VentanaJuego ventanaJuego) {
		this.ventanaJuego = ventanaJuego;
		setLayout(null);
		textArea = new JTextArea("");
		textArea.setBounds(10, 202, 154, 85);
		textArea.setEditable(false);

		// AutoScroll
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane = new JScrollPane(textArea);
		this.revalidate();

		scrollPane.setBounds(0, 565, 330, 100);
		add(scrollPane);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 560, 900, 50);
		add(separator);

		partida = prod;

		fondo = ImgExtra.FONDO;
		dado_boton = ImgExtra.BOTON_DADO;
		modificadorDelCursor = new JLabel();
		modificadorDelCursor.setBounds(280, 30, 100, 100);
		modificadorDelCursor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modificadorDelCursor.setVisible(true);
		add(modificadorDelCursor);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(fondo, 0, 0, null);

		if (dado != null) {
			g.drawImage(dado, 302, 50, null);
			modificadorDelCursor.setVisible(false);
		} else {
			g.drawImage(ImgExtra.CUADR_TEXTO, 25, 25, null);
			g.drawString("Haga clic sobre los dados", 50, 50);
			g.drawString("para iniciar su turno", 50, 65);
			g.drawImage(dado_boton, 280, 30, null);
			modificadorDelCursor.setVisible(true);
		}
		// Dibujo las casillas
		for (Casilla casilla : partida.getTablero().getCasilleros()) {
			g.setColor(casilla.getTipo().getColor());
			g.fillOval(casilla.getPosX(), casilla.getPosY(), TAMANIO_CASILLA, TAMANIO_CASILLA);
		}

		// Dibujo los jugadores
		for (Jugador jugador : partida.getJugadores()) {
			Toolkit t = Toolkit.getDefaultToolkit();
			Image imagen = t.getImage("Personajes/" + jugador.getPersonaje().getName() + "-body.png");
			g.drawImage(imagen, jugador.getPosicionActual().getPosX(), jugador.getPosicionActual().getPosY() - 12, 30, 40, null);
		}

		imprimirPuntajes(g);

	}

	public void actualizar(Operacion operacion, Jugador jugadorActual) {

		switch (operacion) {
		case NUEVA_RONDA:
			this.textArea.append("*** INICIANDO RONDA " + partida.getRondaActual() + " ***\n");
			break;
		case BOTON_DADO:
			esperarLanzamientoDelDado();

			break;
		case LANZAMIENTO_DADO:
			dado = Dado.getImgCara(jugadorActual.getNroPasos()).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			this.textArea
					.append(jugadorActual.getNombre() + " avanza " + jugadorActual.getNroPasos() + " casillas" + "\n");
			break;

		case CASILLA_ACTIVADA:
			this.textArea.append(jugadorActual.getNombre() + " activo una " + jugadorActual.getPosicionActual() + "\n");
			break;

		case SELECCIONAR_CAMINO:
			textArea.append(jugadorActual.getNombre() + " te quedan " + jugadorActual.getNroPasos() + " pasos\n");
			textArea.append(jugadorActual.getNombre() + " seleccione un camino\n");
			mostrarOpcionesCamino(jugadorActual.getPosicionActual());

			partida.setRespuestaDePanel(caminoElegido);
			break;

		case SELECCIONAR_ACCION:
			this.textArea.append(jugadorActual.getNombre() + " seleccione un objeto\n");
			int objetoElegido = mostrarOpcionesObjetos(jugadorActual);
			
			if (objetoElegido != -1) { 
				if (jugadorActual.getMochila_objetos(objetoElegido).isConObjetivo() == true)
					jugadorActual.getMochila_objetos(objetoElegido).setVictima(jugadorSeleccionado);
				
				partida.setRespuestaDePanel(objetoElegido);
			}else {
				partida.setRespuestaDePanel(null); 
			}
			
			break;

		case SIN_ACCION:
			textArea.append(jugadorActual.getNombre() + " no puede realizar ninguna accion.\n");
			break;

		case PERDIO_TURNO:
			textArea.append(jugadorActual.getNombre() + ", perdiste tu turno.\n");
			break;

		case PUNTAJES_FINALES:
			PuntajesVentana ventanaPuntos = new PuntajesVentana((ArrayList<Jugador>) partida.getJugadores(),
					partida.getJugadorGanador());
			ventanaPuntos.setVisible(true);
			ventanaPuntos.setFocusable(true);
			ventanaJuego.dispose();
			break;

		default:
			break;
		}
		repaint();
	}

	private void esperarLanzamientoDelDado() {
		dado = null;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getX() > 280 && e.getX() < (280 + dado_boton.getHeight(null)) && e.getY() > 30
						&& e.getY() < (280 + dado_boton.getWidth(null))) {
					botonPresionado = true;
				}
			}
		});
		while (!botonPresionado) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		botonPresionado = false;

	}


	private int mostrarOpcionesObjetos(Jugador jugadorActual) {
		Objeto[] aListar = jugadorActual.getMochila_objetos();
		ArrayList<JRadioButton> botonesUsados = new ArrayList<JRadioButton>();

		// creo los componentes
		JLabel mensaje = new JLabel(
				"Seleccione un objeto para utilizarlo (tiene " + TIEMPO_ELEGIR_OPCION + " segundos)");
		mensaje.setBounds(345, 555, 400, 35);
		add(mensaje);

		JLabel mensajeObj = new JLabel("Seleccione un jugador");
		mensajeObj.setBounds(345, 600, 400, 35);
		add(mensajeObj);

		// Lo clono para que el remove no borre ese jugador en la partida
		ArrayList<Jugador> posiblesObjetivos =  new ArrayList<Jugador>(partida.getJugadores());
		
		posiblesObjetivos.remove(jugadorActual);

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
			
			// si es distinto de null lo agrego
			if (elemento != null) {
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
			}
			
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

		// espero a que aprete el boton o pasen los segundos
		long tiempo_limite_ini = System.currentTimeMillis();
		long tiempo_limite_fin = System.currentTimeMillis();
		while (botonPresionado == false && (tiempo_limite_fin - tiempo_limite_ini) < (TIEMPO_ELEGIR_OPCION * 1000)) {
			try {
				Thread.sleep(100);
				tiempo_limite_fin = System.currentTimeMillis();
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
		
		revalidate();
		repaint();
		botonPresionado = false;

		int jg_selec = Integer.valueOf(grupoJugadores.getSelection().getActionCommand());
		jugadorSeleccionado = posiblesObjetivos.get(jg_selec);

		limpiarGrupo(botonesUsados);
		
		return objetoElegido;

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

	private void mostrarOpcionesCamino(final Casilla aListar) {

		// creo los componentes
		JLabel mensaje = new JLabel(
				"Haga clic la siguiente casilla, para avanzar (tiene " + TIEMPO_ELEGIR_OPCION + " segundos)");
		mensaje.setBounds(345, 565, 400, 20);

		add(mensaje);

		// Para elegir camino
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for (Casilla camino : aListar.getSiguientesCasillas()) {

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
		long tiempo_limite_fin = System.currentTimeMillis();
		while (botonPresionado == false && (tiempo_limite_fin - tiempo_limite_ini) < (TIEMPO_ELEGIR_OPCION * 1000)) {
			try {
				Thread.sleep(100);
				tiempo_limite_fin = System.currentTimeMillis();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		remove(mensaje);

		if (botonPresionado) {
			botonPresionado = false;
		} else {
			caminoElegido = aListar.getSiguientesCasillas().get(0);
		}

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
			g.setColor(Color.GREEN);
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

}