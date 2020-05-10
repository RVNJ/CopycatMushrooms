package copycat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Actor extends Sprite implements Attack {

	protected int health;
	protected int fullHealth;
	protected int attackDamage;
	private int coolDownCounter;
	private int coolDown;
	protected double speed;
	private boolean isColliding;
	
	public Actor(Point2D.Double startingPosition, Point2D.Double initHitbox, BufferedImage img, int health, int coolDown, double speed, int attackDamage) {
		super(startingPosition, initHitbox, img);
		this.health = health;
		this.fullHealth = health;
		this.coolDownCounter = coolDown;
		this.coolDown = coolDown;
		this.speed = speed;
		this.attackDamage = attackDamage;
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
		coolDownCounter--;		
	}
	
	public boolean readyForAction() {
		if (coolDownCounter <= 0) {
			return true;
		}
		return false;
	}
	
	public void resetCoolDown() {
		coolDownCounter = coolDown;
	}
		
	public void changeHealth(int change) {
		health += change;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public void drawHealthBar(Graphics g) {
		Point2D.Double pos = this.getPosition();
		Point2D.Double box = this.getHitbox();
	    g.setColor(Color.BLACK);  
		g.drawRect((int)pos.getX(),(int) pos.getY() - 5, (int)box.getX(), 3);  
	    g.setColor(new Color(191,191,191));  
		g.fillRect((int)pos.getX(),(int) pos.getY() - 5, (int)(box.getX() * this.health / (double)this.fullHealth), 3);  
	}
	
	@Override
	public void attack(Actor other) {
		if (this != other && this.isCollidingOther(other)) {
			setColliding(true);
			if (this.readyForAction()) {
				other.changeHealth(-attackDamage);
				this.resetCoolDown();
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
