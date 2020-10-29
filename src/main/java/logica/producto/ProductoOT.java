package logica.producto;


/**
 * Clase que representa un producto y almacena su informacion.
 * @author Moises
 *
 */
public class ProductoOT  {
	
	private int id;
	private String nombre; 
	private int pasillo; 
	private int estanteria; 
	private int altura; 

	
	private int unidades;
	
	public ProductoOT() {}
	
	public ProductoOT(int rowId, String rowNombre, int unidades,int rowPasillo,int rowEstanteria, int altura) {
		this.id = rowId;
		this.nombre = rowNombre;
		this.pasillo=rowPasillo;
		this.estanteria = rowEstanteria; 
		this.altura = altura; 

		this.setUnidades(unidades);
	}
	
	@Override
	public String toString() {
		String cadena = id + " - " + nombre+" - "+this.pasillo+" - "+this.estanteria; 
		return cadena; 
	}

	public int getId() { return this.id; }
	public String getNombre() { return this.nombre; }
	public int getPasillo() {return this.pasillo;}
	public int getEstanteria() {return this.estanteria;}
	public int getUnidades() {return unidades;}
	public int getAltura() {return altura;}
	
	public void setId(int value) { this.id=value; }
	public void setNombre(String value) { this.nombre=value; }
	public void setPasillo(int pasillo) {this.pasillo=pasillo;}
	public void setEstanteria(int estanteria) {this.estanteria=estanteria;}
	public void setUnidades(int unidades) {this.unidades = unidades;}
	public void setAltura(int altura) {this.altura = altura;}
	
}
