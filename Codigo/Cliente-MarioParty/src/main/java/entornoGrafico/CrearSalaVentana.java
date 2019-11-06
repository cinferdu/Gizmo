package entornoGrafico;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import paquete.PaqueteCreacionSala;

public class CrearSalaVentana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textField_MaxJug;
	private Cliente cliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearSalaVentana frame = new CrearSalaVentana(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param client 
	 */
	public CrearSalaVentana(Cliente client) {
		this.cliente = client;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Creacion de Sala");
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la Sala");
		lblNombreDeLa.setBounds(10, 74, 145, 14);
		contentPane.add(lblNombreDeLa);
		
		JLabel lblDuenio = new JLabel("Duenio");
		lblDuenio.setBounds(10, 36, 145, 14);
		contentPane.add(lblDuenio);
		
		JLabel lblNombredelduenio = new JLabel(cliente.getNombreCliente());
		lblNombredelduenio.setBounds(168, 36, 111, 14);
		contentPane.add(lblNombredelduenio);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(168, 71, 111, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblCantidadMaximaDe = new JLabel("Cantidad Max de Jugadores");
		lblCantidadMaximaDe.setBounds(10, 113, 145, 20);
		contentPane.add(lblCantidadMaximaDe);
		
		textField_MaxJug = new JTextField();
		textField_MaxJug.setBounds(168, 113, 111, 20);
		contentPane.add(textField_MaxJug);
		textField_MaxJug.setColumns(10);
		
		JButton btnCrear = new JButton("Crear Sala");
		btnCrear.setBounds(66, 216, 170, 23);
		contentPane.add(btnCrear);

		
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreSala;
				int limite = Integer.valueOf(textField_MaxJug.getText());
				
				if ((nombreSala = textFieldNombre.getText().trim()).length() > 0 &&
						(limite == 2 || limite == 3 || limite == 4) ){
					cliente.enviarMensaje(new PaqueteCreacionSala(nombreSala, limite, cliente.getNombreCliente()));
				} else {
					JOptionPane.showMessageDialog(null, "El nombre de la sala debe contener al menos 1 caracter y "
							+ "el limite de jugadores solo puede ser 2, 3 o 4.");
				}
			}
		});
	}
}
