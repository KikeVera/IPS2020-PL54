package persistencia.almacenero;

import java.util.List;

import persistencia.database.Database;

public class AlmaceneroModel {
	private Database db=new Database();
	
	public int getId() {
		
		String sql= "Select idalmacenero from almacenero";
		List<AlmaceneroEntity> lista=db.executeQueryPojo(AlmaceneroEntity.class, sql);
		
		//De momento vamos a retornar el unico almacenero que hay guardado.
		return lista.get(0).getId();
	}
	
}
