package persistencia.pedido;

import java.util.*;

import persistencia.database.Database;
import util.Util;


 
public class PedidosModel {
//PENDIENTE DE HACER BIEN

	private Database db=new Database();
	
	
	
	
	
	public List<Object[]> getPedidosArray() {
		
		String sql= "Select id,fecha,tama�o,idUsuario,productos from pedido order by fecha";
		return db.executeQueryArray(sql);
	}
	
	public List<PedidoEntity> getPedidos() {
		String sql= "Select id,fecha,tama�o,idUsuario,productos from pedido order by fecha";
		
	
		return db.executeQueryPojo(PedidoEntity.class, sql);
	}
	
	/**
	 * Devuelve un pedido con un id en especifico
	 * @return Pedido con el id dado
	 */
	public PedidoEntity getPedido(int id) {
		String sql = "Select id,fecha,tama�o,idUsuario,productos from pedido where id=?";
		PedidoEntity pedido = db.executePojo(PedidoEntity.class, sql, id); 
		return pedido; 
	}
	
	
	public List<PedidoEntity> getPedidoFecha(Date fecha){
		String sql= "Select id,fecha,tama�o,idUsuario,productos from pedido where fecha= ?";
		String d=util.Util.dateToIsoString(fecha);
		return db.executeQueryPojo(PedidoEntity.class, sql, d);
		
	}
	
	public PedidoEntity getPedidoID(int id){
		String sql= "Select id,fecha,tama�o,idUsuario,productos from pedido where id= ?";
		
		return db.executePojo(PedidoEntity.class, sql, id);
		
	}
	
	public void setPedido(HashMap<Integer,Integer> pedido, String idUsuario) {
		int id=getPedidos().size()+1;
		int tama�o=0;
		Integer[] numbers=pedido.values().toArray(new Integer[pedido.size()]);
		for(int i=0;i<pedido.size();i++) {
			tama�o=tama�o+numbers[i];
		}
		
		String fecha=Util.dateToIsoString(new Date());
		String productos=Util.productosToString(pedido);
		String sql="insert into Pedido values (?,?,?,?,?)";
		db.executeUpdate(sql,id,fecha,tama�o,idUsuario,productos);
		
		
	}
	
	
	
	
	


	
}
