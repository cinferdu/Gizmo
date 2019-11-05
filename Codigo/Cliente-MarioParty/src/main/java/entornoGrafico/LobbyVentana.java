package entornoGrafico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class LobbyVentana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LobbyVentana frame = new LobbyVentana();
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
	public LobbyVentana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList<String> list = new JList<String>();
		list.setBounds(10, 128, 485, 169);
		contentPane.add(list);
		
		JButton btnIngresar = new JButton("Entrar");
		btnIngresar.setBounds(41, 54, 142, 63);
		contentPane.add(btnIngresar);
		
		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.setBounds(313, 54, 142, 63);
		contentPane.add(btnCrearSala);
		
		JLabel lblLobby = new JLabel("Lobby");
		lblLobby.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLobby.setHorizontalAlignment(SwingConstants.CENTER);
		lblLobby.setBounds(183, 11, 129, 27);
		contentPane.add(lblLobby);
	}
}
