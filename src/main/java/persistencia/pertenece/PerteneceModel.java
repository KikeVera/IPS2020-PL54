package persistencia.pertenece;

import java.util.List;

import persistencia.database.Database;

public class PerteneceModel {

	public static final String SQL_BY_SUBCATEGORIA = "select * from pertenece where nombreSubcategoria = ?";
	
	public static final String SQL_BY_FINDBYIDANDNAME = "select * from pertenece where idProducto = ? and nombreSubcategoria = ?";

	
	private Database db=new Database();
	
	/**
	 * Devuelve las relaciones de pertenencia para una determinada subcategoria 
	 * @param nombreSubcategoria Nombre de la subcategoria deseada 
	 * @return
	 */
	public List<PerteneceEntity> getPerteneceBySubcategoria(String nombreSubcategoria) {
		String sql = SQL_BY_SUBCATEGORIA;
		return db.executeQueryPojo(PerteneceEntity.class, sql,nombreSubcategoria); 
	}
	

	public List<PerteneceEntity> findByIdAndName(int id, String nombre) {
		String sql = SQL_BY_FINDBYIDANDNAME;
		return db.executeQueryPojo(PerteneceEntity.class, sql,id,nombre); 
	}
}
