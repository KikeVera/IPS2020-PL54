package util.pedido;

/**
 * Datos del modelo de dominio de cada una de las carreras
 * IMPORTANTE: Cuando se usan los componentes de Apache Commons DbUtils debe
 * mantenerse de forma estricta el convenio de capitalización de Java:
 *  - Capitalizar todas las palabras que forman un identificador 
 *    excepto la primera letra de nombres de métodos y variables.
 *  - No utilizar subrayados
 * Seguir tambien estos mismos criterios en los nombres de tablas y campos de la BD
 */

//PENDIENTE DE HACER BIEN
public class PedidoEntity {
	private String id;
	private String nombre; //las fechas son string (vienen de sqlite)
	private String descripcion;
	private Double precio;
	
	public  PedidoEntity() {}
	public  PedidoEntity( String id,String nombre,String descripcion,Double precio) {
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.precio=precio;
		
	}
		

	public String getId() { return this.id; }
	public String getNombre() { return this.nombre; }
	public String getDescripcion() { return this.descripcion; }
	public Double getPrecio() { return this.precio; }
	
	
	public void setId(String value) { this.id=value; }
	public void setNombre(String value) { this.nombre=value; }
	public void setDescripcion(String value) { this.descripcion=value; }
	public void setFecha(Double value) { this.precio=value; }
	
}
