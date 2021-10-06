import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Turtle extends Animal {

	int varForDecisionPointY;

	public Turtle(JPanel panel, Point mousePoint) {
		super(panel, mousePoint);
		// TODO Auto-generated constructor stub
		size = 60;
		varForDecisionPointY = size - 17;
		rightImage = getScaledImage(new ImageIcon("./Q2/turtle.png").getImage(), size, size);
		leftImage = getScaledImage(new ImageIcon("./Q2/turtle2.png").getImage(), size, size);

		decide_direction();

		drawPoint = new Point(mouseclickPoint.x - size / 2, mouseclickPoint.y - size / 2);
		decisionPoint = new Point(drawPoint.x, drawPoint.y + varForDecisionPointY);
		aquarium.add(this);
		setBounds(drawPoint.x, drawPoint.y, size, size);
		aquarium.updateUI();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean isFirst = false;
		while (!inturrupt) {
			if (aquarium.contains(decisionPoint)) {
				y_direction = 1;
				drawPoint.setLocation(drawPoint.x, drawPoint.y + y_direction);
				setBounds(drawPoint.x, drawPoint.y, size, size);

				if ((generator.nextInt(100) + 1) <= 2)
					speed = generator.nextInt(1000) + 1;

				decisionPoint.setLocation(drawPoint.x, drawPoint.y + varForDecisionPointY);
				isFirst = true;
			} else {

				if (isFirst == false) {
					decisionPoint.setLocation(drawPoint.x, aquarium.getHeight());
					isFirst = true;
				}

				if (aquarium.contains(decisionPoint.x, decisionPoint.y - 1) == false) {
					x_direction *= -1;
					change_direction_picture();
				}

				drawPoint.setLocation(drawPoint.x + x_direction, drawPoint.y);
				setBounds(drawPoint.x, drawPoint.y, size, size);

				if (generator.nextInt(500) + 1 < 10)
					decide_direction();

				if ((generator.nextInt(100) + 1) <= 2)
					speed = generator.nextInt(1000) + 1;

				int x = drawPoint.x;
				if (x_direction == 1)
					x += size;
				decisionPoint.setLocation(x, decisionPoint.y);

			}

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
		change_direction_picture();
	}

}
