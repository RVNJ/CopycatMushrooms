package copycat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class NuclearBomb extends Actor {

	static private BufferedImage IMG;
	static {
		try {
			IMG = ImageIO.read(new File("src/copycat/actorimages/nuclearbomb.png"));
		} catch (IOException e) {
			System.out.println("you're bad");
			e.printStackTrace();
		}
	}
	
	static private final int MAXIMUM_HITPOINTS = 1_000_000_000;
	static private final int HITPOINTS = MAXIMUM_HITPOINTS;
	static private final int MAXIMUM_LIFESPAN = 60_000;
	static private final int LIFESPAN_TIMER = MAXIMUM_LIFESPAN;
	static private final int ATTACK_POWER = 1_000_000;
	static private final int ATTACK_POWER_ACCELERATION = 0;
	static private final int ATTACK_POWER_CAP = ATTACK_POWER;
	static private final int BLEED_DAMAGE = 0;
	static private final int DAMAGE_ON_DEATH = ATTACK_POWER;
	static private final int ATTACK_RANGE = 100;
	static private final int ATTACK_COOLDOWN = 0;
	static private final int ATTACK_COOLDOWN_TIMER = 0;
	static private final int STUN_DURATION = 0;
	static private final int BIND_DURATION = 0;
	static private final int HEALING = 0;
	static private final int HEALING_COOLDOWN = 0;
	static private final int IMMUNITY_DURATION = 0;
	static private final int IMMUNITY_TIMER = 0;
	static private final int DAMAGE_REDUCTION_DURATION = 0;
	static private final int DAMAGE_REDUCTION_TIMER = 0;
	static private final double DAMAGE_REDUCTION_AMOUNT = 0.00;
	static private final boolean FLYING = false;
	static private final double SPEED = 0;
	static private final double SPEED_ACCELERATION = 0;
	static private final double SPEED_ACCELERATION_CAP = 0;
	static private final int LEVEL = 1;
	static private final int COST = 50_000;

	public NuclearBomb(Point2D.Double startingPosition, Point2D.Double initHitbox) {
		super(startingPosition, initHitbox, IMG, MAXIMUM_HITPOINTS, HITPOINTS, MAXIMUM_LIFESPAN, LIFESPAN_TIMER, ATTACK_POWER,
				ATTACK_POWER_ACCELERATION, ATTACK_POWER_CAP, BLEED_DAMAGE, DAMAGE_ON_DEATH, ATTACK_RANGE,
				ATTACK_COOLDOWN, ATTACK_COOLDOWN_TIMER, STUN_DURATION, BIND_DURATION, HEALING, HEALING_COOLDOWN,
				IMMUNITY_DURATION, IMMUNITY_TIMER, DAMAGE_REDUCTION_DURATION, DAMAGE_REDUCTION_TIMER,
				DAMAGE_REDUCTION_AMOUNT, FLYING, SPEED, SPEED_ACCELERATION, SPEED_ACCELERATION_CAP, LEVEL, COST);
	};

	
	@Override
	public void drawHitpointsBar(Graphics g) {
	}
	@Override
	public void drawLifespanBar(Graphics g) {
		Point2D.Double pos = this.getPosition();
		Point2D.Double box = this.getHitbox();
		g.setColor(Color.BLACK);
		g.drawRect((int) pos.getX(), (int) pos.getY() - 6, (int) box.getX(), 4);
		g.setColor(new Color(40, 40, 255));
		g.fillRect((int) pos.getX()+1, (int) pos.getY() - 5, (int) ((box.getX()-1) * ((double)this.lifespanTimer / (double) this.maximumLifespan)), 3);
	}
	@Override
	public void drawMaximumEffectBar(Graphics g) {
	}
	

	/**
	 * destroys everything that exists on the game field, decrements its own health in addition to all plants, zombeis and neutrals
	 */
	
	@Override
	public void update() {
		super.update();
		for (Actor plant : ActorTest.plants) {
			if (!(plant instanceof Home)) {
				this.attack(plant);
			}
		}
		for (Actor zombie : ActorTest.zombies) {
			this.attack(zombie);
		}
		for (Actor neutral : ActorTest.neutrals) {
			if (!(neutral instanceof NuclearBomb) && !(neutral instanceof Poison)) {
				this.attack(neutral);
			}
		}
		this.changeLifespan(-100);
	}

	/**
	 * overrides the attack method to be allowed to attack anything regardless of its collision status
	 */
	@Override
	public void attack(Actor other) {
		other.changeHitpoints(-attackPower);
	}
}