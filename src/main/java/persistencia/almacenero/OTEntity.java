package persistencia.almacenero;

public class OTEntity {
	private int idOt;
	private int idAlmacenero;
	private int idPedido;
	private String estado;
	
	public OTEntity() {		}
	
	public OTEntity(int idot,String status, int idal,int idped) {	
		this.idOt=idot;
		this.idAlmacenero=idal;
		this.idPedido=idped;
		this.estado=status;
	}

	public int getIdOt() {
		return idOt;
	}

	public void setIdOt(int idOt) {
		this.idOt = idOt;
	}

	public int getIdAlmacenero() {
		return idAlmacenero;
	}

	public void setIdAlmacenero(int idAlmacenero) {
		this.idAlmacenero = idAlmacenero;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
