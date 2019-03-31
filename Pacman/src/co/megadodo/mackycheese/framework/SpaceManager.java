

package co.megadodo.mackycheese.framework;

import java.util.ArrayList;


public class SpaceManager {
	private SpaceManager() {}
	
	public static void doCollisions(Game g, ArrayList<Entity> entities) {
		for(int count = 0; count < entities.size(); count++) {
			for(int count2 = 0; count2 < entities.size(); count2++) {
				if(count != count2) { // if we are not colliding with myself
					Entity entA = entities.get(count);
					Entity entB = entities.get(count2);
					Axis ax = null;
					if(
//							entA.hasNotCollidedWith(entB) && 
							entA.getBounds().intersects(entB.getBounds())) {
						if(entA.getType().canCollideWith(entB.getType())) {
							entA.collide(entB, ax);
						}
						if(entB.getType().canCollideWith(entA.getType())) {
							entB.collide(entA, ax);
						}
					}
				}
			}
		}
	}
}
