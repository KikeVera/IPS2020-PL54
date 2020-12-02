package logica.producto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import logica.Controller;
import logica.pagoPedido.PagoPedidoController;
import persistencia.categoria.CategoriaEntity;
import persistencia.categoria.CategoriaModel;
import persistencia.contiene.ContieneEntity;
import persistencia.contiene.ContieneModel;
import persistencia.pedido.PedidosModel;
import persistencia.pertenece.subcategorias.PerteneceSubcategoriaEntity;
import persistencia.pertenece.subcategorias.PerteneceSubcategoriaModel;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;
import persistencia.producto.VentaEntity;
import persistencia.producto.VentasModel;
import persistencia.subcategoria.SubcategoriaEntity;
import persistencia.subcategoria.SubcategoriaModel;
import persistencia.usuario.UsuarioEntity;
import persistencia.usuario.UsuarioModel;

import ui.pagoPedido.PagoPedidoView;
import ui.producto.ProductosView;
import util.Util;
import util.swingTables.SwingUtil;

public class ProductosController implements Controller {

	private ProductosModel model; // Acceso a la base de datos en el ambito de los productos
	private ProductosView view; // Interfaz usuario
	private Carrito carrito; // Carrito que contiene al pedido
	private PedidosModel pedidoModel;
	private int lastSelectedPedidoRow; // Almacena ultima seleccion en la tabla productos

	private Stack<TableModel> navegacion; // Almacena el registro de la navegacion a traves de los menus de categorias y
											// subcategorias
	private PagoPedidoController pagoPedido;
	private VentaEntity venta;
	private VentasModel vm;
	
	public ProductosController(ProductosModel m, ProductosView v, PedidosModel pem, VentasModel vm,
			UsuarioEntity usuario) {
		this.pedidoModel = pem;
		this.model = m;
		this.view = v;
		this.venta = new VentaEntity();
		this.vm = vm;

		this.lastSelectedPedidoRow = 0;

		this.navegacion = new Stack<TableModel>();
		this.pagoPedido = new PagoPedidoController(new PagoPedidoView(), this.view, venta);
		this.initView(usuario);

	}

	@Override
	public void initView() {
		// De momento no utilizado, solo se usa para cumplir con la interfaz
	}

