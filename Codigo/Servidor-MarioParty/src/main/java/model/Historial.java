package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HISTORIAL")
public class Historial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3378000445303652318L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "FK_User")
	private Usuario user;

	private int rondas;
	private String jugadores;
	private String ganador;
	private int monedas;
	private int puntosMinijuego;
	private String personaje;
	
	public Historial() {
		super();
	}

	public Historial(Usuario user, int rondas, String jugadores, String ganador, int monedas, int puntosMinijuego,
			String personaje) {
		super();
		this.user = user;
		this.rondas = rondas;
		this.jugadores = jugadores;
		this.ganador = ganador;
		this.monedas = monedas;
		this.puntosMinijuego = puntosMinijuego;
		this.personaje = personaje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public int getRondas() {
		return rondas;
	}

	public void setRondas(int rondas) {
		this.rondas = rondas;
	}

	public String getJugadores() {
		return jugadores;
	}

	public void setJugadores(String jugadores) {
		this.jugadores = jugadores;
	}

	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public int getMonedas() {
		return monedas;
	}

	public void setMonedas(int monedas) {
		this.monedas = monedas;
	}

	public int getPuntosMinijuego() {
		return puntosMinijuego;
	}

	public void setPuntosMinijuego(int puntosMinijuego) {
		this.puntosMinijuego = puntosMinijuego;
	}

	public String getPersonaje() {
		return personaje;
	}

	public void setPersonaje(String personaje) {
		this.personaje = personaje;
	}
}
