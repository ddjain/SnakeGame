import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


/**
 * @author DarshanJain
 * @category Game
 * @version 1.01
 * @email nnd.darshan@gmail.com
 * @mobile 8421761442
 */
public class MainWindow implements KeyListener {
	JFrame jf;
	JLabel fruit;
	char key;
	int X_POS;
	int point = 0;
	int Fruit_X, Fruit_Y;
	JRadioButton jb;
	JLabel scores;
	int speed;

	public void increaseSpeed() {
		if (point % 5 == 0) {
			if (speed > 1) {
				speed--;
			}
		}
	}

	public void generateFruit() {
		increaseSpeed();
		Fruit_X = (int) Math.floor(Math.random() * 1000 % 468);
		Fruit_Y = (int) Math.floor(Math.random() * 1000 % 433);
		System.out.println("FRUIT X " + Fruit_X + "   FRUIT y" + Fruit_Y);
		fruit.setBounds(Fruit_X, Fruit_Y, 15, 15);
		jf.add(fruit);
	}

	public MainWindow() {
		speed = 10;
		jf = new JFrame();
		fruit = new JLabel("*");
		scores = new JLabel("SCORE");
		frameInitialization();
		generateFruit();
		callToSnake();
	}

	private void frameInitialization() {
		setX_POS(100);
		setY_POS(100);
		fruit = new JLabel("@");
		scores.setBounds(50, 50, 50, 50);
		jf.add(scores);
		jf.setSize(500, 500);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.addKeyListener(this);
		jf.setResizable(true);
		jb = new JRadioButton("@");
	}

	private void callToSnake() {
		new Thread() {
			@Override
			public void run() {
				int i = 1;
				while (true) {
					System.out.println("SPPED:" + speed + "  SNAKE: X= " + X_POS + "  Y = " + Y_POS);
					int xdiff = X_POS - Fruit_X;
					int ydiff = Y_POS - Fruit_Y;
					if (xdiff > -10 && xdiff < 10 && ydiff > -10 && ydiff < 10) {
						System.out.println("FRUIT TOUCHED x= " + xdiff + "   " + ydiff);
						increaseScore();
						generateFruit();
					}
					if (X_POS == 468 || Y_POS == 433 || X_POS == -10 || Y_POS == -10) {
						System.out.println("GAME OVER   ");
						break;
					}
					if (key == 'R') {
						jb.setBounds(X_POS++, Y_POS, 30, 30);

					}
					if (key == 'L') {
						jb.setBounds(X_POS--, Y_POS, 30, 30);

					}
					if (key == 'U') {
						jb.setBounds(X_POS, Y_POS--, 30, 30);
					}
					if (key == 'D') {
						jb.setBounds(X_POS, Y_POS++, 30, 30);
					}
					jf.add(jb);
					try {
						Thread.sleep(speed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();

	}

	public static void main(String[] args) {
		new MainWindow();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("KEY PRESSED");
		System.out.println(e.getKeyCode());
		// generateFruit();
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && key != 'L') {
			key = 'R';
			System.out.println("KEY RIGHT");
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && key != 'R') {
			key = 'L';

			System.out.println("KEY LEFT " + key);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && key != 'D') {
			key = 'U';

			System.out.println("KEY UP " + key);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && key != 'U') {
			key = 'D';

			System.out.println("KEY DOWN");
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			key = 'P';

			System.out.println("KEY DOWN");
		}
		if (e.getKeyCode() == 'N') {
			generateFruit();

		}
		if (e.getKeyCode() == KeyEvent.VK_ADD) {
			increaseSpeed();
		}
		if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
			speed++;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void increaseScore() {
		point++;
		scores.setText("SCORE: " + point);

	}

	public int getX_POS() {
		return X_POS;
	}

	public void setX_POS(int x_POS) {
		X_POS = x_POS;
	}

	public int getY_POS() {
		return Y_POS;
	}

	public void setY_POS(int y_POS) {
		Y_POS = y_POS;
	}

	int Y_POS;

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}
}
