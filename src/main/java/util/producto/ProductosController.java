package util.producto;

public class ProductosController {
	
	private ProductosModel model; 
	private ProductosView view; 
	
	
	public ProductosController(ProductosModel m, ProductosView v) {
		this.model = m; 
		this.view = v; 
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
	public void getListaCarreras() {}
	
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
