package util.producto;

public class ProductoEntity {
	//La que recoge informacion (con todos los atributos y demas)
	
	private String id;
	private String nombre; 
	private String descripcion;
	private Double precio;
	
	public String getId() { return this.id; }
	public String getNombre() { return this.nombre; }
	public String getDescripcion() { return this.descripcion; }
	public Double getPrecio() { return this.precio; }
	
	
	public void setId(String value) { this.id=value; }
	public void setNombre(String value) { this.nombre=value; }
	public void setDescripcion(String value) { this.descripcion=value; }
	public void setFecha(Double value) { this.precio=value; }
}
