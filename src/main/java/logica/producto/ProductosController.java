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
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import logica.Controller;
import logica.pagoPedido.PagoPedidoController;
import persistencia.categoria.CategoriaEntity;
import persistencia.categoria.CategoriaModel;
import persistencia.contiene.ContieneEntity;
import persistencia.contiene.ContieneModel;
import persistencia.pedido.PedidosModel;
import persistencia.pertenece.PerteneceEntity;
import persistencia.pertenece.PerteneceModel;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;
import persistencia.subcategoria.SubcategoriaEntity;
import persistencia.subcategoria.SubcategoriaModel;
import persistencia.usuario.UsuarioEntity;
import persistencia.usuario.UsuarioModel;
import ui.SwingMain;
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
	private Venta venta;

	public ProductosController(ProductosModel m, ProductosView v, PedidosModel pem, UsuarioEntity usuario) {
		this.pedidoModel = pem;
		this.model = m;
		this.view = v;
		this.venta=new Venta();
		this.venta.setEmpresa(Util.dateToIsoString(new Date()));
		this.lastSelectedPedidoRow = 0;
		this.navegacion = new Stack<TableModel>();
		this.pagoPedido = new PagoPedidoController(new PagoPedidoView(),this.view,venta);
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
		this.navegacion.push(createNavegacionCategorias(m.getCategorias()));

		// Establcemos direccion
		this.venta.setTipoUsuario(this.carrito.getUsuario().getTipo());
		if (!this.carrito.getUsuario().getTipo().equals("Anónimo")) {
			this.view.getTextDireccionEnvio().setText(this.carrito.getUsuario().getDireccion());
		}
		if (!this.carrito.getUsuario().getTipo().equals("Empresa")) {
			this.view.getBtnPagarPedido().setEnabled(true);
			this.view.getBtnFinalizarPedido().setEnabled(false);
			this.venta.setEmpresa(this.carrito.getUsuario().getIdUsuario());
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

	/**
	 * Crea un modelo de tabla para una lista de productos
	 * 
	 * @param productos Lista de productos
	 * @return modelo de tabla para esa lista de productos
	 */
	private TableModel createNavegacionProductos(List<ProductoEntity> productos) {
		TableModel tmodel = null; 
		
		if(carrito.getUsuario().getTipo().equals("Empresa")) {
			
			tmodel = SwingUtil.getTableModelFromPojos(productos,
					new String[] { "id", "nombre", "descripcion","precioEmpresa" });
			
			// Se cambia tambien la columna del precio para expresar la moneda usada
			view.getTabProductos().setModel(tmodel);
			TableColumn columna = view.getTabProductos().getColumn("precioEmpresa");
			columna.setHeaderValue("precio(€)");
			SwingUtil.autoAdjustColumns(view.getTabProductos());
		}
		else {
			tmodel = SwingUtil.getTableModelFromPojos(productos,
					new String[] { "id", "nombre", "descripcion","precioNormal" });
			
			// Se cambia tambien la columna del precio para expresar la moneda usada
			view.getTabProductos().setModel(tmodel);
			TableColumn columna = view.getTabProductos().getColumn("precioNormal");
			columna.setHeaderValue("precio(€)");
			
		}
		
		SwingUtil.autoAdjustColumns(view.getTabProductos());

		// Como se guarda la clave del ultimo elemento seleccionado, restaura la
		// seleccion de los detalles
		this.restoreDetail();
		return tmodel;
	}

	/**
	 * Crea un modelo de tabla para una lista de categorias
	 * 
	 * @param productos Lista de categorias
	 * @return modelo de tabla para esa lista de categorias
	 */
	private TableModel createNavegacionCategorias(List<CategoriaEntity> list) {
		TableModel tmodel = SwingUtil.getTableModelFromPojos(list, new String[] { "nombreCategoria" });

		// Se cambia tambien la columna del precio para expresar la moneda usada
		view.getTabProductos().setModel(tmodel);
		TableColumn columna = view.getTabProductos().getColumn("nombreCategoria");
		columna.setHeaderValue("Categoría");
		SwingUtil.autoAdjustColumns(view.getTabProductos());

		// Como se guarda la clave del ultimo elemento seleccionado, restaura la
		// seleccion de los detalles
		this.restoreDetail();
		return tmodel;
	}

	/**
	 * Crea un modelo de tabla para una lista de subcategorias
	 * 
	 * @param productos Lista de subcategorias
	 * @return modelo de tabla para esa lista de subcategorias
	 */
	private TableModel createNavegacionSubcategorias(List<SubcategoriaEntity> subcategorias) {
		TableModel tmodel = SwingUtil.getTableModelFromPojos(subcategorias, new String[] { "nombreSubcategoria" });

		// Se cambia tambien la columna del precio para expresar la moneda usada
		view.getTabProductos().setModel(tmodel);
		TableColumn columna = view.getTabProductos().getColumn("nombreSubcategoria");
		columna.setHeaderValue("Subcategoría");
		SwingUtil.autoAdjustColumns(view.getTabProductos());

		// Como se guarda la clave del ultimo elemento seleccionado, restaura la
		// seleccion de los detalles
		this.restoreDetail();
		return tmodel;
	}

	/**
	 * Inicializa la tabla que representa el pedido al iniciar la interfaz de
	 * usuario
	 */
	private void inicializarTablaPedido() {
		TableModel tmodel = new DefaultTableModel(new String[] { "id", "nombre", "unidades", "precio total(€)" }, 0);

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
		String[] properties = new String[] { "id", "nombre", "unidades", "precio total(€)" };
		TableModel tm = SwingUtil.getTableModelFromPedido(properties, carrito);
		this.view.getTabPedido().setModel(tm);
		this.view.getTabPedido().getSelectionModel().setSelectionInterval(lastSelectedPedidoRow, lastSelectedPedidoRow);

		// Actualizamos el precio
		this.view.getTextPrecio().setText(String.format("%.2f", this.carrito.calcPrecio(carrito.getUsuario().getTipo())) + "€");
		this.view.getSpUnidades().setValue(1);
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

			int id = (int) view.getTabProductos().getValueAt(selectedRow, 0);

			this.carrito.addProduct(id, ud);
			updateDetail();
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
	 * Guarda el pedido en la base de datos
	 */
	private void guardarPedido() {
		if (carrito.getPedido().isEmpty()) {
			JOptionPane.showMessageDialog(this.view.getFrame(), "El pedido esta vacío ", "Tienda online: Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return; 
		}
		if (this.view.getTextDireccionEnvio().getText().isEmpty() || this.view.getTextDireccionEnvio().getText().isBlank()) {
			JOptionPane.showMessageDialog(this.view.getFrame(), "Debe establecer una dirección de envío",
					"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);
			return; 
		}

		pedidoModel.setPedido(carrito.getPedido(), carrito.getUsuario().getIdUsuario());
		if(this.carrito.getUsuario().getTipo().equals("Anónimo")) {
			UsuarioModel um = new UsuarioModel(); 
			um.setUsuario(carrito.getUsuario().getIdUsuario(), "Anónimo", this.view.getTextDireccionEnvio().getText());
		}
		
		this.venta.setImporte(Double.parseDouble(view.getTextPrecio().getText()));
		view.getFrame().dispose();
		SwingMain frame = new SwingMain();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	/**
	 * Pasa al siguiente menu de navegacion
	 */
	private void next() {

		// Iniciamos modelos necesarios
		CategoriaModel modelC = new CategoriaModel();
		SubcategoriaModel modelS = new SubcategoriaModel();
		ContieneModel modelCon = new ContieneModel();
		PerteneceModel modelP = new PerteneceModel();

		// Cogemos categoria/subcatgoria seleccionada y lanzamos mensaje en caso de que
		// no se haya seleccionado
		// ninguna
		int selectedRow = view.getTabProductos().getSelectedRow();

		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this.view.getFrame(),
					"Debe seleccionar una categoría/subcategoría disponible para " + "entrar en ella.",
					"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);

		} else {

			// Cogemos el nombre de la categoria/subcategoria/producto
			String nombre = (String) view.getTabProductos().getValueAt(selectedRow, 0);

			if (this.navegacion.size() == 1) { // Si es la primera iteracion desde las categorias principales

				// Si la categoria no es pura
				if (modelC.getCategoriasNoPurasByNombre(nombre).size() == 1) {
					// Obtenemos sus subcategorias
					List<SubcategoriaEntity> subcategorias = modelS.getSubcategoriasByCategoria(nombre);
					this.navegacion.push(createNavegacionSubcategorias(subcategorias));
				} else { // Si la categoria es pura
					// Obtenemos productos directos
					List<ProductoEntity> productos = this.model.getListaProductosByCategoria(nombre);

					// Control de botones
					this.navegacion.push(createNavegacionProductos(productos));
					this.view.getBtnSiguiente().setEnabled(false);
					this.view.getBtnAnadir().setEnabled(true);
					this.view.getBtnEliminar().setEnabled(true);

				}
			} else { // En caso de que no sea la primera iteracion

				// Si la subcategoria no es pura
				if (modelS.getSubcategoriasNoPurasByNombre(nombre).size() == 1) {
					// Obtenemos sus subcategorias
					List<ContieneEntity> relaciones = modelCon.getSubcategoriasContenidasByName(nombre);

					List<SubcategoriaEntity> subcategorias = new ArrayList<SubcategoriaEntity>();
					for (ContieneEntity c : relaciones) {
						subcategorias.add(modelS.getSubcategoriasByNombre(c.getNombreSubcategoriaContenida()).get(0));
					}
					this.navegacion.push(createNavegacionSubcategorias(subcategorias));

				} else { // Si la subcategoria es pura
							// Obtenemos la relacion que encaja con la subcategoria
					List<PerteneceEntity> relaciones = modelP.getPerteneceBySubcategoria(nombre);

					// Creamos la lista de productos directos
					List<ProductoEntity> productos = new ArrayList<ProductoEntity>();
					for (PerteneceEntity p : relaciones) {
						productos.add(this.model.findProductById(p.getIdProducto()).get(0));
					}

					// Control de botones
					this.view.getBtnSiguiente().setEnabled(false);
					this.view.getBtnAnadir().setEnabled(true);
					this.view.getBtnEliminar().setEnabled(true);
					this.navegacion.push(createNavegacionProductos(productos));
				}
			}
		}

		// Control de botones
		if (this.navegacion.size() > 1) { // Si hay mas de un menu hacia atras
			this.view.getBtnAtras().setEnabled(true);
		} else { // Si no hay mas menus hacia atras
			this.view.getBtnAtras().setEnabled(false);
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
