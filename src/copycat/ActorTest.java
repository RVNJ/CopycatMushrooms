package copycat;

//imports
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;//Color;
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.EventQueue;
//import java.awt.FlowLayout;
import java.awt.geom.*;//Point2D;
//import java.awt.geom.Point2D.Double;
//import java.awt.Graphics;
//import java.awt.GridLayout;
//import java.awt.Image;
//import java.awt.LayoutManager;
//import java.awt.Point;
//import java.awt.image.BufferedImage;
//import java.awt.Toolkit;
//import java.awt.Window;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javax.swing.*;//BoxLayout;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JLayeredPane;
//import javax.swing.JPanel;
//import javax.swing.SwingConstants;
//import javax.swing.Timer;
//import javax.swing.event.ListSelectionEvent;

import java.util.ArrayList;
import java.util.Random;

public class ActorTest extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
//	declares the images to be buffered for the game
	static BufferedImage biohazardImage;
	static BufferedImage blueMushroomImage;
	static BufferedImage doubleMushroomImage;
	static BufferedImage fertilizerImage;
	static BufferedImage greenBiohazardImage;
	static BufferedImage greenMushroomImage;
	static BufferedImage goldenMushroomImage;
	static BufferedImage nuclearBombImage;
	static BufferedImage placeholderImage;
	static BufferedImage poisonImage;
	static BufferedImage redMushroomImage;
	static BufferedImage resourceImage;
	static BufferedImage fenceImage;
	static BufferedImage blackBiohazardImage;
	static BufferedImage redBiohazardImage;
	static BufferedImage blueBiohazardImage;
	static BufferedImage orangeBiohazardImage;
	static BufferedImage threeByThreeAreaOfEffectImage;

	private Timer timer;
	public static ArrayList<Actor> plants;
	public static ArrayList<Actor> zombies;
	public static ArrayList<Actor> neutrals;
	private Random rand;
	static JPanel plantSelect;
	static JPanel scorePanel;
	static JLabel scoreLabel;
	static JLabel difficultyLabel;
	JLabel mouseCoordinates;
	static int mouseClickState = 0;
	static int score = 0;
//	sets the game dimensions
	static int xDimension = 1000;//1000;//500;
	static int yDimension = 600;//600;//300;
//	sets the game's grid width and height
	static int gridWidth = 100;//100;//50;
	static int gridHeight = 100;//100;//50;
//	initializes the difficulty and resource spawn chance
	static int difficultyLevel = 1;
	public static double resourceSpawnChanceMultiplier = 1;
