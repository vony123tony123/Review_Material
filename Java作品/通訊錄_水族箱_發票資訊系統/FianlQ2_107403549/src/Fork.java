import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicBorders;

public class Fork extends JLabel implements Runnable
{
	protected SecureRandom generator = new SecureRandom();
	boolean hasCatch = false;
	JPanel aquarium;
	Point mouseclickPoint;
	ArrayList<Animal> animals;
	Point decisionPoint;
	Point drawPoint;
	int verticalY = 1;
	int size;
	public static int[] fork_num;
	public static int[] catch_num;
	public static int[] fish_num;
	public static int[] turtle_num;
	public static int[] shark_num;
	Animal catchedAnimal=null;
	
	protected static boolean removeswitch=false;
	protected static boolean removechoose = false;
	private int speed = generator.nextInt(1000) + 1;

	public Fork(JPanel Panel, Point mousePoint, ArrayList<Animal> animals)
	{
		aquarium = Panel;
		mouseclickPoint = mousePoint;
		this.animals = animals;
		size=40;
		setIcon(new ImageIcon(getScaledImage(new ImageIcon("./Q2/stick.png").getImage(), size, size)));
		drawPoint = mousePoint;
		decisionPoint = new Point(mousePoint.x,mousePoint.y+size);
		aquarium.add(this);
		setBounds(mousePoint.x,mousePoint.y,size,size);
		setBorder(new BasicBorders().getButtonBorder());
		
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(removeswitch) {
					Fork choosed = (Fork) e.getSource();
					aquarium.remove(choosed);
					aquarium.updateUI();
					fork_num[0]--;
					if(hasCatch) {
						aquarium.remove(catchedAnimal);
						aquarium.updateUI();
						System.out.println(catchedAnimal.getClass().getName());
						if(catchedAnimal.getClass().getName()=="Fish") {
							fish_num[0]--;
							Main.UpdateStatus();
						}else if(catchedAnimal.getClass().getName() == "Turtle") {
							turtle_num[0]--;
							Main.UpdateStatus();
						}else if(catchedAnimal.getClass().getName() == "Shark") {
							shark_num[0]--;
							Main.UpdateStatus();
						}
						catch_num[0]++;
					}
				}
			}
		});
	}
	
	@Override
	public void run()
	{
		while(hasCatch == false) {
			if(checkCatch())
				break;
			if(aquarium.contains(decisionPoint)) {
				drawPoint.y+=verticalY;
				setLocation(drawPoint.x, drawPoint.y);
				if(verticalY == 1) {
					decisionPoint = new Point(drawPoint.x,drawPoint.y+size);
				}else {
					decisionPoint = drawPoint;
				}
				
			}else{
				verticalY*=-1;
				drawPoint.y+=verticalY;
				setLocation(drawPoint.x, drawPoint.y);
			}
			
			if (generator.nextInt(1000) + 1 < 10)
				verticalY*=-1;
			
			if ((generator.nextInt(100) + 1) <= 2)
				speed = generator.nextInt(1000) + 1;
			
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
	
	private boolean checkCatch() {
		for(Animal animal :animals) {
			if(animal.inturrupt == false && Point2D.distance(
					getBounds().getCenterX(), getBounds().getCenterY(), animal.getBounds().getCenterX(),
					animal.getBounds().getCenterY()
			) < 30){
				animal.inturrupt = true;
				catchedAnimal=animal;
				hasCatch = true;
				return true;
			}
		}
		return false;
	}
	
	protected static void connectNum(int[] fish,int[] turtle, int[] shark, int[] fork, int[] catchNum) {
		fish_num=fish;
		turtle_num=turtle;
		shark_num = shark;
		fork_num = fork;
		catch_num = catchNum;
	}

}
