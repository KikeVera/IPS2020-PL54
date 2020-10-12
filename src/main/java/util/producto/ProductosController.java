package util.producto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ui.SwingMain;
import util.pedido.PedidosModel;
import util.swingTables.SwingUtil;

public class ProductosController {
	
	private ProductosModel model; 
	private ProductosView view;
	private Carrito carrito;
	private PedidosModel pedidoModel;
	
	
	public ProductosController(ProductosModel m, ProductosView v, PedidosModel pem) {
		this.pedidoModel=pem;
		this.model = m; 
		this.view = v;  
		this.initView();
	}
	
	public void initView() {

		//Inicializamos el carrito pasandole el catalogo de productos disponibles. Inicializamos 
		//tambien la tabla de productos.
		this.carrito = new Carrito(this.getListaProductos());
		
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
		 
		this.view.getBtnAnadir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addProduct();
			}
		});

		
		this.view.getBtnEliminar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteProduct();
			}
		});
		
		this.view.getBtnFinalizarPedido().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarPedido();
				SwingMain frame = new SwingMain();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				view.getFrame().dispose();
				
				
			}
		});
	}
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	public List<ProductoEntity> getListaProductos() {
		List<ProductoEntity> productos = model.getListaProductos();
		TableModel tmodel=SwingUtil.getTableModelFromPojos(productos, new String[] {"id","nombre", "descripcion", "precio"});
		view.getTabProductos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTabProductos());
		
		//Como se guarda la clave del ultimo elemento seleccionado, restaura la seleccion de los detalles
		this.restoreDetail();
		return productos;

	}
	
	private void inicializarTablaPedido() {
		TableModel tmodel= new DefaultTableModel(new String[] {"id","nombre","precio","unidades"},0);
		view.getTabPedido().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTabProductos());
	}
	
	/**
	 * Restaura la informacion del detalle de la carrera para visualizar los valores correspondientes
	 * a la ultima clave almacenada.
	 */
	public void restoreDetail() {}
	
	/**
	 * Al seleccionar un item de la tabla muestra el detalle con el valor del porcentaje de descuento
	 * de la carrera seleccinada y los valores de esta entidad
	 */
	public void updateDetail() {
		
		//Actualizamos la tabla correspondiente al pedido 
		String[] properties = new String[] {"id","nombre","precio","unidades"};
		TableModel tm = SwingUtil.getTableModelFromPedido(properties, carrito);
		this.view.getTabPedido().setModel(tm);
		
		//Actualizamos el precio
		this.view.getTextPrecio().setText(String.format("%.2f",this.carrito.calcPrecio()) + "€");
	}
	
	/**
	 * Añade un producto al carrito. Es el actionListener perteneciente al boton anadir.
	 */
	private void addProduct() {
		int selectedRow = view.getTabProductos().getSelectedRow();
		int ud = (int) view.getSpUnidades().getValue(); 
		
		int id = (int) view.getTabProductos().getValueAt(selectedRow, 0); 
		
		this.carrito.addProduct(id, ud);
		updateDetail();
	}
	
	/**
	 * Elimina un producto del carrito. Es el actionListener perteneciente al boton eliminar.
	 */
	private void deleteProduct() {
		int selectedRow = view.getTabProductos().getSelectedRow();
		int ud = (int) view.getSpUnidades().getValue(); 
		
		int id = (int) view.getTabProductos().getValueAt(selectedRow, 0); 
		
		this.carrito.removeProduct(id, ud);
		updateDetail();
	}
	
	/**
	 *Guarda el pedido en la base de datos
	 */
	
	private void guardarPedido() {
		pedidoModel.setPedido(carrito.getPedido());
		
	}
}

