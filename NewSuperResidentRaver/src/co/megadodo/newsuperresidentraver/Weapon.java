package co.megadodo.newsuperresidentraver;

public enum Weapon {

	AK47,
	CHAINSAW,
	FIST,
	GRENADE,
	MACHINEGUN,
	MINE,
	PISTOL,
	RIFLE,
	SHOTGUN,
	TOMMYGUN;
	
	public int getSpriteNum() {
		switch(this) {
			case AK47: return 6;
			case CHAINSAW: return 7;
			case FIST: return 0;
			case GRENADE: return 8;
			case MACHINEGUN: return 3;
			case MINE: return 9;
			case PISTOL: return 1;
			case RIFLE: return 5;
			case SHOTGUN: return 2;
			case TOMMYGUN: return 4;
		}
		return -1;
	}
	
	public String getName() {
		return this.toString().toLowerCase();
	}
}
