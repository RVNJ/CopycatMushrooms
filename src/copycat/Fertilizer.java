package copycat;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Fertilizer extends Actor {

	static private BufferedImage IMG;
	static int fertilizerCount = 0;
	
	static {
		try {
			IMG = ImageIO.read(new File("src/copycat/actorimages/fertilizer.png"));
		} catch (IOException e) {
			System.out.println("you're bad");
			e.printStackTrace();
		}
	}

	static private final int HEALTH = 400;
	static private final int COOL_DOWN = 0;
	static private final double SPEED = 0;
	static private final int ATTACK_DAMAGE = 0;

	public Fertilizer(Point2D.Double startingPosition, Point2D.Double initHitbox) {// Point2D.Double
																						// startingPosition,
																						// Point2D.Double initHitbox,
																						// BufferedImage img, int
																						// health, int coolDown, double
																						// speed, int attackDamage) {
		super(startingPosition, initHitbox, IMG, HEALTH, COOL_DOWN, SPEED, ATTACK_DAMAGE);
		
	};

	/**
	 * overrides the update to set the chance of spawning a gold coin resource according the number of
	 * fertilizer bags on the game field
	 */
	@Override
	public void update() {
		super.update();
		ActorTest.resourceSpawnChanceMultiplier = 1 - (0.002 * fertilizerCount);
	}
}