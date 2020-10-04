package negocio.almacen;

public class Incidencia {
	
	 String descripcion;
	 boolean solved= false;
	
	
	public Incidencia(String descripción) {
		this.descripcion=descripción;
		
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
