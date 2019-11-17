package entornoGrafico;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import mensaje.MsjLogin;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUser;
	private JTextField textFieldPass;
	private Cliente cliente;

	public static void main(String[] args) {
		new Login(null).setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Login(Cliente client) {
		this.cliente = client;
		setResizable(false);
		setTitle("Ingreso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 174, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(29, 86, 100, 20);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);

		textFieldPass = new JPasswordField();
		textFieldPass.setBounds(29, 130, 100, 20);
		contentPane.add(textFieldPass);
		textFieldPass.setColumns(10);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLogin.setBounds(0, 11, 158, 37);
		contentPane.add(lblLogin);

		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textFieldUser.getText().trim();
				String pass = textFieldPass.getText().trim();

				if (user.length() < 0) {
					JOptionPane.showMessageDialog(contentPane, "Usa 1 caracter o mas para tu usuario");
					return;
				}
				if (user.length() < 0) {
					JOptionPane.showMessageDialog(contentPane, "La contraseña no puede estar vacia.");
					return;
				}
				
				cliente.enviarMensaje(new MsjLogin(user,pass));
			}
		});
		btnNewButton.setBounds(29, 163, 100, 23);
		contentPane.add(btnNewButton);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setBounds(29, 66, 46, 14);
		contentPane.add(lblUser);

		JLabel lblPass = new JLabel("Contraseña");
		lblPass.setBounds(29, 104, 100, 30);
		contentPane.add(lblPass);
	}
}
