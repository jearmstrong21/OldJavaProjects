package co.megadodo.newsuperresidentraver;

import java.awt.Graphics2D;
import java.io.File;

import co.megadodo.newsuperresidentraver.engine.Animation;
import co.megadodo.newsuperresidentraver.engine.Entity;
import co.megadodo.newsuperresidentraver.engine.EntityType;
import co.megadodo.newsuperresidentraver.engine.Game;
import co.megadodo.newsuperresidentraver.engine.Vector;

public class EntityPlayer extends Entity {
	
	Weapon weapon;
	
	public void setIdle() {
		sprite.makeAnim("idle"+weapon.getName());
	}
	
	public void setWalk() {
		sprite.makeAnim("walk"+weapon.getName());
	}
	
	public void setFire() {
		sprite.makeAnim("fire"+weapon.getName());
	}
	
	public String[] formatStrs(String file) {
		int children = filesInSubFile(file);
		String[] strs = new String[children];
		for(int i=0;i<children;i++) {
			strs[i] = ""+i;
		}
		return strs;
	}
	
	public int filesInSubFile(String dir) {
		File file = new File(dir);
		int children = file.list().length;
		return children;
	}

	public EntityPlayer(Vector pos, Vector size) {
		super(pos, size, EntityType.PLAYER, "player");
		for(Weapon w : Weapon.values()) {
			String str = w.getName();
			int num = w.getSpriteNum();
			String file = "entities/player/"+str+"/walk/";
			sprite.addAnim(new Animation(4, "walk"+str, file + "player-walk-"+num+"-0",
					".png", formatStrs(file)));
		}
		for(Weapon w : Weapon.values()) {
			String str = w.getName();
			int num = w.getSpriteNum();
			String file = "entities/player/"+str+"/idle/";
			sprite.addAnim(new Animation(1000,"idle"+str,file+"player-idle-",".png",""+num));
		}
		for(Weapon w : Weapon.values()) {
			String str = w.getName();
			int num = w.getSpriteNum();
			String file = "entities/player/"+str+"/fire/";
			sprite.addAnim(new Animation(5,"fire"+str,file+"player-fire-"+num+"-0",".png",formatStrs(file)));
		}
		weapon = Weapon.PISTOL;
		setIdle();
		//walk4 idle1000 fire5
	}
	int speed=1;
	
	public void update() {
		pos.y = Game.H - Game.floorLvl;
		sprite.update();
		if(Game.inst.getKey('a') || Game.inst.getKey('A')) {
			vel.x=-speed;
			setWalk();
		} else if(Game.inst.getKey('d') || Game.inst.getKey('D')) {
			vel.x = speed;
			setWalk();
		} else {
			vel.x = 0;
//			setIdle();
		}
		pos = Vector.plus(pos, vel);
	}
	
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
	}

}
