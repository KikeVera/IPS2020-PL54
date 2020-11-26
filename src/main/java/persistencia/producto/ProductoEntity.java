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
	private double precioNormal;
	private double precioEmpresa;
	private int pasillo; 
	private int estanteria; 
	private int altura;
	private int stock;
	private int stockMin;
	private int stockReposicion;
	private String nombreCategoria;
	private int IVA; 

	public ProductoEntity() {}
	
	public ProductoEntity(int rowId, String rowNombre, String rowDescripcion, double rowPrecioNormal,
			double rowPrecioEmpresa,int rowPasillo,int rowEstanteria, int rowAltura, String rowNombreCategoria,
			int rowStock,int rowStockMin,int rowStockReposicion,int rowIVA) {
		this.id = rowId;
		this.nombre = rowNombre;
		this.descripcion = rowDescripcion;
		this.precioNormal = rowPrecioNormal; 
		this.precioEmpresa = rowPrecioEmpresa; 
		this.pasillo = rowPasillo;
		this.estanteria = rowEstanteria; 
		this.altura= rowAltura;
		this.nombreCategoria = rowNombreCategoria; 
		this.stock= rowStock;
		this.stockMin= rowStockMin;
		this.stockReposicion= rowStockReposicion;
		this.IVA = rowIVA; 
	}
	
	@Override
	public String toString() {
		String cadena = id + " - " + nombre + " - " + precioNormal + "-" + precioEmpresa; 
		return cadena; 
	}

	public int getId() { return this.id; }
	public String getNombre() { return this.nombre; }
	public String getDescripcion() { return this.descripcion; }
	public double getPrecioNormal() { return this.precioNormal; }
	public double getPrecioEmpresa() { return this.precioEmpresa; }
	public int getPasillo() {return this.pasillo;}
	public int getEstanteria() {return this.estanteria;}
	public int getAltura() { return altura;}
	public String getNombreCategoria() { return this.nombreCategoria; }
	public int getStock() {return stock;}
	public int getStockMin() {return stockMin;}
	public int getStockReposicion() {return stockReposicion;}
	public int getIVA() {return IVA;}

	public void setId(int value) { this.id=value; }
	public void setNombre(String value) { this.nombre=value; }
	public void setDescripcion(String value) { this.descripcion=value; }
	public void setPrecioNormal(double value) { this.precioNormal=value; }
	public void setPrecioEmpresa(double value) { this.precioEmpresa=value; }
	public void setPasillo(int pasillo) {this.pasillo=pasillo;}
	public void setEstanteria(int estanteria) {this.estanteria=estanteria;}
	public void setAltura(int altura) { this.altura = altura; }
	public void setNombreCategoria(String value) { this.nombreCategoria=value; }
	public void setStockReposicion(int stockReposicion) {this.stockReposicion = stockReposicion;}
	public void setStock(int stock) {this.stock = stock;}
	public void setStockMin(int stockMin) {this.stockMin = stockMin;}
	public void setIVA(int value) {this.IVA = value;}


}

