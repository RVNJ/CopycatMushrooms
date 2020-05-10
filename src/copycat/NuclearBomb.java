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

	static private final int HEALTH = 524_288_000;
	static private final int COOL_DOWN = 0;
	static private final double SPEED = 0;
	static private final int ATTACK_DAMAGE = 1_048_576;

	public NuclearBomb(Point2D.Double startingPosition, Point2D.Double initHitbox) {// Point2D.Double
																						// startingPosition,
																						// Point2D.Double initHitbox,
																						// BufferedImage img, int
																						// health, int coolDown, double
																						// speed, int attackDamage) {
		super(startingPosition, initHitbox, IMG, HEALTH, COOL_DOWN, SPEED, ATTACK_DAMAGE);

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
		other.changeHealth(-ATTACK_DAMAGE);
		this.changeHealth(-ATTACK_DAMAGE);
	}
}