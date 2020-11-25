package persistencia.pertenece.subcategorias;

/**
 * Representa relación entre una subcategoría y sus productos. 
 * @author Moises
 *
 */
public class PerteneceSubcategoriaEntity {
	
	private int idProducto; 
	private String nombreSubcategoria; 
	
	public PerteneceSubcategoriaEntity() {}
	
	public PerteneceSubcategoriaEntity(int rowIdProducto, String rowNombreSubcategoria) {
		this.idProducto = rowIdProducto; 
		this.nombreSubcategoria = rowNombreSubcategoria; 
	}
	
	public int getIdProducto() {return this.idProducto;}
	public String getNombreSubcategoria() {return this.nombreSubcategoria;}
	
	public void setIdProducto(int value) {this.idProducto = value;}
	public void setNombreSubcategoria(String value) {this.nombreSubcategoria = value;}

}
