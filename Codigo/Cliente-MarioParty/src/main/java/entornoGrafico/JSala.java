package entornoGrafico;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import cliente.Sala;
import mensaje.MsjIniciarPartida;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class JSala extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> listModel;
	private JLabel labelDuenio;
	private Cliente cliente;
	private JButton btnIniciarPartida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JSala frame = new JSala(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JSala(Cliente client) {
		cliente = client;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);

		JLabel lblCreador = new JLabel("Creador");
		lblCreador.setBounds(10, 22, 58, 14);
		contentPane.add(lblCreador);

		labelDuenio = new JLabel("");
		labelDuenio.setBounds(10, 47, 81, 14);
		contentPane.add(labelDuenio);

		listModel = new DefaultListModel<String>();

		JList<String> list = new JList<String>(listModel);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 125, 485, 169);
		list.setLayoutOrientation(JList.VERTICAL);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(148, 65, 126, 186);
		contentPane.add(scrollPane);

		JLabel lblJugadoresOnline = new JLabel("Jugadores Online");
		lblJugadoresOnline.setBounds(148, 47, 126, 14);
		contentPane.add(lblJugadoresOnline);
		/*
		btnIniciarPartida = new JButton("Iniciar Partida");
		btnIniciarPartida.setBounds(10, 220, 120, 30);
		contentPane.add(btnIniciarPartida);

		btnIniciarPartida.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (listModel.getSize() >= 2) {

					cliente.enviarMensaje(new MsjIniciarPartida(obtenerNombreDeJugadores()));

				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Para iniciar la partida deben ser al menos 2 jugadores en la sala");
				}
			}
		});
		*/
	}

	public void inicializarSala(Sala sala) {
		setTitle("Sala: " + sala.getNombreSala());
		this.labelDuenio.setText(sala.getNombreDuenio());
		for (String nombre : sala.getNombreJugadores()) {
			this.listModel.addElement(nombre);
		}
		if (sala.getNombreDuenio().equals(cliente.getNombreCliente())) {
			btnIniciarPartida = new JButton("Iniciar Partida");
			btnIniciarPartida.setBounds(10, 220, 120, 30);
			contentPane.add(btnIniciarPartida);

			btnIniciarPartida.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (listModel.getSize() >= 2) {

						cliente.enviarMensaje(new MsjIniciarPartida(obtenerNombreDeJugadores()));

					} else {
						JOptionPane.showMessageDialog(contentPane,
								"Para iniciar la partida deben ser al menos 2 jugadores en la sala");
					}
				}
			});
		}
	}

	public void agregarAlaSala(String userNuevo) {
		this.listModel.addElement(userNuevo);
	}

	public void eliminarDeSala(String user) {
		this.listModel.removeElement(user);
	}
	
	public ArrayList<String> obtenerNombreDeJugadores() {
		ArrayList<String> listaNombres = new ArrayList<String>();

		for (int i = 0; i < listModel.size(); i++) {
			listaNombres.add(listModel.getElementAt(i));
		}

		return listaNombres;
	}

}
