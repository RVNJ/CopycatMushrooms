//not used

package copycat;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HealerBiohazard extends Actor {

	static private BufferedImage IMG;
	static {
		try {
			IMG = ImageIO.read(new File("src/copycat/actorimages/bluebiohazard.png"));
		} catch (IOException e) {
			System.out.println("you're bad");
			e.printStackTrace();
		}
	}

	static private final int MAXIMUM_HITPOINTS = 4_000;
	static private final int HITPOINTS = MAXIMUM_HITPOINTS;
	static private final int MAXIMUM_LIFESPAN = 120_000;
	static private final int LIFESPAN_TIMER = MAXIMUM_LIFESPAN;
	static private final int ATTACK_POWER = 100;
	static private final int ATTACK_POWER_ACCELERATION = 0;
	static private final int ATTACK_POWER_CAP = ATTACK_POWER;
	static private final int BLEED_DAMAGE = 0;
	static private final int DAMAGE_ON_DEATH = -100;
	static private final int ATTACK_RANGE = 1;
	static private final int ATTACK_COOLDOWN = 50;
	static private final int ATTACK_COOLDOWN_TIMER = 0;
	static private final int STUN_DURATION = 0;
	static private final int BIND_DURATION = 0;
	static private final int HEALING = 40;
	static private final int HEALING_COOLDOWN = 2_000;
	static private final int IMMUNITY_DURATION = 0;
	static private final int IMMUNITY_TIMER = 0;
	static private final int DAMAGE_REDUCTION_DURATION = 20_000;
	static private final int DAMAGE_REDUCTION_TIMER = DAMAGE_REDUCTION_DURATION;
	static private final double DAMAGE_REDUCTION_AMOUNT = 50.00;
	static private final boolean FLYING = false;
	static private final double SPEED = -0.1;
	static private final double SPEED_ACCELERATION = 0;
	static private final double SPEED_ACCELERATION_CAP = SPEED;
	static private final int LEVEL = 1;
	static private final int COST = 0;

	public HealerBiohazard(Point2D.Double startingPosition, Point2D.Double initHitbox) {
		super(startingPosition, initHitbox, IMG, MAXIMUM_HITPOINTS, HITPOINTS, MAXIMUM_LIFESPAN, LIFESPAN_TIMER, ATTACK_POWER,
				ATTACK_POWER_ACCELERATION, ATTACK_POWER_CAP, BLEED_DAMAGE, DAMAGE_ON_DEATH, ATTACK_RANGE,
				ATTACK_COOLDOWN, ATTACK_COOLDOWN_TIMER, STUN_DURATION, BIND_DURATION, HEALING, HEALING_COOLDOWN,
				IMMUNITY_DURATION, IMMUNITY_TIMER, DAMAGE_REDUCTION_DURATION, DAMAGE_REDUCTION_TIMER,
				DAMAGE_REDUCTION_AMOUNT, FLYING, SPEED, SPEED_ACCELERATION, SPEED_ACCELERATION_CAP, LEVEL, COST);
	};

	/**
	 * overrides the update to only attack plants and neutrals that are not of the type Poison or NuclearBomb, changes its health
	 * by adding +2 to itself when it is below full health and changes zombies' health by +5 when the zombie is below full health,
	 * increases score by 500 when it is killed
	 */
	@Override
	public void update() {
		super.update();
		if (isAlive()) {
			for (Actor zombie : ActorTest.zombies) {
				if (isCollidingOther(zombie)) {
					if (readyForAttack()) {
						if (hitpoints <= (maximumHitpoints - 2)) {
							changeHitpoints(2);
						}
						if(zombie.hitpoints <= (zombie.maximumHitpoints - 5)) {
							zombie.changeHitpoints(5);
						}
						for (Actor plant : ActorTest.plants) {
							this.attack(plant);
						}
						for (Actor neutral : ActorTest.neutrals) {
							if (!(neutral instanceof Poison) && !(neutral instanceof NuclearBomb)) {
								this.attack(neutral);
							}
						}
						this.resetAttackCooldown();
					}
				}
			}			
		} else if (!isAlive()) {
			ActorTest.score += 500;
		}
	}
}