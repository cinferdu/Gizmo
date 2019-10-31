package socket;

import javax.swing.JFrame;

public class VentanaCliente extends JFrame{
	
	public VentanaCliente(){
		
		setBounds(600,300,280,350);
				
		PanelCliente ventana=new PanelCliente();
		
		add(ventana);
		
		setVisible(true);
		
		addWindowListener(new EntrarAlChat());
		}	
	
}
