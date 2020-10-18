package logica.pedido;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import logica.Controller;
import logica.producto.ProductoOT;
import persistencia.almacenero.OTModel;
import persistencia.pedido.PedidosModel;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;
import ui.almacen.AlmacenView;
import ui.almacen.OperacionesOTView;
import util.Util;
import util.swingTables.SwingUtil;

public class AlmacenController implements Controller {
	
	private ProductosModel model; 
	private AlmacenView view;
	
	private PedidosModel pedidoModel;
	private OTModel otmodel;
	
	
	public AlmacenController(ProductosModel m, AlmacenView v, PedidosModel pem,OTModel otm) {
		this.pedidoModel=pem;
		this.model = m; 
		this.view = v;  
		this.otmodel=otm;
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
		this.view.getbtVerPedido().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verPedido();
			}
		});
		
		this.view.getbtAsignar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				asignarOT();
			}
		});
		
		this.view.getbtSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getFrame().dispose();
			}
		});
		
		this.view.getbtOperacionesOT().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OperacionesOTController controller=new OperacionesOTController(new OperacionesOTView(), new OTModel());
				controller.initController();
			}
		});
		
		
	}
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	
	
	private void inicializarTablaPedido() {
		List<PedidoUse> pedidos=Util.entityToUseList(pedidoModel.getPedidos());
		TableModel tmodel= SwingUtil.getTableModelFromPojos(pedidos,new String[] {"id","fecha","tamaño"});
		view.getTabPedidos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTabPedidos());
	}
	
	private void verPedido() {
		
		
		int index=view.getTabPedidos().getSelectedRow();
		if(index==-1) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: Pedido no seleccionado","Advertencia operacion", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		List<ProductoEntity> catalogo=model.getListaProductos();
		PedidoUse pedido=Util.entityToUseList(pedidoModel.getPedidos()).get(index);
		List<ProductoOT> productos=Util.hashMapToProductsList(pedido.getProductos(), catalogo);
		TableModel tmodel= SwingUtil.getTableModelFromPojos(productos,new String[] {"id","nombre","unidades"});
		
		this.view.getTabProductos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTabProductos());
	}
	
	//Guardamos la orden de trabajo en la base de datos
	private void asignarOT() {
		int index=view.getTabPedidos().getSelectedRow();
		if(index==-1) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: Orden no seleccionada","Advertencia operacion", JOptionPane.WARNING_MESSAGE);
			return;
		}
		PedidoUse pedido=Util.entityToUseList(pedidoModel.getPedidos()).get(view.getTabPedidos().getSelectedRow());
		
		//De momento le vamos a pasar el id de almacenero 1 ya que solo hay 1 almacenero
		otmodel.setOT(pedido.getId(), 1);	
	}
	
}

