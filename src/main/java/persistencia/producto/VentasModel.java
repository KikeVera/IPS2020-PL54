package persistencia.producto;

import java.util.*;

import persistencia.database.Database;



 
public class VentasModel {
//PENDIENTE DE HACER BIEN

	private Database db=new Database();
	
	
	
	
	
	
	public List<VentaEntity> getVentas() {
		String sql= "Select fecha,tipoPago,tipoUsuario,empresa,importe from venta order by fecha";
		
	
		return db.executeQueryPojo(VentaEntity.class, sql);
	}
	
	/**
	 * Devuelve un pedido con un id en especifico
	 * @return Pedido con el id dado
	 */
	
	
	public void setVenta(String fecha,String tipoPago,String tipoUsuario,String empresa,double importe) {
		
		String sql="insert into Venta values (?,?,?,?,?)";
		db.executeUpdate(sql,fecha,tipoPago,tipoUsuario,empresa,importe);
		
		
	}
	
	
	
	
	
	
	
	


	
}
