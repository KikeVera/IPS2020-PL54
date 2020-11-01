package persistencia.usuario;

import java.util.UUID;

/**
 * Representa a un usuario. DE MOMENTO solo estara compuesto por un ID que se genera autmoaticamente (aun no+
 * almacenados en BD, simplemente lo simula)
 * @author Moises
 */
public class UsuarioEntity {
	

	private String idUsuario; 
	private String tipo;
	private String direccion; 
	
	public UsuarioEntity(String rowIdUsario, String rowTipo,String rowDireccion) { 
		this.idUsuario = rowIdUsario; 
		this.tipo = rowTipo; 
		this.direccion = rowDireccion; 
	}
	
	public UsuarioEntity() {
		this.idUsuario = UUID.randomUUID().toString().substring(0,5); 
		this.tipo = "Anónimo";
		this.direccion = ""; 
	}
	
	public String getIdUsuario() {return this.idUsuario;}
	public String getTipo() {return this.tipo;}
	public String getDireccion() {return this.direccion;}
	
	public void setIdUsuario(String value) { this.idUsuario=value; }
	public void setTipo(String value) { this.tipo=value; }
	public void setDireccion(String value) { this.direccion=value; }


}
