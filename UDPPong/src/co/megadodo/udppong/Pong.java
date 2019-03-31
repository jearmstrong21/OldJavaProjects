package co.megadodo.udppong;

public class Pong {

	private Pong() {};
	
//	public static final String HOST = "192.168.88.218";
	public static final String HOST = "localhost";
	public static final int PORT = 1234;
	public static final int W = 500;
	public static final int H = 500;
	public static final int MIL_PER_FRAME = 10;
	public static final int PADDLE_X_L = 50;
	public static final int PADDLE_X_R = W-PADDLE_X_L;
	public static final int PADDLE_BOUNCE_MARG = 10;
	public static final int PADDLE_H = 40;
	public static final int PADDLE_W = 10;
	public static final int PADDLE_EASING = 1;
	public static final int BALL_RAD = 10;
	public static final int BALL_MARGIN = 0;
	public static final String ID_REQUEST = "ID";
	public static final String ACK_ID_REQUEST = "ACKID";
	public static final String STAT_REQUEST = "STAT";
	public static final String EXIT_REQUEST = "EXIT";
	public static final String ACK_STAT_REQUEST = "ACKSTAT";
	
	public static final String requestID() {
		return ID_REQUEST;
	}
	
	public static final String requestExit() {
		return EXIT_REQUEST;
	}
	
	public static final String encodeID(int id) {
		return ACK_ID_REQUEST + " " + id;
	}
	
	public static final int decodeID(String str) {
		if(str == null) return -1; // default "broken" value
		return Integer.parseInt(str.split(" ")[1]);
	}
	
	public static final String requestStatus(int paddleY, int id) {
		String str = STAT_REQUEST + " ";
		str+=id+" ";
		str+=paddleY;
		return str;
	}
	
	public static final boolean isReqStatus(String str) {
		return str.startsWith(STAT_REQUEST);
	}
	
	public static final boolean isReqExit(String str) {
		return str.equals(EXIT_REQUEST);
	}
	
	public static final boolean isReqID(String str) {
		return str.equals(ID_REQUEST);
	}
	
	//[0] = id from
	//[1] = paddle x
	public static final int[] decodeReqStatus(String req) {
		if(req == null) return new int[2];
		int[] decoded = new int[2];
		String[] strs = req.split(" ");
		decoded[0] = Integer.parseInt(strs[1]);
		decoded[1] = Integer.parseInt(strs[2]);
		return decoded;
	}
	
	//[0] = paddleY
	//[1] = ballX
	//[2] = ballY
	public static final int[] decodeStatus(String stat) {
		if(stat == null) return new int[3];
		int[] decoded = new int[3];
		String[] strs = stat.split(" ");
		decoded[0] = Integer.parseInt(strs[1]);
		decoded[1] = Integer.parseInt(strs[2]);
		decoded[2] = Integer.parseInt(strs[3]);
		return decoded;
	}
	
	public static final String encodeStatus(int paddleY, int ballX, int ballY) {
		String str = ACK_STAT_REQUEST+" ";
		str+=paddleY+" ";
		str+=ballX+" ";
		str+=ballY+" ";
		return str;
	}
	
}
