package util.recogida;

public class IncidenciaEntity {
	
	private  int idPedido;
	 private String descripcion;
	
	
	 public IncidenciaEntity() {}
	public IncidenciaEntity(int idPedido,String descripción) {
		this.descripcion=descripción;
		this.idPedido=idPedido;
		
	}



	public int getIdPedido() {
		return idPedido;
	}



	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	

}
