package persistencia.pertenece.categorias;

public class PerteneceCategoriaEntity {
	
	private int idProducto; 
	private String nombreCategoria; 
	
	public PerteneceCategoriaEntity() {}
	
	public PerteneceCategoriaEntity(int rowIdProducto, String rowNombreSubcategoria) {
		this.idProducto = rowIdProducto; 
		this.nombreCategoria = rowNombreSubcategoria; 
	}
	
	public int getIdProducto() {return this.idProducto;}
	public String getNombreCategoria() {return this.nombreCategoria;}
	
	public void setIdProducto(int value) {this.idProducto = value;}
	public void setNombreCategoria(String value) {this.nombreCategoria = value;}
}
