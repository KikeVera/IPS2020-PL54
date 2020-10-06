package util.producto;

import java.util.List;

import javax.swing.table.TableModel;

import util.swingTables.SwingUtil;

public class ProductosController {
	
	private ProductosModel model; 
	private ProductosView view; 
	
	
	public ProductosController(ProductosModel m, ProductosView v) {
		this.model = m; 
		this.view = v; 
		this.initView();
	}
	
	public void initView() {
		//Inicializa la fecha de hoy a un valor que permitira mostrar carreras en diferentes fases 
		//y actualiza los datos de la vista
		this.getListaProductos();
		
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true); 
	}
	
	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los objetos del UI.
	 * Cada manejador de eventos se instancia de la misma forma, para que invoque un metodo privado
	 * de este controlador, encerrado en un manejador de excepciones generico para mostrar ventanas
	 * emergentes cuando ocurra algun problema o excepcion controlada.
	 */
	public void initController() {}
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	public void getListaProductos() {
		List<ProductoEntity> productos = model.getListaProductos();
		TableModel tmodel=SwingUtil.getTableModelFromPojos(productos, new String[] {"id", "nombre", "descripcion"});
		view.getTablaProductos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablaProductos());
		
		//Como se guarda la clave del ultimo elemento seleccionado, restaura la seleccion de los detalles
		this.restoreDetail();

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
	public void updateDetail() {}
}
