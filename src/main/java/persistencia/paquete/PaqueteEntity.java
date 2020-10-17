package persistencia.paquete;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import persistencia.pedido.PedidoEntity;

public class PaqueteEntity {
	
	private String idPaquete; 
	private int idPedido; 

	private String fecha; 
	
	public PaqueteEntity() {}
	
	public PaqueteEntity(String rowIdPaquete, int rowIdPedido, String rowFecha) {
		this.idPaquete = rowIdPaquete; 
		this.idPedido = rowIdPedido; 
		
		this.fecha = rowFecha; 
	}
	
	public String getIdPaquete() { return this.idPaquete;}
	public int getIdPedido() {return this.idPedido;}
	
	public String getFecha() {return this.fecha;}
	
	public void setIdPaquete(String idPaquete) {this.idPaquete = idPaquete;}
	public void setIdPedido(int idPedido) {this.idPedido = idPedido;}
	
	public void setFecha(String fecha) {this.fecha = fecha;}
	

	public void generarDocumentacion() {
		File etiqueta = new File ("files","etiqueta" + this.idPaquete + ".txt");
		File albaran = new File ("files","albaran" + this.idPaquete + ".txt");
		
		generarEtiqueta(etiqueta);
		generarAlbaran(albaran);
		
	}
	
	private void generarEtiqueta(File etiquta) {
		FileWriter fw = null;
		BufferedWriter bw = null;  
				
        try
        {
        	fw = new FileWriter(etiquta); 
            bw = new BufferedWriter(fw);
            PaqueteModel pm = new PaqueteModel(); 
            PedidoEntity pedido = pm.getPedido(this.idPedido); 
            
            bw.write("----Etiqueta----\n");
            bw.write("Id paquete: " + this.idPaquete + "\n");
            bw.write("Usuario: " + pedido.getIdUsuario() + "\n");
            bw.write("Fecha de envio: " + this.fecha + "\n");

        } catch (IOException e) {
            System.out.println("Error al crear la etiqueta del paquete: " + this.idPaquete);
        } finally {
           try {
           if (bw != null)
              bw.close();
           if (fw != null)
               fw.close();
           } catch (IOException e) {
        	   System.out.println("Error al cerrar la etiqueta del paquete: " + this.idPaquete);
           }
        }
	}
	
	private void generarAlbaran(File albaran) {
		FileWriter fw = null;
		BufferedWriter bw = null;  
				
        try
        {
        	fw = new FileWriter(albaran); 
            bw = new BufferedWriter(fw);
            PaqueteModel pm = new PaqueteModel(); 
            PedidoEntity pedido = pm.getPedido(this.idPedido); 
            
            bw.write("----Albaran----\n");
            bw.write("Id paquete: " + this.idPaquete + "\n");
            bw.write("Id pedido: " + this.idPedido + "\n");
            bw.write("Usuario: " + pedido.getIdUsuario() + "\n");
            bw.write("Fecha de envio: " + this.fecha + "\n");
            bw.write("Tamaño: " + pedido.getTamaño() + "\n");
            bw.write("Lista productos: " + pedido.getProductos() + "\n");

        } catch (IOException e) {
            System.out.println("Error al crear el albaran del paquete: " + this.idPaquete);
        } finally {
           try {
           if (bw != null)
              bw.close();
           if (fw != null)
               fw.close();
           } catch (IOException e) {
        	   System.out.println("Error al cerrar el albaran del paquete: " + this.idPaquete);
           }
        }
	}
}
