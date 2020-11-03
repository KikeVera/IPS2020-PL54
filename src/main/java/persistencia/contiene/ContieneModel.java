package persistencia.contiene;

import java.util.List;

import persistencia.database.Database;

public class ContieneModel {

	
	public static final String SQL_GET_CONTENIDAS_BY_NAME = "select *  from contiene where nombreSubcategoriaOrigen = ?";

	
	private Database db=new Database();
	
	/**
	 * Nos devuelve una lista de subcategorias contenidas por una subcategoria indicada 
	 * @param nombreSubcategoria Nombre de la subcategoria indicada 
	 * @return
	 */
	public List<ContieneEntity> getSubcategoriasContenidasByName(String nombreSubcategoria) {
		String sql = SQL_GET_CONTENIDAS_BY_NAME;
		return db.executeQueryPojo(ContieneEntity.class, sql,nombreSubcategoria); 
	}

}
