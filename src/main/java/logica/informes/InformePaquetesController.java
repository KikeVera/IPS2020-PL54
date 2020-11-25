package logica.informes;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import logica.Controller;

import persistencia.paquete.PaqueteEntity;
import persistencia.paquete.PaqueteModel;

import ui.informe.InformePaquetesView;


public class InformePaquetesController implements Controller {
	private PaqueteModel pm;
	private InformePaquetesView view;
	
	
	
	public InformePaquetesController(PaqueteModel pm, InformePaquetesView view) {
		this.pm=pm;
		this.view=view;
		
		this.initView();
	}
	
	public void initView() {

		//Inicializamos el carrito pasandole el catalogo de productos disponibles. Inicializamos 
		//tambien la tabla de productos.
		
		
		//Inicializamos la tabla que representara al pedido 
		
		
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true);
		crearInforme();
		
	}
	
	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los objetos del UI.
	 * Cada manejador de eventos se instancia de la misma forma, para que invoque un metodo privado
	 * de este controlador.
	 */
	public void initController() {
		
		
		
	}
	
	public void crearInforme() {
		List<PaqueteEntity> paquetes= pm.getPaquetes();
		List<String> fechas= new ArrayList<>();
		List<Integer> idAlmaceneros=new ArrayList<>();
		
		for(PaqueteEntity paquete: paquetes) {
			if(!fechas.contains(paquete.getFecha())) {
				fechas.add(paquete.getFecha());
			}
			
			if(!idAlmaceneros.contains(paquete.getIdAlmacenero())) {
				idAlmaceneros.add(paquete.getIdAlmacenero());
			}
			
		}
		
		Collections.sort(fechas);
		Collections.sort(idAlmaceneros);
		view.getPanel().setLayout(new GridLayout(fechas.size()+1, idAlmaceneros.size()+1, 0, 0));
		
		JLabel label;
		
			for(int i=-1;i<fechas.size();i++) {
				for(int j=-1;j<idAlmaceneros.size();j++) {
					if(i==-1) {
						if(j==-1) {
							label=new JLabel();
							label.setText("");
							
							label.setOpaque(true);
							label.setBackground(new Color(211, 211, 211));
							
							
						}
						else {
							label=new JLabel();
							label.setText("Alm: "+Integer.toString(idAlmaceneros.get(j)));
							label.setForeground(new Color(255, 140, 0));
							label.setOpaque(true);
							label.setBackground(new Color(211, 211, 211));
							label.setFont(new Font("Arial", Font.BOLD, 18));
						
							
							
						}
					}
					
					else {
						if(j==-1) {
							label=new JLabel();
							label.setText(fechas.get(i));
							label.setForeground(Color.BLUE);
							label.setFont(new Font("Arial", Font.BOLD, 18));
							
							label.setOpaque(true);
							label.setBackground(new Color(211, 211, 211));
							
							
							
						}
						
						else {
							label=new JLabel();
							label.setText(Integer.toString(pm.getPaquetesByFechaAndAlmacenero(fechas.get(i), idAlmaceneros.get(j)).size()));
							label.setFont(new Font("Arial", Font.PLAIN, 16));
							
							
						}
					}
					
					
					label.setBorder(new LineBorder(new Color(0, 0, 0),1));
					label.setHorizontalAlignment(SwingConstants.CENTER);
					view.getPanel().add(label);
				
				
			}
			
		}
		
			view.getFrame().revalidate();
		
		
	}
	
	
	
}

