package util.pedido;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.TableModel;

import util.Util;
import util.almacenero.OTEntity;
import util.almacenero.OTModel;
import util.producto.ProductoEntity;
import util.producto.ProductoPedido;
import util.producto.ProductosModel;
import util.swingTables.SwingUtil;

public class AlmacenController {
	
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
		
		
	}
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	
	
	private void inicializarTablaPedido() {
		List<PedidoUse> pedidos=Util.entityToUse(pedidoModel.getPedidos());
		TableModel tmodel= SwingUtil.getTableModelFromPojos(pedidos,new String[] {"id","fecha","tamaño"});
		view.getTabPedidos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTabPedidos());
	}
	
	private void verPedido() {
		List<ProductoEntity> catalogo=model.getListaProductos();
		PedidoUse pedido=Util.entityToUse(pedidoModel.getPedidos()).get(view.getTabPedidos().getSelectedRow());
		List<ProductoPedido> productos=Util.hashMapToProductsList(pedido.getProductos(), catalogo);
		TableModel tmodel= SwingUtil.getTableModelFromPojos(productos,new String[] {"id","nombre","descripcion","precio","unidades"});
		
		this.view.getTabProductos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTabProductos());
	}
	
	//Guardamos la orden de trabajo en la base de datos
	private void asignarOT() {
		PedidoUse pedido=Util.entityToUse(pedidoModel.getPedidos()).get(view.getTabPedidos().getSelectedRow());
		
		//De momento le vamos a pasar el id de almacenero 1 ya que solo hay 1 almacenero
		otmodel.setOT(pedido.getId(), 1);	
		
		for(OTEntity ot:otmodel.getOTs()) {
			System.out.println(ot.getId_ot()+" "+ot.getStatus()+" "+ot.getId_almacenero()+" "+ot.getId_pedido());
		}
	}
	
	
	
	
	
	
	
	
	
	
}

