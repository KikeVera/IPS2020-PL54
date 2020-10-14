package persistencia.almacenero;

public class OTEntity {
	private int idot;
	private int idalmacenero;
	private int idpedido;
	private String estado;
	
	public OTEntity() {		}
	
	public OTEntity(int id_ot,String status, int id_al,int id_ped) {	
		this.setId_ot(id_ot);
		this.idalmacenero=id_al;
		this.idpedido=id_ped;
		this.estado=status;
	}

	public int getId_almacenero() {
		return idalmacenero;
	}

	public void setId_almacenero(int id_almacenero) {
		this.idalmacenero = id_almacenero;
	}

	public int getId_pedido() {
		return idpedido;
	}

	public void setId_pedido(int id_pedido) {
		this.idpedido = id_pedido;
	}

	public String getStatus() {
		return estado;
	}

	public void setStatus(String status) {
		this.estado = status;
	}

	public int getId_ot() {
		return idot;
	}

	public void setId_ot(int id_ot) {
		this.idot = id_ot;
	}
	

}
