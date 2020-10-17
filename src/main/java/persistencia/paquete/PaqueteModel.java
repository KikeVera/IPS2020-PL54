package persistencia.paquete;

import java.util.Date;
import java.util.UUID;

import persistencia.database.Database;
import persistencia.pedido.PedidoEntity;

public class PaqueteModel {
	public static final String SQL_FIND_PEDIDO = "select id,fecha,tamaño,productos from Pedido where id = ?";
	public static final String SQL_CREATE_PAQUETE = "insert into Paquete values (?,?,?,?)";
	
	private Database db=new Database();

	/**
	 * Devuelve un pedido en especifico con un id en especifico
	 * @return Un pedido con un id especifico  
	 */
	public PedidoEntity getPedido(int idPedido) {
		return db.executeQueryPojo(PedidoEntity.class, SQL_FIND_PEDIDO,idPedido).get(0); 
	}
	
	/**
	 * Crea un paquete en la base de datos a partir del id de un pedido y el id de un usuario.
	 * @param idPedido Id del pedido deseado 
	 * @param idUsuario Id del usuario deseado (A revisar)
	 */
	public void createPaquete(int idPedido, String idUsuario) {
		Date date = new Date(); 
		db.executeUpdate(SQL_CREATE_PAQUETE, UUID.randomUUID().toString().substring(0, 5),idPedido,idUsuario,date.getTime());
	}
	
}
