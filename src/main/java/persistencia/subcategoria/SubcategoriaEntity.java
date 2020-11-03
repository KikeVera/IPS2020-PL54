package persistencia.subcategoria;

/**
 * Representa una subcategoria.
 * @author Moises
 *
 */
public class SubcategoriaEntity {
	
	private String nombreSubcategoria; 
	
	public SubcategoriaEntity() {}
	
	public SubcategoriaEntity(String rowNombreSubcategoria) {
		this.nombreSubcategoria = rowNombreSubcategoria; 
	}
	
	public String getNombreSubcategoria() {return this.nombreSubcategoria;}
	
	public void setNombreSubcategoria(String value) {this.nombreSubcategoria = value;}

}
