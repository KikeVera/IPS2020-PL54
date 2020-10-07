package util.pedido;

import java.util.*;

import util.database.Database;


 
public class PedidosModel {
//PENDIENTE DE HACER BIEN

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
