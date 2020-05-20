package copycat;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GreenMushroom extends Actor {

	static private BufferedImage IMG;
	static {
		try {
			IMG = ImageIO.read(new File("src/copycat/actorimages/greenmushroom.png"));
		} catch (IOException e) {
			System.out.println("you're bad");
			e.printStackTrace();
		}
	}

	/**
	 * stronger, faster mushroom with higher health and a longer cooldown than the red mushroom
	 */
	static private final int MAXIMUM_HITPOINTS = 400;
	static private final int HITPOINTS = MAXIMUM_HITPOINTS;
	static private final int LIFESPAN_TIMER = 999_999_999;
	static private final int ATTACK_POWER = 100;
	static private final int ATTACK_POWER_ACCELERATION = 1;
	static private final int ATTACK_POWER_CAP = 200;
	static private final int BLEED_DAMAGE = 2;
	static private final int DAMAGE_ON_DEATH = 10;
	static private final int ATTACK_RANGE = 1;
	static private final int ATTACK_COOLDOWN = 25;
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
	static private final double SPEED = 2;
	static private final double SPEED_ACCELERATION = 0;
	static private final double SPEED_ACCELERATION_CAP = 2;
	static private final int LEVEL = 1;
	static private final int COST = 2_000;

	public GreenMushroom(Point2D.Double startingPosition, Point2D.Double initHitbox) {// Point2D.Double
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
}