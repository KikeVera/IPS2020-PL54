package negocio.almacen;

public class Incidencia {
	
	 String descripcion;
	 boolean solved= false;
	
	
	public Incidencia(String descripci�n) {
		this.descripcion=descripci�n;
		
	}
	
	public String getDescripcion() {
		return descripcion;
		
	}
	
	public void solved(boolean solved) {
		this.solved=solved;
	}
	
	public boolean isSolved() {
		return solved;
	}

}
