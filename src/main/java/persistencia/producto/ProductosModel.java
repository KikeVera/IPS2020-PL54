package persistencia.producto;

import java.util.List;

import persistencia.database.Database;

/**
 * Clase encargada de acceder a la base de datos en el ambito de productos 
 * @author Moises
 *
 */
public class ProductosModel {

	public static final String SQL_LISTA_PRODUCTOS = "SELECT id,nombre,descripcion,precio,pasillo,estanteria,altura from Producto";
	
	private Database db=new Database();

	/**
	 * Devuelve una lista con los productos que se ofrecen en nuestro catalogo.
	 * @return Lista de productos disponibles 
	 */
	public List<ProductoEntity> getListaProductos() {
		String sql = SQL_LISTA_PRODUCTOS;
		return db.executeQueryPojo(ProductoEntity.class, sql); 
	}
	
	
}
