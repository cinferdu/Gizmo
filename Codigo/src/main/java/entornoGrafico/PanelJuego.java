package entornoGrafico;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import casilla.Casilla;
import comunicacionObserver.Consumidor;
import comunicacionObserver.Operacion;
import game.Dado;
import game.Jugador;
import game.Partida;
import objeto.Objeto;

public class PanelJuego extends JPanel implements Consumidor {
	private static final int INICIO_PUNTAJES = 730;
	private static final int SEPARACION_PUNTAJES = 50;
	private static final int TAMANIO_CASILLA = 30;
	private static final int TIEMPO_ELEGIR_OPCION = 10; // en segundos

	private ImageIcon fondo;
	private Image dado = null;

	private static final long serialVersionUID = 3007758429335180626L;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private Partida partida;
	private boolean botonPresionado = false;

	// para mostrarOpciones(...) listener
	private JComboBox<Object> listaOpciones = null;
	private JLabel descripcion = null;
	private Casilla caminoElegido = null;
	
	private VentanaJuego ventanaJuego;

	public PanelJuego(Partida prod, VentanaJuego ventanaJuego) {
		this.ventanaJuego = ventanaJuego;
		setLayout(null);
		textArea = new JTextArea();
		textArea.setBounds(10, 202, 154, 85);
		textArea.setEditable(false);

		// AutoScroll
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane = new JScrollPane(textArea);
		this.revalidate();

		scrollPane.setBounds(0, 565, 330, 100);
		add(scrollPane);

		textArea.setText("");

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 560, 900, 50);
		add(separator);

		partida = prod;

		// para achicar la imagen
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("img\\background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(730, 550, Image.SCALE_SMOOTH);
		fondo = new ImageIcon(dimg);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(fondo.getImage(), 0, 0, null);

		if (dado != null)
			g.drawImage(dado, 302, 50, null);
		// Dibujo las casillas
		for (Casilla casilla : partida.getTablero().getCasilleros()) {
			g.setColor(casilla.getTipo().getColor());
			g.fillOval(casilla.getPosX(), casilla.getPosY(), TAMANIO_CASILLA, TAMANIO_CASILLA);
		}

		// Dibujo los jugadores
		for (Jugador jugador : partida.getJugadores()) {
			g.setColor(jugador.getColor());
			g.fillOval(jugador.getPosicionActual().getPosX() + 10, jugador.getPosicionActual().getPosY() + 10, 10, 10);
		}

		imprimirPuntajes(g);

	}

	public void actualizar(Operacion operacion, Jugador jugadorActual) { // cambiar por switch?

		switch (operacion) {
		case NUEVA_RONDA:
			this.textArea.append("*** INICIANDO RONDA " + partida.getRondaActual() + " ***\n");
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
			this.textArea.append(jugadorActual.getNombre() + " seleccione un camino\n");
			mostrarOpciones(jugadorActual.getPosicionActual());

			partida.setRespuestaDePanel(caminoElegido);
			break;

		case SELECCIONAR_ACCION:
			this.textArea.append(jugadorActual.getNombre() + " seleccione un objeto\n");
			Object control_null = mostrarOpciones(2, jugadorActual);
			if (control_null == null) {
				partida.setRespuestaDePanel(null);
				return;
			}
			int objetoElegido = (Integer) control_null;

			if (jugadorActual.getMochila_objetos(objetoElegido).isConObjetivo() == true) {
				jugadorActual.getMochila_objetos(objetoElegido).setVictima((Jugador) mostrarOpciones(3, jugadorActual));
			}
			partida.setRespuestaDePanel(objetoElegido);
			break;

		case SIN_ACCION:
			textArea.append(jugadorActual.getNombre() + " no puede realizar ninguna accion.\n");
			break;

		case PERDIO_TURNO:
			textArea.append(jugadorActual.getNombre() + ", la proxima vez sera.\n");
			break;

		case PUNTAJES_FINALES:
			PuntajesVentana test = new PuntajesVentana((ArrayList<Jugador>) partida.getJugadores(),
					partida.getJugadorGanador());
			test.setVisible(true);
			test.setFocusable(true);
			ventanaJuego.dispose();
			break;

		default:
			break;
		}

	}

