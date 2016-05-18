public class Main {
	
	public static void main(String[] args) throws Exception {
		try {
			// args: id, totalNumber, port
			Client.TOTAL_PLAYER_NUM = Integer.parseInt(args[1]);
			Client.port = Integer.parseInt(args[2]);
			Client client = new Client(args[0]);
			client.joinGame();
		} catch (Exception e) {
				System.out.println("Please pass arguments from terminal!");
			System.out.println(e);
		}
	}
}
