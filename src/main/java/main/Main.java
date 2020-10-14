package main;



import ui.SwingMain;

public class Main {

	public static void main(String[] args) {
		try {
			SwingMain window = new SwingMain();
			window.setLocationRelativeTo(null);
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

