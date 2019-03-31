package co.megadodo.mackycheese.framework.combat;

import co.megadodo.mackycheese.framework.Axis;
import co.megadodo.mackycheese.framework.Entity;
import co.megadodo.mackycheese.framework.Game;

public class CombatableEntity extends Entity {

	protected CombatEntity combat = new CombatEntity();
	
	public CombatableEntity(Game g, int posX, int posY, int sizeX, int sizeY, String name) {
		super(g, posX, posY, sizeX, sizeY, name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void collide(Entity other, Axis ax) {
		if(other instanceof CombatableEntity) {
			this.combat.attack(((CombatableEntity)other).combat);
		}
	}
	
	@Override
	public void update() {
		super.update();
		if(combat.health <= 0) {
			this.kill();
		}
	}

}


