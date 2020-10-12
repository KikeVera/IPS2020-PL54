package util.producto;

/**
 * Clase que representa un producto y almacena su informacion.
 * @author Moises
 *
 */
public class ProductoPedido {
	
	private int id;
	private String nombre; 
	private String descripcion;
	private double precio;
	private int unidades;
	
	public ProductoPedido() {}
	
	public ProductoPedido(int rowId, String rowNombre, String rowDescripcion, double rowPrecio, int unidades) {
		this.id = rowId;
		this.nombre = rowNombre;
		this.descripcion = rowDescripcion;
		this.precio = rowPrecio; 
		this.setUnidades(unidades);
	}
	
	@Override
	public String toString() {
		String cadena = id + " - " + nombre + " - " + precio; 
		return cadena; 
	}

	public int getId() { return this.id; }
	public String getNombre() { return this.nombre; }
	public String getDescripcion() { return this.descripcion; }
	public double getPrecio() { return this.precio; }
	public int getUnidades() {return unidades;}
	
	public void setId(int value) { this.id=value; }
	public void setNombre(String value) { this.nombre=value; }
	public void setDescripcion(String value) { this.descripcion=value; }
	public void setPrecio(double value) { this.precio=value; }
	public void setUnidades(int unidades) {this.unidades = unidades;}
	

}
