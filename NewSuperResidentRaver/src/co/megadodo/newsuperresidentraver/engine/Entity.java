package co.megadodo.newsuperresidentraver.engine;

import java.awt.Graphics2D;

public class Entity {

	public Vector pos;
	public Vector size;
	public BoxCollider co;
	public EntityType type;
	public SpriteManager sprite;
	public Vector vel;
	public Vector acc;
	public String name;
	public boolean dead;
	public Entity(Vector pos, Vector size,EntityType t,String n) {
		this.dead = false;
		this.pos=pos;
		this.size=size;
		this.name = n;
		this.vel = Vector.zero;
		this.acc = Vector.zero;
		this.co =new BoxCollider(pos,size);
		this.type = t;
		this.sprite = new SpriteManager();
	}
	
	public boolean equalsEnt(Entity o) {
		return o.name.equals(name);
	}
	
	public void update() {
		sprite.update();
	}
	
	public void draw(Graphics2D g2d) {
		sprite.draw(g2d, pos.x, pos.y, size.x, size.y);
	}
	
	public void kill() {
		dead = true;
		Game.inst.removeEntity(this);
	}
	
	public void collide(Entity other) {
		
	}
	
}
