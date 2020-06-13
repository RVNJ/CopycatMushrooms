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

	static private final int MAXIMUM_HITPOINTS = 50_000;
	static private final int HITPOINTS = MAXIMUM_HITPOINTS;
	static private final int MAXIMUM_LIFESPAN = 999_999_999;
	static private final int LIFESPAN_TIMER = MAXIMUM_LIFESPAN;
	static private final int ATTACK_POWER = 2_000;
	static private final int ATTACK_POWER_ACCELERATION = 2_000;
	static private final int ATTACK_POWER_CAP = 20_000;
	static private final int BLEED_DAMAGE = 0;
	static private final int DAMAGE_ON_DEATH = 1_00;
	static private final int ATTACK_RANGE = 1;
	static private final int ATTACK_COOLDOWN = 100;
	static private final int ATTACK_COOLDOWN_TIMER = 0;
	static private final int STUN_DURATION = 0;
	static private final int BIND_DURATION = 0;
	static private final int HEALING = 0;
	static private final int HEALING_COOLDOWN = 0;
	static private final int IMMUNITY_DURATION = 2_000;
	static private final int IMMUNITY_TIMER = IMMUNITY_DURATION;
	static private final int DAMAGE_REDUCTION_DURATION = 10_000;
	static private final int DAMAGE_REDUCTION_TIMER = DAMAGE_REDUCTION_DURATION;
	static private final double DAMAGE_REDUCTION_AMOUNT = 25.00;
	static private final boolean FLYING = false;
	static private final double SPEED = -0.2;
	static private final double SPEED_ACCELERATION = 0;
	static private final double SPEED_ACCELERATION_CAP = SPEED;
	static private final int LEVEL = 1;
	static private final int COST = 0;

	public EmpoweredBiohazard(Point2D.Double startingPosition, Point2D.Double initHitbox) {
		super(startingPosition, initHitbox, IMG, MAXIMUM_HITPOINTS, HITPOINTS, MAXIMUM_LIFESPAN, LIFESPAN_TIMER,
				ATTACK_POWER, ATTACK_POWER_ACCELERATION, ATTACK_POWER_CAP, BLEED_DAMAGE, DAMAGE_ON_DEATH, ATTACK_RANGE,
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
		if (immunityTimer <= 0 && damageReductionTimer <= 0) {
			g.setColor(Color.BLACK);
			g.drawRect((int) pos.getX() - 10, (int) pos.getY() - 8, (int) (box.getX() + 20), 3);
			g.setColor(new Color(255, 40, 40));
			g.fillRect((int) pos.getX() - 9, (int) pos.getY() - 7, (int) ((box.getX() + 19) * ((double) this.hitpoints / (double) this.maximumHitpoints)), 2);
		} else {
			g.setColor(Color.BLACK);
			g.drawRect((int) pos.getX() - 10, (int) pos.getY() - 11, (int) (box.getX() + 20), 3);
			g.setColor(new Color(255, 40, 40));
			g.fillRect((int) pos.getX() - 9, (int) pos.getY() - 10, (int) ((box.getX() + 19) * ((double) this.hitpoints / (double) this.maximumHitpoints)), 2);
		}
	}

	@Override
	public void drawLifespanBar(Graphics g) {
		Point2D.Double pos = this.getPosition();
		Point2D.Double box = this.getHitbox();
		if (this.immunityTimer > 0) {
			g.setColor(Color.BLACK);
			g.drawRect((int) pos.getX() - 10, (int) pos.getY() - 8, (int) box.getX() + 20, 3);
			g.setColor(new Color(255, 192, 192));
			g.fillRect((int) pos.getX() - 9, (int) pos.getY() - 7, (int) ((box.getX() + 19) * ((double) this.immunityTimer / (double) this.immunityDuration)), 2);
		} else {
			if (this.damageReductionTimer > 0) {
				g.setColor(Color.BLACK);
				g.drawRect((int) pos.getX() - 10, (int) pos.getY() - 8, (int) box.getX() + 20, 3);
				g.setColor(new Color(255, 128, 40));
				g.fillRect((int) pos.getX() - 9, (int) pos.getY() - 7, (int) ((box.getX() + 19) * ((double) this.damageReductionTimer / (double) this.damageReductionDuration)), 2);
			}
		}
	}

	@Override
	public void drawMaximumEffectBar(Graphics g) {
		Point2D.Double pos = this.getPosition();
		Point2D.Double box = this.getHitbox();
		g.setColor(Color.BLACK);
		g.drawRect((int) pos.getX() - 10, (int) pos.getY() - 5, (int) (box.getX() + 20), 3);
		g.setColor(new Color(255, 255, 40));
		g.fillRect((int) pos.getX() - 9, (int) pos.getY() - 4, (int) ((box.getX() + 19) * ((double) this.attackPower / (double) this.attackPowerCap)), 2);
	}

//	public void spawnThreeByThreeAreaOfEffect(Point2D.Double spawnLocation) {
//		
//	}

	/**
	 * overrides the update to increase its damage per cooldown time by 100, up to a
	 * maximum of 2000, only attacks plants and neutrals not of type Poison or
	 * NuclearBomb, when it dies, increases the score by 10000
	 */
	@Override
	public void update() {
		super.update();

		if (this.isAlive()) {
			for (Actor plant : ActorTest.plants) {
				this.attack(plant);
			}
			for (Actor neutral : ActorTest.neutrals) {
				if (!(neutral instanceof Poison) && !(neutral instanceof NuclearBomb)) {
					this.attack(neutral);
				}
			}
			if (this.attackPower < attackPowerCap) {
				if (this.readyForAttack()) {
					this.attackPower += attackPowerAcceleration;
					this.resetAttackCooldown();
				}
			}
			if (immunityTimer > 0) {
				immunityTimer -= 10;
			} else {
				if (damageReductionTimer > 0) {
					damageReductionTimer -= 10;
				}
			}
		} else if (!this.isAlive()) {
			ActorTest.score += 10000;
		}
	}
}