package util.producto;

import java.util.List;

import util.database.Database;

public class ProductosModel {
	// la que accede a los datos de los productos

	public static final String SQL_LISTA_PRODUCTOS = "SELECT id,nombre,descripcion,precio from Producto";
	
	private Database db=new Database();

	public List<ProductoEntity> getListaProductos() {
		String sql = SQL_LISTA_PRODUCTOS;
		return db.executeQueryPojo(ProductoEntity.class, sql); 
	}
}
