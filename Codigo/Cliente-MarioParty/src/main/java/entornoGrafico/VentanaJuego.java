package entornoGrafico;

import javax.swing.JFrame;

import cliente.Cliente;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 5146957524880202935L;
	private PanelJuego contentPane;

	public VentanaJuego(Cliente client) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new PanelJuego(client);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("MARIO PARTY");
	}
	
	public PanelJuego getPanel() {
		return contentPane;
	}

}
