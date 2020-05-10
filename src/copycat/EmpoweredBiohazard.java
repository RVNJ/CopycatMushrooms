package copycat;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;

public class EmpoweredBiohazard extends Actor {

	static private BufferedImage IMG;
	static {
		try {
			IMG = ImageIO.read(new File("src/copycat/actorimages/blackbiohazard.png"));
		} catch (IOException e) {
			System.out.println("you're bad");
			e.printStackTrace();
		}
	}

	static private final int HEALTH = 4000;
	static private final int COOL_DOWN = 20;
	static private final double SPEED = -0.1;
	static private final int ATTACK_DAMAGE = 200;

	public EmpoweredBiohazard(Point2D.Double startingPosition, Point2D.Double initHitbox) {// Point2D.Double
																						// startingPosition,
																						// Point2D.Double initHitbox,
																						// BufferedImage img, int
																						// health, int coolDown, double
																						// speed, int attackDamage) {
		super(startingPosition, initHitbox, IMG, HEALTH, COOL_DOWN, SPEED, ATTACK_DAMAGE);

	};

	/**
	 * overrides drawHealthBar method to draw a larger health bar
	 */
	@Override
	public void drawHealthBar(Graphics g) {
		Point2D.Double pos = this.getPosition();
		Point2D.Double box = this.getHitbox();
	    g.setColor(Color.BLACK);  
		g.drawRect((int)pos.getX() - 5,(int) pos.getY() - 5, (int)(box.getX()+9), 3);  
	    g.setColor(new Color(191,191,191));  
		g.fillRect((int)pos.getX() - 5,(int) pos.getY() - 5, (int)((box.getX()+9) * this.health / (double)this.fullHealth), 3);
	}

	/**
	 * overrides the update to increase its damage per cooldown time by 100, up to a maximum of 2000, only attacks plants and
	 * neutrals not of type Poison or NuclearBomb, when it dies, increases the score by 10000
	 */
	@Override
	public void update() {
		super.update();
		if(this.attackDamage < 2000) {
			if(this.readyForAction()) {
				this.attackDamage += 100;
				this.resetCoolDown();
			}
		}
		if(this.isAlive()) {
			for(Actor plant : ActorTest.plants) {
				this.attack(plant);
			}
			for(Actor neutral : ActorTest.neutrals) {
				if(!(neutral instanceof Poison) && !(neutral instanceof NuclearBomb)) {
					this.attack(neutral);
				}
			}
		} else if (!this.isAlive()) {
			ActorTest.score += 10000;
		}
	}
}