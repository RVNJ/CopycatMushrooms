package copycat;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Poison extends Actor {

	static private BufferedImage IMG;
	static {
		try {
			IMG = ImageIO.read(new File("src/copycat/actorimages/poison.png"));
		} catch (IOException e) {
			System.out.println("you're bad");
			e.printStackTrace();
		}
	}

	static private final int HEALTH = 10000;
	static private final int COOL_DOWN = 5;
	static private final double SPEED = 0;
	static private final int ATTACK_DAMAGE = 5;

	public Poison(Point2D.Double startingPosition, Point2D.Double initHitbox) {// Point2D.Double
																				// startingPosition,
																				// Point2D.Double initHitbox,
																				// BufferedImage img, int
																				// health, int coolDown, double
																				// speed, int attackDamage) {
		super(startingPosition, initHitbox, IMG, HEALTH, COOL_DOWN, SPEED, ATTACK_DAMAGE);
			
	};

	/**
	 * damages all plants and zombies, does not affect any neutrals except for itself
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
			if (!(neutral instanceof NuclearBomb) && !(neutral instanceof Fence) && !(neutral instanceof Fertilizer) && !(neutral instanceof Resource)) {
				this.attack(neutral);
			}
		}
	}

	/**
	 * overrides the attack method to allow it to attack everything without needing to collide first
	 */
	@Override
	public void attack(Actor other) {
		other.changeHealth(-ATTACK_DAMAGE);
		this.changeHealth(-ATTACK_DAMAGE);
	}
}