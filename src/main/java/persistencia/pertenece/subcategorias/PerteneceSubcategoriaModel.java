package persistencia.pertenece.subcategorias;

import java.util.List;

import persistencia.database.Database;

public class PerteneceSubcategoriaModel {

	public static final String SQL_BY_SUBCATEGORIA = "select * from perteneceSubcategoria  where nombreSubcategoria = ?";
	
	public static final String SQL_BY_FINDBYIDANDNAME = "select * from perteneceSubcategoria  where idProducto = ? and nombreSubcategoria = ?";

	
	private Database db=new Database();
	
	/**
	 * Devuelve las relaciones de pertenencia para una determinada subcategoria 
	 * @param nombreSubcategoria Nombre de la subcategoria deseada 
	 * @return
	 */
	public List<PerteneceSubcategoriaEntity> getPerteneceBySubcategoria(String nombreSubcategoria) {
		String sql = SQL_BY_SUBCATEGORIA;
		return db.executeQueryPojo(PerteneceSubcategoriaEntity.class, sql,nombreSubcategoria); 
	}
	

	public List<PerteneceSubcategoriaEntity> findByIdAndName(int id, String nombre) {
		String sql = SQL_BY_FINDBYIDANDNAME;
		return db.executeQueryPojo(PerteneceSubcategoriaEntity.class, sql,id,nombre); 
	}
}
