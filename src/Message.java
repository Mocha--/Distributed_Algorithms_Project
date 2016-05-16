
public class Message {
    public static String JOIN_GAME = "1";
    public static String START_GAME = "2";
    public static String NEXT_DIRECTION = "3";
    public static String GAME_OVER = "4";

    public static String[] joinGame(String id){
        return new String[]{id, Message.JOIN_GAME};
    }

    public static String[] startGame(String id){
        return new  String[]{id, Message.START_GAME};
    }

    public static String[] nextDirection(String id, int seq, String direction){
        return new String[]{id, Message.NEXT_DIRECTION, Integer.toString(seq), direction};
    }

    public static String[] gameOver(String id){
        return new String[]{id, Message.GAME_OVER};
    }
}
