package persistencia.almacenero;

public class OTEntity {
	private int id_ot;
	private int id_almacenero;
	private int id_pedido;
	private String estado;
	
	public OTEntity() {		}
	
	public OTEntity(int id_ot, int id_al,int id_ped,String status) {	
		this.setId_ot(id_ot);
		this.id_almacenero=id_al;
		this.id_pedido=id_ped;
		this.estado=status;
	}

	public int getId_almacenero() {
		return id_almacenero;
	}

	public void setId_almacenero(int id_almacenero) {
		this.id_almacenero = id_almacenero;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public String getStatus() {
		return estado;
	}

	public void setStatus(String status) {
		this.estado = status;
	}

	public int getId_ot() {
		return id_ot;
	}

	public void setId_ot(int id_ot) {
		this.id_ot = id_ot;
	}
	

}
