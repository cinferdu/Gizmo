package mensaje;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import game.Jugador;
import game.MiniJuego;
import game.Partida;
import game.Personaje;
import game.Sprite;
import loteria.Loteria;
import objeto.CajaMisteriosa;
import objeto.GuanteBlanco;
import sala.Sala;
import servidor.PartidaThread;
import servidor.Servidor;

public class MsjIniciarMinijuego extends Mensaje{
	private static final long serialVersionUID = 1L;

	public MsjIniciarMinijuego() {
		super();
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		
	}
	
	
}
