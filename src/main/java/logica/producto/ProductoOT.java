package logica.producto;

/**
 * Clase que representa un producto y almacena su informacion.
 * @author Moises
 *
 */
public class ProductoOT {
	
	private int id;
	private String nombre; 
	
	private int unidades;
	
	public ProductoOT() {}
	
	public ProductoOT(int rowId, String rowNombre, int unidades) {
		this.id = rowId;
		this.nombre = rowNombre;
		
		this.setUnidades(unidades);
	}
	
	@Override
	public String toString() {
		String cadena = id + " - " + nombre; 
		return cadena; 
	}

	public int getId() { return this.id; }
	public String getNombre() { return this.nombre; }
	
	public int getUnidades() {return unidades;}
	
	public void setId(int value) { this.id=value; }
	public void setNombre(String value) { this.nombre=value; }
	
	public void setUnidades(int unidades) {this.unidades = unidades;}
	

}
