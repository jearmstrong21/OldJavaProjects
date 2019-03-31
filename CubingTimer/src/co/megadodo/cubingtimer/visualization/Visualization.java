package co.megadodo.cubingtimer.visualization;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import co.megadodo.cubingtimer.Cube;
import co.megadodo.cubingtimer.CubingTimer;
import co.megadodo.cubingtimer.Solve;
import co.megadodo.cubingtimer.SolveComparator;
import co.megadodo.cubingtimer.SortField;
import processing.core.PApplet;
import processing.core.PVector;

public class Visualization extends PApplet {

	ArrayList<Solve> solves;
	
	public static void main(String[] args) {
		System.out.println("Visualization!!");
		PApplet.main("co.megadodo.cubingtimer.visualization.Visualization");
	}
	
	public void settings() {
		size(770,770);
	}
	
	PVector graphStart;
	PVector graphEnd;
	float graphW;
	float graphH;
	
	Cube selected;
	int textSize;
	
	public void setup() {
		selected = null;
		solves = Solve.parseSolves(CubingTimer.FILE_NAME);
		solves.sort(new SolveComparator(SortField.SOLVEDATE));
		System.out.println("Loaded solves");
		textFont(loadFont("Monospaced-100.vlw"));
		textSize = 20;
		textSize(textSize);
		graphStart = new PVector(60,100);
		graphEnd = new PVector(width-50,height-120);
		graphW = graphEnd.x-graphStart.x;
		graphH = graphEnd.y-graphStart.y;
	}
	
	int red = color(255,0,0);
	int green = color(0,255,0);
	
