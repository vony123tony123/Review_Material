import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class RoundRect{
	
	protected Point startPoint;
	protected Point finishPoint;
	protected int width=0;
	protected int height=0;
	protected int roundweight;
	protected int roundheight;
	protected Color color;
	protected boolean is_mouse;
	
	public RoundRect(Color color,Point startpoint, int weight, int height,int roundweight, int roundheight) {
		this.color=color;
		this.width=weight;
		this.height=height;
		this.roundweight=roundweight;
		this.roundheight=roundheight;
		this.startPoint=startpoint;
		this.finishPoint=startpoint;
		this.is_mouse=false;
	}
	
	public RoundRect(Color color,Point startPoint)
	{
		this.color=color;
		this.startPoint=startPoint;
		this.roundweight=20;
		this.roundheight=20;
		this.finishPoint=startPoint;
		this.is_mouse=true;
	}

	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		int x=Math.min(startPoint.x, finishPoint.x);
		int y=Math.min(startPoint.y, finishPoint.y);
		if(this.is_mouse==true)
		{
			this.width=Math.abs(startPoint.x-finishPoint.x);
			this.height=Math.abs(startPoint.y-finishPoint.y);
		}
		g.drawRoundRect(x, y, this.width, this.height, this.roundweight, this.roundheight);
	}
	
	public void setFinishPoint(Point finishPoint) {
		this.finishPoint = finishPoint;
	}

}