//	declares the image offsets
	static int goldenMushroomXOffset;
	static int goldenMushroomYOffset;
	static int biohazardXOffset;
	static int biohazardYOffset;
	static int greenBiohazardXOffset;
	static int greenBiohazardYOffset;
	static int nuclearBombXOffset;
	static int nuclearBombYOffset;
	static int resourceXOffset;
	static int resourceYOffset;
	static int blackBiohazardXOffset;
	static int blackBiohazardYOffset;
	static int redBiohazardXOffset;
	static int redBiohazardYOffset;
	static int blueBiohazardXOffset;
	static int blueBiohazardYOffset;
	static int orangeBiohazardXOffset;
	static int orangeBiohazardYOffset;
	static int threeByThreeAreaOfEffectXOffset;
	static int threeByThreeAreaOfEffectYOffset;
	
	static boolean debugEnabled = true;//false;

	/**
	 * delcares the image locations both contained in the filesystem and their
	 * offsets when spawned in the game
	 */
	static {
		try {
			redMushroomImage = ImageIO.read(new File("src/copycat/actorimages/redmushroom.png"));
			greenMushroomImage = ImageIO.read(new File("src/copycat/actorimages/greenmushroom.png"));
			blueMushroomImage = ImageIO.read(new File("src/copycat/actorimages/bluemushroom.png"));
			doubleMushroomImage = ImageIO.read(new File("src/copycat/actorimages/doublemushroom.png"));
			fertilizerImage = ImageIO.read(new File("src/copycat/actorimages/fertilizer.png"));
			fenceImage = ImageIO.read(new File("src/copycat/actorimages/fence.png"));
			poisonImage = ImageIO.read(new File("src/copycat/actorimages/poison.png"));
			nuclearBombImage = ImageIO.read(new File("src/copycat/actorimages/nuclearbomb.png"));
			
			threeByThreeAreaOfEffectImage = ImageIO.read(new File("src/copycat/actorimages/3x3aoe.png"));
			threeByThreeAreaOfEffectXOffset = (int) (double) (0.5 * (gridWidth - threeByThreeAreaOfEffectImage.getWidth())); 
			threeByThreeAreaOfEffectYOffset = (int) (double) (0.5 * (gridHeight - threeByThreeAreaOfEffectImage.getHeight())); 

			goldenMushroomImage = ImageIO.read(new File("src/copycat/actorimages/goldenmushroom.png"));
			goldenMushroomXOffset = (int) (double) (0.5 * (gridWidth - goldenMushroomImage.getWidth()));
			goldenMushroomYOffset = (int) (double) (0.5 * (gridHeight - goldenMushroomImage.getHeight()));

			resourceImage = ImageIO.read(new File("src/copycat/actorimages/resource.png"));
			resourceXOffset = (int) (double) (0.5 * (gridWidth - resourceImage.getWidth()));
			resourceYOffset = (int) (double) (0.5 * (gridHeight - resourceImage.getHeight()));

			biohazardImage = ImageIO.read(new File("src/copycat/actorimages/biohazard.png"));
			biohazardXOffset = (int) (double) (0.5 * (gridWidth - biohazardImage.getWidth()));
			biohazardYOffset = (int) (double) (0.5 * (gridHeight - biohazardImage.getHeight()));

			greenBiohazardImage = ImageIO.read(new File("src/copycat/actorimages/greenbiohazard.png"));
			greenBiohazardXOffset = (int) (double) (0.5 * (gridWidth - greenBiohazardImage.getWidth()));
			greenBiohazardYOffset = (int) (double) (0.5 * (gridHeight - greenBiohazardImage.getHeight()));

			blueBiohazardImage = ImageIO.read(new File("src/copycat/actorimages/bluebiohazard.png"));
			blueBiohazardXOffset = (int) (double) (0.5 * (gridWidth - blueBiohazardImage.getWidth()));
			blueBiohazardYOffset = (int) (double) (0.5 * (gridHeight - blueBiohazardImage.getHeight()));

			redBiohazardImage = ImageIO.read(new File("src/copycat/actorimages/redbiohazard.png"));
			redBiohazardXOffset = (int) (double) (0.5 * (gridWidth - redBiohazardImage.getWidth()));
			redBiohazardYOffset = (int) (double) (0.5 * (gridHeight - redBiohazardImage.getHeight()));

			blackBiohazardImage = ImageIO.read(new File("src/copycat/actorimages/blackbiohazard.png"));
			blackBiohazardXOffset = (int) (double) (0.5 * (gridWidth - blackBiohazardImage.getWidth()));
			blackBiohazardYOffset = (int) (double) (0.5 * (gridHeight - blackBiohazardImage.getHeight()));

			orangeBiohazardImage = ImageIO.read(new File("src/copycat/actorimages/orangebiohazard.png"));
			orangeBiohazardXOffset = (int) (double) (0.5 * (gridWidth - orangeBiohazardImage.getWidth()));
			orangeBiohazardYOffset = (int) (double) (0.5 * (gridHeight - orangeBiohazardImage.getHeight()));
		} catch (IOException e) {
			System.out.println("one or more images was not found");
			System.exit(0);
		}

	}

	/**
	 * initializes the constructor by setting the game size, declaring the
	 * arraylists used, spawning the Home subclasses and a single coin, initializes
	 * the timer and adds the mouse listener
	 */
	public ActorTest() {

		System.out.println("Hello World.");

		setPreferredSize(new Dimension(xDimension, yDimension));

		rand = new Random();

		plants = new ArrayList<>();
		zombies = new ArrayList<>();
		neutrals = new ArrayList<>();

//not necessary for this file
//		for (int i = 0; i < 5; i++) {
//			Home home = new Home(new Point2D.Double(goldenMushroomXOffset, Math.floor(i * 50) + 50 + goldenMushroomYOffset), new Point2D.Double(goldenMushroomImage.getWidth(), goldenMushroomImage.getHeight()));
//			plants.add(home);
//		}

		Resource coin = new Resource(new Point2D.Double((Math.floor((Math.random() * (xDimension-(3*gridWidth))) / gridWidth) * gridWidth) + gridWidth + resourceXOffset, (Math.floor((Math.random() * (yDimension-gridHeight)) / gridHeight) * gridHeight + gridHeight) + resourceYOffset), new Point2D.Double(resourceImage.getWidth(), resourceImage.getHeight()));
		neutrals.add(coin);

		timer = new Timer(25, this);
		timer.start();

		addMouseListener(this);

	}

	/**
	 * re-draws any changing components
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int i = 1;
		int j = 1;
		for (i = 0; i <= (xDimension / gridWidth); i++) {
			for (j = 1; j <= (yDimension / gridHeight); j++) {
				g.drawLine(i * gridWidth, j * gridHeight, i * gridWidth, yDimension);
				g.drawLine(i * gridWidth, j * gridHeight, xDimension, j * gridHeight);
			}
		}
		for (Actor plant : plants) {
			plant.draw(g, 0);
			plant.drawHitpointsBar(g);
			plant.drawLifespanBar(g);
			plant.drawMaximumEffectBar(g);
		}
		for (Actor zombie : zombies) {
			zombie.draw(g, 0);
			zombie.drawHitpointsBar(g);
			zombie.drawLifespanBar(g);
			zombie.drawMaximumEffectBar(g);
			
		}
		for (Actor neutral : neutrals) {
			neutral.draw(g, 0);
			neutral.drawHitpointsBar(g);
			neutral.drawLifespanBar(g);
			neutral.drawMaximumEffectBar(g);
		}
	}

	/**
	 * runs for every iteration of the game loop
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int row = (rand.nextInt(5) + 1);
		int col = (rand.nextInt(2) + 8);
		int x = (col * gridWidth);
		int y = (row * gridHeight);

		int resourceSpawnChance = rand.nextInt(10000);
		int zombieSpawnChance = rand.nextInt(10000);

		ArrayList<Actor> newPlants = new ArrayList<>();
		ArrayList<Actor> newZombies = new ArrayList<>();
		ArrayList<Actor> newNeutrals = new ArrayList<>();

		if (debugEnabled) {
//			if (zombieSpawnChance >= (9920)) {
//				Biohazard biohazard = new Biohazard(
//						new Point2D.Double(x + blackBiohazardXOffset, y + blackBiohazardYOffset),
//						new Point2D.Double(blackBiohazardImage.getWidth(), blackBiohazardImage.getHeight()));
//				zombies.add(biohazard);
//			}
			if (zombieSpawnChance >= (9995)) {
				EmpoweredBiohazard empoweredBiohazard = new EmpoweredBiohazard(new Point2D.Double(x + blackBiohazardXOffset, y + blackBiohazardYOffset), new Point2D.Double(blackBiohazardImage.getWidth(), blackBiohazardImage.getHeight()));
				zombies.add(empoweredBiohazard);
			}
		} else {
//		spawns a resource using a multiplier that can be modified by spreading fertilizer
			if (resourceSpawnChance >= (resourceSpawnChanceMultiplier * 9990)) {
				Resource coin = new Resource(new Point2D.Double((Math.floor((Math.random() * (xDimension-(3*gridWidth))) / gridWidth) * gridWidth) + gridWidth + resourceXOffset, (Math.floor((Math.random() * (yDimension-gridHeight)) / gridHeight) * gridHeight + gridHeight) + resourceYOffset), new Point2D.Double(resourceImage.getWidth(), resourceImage.getHeight()));
				neutrals.add(coin);
			}
//		spawns biohazards based on the random number, then modified by the current difficulty, shifting down if too high for the current score/difficulty
			if (zombieSpawnChance >= (9800 + (200 - (difficultyLevel * 10)))) {
				WeakBiohazard weakbiohazard = new WeakBiohazard(
						new Point2D.Double(x + orangeBiohazardXOffset, y + orangeBiohazardYOffset),
						new Point2D.Double(orangeBiohazardImage.getWidth(), orangeBiohazardImage.getHeight()));
				zombies.add(weakbiohazard);
			} else if ((zombieSpawnChance >= (9790) && zombieSpawnChance < (9800))) {
				if (difficultyLevel >= 10) {
					StrongBiohazard strongbiohazard = new StrongBiohazard(
							new Point2D.Double(x + greenBiohazardXOffset, y + greenBiohazardYOffset),
							new Point2D.Double(greenBiohazardImage.getWidth(), greenBiohazardImage.getHeight()));
					zombies.add(strongbiohazard);
				} else {
					Biohazard biohazard = new Biohazard(new Point2D.Double(x + biohazardXOffset, y + biohazardYOffset),
							new Point2D.Double(biohazardImage.getWidth(), biohazardImage.getHeight()));
					zombies.add(biohazard);
				}
			} else if (zombieSpawnChance >= (9780) && zombieSpawnChance < (9790)) {
				if (difficultyLevel >= 12) {
					AcceleratorBiohazard acceleratorbiohazard = new AcceleratorBiohazard(
							new Point2D.Double(x + greenBiohazardXOffset, y + greenBiohazardYOffset),
							new Point2D.Double(greenBiohazardImage.getWidth(), greenBiohazardImage.getHeight()));
					zombies.add(acceleratorbiohazard);
				} else {
					StrongBiohazard strongbiohazard = new StrongBiohazard(
							new Point2D.Double(x + greenBiohazardXOffset, y + greenBiohazardYOffset),
							new Point2D.Double(greenBiohazardImage.getWidth(), greenBiohazardImage.getHeight()));
					zombies.add(strongbiohazard);
				}
			} else if (zombieSpawnChance >= (9760) && zombieSpawnChance < (9780)) {
				if (difficultyLevel >= 14) {
					EmpoweredBiohazard empoweredbiohazard = new EmpoweredBiohazard(
							new Point2D.Double(x + blackBiohazardXOffset, y + blackBiohazardYOffset),
							new Point2D.Double(blackBiohazardImage.getWidth(), blackBiohazardImage.getHeight()));
					zombies.add(empoweredbiohazard);
				} else {
					AcceleratorBiohazard acceleratorbiohazard = new AcceleratorBiohazard(
							new Point2D.Double(x + greenBiohazardXOffset, y + greenBiohazardYOffset),
							new Point2D.Double(greenBiohazardImage.getWidth(), greenBiohazardImage.getHeight()));
					zombies.add(acceleratorbiohazard);
				}
			} else if (zombieSpawnChance >= (9740) && zombieSpawnChance < (9760)) {
				HealerBiohazard healerbiohazard = new HealerBiohazard(
						new Point2D.Double(x + blueBiohazardXOffset, y + blueBiohazardYOffset),
						new Point2D.Double(blueBiohazardImage.getWidth(), blueBiohazardImage.getHeight()));
				zombies.add(healerbiohazard);
			} else if (zombieSpawnChance >= (9720) && zombieSpawnChance < (9740)) {
				Biohazard biohazard = new Biohazard(new Point2D.Double(x + biohazardXOffset, y + biohazardYOffset),
						new Point2D.Double(biohazardImage.getWidth(), biohazardImage.getHeight()));
				zombies.add(biohazard);
			}
		}

		for (Actor plant : plants) {
			if (plant instanceof DoubleMushroom) {
				DoubleMushroom doublemushroom = (DoubleMushroom) plant;
				newPlants.addAll(doublemushroom.mushroomsToAdd);
			}
			plant.update();
			for (Actor zombie : zombies) {
				plant.attack(zombie);
			}
			if (plant.getPosition().getX() <= xDimension) {
				if (plant.isAlive()) {
					newPlants.add(plant);
					plant.move();
				}
			} else if (plant.getPosition().getX() > xDimension) {
				score += (2*plant.getCost());
			}
		}
		plants = newPlants;

		for (Actor zombie : zombies) {
			zombie.update();
			if (zombie.isAlive() && zombie.getPosition().getX() >= 0) {
				newZombies.add(zombie);
				zombie.move();
			}
			if(!zombie.isAlive() && zombie instanceof EmpoweredBiohazard) {
				double xLoc = zombie.getPosition().getX();
				double yLoc = zombie.getPosition().getY();
				AreaOfEffect threeByThreeAreaOfEffect = new AreaOfEffect((new Point2D.Double(xLoc-gridWidth,yLoc-gridHeight)), new Point2D.Double(threeByThreeAreaOfEffectImage.getWidth(), threeByThreeAreaOfEffectImage.getHeight()));
				newZombies.add(threeByThreeAreaOfEffect);
				Biohazard biohazard = new Biohazard(new Point2D.Double(xLoc, yLoc), new Point2D.Double(biohazardImage.getWidth(), biohazardImage.getHeight()));
				newZombies.add(biohazard);
			}
		}
		zombies = newZombies;

		for (Actor neutral : neutrals) {
			neutral.update();
			if (neutral.isAlive()) {
				newNeutrals.add(neutral);
			}
			
		}
		neutrals = newNeutrals;

//		formulates the difficulty, based on score - goes up but not down
		int difficultyFormula = (int) (20 * (Math.pow(2, difficultyLevel)));
		if (score >= difficultyFormula) {
			if (difficultyLevel < 20) {
				difficultyLevel++;
			}
		}
//		increases score by 10 with every iteration
		score += 10;
//		sets the score label
		String scoreSetter = (Integer.toString(score));
		scoreLabel.setText("Points: " + scoreSetter);
		// sets the difficulty label
		String difficultySetter = ("Difficulty Level: " + Integer.toString(difficultyLevel));
		difficultyLabel.setText(difficultySetter);

		repaint();
	}

	/**
	 * runs the initializeLayout method to start the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initializeLayout();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * initializes the GUI layout, including the frame, panels, labels and buttons
	 * used
	 */
	private static void initializeLayout() {

		JFrame app = new JFrame("Mushrooms vs Biohazards");
		app.setResizable(false);
		ActorTest panel = new ActorTest();

		app.setContentPane(panel);

		panel.setBackground(new Color(32, 128, 32));
		app.setBounds(100, 100, 500, 322);
		app.pack();
		app.setVisible(true);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton button_1 = new JButton();
		button_1.setIcon(new ImageIcon("src/copycat/actorimages/redmushroom.png"));
		button_1.setBounds(0, 0, 50, 50);
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mouseClickState = 1;
			}
		});

		JButton button_2 = new JButton();
		button_2.setIcon(new ImageIcon("src/copycat/actorimages/greenmushroom.png"));
		button_2.setBounds(50, 0, 50, 50);
		panel.add(button_2);
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mouseClickState = 2;
			}
		});
