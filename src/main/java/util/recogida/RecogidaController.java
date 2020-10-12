package util.recogida;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import javax.swing.table.TableModel;

import negocio.almacen.Incidencia;
import negocio.almacen.Recogida;
import ui.recogida.ComparacionView;
import ui.recogida.IncidenciaView;
import ui.recogida.RevisionView;
import util.Util;
import util.pedido.PedidoUse;
import util.producto.ProductoEntity;
import util.producto.ProductoPedido;
import util.producto.ProductosModel;
import util.swingTables.SwingUtil;

public class RecogidaController {
	
	private ProductosModel model; 
	private IncidenciaModel incidenciaModel;
	private RevisionView view;
	private PedidoUse pedido;
	private Recogida recogida;
	
	
	
	
	public RecogidaController(ProductosModel m,IncidenciaModel im, RevisionView v, PedidoUse pedido) {
		this.pedido=pedido;
		this.model = m; 
		this.view = v;  
		this.incidenciaModel=im;
		
		recogida= new Recogida(Util.hashMapToProductsList(this.pedido.getProductos(), model.getListaProductos()));
		this.initView();
	}
	
	public void initView() {

		//Inicializamos el carrito pasandole el catalogo de productos disponibles. Inicializamos 
		//tambien la tabla de productos.
		
		
		//Inicializamos la tabla que representara al pedido 
		inicializarTabla();
		
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true);
		
	}
	
	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los objetos del UI.
	 * Cada manejador de eventos se instancia de la misma forma, para que invoque un metodo privado
	 * de este controlador.
	 */
	public void initController() {
		
		this.view.getBtComprobar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ComparacionView compara= new ComparacionView(getSelectedProduct(), getAlmacenProduct());
				compara.setLocationRelativeTo(view.getFrame());
				compara.setModal(true);
				compara.setVisible(true);
				
			}
		});
		
		this.view.getBtIncidencia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				IncidenciaView compara= new IncidenciaView(recogida);
				compara.setLocationRelativeTo(view.getFrame());
				compara.setModal(true);
				compara.setVisible(true);
				
			}
		});
		
		this.view.getBtSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				view.getFrame().dispose();
				
			}
		});
		
		this.view.getBtGuardarIncidencias().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				guardarIncidencias();
				
			}
		});
		
		
	}
	
	private void guardarIncidencias() {
		List<IncidenciaEntity> lista= new ArrayList<IncidenciaEntity>();
		for(Incidencia incidencia: recogida.getIncidencias()) {
			lista.add(new IncidenciaEntity(pedido.getId(), incidencia.getDescripcion()));
		
		}
		
		incidenciaModel.setIncidencias(lista);
	}
	
	private ProductoPedido getSelectedProduct() {
		return recogida.getPedido().get(view.getTableProductos().getSelectedRow());
		
	}
	
	private ProductoEntity getAlmacenProduct() {
		List<ProductoEntity> catalogo= model.getListaProductos();
		ProductoPedido buscado= getSelectedProduct();
		for(ProductoEntity entity:catalogo) {
			if (entity.getId()==buscado.getId()) {
				return entity;
			}
		}
		return new ProductoEntity(-1,"Producto no encontrado","",0);
	}
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	
	
	private void inicializarTabla() {
		List<ProductoPedido> productos=recogida.getPedido();
		TableModel tmodel= SwingUtil.getTableModelFromPojos(productos,new String[] {"id","nombre","descripcion","precio","unidades"});
		this.view.getTableProductos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableProductos());
	}
	
	
	
	
	
	
	
	
	
	
}

