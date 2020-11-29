package persistencia.paquete;

public class EstadoEntity {
	
	

	private int idot; 
	private String terminado; 

	private String posibleEmpaquetado; 
	private String maps;
	private int nProductos ;
	
	

	public EstadoEntity() {}
	
	public EstadoEntity(int idot,  String terminado, String posibleEmpaquetado,String maps,int nProductos ) {
		this.idot=idot;
		this.terminado=terminado;
		this.posibleEmpaquetado=posibleEmpaquetado;
		this.maps=maps;
		this.nProductos=nProductos;
	}
	
	public int getIdOT() {
		return idot;
	}

	public void setIdOT(int idot) {
		this.idot = idot;
	}

	public String getTerminado() {
		return terminado;
	}

	public void setTerminado(String terminado) {
		this.terminado = terminado;
	}

	public String getPosibleEmpaquetado() {
		return posibleEmpaquetado;
	}

	public void setPosibleEmpaquetado(String posibleEmpaquetado) {
		this.posibleEmpaquetado = posibleEmpaquetado;
	}
	
	public String getMaps() {
		return maps;
	}

	public void setMaps(String maps) {
		this.maps = maps;
	}

	public int getnProductos() {
		return nProductos;
	}

	public void setnProductos(int nProductos) {
		this.nProductos = nProductos;
	}
	

}
