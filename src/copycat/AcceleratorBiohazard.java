package copycat;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AcceleratorBiohazard extends Actor {

	static private BufferedImage IMG;
	static {
		try {
			IMG = ImageIO.read(new File("src/copycat/actorimages/redbiohazard.png"));
		} catch (IOException e) {
			System.out.println("you're bad");
			e.printStackTrace();
		}
	}

	static private final int HEALTH = 400;
	static private final int COOL_DOWN = 20;
	static private final double SPEED = -0.5;
	static private final int ATTACK_DAMAGE = 200;

	public AcceleratorBiohazard(Point2D.Double startingPosition, Point2D.Double initHitbox) {// Point2D.Double
																						// startingPosition,
																						// Point2D.Double initHitbox,
																						// BufferedImage img, int
																						// health, int coolDown, double
																						// speed, int attackDamage) {
		super(startingPosition, initHitbox, IMG, HEALTH, COOL_DOWN, SPEED, ATTACK_DAMAGE);

	};
	
	@Override
	public void update() {
		super.update();
		if(this.isAlive()) {
			for(Actor plant : ActorTest.plants) {
				this.attack(plant);
			}
			for(Actor neutral : ActorTest.neutrals) {
				if(!(neutral instanceof Poison) && !(neutral instanceof NuclearBomb)) {
					this.attack(neutral);
				}
			}
			if(this.speed > -4) {
				if(this.readyForAction()) {
					this.speed -= 0.1;
					this.resetCoolDown();
				}
			}
		} else if (!this.isAlive()) {
			ActorTest.score += 1000;
		}
	}
	
	@Override
	public void attack(Actor other) {
		if (this != other && this.isCollidingOther(other)) {
			setColliding(true);
			this.speed = -0.05;
			if (this.readyForAction()) {
				other.changeHealth(-attackDamage);
				this.resetCoolDown();
			}
		}
	}
}