package util;

import java.util.*;


 
public class PedidosModel {


	private Database db=new Database();
	
	
	
	public static final String SQL_LISTA_CARRERAS=
			"SELECT id,nombre,descripcion,precio from Producto";
	
	public List<Object[]> getPedidosArray() {
		
		String sql= SQL_LISTA_CARRERAS;
		return db.executeQueryArray(sql);
	}
	
	public List<PedidoEntity> getPedidos(Date fechaInscripcion) {
		String sql= SQL_LISTA_CARRERAS;
		
	
		return db.executeQueryPojo(PedidoEntity.class, sql);
	}
	
	
	


	
}
