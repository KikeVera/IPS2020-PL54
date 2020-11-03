package persistencia.categoria;

import java.util.List;

import persistencia.database.Database;

public class CategoriaModel {
	
	private Database db=new Database();
	
	public static final String SQL_LISTA_CATEGORIAS = "select * from categoria";
	
	public static final String SQL_WITH_SUBCATEGORIAS = "select * from categoria where nombreCategoria in (select nombreCategoria from subcategoria) and nombreCategoria=?";



	/**
	 * Nos devuelve una categoria en caso de que contenga subcategoris(es decir, nos devuelve la categoria si no 
	 * es pura)
	 * @param nombre Nombre de la categoria 
	 * @return
	 */
	public List<CategoriaEntity> getCategoriasNoPurasByNombre(String nombre) {
		String sql = SQL_WITH_SUBCATEGORIAS;
		return db.executeQueryPojo(CategoriaEntity.class, sql,nombre); 
	}
	
	/**
	 * Nos devuelve una lista con todas las categorias disponibles
	 * @return
	 */
	public List<CategoriaEntity> getCategorias() {
		String sql = SQL_LISTA_CATEGORIAS;
		return db.executeQueryPojo(CategoriaEntity.class, sql); 
	}
}
