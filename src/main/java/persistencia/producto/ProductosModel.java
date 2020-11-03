package persistencia.producto;

import java.util.List;

import persistencia.database.Database;

/**
 * Clase encargada de acceder a la base de datos en el ambito de productos 
 * @author Moises
 *
 */
public class ProductosModel {

	public static final String SQL_LISTA_PRODUCTOS = "SELECT * from Producto";
	
	public static final String SQL_BY_CATEGORIA = "select * from producto where nombreCategoria = ?";
	
	public static final String SQL_FIND_BY_ID = "select * from producto where id = ?";

	
	private Database db=new Database();

	/**
	 * Devuelve una lista con los productos que se ofrecen en nuestro catalogo.
	 * @return Lista de productos disponibles 
	 */
	public List<ProductoEntity> getListaProductos() {
		String sql = SQL_LISTA_PRODUCTOS;
		return db.executeQueryPojo(ProductoEntity.class, sql); 
	}
	

	/**
	 * Nos devuelve los productos pertenecientes a una categoría.
	 * @param nombreCategoria Nombre de la categoria 
	 * @return
	 */
	public List<ProductoEntity> getListaProductosByCategoria(String nombreCategoria) {
		String sql = SQL_BY_CATEGORIA;
		return db.executeQueryPojo(ProductoEntity.class, sql,nombreCategoria); 
	}
	
	/**
	 * Devuelve un producto con cierto id 
	 * @param id Id a buscar 
	 * @return
	 */
	public List<ProductoEntity> findProductById(int id) {
		String sql = SQL_FIND_BY_ID;
		return db.executeQueryPojo(ProductoEntity.class, sql,id); 
	}
	
	
}
