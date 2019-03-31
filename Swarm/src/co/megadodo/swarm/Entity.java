package co.megadodo.swarm;

import java.util.ArrayList;

public class Entity {
	int x;
	int y;
	float r;
	float g;
	float b;
	double rotat;
	int speed,rad,viewDist,numInRange,radDistForMate;
	double changeSpeed,randFactor,mateChance;
	int id = 0;
	boolean dead = false;
	private static int num = 0;
	
	public static Entity NULLENT = new Entity(-1,-1,-1);
	
	public static int SPEED = 5;
	public static int MARG = 0;
	public static int RAD = 10;
	public static int VIEWDIST = 50;
	public static int NUMINRANGE = 10;
	public static double CHANGESPEED = 0.05;
	public static double RANDFACTOR = 5;
	public static double MATECHANCE = 0;
	
	int genDriftInt(int b) {
		boolean a = Math.random() < 0.5;
		if(a) return (int)(Math.random()*-1*b);
		return (int)(Math.random()*b);
	}
	
	double genDriftDouble(double b) {
		boolean a = Math.random() < 0.5;
		if(a) return Math.random()*-1*b;
		return Math.random()*b;
	}
	
	public Entity(int x, int y, double rotat) {
		id = num;
		num++;
		this.x = x;
		this.y = y;
		this.rotat = rotat;
		this.r = (float) Math.random();
		this.g = (float) Math.random();
		this.b = (float) Math.random();
		this.speed = SPEED;
		this.rad = RAD;
		this.viewDist = VIEWDIST;
		this.numInRange = NUMINRANGE;
		this.changeSpeed = CHANGESPEED;
		this.randFactor = RANDFACTOR;
		this.mateChance = MATECHANCE;
		
	}
	//reproduction constructor
	protected Entity(int x, int y, float r, float g, float b, double rotat, int speed, int rad, int viewDist, int numInRange, double changeSpeed, double randFactor, double mateChance) {
		id = num;
		num++;
		this.x = x + genDriftInt(15);
		this.y = y + genDriftInt(15);
		this.r = r + (float)genDriftDouble(0.1D);
		this.g = g + (float)genDriftDouble(0.1D);
		this.b = b + (float)genDriftDouble(0.1D);
		this.rotat = rotat + genDriftDouble(Math.PI);
		this.speed = speed + genDriftInt(5);
		this.rad = rad + genDriftInt(5);
		
		this.viewDist = viewDist + genDriftInt(30);
		this.numInRange = numInRange + genDriftInt(5);
		this.changeSpeed = changeSpeed + genDriftDouble(0.1);
		this.randFactor = randFactor + genDriftDouble(0.01);
		this.mateChance = mateChance + genDriftDouble(0.01);
		
		if(r<0F)r=0.1F;
		if(g<0F)g=0.1F;
		if(b<0F)b=0.1F;
		
		if(r>1F)r=0.9F;
		if(g>1F)g=0.9F;
		if(b>1F)b=0.9F;
		
		if(rad<1)rad=1;
	}
	
	public int dist(int x, int y) {
		return dist(x,y,this.x,this.y);
	}
	
	public boolean canMate(Entity other) {
		return Math.abs(other.rad - rad) <= 5;
	}
	
	
	public static int dist(int a, int b, int c, int d) {
		return (int) Math.sqrt( (a-c)*(a-c) + (b-d)*(b-d));
	}
	int lx = -1;
	int ly = -1;
	
	public void update(ArrayList<Entity> all, int mousex, int mousey, int maxW, int maxH) {
		lx = x;
		ly = y;
		if(x < MARG ) x = maxW - MARG;
		if(y < MARG ) y = maxH - MARG;
		if(x > maxW - MARG) x = MARG;
		if(y > maxH - MARG) y = MARG;
		if(r<0F)r=0.1F;
		if(g<0F)g=0.1F;
		if(b<0F)b=0.1F;
		
		if(r>1F)r=0.9F;
		if(g>1F)g=0.9F;
		if(b>1F)b=0.9F;
		ArrayList<Entity> list = new ArrayList<Entity>();
		for(Entity ent: all) {
			
			if( dist(ent.x, ent.y) <= viewDist && list.size() < numInRange && Math.abs(ent.rad-rad) <= 20) list.add(ent);
		}
		int total = 0;
		double avg = 0;
		for(Entity ent: list) {
//			if(Math.abs(ent.rotat-rotat) <= 1) continue;
			total++;;
			avg+=ent.rotat;
		}
		avg/=total;
		
		
		if(rotat <= avg) rotat+=changeSpeed;
		if(rotat >= avg) rotat-=changeSpeed;
		double rand = Math.random();
		double newRand = Math.random()/randFactor;
		if(rand>0.5)rotat+=newRand;
		else rotat-=newRand;
		
		x+=(int)(Math.cos(rotat)*speed);
		y+=(int)(Math.sin(rotat)*speed);

		
		if(Math.random() < 0.0001) rad++;
		if(rad > 1000) rad = 1000;
		
	}
	
	public Entity collide(Entity other) {
		if(other.rad-rad > 5) {
			other.rad+=1;
			System.out.println("DEATH " + id);
//			return null;
			rad--;
			if(rad < 1) return null;
		}
		if(Math.abs(other.rad-rad) <= 5 && Math.random() < mateChance && Math.random() < other.mateChance) {
			return reprod(other);
		}
		return Entity.NULLENT;
	}
	
	public Entity reprod(Entity mate) {
		Entity a = new Entity( (x+mate.x)/2, (y+mate.y)/2, (r+mate.r)/2, (g+mate.g)/2, (b+mate.b)/2, (rotat+mate.rotat)/2, (speed+mate.speed)/2, (rad+mate.rad)/2, (viewDist+mate.viewDist)/2, (numInRange+mate.numInRange)/2, (changeSpeed+mate.changeSpeed)/2, (randFactor+mate.randFactor)/2,(mateChance+mate.mateChance)/2);
		
		return a;
	}
}
