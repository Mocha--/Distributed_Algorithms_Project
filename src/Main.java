import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) throws Exception {
		try {
			Client client = new Client("222");
			Window window = new Window(client);
			client.startPlay();
		} catch (Exception e) {
			System.out.println("Main Method Error!");
			System.out.println(e);
		}
	}
}
