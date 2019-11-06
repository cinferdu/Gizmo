package entornoGrafico;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import cliente.Sala;

import javax.swing.JLabel;
import javax.swing.JList;

public class JSala extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> listModel;
	private JLabel labelDuenio;
	private Cliente cliente;

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
		list.setBounds(10, 128, 485, 169);
		list.setLayoutOrientation(JList.VERTICAL);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(148, 65, 126, 186);
		contentPane.add(scrollPane);
		
		JLabel lblJugadoresOnline = new JLabel("Jugadores Online");
		lblJugadoresOnline.setBounds(148, 47, 126, 14);
		contentPane.add(lblJugadoresOnline);
	}
	
	public void inicializarSala(Sala sala) {
		this.labelDuenio.setText(sala.getNombreDuenio());
		this.listModel.addElement(cliente.getNombreCliente());
	}

}
