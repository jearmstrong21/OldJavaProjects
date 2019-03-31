package co.megadodo.cubingtimer;

public class Timer {
	
	long timeStart;
	long timePause;
	String strTime;
	boolean paused;
	boolean started;
	
	public Timer() {
		timeStart =    0;
		timePause =    0;
		paused    = true;
		started   = false;
		strTime   = "00 : 00 : 00 : 000";
	}
	
	public void stop() {
		timeStart =    0;
		timePause =    0;
		paused    =  true;
		started   = false;
		strTime   = "00 : 00 : 00 : 000";
	}
	
	public void start() {
		paused = false;
		started = true;
		timeStart = System.currentTimeMillis();
		timePause = System.currentTimeMillis();
		strTime   = "00 : 00 : 00 : 000";
	}
	
	public void pause() {
		paused = true;
		timePause = System.currentTimeMillis();
	}
	
	public void unpause() {
		paused = false;
		timeStart += System.currentTimeMillis() - timePause;
	}
	
	public String getStrTime() {
		return strTime;
	}
	
	public String formatHour(long h) {
		if(h < 10) return "0" + h;
		return "" + h;
	}
	
	public String formatMinute(long m) {
		if(m < 10) return "0" + m;
		return "" + m;
	}
	
	public String formatSecond(long s) {
		if(s < 10) return "0" + s;
		return "" + s;
	}
	
	public String formatMillis(long m) {
		if(m < 10) return "00" + m;
		if(m < 100) return "0" + m;
		return "" + m;
	}
	
	public long passedTime() {
		return System.currentTimeMillis() - timeStart;
	}
	
	public long getMillis() {
		return passedTime() % 1000;
	}
	
	public long getSecond() {
		return (passedTime() / 1000) % 60;
	}
	
	public long getMinute() {
		return (passedTime() / 1000 / 60) % 60;
	}
	
	public long getHour() {
		return (passedTime() / 1000 / 60 / 60);
	}
	
	public void update() {
		if(!paused) {
			strTime = formatHour(getHour()) + " : " + formatMinute(getMinute()) + " : " + formatSecond(getSecond()) + " : " + formatMillis(getMillis());
		}
	}
	
	public void changeStarted() {
		if(paused) unpause();
		else pause();
	}

}
