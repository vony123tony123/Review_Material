/*
 * 107403549 資管三 涂建名
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sun.corba.se.spi.orbutil.fsm.State;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.glassfish.external.probe.provider.PluginPoint;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Stack;
import java.util.TreeMap;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Windows {

	private JFrame frame;
	private JTextField GoalXField;
	private JTextField GoalYField;
	private JPanel SouthPanel;
	private JPanel NorthPanel;
	private JLabel GoalTitleLabel;
	private JButton RunButton;
	private JPanel CenterPanel;
	
	private LinkedHashMap<Point, JLabel> centerMap=new LinkedHashMap<Point, JLabel>();
	private ArrayList<Point> wallpoints=new ArrayList<Point>();
	private readMap readMap=new readMap(wallpoints);
	private int maxrow=readMap.getMaxrow();
	private Point goal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Windows window = new Windows();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Windows() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		NorthPanel = new JPanel();
		frame.getContentPane().add(NorthPanel, BorderLayout.NORTH);
		
		GoalTitleLabel = new JLabel("\u9078\u64C7\u7D42\u9EDE(x,y):");
		NorthPanel.add(GoalTitleLabel);
		
		GoalXField = new JTextField();
		NorthPanel.add(GoalXField);
		GoalXField.setColumns(5);
		
		GoalYField = new JTextField();
		NorthPanel.add(GoalYField);
		GoalYField.setColumns(5);
		
		SouthPanel = new JPanel();
		frame.getContentPane().add(SouthPanel, BorderLayout.SOUTH);
		SouthPanel.setLayout(new BorderLayout(0, 0));
		
		RunButton = new JButton("Run");
		RunButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goal=new Point(Integer.valueOf(GoalYField.getText()),Integer.valueOf(GoalXField.getText()));
				if(centerMap.get(goal).getBackground()==Color.black)
				{
					JOptionPane.showMessageDialog(null, "此處為牆壁，請重設終點");
				}else {
					centerMap.get(goal).setBackground(Color.green);
					backtracking();
				}
			}
		});
		SouthPanel.add(RunButton);
		
		CenterPanel = new JPanel();
		frame.getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new GridLayout(10,10, 0, 0));
		for(int i=1;i<=maxrow;i++)
		{
			for(int j=1;j<=maxrow;j++)
			{
				Point point = new Point(j,i);
				JLabel label = new JLabel();
				label.setPreferredSize(new Dimension(50,50));
				label.setOpaque(true);
				if(wallpoints.contains(point))
				{
					label.setBackground(Color.black);
					
				}else {
					label.setBackground(Color.white);
					label.setText("("+i+","+j+")");
					label.setHorizontalAlignment(SwingConstants.CENTER);
				}
				CenterPanel.add(label);
				centerMap.put(point, label);
			}
		}
		centerMap.get(new Point(1,1)).setBackground(Color.blue);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	private void backtracking()
	{
		Stack<Point> stack = new Stack<Point>();
//		Stack<Point> faultPoints = new Stack<Point>();
		Point currentPoint=new Point(1,1);
		stack.push(currentPoint);
		while(!currentPoint.equals(goal))
		{
			Point[] testPoints= {new Point(currentPoint.x+1,currentPoint.y),
								new Point(currentPoint.x-1,currentPoint.y),
								new Point(currentPoint.x,currentPoint.y-1),
								new Point(currentPoint.x,currentPoint.y+1)};
			
			for(Point p:testPoints)
			{
				if((p.x<1||p.x>maxrow)||(p.y<1||p.y>maxrow))
					continue;
				else if(centerMap.get(p).getBackground().equals(Color.black))
					continue;
				else if(stack.contains(p))
					continue;
				else {
					stack.push(p);
					centerMap.get(p).setBackground(Color.gray);
				}
			}
			
			System.out.println(stack);
			
			if(stack.peek().equals(currentPoint))
			{
//				其他解法:
//				faultPoints.add(stack.peek());
//				stack.pop();
//				centerMap.get(currentPoint).setBackground(Color.white);
//				currentPoint=stack.peek();
				
				centerMap.get(currentPoint).setBackground(Color.white);
				while(!centerMap.get(currentPoint).getBackground().equals(Color.gray))
				{
					stack.pop();
					centerMap.get(currentPoint).setBackground(Color.white);
					if(stack.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "找不到目標");
						System.exit(0);
					}else {
						currentPoint=stack.peek();
					}
				}
				
				
			}else {
				centerMap.get(currentPoint).setBackground(Color.red);
				currentPoint=stack.peek();
			}
			
		}
		centerMap.get(new Point(1,1)).setBackground(Color.blue);
		centerMap.get(goal).setBackground(Color.green);
	}

}
