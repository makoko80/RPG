package Rpg;

public abstract class Monster {
	int hp;
	String name;
	boolean alive;

	public Monster(String name) {
		this.name = name;
		this.hp = 45;
		this.alive = true;
	}

	public void run() {
		System.out.println(this.name + "は逃げ出した");
	}

	public abstract void attack(Hero h);

	private void die() {
		System.out.println(this.name + "を倒した");
		this.alive = false;
	}

	public int checkAlive(Hero h) {
		if (this.hp > 0 && this.alive == true) {
			this.attack(h);
			return 0;
		} else if (this.alive == true) {
			this.die();
			return 1;
		}
		return 0;
	}

}
