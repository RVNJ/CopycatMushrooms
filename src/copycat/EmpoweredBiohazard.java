package copycat;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;

public class EmpoweredBiohazard extends Actor {

	static private BufferedImage IMG;
	static {
		try {
			IMG = ImageIO.read(new File("src/copycat/actorimages/blackbiohazard.png"));
		} catch (IOException e) {
			System.out.println("you're bad");
			e.printStackTrace();
		}
	}

	static private final int MAXIMUM_HITPOINTS = 1_000;
	static private final int HITPOINTS = MAXIMUM_HITPOINTS;
	static private final int LIFESPAN_TIMER = 999_999_999;
	static private final int ATTACK_POWER = 100;
	static private final int ATTACK_POWER_ACCELERATION = 10;
	static private final int ATTACK_POWER_CAP = 2_000;
	static private final int BLEED_DAMAGE = 50;
	static private final int DAMAGE_ON_DEATH = 200;
	static private final int ATTACK_RANGE = 1;
	static private final int ATTACK_COOLDOWN = 50;
	static private final int ATTACK_COOLDOWN_TIMER = 0;
	static private final int STUN_DURATION = 0;
	static private final int BIND_DURATION = 0;
	static private final int HEALING = 0;
	static private final int HEALING_COOLDOWN = 0;
	static private final int IMMUNITY_DURATION = 5_000;
	static private final int IMMUNITY_TIMER = 0;
	static private final int DAMAGE_REDUCTION_DURATION = 60_000;
	static private final int DAMAGE_REDUCTION_TIMER = DAMAGE_REDUCTION_DURATION;
	static private final double DAMAGE_REDUCTION_AMOUNT = 25.00;
	static private final boolean FLYING = false;
	static private final double SPEED = -0.1;
	static private final double SPEED_ACCELERATION = 0;
	static private final double SPEED_ACCELERATION_CAP = -0.1;
	static private final int LEVEL = 1;
	static private final int COST = 10_000;

	public EmpoweredBiohazard(Point2D.Double startingPosition, Point2D.Double initHitbox) {// Point2D.Double
																						// startingPosition,
																						// Point2D.Double initHitbox,
																						// BufferedImage img, int
																						// health, int coolDown, double
																						// speed, int attackDamage) {
		super(startingPosition, initHitbox, IMG, MAXIMUM_HITPOINTS, HITPOINTS, LIFESPAN_TIMER, ATTACK_POWER,
				ATTACK_POWER_ACCELERATION, ATTACK_POWER_CAP, BLEED_DAMAGE, DAMAGE_ON_DEATH, ATTACK_RANGE,
				ATTACK_COOLDOWN, ATTACK_COOLDOWN_TIMER, STUN_DURATION, BIND_DURATION, HEALING, HEALING_COOLDOWN,
				IMMUNITY_DURATION, IMMUNITY_TIMER, DAMAGE_REDUCTION_DURATION, DAMAGE_REDUCTION_TIMER,
				DAMAGE_REDUCTION_AMOUNT, FLYING, SPEED, SPEED_ACCELERATION, SPEED_ACCELERATION_CAP, LEVEL, COST);
	};

	/**
	 * overrides drawHealthBar method to draw a larger health bar
	 */
	@Override
	public void drawHitpointsBar(Graphics g) {
		Point2D.Double pos = this.getPosition();
		Point2D.Double box = this.getHitbox();
	    g.setColor(Color.BLACK);  
		g.drawRect((int)pos.getX() - 5,(int) pos.getY() - 5, (int)(box.getX()+9), 3);  
	    g.setColor(new Color(191,191,191));  
		g.fillRect((int)pos.getX() - 5,(int) pos.getY() - 5, (int)((box.getX()+9) * this.hitpoints / (double)this.hitpoints), 3);
	}

	/**
	 * overrides the update to increase its damage per cooldown time by 100, up to a maximum of 2000, only attacks plants and
	 * neutrals not of type Poison or NuclearBomb, when it dies, increases the score by 10000
	 */
	@Override
	public void update() {
		super.update();
		if(this.attackPower < 2000) {
			if(this.readyForAttack()) {
				this.attackPower += attackPowerAcceleration;
				this.resetAttackCooldown();
			}
		}
		if(this.isAlive()) {
			for(Actor plant : ActorTest.plants) {
				this.attack(plant);
			}
			for(Actor neutral : ActorTest.neutrals) {
				if(!(neutral instanceof Poison) && !(neutral instanceof NuclearBomb)) {
					this.attack(neutral);
				}
			}
		} else if (!this.isAlive()) {
			ActorTest.score += 10000;
		}
	}
}