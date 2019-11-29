package entornoGrafico;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cliente.Cliente;
import mensaje.MsjIngresarLobby;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 5146957524880202935L;
	private PanelJuego contentPane;
	private Cliente cliente;

	public VentanaJuego(Cliente client) {
		cliente = client;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new PanelJuego(client);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("MARIO PARTY" + " (" + client.getNombreCliente() + ")");
	}

	public PanelJuego getPanel() {
		return contentPane;
	}


	public void modoEspectador() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "¿Desea volver al Lobby?",
						"Salir", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					MsjIngresarLobby msj = new MsjIngresarLobby();
					msj.setEspectador(true);
					cliente.enviarMensaje(msj);
				}
			}
		});
	}
}
