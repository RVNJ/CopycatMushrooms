package copycat;

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

	static private final int HEALTH = 1000;
	static private final int COOL_DOWN = 2;
	static private final double SPEED = 0;
	static private final int ATTACK_DAMAGE = 0;

	public BlueMushroom(Point2D.Double startingPosition, Point2D.Double initHitbox) {// Point2D.Double
																						// startingPosition,
																						// Point2D.Double initHitbox,
																						// BufferedImage img, int
																						// health, int coolDown, double
																						// speed, int attackDamage) {
		super(startingPosition, initHitbox, IMG, HEALTH, COOL_DOWN, SPEED, ATTACK_DAMAGE);

	};

	/**
	 * overrides the update to add score
	 */
	//disabled for presentability
	@Override
	public void update() {
		super.update();
		ActorTest.score += 5;
	}
}