package co.megadodo.cubingtimer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Scrambler {
	
	public static String scramble(Cube c) {
		return scramble(movesForCube(c), c.moves);
	}
	
	public static String scramble(Cube c, long seed) {
		return scramble(movesForCube(c), c.moves, seed);
	}
	
	public static String scramble(ArrayList<String> moves, int num) {
		return scramble(moves, num, (long)(Math.random()*Long.MAX_VALUE));
	}
	
	public static String scramble(ArrayList<String> moves, int num, long seed) {
		Random rand = new Random(seed);
		String str = "";
		String last = "";
		String cur = "";
		for(int i = 0; i < num; i++) {
			last = cur;
			cur = moves.get(rand.nextInt(moves.size()));
			while(cubeActualMove(last).equals(cubeActualMove(cur)) || cubeExtraMove(last).equals(cubeExtraMove(cur))) {
				cur = moves.get(rand.nextInt(moves.size()));
			}
			str+=cur+" ";
			if(i%10==2)str+="<br/>";
		}
		return "<html>"+str.trim()+"</html>";
	}

	public static ArrayList<String> movesForCube(Cube c) {
		if(!c.scramble) throw new RuntimeException("Unscramblable cube!!!!!!?!?!?!?!?!1111?");
		ArrayList<String> strs = new ArrayList<String>();
		switch(c) {
			case CUBE_2x2: return cubifyMoves("F","R","U");
			case CUBE_3x3: return cubifyMoves("F","R","L","U","D","B");
			case CUBE_4x4: return cubifyMoves("F","R","L","U","D","B","f","r","l","u","d","b");
			case CUBE_5x5: return cubifyMoves("F","R","L","U","D","B","f","r","l","u","d","b");
		}
		return strs;
	}
	
	public static String cubeActualMove(String str) {
		return str.replace("'", "").replace("2", "");
	}
	
	public static String cubeExtraMove(String str) {
		return str.replace(cubeActualMove(str), "");
	}
	
	public static ArrayList<String> cubifyMoves(String...strings) {
		ArrayList<String> strs = new ArrayList<String>();
		for(String s : strings) strs.add(s);
		return cubifyMoves(strs);
//		return strs;
	}
	
	public static ArrayList<String> cubifyMoves(ArrayList<String> strs) {
//		for(String s : strs) { strs.add(s + "'"); strs.add(s+"2"); }
		ArrayList<String> newStrs = new ArrayList<String>();
		for(int i = 0; i < strs.size(); i++) {
			newStrs.add(strs.get(i)+"'");
			newStrs.add(strs.get(i)+"2");
		}
		strs.addAll(newStrs);
		return strs;
	}
	
}
