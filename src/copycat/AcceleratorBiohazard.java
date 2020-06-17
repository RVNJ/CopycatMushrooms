package copycat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AcceleratorBiohazard extends Actor {

	static private BufferedImage IMG;
	static {
		try {
			IMG = ImageIO.read(new File("src/copycat/actorimages/redbiohazard.png"));
		} catch (IOException e) {
			System.out.println("you're bad");
			e.printStackTrace();
		}
	}

	static private final int MAXIMUM_HITPOINTS = 4_000;
	static private final int HITPOINTS = MAXIMUM_HITPOINTS;
	static private final int MAXIMUM_LIFESPAN = 999_999_999;
	static private final int LIFESPAN_TIMER = MAXIMUM_LIFESPAN;
	static private final int ATTACK_POWER = 200;
	static private final int ATTACK_POWER_ACCELERATION = 10;
	static private final int ATTACK_POWER_CAP = 1_000;
	static private final int BLEED_DAMAGE = 1;
	static private final int DAMAGE_ON_DEATH = 0;
	static private final int ATTACK_RANGE = 1;
	static private final int ATTACK_COOLDOWN = 10;
	static private final int ATTACK_COOLDOWN_TIMER = 0;
	static private final int STUN_DURATION = 0;
	static private final int BIND_DURATION = 0;
	static private final int HEALING = 0;
	static private final int HEALING_COOLDOWN = 0;
	static private final int IMMUNITY_DURATION = 0;
	static private final int IMMUNITY_TIMER = 0;
	static private final int DAMAGE_REDUCTION_DURATION = 0;
	static private final int DAMAGE_REDUCTION_TIMER = DAMAGE_REDUCTION_DURATION;
	static private final double DAMAGE_REDUCTION_AMOUNT = 0.00;
	static private final boolean FLYING = false;
	static private final double SPEED = 0;
	static private final double SPEED_ACCELERATION = -0.025;
	static private final double SPEED_ACCELERATION_CAP = -2;
	static private final int LEVEL = 1;
	static private final int COST = 400;

	public AcceleratorBiohazard(Point2D.Double startingPosition, Point2D.Double initHitbox) {
		super(startingPosition, initHitbox, IMG, MAXIMUM_HITPOINTS, HITPOINTS, MAXIMUM_LIFESPAN, LIFESPAN_TIMER,
				ATTACK_POWER, ATTACK_POWER_ACCELERATION, ATTACK_POWER_CAP, BLEED_DAMAGE, DAMAGE_ON_DEATH, ATTACK_RANGE,
				ATTACK_COOLDOWN, ATTACK_COOLDOWN_TIMER, STUN_DURATION, BIND_DURATION, HEALING, HEALING_COOLDOWN,
				IMMUNITY_DURATION, IMMUNITY_TIMER, DAMAGE_REDUCTION_DURATION, DAMAGE_REDUCTION_TIMER,
				DAMAGE_REDUCTION_AMOUNT, FLYING, SPEED, SPEED_ACCELERATION, SPEED_ACCELERATION_CAP, LEVEL, COST);
	};

	@Override
	public void drawHitpointsBar(Graphics g) {
		Point2D.Double pos = this.getPosition();
		Point2D.Double box = this.getHitbox();
		g.setColor(Color.BLACK);
		g.drawRect((int) pos.getX(), (int) pos.getY() - 10, (int) box.getX(), 4);
		g.setColor(new Color(255, 40, 40));
		g.fillRect((int) pos.getX()+1, (int) pos.getY() - 9, (int) ((box.getX()-1) * ((double) this.hitpoints / (double) this.maximumHitpoints)), 3);
	}

	@Override
	public void drawLifespanBar(Graphics g) {
	}

	@Override
	public void drawMaximumEffectBar(Graphics g) {
		Point2D.Double pos = this.getPosition();
		Point2D.Double box = this.getHitbox();
		g.setColor(Color.BLACK);
		g.drawRect((int) pos.getX(), (int) pos.getY() - 6, (int) box.getX(), 4);
		g.setColor(new Color(255, 255, 40));
//		g.fillRect((int) pos.getX()+1, (int) pos.getY() - 5, (int) ((box.getX()-1) * ((double) this.attackPower / (double) this.attackPowerCap)), 3);
		g.fillRect((int) pos.getX()+1, (int) pos.getY() - 5, (int) ((box.getX()-1) * ((double) this.speed / (double) this.speedAccelerationCap)), 3);
	}

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
			if (this.readyForAttack()) {
				if (this.speed > -1) {
					this.speed -= 0.1;
				}
				if (this.attackPower < attackPowerCap) {
					this.attackPower += attackPowerAcceleration;
				}
				this.resetAttackCooldown();
			}
		} else if (!this.isAlive()) {
			ActorTest.score += 1000;
		}
	}

	@Override
	public void attack(Actor other) {
		if (this != other && this.isCollidingOther(other)) {
			setColliding(true);
			attackLogic(other);
			this.speed = -0.1;
			this.attackPower = 200;
		}
	}
}