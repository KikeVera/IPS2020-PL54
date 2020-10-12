package negocio.almacen;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


import javax.swing.table.TableModel;

import ui.almacen.ComparacionView;
import ui.almacen.IncidenciaView;
import ui.almacen.RevisionView;

import util.producto.ProductoEntity;
import util.producto.ProductoPedido;
import util.producto.ProductosModel;
import util.swingTables.SwingUtil;

public class RecogidaController {
	
	private ProductosModel model; 
	private RevisionView view;
	private Recogida recogida;
	
	
	
	
	public RecogidaController(ProductosModel m, RevisionView v, Recogida recogida) {
		this.recogida=recogida;
		this.model = m; 
		this.view = v;  
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

