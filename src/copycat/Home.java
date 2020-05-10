package copycat;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Home extends Actor {

	static private BufferedImage IMG;
	static {
		try {
			IMG = ImageIO.read(new File("src/copycat/actorimages/goldenmushroom.png"));
		} catch (IOException e) {
			System.out.println("you're bad");
			e.printStackTrace();
		}
	}

	static private final int HEALTH = 1;
	static private final int COOL_DOWN = 0;
	static private final double SPEED = 0;
	static private final int ATTACK_DAMAGE = 0;

	public Home(Point2D.Double startingPosition, Point2D.Double initHitbox) {// Point2D.Double
																						// startingPosition,
																						// Point2D.Double initHitbox,
																						// BufferedImage img, int
																						// health, int coolDown, double
																						// speed, int attackDamage) {
		super(startingPosition, initHitbox, IMG, HEALTH, COOL_DOWN, SPEED, ATTACK_DAMAGE);
	};

	/**
	 * overrides the update to print the final score and close the game when it is killed by a zombie
	 */
	@Override
	public void update() {
		super.update();
		if(!isAlive()) {
			System.out.println("Game Over! Final score: "+ActorTest.score);
			System.exit(0);
		}
	}
	
}