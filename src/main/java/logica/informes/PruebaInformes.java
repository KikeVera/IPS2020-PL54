package logica.informes;

import logica.informes.InformeImporteVentaController.InformeVenta;
import persistencia.paquete.PaqueteModel;
import persistencia.producto.VentasModel;
import ui.informe.InformePaquetesView;
import ui.informe.InformeImporteVentaView;
import ui.informe.InformePaquetesListosView;

public class PruebaInformes {

	public static void main(String[] args) {
		
		InformePaquetesController informe= new InformePaquetesController(new PaqueteModel(), new InformePaquetesView());
		InformeImporteVentaController informe2= new InformeImporteVentaController(new VentasModel(), new InformeImporteVentaView(),InformeVenta.tipoPago);
		InformeImporteVentaController informe3= new InformeImporteVentaController(new VentasModel(), new InformeImporteVentaView(),InformeVenta.tipoUsuario);
		InformeImporteVentaController informe4= new InformeImporteVentaController(new VentasModel(), new InformeImporteVentaView(),InformeVenta.empresa);
		InformePaquetesListosController informe5= new InformePaquetesListosController(new PaqueteModel(), new InformePaquetesListosView());

	}

}
