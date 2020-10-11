package util.pedido;

import java.util.*;


import util.database.Database;


 
public class PedidosModel {
//PENDIENTE DE HACER BIEN

	private Database db=new Database();
	
	
	
	
	
	public List<Object[]> getPedidosArray() {
		
		String sql= "Select id,fecha,tamaño,productos from pedidos order by fecha";
		return db.executeQueryArray(sql);
	}
	
	public List<PedidoEntity> getPedidos() {
		String sql= "Select id,fecha,tamaño,productos from pedidos order by fecha";
		
	
		return db.executeQueryPojo(PedidoEntity.class, sql);
	}
	
	
	public List<PedidoEntity> getPedidoFecha(Date fecha){
		String sql= "Select id,fecha,tamaño,productos from pedidos where fecha= ?";
		String d=util.Util.dateToIsoString(fecha);
		return db.executeQueryPojo(PedidoEntity.class, sql, d);
		
	}
	
	
	


	
}
