package persistencia.paquete;

import java.util.List;

import persistencia.database.Database;

public class PaqueteModel {
	
	public static final String SQL_CREATE_PAQUETE = "insert into Paquete values (?,?,?,?,?,?,?)";
	public static final String SQL_DELETE_PAQUETE = "delete from Paquete where idPedido = ?";
	
	private Database db=new Database();


	
	/**
	 * Crea un paquete en la base de datos a partir del id de un pedido y el id del paquete.
	 * @param idPedido Id del pedido deseado 
	 * @param idPaquete Id que tendra el paquete generado
	 * @param fecha Fecha en la que se crea el paquete 
	 */
	public void createPaquete(int idPedido, String idPaquete, String fecha,int idAlmacenero,String direccion,int uds,String estado) {
		db.executeUpdate(SQL_CREATE_PAQUETE,idPaquete,idPedido,fecha,idAlmacenero,direccion,uds,estado);
	}
	
	
	public void borrarPaquete(String idPaquete) {
		db.executeUpdate(SQL_DELETE_PAQUETE,idPaquete);
	}
	
	public List<PaqueteEntity> getPaquetes() {
		
		String sql= "Select idPaquete,idPedido,fecha,idAlmacenero,direccion,uds,estado from Paquete order by fecha";
		
	
		return db.executeQueryPojo(PaqueteEntity.class, sql);
	}
	
	
	public List<PaqueteEntity> getPaquetesByFechaAndAlmacenero(String fecha,int idAlmacenero){
		String sql= "Select * from Paquete where fecha = ? and idAlmacenero=?";
		return db.executeQueryPojo(PaqueteEntity.class, sql,fecha,idAlmacenero);
		
	}
	
	public List<PaqueteEntity> getPaquetesByStatus(String estado){
		String sql= "Select * from Paquete where estado = ?";
		return db.executeQueryPojo(PaqueteEntity.class, sql,estado);
		
	}
	
	
	public List<PaqueteEntity> updateStatus(String estado,int idPedido){
		String sql="update Paquete set estado = ? where idPedido = ?";
		return db.executeQueryPojo(PaqueteEntity.class, sql,estado,idPedido);
		
	}
	
}
