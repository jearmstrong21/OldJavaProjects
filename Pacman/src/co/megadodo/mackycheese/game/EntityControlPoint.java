package co.megadodo.mackycheese.game;

import java.awt.Color;
import java.awt.Graphics2D;

import co.megadodo.mackycheese.framework.Entity;
import co.megadodo.mackycheese.framework.EntityType;
import co.megadodo.mackycheese.framework.Game;

public class EntityControlPoint extends Entity {

	public EntityControlPoint(Game g, int posX, int posY) {
		super(g, posX, posY, 16, 16, "controlpoint");
		this.type = EntityType.createEntityType("pacman-entity");
	}
	
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		g2d.setColor(new Color(0, 255, 0, 100));
		g2d.fillRect(posX, posY, sizeX, sizeY);
	}

}