	public void draw() {
		textAlign(LEFT,TOP);
		background(255);
		fill(0);
		text("Selected cube: " + selected,5,5);

		noFill();
		stroke(0);
		rect(graphStart.x,graphStart.y,graphEnd.x-graphStart.x,graphEnd.y-graphStart.y);
		
		if(selected != null) {
			ArrayList<Solve> list = solvesForCube();
			int size = list.size();
			
			long largestSolveTime = 0;
			int largestSolveTimeInd = 0;
			for(int i = 0; i < size; i++) {
				Solve s = list.get(i);
				if(s.solveTime > largestSolveTime) {
					largestSolveTime = s.solveTime;
					largestSolveTimeInd = i;
				} 
			}
			
			long smallestSolveTime = largestSolveTime;
			int smallestSolveTimeInd = 0;
			for(int i = 0; i < size; i++) {
				Solve s = list.get(i);
				if(smallestSolveTime > s.solveTime) {
					smallestSolveTime = s.solveTime;
					smallestSolveTimeInd = i;
				}
			}
			
			float barWidth = graphW/size;
			for(int i = 0; i < size; i++) {
				Solve s = list.get(i);
				float x = graphStart.x + barWidth*i;
				float height = mapHeight(s.solveTime, largestSolveTime);
				int col = lerpColor(green,red,map(height,0,graphH,0,1));
				fill(col);
				stroke(0);
				rect(x,graphEnd.y-height,barWidth,height);
				textSize(15);
				verticalText(s.strTime.replace(" ", ""),x+barWidth/2+15/2,graphEnd.y+5);
//				verticalText(s.solveDate.replace(" ", ""),x+barWidth/2+15/2,graphEnd.y+5);
//				verticalText(s.strTime.replace(" ", "")+"\n"+s.solveDate.replace(" ", ""),x+barWidth/2+15/2,graphEnd.y+5);
//				fill(255-red(col),255-green(col),255-blue(col));
				fill(0);
				stroke(0);
				textAlign(RIGHT,TOP);
				
				verticalText(s.solveDate.replace(" ", ""),x+barWidth/2+15/2,graphEnd.y-5);
				
				textAlign(LEFT,TOP);
				textSize(textSize);
			}
			
			//  1sec
			//  5sec
			// 10sec
			// 30sec
			//  1min
			//  2min
			//  5min
			// 10min
			// 20min
			// 40min
			//  1h
			//  1.5h
			//  2h
			fill(0);
			stroke(0);
			textAlign(RIGHT,TOP);
			if(largestSolveTime > secMillis(1) && largestSolveTime < secMillis(1*30)) {
				float h = mapHeight(secMillis(1), largestSolveTime);
				text("1sec",graphStart.x,graphEnd.y-h);
				line(graphStart.x-40, graphEnd.y-h, graphStart.x, graphEnd.y-h);
			}
			if(largestSolveTime > secMillis(5) && largestSolveTime < secMillis(5*30)) {
				float h = mapHeight(secMillis(5),largestSolveTime);
				text("5sec",graphStart.x,graphEnd.y-h);
				line(graphStart.x-40, graphEnd.y-h, graphStart.x, graphEnd.y-h);
			}
			if(largestSolveTime > secMillis(10) && largestSolveTime < secMillis(10*30)) {
				float h = mapHeight(secMillis(10),largestSolveTime);
				text("10sec",graphStart.x,graphEnd.y-h);
				line(graphStart.x-40, graphEnd.y-h, graphStart.x, graphEnd.y-h);
			}
			if(largestSolveTime > secMillis(30) && largestSolveTime < secMillis(30*30)) {
				float h = mapHeight(secMillis(30),largestSolveTime);
				text("30sec",graphStart.x,graphEnd.y-h);
				line(graphStart.x-40, graphEnd.y-h, graphStart.x, graphEnd.y-h);	
			}
			if(largestSolveTime > minMillis(1) && largestSolveTime < minMillis(1*30)) {
				float h = mapHeight(minMillis(1),largestSolveTime);
				text("1min",graphStart.x,graphEnd.y-h);
				line(graphStart.x-40, graphEnd.y-h, graphStart.x, graphEnd.y-h);	
			}
			if(largestSolveTime > minMillis(2) && largestSolveTime < minMillis(2*30)) {
				float h = mapHeight(minMillis(2),largestSolveTime);
				text("2min",graphStart.x,graphEnd.y-h);
				line(graphStart.x-40, graphEnd.y-h, graphStart.x, graphEnd.y-h);	
			}
			if(largestSolveTime > minMillis(5) && largestSolveTime < minMillis(5*30)) {
				float h = mapHeight(minMillis(5),largestSolveTime);
				text("5min",graphStart.x,graphEnd.y-h);
				line(graphStart.x-40, graphEnd.y-h, graphStart.x, graphEnd.y-h);	
			}
			if(largestSolveTime > minMillis(10) && largestSolveTime < minMillis(10*30)) {
				float h = mapHeight(minMillis(10),largestSolveTime);
				text("10min",graphStart.x,graphEnd.y-h);
				line(graphStart.x-40, graphEnd.y-h, graphStart.x, graphEnd.y-h);	
			}
			if(largestSolveTime > minMillis(20) && largestSolveTime < minMillis(20*30)) {
				float h = mapHeight(minMillis(20),largestSolveTime);
				text("20min",graphStart.x,graphEnd.y-h);
				line(graphStart.x-40, graphEnd.y-h, graphStart.x, graphEnd.y-h);	
			}
			if(largestSolveTime > minMillis(40) && largestSolveTime < minMillis(40*30)) {
				float h = mapHeight(minMillis(40),largestSolveTime);
				text("40min",graphStart.x,graphEnd.y-h);
				line(graphStart.x-40,graphEnd.y-h,graphStart.x,graphEnd.y-h);
			}
			if(largestSolveTime > hourMillis(1) && largestSolveTime < hourMillis(1*30)) {
				float h = mapHeight(hourMillis(1),largestSolveTime);
				text("1h",graphStart.x,graphEnd.y-h);
				line(graphStart.x-40,graphEnd.y-h,graphStart.x,graphEnd.y-h);
			}
			if(largestSolveTime > hourMillis(1.5f) && largestSolveTime < hourMillis(1.5f*30)) {
				float h = mapHeight(hourMillis(1.5f),largestSolveTime);
				text("1.5h",graphStart.x,graphEnd.y-h);
				line(graphStart.x-40,graphEnd.y-h,graphStart.x,graphEnd.y-h);
			}
		}
	}

	public float mapHeight(long solveTime, long largestSolveTime) {
		return map(solveTime, 0, largestSolveTime, 0, graphH);
	}
	
	public long secMillis(int secs) {
		return 1000*secs;
	}
	
	public long minMillis(int mins) {
		return secMillis(mins*60);
	}
	
	public long hourMillis(float hrs) {
		return (long) (hrs*minMillis(60));
	}
	
	public void verticalText(String text, float x, float y) {
		pushMatrix();
		translate(x,y);
		rotate(HALF_PI);
		text(text,0,0);
		popMatrix();
	}
	
	public ArrayList<Solve> solvesForCube() {
		ArrayList<Solve> list = new ArrayList<Solve>();
		for(Solve s : solves) if(s.cube == selected) list.add(s);
		return list;
	}
	
	public void mousePressed() {
		
	}
	
	public void showMessage(String s) {
		showMessage(s,s);
	}
	
	public void showMessage(String msg, String title) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.DEFAULT_OPTION);
	}
	
	public void keyPressed() {
		if(key == 'z') {
			Cube c = Cube.promptForCube();
			if(c == null) {
				showMessage("No action taken - no cube selected", "No action taken");
			} else {
				selected = c;
			}
		}
	}
	
}
