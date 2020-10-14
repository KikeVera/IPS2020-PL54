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
		int id=getOTs().size()+1;		
		String estado="asignado";
		String sql="insert into ordentrabajo values (?,?,?,?)";
		db.executeUpdate(sql,id,estado,idalmacenero,idpedido);
		
		
	}
	
}
