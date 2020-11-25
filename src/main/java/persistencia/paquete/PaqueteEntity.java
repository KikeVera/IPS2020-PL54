package persistencia.paquete;

public class PaqueteEntity {
	
	private String idPaquete; 
	private int idPedido; 

	private String fecha; 
	private int idAlmacenero;
	

	private String direccion;
	private int uds; 
	

	private String estado;
	
	public PaqueteEntity() {}
	
	public PaqueteEntity(String rowIdPaquete, int rowIdPedido, String rowFecha,int idAlmacenero,String direccion,int uds, String estado) {
		this.idPaquete = rowIdPaquete; 
		this.idPedido = rowIdPedido; 
		this.fecha = rowFecha; 
		this.idAlmacenero=idAlmacenero;
		this.direccion=direccion;
		this.uds=uds;
		this.estado=estado;
	}
	
	public String getIdPaquete() { return this.idPaquete;}
	public int getIdPedido() {return this.idPedido;}
	
	public String getFecha() {return this.fecha;}
	
	public void setIdPaquete(String idPaquete) {this.idPaquete = idPaquete;}
	public void setIdPedido(int idPedido) {this.idPedido = idPedido;}
	
	public void setFecha(String fecha) {this.fecha = fecha;}
	public int getIdAlmacenero() {
		return idAlmacenero;
	}

	public void setIdAlmacenero(int idAlmacenero) {
		this.idAlmacenero = idAlmacenero;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public int getUds() {
		return uds;
	}

	public void setUds(int uds) {
		this.uds = uds;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}
