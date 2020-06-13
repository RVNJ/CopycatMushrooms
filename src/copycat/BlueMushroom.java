package copycat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BlueMushroom extends Actor {

	static private BufferedImage IMG;
	
	static {
		try {
			IMG = ImageIO.read(new File("src/copycat/actorimages/bluemushroom.png"));
		} catch (IOException e) {
			System.out.println("you're bad");
			e.printStackTrace();
		}
	}

	static private final int MAXIMUM_HITPOINTS = 1_000;
	static private final int HITPOINTS = MAXIMUM_HITPOINTS;
	static private final int MAXIMUM_LIFESPAN = 999_999_999;
	static private final int LIFESPAN_TIMER = MAXIMUM_LIFESPAN;
	static private final int ATTACK_POWER = 1;
	static private final int ATTACK_POWER_ACCELERATION = 1;
	static private final int ATTACK_POWER_CAP = 200;
	static private final int BLEED_DAMAGE = 0;
	static private final int DAMAGE_ON_DEATH = 0;
	static private final int ATTACK_RANGE = 1;
	static private final int ATTACK_COOLDOWN = 4;
	static private final int ATTACK_COOLDOWN_TIMER = 0;
	static private final int STUN_DURATION = 0;
	static private final int BIND_DURATION = 0;
	static private final int HEALING = 100;
	static private final int HEALING_COOLDOWN = 200;
	static private final int IMMUNITY_DURATION = 4_000;
	static private final int IMMUNITY_TIMER = 0;
	static private final int DAMAGE_REDUCTION_DURATION = 10_000;
	static private final int DAMAGE_REDUCTION_TIMER = DAMAGE_REDUCTION_DURATION;
	static private final double DAMAGE_REDUCTION_AMOUNT = 50.00;
	static private final boolean FLYING = false;
	static private final double SPEED = 0.5;
	static private final double SPEED_ACCELERATION = 0;
	static private final double SPEED_ACCELERATION_CAP = SPEED;
	static private final int LEVEL = 1;
	static private final int COST = 4000;

	public BlueMushroom(Point2D.Double startingPosition, Point2D.Double initHitbox) {
		super(startingPosition, initHitbox, IMG, MAXIMUM_HITPOINTS, HITPOINTS, MAXIMUM_LIFESPAN, LIFESPAN_TIMER, ATTACK_POWER,
				ATTACK_POWER_ACCELERATION, ATTACK_POWER_CAP, BLEED_DAMAGE, DAMAGE_ON_DEATH, ATTACK_RANGE,
				ATTACK_COOLDOWN, ATTACK_COOLDOWN_TIMER, STUN_DURATION, BIND_DURATION, HEALING, HEALING_COOLDOWN,
				IMMUNITY_DURATION, IMMUNITY_TIMER, DAMAGE_REDUCTION_DURATION, DAMAGE_REDUCTION_TIMER,
				DAMAGE_REDUCTION_AMOUNT, FLYING, SPEED, SPEED_ACCELERATION, SPEED_ACCELERATION_CAP, LEVEL, COST);
	};
	
	@Override
	public void drawHitpointsBar(Graphics g) {
		Point2D.Double pos = this.getPosition();
		Point2D.Double box = this.getHitbox();
		if (immunityTimer <= 0 && damageReductionTimer <= 0) {
			g.setColor(Color.BLACK);
			g.drawRect((int) pos.getX(), (int) pos.getY() - 8, (int) (box.getX()), 3);
			g.setColor(new Color(255, 40, 40));
			g.fillRect((int) pos.getX()+1, (int) pos.getY() - 7, (int) ((box.getX()-1) * ((double) this.hitpoints / (double) this.maximumHitpoints)), 2);
		} else {
			g.setColor(Color.BLACK);
			g.drawRect((int) pos.getX(), (int) pos.getY() - 11, (int) (box.getX()), 3);
			g.setColor(new Color(255, 40, 40));
			g.fillRect((int) pos.getX()+1, (int) pos.getY() - 10, (int) ((box.getX()-1) * ((double) this.hitpoints / (double) this.maximumHitpoints)), 2);
		}
	}

	@Override
	public void drawLifespanBar(Graphics g) {
		Point2D.Double pos = this.getPosition();
		Point2D.Double box = this.getHitbox();
		if (this.immunityTimer > 0) {
			g.setColor(Color.BLACK);
			g.drawRect((int) pos.getX(), (int) pos.getY() - 8, (int) box.getX(), 3);
			g.setColor(new Color(255, 192, 192));
			g.fillRect((int) pos.getX()+1, (int) pos.getY() - 7, (int) ((box.getX()-1) * ((double) this.immunityTimer / (double) this.immunityDuration)), 2);
		} else {
			if (this.damageReductionTimer > 0) {
				g.setColor(Color.BLACK);
				g.drawRect((int) pos.getX(), (int) pos.getY() - 8, (int) box.getX(), 3);
				g.setColor(new Color(255, 128, 40));
				g.fillRect((int) pos.getX()+1, (int) pos.getY() - 7, (int) ((box.getX()-1) * ((double) this.damageReductionTimer / (double) this.damageReductionDuration)), 2);
			}
		}
	}

	@Override
	public void drawMaximumEffectBar(Graphics g) {
		Point2D.Double pos = this.getPosition();
		Point2D.Double box = this.getHitbox();
		g.setColor(Color.BLACK);
		g.drawRect((int) pos.getX(), (int) pos.getY() - 5, (int) (box.getX()), 3);
		g.setColor(new Color(255, 255, 40));
		g.fillRect((int) pos.getX()+1, (int) pos.getY() - 4, (int) ((box.getX()-1) * ((double) this.attackPower / (double) this.attackPowerCap)), 2);
	}

	/**
	 * overrides the update to add score
	 */
	@Override
	public void update() {
		super.update();
		ActorTest.score += 5;
		if(attackPower < attackPowerCap) {
			attackPower += attackPowerAcceleration;
		}
//		if (this.attackPower < attackPowerCap) {
//			if (this.readyForAttack()) {
//				this.attackPower += attackPowerAcceleration;
//				this.resetAttackCooldown();
//			}
//		}
		if (immunityTimer > 0) {
			immunityTimer -= 10;
		} else {
			if (damageReductionTimer > 0) {
				damageReductionTimer -= 10;
			}
		}
	}
}