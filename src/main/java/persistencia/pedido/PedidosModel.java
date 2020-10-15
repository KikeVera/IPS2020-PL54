package persistencia.pedido;

import java.util.*;

import persistencia.database.Database;
import util.Util;


 
public class PedidosModel {
//PENDIENTE DE HACER BIEN

	private Database db=new Database();
	
	
	
	
	
	public List<Object[]> getPedidosArray() {
		
		String sql= "Select id,fecha,tamaño,productos from pedido order by fecha";
		return db.executeQueryArray(sql);
	}
	
	public List<PedidoEntity> getPedidos() {
		String sql= "Select id,fecha,tamaño,productos from pedido order by fecha";
		
	
		return db.executeQueryPojo(PedidoEntity.class, sql);
	}
	
	
	public List<PedidoEntity> getPedidoFecha(Date fecha){
		String sql= "Select id,fecha,tamaño,productos from pedido where fecha= ?";
		String d=util.Util.dateToIsoString(fecha);
		return db.executeQueryPojo(PedidoEntity.class, sql, d);
		
	}
	
	public PedidoEntity getPedidoID(int id){
		String sql= "Select id,fecha,tamaño,productos from pedido where id= ?";
		
		return db.executePojo(PedidoEntity.class, sql, id);
		
	}
	
	public void setPedido(HashMap<Integer,Integer> pedido) {
		int id=getPedidos().size()+1;
		int tamaño=0;
		Integer[] numbers=pedido.values().toArray(new Integer[pedido.size()]);
		for(int i=0;i<pedido.size();i++) {
			tamaño=tamaño+numbers[i];
		}
		
		String fecha=Util.dateToIsoString(new Date());
		String productos=Util.productosToString(pedido);
		String sql="insert into Pedido values (?,?,?,?)";
		db.executeUpdate(sql,id,fecha,tamaño,productos);
		
		
	}
	
	
	
	
	


	
}
