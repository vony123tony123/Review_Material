/* 作者:
 * 107403549 資管三 涂建名
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
		frame.pack();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel playground = new JPanel();
		frame.getContentPane().add(playground);
		
		UIManager.put("ProgressBar.selectionBackground", Color.GREEN);
		UIManager.put("ProgressBar.selectionForeground", Color.BLACK);
		JProgressBar progressBar = new JProgressBar();
		progressBar.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		progressBar.setForeground(Color.RED);
		progressBar.setStringPainted(true);
		progressBar.setValue(100);
		frame.getContentPane().add(progressBar, BorderLayout.SOUTH);
		
		ArrayList<ArrayList<String>> gameplacedata 
		 								=IOTextFile.readRecords("./map.txt");
		playground.setLayout(new GridLayout(gameplacedata.size(), 
				 							gameplacedata.get(0).size(), 0, 0));
		
		for(int i=0;i<gameplacedata.size();i++)
		{
			for(int j=0;j<gameplacedata.get(0).size();j++) 
			{
				int judge=Integer.parseInt(gameplacedata.get(i).get(j));
				GamePlaceObject gamePlaceObject=null;
				switch(judge) 
				{
					case 0:
						gamePlaceObject=new Road();
						break;
					case 1:
						double random=Math.random();
						if(random<0.2)
							gamePlaceObject=new Love();
						else if(random<0.4&&random>0.2)
							gamePlaceObject=new Ghost();
						else 
							gamePlaceObject=new Wall();
						break;
					case 2:
						gamePlaceObject=new End();
						break;
						
				}
				gamePlaceObject.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						GamePlaceObject x=(GamePlaceObject)e.getSource();
						x.Changelife();
						progressBar.setValue(x.getCurrentlife());
						if(x.getCurrentlife()<=0) {
							JOptionPane.showMessageDialog(null, "你輸了");
							System.exit(0);
						}
					}
				});
				playground.add(gamePlaceObject);
			}
		}
		 
	}
	
}
