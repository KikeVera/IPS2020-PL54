package persistencia.producto;

/**
 * Clase que representa un producto y almacena su informacion.
 * @author Moises
 *
 */
public class ProductoEntity{
	
	private int id;
	private String nombre; 
	private String descripcion;
	private double precio;
	private int pasillo; 
	private int estanteria; 
	private int altura; 

	public ProductoEntity() {}
	
	public ProductoEntity(int rowId, String rowNombre, String rowDescripcion, double rowPrecio,
			int rowPasillo,int rowEstanteria, int altura) {
		this.id = rowId;
		this.nombre = rowNombre;
		this.descripcion = rowDescripcion;
		this.precio = rowPrecio; 
		this.pasillo = rowPasillo;
		this.estanteria = rowEstanteria; 
		this.altura=altura;
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
	public int getPasillo() {return this.pasillo;}
	public int getEstanteria() {return this.estanteria;}
	public int getAltura() { return altura;}

	
	public void setId(int value) { this.id=value; }
	public void setNombre(String value) { this.nombre=value; }
	public void setDescripcion(String value) { this.descripcion=value; }
	public void setPrecio(double value) { this.precio=value; }
	public void setPasillo(int pasillo) {this.pasillo=pasillo;}
	public void setEstanteria(int estanteria) {this.estanteria=estanteria;}
	public void setAltura(int altura) { this.altura = altura; }



}
