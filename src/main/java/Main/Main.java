package Main;



import ui.SwingMain;

public class Main {

	public static void main(String[] args) {
		try {
			SwingMain window = new SwingMain();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

