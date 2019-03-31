package co.megadodo.mancala;

public class MancalaPos {
	
	int player;
	int hole;
	public MancalaPos(int player, int hole) {
		this.player = player;
		this.hole = hole;
	}
	// counterclockwise
	public MancalaPos next(int p) {

		if(player == 1 && hole == 0) {
			return new MancalaPos(1,1);
		}
		if(player == 1 && hole == 1) {
			return new MancalaPos(1,2);
		}
		if(player == 1 && hole == 2) {
			return new MancalaPos(1,3);
		}
		if(player == 1 && hole == 3) {
			return new MancalaPos(1,4);
		}
		if(player == 1 && hole == 4) {
			return new MancalaPos(1,5);
		}
		if(player == 1 && hole == 5) {
			if(p == 1) return new MancalaPos(1,-1);
			else return new MancalaPos(0,5);
		}
		if(player == 1 && hole == -1) {
			return new MancalaPos(0,5);
		}
		if(player == 0 && hole == 5) {
			return new MancalaPos(0,4);
		}
		if(player == 0 && hole == 4) {
			return new MancalaPos(0,3);
		}
		if(player == 0 && hole == 3) {
			return new MancalaPos(0,2);
		}
		if(player == 0 && hole == 2) {
			return new MancalaPos(0,1);
		}
		if(player == 0 && hole == 1) {
			return new MancalaPos(0,0);
		}
		if(player == 0 && hole == 0) {
			if(p == 0) return new MancalaPos(0,-1);
			else return new MancalaPos(1,0);
		}
		if(player == 0 && hole == -1) {
			return new MancalaPos(1,0);
		}
		
		return new MancalaPos(123456789,987654321);
		
	}
//
//	hole = -1, this is player's home
//	public MancalaPos nextClockwise(int p) {
//		if(player == 0 && hole == 0) {
//			if(p == 0) {
//				return new MancalaPos(0,-1);
//			} else {
//				return new MancalaPos(1,0);
//			}
//		}
//		if(player == 0 && hole == 1) {
//			return new MancalaPos(0,0);
//		}
//		if(player == 0 && hole == 2) {
//			return new MancalaPos(0,1);
//		}
//		if(player == 0 && hole == 3) {
//			return new MancalaPos(0,2);
//		}
//		if(player == 0 && hole == 4) {
//			return new MancalaPos(0,3);
//		}
//		if(player == 0 && hole == 5) {
//			return new MancalaPos(0,4);
//		}
//		if(player == 1 && hole == -1) {
//			return new MancalaPos(0,5);
//		}
//		if(player == 1 && hole == 5) {
//			if(p == 0) {
//				return new MancalaPos(0,5);
//			} else {
//				return new MancalaPos(1,-1);
//			}
//		}
//		if(player == 1 && hole == 4) {
//			return new MancalaPos(1,5);
//		}
//		if(player == 1 && hole == 3) {
//			return new MancalaPos(1,4);
//		}
//		if(player == 1 && hole == 2) {
//			return new MancalaPos(1,3);
//		}
//		if(player == 1 && hole == 1) {
//			return new MancalaPos(1,2);
//		}
//		if(player == 1 && hole == 0) {
//			return new MancalaPos(1,1);
//		}
//		if(player == 0 && hole == -1) {
//			return new MancalaPos(1,0);
//		}
////		if(player == 0 && hole == -1) {
////			return new MancalaPos(1,0);
////		}
////		if()
//		return new MancalaPos(123456789,987654321);
//	}
	
	@Override
	public String toString() {
		return "MancalaPos[player="+player+",hole="+hole+"]";
	}

}
