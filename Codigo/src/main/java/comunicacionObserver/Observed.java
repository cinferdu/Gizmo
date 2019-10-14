package comunicacionObserver;

public interface Observed {
	public void registrar(Observer obs);
	public void desregistrar(Observer obs);
	public void avisar(Operacion operacion, Info_Observer data);
}
