package game;

public class MiniJuego {
	private String nombre;
	private String instrucciones;
	private int limiteTiempo;
	
	public MiniJuego() {
		super();
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getInstrucciones() {
		return instrucciones;
	}
	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}
	public int getLimiteTiempo() {
		return limiteTiempo;
	}
	public void setLimiteTiempo(int limiteTiempo) {
		this.limiteTiempo = limiteTiempo;
	}
}
