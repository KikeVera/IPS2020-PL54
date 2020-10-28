package persistencia.producto;

/**
 * Clase que representa la informacion de ubicacion de un producto.
 *
 */
public class ProductoInfo implements Comparable<ProductoInfo> {
	
	private String nombre; 
	private int pasillo; 
	private int estanteria; 
	
	public ProductoInfo() {}
	
	public ProductoInfo(String rowNombre,int rowPasillo,int rowEstanteria) {
		this.nombre = rowNombre;
		this.pasillo = rowPasillo;
		this.estanteria = rowEstanteria; 
		
	}
	
	public String getNombre() { return this.nombre; }
	public int getPasillo() {return this.pasillo;}
	public int getEstanteria() {return this.estanteria;}
	
	public void setNombre(String value) { this.nombre=value; }
	public void setPasillo(int pasillo) {this.pasillo=pasillo;}
	public void setEstanteria(int estanteria) {this.estanteria=estanteria;}

	@Override
	public int compareTo(ProductoInfo pe) {		
		if(pe.getPasillo()>this.pasillo){
            return -1;
        }else if(pe.getPasillo()==this.pasillo){
            return 0;
        }else{
            return 1;
        }
	}
	@Override
	public String toString() {
		return this.nombre+" - "+this.pasillo+" - "+this.estanteria;
	}

}
