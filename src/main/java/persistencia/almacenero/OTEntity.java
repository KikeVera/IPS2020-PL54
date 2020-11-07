package persistencia.almacenero;

public class OTEntity {
	private int idOt;
	private int idAlmacenero;
	private String  idPedido;
	private String estado;
	private int capacidad;
	
	public OTEntity() {		}
	
	public OTEntity(int idot,String status, int idal,String idped,int cap) {	
		this.idOt=idot;
		this.idAlmacenero=idal;
		this.idPedido=idped;
		this.estado=status;
		this.capacidad=cap;
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

	public String  getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(String  idPedido) {
		this.idPedido = idPedido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

}
