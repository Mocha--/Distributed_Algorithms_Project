import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) throws Exception {
		try {
			Client client = new Client("2");
			client.joinGame();
		} catch (Exception e) {
			System.out.println("Main Method Error!");
			System.out.println(e);
		}
	}
}
