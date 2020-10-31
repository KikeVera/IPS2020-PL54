package persistencia.usuario;

import java.util.UUID;

/**
 * Representa a un usuario. DE MOMENTO solo estara compuesto por un ID que se genera autmoaticamente (aun no+
 * almacenados en BD, simplemente lo simula)
 * @author Moises
 */
public class UsuarioEntity {
	

	private String codigo; 
	private String tipo;
	private String direccion; 
	
	public UsuarioEntity(String rowCodigo, String rowTipo,String rowDireccion) { 
		this.codigo = rowCodigo; 
		this.tipo = rowTipo; 
		this.direccion = rowDireccion; 
	}
	
	public UsuarioEntity() {
		this.codigo = UUID.randomUUID().toString().substring(0,5); 
		this.tipo = "anonimo"; 
	}
	
	public String getCodigo() {return this.codigo;}
	public String getTipo() {return this.tipo;}
	public String getDireccion() {return this.direccion;}
	
	public void setCodigo(String value) { this.codigo=value; }
	public void setTipo(String value) { this.tipo=value; }
	public void setDireccn(String value) { this.direccion=value; }


}
