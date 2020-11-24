package logica.informes;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import logica.Controller;
import logica.producto.ProductoOT;
import persistencia.almacenero.OTEntity;
import persistencia.almacenero.OTModel;
import persistencia.paquete.PaqueteModel;
import persistencia.pedido.PedidosModel;
import persistencia.pedido.TrozoEntity;
import persistencia.pedido.TrozosModel;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;
import ui.SwingMain;
import ui.almacen.AlmacenView;
import ui.almacen.OperacionesOTView;
import ui.informe.InformePaquetesView;
import util.Util;
import util.swingTables.SwingUtil;

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
		crearInforme();
		
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
	
	public void crearInforme() {
		
		
	}
	
	
	
}

