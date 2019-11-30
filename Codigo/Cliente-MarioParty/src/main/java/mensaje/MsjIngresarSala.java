package mensaje;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import entornoGrafico.JSala;
import sala.ErrorAlIngresar;
import sala.Sala;

public class MsjIngresarSala extends Mensaje {

	private static final long serialVersionUID = 1L;

	private Sala sala;
	private int id_sala;
	private ErrorAlIngresar error;

	public MsjIngresarSala(Sala sala) {
		this.setSala(sala);
		this.clase = this.getClass().getSimpleName();
	}

	public MsjIngresarSala(int id_sala) {
		this.id_sala = id_sala;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {

		if (resultado) {
			// CambiarVentana(Jframe ...){
			JFrame ventanaActual = this.listenerClient.getCliente().getVentanaActual();
			this.listenerClient.getCliente().getVentanaActual().dispose();
			ventanaActual.setVisible(false);
			ventanaActual = new JSala(listenerClient.getCliente());
			((JSala) ventanaActual).inicializarSala(this.sala);
			ventanaActual.setVisible(true);
			listenerClient.getCliente().setVentanaActual(ventanaActual);
			// }

		} else {
			switch (error) {
			case SALA_LLENA:
				JOptionPane.showMessageDialog(null, "Sala llena");
				break;
			case EN_PARTIDA:
				int dialogResult = JOptionPane.showConfirmDialog(null, "¿Desea entrar como espectador?",
						"Spec", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					listenerClient.getCliente().enviarMensaje(new MsjPartidaEspectador(id_sala));
				}
				break;
			case SALA_PRIVADA:
				String cadenaIngresada = JOptionPane.showInputDialog(null, "Ingrese la password de la sala:", "Sala privada", JOptionPane.QUESTION_MESSAGE);
				listenerClient.getCliente().enviarMensaje(new MsjVerificarPassword(cadenaIngresada, id_sala));
				break;
			}
		}
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

	public ErrorAlIngresar getError() {
		return error;
	}

	public void setError(ErrorAlIngresar error) {
		this.error = error;
	}

}
