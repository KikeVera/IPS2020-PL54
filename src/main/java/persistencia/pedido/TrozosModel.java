package persistencia.pedido;

import java.util.HashMap;
import java.util.List;

import persistencia.database.Database;
import util.Util;

public class TrozosModel {

	private Database db=new Database();
	
	public List<TrozoEntity> getTrozos() {
		String sql= "Select id,tamaño,productos from trozo";
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
		String sql="insert into trozo values (?,?,?,?,?)";
		db.executeUpdate(sql,id,tamaño,productos);
		
		
	}
}
