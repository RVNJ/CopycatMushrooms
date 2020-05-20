package copycat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Actor extends Sprite implements Attack {

	protected int maximumHitpoints;
	protected int hitpoints;
	protected int lifespanTimer;
	protected int attackPower;
	protected int attackPowerAcceleration;
	protected int attackPowerCap;
	protected int bleedDamage;
	protected int damageOnDeath;
	protected int attackRange;
	protected int attackCooldown;
	protected int attackCooldownTimer;
	protected int stunDuration;
	protected int bindDuration;
	protected int healing;
	protected int healingCooldown;
	protected int immunityDuration;
	protected int immunityTimer;
	protected int damageReductionDuration;
	protected int damageReductionTimer;
	protected double damageReductionAmount;
	protected boolean flying;
	protected double speed;
	protected double speedAcceleration;
	protected double speedAccelerationCap;
	protected int level;
	protected int cost;
	private boolean isColliding;

	public Actor(Point2D.Double startingPosition, Point2D.Double initHitbox, BufferedImage img, int maximumHitpoints,
			int hitpoints, int lifespanTimer, int attackPower, int attackPowerAcceleration, int attackPowerCap,
			int bleedDamage, int damageOnDeath, int attackRange, int attackCooldown, int attackCooldownTimer,
			int stunDuration, int bindDuration, int healing, int healingCooldown, int immunityDuration,
			int immunityTimer, int damageReductionDuration, int damageReductionTimer, double damageReductionAmount,
			boolean flying, double speed, double speedAcceleration, double speedAccelerationCap, int level, int cost) {
		super(startingPosition, initHitbox, img);
		this.maximumHitpoints = hitpoints;
		this.hitpoints = hitpoints;
		this.lifespanTimer = lifespanTimer;
		this.attackPower = attackPower;
		this.attackPowerAcceleration = attackPowerAcceleration;
		this.attackPowerCap = attackPowerCap;
		this.bleedDamage = bleedDamage;
		this.damageOnDeath = damageOnDeath;
		this.attackRange = attackRange;
		this.attackCooldown = attackCooldown;
		this.attackCooldownTimer = attackCooldown;
		this.stunDuration = stunDuration;
		this.bindDuration = bindDuration;
		this.healing = healing;
		this.healingCooldown = healingCooldown;
		this.immunityDuration = immunityDuration;
		this.immunityTimer = immunityDuration;
		this.damageReductionDuration = damageReductionDuration;
		this.damageReductionTimer = damageReductionDuration;
		this.damageReductionAmount = damageReductionAmount;
		this.flying = flying;
		this.speed = speed;
		this.speedAcceleration = speedAcceleration;
		this.speedAccelerationCap = speedAccelerationCap;
		this.level = level;
		this.cost = cost;
		isColliding = false;
	}

	public void move() {
		if (!isColliding)
			shiftPosition(new Point2D.Double(speed, 0));
	}

	public void setColliding(boolean collisionStatus) {
		isColliding = collisionStatus;
	}

	public boolean getColliding() {
		return isColliding;
	}

	public void update() {
		isColliding = false;
		attackCooldownTimer--;
	}

	public boolean readyForAttack() {
		if (attackCooldownTimer <= 0) {
			return true;
		}
		return false;
	}

	public void resetAttackCooldown() {
		attackCooldownTimer = attackCooldown;
	}

	public void changeHitpoints(int change) {
		hitpoints += change;
	}

	public boolean isAlive() {
		return hitpoints > 0;
	}

	public void drawHitpointsBar(Graphics g) {
		Point2D.Double pos = this.getPosition();
		Point2D.Double box = this.getHitbox();
		g.setColor(Color.BLACK);
		g.drawRect((int) pos.getX(), (int) pos.getY() - 5, (int) box.getX(), 3);
		g.setColor(new Color(191, 191, 191));
		g.fillRect((int) pos.getX(), (int) pos.getY() - 5,
				(int) (box.getX() * this.hitpoints / (double) this.maximumHitpoints), 3);
	}

	@Override
	public void attack(Actor other) {
		if (this != other && this.isCollidingOther(other)) {
			setColliding(true);
			if (this.readyForAttack()) {
				other.changeHitpoints(-attackPower);
				this.resetAttackCooldown();
			}
		}
	}

	public boolean setCollisionStatus(Actor other) {
		if (other.getPosition().getY() + other.getHitbox().getY() < other.getPosition().getY()) {
			return false;
		}
		// See if this rectangle is below the other
		if (other.getPosition().getY() > other.getPosition().getY() + other.getHitbox().getY()) {
			return false;
		}

		// See if this rectangle is left of the other
		if (other.getPosition().getX() + other.getHitbox().getX() < other.getPosition().getX()) {
			return false;
		}
		// See if this rectangle is right of the other
		if (other.getPosition().getX() > other.getPosition().getX() + other.getHitbox().getX()) {
			return false;
		}
		return true;
	}

}
