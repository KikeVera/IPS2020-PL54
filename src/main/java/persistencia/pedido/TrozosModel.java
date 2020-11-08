package persistencia.pedido;

import java.util.HashMap;
import java.util.List;

import persistencia.database.Database;
import util.Util;

public class TrozosModel {

	private Database db=new Database();
	
	public List<TrozoEntity> getTrozos() {
		String sql= "Select * from Trozo";
		return db.executeQueryPojo(TrozoEntity.class, sql);
	}
	
	/**
	 * Metodo para guardar los trozos de los pedidos
	 * @param pedido
	 * @param id se calcula con el id del pedido original mas el numero del trozo. EJ 2-1
	 */
	public void setFragmentoPedido(HashMap<Integer,Integer> pedido,String id) {
		int tamaño=0;
		Integer[] numbers=pedido.values().toArray(new Integer[pedido.size()]);
		for(int i=0;i<pedido.size();i++) {
			tamaño=tamaño+numbers[i];
		}
		
		String productos=Util.productosToString(pedido);
		String sql="insert into Trozo values (?,?,?)";
		db.executeUpdate(sql,id,tamaño,productos);
		
		
	}
	
	public TrozoEntity getTrozo(String id) {
		String sql = "Select * from Trozo where id=?";
		TrozoEntity trozo = db.executePojo(TrozoEntity.class, sql, id); 
		return trozo; 
	}
	
	public void updateTrozo(HashMap<Integer,Integer> update,String id) {
		
		String productos=Util.productosToString(update);
		String sql="UPDATE Trozo SET productos = ? WHERE id= ? ";
		db.executeUpdate(sql,productos,id);
	}
}
