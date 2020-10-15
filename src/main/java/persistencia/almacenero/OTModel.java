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
	public void setOT(int idpedido,int idalmacenero) {
		int idot=getOTs().size()+1;		
		String estado="asignado";
		String sql="insert into ordentrabajo values (?,?,?,?)";
		db.executeUpdate(sql,idot,estado,idalmacenero,idpedido);		
	}
	
	public void updateStatus(int id,String status) {
			
		
		String sql="update ordentrabajo set estado = ? where idot = ?";
		db.executeUpdate(sql,status,id);		
	}
	
	
	
}
