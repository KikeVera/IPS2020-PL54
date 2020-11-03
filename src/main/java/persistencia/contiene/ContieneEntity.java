package persistencia.contiene;

/**
 * Representa relación entre subcategorias. Nos informa de si una categoría esta contenida en otra.
 * @author Moises
 *
 */
public class ContieneEntity {

	private String nombreSubcategoriaOrigen; 
	private String nombreSubcategoriaContenida; 
	
	public ContieneEntity() {}
	
	public ContieneEntity(String rowNombreSubcategoriaOrigen, String rowNombreSubcategoriaContenida) {
		this.nombreSubcategoriaOrigen = rowNombreSubcategoriaOrigen; 
		this.nombreSubcategoriaContenida = rowNombreSubcategoriaContenida; 
	}
	
	public String getNombreSubcategoriaOrigen() {return this.nombreSubcategoriaOrigen;}
	public String getNombreSubcategoriaContenida() {return this.nombreSubcategoriaContenida;}
	
	public void setNombreSubcategoriaOrigen(String value) {this.nombreSubcategoriaOrigen = value;}
	public void setNombreSubcategoriaContenida(String value) {this.nombreSubcategoriaContenida = value;}
}
