package entornoGrafico;

public class HiloActualizador extends Thread {

	private PanelJuego panelDelJuego;
	private long tiempo;

	public HiloActualizador(PanelJuego panel, int tiempo) {
		panelDelJuego = panel;
		this.tiempo = tiempo; 
	}

	public void run() {
		while (true) {
			try {
				sleep(tiempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			panelDelJuego.repaint();
		}
		
	}

}
