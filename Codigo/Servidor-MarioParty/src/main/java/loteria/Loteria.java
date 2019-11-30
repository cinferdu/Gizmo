package loteria;
import java.util.ArrayList;
import java.util.HashMap;

public class Loteria {
	
	private HashMap<String,int[]> jugadas; //Contendra los nombre y el vector de sus numeros
	private ArrayList<Integer> numerosGanadores;
	private HashMap<String, Integer>tablaAciertos;
	
	public Loteria(HashMap<String, int[]> jugadas) {
		this.jugadas = jugadas;
		int numero;
		numerosGanadores = new ArrayList<Integer>();
		for (int i = 1; i <= 6; i++) {
			numero = (int) (Math.random() * 50 + 1);
			if (numerosGanadores.contains(numero)) {
				i--;
			} else {
				numerosGanadores.add(numero);
			}
		}
		resolverTabla();
	}
	
	private void resolverTabla() {
		for (String key : jugadas.keySet()) {
			int aciertos = 0;
			for(int i=0;i<jugadas.size();i++) {
				if(numerosGanadores.contains(jugadas.get(key)[i]))
					aciertos++;
			}
			tablaAciertos.put(key, aciertos);
		}
	}

	public HashMap<String, int[]> getJugadas() {
		return jugadas;
	}

	public ArrayList<Integer> getNumerosGanadores() {
		return numerosGanadores;
	}

	public HashMap<String, Integer> getTablaAciertos() {
		return tablaAciertos;
	}
	
}