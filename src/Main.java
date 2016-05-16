import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) throws Exception {
		try {
//			Window window = new Window();
//			window.setTitle("Title");
//			window.setSize(800, 800);
//			window.setVisible(true);
//			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Client client = new Client("2");
			client.startPlay();
		} catch (Exception e) {
			System.out.println("Main Method Error!");
			System.out.println(e);
		}
	}
}
