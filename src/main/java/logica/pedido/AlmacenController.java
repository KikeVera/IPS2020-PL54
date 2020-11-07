package logica.pedido;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import logica.Controller;
import logica.producto.ProductoOT;
import persistencia.almacenero.OTEntity;
import persistencia.almacenero.OTModel;
import persistencia.pedido.PedidosModel;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;
import ui.SwingMain;
import ui.almacen.AlmacenView;
import ui.almacen.OperacionesOTView;
import util.Util;
import util.swingTables.SwingUtil;

public class AlmacenController implements Controller {
	
	private ProductosModel model; 
	private AlmacenView view;
	
	private PedidosModel pedidoModel;
	private OTModel otmodel;
	private List<PedidoUse> pedidos;
	
	private List<String> idpedidos = new ArrayList<String>();	
	private final int size=15;
	private int actual=0;
	
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
				SwingMain frame = new SwingMain();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		
		this.view.getbtOperacionesOT().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OperacionesOTController controller=new OperacionesOTController(new OperacionesOTView(), new OTModel());
				controller.initController();
				view.getFrame().dispose();
			}
		});
		
		
	}
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	
	
	private void inicializarTablaPedido() {
		pedidos=new ArrayList<>();
		
		for(PedidoUse pedido:Util.entityToUseList(pedidoModel.getPedidos())) {
			if(otmodel.getOTByIdPedido(Integer.toString(pedido.getId())).isEmpty()) {
				pedidos.add(pedido);
			}
		}
			
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
		PedidoUse pedido=pedidos.get(index);

		//otmodel.updateStatus(otmodel.getOTByIdPedido(Integer.toString(pedido.getId())).get(0).getIdOt(), "CAPWELL");

		idpedidos.add(Integer.toString(pedido.getId()));
		if(idpedidos.size()==1 && sumaTamPedidos(idpedidos)<=this.size) {
			otmodel.setOT(idpedidos, sumaTamPedidos(idpedidos)); 	
		}
		else if(sumaTamPedidos(idpedidos)<=this.size) {
			int id_ot=this.otmodel.getOTByIdPedido(idpedidos.get(0)).get(0).getIdOt();
			otmodel.updateOT(id_ot, sumaTamPedidos(idpedidos), idpedidos);	
		}
		else if(sumaTamPedidos(idpedidos)>this.size) {
			String aux=idpedidos.get(idpedidos.size());
			idpedidos.clear();
			idpedidos.add(aux);
			otmodel.setOT(idpedidos, sumaTamPedidos(idpedidos)); 
		}
		else if(idpedidos.size()==1 && sumaTamPedidos(idpedidos)>this.size) //Falta implementar
			Util.dividePedido(pedido.getProductos(),size);	
		
		List<OTEntity> lo=otmodel.getOTs();
		inicializarTablaPedido();
			
	}
	/**
	 *  Suma el tamaño de los pedidos de una lista pasada como parametro
	 * @param lista
	 * @return total
	 */
	private int sumaTamPedidos(List<String> lista) {
		int total=0;
		for(String s:lista) {
			total+=this.pedidoModel.getPedido(Integer.parseInt(s)).getTamaño();
		}
		return total;
	}
}

