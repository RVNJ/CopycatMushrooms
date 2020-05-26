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

	static private final int MAXIMUM_HITPOINTS = 100;
	static private final int HITPOINTS = MAXIMUM_HITPOINTS;
	static private final int MAXIMUM_LIFESPAN = 999_999_999;
	static private final int LIFESPAN_TIMER = MAXIMUM_LIFESPAN;
	static private final int ATTACK_POWER = 25;
	static private final int ATTACK_POWER_ACCELERATION = 1;
	static private final int ATTACK_POWER_CAP = 50;
	static private final int BLEED_DAMAGE = 0;
	static private final int DAMAGE_ON_DEATH = 0;
	static private final int ATTACK_RANGE = 1;
	static private final int ATTACK_COOLDOWN = 25;
	static private final int ATTACK_COOLDOWN_TIMER = 0;
	static private final int STUN_DURATION = 0;
	static private final int BIND_DURATION = 0;
	static private final int HEALING = 0;
	static private final int HEALING_COOLDOWN = 0;
	static private final int IMMUNITY_DURATION = 10_000;
	static private final int IMMUNITY_TIMER = 0;
	static private final int DAMAGE_REDUCTION_DURATION = 0;
	static private final int DAMAGE_REDUCTION_TIMER = DAMAGE_REDUCTION_DURATION;
	static private final double DAMAGE_REDUCTION_AMOUNT = 0.00;
	static private final boolean FLYING = false;
	static private final double SPEED = 1;
	static private final double SPEED_ACCELERATION = 0;
	static private final double SPEED_ACCELERATION_CAP = 1;
	static private final int LEVEL = 1;
	static private final int COST = 2_000;

	public DoubleMushroom(Point2D.Double startingPosition, Point2D.Double initHitbox) {
		super(startingPosition, initHitbox, IMG, MAXIMUM_HITPOINTS, HITPOINTS, MAXIMUM_LIFESPAN, LIFESPAN_TIMER, ATTACK_POWER,
				ATTACK_POWER_ACCELERATION, ATTACK_POWER_CAP, BLEED_DAMAGE, DAMAGE_ON_DEATH, ATTACK_RANGE,
				ATTACK_COOLDOWN, ATTACK_COOLDOWN_TIMER, STUN_DURATION, BIND_DURATION, HEALING, HEALING_COOLDOWN,
				IMMUNITY_DURATION, IMMUNITY_TIMER, DAMAGE_REDUCTION_DURATION, DAMAGE_REDUCTION_TIMER,
				DAMAGE_REDUCTION_AMOUNT, FLYING, SPEED, SPEED_ACCELERATION, SPEED_ACCELERATION_CAP, LEVEL, COST);
	};

	
	@Override
	public void update() {
		super.update();
		RedMushroom redmushroom = null;
		GreenMushroom greenmushroom = null;
		for (Actor plant : ActorTest.plants) {
			if (this.isCollidingOther(plant)) {
				this.resetAttackCooldown();
//				System.out.println(this.readyForAttack());
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