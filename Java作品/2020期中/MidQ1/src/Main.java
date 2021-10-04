/*
 * 107403549 資管三 涂建名
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sun.font.ExtendedTextLabel;
import sun.java2d.pipe.hw.ExtendedBufferCapabilities;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;

public class Main {

	private JFrame frame;
	private JLabel enter_left_position_label;
	private JLabel label;
	private JTextField picture_round_width;
	private JTextField picture_round_height;
	private PaintPanel centerPanel;
	private boolean getPoint_start=false;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 764, 583);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel UpperPanel = new JPanel();
		frame.getContentPane().add(UpperPanel, BorderLayout.NORTH);
		GridBagLayout gbl_UpperPanel = new GridBagLayout();
		gbl_UpperPanel.columnWidths = new int[]{210, 0, 0, 0, 0, 0, 0};
		gbl_UpperPanel.rowHeights = new int[]{23, 29, 0, 0};
		gbl_UpperPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_UpperPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		UpperPanel.setLayout(gbl_UpperPanel);
		
		enter_left_position_label = new JLabel("\u5DE6\u4E0A\u5EA7\u6A19");
		GridBagConstraints gbc_enter_left_position_label = new GridBagConstraints();
		gbc_enter_left_position_label.anchor = GridBagConstraints.EAST;
		gbc_enter_left_position_label.insets = new Insets(0, 0, 5, 5);
		gbc_enter_left_position_label.gridx = 0;
		gbc_enter_left_position_label.gridy = 0;
		UpperPanel.add(enter_left_position_label, gbc_enter_left_position_label);
		
		final JTextField left_position_X_Field = new JTextField();
		GridBagConstraints gbc_left_position_X_Field = new GridBagConstraints();
		gbc_left_position_X_Field.insets = new Insets(0, 0, 5, 5);
		gbc_left_position_X_Field.gridx = 1;
		gbc_left_position_X_Field.gridy = 0;
		UpperPanel.add(left_position_X_Field, gbc_left_position_X_Field);
		left_position_X_Field.setColumns(10);
		
		final JTextField left_position_Y_Field = new JTextField();
		GridBagConstraints gbc_left_position_Y_Field = new GridBagConstraints();
		gbc_left_position_Y_Field.insets = new Insets(0, 0, 5, 5);
		gbc_left_position_Y_Field.gridx = 2;
		gbc_left_position_Y_Field.gridy = 0;
		UpperPanel.add(left_position_Y_Field, gbc_left_position_Y_Field);
		left_position_Y_Field.setColumns(10);
		
		JButton button = new JButton("\u53D6\u9EDE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "開始取點");
				getPoint_start=true;
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.WEST;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 3;
		gbc_button.gridy = 0;
		UpperPanel.add(button, gbc_button);
		
		label = new JLabel("\u5716\u5F62\u9577\u5BEC");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		UpperPanel.add(label, gbc_label);
		
		final JTextField picture_weight_field = new JTextField();
		GridBagConstraints gbc_picture_weight_field = new GridBagConstraints();
		gbc_picture_weight_field.insets = new Insets(0, 0, 5, 5);
		gbc_picture_weight_field.gridx = 1;
		gbc_picture_weight_field.gridy = 1;
		UpperPanel.add(picture_weight_field, gbc_picture_weight_field);
		picture_weight_field.setColumns(10);
		
		final JTextField picture_height_field = new JTextField();
		GridBagConstraints gbc_picture_height_field = new GridBagConstraints();
		gbc_picture_height_field.insets = new Insets(0, 0, 5, 5);
		gbc_picture_height_field.gridx = 2;
		gbc_picture_height_field.gridy = 1;
		UpperPanel.add(picture_height_field, gbc_picture_height_field);
		picture_height_field.setColumns(10);
		
		JLabel label_1 = new JLabel("顏色:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 4;
		gbc_label_1.gridy = 1;
		UpperPanel.add(label_1, gbc_label_1);
		
		final JButton color_chooser = new JButton("");
		color_chooser.setMinimumSize(new Dimension(77, 9));
		color_chooser.setBackground(Color.BLACK);
		color_chooser.setForeground(Color.BLACK);
		GridBagConstraints gbc_color_chooser = new GridBagConstraints();
		gbc_color_chooser.anchor = GridBagConstraints.EAST;
		gbc_color_chooser.fill = GridBagConstraints.VERTICAL;
		gbc_color_chooser.insets = new Insets(0, 0, 5, 5);
		gbc_color_chooser.gridx = 4;
		gbc_color_chooser.gridy = 1;
		UpperPanel.add(color_chooser, gbc_color_chooser);
		color_chooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(frame, "選擇顏色", Color.black);
				if(color!=null) {
					color_chooser.setBackground(color);
					centerPanel.setCurrentColor(color);
				}
			}
		});
		
		JLabel picture_round_label = new JLabel("圓角長寬");
		GridBagConstraints gbc_picture_round_label = new GridBagConstraints();
		gbc_picture_round_label.anchor = GridBagConstraints.EAST;
		gbc_picture_round_label.insets = new Insets(0, 0, 0, 5);
		gbc_picture_round_label.gridx = 0;
		gbc_picture_round_label.gridy = 2;
		UpperPanel.add(picture_round_label, gbc_picture_round_label);
		
		picture_round_width = new JTextField();
		picture_round_width.setColumns(10);
		GridBagConstraints gbc_picture_round_width = new GridBagConstraints();
		gbc_picture_round_width.insets = new Insets(0, 0, 0, 5);
		gbc_picture_round_width.gridx = 1;
		gbc_picture_round_width.gridy = 2;
		UpperPanel.add(picture_round_width, gbc_picture_round_width);
		
		picture_round_height = new JTextField();
		picture_round_height.setColumns(10);
		GridBagConstraints gbc_picture_round_height = new GridBagConstraints();
		gbc_picture_round_height.insets = new Insets(0, 0, 0, 5);
		gbc_picture_round_height.gridx = 2;
		gbc_picture_round_height.gridy = 2;
		UpperPanel.add(picture_round_height, gbc_picture_round_height);
		
		JButton button_1 = new JButton("繪製圖形");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Point p=new Point();
					p.x=Integer.parseInt(left_position_X_Field.getText());
					p.y=Integer.parseInt(left_position_Y_Field.getText());
					int width=Integer.parseInt(picture_weight_field.getText());
					int height=Integer.parseInt(picture_height_field.getText());
					int roundwidth=Integer.parseInt(picture_round_width.getText());
					int roundheight=Integer.parseInt(picture_round_height.getText());
					if(p.x<0||p.x>centerPanel.getSize().width||p.y<0||p.y>centerPanel.getSize().height)
						JOptionPane.showMessageDialog(frame,"座標超過畫布，請輸入別的座標");
					else {
						centerPanel.setCurrentPainter(new RoundRect(color_chooser.getBackground()
								,p,width,height,roundwidth,roundheight));
						centerPanel.repaint();
						centerPanel.MouseReleasedProcess();
					}
				}catch (NumberFormatException ne) {
					JOptionPane.showMessageDialog(frame,"輸入值有誤或缺失，請檢察您的輸入");
				}
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.WEST;
		gbc_button_1.insets = new Insets(0, 0, 0, 5);
		gbc_button_1.gridx = 4;
		gbc_button_1.gridy = 2;
		UpperPanel.add(button_1, gbc_button_1);
		
		JButton button_2 = new JButton("上一步");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centerPanel.Updo();
			}
		});
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.anchor = GridBagConstraints.WEST;
		gbc_button_2.gridx = 5;
		gbc_button_2.gridy = 2;
		UpperPanel.add(button_2, gbc_button_2);
		
		JPanel ButtonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) ButtonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		frame.getContentPane().add(ButtonPanel, BorderLayout.SOUTH);
		
		final JLabel position_label = new JLabel("\u6E38\u6A19\u4F4D\u7F6E:");
		ButtonPanel.add(position_label);
		
		centerPanel=new PaintPanel();
		frame.getContentPane().add(centerPanel,BorderLayout.CENTER);
		centerPanel.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				position_label.setText("\u6E38\u6A19\u4F4D\u7F6E:("+e.getX()+","+e.getY()+")");
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if(getPoint_start==false)
				{
					position_label.setText("\u6E38\u6A19\u4F4D\u7F6E:("+e.getX()+","+e.getY()+")");
					centerPanel.getCurrentPainter().setFinishPoint(e.getPoint());
					centerPanel.repaint();
				}
			}
		});
		centerPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(getPoint_start==true)
				{
					left_position_X_Field.setText(Integer.toString(e.getX()));
					left_position_Y_Field.setText(Integer.toString(e.getY())); 
					JOptionPane.showMessageDialog(frame, "取點成功");
					getPoint_start=false;
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(getPoint_start==false)
					centerPanel.MousePressProcess(e.getPoint());
			}	
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if(getPoint_start==false)
					centerPanel.MouseReleasedProcess();
			}
			
			public void mouseExited(MouseEvent e) {
				position_label.setText("\u6E38\u6A19\u4F4D\u7F6E:畫布外");
			}
			
		});
		
		
	}

}
