package logica.informes;



import java.util.List;


import javax.swing.table.TableModel;

import logica.Controller;

import persistencia.paquete.PaqueteEntity;
import persistencia.paquete.PaqueteModel;

import ui.informe.InformePaquetesListosView;

import util.swingTables.SwingUtil;

public class InformePaquetesListosController implements Controller {
	
	
	private InformePaquetesListosView view;
	
	private PaqueteModel pm;
	

	
	
	public InformePaquetesListosController(PaqueteModel pm, InformePaquetesListosView v) {
		
		this.view = v;  
		this.pm=pm;
		this.initView();
	}
	
	public void initView() {

		//Inicializamos el carrito pasandole el catalogo de productos disponibles. Inicializamos 
		//tambien la tabla de productos.
		
		
		//Inicializamos la tabla que representara al pedido 
		inicializarTablaPedido();
		
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true);
		
	}
	
	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los objetos del UI.
	 * Cada manejador de eventos se instancia de la misma forma, para que invoque un metodo privado
	 * de este controlador.
	 */
	public void initController() {
		
		
	}
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	
	
	private void inicializarTablaPedido() {
		List<PaqueteEntity> paquetes=pm.getPaquetesByStatus("READY");
		
		
			
		TableModel tmodel= SwingUtil.getTableModelFromPojos(paquetes,new String[] {"idPaquete","direccion","idPedido","uds"});
		view.getTable().setModel(tmodel);
		
	}
	
	
}

