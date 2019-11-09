package mensaje;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cliente.Sala;
import entornoGrafico.JSala;

public class MsjIngresarSala extends Mensaje {

	private static final long serialVersionUID = 1L;

	private Sala sala;
	private int id_sala;
	
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
			//CambiarVentana(Jframe ...){
			JFrame ventanaActual = this.listenerClient.getCliente().getVentanaActual();
			this.listenerClient.getCliente().getVentanaActual().dispose();
			ventanaActual.setVisible(false);
			ventanaActual = new JSala(listenerClient.getCliente());
			ventanaActual.setVisible(true);
			((JSala) ventanaActual).inicializarSala(this.sala);
			listenerClient.getCliente().setVentanaActual(ventanaActual);
			//}
			
		} else {
			JOptionPane.showMessageDialog(null, "Sala llena");
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
	
}
