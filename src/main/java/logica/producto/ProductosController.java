package logica.producto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import logica.Controller;
import persistencia.pedido.PedidosModel;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;
import persistencia.usuario.UsuarioEntity;
import ui.SwingMain;
import ui.producto.ProductosView;
import util.swingTables.SwingUtil;

public class ProductosController implements Controller {
	
	private ProductosModel model; //Acceso a la base de datos en el ambito de los productos 
	private ProductosView view; //Interfaz usuario 
	private Carrito carrito; //Carrito que contiene al pedido 
	private PedidosModel pedidoModel;
	private int lastSelectedPedidoRow; //Almacena ultima seleccion en la tabla productos

 
	public ProductosController(ProductosModel m, ProductosView v, PedidosModel pem,UsuarioEntity usuario) {
		this.pedidoModel=pem;
		this.model = m; 
		this.view = v; 
		this.lastSelectedPedidoRow = 0;   
		this.initView(usuario);
	}
	
	@Override
	public void initView() {
		// De momento no utilizado, solo se usa para cumplir con la interfaz	
	}
	
	/**
	 * Inicia la vista 
	 * @param usuario El usuario que estara realizando el pedido
	 */
	public void initView(UsuarioEntity usuario) {

		//Inicializamos el carrito pasandole el catalogo de productos disponibles. Inicializamos 
		//tambien la tabla de productos.
		this.carrito = new Carrito(this.getListaProductos(),usuario);
		
		//Inicializamos la tabla que representara al pedido 
		inicializarTablaPedido();
		
		view.getLblUsuario().setText(view.getLblUsuario().getText() + " " + this.carrito.getUsuario().getCodigo()
				+ " Tipo de usuario: " + this.carrito.getUsuario().getTipo());
		
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
		
		
		view.getTabPedido().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//no usa mouseClicked porque al establecer seleccion simple en la tabla de carreras
				//el usuario podria arrastrar el raton por varias filas e interesa solo la ultima
				setLastSelectedPedido(); 
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
	 * La obtencion de la lista de productos solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	public List<ProductoEntity> getListaProductos() {
		List<ProductoEntity> productos = model.getListaProductos();
		TableModel tmodel=SwingUtil.getTableModelFromPojos(productos, new String[] {"id","nombre", "descripcion", "precio"});
		
		//Se cambia tambien la columna del precio para expresar la moneda usada
		view.getTabProductos().setModel(tmodel);
		TableColumn columna = view.getTabProductos().getColumn("precio"); 
		columna.setHeaderValue("precio(€)");
		SwingUtil.autoAdjustColumns(view.getTabProductos());
		
		//Como se guarda la clave del ultimo elemento seleccionado, restaura la seleccion de los detalles
		this.restoreDetail();
		return productos;

	}
	
	/**
	 * Inicializa la tabla que representa el pedido al iniciar la interfaz de usuario 
	 */
	private void inicializarTablaPedido() {
		TableModel tmodel= new DefaultTableModel(new String[] {"id","nombre","unidades","precio total(€)"},0);
		
		view.getTabPedido().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTabPedido());
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
		String[] properties = new String[] {"id","nombre","unidades","precio total(€)"};
		TableModel tm = SwingUtil.getTableModelFromPedido(properties, carrito);
		this.view.getTabPedido().setModel(tm);
		this.view.getTabPedido().getSelectionModel().setSelectionInterval(lastSelectedPedidoRow,lastSelectedPedidoRow);
		
		//Actualizamos el precio
		this.view.getTextPrecio().setText(String.format("%.2f",this.carrito.calcPrecio()) + "€");
		this.view.getSpUnidades().setValue(1);
	}
	
	/**
	 * Añade un producto al carrito. Es el actionListener perteneciente al boton anadir.
	 * En caso de que no haya ningun producto a añadir , se mostrará un aviso al usuario.
	 */
	private void addProduct() {
		
		if(view.getTabProductos().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this.view.getFrame(),"Debe seleccionar un producto disponible para "
					+ "agregarlo al carrito","Tienda online: Advertencia",JOptionPane.WARNING_MESSAGE);
		}	
		else {
			int selectedRow = view.getTabProductos().getSelectedRow();
			int ud = (int) view.getSpUnidades().getValue(); 

			int id = (int) view.getTabProductos().getValueAt(selectedRow, 0); 
			
			this.carrito.addProduct(id, ud);
			updateDetail();
		}
	}
	
	/**
	 * Elimina un producto del carrito. Es el actionListener perteneciente al boton eliminar.
	 * En caso de que no haya ningun producto a eliminar , se mostrará un aviso al usuario.
	 */
	private void deleteProduct() {
		
		if(view.getTabPedido().getRowCount() == 0) {
			JOptionPane.showMessageDialog(this.view.getFrame(),"Debe seleccionar el producto que desea eliminar de su carrito para "
					+ "elimnarlo","Tienda online: Advertencia",JOptionPane.WARNING_MESSAGE);
		}	
		else {
			int selectedRow = view.getTabPedido().getSelectedRow();
			int ud = (int) view.getSpUnidades().getValue(); 
			
			int id = (int) view.getTabPedido().getValueAt(selectedRow, 0); 
			
			this.carrito.removeProduct(id, ud);
			updateDetail();
		}

	}
	
	/**
	 * Actualiza la ultima seleccion en la tabla referente al pedido 
	 */
	private void setLastSelectedPedido() {
		this.lastSelectedPedidoRow = this.view.getTabPedido().getSelectedRow(); 
	}
	
	/**
	 *Guarda el pedido en la base de datos
	 */
	
	private void guardarPedido() {
		if(carrito.getPedido().isEmpty()) {
			JOptionPane.showMessageDialog(this.view.getFrame(),"El pedido esta vacío ","Tienda online: Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
			}
		pedidoModel.setPedido(carrito.getPedido(),carrito.getUsuario().getCodigo());
		
	}


	

}

