package entornoGrafico;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import cliente.Sala;
import mensaje.MsjIngresarSala;

public class LobbyVentana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> listModel; // donde se agregan las salas
	private JList<String> list;
	private Cliente cliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LobbyVentana frame = new LobbyVentana(null);
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
	public LobbyVentana(Cliente client) {
		this.cliente = client;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		

		listModel = new DefaultListModel<String>();
		
		list = new JList<String>(listModel);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 128, 485, 169);
		list.setLayoutOrientation(JList.VERTICAL);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 128, 485, 169);
		contentPane.add(scrollPane);
		
		JButton btnIngresar = new JButton("Entrar");
		btnIngresar.addActionListener(new ActionListener() {//TODO fijarse la conexion a la sala!
			public void actionPerformed(ActionEvent arg0) {
				String salaCompleta = list.getSelectedValue();
				if (salaCompleta == null) {
					return;
				}
				int idSala = Integer.valueOf(salaCompleta.substring(0, salaCompleta.indexOf(".")));
				
				client.enviarMensaje(new MsjIngresarSala(idSala)); //TODO enviar mjs al server para recuperar sala de x cliente
//				CrearSalaVentana ventana = new CrearSalaVentana(cliente);
//				ventana.setVisible(true);
//				cliente.setVentanaActual(ventana);
//				dispose();
			}
		});
		btnIngresar.setBounds(41, 54, 142, 63);
		contentPane.add(btnIngresar);
		
		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CrearSalaVentana ventana = new CrearSalaVentana(cliente);
				ventana.setVisible(true);
				cliente.getVentanaActual().dispose();
				cliente.setVentanaActual(ventana);
				dispose();
			}
		});
		btnCrearSala.setBounds(313, 54, 142, 63);
		contentPane.add(btnCrearSala);
		
		JLabel lblLobby = new JLabel("Lobby");
		lblLobby.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLobby.setHorizontalAlignment(SwingConstants.CENTER);
		lblLobby.setBounds(183, 11, 129, 27);
		contentPane.add(lblLobby);
		
	}
	
	public void mostrarSala(TreeMap<Integer, Sala> salasActivas) {
		for (Entry<Integer, Sala> entry : salasActivas.entrySet()) {
			listModel.addElement(entry.getKey() + ". " + entry.getValue().getNombreSala());
		}
	}
	
}
