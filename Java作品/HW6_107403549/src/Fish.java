
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fish extends Animal {
	protected int kind = generator.nextInt(3);

	public Fish(JPanel panel, Point point) {
		super(panel, point);

		size = generator.nextInt(100) + 20;
		switch (kind) {
		case 0:
			rightImage = getScaledImage(new ImageIcon("./JAVA_HW6_附件/1.png").getImage(), size, size);
			leftImage = getScaledImage(new ImageIcon("./JAVA_HW6_附件/2.png").getImage(), size, size);
			break;
		case 1:
			rightImage = getScaledImage(new ImageIcon("./JAVA_HW6_附件/3.png").getImage(), size, size);
			leftImage = getScaledImage(new ImageIcon("./JAVA_HW6_附件/4.png").getImage(), size, size);
			break;
		case 2:
			rightImage = getScaledImage(new ImageIcon("./JAVA_HW6_附件/5.png").getImage(), size, size);
			leftImage = getScaledImage(new ImageIcon("./JAVA_HW6_附件/6.png").getImage(), size, size);
			break;
		}
		decide_direction();
		drawPoint = new Point(mouseclickPoint.x - size / 2, mouseclickPoint.y - size / 2);
		decide_decision_point();
		aquarium.add(this);
		setBounds(drawPoint.x, drawPoint.y, size, size);
	}

	@Override
	public void run() {
		while (!inturrupt) {
			detected_panel_bound();
			drawPoint.setLocation(drawPoint.x + x_direction, drawPoint.y + y_direction);
			setBounds(drawPoint.x, drawPoint.y, size, size);

			if (generator.nextInt(1000) + 1 < 10) {
				decide_direction();
			}

			if ((generator.nextInt(100) + 1) <= 2)
				speed = generator.nextInt(1000) + 1;

			decide_decision_point();

			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void decide_direction() {

		x_direction = generator.nextInt(2);
		if (x_direction == 0)
			x_direction = -1;
		y_direction = generator.nextInt(2);
		if (y_direction == 0)
			y_direction = -1;
		change_direction_picture();
	}

	protected void detected_panel_bound() {
		if (aquarium.contains(decisionPoint) == false) {
			x_direction *= -1;
			change_direction_picture();
			y_direction *= -1;
		}
	}

	protected void decide_decision_point() {
		int x = drawPoint.x;
		int y = drawPoint.y;
		if (x_direction == 1)
			x += size;
		if (y_direction == 1)
			y += size;

		if (decisionPoint == null)
			decisionPoint = new Point(x, y);
		else {
			decisionPoint.setLocation(x, y);
		}
	}

}
