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

	static private final int HEALTH = 200;
	static private final int COOL_DOWN = 5;
	static private final double SPEED = -0.2;
	static private final int ATTACK_DAMAGE = 1;

	public HealerBiohazard(Point2D.Double startingPosition, Point2D.Double initHitbox) {// Point2D.Double
																						// startingPosition,
																						// Point2D.Double initHitbox,
																						// BufferedImage img, int
																						// health, int coolDown, double
																						// speed, int attackDamage) {
		super(startingPosition, initHitbox, IMG, HEALTH, COOL_DOWN, SPEED, ATTACK_DAMAGE);

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
					if (readyForAction()) {
						if (health <= (fullHealth - 2)) {
							changeHealth(2);
						}
						if(zombie.health <= (zombie.fullHealth - 5)) {
							zombie.changeHealth(5);
						}
						for (Actor plant : ActorTest.plants) {
							this.attack(plant);
						}
						for (Actor neutral : ActorTest.neutrals) {
							if (!(neutral instanceof Poison) && !(neutral instanceof NuclearBomb)) {
								this.attack(neutral);
							}
						}
						this.resetCoolDown();
					}
				}
			}			
		} else if (!isAlive()) {
			ActorTest.score += 500;
		}
	}
}