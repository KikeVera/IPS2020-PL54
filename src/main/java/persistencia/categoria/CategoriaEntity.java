package persistencia.categoria;

/**
 * Representa a una categoria principal 
 * @author Moises
 *
 */
public class CategoriaEntity {

	private String nombreCategoria; 
	
	public CategoriaEntity() {}
	
	public CategoriaEntity(String rowNombreCategoria) {
		this.nombreCategoria = rowNombreCategoria; 
	}
	
	public String getNombreCategoria() {return this.nombreCategoria;}
	
	public void setNombreCategoria(String value) {this.nombreCategoria = value;}
}
