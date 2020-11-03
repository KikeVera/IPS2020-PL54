package persistencia.pertenece;

/**
 * Representa relación entre una subcategoría y sus productos. 
 * @author Moises
 *
 */
public class PerteneceEntity {
	
	private int idProducto; 
	private String nombreSubcategoria; 
	
	public PerteneceEntity() {}
	
	public PerteneceEntity(int rowIdProducto, String rowNombreSubcategoria) {
		this.idProducto = rowIdProducto; 
		this.nombreSubcategoria = rowNombreSubcategoria; 
	}
	
	public int getIdProducto() {return this.idProducto;}
	public String getNombreSubcategoria() {return this.nombreSubcategoria;}
	
	public void setIdProducto(int value) {this.idProducto = value;}
	public void setNombreSubcategoria(String value) {this.nombreSubcategoria = value;}

}
