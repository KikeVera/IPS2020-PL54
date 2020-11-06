package persistencia.paquete;

import java.util.List;

import persistencia.database.Database;

public class EstadoModel {
	
	public static final String SQL_CREATE_ESTADO = "insert into Estado values (?,?,?)";
	
	
	private Database db=new Database();


	
	/**
	 * Crea un paquete en la base de datos a partir del id de un pedido y el id del paquete.
	 * @param idPedido Id del pedido deseado 
	 * @param idPaquete Id que tendra el paquete generado
	 * @param fecha Fecha en la que se crea el paquete 
	 */
	public void createEstado(int idot, String terminado, String posibleEmpaquetado) {
		db.executeUpdate(SQL_CREATE_ESTADO,idot,terminado,posibleEmpaquetado);
	}
	
	
	
	public List<EstadoEntity> getEstados() {
		
		String sql= "Select idot,terminado,posibleEmpaquetado from Estado";
		
	
		return db.executeQueryPojo(EstadoEntity.class, sql);
	}
	
	public List<EstadoEntity> getEstadoFromOT(int idot) {
		String sql= "Select idot,terminado,posibleEmpaquetado from Estado where idot = ?";
		return db.executeQueryPojo(EstadoEntity.class, sql, idot);
		
	}
	
	public void updateEstado(int idot, String terminado, String posibleEmpaquetado) {
		String sql="UPDATE Estado SET terminado = ?, posibleEmpaquetado=? WHERE idot= ? ";
		db.executeUpdate(sql,terminado,posibleEmpaquetado,idot);
		
	}
	
	
}
