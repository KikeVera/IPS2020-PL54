package persistencia.paquete;

import java.util.List;

import persistencia.database.Database;

public class PaqueteModel {
	
	public static final String SQL_CREATE_PAQUETE = "insert into Paquete values (?,?,?)";
	
	private Database db=new Database();


	
	/**
	 * Crea un paquete en la base de datos a partir del id de un pedido y el id del paquete.
	 * @param idPedido Id del pedido deseado 
	 * @param idPaquete Id que tendra el paquete generado
	 * @param fecha Fecha en la que se crea el paquete 
	 */
	public void createPaquete(int idPedido, String idPaquete, String fecha) {
		db.executeUpdate(SQL_CREATE_PAQUETE,idPaquete,idPedido,fecha);
	}
	
	public List<PaqueteEntity> getPaquetes() {
		
		String sql= "Select idPaquete,idPedido,fecha from Paquete";
		
	
		return db.executeQueryPojo(PaqueteEntity.class, sql);
	}
	
	
}
