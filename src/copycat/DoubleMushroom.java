//not used

package copycat;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class DoubleMushroom extends Actor {

	public ArrayList<Actor> mushroomsToAdd = new ArrayList<>();
	static private BufferedImage IMG;

	static {
		try {
			IMG = ImageIO.read(new File("src/copycat/actorimages/doublemushroom.png"));
		} catch (IOException e) {
			System.out.println("you're bad");
			e.printStackTrace();
		}
	}

	static private final int HEALTH = 1;
	static private final int COOL_DOWN = 100;
	static private final double SPEED = 0;
	static private final int ATTACK_DAMAGE = 0;

	public DoubleMushroom(Point2D.Double startingPosition, Point2D.Double initHitbox) {// Point2D.Double
																						// startingPosition,
																						// Point2D.Double initHitbox,
																						// BufferedImage img, int
																						// health, int coolDown, double
																						// speed, int attackDamage) {
		super(startingPosition, initHitbox, IMG, HEALTH, COOL_DOWN, SPEED, ATTACK_DAMAGE);

	};

	/**
	 * overrides the update to spawn red and/or green mushrooms that collide with it
	 */
	//disabled for presentability
	@Override
	public void update() {
		super.update();
		RedMushroom redmushroom = null;
		GreenMushroom greenmushroom = null;
		for (Actor plant : ActorTest.plants) {
			if (this.isCollidingOther(plant)) {
				this.resetCoolDown();
				System.out.println(this.readyForAction());
				if (plant instanceof RedMushroom) {
					redmushroom = new RedMushroom(getPosition(), plant.hitbox);
					mushroomsToAdd.add(redmushroom);
				}
				if (plant instanceof GreenMushroom) {
					greenmushroom = new GreenMushroom(getPosition(), plant.hitbox);
					mushroomsToAdd.add(greenmushroom);
				}
			}
		}
	}
}
//			if (this.isCollidingOther(plant)) {
//				if (readyForAction()) {

//					resetCoolDown();
//				}
//			}
//		}
//		System.out.println("are you ready for this");

//		System.out.println("update double mushroom");
//		if(redmushroom != null) {
//			mushroomsToAdd.add(redmushroom);
//		}
//		if(greenmushroom != null) {
//			mushroomsToAdd.add(greenmushroom);
//		}
//	}
//	System.out.println("damage: "+redmushroom.attackDamage+" | speed: "+redmushroom.speed+" | health: "+redmushroom.health+"/"+redmushroom.fullHealth);
//}