package socket;

import java.io.Serializable;
import java.util.ArrayList;

class PaqueteDatos implements Serializable { //Serializable sirve para convertirlo en una serie de bits por lo que es posible enviarlo
	private String Usuario;
	private String Mensaje;
	private String IP;
	private ArrayList<String> direccionesIp;
	
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getMensaje() {
		return Mensaje;
	}
	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public ArrayList<String> getDireccionesIp() {
		return direccionesIp;
	}
	public void setDireccionesIp(ArrayList<String> direccionesIp) {
		this.direccionesIp = direccionesIp;
	}
	
}
