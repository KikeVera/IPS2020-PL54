package logica.informes;

import logica.Controller;
import ui.informe.MenuInformes;


public class InformesController implements Controller {
	private MenuInformes view;
	
	
	
	public InformesController(MenuInformes view) {
		this.view=view;
		
		this.initView();
	}
	
	public void initView() {	
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true);
		
	}

	@Override
	public void initController() {
		// TODO Auto-generated method stub
		
	}
	
}

