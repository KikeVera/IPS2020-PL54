package logica.informes;

import persistencia.paquete.PaqueteModel;
import ui.informe.InformePaquetesView;

public class PruebaInformes {

	public static void main(String[] args) {
		System.out.println(new PaqueteModel().getPaquetes().get(0).getIdPedido());
		InformePaquetesController informe= new InformePaquetesController(new PaqueteModel(), new InformePaquetesView());

	}

}
