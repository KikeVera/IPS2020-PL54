package persistencia.subcategoria;

import java.util.List;

import persistencia.database.Database;

public class SubcategoriaModel {
	
	private Database db=new Database();
	
	public static final String SQL_BY_CATEGORIA = "select * from subcategoria where nombreCategoria = ?";
	public static final String SQL_SUBCATEGORIANOPURA_BY_NOMBRE = "select * from subcategoria where nombreSubcategoria = ? and nombreSubcategoria in (select nombreSubcategoriaOrigen from contiene)";
	public static final String SQL_SUBCATEGORIA_BY_NOMBRE = "select * from subcategoria where nombreSubcategoria = ?";

	/**
	 * Nos devuelve las subcategorias que pertenecen a una categoría indicada
	 * @param nombreCategoria Categoria que se desea 
	 * @return
	 */
	public List<SubcategoriaEntity> getSubcategoriasByCategoria(String nombreCategoria) {
		String sql = SQL_BY_CATEGORIA;
		return db.executeQueryPojo(SubcategoriaEntity.class, sql,nombreCategoria); 
	}
	
	/**
	 * Nos devuelve una subcategoria, si esta no es pura (se entiende por no ser pura, a que contiene 
	 * otras subcategorias en vez de productos)
	 * @param nombreSubcategoria Nombre de la subcategoria a confirmar 
	 * @return
	 */
	public List<SubcategoriaEntity> getSubcategoriasNoPurasByNombre(String nombreSubcategoria) {
		String sql = SQL_SUBCATEGORIANOPURA_BY_NOMBRE;
		return db.executeQueryPojo(SubcategoriaEntity.class, sql,nombreSubcategoria); 
	}
	
	/**
	 * Buscamos una subcategoria por nombre 
	 * @param nombreSubcategoria Nombre de la subcategoria 
	 * @return
	 */
	public List<SubcategoriaEntity> getSubcategoriasByNombre(String nombreSubcategoria) {
		String sql = SQL_SUBCATEGORIA_BY_NOMBRE;
		return db.executeQueryPojo(SubcategoriaEntity.class, sql,nombreSubcategoria); 
	}
}
