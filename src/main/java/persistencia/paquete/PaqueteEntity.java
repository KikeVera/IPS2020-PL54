package persistencia.paquete;

public class PaqueteEntity {
	
	private String idPaquete; 
	private int idPedido; 

	private String fecha; 
	
	public PaqueteEntity() {}
	
	public PaqueteEntity(String rowIdPaquete, int rowIdPedido, String rowFecha) {
		this.idPaquete = rowIdPaquete; 
		this.idPedido = rowIdPedido; 
		this.fecha = rowFecha; 
	}
	
	public String getIdPaquete() { return this.idPaquete;}
	public int getIdPedido() {return this.idPedido;}
	
	public String getFecha() {return this.fecha;}
	
	public void setIdPaquete(String idPaquete) {this.idPaquete = idPaquete;}
	public void setIdPedido(int idPedido) {this.idPedido = idPedido;}
	
	public void setFecha(String fecha) {this.fecha = fecha;}
	

	
}
