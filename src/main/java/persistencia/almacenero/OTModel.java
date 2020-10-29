package persistencia.almacenero;

import java.util.List;

import persistencia.database.Database;

public class OTModel {
	
	private Database db=new Database();
					
	public List<Object[]> getOTsArray() {
		
		String sql= "Select idot,estado,idalmacenero,idpedido from ordentrabajo";
		return db.executeQueryArray(sql);
	}
	
	public List<OTEntity> getOTs() {
		String sql= "Select idot,estado,idalmacenero,idpedido from ordentrabajo";		
		
		return db.executeQueryPojo(OTEntity.class, sql);
	}
	
	public List<OTEntity> getOTByIdPedido(int idpedido) {
		String sql= "Select idot,estado,idalmacenero,idpedido from ordentrabajo where idpedido = ?";		
		
		return db.executeQueryPojo(OTEntity.class, sql,idpedido);
	}
	public List<OTEntity> getOTsByStatus(String status) {
		String sql= "Select idot,estado,idalmacenero,idpedido from ordentrabajo where estado = ?";		
		
		return db.executeQueryPojo(OTEntity.class, sql,status);
	}
	
	public void setOT(int idpedido,int idalmacenero) {
		int idot=getOTs().size()+1;		
		String estado="ASIGNADO";
		String sql="insert into ordentrabajo values (?,?,?,?)";
		db.executeUpdate(sql,idot,estado,idalmacenero,idpedido);		
	}
	
	
	public void updateStatus(int id,String status) {
			
		
		String sql="update ordentrabajo set estado = ? where idot = ?";
		db.executeUpdate(sql,status,id);		
	}
	
	
	
}