//		disabled for presentability
		JButton button_3 = new JButton();
		button_3.setIcon(new ImageIcon("src/copycat/actorimages/bluemushroom.png"));
		button_3.setBounds(100, 0, 50, 50);
		panel.add(button_3);
		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mouseClickState = 3;
			}
		});

		JButton button_4 = new JButton();
		button_4.setIcon(new ImageIcon("src/copycat/actorimages/doublemushroom.png"));
		button_4.setBounds(150, 0, 50, 50);
		panel.add(button_4);
		button_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mouseClickState = 4;
			}
		});

		JButton button_5 = new JButton();
		button_5.setIcon(new ImageIcon("src/copycat/actorimages/fertilizer.png"));
		button_5.setBounds(200, 0, 50, 50);
		panel.add(button_5);
		button_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mouseClickState = 5;
			}
		});

		JButton button_6 = new JButton();
		button_6.setIcon(new ImageIcon("src/copycat/actorimages/fence.png"));
		button_6.setBounds(250, 0, 50, 50);
		panel.add(button_6);
		button_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mouseClickState = 6;
			}
		});

		JButton button_7 = new JButton();
		button_7.setIcon(new ImageIcon("src/copycat/actorimages/poison.png"));
		button_7.setBounds(300, 0, 50, 50);
		panel.add(button_7);
		button_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mouseClickState = 7;
			}
		});

		JButton button_8 = new JButton();
		button_8.setIcon(new ImageIcon("src/copycat/actorimages/nuclearbomb.png"));
		button_8.setBounds(350, 0, 50, 50);
		panel.add(button_8);
		button_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mouseClickState = 8;
			}
		});

		scorePanel = new JPanel();
		LayoutManager layout = new BoxLayout(scorePanel, BoxLayout.Y_AXIS);
		scorePanel.setLayout(layout);
		scorePanel.setPreferredSize(new Dimension(177, 32));

		scoreLabel = new JLabel();// "",SwingConstants.RIGHT);
		scoreLabel.setAlignmentY(TOP_ALIGNMENT);

		difficultyLabel = new JLabel();// "",SwingConstants.RIGHT);
		difficultyLabel.setAlignmentY(BOTTOM_ALIGNMENT);

		scorePanel.setBackground(new Color(32, 128, 32));
		scorePanel.add(scoreLabel);
		scorePanel.add(difficultyLabel);

		panel.add(scorePanel);
	}

	/**
	 * uses the coordinates of the mouse to determine which cell it is checking,
	 * checks the cell using the getX() and getY() methods for the image used, for
	 * all elements that exist in the 'allPlants' ArrayList which collects all of
	 * the existing elements from the other three ArrayLists, returns false if a
	 * player resource can be spawned, true if the cell is already occupied
	 * 
	 * @param e
	 * @return is the cell occupied - true/false
	 */
	public boolean cellIsOccupied(MouseEvent e) {
		double baseX = Math.floor(e.getX() / gridWidth) * gridWidth;
		double baseY = Math.floor(e.getY() / gridHeight) * gridHeight;
		boolean result = false;

		ArrayList<Actor> allPlants = new ArrayList<>();
		allPlants.addAll(plants);
		allPlants.addAll(zombies);
		allPlants.addAll(neutrals);

		for (Actor everything : allPlants) {
			if (baseX <= everything.getPosition().getX() && (baseX + 50) >= everything.getPosition().getX()
					&& baseY <= everything.getPosition().getY() && (baseY + 50) >= everything.getPosition().getY()) {
				result = true;
			}
		}
		return result;
	}

