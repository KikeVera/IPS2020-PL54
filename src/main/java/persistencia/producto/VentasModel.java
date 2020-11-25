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
	
	public List<VentaEntity> getVentasByTipoPagoAndFecha(String fecha,String tipoPago) {
		String sql= "Select * from Venta where fecha = ? and tipoPago=?";
		return db.executeQueryPojo(VentaEntity.class, sql,fecha,tipoPago);
		
	}
	
	public List<VentaEntity> getVentasByTipoUsuarioAndFecha(String fecha,String tipoUsuario) {
		String sql= "Select * from Venta where fecha = ? and tipoUsuario=?";
		return db.executeQueryPojo(VentaEntity.class, sql,fecha,tipoUsuario);
		
	}
	
	public List<VentaEntity> getVentasByEmpresaAndFecha(String fecha,String empresa) {
		String sql= "Select * from Venta where fecha = ? and empresa=?";
		return db.executeQueryPojo(VentaEntity.class, sql,fecha,empresa);
		
	}
	
	
	
	
	
	
	


	
}