	/**
	 * Inicia la vista
	 * 
	 * @param usuario El usuario que estara realizando el pedido
	 */
	public void initView(UsuarioEntity usuario) {

		// Inicializamos el carrito pasandole el catalogo de productos disponibles.
		// Inicializamos
		// tambien la tabla de productos.
		this.carrito = new Carrito(this.getListaProductos(), usuario);

		// Inicializamos la tabla que representara al pedido
		inicializarTablaPedido();

		view.getLblUsuario().setText(view.getLblUsuario().getText() + " " + this.carrito.getUsuario().getIdUsuario()
				+ " | Tipo de usuario: " + this.carrito.getUsuario().getTipo());

		// Inicializar tabla con categorias
		CategoriaModel m = new CategoriaModel();
		this.navegacion.push(createFirstNavegacion(m.getCategorias()));

		// Establcemos direccion
		this.venta.setTipoUsuario(this.carrito.getUsuario().getTipo());
		if (!this.carrito.getUsuario().getTipo().equals("Anónimo")) {
			this.view.getTextDireccionEnvio().setText(this.carrito.getUsuario().getDireccion());
		}
		if (!this.carrito.getUsuario().getTipo().equals("Empresa")) {
			this.view.getBtnPagarPedido().setEnabled(true);
			this.view.getBtnFinalizarPedido().setEnabled(false);

		}

		// Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true);

	}

	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los
	 * objetos del UI. Cada manejador de eventos se instancia de la misma forma,
	 * para que invoque un metodo privado de este controlador.
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
				// no usa mouseClicked porque al establecer seleccion simple en la tabla de
				// carreras
				// el usuario podria arrastrar el raton por varias filas e interesa solo la
				// ultima
				setLastSelectedPedido();
			}
		});

		this.view.getBtnFinalizarPedido().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarPedido();
			}
		});

		this.view.getBtnSiguiente().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});

		this.view.getBtnAtras().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});

		this.view.getBtnPagarPedido().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarPago();
			}
		});
	}

	/**
	 * La obtencion de la lista de productos solo necesita obtener la lista de
	 * objetos del modelo y usar metodo de SwingUtil para crear un tablemodel que se
	 * asigna finalmente a la tabla.
	 */
	public List<ProductoEntity> getListaProductos() {
		List<ProductoEntity> productos = model.getListaProductos();
		return productos;

	}

	private TableModel createNavegacion(List<SubcategoriaEntity> subCategorias, List<ProductoEntity> productos) {

		// Array que representa las propiedades que se mostraran
		String[] colProperties = null;

		// En funcion del tipo de usuario, se muestra un precio en especifico
		colProperties = new String[] { "id", "nombre", "descripcion", "precio IVA incluido(€)", "info" };

		// Iniciamos el modelo de la tabla
		TableModel tm = new DefaultTableModel(colProperties, subCategorias.size() + productos.size());

		// Introducimo en el modelo las categorias
		int i = 0;
		for (SubcategoriaEntity categoria : subCategorias) {
			tm.setValueAt(categoria.getNombreSubcategoria(), i, 1);
			i++;
		}

		// Introducimos en el modelo los productos
		for (ProductoEntity producto : productos) {
			tm.setValueAt(producto.getId(), i, 0);
			tm.setValueAt(producto.getNombre(), i, 1);
			tm.setValueAt(producto.getDescripcion(), i, 2);

			// Cuidado con el precio a modificar
			if (carrito.getUsuario().getTipo().equals("Empresa")) {
				double auxiliar = producto.getPrecioEmpresa() * (producto.getIVA() / 100.0);
				tm.setValueAt(String.format("%.2f", producto.getPrecioEmpresa() + auxiliar), i, 3);
			} else {
				double auxiliar = producto.getPrecioNormal() * (producto.getIVA() / 100.0);
				tm.setValueAt(String.format("%.2f", producto.getPrecioNormal() + auxiliar), i, 3);
			}

			// Formamos el mensaje de estado
			if (producto.getStock() < producto.getStockMin()) {
				tm.setValueAt("¡Solo quedan " + producto.getStock() + "!", i, 4);
			} else {
				tm.setValueAt("Stock disponible", i, 4);
			}
			i++;
		}

		// Establecemos y ajustamos el modelo de la tabla
		view.getTabProductos().setModel(tm);
		SwingUtil.autoAdjustColumns(view.getTabProductos());

		// Como se guarda la clave del ultimo elemento seleccionado, restaura la
		// seleccion de los detalles
		this.restoreDetail();
		return tm;
	}

	/**
	 * Crea un modelo de tabla para una lista de categorias
	 * 
	 * @param productos Lista de categorias
	 * @return modelo de tabla para esa lista de categorias
	 */
	private TableModel createFirstNavegacion(List<CategoriaEntity> list) {

		// Array que representa las propiedades que se mostraran
		String[] colProperties = null;

		// En funcion del tipo de usuario, se muestra un precio en especifico
		colProperties = new String[] { "id", "nombre", "descripcion", "precio IVA incluido(€)", "info" };

		// Iniciamos el modelo de la tabla
		TableModel tm = new DefaultTableModel(colProperties, list.size());

		// Introducimo en el modelo las categorias
		int i = 0;
		for (CategoriaEntity categoria : list) {
			tm.setValueAt(categoria.getNombreCategoria(), i, 1);
			i++;
		}

		// Se cambia tambien la columna del precio para expresar la moneda usada
		view.getTabProductos().setModel(tm);
		SwingUtil.autoAdjustColumns(view.getTabProductos());

		// Como se guarda la clave del ultimo elemento seleccionado, restaura la
		// seleccion de los detalles
		this.restoreDetail();
		return tm;
	}

	/**
	 * Inicializa la tabla que representa el pedido al iniciar la interfaz de
	 * usuario
	 */
	private void inicializarTablaPedido() {
		TableModel tmodel = new DefaultTableModel(
				new String[] { "id", "nombre", "unidades", "precio bruto total(€)", "precio neto total(€)", "IVA" }, 0);

		view.getTabPedido().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTabPedido());
	}

	/**
	 * Restaura la informacion del detalle de la carrera para visualizar los valores
	 * correspondientes a la ultima clave almacenada.
	 */
	public void restoreDetail() {
	}

	/**
	 * Al seleccionar un item de la tabla muestra el detalle con el valor del
	 * porcentaje de descuento de la carrera seleccinada y los valores de esta
	 * entidad
	 */
	public void updateDetail() {

		// Actualizamos la tabla correspondiente al pedido
		String[] properties = new String[] { "id", "nombre", "unidades", "precio bruto total(€)",
				"precio neto total(€)", "IVA" };
		TableModel tm = SwingUtil.getTableModelFromPedido(properties, carrito);
		this.view.getTabPedido().setModel(tm);
		SwingUtil.autoAdjustColumns(view.getTabPedido());
		this.view.getTabPedido().getSelectionModel().setSelectionInterval(lastSelectedPedidoRow, lastSelectedPedidoRow);

		// Actualizamos el precio
		this.view.getTextPrecioNeto()
				.setText(String.format("%.2f", this.carrito.calcPrecioNeto(carrito.getUsuario().getTipo())) + "€");
		this.view.getSpUnidades().setValue(1);

		this.view.getTextPrecioBruto()
				.setText(String.format("%.2f", this.carrito.calcPrecioBruto(carrito.getUsuario().getTipo())) + "€");

	}

	/**
	 * Añade un producto al carrito. Es el actionListener perteneciente al boton
	 * anadir. En caso de que no haya ningun producto a añadir , se mostrará un
	 * aviso al usuario.
	 */
	private void addProduct() {

		if (view.getTabProductos().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this.view.getFrame(),
					"Debe seleccionar un producto disponible para " + "agregarlo al carrito",
					"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);
		} else {
			int selectedRow = view.getTabProductos().getSelectedRow();
			int ud = (int) view.getSpUnidades().getValue();
			Object aux = view.getTabProductos().getValueAt(selectedRow, 0);

			if (aux == null) {
				JOptionPane.showMessageDialog(this.view.getFrame(), "Solo puede agregar productos al carrito.",
						"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);
			} else {
				ProductosModel pm = new ProductosModel();
				int id = (int) aux;
				if (ud > pm.findProductById(id).get(0).getStock()) {
					JOptionPane.showMessageDialog(this.view.getFrame(), "Insuficiente stock disponible",
							"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);
				} else {
					this.carrito.addProduct(id, ud);
					updateDetail();
					pm.updateStock(id, pm.findProductById(id).get(0).getStock() - ud);
					actualizarVistaStockProducto();
				}

			}
		}
	}
	
	/**
	 * Elimina un producto del carrito. Es el actionListener perteneciente al boton
	 * eliminar. En caso de que no haya ningun producto a eliminar , se mostrará un
	 * aviso al usuario.
	 */
	private void deleteProduct() {

		if (view.getTabPedido().getRowCount() == 0) {
			JOptionPane.showMessageDialog(this.view.getFrame(),
					"Debe seleccionar el producto que desea eliminar de su carrito para " + "elimnarlo",
					"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);
		} else {
			ProductosModel pm = new ProductosModel(); 
			int selectedRow = view.getTabPedido().getSelectedRow();
			int ud = (int) view.getSpUnidades().getValue();
			int id = (int) this.view.getTabPedido().getValueAt(selectedRow, 0); 
			this.carrito.removeProduct(id, ud);
			updateDetail();
			pm.updateStock(id, pm.findProductById(id).get(0).getStock() + ud);
			actualizarVistaStockProducto(); 
		}

	}

	
	/**
	 * Actualiza la vista (parte referente al stock) de la tabla que muestra los productos 
	 */
	private void actualizarVistaStockProducto() {
		ProductosModel model = new ProductosModel(); 
		
		TableModel tm = this.view.getTabProductos().getModel(); 
		for(int i = 0;i < tm.getRowCount();i++) {
			Integer aux = (Integer) tm.getValueAt(i, 0); 
			if(aux != null) {
				int id = (int) aux; 
				int index = searchIdInTable(id);
				if(index != -1) {
					ProductoEntity producto = model.findProductById(id).get(0); 
					int stock = producto.getStock(); 
					if(stock == 0) {
						tm.setValueAt("¡Agotado!",  index, 4);
					}
					else if (stock < producto.getStockMin()) {
						tm.setValueAt("¡Solo quedan " + stock + "!", index, 4);
					} 
					else {
						tm.setValueAt("Stock disponible", index, 4);
					}
				}
			}

			this.view.getTabProductos().repaint();
		}

	}
	
	private int searchIdInTable(int id) {
		for(int i = 0; i < this.view.getTabProductos().getRowCount(); i++) {
			Integer aux = (Integer) this.view.getTabProductos().getValueAt(i, 0); 
			if(aux == null) {
				aux = -1; 
			}
			if( aux == id) {
				return i; 
			}
		}
		return -1; 
	}
	


	/**
	 * Actualiza la ultima seleccion en la tabla referente al pedido
	 */
	private void setLastSelectedPedido() {
		this.lastSelectedPedidoRow = this.view.getTabPedido().getSelectedRow();
	}

	/**
	 * Guarda el pedido en la base de datos
	 */
	private void guardarPedido() {
		if (carrito.getPedido().isEmpty()) {
			JOptionPane.showMessageDialog(this.view.getFrame(), "El pedido esta vacío ", "Tienda online: Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (this.view.getTextDireccionEnvio().getText().isEmpty()
				|| this.view.getTextDireccionEnvio().getText().isBlank()) {
			JOptionPane.showMessageDialog(this.view.getFrame(), "Debe establecer una dirección de envío",
					"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);
			return;
		}

		pedidoModel.setPedido(carrito.getPedido(), carrito.getUsuario().getIdUsuario());
		if (this.carrito.getUsuario().getTipo().equals("Anónimo")) {
			UsuarioModel um = new UsuarioModel();
			um.setUsuario(carrito.getUsuario().getIdUsuario(), "Anónimo", this.view.getTextDireccionEnvio().getText());
		}
		


		guardarVenta();
		view.getFrame().dispose();

	}
	


	/**
	 * Guarda en la base de datos la venta
	 */

	private void guardarVenta() {
		this.venta.setTipoUsuario(this.carrito.getUsuario().getTipo());
		if (this.carrito.getUsuario().getTipo().equals("Empresa")) {
			this.venta.setEmpresa(this.carrito.getUsuario().getIdUsuario());
		}
		this.venta.setImporte(this.carrito.calcPrecioBruto(carrito.getUsuario().getTipo()));
		this.venta.setFecha(Util.dateToIsoString(new Date()));
		vm.setVenta(venta.getFecha(), venta.getTipoPago(), venta.getTipoUsuario(), venta.getEmpresa(),
				venta.getImporte());
	}

	/**
	 * Pasa al siguiente menu de navegacion
	 */
	private void next() {

		// Iniciamos modelos necesarios
		SubcategoriaModel modelS = new SubcategoriaModel();
		ContieneModel modelCon = new ContieneModel();
		PerteneceSubcategoriaModel modelP = new PerteneceSubcategoriaModel();

		// Cogemos categoria/subcatgoria seleccionada y lanzamos mensaje en caso de que
		// no se haya seleccionado
		// ninguna
		int selectedRow = view.getTabProductos().getSelectedRow();

		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this.view.getFrame(),
					"Debe seleccionar una categoría/subcategoría disponible para " + "entrar en ella.",
					"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);

		} else if (view.getTabProductos().getValueAt(selectedRow, 0) != null) {
			JOptionPane.showMessageDialog(this.view.getFrame(), "Solo puede entrar a categorías o subcategorías.",
					"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);
		} else {

			// Cogemos el nombre de la categoria/subcategoria/producto
			String nombre = (String) view.getTabProductos().getValueAt(selectedRow, 1);

			if (this.navegacion.size() == 1) { // Si es la primera iteracion desde las categorias principales

				List<SubcategoriaEntity> subcategorias = modelS.getSubcategoriasByCategoria(nombre);
				List<ProductoEntity> aux = this.model.getListaProductosByCategoria(nombre);
				List<ProductoEntity> productos = new ArrayList<ProductoEntity>();
				for (ProductoEntity p : aux) {
					if (checkProducto(p.getId(), subcategorias)) {
						productos.add(p);
					}
				}
				

				// Control de botones
				buttonControl(subcategorias, productos);
				this.navegacion.push(createNavegacion(subcategorias, productos));

			} else { // En caso de que no sea la primera iteracion

				// Obtenemos sus subcategorias
				List<ContieneEntity> relacionesC = modelCon.getSubcategoriasContenidasByName(nombre);
				List<SubcategoriaEntity> subcategorias = new ArrayList<SubcategoriaEntity>();
				for (ContieneEntity c : relacionesC) {
					subcategorias.add(modelS.getSubcategoriasByNombre(c.getNombreSubcategoriaContenida()).get(0));
				}

				// Obtenemos la relacion que encaja con la subcategoria
				List<PerteneceSubcategoriaEntity> relacionesP = modelP.getPerteneceBySubcategoria(nombre);
				// Creamos la lista de productos directos
				List<ProductoEntity> productos = new ArrayList<ProductoEntity>();
				for (PerteneceSubcategoriaEntity p : relacionesP) {
					if (checkProducto(p.getIdProducto(), subcategorias)) {
						productos.add(this.model.findProductById(p.getIdProducto()).get(0));
					}
				}


				// Control de botones
				buttonControl(subcategorias, productos);
				this.navegacion.push(createNavegacion(subcategorias, productos));

			}
			
			actualizarVistaStockProducto(); 
		}

		// Control de botones
		if (this.navegacion.size() > 1) { // Si hay mas de un menu hacia atras
			this.view.getBtnAtras().setEnabled(true);
		} else { // Si no hay mas menus hacia atras
			this.view.getBtnAtras().setEnabled(false);
		}
		
	}

	/**
	 * Comprueba que un producto no pertenezca a una lista de subcategorias
	 * 
	 * @param idProducto    a analizar
	 * @param subcategorias lista de subcategorias a analizar
	 * @return true si el producto no esta en niguna de las subcategorias o false en
	 *         caso contrario
	 */
	private boolean checkProducto(int idProducto, List<SubcategoriaEntity> subcategorias) {
		PerteneceSubcategoriaModel model = new PerteneceSubcategoriaModel();
		boolean aux = true;
		for (SubcategoriaEntity subcategoria : subcategorias) {
			if (!model.findByIdAndName(idProducto, subcategoria.getNombreSubcategoria()).isEmpty()) {
				aux = false;
			}
		}
		return aux;
	}

	/**
	 * Actualiza los botones de la tienda en funcion del siguiente "next"
	 * 
	 * @param subcategorias Lista de subcategorias
	 * @param productos     Lista de productos
	 */
	private void buttonControl(List<SubcategoriaEntity> subcategorias, List<ProductoEntity> productos) {
		if (productos.size() > 0) {
			this.view.getBtnAnadir().setEnabled(true);
			this.view.getBtnEliminar().setEnabled(true);
		}
		if (subcategorias.isEmpty()) {
			this.view.getBtnSiguiente().setEnabled(false);
		}
	}

	/**
	 * Pasa al anteior menu de navegacion
	 */
	private void back() {

		// Restablcemos el table model anterior
		this.navegacion.pop();
		TableModel t = this.navegacion.peek();
		this.view.getTabProductos().setModel(t);

		// Control de botones
		if (this.navegacion.size() > 1) { // Si hay mas menus
			this.view.getBtnAtras().setEnabled(true);
		} else { // Si solo hay un menu
			this.view.getBtnAtras().setEnabled(false);
		}

		// Controlamos el boton de añadir/eliminar productos en el menu de
		// catgorias/subcategorias
		this.view.getBtnAnadir().setEnabled(false);
		this.view.getBtnEliminar().setEnabled(false);
		this.view.getBtnSiguiente().setEnabled(true);
		
		actualizarVistaStockProducto();
	}

	private void iniciarPago() {
		if (this.carrito.getPedido().isEmpty()) {
			JOptionPane.showMessageDialog(this.view.getFrame(), "No puede realizar un pedido vacío",
					"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);
			return;
		}
		this.pagoPedido.initView();
	}

}
