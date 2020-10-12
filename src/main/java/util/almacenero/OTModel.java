package util.almacenero;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import util.Util;
import util.database.Database;

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
	
}
