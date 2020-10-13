package util.almacenero;

import java.util.List;

import util.database.Database;

public class AlmaceneroModel {
	private Database db=new Database();
	
	public int getId() {
		
		String sql= "Select idalmacenero from almacenero";
		List<Almacenero> lista=db.executeQueryPojo(Almacenero.class, sql);
		
		//De momento vamos a retornar el unico almacenero que hay guardado.
		return lista.get(0).getId();
	}
	
}