//spawns a red mushroom, uses a generic spawn code
	public void spawnRedMushroom(MouseEvent e) {
		int cost = new RedMushroom(null, null).getCost();
		if (score >= cost) {
			if (e.getX() < (10 * gridWidth) && e.getX() > (0 * gridWidth) && e.getY() >= gridHeight) {
				RedMushroom redmushroom = new RedMushroom(setCoords(setImageHeightWidth(redMushroomImage), e),
						setImageHeightWidth(redMushroomImage));
				plants.add(redmushroom);
				score -= cost;
			} else {
				System.out.println("You cannot plant a red mushroom here.");
			}
		} else {
			System.out.println("You do not have enough points to plant a red mushroom! (Cost: " + cost + ")");
		}
	}

	// spawns a green mushroom, uses a generic spawn code
	public void spawnGreenMushroom(MouseEvent e) {
		int cost = new GreenMushroom(null, null).getCost();
		if (score >= cost) {
			if (e.getX() < (10 * gridWidth) && e.getX() > (0 * gridWidth) && e.getY() >= gridHeight) {
				GreenMushroom greenmushroom = new GreenMushroom(setCoords(setImageHeightWidth(greenMushroomImage), e),
						setImageHeightWidth(greenMushroomImage));
				plants.add(greenmushroom);
				score -= cost;
			} else {
				System.out.println("You cannot plant a green mushroom here.");
			}
		} else {
			System.out.println("You do not have enough points to plant a green mushroom! (Cost: " + cost + ")");
		}
	}

