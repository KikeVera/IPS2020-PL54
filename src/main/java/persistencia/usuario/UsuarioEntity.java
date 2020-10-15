package persistencia.usuario;

import java.util.UUID;

/**
 * Representa a un usuario. DE MOMENTO solo estara compuesto por un ID que se genera autmoaticamente (aun no+
 * almacenados en BD, simplemente lo simula)
 * @author Moises
 */
public class UsuarioEntity {
	
	private String id;
	
	public UsuarioEntity() {
		this.id = UUID.randomUUID().toString().substring(0, 5); //Genera el id automaticamente
	}
	
	public String getId() {return this.id;}
}
