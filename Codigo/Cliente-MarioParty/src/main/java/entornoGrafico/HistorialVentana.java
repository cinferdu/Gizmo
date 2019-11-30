package entornoGrafico;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cliente.Cliente;
import mensaje.MsjHistorial;
import mensaje.MsjIngresarLobby;
import model.Historial;

public class HistorialVentana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	private Cliente cliente;
	
	
    private JTextField txtIsbn;
    DefaultTableModel dtm ;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistorialVentana frame = new HistorialVentana(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HistorialVentana(Cliente client) {
		this.cliente = client;

		setTitle("Historial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblBookName = new JLabel("Nombre de Usuario: ");
		lblBookName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBookName.setBounds(23, 11, 80, 14);
		contentPane.add(lblBookName);

		txtIsbn = new JTextField();
		txtIsbn.setBounds(113, 8, 202, 20);
		contentPane.add(txtIsbn);
		txtIsbn.setColumns(10);

		JButton btnSearch = new JButton("Buscar");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(338, 7, 103, 23);
		contentPane.add(btnSearch);
		
		JButton btnBack = new JButton("Volver");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setBounds(338, 50, 103, 23);
		contentPane.add(btnBack);

		Object[] title = { "ID","Jugador", "Rondas", "Jugadores", "Ganador", "Monedas","Personje" };
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(title);
		table = new JTable(dtm);
		table.setBounds(23, 55, 435, 217);
		table.setModel(dtm);
		JScrollPane scroll = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setForeground(Color.RED);
		table.setRowHeight(30);
		contentPane.add(scroll);
		pack();

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setForeground(Color.BLACK);
				cliente.enviarMensaje(new MsjHistorial(cliente.getNombreCliente()));
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setForeground(Color.BLACK);
				MsjIngresarLobby msj = new MsjIngresarLobby();
				cliente.enviarMensaje(msj);
			}
		});
	}

	public void mostrarHistorial(List<Historial> tabla) {
		dtm.setRowCount(0);//clear
		//"ID","Jugador", "Rondas", "Jugadores", "Ganador", "Monedas","Personje"
		for (Historial historial : tabla) {
			Object[]  row = { historial.getId(), historial.getUser().getUser(), historial.getRondas(), historial.getJugadores(), historial.getGanador(), historial.getMonedas(), historial.getPersonaje()};
			dtm.addRow(row);
		}
	}
}
