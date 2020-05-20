package copycat;

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
	
	static private final int MAXIMUM_HITPOINTS = 999_999_999;
	static private final int HITPOINTS = MAXIMUM_HITPOINTS;
	static private final int LIFESPAN_TIMER = 30_000;
	static private final int ATTACK_POWER = 999_999_999;
	static private final int ATTACK_POWER_ACCELERATION = 0;
	static private final int ATTACK_POWER_CAP = ATTACK_POWER;
	static private final int BLEED_DAMAGE = 0;
	static private final int DAMAGE_ON_DEATH = ATTACK_POWER;
	static private final int ATTACK_RANGE = 100;
	static private final int ATTACK_COOLDOWN = 20;
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
	static private final int COST = 100_000;

	public NuclearBomb(Point2D.Double startingPosition, Point2D.Double initHitbox) {// Point2D.Double
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
			if (!(neutral instanceof Poison)) {
				this.attack(neutral);
			}
		}
	}

	/**
	 * overrides the attack method to be allowed to attack anything regardless of its collision status
	 */
	@Override
	public void attack(Actor other) {
		other.changeHitpoints(-attackPower);
		this.changeHitpoints(-attackPower);
	}
}