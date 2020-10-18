package logica.pedido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.TableModel;

import logica.Controller;
import persistencia.almacenero.OTEntity;
import persistencia.pedido.PedidosModel;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductoInfo;
import persistencia.producto.ProductosModel;
import ui.almacen.InformacionView;
import util.Util;
import util.swingTables.SwingUtil;

public class InformacionController implements Controller {
	
	private ProductosModel model; 
	private InformacionView view;
	private OTEntity ot;
	
	private PedidosModel pem;
	List<Integer> idPedidos;
	
	public InformacionController(OTEntity ote,ProductosModel pm,PedidosModel pedmod) {		
		this.ot=ote;
		this.model=pm;
		this.pem=pedmod;
		
		idPedidos=new ArrayList<>();
		this.initView();

	}
	
	public void initView() {

		inicializarTabla();
		
		view.getFrame().setVisible(true);
		
	}
	
	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los objetos del UI.
	 * Cada manejador de eventos se instancia de la misma forma, para que invoque un metodo privado
	 * de este controlador.
	 */
	public void initController() {
		
		this.view.getBtTerminar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getFrame().dispose();				
			}
		});		
	}
	
/**
 * 
 */
	private void inicializarTabla() {
		List<ProductoEntity> catalogo=model.getListaProductos();
		
		List<PedidoUse> Pedidos=new ArrayList<>();

		idPedidos.add(ot.getIdPedido());

		for(Integer id: idPedidos) {
			Pedidos.add(Util.entityToUse(this.pem.getPedidoID(id)));
		}

		List<ProductoInfo> productos=Util.hashMapToProductEntityList(Pedidos.get(0).getProductos(), catalogo);
		Collections.sort(productos);
		
		/*for(ProductoInfo pi:productos)
			System.out.println(pi);*/
		
		TableModel tmodel= SwingUtil.getTableModelFromPojos(productos,new String[] {"nombre","pasillo","estanteria"});
		view.getTableProductos().setModel(tmodel);
	}

}