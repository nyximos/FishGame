package gameObject;

import static gameObject.Constants.COIN_RADIUS;
import static gameObject.Constants.ALIEN_RADIUS;
import static gameObject.Constants.EGG_PRICE;
import static gameObject.Constants.FISH_CHANGE_DIR_INTERVAL;
import static gameObject.Constants.FISH_HUNGRY_CONSTRAINT;
import static gameObject.Constants.FOOD_PRICE;
import static gameObject.Constants.GUPPY_PRICE;
import static gameObject.Constants.PIRANHA_PRICE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Drawer {
	protected JFrame frame;
	protected DrawPanel drawPanel;
	protected Aquarium aquarium;
	protected long time;
	protected boolean menuState;

	protected MouseListener mouseListener;
	protected KeyListener keyListener;

	protected boolean jalan;
	protected boolean savingFile;
	
	public static long score;
	public static long finalScore;



	public static String name;
	public static int on = 0;


	TimerTask alienTask = new TimerTask() {
		public void run() {
			aquarium.createAlien();
		}
	};

	Timer alienTimer = new Timer();
	long alienDelay = 3000;
	long alienPeriod = 27000;
	

	/**
	 * Constructor Drawer.
	 */
	public Drawer() {
		menuState = true;
		jalan = true;
		savingFile = false;
 
		aquarium = new Aquarium(1080, 720);

		frame = new JFrame("물고기키우기");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel();

		mouseListener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (menuState) {
					if ((e.getX() >= 600 && e.getX() <= 965) && (e.getY() >= 70 && e.getY() <= 170)) {
						name = JOptionPane.showInputDialog("Please input name: ");
						menuState = false;
						System.out.println(name+"님이 게임을 시작했습니다.");
						aquarium.createGuppy();
						alienTimer.schedule(alienTask, alienDelay, alienPeriod);

					}
					if ((e.getX() >= 600 && e.getX() <= 965) && (e.getY() >= 212 && e.getY() <= 283)) {
						String loadFilename = JOptionPane.showInputDialog("Please input file name for load: ");
						System.out.println("Load Game");
						menuState = false;
					}
				} else {
					int money = aquarium.getMoney();
					Point pNow = new Point(e.getX(), e.getY());
					Point closestCoin = aquarium.getClosestCoin(pNow);

					Point closestAlien = aquarium.getClosestAlien(pNow);
					if (pNow.findDistance(closestAlien) <= ALIEN_RADIUS && aquarium.getListAlien().getSize() > 0) {
						for (int i = 1; i <= aquarium.getListAlien().getSize(); i++)
							if (aquarium.getListAlien().get(i).equals(closestAlien)) {
								System.out.println("때찌"+aquarium.getListAlien().get(i).getClick());
								aquarium.getListAlien().get(i).plusClick();

								if (aquarium.getListAlien().get(i)
										.AlienisTimeToDie(aquarium.getListAlien().get(i).getClick())) {
									aquarium.getListAlien().remove(aquarium.getListAlien().get(i));
									aquarium.createCoin(pNow,5);
									break;
								}
							}

					}

					if (pNow.findDistance(closestCoin) <= COIN_RADIUS && aquarium.getListCoin().getSize() > 0) {
						for (int i = 1; i <= aquarium.getListCoin().getSize(); i++)
							if (aquarium.getListCoin().get(i).equals(closestCoin)) {
								System.out.println("줍줍");
								int val = aquarium.getGarry().takeCoin(aquarium.getListCoin(),
										aquarium.getListCoin().get(i));

//	                 Coin coin = aquarium.getListCoin().get(i);
//	                 aquarium.getListCoin().remove(coin);
								aquarium.setMoney(money + val);
								break;
							}
					} else {
						if (e.getY() > 140 && e.getX() < 1030) {

							if(aquarium.getListAlien().getSize()==0) {
							if (money >= FOOD_PRICE) {
								money -= FOOD_PRICE;
								aquarium.setMoney(money);
								aquarium.createFood(new Point(e.getX(), 150));
								}
							}
						}


						if ((e.getY() >= 37 && e.getY() <= 69) && (e.getX() >= 931 && e.getX() <= 1041)) {
							savingFile = true;

						}
					}

				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		};

		keyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (!menuState) {
					int money = aquarium.getMoney();
					if (e.getKeyChar() == 'g') {
						if (money >= GUPPY_PRICE) {
							money -= GUPPY_PRICE;
							aquarium.setMoney(money);
							aquarium.createGuppy();
						}
					} else if (e.getKeyChar() == 'p') {
						if (money >= PIRANHA_PRICE) {
							money -= PIRANHA_PRICE;
							aquarium.setMoney(money);
							aquarium.createPiranha();
						}

					} else if (e.getKeyChar() == 'e') {
						int egg = aquarium.getEgg();
						if (money >= EGG_PRICE) {
							egg++;
							money -= EGG_PRICE;
							aquarium.setEgg(egg);
							aquarium.setMoney(money);
						}

					} else if (e.getKeyChar() == 'x') {
						JDialog.setDefaultLookAndFeelDecorated(true);
						int response = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (response == JOptionPane.NO_OPTION) {
							// dummy line
						} else if (response == JOptionPane.YES_OPTION) {
							jalan = false;
						} else if (response == JOptionPane.CLOSED_OPTION) {
							// dummy line
						}
					} else if (e.getKeyChar() == 'q') {
						aquarium.setMoney(9999999);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		};

		drawPanel.addMouseListener(mouseListener);
		drawPanel.addKeyListener(keyListener);

		drawPanel.setFocusable(true);
		drawPanel.requestFocusInWindow();

		frame.setResizable(false);
		frame.setSize(1080, 720);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

		frame.add(drawPanel);
	}

	public void run() {
		while (jalan) {
			frame.repaint();
			if (savingFile) {
				String saveFilename = JOptionPane.showInputDialog("Please input file name for save: ");
				System.out.println("Game Saved to" + saveFilename);
//				saveGame(saveFilename);
				savingFile = false;
			}
		}
		// Exit window
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}

	public double getTime() {
		return aquarium.getCurrentTime();
	}

	public int getFrame() {
		double temp = getTime() % 1.5;

		if (temp >= 0 && temp < 0.15) {
			return 0;
		} else if (temp >= 0.15 && temp < 0.3) {
			return 1;
		} else if (temp >= 0.3 && temp < 0.45) {
			return 2;
		} else if (temp >= 0.45 && temp < 0.6) {
			return 3;
		} else if (temp >= 0.6 && temp < 0.75) {
			return 4;
		} else if (temp >= 0.75 && temp < 0.9) {
			return 5;
		} else if (temp >= 0.9 && temp < 1.05) {
			return 6;
		} else if (temp >= 1.05 && temp < 1.2) {
			return 7;
		} else if (temp >= 1.2 && temp < 1.35) {
			return 8;
		} else {
			return 9;
		}
	}

	class DrawPanel extends JPanel {
		private Image backgroundImage;
		private Image mainMenu;
		private Image winImage;
		private Image loseImage;
		private Font font;

		public DrawPanel() {
			super();
			loadMenu();
			loadBackground();
			loadWin();
			loadLose();
			loadFont();
		}

		public void loadFont() {
			font = null;
			try {
				font = Font.createFont(Font.TRUETYPE_FONT,
						new FileInputStream(new File("assets/fonts/OpenSans-Regular.ttf")));
			} catch (Exception e) {
				System.out.println(e);
			}
			font = font.deriveFont(Font.PLAIN, 25);

		}

		public void loadBackground() {
			ImageIcon temp = new ImageIcon("assets/img/aquarium2.png");
			backgroundImage = temp.getImage();
		}

		public void loadMenu() {
			ImageIcon temp = new ImageIcon("assets/img/mainmenu2.png");
			mainMenu = temp.getImage();
		}

		public void loadWin() {
			ImageIcon temp = new ImageIcon("assets/img/win2.png");
			winImage = temp.getImage();
		}

		public void loadLose() {
			ImageIcon temp = new ImageIcon("assets/img/lose2.png");
			loseImage = temp.getImage();


		}

		public void paintComponent(Graphics g) {
			aquarium.moveGuppy();
			aquarium.movePiranha();
			aquarium.moveCoin();
			aquarium.moveFood();
			aquarium.moveSnail();
			aquarium.moveAlien();

			super.paintComponent(g);

			int state = aquarium.getStateGame();
			if (state == 2) {
				g.drawImage(winImage, 0, 0, this);
			} else if (state == 1) {
				g.drawImage(loseImage, 0, 0, this);			
			} else {
				if (menuState) {
					g.drawImage(mainMenu, 0, 0, this);
				} else {

					g.drawImage(backgroundImage, 0, 0, this);

					g.setFont(font);
					String money = Integer.toString(aquarium.getMoney());
					String egg = Integer.toString(aquarium.getEgg());
					g.setColor(Color.WHITE);
					g.drawString(egg, 831, 70);
					g.drawString(money, 950, 115);

					if (!aquarium.getListGuppy().isEmpty()) {
						for (int i = 0; i < aquarium.getListGuppy().getSize(); i++) {
							drawGuppy(aquarium.getListGuppy().get(i), g);
						}
					}

					if (!aquarium.getListPiranha().isEmpty()) {
						for (int i = 0; i < aquarium.getListPiranha().getSize(); i++) {
							drawPiranha(aquarium.getListPiranha().get(i), g);
						}
					}
					if (!aquarium.getListAlien().isEmpty()) {
						for (int i = 0; i < aquarium.getListAlien().getSize(); i++) {
							drawAlien(aquarium.getListAlien().get(i), g);
						}
					}

					for (int i = 0; i < aquarium.getListFood().getSize(); i++) {
						drawFood(aquarium.getListFood().get(i), g);
					}

					for (int i = 0; i < aquarium.getListCoin().getSize(); i++) {
						drawCoin(aquarium.getListCoin().get(i), g);
					}

					drawSnail(aquarium.getGarry(), g);

				}
			}
		}

		public void drawSnail(Snail snail, Graphics g) {
			int fps = getFrame();
			double x = snail.getX();
			double y = snail.getY() - 40;
			int direction = snail.getDirection();
			String filename = "snail" + String.valueOf(fps);

			if (direction == 1) {
				filename = "assets/img/r" + filename + ".png";
			} else {
				filename = "assets/img/" + filename + ".png";
			}

			ImageIcon temp = new ImageIcon(filename);
			Image snailImage = temp.getImage();

			g.drawImage(snailImage, (int) x, (int) y, this);
		}

		public void drawFood(Food food, Graphics g) {
			int fps = getFrame();
			double x = food.getX();
			double y = food.getY();
			String filename = "assets/img/food" + String.valueOf(fps) + ".png";

			ImageIcon temp = new ImageIcon(filename);
			Image foodImage = temp.getImage();

			g.drawImage(foodImage, (int) x, (int) y, this);
		}

		public void drawCoin(Coin coin, Graphics g) {
			int fps = getFrame();
			String filename = "coin" + String.valueOf(fps) + ".png";

			double x = coin.getX();
			double y = coin.getY();
			int level = coin.getValue() / coin.getBaseVal();

			if (level == 1) {
				filename = "assets/img/b" + filename;
			} else if (level == 2) {
				filename = "assets/img/g" + filename;
			} else if (level == 3) {
				filename = "assets/img/s" + filename;
			} else {
				filename = "assets/img/dcoin.png";
			}

			ImageIcon temp = new ImageIcon(filename);
			Image coinImage = temp.getImage();

			g.drawImage(coinImage, (int) x, (int) y, this);
		}

		public void drawGuppy(Guppy guppy, Graphics g) {
			String filename;
			int fps = getFrame();
			int level = guppy.getGrowthStep();
			int direction = guppy.getDirection();
			double x = guppy.getX();
			double y = guppy.getY();

			if (direction == 1) {
				filename = "assets/img/rguppy" + String.valueOf(fps);
			} else {
				filename = "assets/img/guppy" + String.valueOf(fps);
			}

			if (level == 1) {
				filename += ".png";
			} else if (level == 2) {
				filename += "1.png";
			} else if (level == 3) {
				filename += "2.png";
			}

			ImageIcon temp = new ImageIcon(filename);
			Image guppyImage = temp.getImage();

			g.drawImage(guppyImage, (int) x, (int) y, this);
		}

		public void drawPiranha(Piranha piranha, Graphics g) {
			String filename;
			int fps = getFrame();
			int direction = piranha.getDirection();
			double x = piranha.getX();
			double y = piranha.getY();

			if (direction == 1) {
				filename = "assets/img/rpiranha" + String.valueOf(fps) + ".png";
			} else {
				filename = "assets/img/piranha" + String.valueOf(fps) + ".png";
			}

			ImageIcon temp = new ImageIcon(filename);
			Image piranhaImage = temp.getImage();

			g.drawImage(piranhaImage, (int) x, (int) y, this);
		}

		public void drawAlien(Alien alien, Graphics g) {
			String filename;
			int fps = getFrame();
			int direction = alien.getDirection();
			double x = alien.getX();
			double y = alien.getY();

			if (direction == 1) {
				filename = "assets/img/ralien" + String.valueOf(fps) + ".png";
			} else {
				filename = "assets/img/alien" + String.valueOf(fps) + ".png";
			}

			ImageIcon temp = new ImageIcon(filename);
			Image alienImage = temp.getImage();

			g.drawImage(alienImage, (int) x, (int) y, this);
		}

	}




}
