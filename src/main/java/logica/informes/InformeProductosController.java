package logica.informes;



import java.util.ArrayList;
import java.util.List;


import javax.swing.table.TableModel;

import logica.Controller;

import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;
import ui.informe.InformeProductosView;
import util.swingTables.SwingUtil;

public class InformeProductosController implements Controller {
	
	
	private InformeProductosView view;
	
	private ProductosModel pm;
	

	
	
	public InformeProductosController(ProductosModel pm, InformeProductosView v) {
		
		this.view = v;  
		this.pm=pm;
		this.initView();
	}
	
	public void initView() {

		//Inicializamos el carrito pasandole el catalogo de productos disponibles. Inicializamos 
		//tambien la tabla de productos.
		
		
		//Inicializamos la tabla que representara al pedido 
		inicializarTablaProductos();
		
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
	
	
	private void inicializarTablaProductos() {
		List<ProductoEntity> productos=new ArrayList<>();
		for(ProductoEntity pe:pm.getListaProductos()) {
			if(pe.getStock()<pe.getStockMin())
				productos.add(pe);
		}	
			
		TableModel tmodel= SwingUtil.getTableModelFromPojosToProducts(productos,new String[] {"id","nombre","stock","stockMin","stockReposicion"},"unidades necesarias");
		view.getTable().setModel(tmodel);
		
	}
	
	
}