	private Object mostrarOpciones(int tipoOpciones, Object aListar) {
		// creo los componentes
		JLabel mensaje = new JLabel("Seleccione una opcion (tiene " + TIEMPO_ELEGIR_OPCION + " segundos)");
		mensaje.setBounds(345, 565, 250, 20);

		// cargo el combobox
		switch (tipoOpciones) {
		case 1:
			Casilla casilla = (Casilla) aListar;
			listaOpciones = new JComboBox<Object>();
			for (Casilla elemento : casilla.getSiguientesCasillas()) {
				listaOpciones.addItem(elemento);
			}

			break;
		case 2:
			Jugador jugador = (Jugador) aListar;
			listaOpciones = new JComboBox<Object>();
			for (Objeto elemento : jugador.getMochila_objetos()) {
				listaOpciones.addItem(elemento);
			}
			break;
		case 3:
			listaOpciones = new JComboBox<Object>();
			// Lo clono para que al hacer el remove no borre ese jugador en la partida
			ArrayList<Jugador> posiblesObjetivos = (ArrayList<Jugador>) partida.getJugadores().clone();
			posiblesObjetivos.remove((Jugador) aListar);
			for (Jugador elemento : posiblesObjetivos) {
				listaOpciones.addItem(elemento);
			}
			break;
		default:
			break;
		}

		listaOpciones.setBounds(360, 595, 200, 25);
		JButton aceptar = new JButton("Seleccionar");
		aceptar.setBounds(400, 630, 125, 25);

		// para saber cuando apreto el boton
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPresionado = true;
			}
		});

		JLabel descripcion_titulo = null;
		if (tipoOpciones == 2) {
			// a descripcion lo cargo con la descripcion del primer objeto
			descripcion = new JLabel(((Objeto) listaOpciones.getItemAt(0)).getDescripcion());
			descripcion_titulo = new JLabel("Descripcion:");
			descripcion_titulo.setBounds(600, 565, 250, 20);
			descripcion.setBounds(600, 580, 280, 50);
			add(descripcion_titulo);
			add(descripcion);
			listaOpciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Objeto item = (Objeto) listaOpciones.getSelectedItem();
					descripcion.setText(item.getDescripcion());
				}
			});
		}

		add(listaOpciones);
		add(aceptar);
		add(mensaje);
		revalidate(); // esto lo puse porque al jcombobox no le aparecia la flecha hacia abajo

		// espero a que aprete el boton o pasen 5 segundos
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

		Object objetoElegido = null;
		if (tipoOpciones != 2) {
			objetoElegido = listaOpciones.getSelectedItem();
		} else {
			remove(descripcion_titulo);
			remove(descripcion);

			if (botonPresionado == true)
				objetoElegido = listaOpciones.getSelectedIndex();
			else
				objetoElegido = null;

		}
		remove(listaOpciones);
		remove(aceptar);
		remove(mensaje);
		revalidate();
		this.botonPresionado = false;

		listaOpciones = null;

		return objetoElegido;
	}

	private void mostrarOpciones(final Casilla aListar) {
		
		// creo los componentes
		JLabel mensaje = new JLabel("Haga clic la siguiente casilla, para avanzar (tiene " + TIEMPO_ELEGIR_OPCION + " segundos)");
		//JLabel mensaje_2 = new JLabel("Seleccione la siguiente casilla (tiene " + TIEMPO_ELEGIR_OPCION + " segundos)");
		mensaje.setBounds(345, 565, 400, 20);

		add(mensaje);
		//add(mensaje_2);
		revalidate(); // esto lo puse porque al jcombobox no le aparecia la flecha hacia abajo
		
		// Para elegir camino
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for (Casilla camino : aListar.getSiguientesCasillas()) {
					if (e.getX() > camino.getPosX() && e.getX() < (camino.getPosX()+TAMANIO_CASILLA)
							&& e.getY() > camino.getPosY() && e.getY() < (camino.getPosY()+TAMANIO_CASILLA)) {
						
						caminoElegido = camino;
						
						botonPresionado = true; // lo uso como "casilla seleccionada"
					}
					
				}
			}
		});
		
		// espero a que aprete el boton o pasen 5 segundos
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
		revalidate();
		
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