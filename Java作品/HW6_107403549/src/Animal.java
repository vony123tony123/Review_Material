import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Animal extends JLabel implements Runnable {

	protected SecureRandom generator = new SecureRandom();
	protected int speed = generator.nextInt(1000);
	protected int x_direction = 1;
	protected int y_direction = 1;
	protected boolean inturrupt = false;
	protected Image rightImage = null;
	protected Image leftImage = null;
	protected Point mouseclickPoint = null;
	protected Point drawPoint = null;
	protected Point decisionPoint = drawPoint;
	protected JPanel aquarium = null;
	protected int size;
	protected static boolean removeswitch;
	protected static int fish_num[];
	protected static int turtle_num[];

	public Animal(JPanel panel, Point mousePoint) {
		aquarium = panel;
		mouseclickPoint = mousePoint;
		addMouseListener(new removehandler());
	}

	public static void connectNum(int[] fish, int[] turtle) {
		fish_num = fish;
		turtle_num = turtle;
	}

	public static void setRemoveswitch(boolean r) {
		removeswitch = r;
	}

	protected Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	private class removehandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (removeswitch) {
				Animal choosed = (Animal) e.getSource();
				choosed.inturrupt = true;
				aquarium.remove(choosed);
				aquarium.updateUI();
				if (e.getSource().getClass().getName() == "Fish") {
					fish_num[0]--;
				} else {
					turtle_num[0]--;
				}
				Main.UpdateStatus();
			}
		}
	}

	protected void change_direction_picture() {
		if (x_direction > 0) {
			setIcon(new ImageIcon(rightImage));
		} else {
			setIcon(new ImageIcon(leftImage));
		}
	}

}
