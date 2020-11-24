package persistencia.almacenero;

import java.util.Date;
import java.util.List;

import persistencia.database.Database;
import util.Util;

public class OTModel {
	
	private Database db=new Database();
					
	public List<Object[]> getOTsArray() {
		
		String sql= "Select idot,estado,idalmacenero,idpedido,capacidad from ordentrabajo";
		return db.executeQueryArray(sql);
	}
	
	public List<OTEntity> getOTs() {
		String sql= "Select idot,estado,idalmacenero,idpedido,capacidad from ordentrabajo";		
		
		return db.executeQueryPojo(OTEntity.class, sql);
	}
	
	public List<OTEntity> getOTByIdPedido(String idpedido) {
		String sql= "Select idot,estado,idalmacenero,idpedido,capacidad from ordentrabajo where idpedido = ?";			
		return db.executeQueryPojo(OTEntity.class, sql,idpedido);
	}
	
	public List<OTEntity> getOTsByStatus(String status) {
		String sql= "Select idot,estado,idalmacenero,idpedido,capacidad from ordentrabajo where estado = ?";		
		
		return db.executeQueryPojo(OTEntity.class, sql,status);
	}
	
	public void setOT(List<String> idpedidos_arg,int capacidad) {
		int idot=getOTs().size()+1;		
		int idalmacenero=1; //De momento le vamos a pasar el id de almacenero 1 ya que solo hay 1 almacenero
		String estado="ASIGNADO"; 
		String idpedidos=Util.pedidosToString(idpedidos_arg);
		String sql="insert into ordentrabajo values (?,?,?,?,?)";
		db.executeUpdate(sql,idot,estado,idalmacenero,idpedidos,capacidad);		
	}
	/**
	 * Metodo para saber el numero de ots asignadas por almacenero y dia 
	 * @param id_almacenero
	 * @param fecha
	 * @return
	 */
	public int getNumOT(int id_almacenero, Date fecha) {
		String fecha_procesada=Util.dateToIsoString(fecha);
		String sql="select count(*) from ordentrabajo where idalmacenero = ? and fecha = ?";
		return db.executePojo(Integer.class, sql, id_almacenero,fecha_procesada);
	}
	
	/**
	 * Metodo para asignar un trozo a una orden de trabajo
	 * @param idpedidos_arg
	 * @param capacidad
	 */
	public void setTrozoOT(String idpedido_arg,int capacidad) {
		int idot=getOTs().size()+1;		
		int idalmacenero=1; //De momento le vamos a pasar el id de almacenero 1 ya que solo hay 1 almacenero
		String estado="ASIGNADO"; 
		String sql="insert into ordentrabajo values (?,?,?,?,?)";
		db.executeUpdate(sql,idot,estado,idalmacenero,idpedido_arg,capacidad);		
	}
	
	
	public void updateStatus(int id,String status) {		
		String sql="update ordentrabajo set estado = ? where idot = ?";
		db.executeUpdate(sql,status,id);		
	}
	
	public void updateOT(int id,int capacidad,List<String> idpedidos_arg) {	
		String sql="update ordentrabajo set capacidad = ?, idpedido = ? where idot = ?";
		String idpedidos=Util.pedidosToString(idpedidos_arg);
		db.executeUpdate(sql,capacidad,idpedidos,id);		
	}
	
	
	
}
