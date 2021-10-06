import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Shark extends Animal
{
	ArrayList<Fish> fishs;
	int eatAmount = 0;
	int consumetime = 0;
	int ranaway = 0;

	public Shark(JPanel panel, Point mousePoint, ArrayList<Fish> fishs)
	{
		super(panel, mousePoint);
		this.fishs = fishs;
		size = generator.nextInt(100) + 20;
		rightImage = getScaledImage(new ImageIcon("./Q2/shark1.png").getImage(), size, size);
		leftImage = getScaledImage(new ImageIcon("./Q2/shark2.png").getImage(), size, size);
		decide_direction();
		drawPoint = new Point(mouseclickPoint.x - size / 2, mouseclickPoint.y - size / 2);
		decide_decision_point();
		aquarium.add(this);
		setBounds(drawPoint.x, drawPoint.y, size, size);
	}

	@Override
	public void run()
	{
		while (!inturrupt)
		{
			consumetime++;
			eatFish();

			if (consumetime % 50 == 0)
				consume();

			detected_panel_bound();
			drawPoint.setLocation(drawPoint.x + x_direction, drawPoint.y + y_direction);
			setBounds(drawPoint.x, drawPoint.y, size, size);

			if (generator.nextInt(1000) + 1 < 10)
			{
				decide_direction();
			}

			if ((generator.nextInt(100) + 1) <= 2)
				speed = generator.nextInt(1000) + 1;

			decide_decision_point();

			try
			{
				Thread.sleep(speed);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected void decide_direction()
	{

		x_direction = generator.nextInt(2);
		if (x_direction == 0)
			x_direction = -1;
		y_direction = generator.nextInt(2);
		if (y_direction == 0)
			y_direction = -1;
		change_direction_picture();
	}

	protected void detected_panel_bound()
	{
		if (aquarium.contains(decisionPoint) == false)
		{
			x_direction *= -1;
			change_direction_picture();
			y_direction *= -1;
		}
	}

	protected void decide_decision_point()
	{
		int x = drawPoint.x;
		int y = drawPoint.y;
		if (x_direction == 1)
			x += size;
		if (y_direction == 1)
			y += size;

		if (decisionPoint == null)
			decisionPoint = new Point(x, y);
		else
		{
			decisionPoint.setLocation(x, y);
		}
	}

	protected void eatFish()
	{
		boolean hasEatFish = false;
		for (Fish fish : fishs)
		{
			if (eatAmount <= 5)
			{
				if (getBounds().intersects(fish.getBounds()) && fish.inturrupt == false && fish.isAte == false)
				{
					aquarium.remove(fish);
					aquarium.updateUI();
					size += 10;
					eatAmount++;
					rightImage = getScaledImage(new ImageIcon("./Q2/shark1.png").getImage(), size, size);
					leftImage = getScaledImage(new ImageIcon("./Q2/shark2.png").getImage(), size, size);
					change_direction_picture();
					setBounds(drawPoint.x, drawPoint.y, size, size);
					fish.inturrupt = true;
					fish.isAte = true;
					fish_num[0]--;
					hasEatFish = true;
				}
			} else
			{
				if (fish.isAte == false)
				{
					if (
						Point2D.distance(
								getBounds().getCenterX(), getBounds().getCenterY(), fish.getBounds().getCenterX(),
								fish.getBounds().getCenterY()
						) < 100 && ranaway == 0
					)
					{
						x_direction *= -1;
						change_direction_picture();
						y_direction *= -1;
					}
					ranaway++;
					if(ranaway == 5) {
						ranaway=0;
					}
				}
			}
		}
	}

	public void consume()
	{
		if (eatAmount > 0)
		{
			eatAmount--;
			size -= 10;
			rightImage = getScaledImage(new ImageIcon("./Q2/shark1.png").getImage(), size, size);
			leftImage = getScaledImage(new ImageIcon("./Q2/shark2.png").getImage(), size, size);
			change_direction_picture();
			setBounds(drawPoint.x, drawPoint.y, size, size);
		}
	}

}
