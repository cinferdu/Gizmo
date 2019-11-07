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
		this.setId_sala(id_sala);
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		
		if (resultado) {
			JFrame ventanaActual = this.clientListener.getCliente().getVentanaActual();
			ventanaActual.dispose();
			ventanaActual = new JSala(clientListener.getCliente());
			ventanaActual.setVisible(true);
			((JSala) ventanaActual).inicializarSala(this.sala);
			
		} else {
			JOptionPane.showMessageDialog(null, "La sala no fue creada");
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
