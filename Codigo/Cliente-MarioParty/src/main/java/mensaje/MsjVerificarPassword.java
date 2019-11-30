package mensaje;

import javax.swing.JOptionPane;

public class MsjVerificarPassword extends Mensaje {

	private static final long serialVersionUID = 42961652314159484L;
	private String pass;
	private int idSala;
	
	public MsjVerificarPassword(String password, int id_sala) {
		pass = password;
		idSala = id_sala;
		clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		JOptionPane.showMessageDialog(null, "La password es incorrecta", "Accseso Denegado", JOptionPane.WARNING_MESSAGE);
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

}