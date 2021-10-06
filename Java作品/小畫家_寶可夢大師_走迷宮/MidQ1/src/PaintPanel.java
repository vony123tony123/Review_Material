import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.undo.UndoableEdit;

import sun.java2d.pipe.DrawImage;

public class PaintPanel extends JPanel {
	private ArrayList<RoundRect> painters = new ArrayList<RoundRect>();
	private Color currentColor=Color.black;
	private int currentwidth=20;
	private int currentheight=20;
	private Point currentPoint;
	private RoundRect currentPainter;
	

	public PaintPanel() {
			setBackground(Color.WHITE);
	}
	
	public void Updo() {
		painters.remove(painters.size()-1);
		repaint();
	}



	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		if(!painters.isEmpty())
		{
			for(RoundRect paint:painters)
			{
				paint.draw((Graphics2D)g);
			}
		}
		if(currentPainter!=null)
			currentPainter.draw((Graphics2D)g);
	}



	public Color getCurrentColor() {
		return currentColor;
	}



	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}



	public int getCurrentwidth() {
		return currentwidth;
	}



	public void setCurrentwidth(int currentwidth) {
		this.currentwidth = currentwidth;
	}



	public int getCurrentheight() {
		return currentheight;
	}



	public void setCurrentheight(int currentheight) {
		this.currentheight = currentheight;
	}



	public Point getCurrentPoint() {
		return currentPoint;
	}



	public void setCurrentPoint(Point currentPoint) {
		this.currentPoint = currentPoint;
	}



	public RoundRect getCurrentPainter() {
		return currentPainter;
	}



	public void setCurrentPainter(RoundRect currentPainter) {
		this.currentPainter = currentPainter;
	}

	public void MousePressProcess(Point p) {
		currentPainter=new RoundRect(currentColor, p);
	}
	
	public void MouseReleasedProcess() {
		painters.add(currentPainter);
		currentPainter=null;
	}
	
	
}
