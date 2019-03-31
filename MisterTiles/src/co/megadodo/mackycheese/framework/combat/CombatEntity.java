package co.megadodo.mackycheese.framework.combat;

import java.util.ArrayList;
import co.megadodo.mackycheese.framework.*;

public class CombatEntity {
	
	protected int health;
	protected int maxHealth;
	protected boolean invincible;
	protected int defensePoints;
	protected int attackPoints;
	protected int maxDefensePoints = 20;
	protected int maxAttackPoints = 20;
	protected int attackStrength = 0;
	protected int armorPoints = 0;
	protected int maxArmorPoints = 20;

	public CombatEntity() {
		// TODO Auto-generated constructor stub
	}

	public CombatEntity(int health, int maxHealth, boolean invincible, int defensePoints, int attackPoints, int attackStrength, int armorPoints, int maxArmorPoints) {
		super();
		this.health = health;
		this.maxHealth = maxHealth;
		this.invincible = invincible;
		this.defensePoints = defensePoints;
		this.attackPoints = attackPoints;
		this.attackStrength = attackStrength;
		this.armorPoints = armorPoints;
		this.maxArmorPoints = maxArmorPoints;
	}
	
	

	@Override
	public String toString() {
		return "CombatEntity [health=" + health + ", maxHealth=" + maxHealth + ", invincible=" + invincible
				+ ", defensePoints=" + defensePoints + ", attackPoints=" + attackPoints + ", maxDefensePoints="
				+ maxDefensePoints + ", maxAttackPoints=" + maxAttackPoints + ", attackStrength=" + attackStrength
				+ ", armorPoints=" + armorPoints + ", maxArmorPoints=" + maxArmorPoints + "]";
	}

	public int getArmorPoints() {
		return armorPoints;
	}

	public void setArmorPoints(int armorPoints) {
		this.armorPoints = armorPoints;
	}

	public int getMaxArmorPoints() {
		return maxArmorPoints;
	}

	public void setMaxArmorPoints(int maxArmorPoints) {
		this.maxArmorPoints = maxArmorPoints;
	}

	public int getMaxDefensePoints() {
		return maxDefensePoints;
	}

	public void setMaxDefensePoints(int maxDefensePoints) {
		this.maxDefensePoints = maxDefensePoints;
	}

	public int getMaxAttackPoints() {
		return maxAttackPoints;
	}

	public void setMaxAttackPoints(int maxAttackPoints) {
		this.maxAttackPoints = maxAttackPoints;
	}

	public int getAttackStrength() {
		return attackStrength;
	}

	public void setAttackStrength(int attackStrength) {
		this.attackStrength = attackStrength;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public int getDefensePoints() {
		return defensePoints;
	}

	public void setDefensePoints(int defensePoints) {
		this.defensePoints = defensePoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}
	
	public int getDiceRoll() {
		return (int)(Math.random() * 6) + 1;
	}
	
	public void attack(CombatEntity other) {
		final int MAX_ATTACK_POINTS = 3;
		final int MAX_DEFENSE_POINTS = 2;
		
		int otherPoints = Utils.constrain(1, MAX_DEFENSE_POINTS, other.defensePoints);
		int thisPoints = Utils.constrain(1, MAX_ATTACK_POINTS, this.attackPoints);
		int thisLoss = 0;
		int otherLoss = 0;
		ArrayList<Integer> thisDie = new ArrayList<Integer>();
		ArrayList<Integer> otherDie = new ArrayList<Integer>();
		
		for(int count = 0; count < thisPoints; count++) {
			int roll = getDiceRoll();
			System.out.println("THS ROLL" + count + ": " + roll);
			thisDie.add(roll);
		}
		System.out.println();
		for(int count = 0; count < otherPoints; count++) {
			int roll = getDiceRoll();
			System.out.println("OTH ROLL" + count + ": " + roll);
			otherDie.add(roll);
		}
		System.out.println();
		for(int count = 0; count < Math.min(thisPoints, otherPoints); count++) {
			int thisMax = thisDie.get(getMaxIndex(thisDie));
			int otherMax = otherDie.get(getMaxIndex(otherDie));
			
			if(thisMax <  otherMax) { thisLoss++;              }
			if(thisMax == otherMax) {             otherLoss++; }
			if(thisMax >  otherMax) {             otherLoss++; }
			
			System.out.println("THISDIE: " + thisMax + " VS " + otherMax);
			thisDie.remove(getMaxIndex(thisDie));
			otherDie.remove(getMaxIndex(otherDie));
		}
		this.takeArmoredDamage(thisLoss * other.attackStrength);
		other.takeArmoredDamage(otherLoss * this.attackStrength);
	}
	
	
	
	public boolean isDead() {
		return health <= 0;
	}
	
	public static int getMaxIndex(ArrayList<Integer> getMax) {
		int largestIndex = 0;
		for(int count = 1; count < getMax.size(); count++) {
			if(getMax.get(count) > getMax.get(largestIndex)) {
				largestIndex = count;
			}
		}
		return largestIndex;
	}
	
	public void takeArmoredDamage(int amount) {
		double armorPercentage = ((double) armorPoints) / maxArmorPoints;
		double armorTakeAway = armorPercentage * amount;
		this.takeUnarmoredDamage(amount - ((int)armorTakeAway));
	}
	
	public void takeUnarmoredDamage(int amount) {
		if(!this.invincible)
		this.health -= amount;
	}
	

}
