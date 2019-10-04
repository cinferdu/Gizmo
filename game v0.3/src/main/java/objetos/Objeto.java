package objetos;

import java.util.List;

import game2.Jugador;

public abstract class Objeto {
	protected String nombre;
	protected String descripcion;
	
	public Objeto(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	// 
	public Jugador elegirObjetivo(List<Jugador> jugadores, Jugador jugadorActual) {
		return jugadorActual;
	}
	
	public abstract void activarEfecto(Jugador jugador);
	
	// Recibe el String con el nombre de la clase y devuelve una instacia de esa clase
	public static Objeto instanciar(String nombreClase)  {
	    String paquete = Objeto.class.getPackage().getName();
	    Class<?> clazz = null;
		try {
			clazz = Class.forName(paquete+"."+nombreClase);
			return (Objeto) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	
}
