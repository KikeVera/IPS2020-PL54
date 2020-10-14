package persistencia.recogida;

import java.util.*;

import persistencia.database.Database;


 
public class IncidenciaModel {
//PENDIENTE DE HACER BIEN

	private Database db=new Database();
	
	
	
	
	
	public List<Object[]> getIncidenciasArray() {
		
		String sql= "Select idPedido,descripcion from Incidencia order by idPedido";
		return db.executeQueryArray(sql);
	}
	
	public List<IncidenciaEntity> getIncidencias() {
		String sql= "Select idPedido,descripcion from Incidencia order by idPedido";
		
	
		return db.executeQueryPojo(IncidenciaEntity.class, sql);
	}
	
	
	public List<IncidenciaEntity> getIncidenciaIDPedido(int id){
		String sql= "Select idPedido,descripcion from Incidencia where idPedido=?";
		
		return db.executeQueryPojo(IncidenciaEntity.class, sql, id);
		
	}
	
	public void setIncidencia(IncidenciaEntity incidencia) {
		int idPedido=incidencia.getIdPedido();
		String descripcion=incidencia.getDescripcion();
		
		String sql="insert into Incidencia values (?,?)";
		db.executeUpdate(sql,idPedido,descripcion);
		
		
	}
	

	public void setIncidencias(List<IncidenciaEntity> incidencias) {
		for(IncidenciaEntity incidencia:incidencias) {
		int idPedido=incidencia.getIdPedido();
		String descripcion=incidencia.getDescripcion();
		
		String sql="insert into Incidencia values (?,?)";
		db.executeUpdate(sql,idPedido,descripcion);
		}
		
	}
	
	
	
	
	


	
}