//	spawns a blue mushroom, uses a generic spawn code
	public void spawnBlueMushroom(MouseEvent e) {
		int cost = new BlueMushroom(null, null).getCost();
		if (score >= cost) {
			if (e.getX() < (10 * gridWidth) && e.getX() > (0 * gridWidth) && e.getY() >= gridHeight) {
				BlueMushroom bluemushroom = new BlueMushroom(setCoords(setImageHeightWidth(blueMushroomImage), e),
						setImageHeightWidth(blueMushroomImage));
				plants.add(bluemushroom);
				score -= cost;
			} else {
				System.out.println("You cannot plant a blue mushroom here.");
			}
		} else {
			System.out.println("You do not have enough points to plant a blue mushroom! (Cost: " + cost + ")");
		}
	}

//	spawns a double mushroom, uses a generic spawn code
	public void spawnDoubleMushroom(MouseEvent e) {
		int cost = new DoubleMushroom(null, null).getCost();
		if (score >= cost) {
			if (e.getX() < (10 * gridWidth) && e.getX() > (0 * gridWidth) && e.getY() >= gridHeight) {
				DoubleMushroom doublemushroom = new DoubleMushroom(
						setCoords(setImageHeightWidth(doubleMushroomImage), e),
						setImageHeightWidth(doubleMushroomImage));
				plants.add(doublemushroom);
				score -= cost;
			} else {
				System.out.println("You cannot plant a double mushroom here.");
			}
		} else {
			System.out.println("You do not have enough points to plant a double mushroom! (Cost: " + cost + ")");
		}
	}

	// checks if there are fewer than 5 bags of fertilizer (cannot spawn more than
	// 5), spawns another bag if under 5, uses a generic spawn code
	public void spawnFertilizer(MouseEvent e) {
		int cost = new Fertilizer(null, null).getCost();
		if (score >= cost) {
			if (e.getX() < (10 * gridWidth) && e.getX() > (0 * gridWidth) && e.getY() >= gridHeight) {
				Fertilizer.fertilizerCount = 0;
				for (Actor neutral : ActorTest.neutrals) {
					if (neutral instanceof Fertilizer) {
						Fertilizer.fertilizerCount++;
					}
				}
				if (Fertilizer.fertilizerCount < 5) {
					Fertilizer fertilizer = new Fertilizer(setCoords(setImageHeightWidth(fertilizerImage), e),
							setImageHeightWidth(fertilizerImage));
					neutrals.add(fertilizer);
					score -= cost;
				} else {
					System.out.println("You cannot spread more than 5 bags of fertilizer at once!");
				}
			} else {
				System.out.println("You cannot spread fertilizer here.");
			}
		} else {
			System.out.println("You do not have enough points to spread fertilizer! (Cost: " + cost + ")");
		}
	}

	/**
	 * spawns a fence to ward off biohazards, uses a generic spawn code
	 * 
	 * @param e
	 */
	public void spawnFence(MouseEvent e) {
		int cost = new Fence(null, null).getCost();
		if (score >= cost) {
			if (e.getX() < (10 * gridWidth) && e.getX() > (0 * gridWidth) && e.getY() >= gridHeight) {
				Fence fence = new Fence(setCoords(setImageHeightWidth(fenceImage), e), setImageHeightWidth(fenceImage));
				neutrals.add(fence);
				score -= cost;
			} else {
				System.out.println("You cannot construct a fence here.");
			}
		} else {
			System.out.println("You do not have enough points to construct a fence! (Cost: " + cost + ")");
		}
	}

	/**
	 * spawns a deadly poison 'cloud' to kill every non-neutral-aligned actor, uses
	 * a generic spawn code
	 * 
	 * @param e
	 */
	public void spawnPoison(MouseEvent e) {
		int cost = new Poison(null, null).getCost();
		if (score >= cost) {
			if (e.getX() < (10 * gridWidth) && e.getX() > (0 * gridWidth) && e.getY() >= gridHeight) {
				Poison poison = new Poison(setCoords(setImageHeightWidth(poisonImage), e),
						setImageHeightWidth(poisonImage));
				neutrals.add(poison);
				score -= cost;
			} else {
				System.out.println("You cannot place poison here.");
			}
		} else {
			System.out.println("You do not have enough points to poison the field! (Cost: " + cost + ")");
		}
	}

	/**
	 * spawns a nuclear bomb to wipe out the entire field (remains for a few seconds
	 * to continuously kill biohazards and regenerate points), uses a generic spawn
	 * code
	 * 
	 * @param e
	 */
	public void spawnNuclearBomb(MouseEvent e) {
		int cost = new NuclearBomb(null, null).getCost();
		if (score >= cost) {
			if (e.getX() < (10 * gridWidth) && e.getX() > (0 * gridWidth) && e.getY() >= gridHeight) {
				NuclearBomb bomb = new NuclearBomb(setCoords(setImageHeightWidth(nuclearBombImage), e),
						setImageHeightWidth(nuclearBombImage));
				neutrals.add(bomb);
				score -= cost;
			} else {
				System.out.println("You cannot drop a nuclear bomb here.");
			}
		} else {
			System.out.println("You do not have enough points to drop a nuclear bomb! (Cost: " + cost + ")");
		}
	}
	

	

	/**
	 * used in generic spawn to receive the height and width stats for the image
	 * used
	 * 
	 * @param image
	 * @return
	 */
	public static Point2D.Double setImageHeightWidth(BufferedImage image) {
		int instancedImageHeight = image.getHeight();
		int instancedImageWidth = image.getWidth();

		Point2D.Double imageHeightWidth = new Point2D.Double(instancedImageWidth, instancedImageHeight);
		return imageHeightWidth;
	}

	/**
	 * used in generic spawn to set the location of the image based on mouse
	 * coordinates
	 * 
	 * @param imageLoc
	 * @param e
	 * @return
	 */
	public Point2D.Double setCoords(Point2D.Double imageLoc, MouseEvent e) {
		int a = (int) (((double) gridWidth * (Math.floor((1 / (double) gridWidth) * (double) e.getX())))
				+ (0.5 * ((double) gridWidth - (double) imageLoc.x)));
		int b = (int) (((double) gridHeight * (Math.floor((1 / (double) gridHeight) * (double) e.getY())))
				+ (0.5 * ((double) gridHeight - (double) imageLoc.y)));

		Point2D.Double abCoords = new Point2D.Double(a, b);
		return abCoords;
	}

	/**
	 * when the mouse is clicked first part (loop): pick up coin if it exists on the
	 * field (case 0), switch case part (switch / cases 1,2,5-9) (cases 3 and 4
	 * disabled for presentability): checks the state of the mouseclick, based on
	 * the button code run above, checks if the cell is already occupied, if it
	 * isn't, runs the spawn code that corresponds to the related button selected
	 * above, case 0 is used for a standard mouse click (non-button click)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int storedClickState = 0;
		for (Actor coin : neutrals) {
			if (coin instanceof Resource) {
				Point2D.Double point = new Point2D.Double(e.getX(), e.getY());
				if (coin.isCollidingPoint(point)) {
					score += 500;
					coin.hitpoints = 0;
					if (mouseClickState != 0) {
						storedClickState = mouseClickState;
						mouseClickState = 0;
					}
				}
			}
		}
		switch (mouseClickState) {
		case 0:
			if (storedClickState != 0) {
				mouseClickState = storedClickState;
				storedClickState = 0;
			}
			break;
		case 1:
//			if (!cellIsOccupied(e)) {
			spawnRedMushroom(e);
//			} else {
//				System.out.println("this cell is already occupied");
//			}
			break;
		case 2:
//			if (!cellIsOccupied(e)) {
			spawnGreenMushroom(e);
//			} else {
//				System.out.println("this cell is already occupied");
//			}
			break;
		case 3:
//			if(!cellIsOccupied(e)){
//			System.out.println("option disabled");
			spawnBlueMushroom(e);
//			} else {
//				System.out.println("this cell is already occupied");
//			}
			break;
		case 4:
//			if(!cellIsOccupied(e)){
//			System.out.println("option disabled");
			spawnDoubleMushroom(e);
//			} else {
//				System.out.println("this cell is already occupied");
//			}
			break;
		case 5:
			if (!cellIsOccupied(e)) {
				spawnFertilizer(e);
			} else {
				System.out.println("this cell is already occupied");
			}
			break;
		case 6:
			if (!cellIsOccupied(e)) {
				spawnFence(e);
			} else {
				System.out.println("this cell is already occupied");
			}
			break;
		case 7:
//			if (!cellIsOccupied(e)) {
			spawnPoison(e);
//			} else {
//				System.out.println("this cell is already occupied");
//			}
			break;
		case 8:
//			if (!cellIsOccupied(e)) {
			spawnNuclearBomb(e);
//			} else {
//				System.out.println("this cell is already occupied");
//			}
			break;
		default:
			System.out.println("error: invalid option");
			System.exit(0);
			break;
		}
	}

	/**
	 * unused
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * unused
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * unused
	 */
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	/**
	 * unused
	 */
	@Override
	public void mouseExited(MouseEvent e) {

	}

}