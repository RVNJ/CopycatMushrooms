package copycat;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class WeakBiohazard extends Actor {

	static private BufferedImage IMG;
	static {
		try {
			IMG = ImageIO.read(new File("src/copycat/actorimages/orangebiohazard.png"));
		} catch (IOException e) {
			System.out.println("you're bad");
			e.printStackTrace();
		}
	}

	/**
	 * weaker biohazard than normal
	 */
	static private final int HEALTH = 20;
	static private final int COOL_DOWN = 50;
	static private final double SPEED = -0.4;
	static private final int ATTACK_DAMAGE = 24;

	public WeakBiohazard(Point2D.Double startingPosition, Point2D.Double initHitbox) {// Point2D.Double
																						// startingPosition,
																						// Point2D.Double initHitbox,
																						// BufferedImage img, int
																						// health, int coolDown, double
																						// speed, int attackDamage) {
		super(startingPosition, initHitbox, IMG, HEALTH, COOL_DOWN, SPEED, ATTACK_DAMAGE);

	};

	/**
	 * overrides the update to only attack plants and neutrals that are not of the type Poison or NuclearBomb, increments score by 100 when it is killed
	 */
	
	@Override
	public void update() {
		super.update();
		if(isAlive()) {
			for(Actor plant : ActorTest.plants) {
				this.attack(plant);
			}
			for(Actor neutral : ActorTest.neutrals) {
				if(!(neutral instanceof Poison) && !(neutral instanceof NuclearBomb)) {
					this.attack(neutral);
				}
			}
		} else if (!isAlive()) {
			ActorTest.score += 100;
		}
	}
}