package minijuego;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import sun.applet.Main;

@SuppressWarnings("serial")
public class Loteria{
	private int[] elegidos;
	private JFrame frame;
	private JButton[] listaBotones;
	private JLabel etiquetaAc;
	private JLabel etiquetaTitulo;
	private JLabel[] numerosElegidos;
	private int cantPresionados=0;
	
	private static int CANT_NUMEROS=50;
	private static int CANT_NUMEROS_ELEGIDOS=6;
	
	public Loteria() {
		//super("Loteria");
		elegidos = new int[6];
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(new Dimension(520,300));
		frame.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		frame.setTitle("Minijuego");
		////////////titulo//////////////
		etiquetaTitulo = new JLabel();
		etiquetaTitulo.setText("LOTERIA");
		constraints.gridx = 5;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.weighty = 0.1;
		frame.getContentPane().add(etiquetaTitulo,constraints);
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		///////////numeros elegidos/////////
		
		numerosElegidos=new JLabel[CANT_NUMEROS_ELEGIDOS];
		for(int i=0; i< CANT_NUMEROS_ELEGIDOS;i++) {
			numerosElegidos[i]=new JLabel();
			numerosElegidos[i].setText(Integer.toString(i+1));
			constraints.gridx = 3+i;
			constraints.gridy = 3;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 1.0;
			numerosElegidos[i].setVisible(false);
			frame.getContentPane().add(numerosElegidos[i],constraints);
		}
		constraints.weighty = 0.0;
		constraints.weightx = 0.0;
		/////////etiqueta//////////
		etiquetaAc = new JLabel();
		etiquetaAc.setText("Seleccione 6 numeros en 10 segundos para su loteria:");
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 8;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.weighty = 0.1;
		frame.getContentPane().add(etiquetaAc,constraints);
		constraints.weighty = 0.0;
		/////////Botones////////
		listaBotones=new JButton[CANT_NUMEROS];
		
		int j=0;
		for(int i=0;i<CANT_NUMEROS;i++) {
			String etiqueta ="";
			if(i<9)
				etiqueta+=0;
			etiqueta+=i+1;
			listaBotones[i]=new JButton(etiqueta);
			if(i%10 == 0 && i!=0)
				j++;
			constraints.gridx = 1+i-j*10;
			constraints.gridy = 7+j;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 1.0;
			listaBotones[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(cantPresionados<6) {
						JButton botonPulsado = (JButton)e.getSource();
						numerosElegidos[cantPresionados].setText(botonPulsado.getText());
						numerosElegidos[cantPresionados].setVisible(true);
						elegidos[cantPresionados] = Integer.parseInt(botonPulsado.getText());
						cantPresionados++;
					}else {
						JOptionPane.showMessageDialog(null, "Error. Cantidad Maxima de Botones es de 6");
					}
					
				}
			});
			frame.getContentPane().add(listaBotones[i],constraints);
		};

		constraints.weighty = 0.0;
		constraints.weightx = 0.0;
		
		JLabel aux=new JLabel();
		constraints.gridx =0;
		constraints.gridy = 12;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.2;
		frame.getContentPane().add(aux,constraints);
		frame.setVisible(true);
		Timer timer = new Timer();
		TimerTask tarea = new TimerTask() {
			
			@Override
			public void run() {
				cerrarVentana();
			}

		};
		
		timer.schedule(tarea, 10000);
	}
	
	private void cerrarVentana() {
		JOptionPane.showMessageDialog(null, "Se ha terminado el tiempo", null, JOptionPane.ERROR_MESSAGE);
		frame.dispose();
	}
	
	public int[] getNumerosElegidos() {
		return this.elegidos;
	}
    
}
