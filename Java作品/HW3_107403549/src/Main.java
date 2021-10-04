/*
 * 資管三 107403549 涂建名
 * 
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import sun.nio.cs.FastCharsetProvider;
import sun.print.PSPrinterJob.PluginPrinter;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private JLabel DateLabel;
	private JLabel WeatherLabel;
	private JLabel TitleLabel;
	private JPanel TitlePanel;
	private JPanel ToolPanel_VIEW;
	private JButton EditButton;
	private JButton NewButton;
	private JTextArea ContentArea;
	private JComboBox WeatherCombo;
	private JPanel ToolPanel_EDIT;
	private JButton Save_Button;
	private JButton Save_As_Button;
	private JButton Import_Button;
	private JButton Cancel_Button;
	private JPanel ToolPanel;
	private JLabel LoveLabel;
	private PostSerializable currentPostSerializable;

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
		frame.setTitle("\u806F\u7D61\u7C3F\r\n");
		frame.setBounds(100, 100, 758, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TitlePanel = new JPanel();
		TitlePanel.setBackground(new Color(50, 205, 50));
		frame.getContentPane().add(TitlePanel, BorderLayout.NORTH);
		GridBagLayout gbl_TitlePanel = new GridBagLayout();
		gbl_TitlePanel.columnWidths = new int[]{171, 0};
		gbl_TitlePanel.rowHeights = new int[]{31, 31, 31, 0};
		gbl_TitlePanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_TitlePanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		TitlePanel.setLayout(gbl_TitlePanel);
		
		TitleLabel = new JLabel("\u806F\u7D61\u7C3F");
		TitleLabel.setFont(new Font("微軟正黑體", Font.BOLD | Font.ITALIC, 23));
		GridBagConstraints gbc_TitleLabel = new GridBagConstraints();
		gbc_TitleLabel.fill = GridBagConstraints.BOTH;
		gbc_TitleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_TitleLabel.gridx = 0;
		gbc_TitleLabel.gridy = 0;
		TitlePanel.add(TitleLabel, gbc_TitleLabel);
		
		WeatherCombo = new JComboBox();
		WeatherCombo.setVisible(false);
		WeatherCombo.setMaximumSize(new Dimension(70, 70));
		WeatherCombo.setModel(new DefaultComboBoxModel(new String[] {"\u6674\u5929", "\u9670\u5929", "\u96E8\u5929"}));
		GridBagConstraints gbc_WeatherCombo = new GridBagConstraints();
		gbc_WeatherCombo.fill = GridBagConstraints.BOTH;
		gbc_WeatherCombo.insets = new Insets(0, 0, 5, 0);
		gbc_WeatherCombo.gridx = 0;
		gbc_WeatherCombo.gridy = 1;
		TitlePanel.add(WeatherCombo, gbc_WeatherCombo);
		
		WeatherLabel = new JLabel("\u5929\u6C23");
		WeatherLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		WeatherLabel.setFont(new Font("微軟正黑體", Font.BOLD | Font.ITALIC, 20));
		GridBagConstraints gbc_WeatherLabel = new GridBagConstraints();
		gbc_WeatherLabel.fill = GridBagConstraints.BOTH;
		gbc_WeatherLabel.insets = new Insets(0, 0, 5, 0);
		gbc_WeatherLabel.gridx = 0;
		gbc_WeatherLabel.gridy = 1;
		TitlePanel.add(WeatherLabel, gbc_WeatherLabel);
		
		DateLabel = new JLabel("");
		GridBagConstraints gbc_DateLabel = new GridBagConstraints();
		gbc_DateLabel.fill = GridBagConstraints.BOTH;
		gbc_DateLabel.gridx = 0;
		gbc_DateLabel.gridy = 2;
		TitlePanel.add(DateLabel, gbc_DateLabel);
		
		ContentArea = new JTextArea();
		ContentArea.setBackground(new Color(50, 205, 50));
		ContentArea.setEditable(false);
		frame.getContentPane().add(ContentArea, BorderLayout.CENTER);
		
		ToolPanel = new JPanel();
		frame.getContentPane().add(ToolPanel, BorderLayout.SOUTH);
		
		ToolPanel_VIEW = new JPanel();
		ToolPanel.setLayout(new BoxLayout(ToolPanel, BoxLayout.X_AXIS));
		ToolPanel.add(ToolPanel_VIEW);
		ToolPanel_VIEW.setBackground(new Color(255, 127, 80));
		GridBagLayout gbl_ToolPanel_VIEW = new GridBagLayout();
		gbl_ToolPanel_VIEW.columnWidths = new int[]{47, 448, 129, 114, 0};
		gbl_ToolPanel_VIEW.rowHeights = new int[]{31, 0};
		gbl_ToolPanel_VIEW.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_ToolPanel_VIEW.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		ToolPanel_VIEW.setLayout(gbl_ToolPanel_VIEW);
		
		LoveLabel = new JLabel("");
		GridBagConstraints gbc_LoveLabel = new GridBagConstraints();
		gbc_LoveLabel.insets = new Insets(0, 0, 0, 5);
		gbc_LoveLabel.gridx = 0;
		gbc_LoveLabel.gridy = 0;
		LoveLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(currentPostSerializable.getIsLike()==true)
				{
					switchLove(false);
					currentPostSerializable.setIsLike(false);
				}else {
					switchLove(true);
					currentPostSerializable.setIsLike(true);
				}
				IOPostSerializable.writeRecords(currentPostSerializable, "./post");
			}
		});
		ToolPanel_VIEW.add(LoveLabel, gbc_LoveLabel);
		
		EditButton = new JButton("\u7DE8\u8F2F");
		EditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMode(false);
				DateLabel.setText(new Date().toString());
			}
		});
		
		GridBagConstraints gbc_EditButton = new GridBagConstraints();
		gbc_EditButton.fill = GridBagConstraints.BOTH;
		gbc_EditButton.insets = new Insets(0, 0, 0, 5);
		gbc_EditButton.gridx = 2;
		gbc_EditButton.gridy = 0;
		ToolPanel_VIEW.add(EditButton, gbc_EditButton);
		
		NewButton = new JButton("\u65B0\u589E");
		NewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMode(false);
				ContentArea.setText("");
				DateLabel.setText(new Date().toString());
			}
		});
		GridBagConstraints gbc_NewButton = new GridBagConstraints();
		gbc_NewButton.fill = GridBagConstraints.BOTH;
		gbc_NewButton.gridx = 3;
		gbc_NewButton.gridy = 0;
		ToolPanel_VIEW.add(NewButton, gbc_NewButton);
		
		ToolPanel_EDIT = new JPanel();
		ToolPanel_EDIT.setVisible(false);
		ToolPanel_EDIT.setForeground(new Color(128, 0, 0));
		ToolPanel.add(ToolPanel_EDIT);
		ToolPanel_EDIT.setBackground(new Color(160, 82, 45));
		ToolPanel_EDIT.setLayout(new BoxLayout(ToolPanel_EDIT, BoxLayout.X_AXIS));
		
		Save_Button = new JButton("\u5132\u5B58");
		Save_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PostSerializable newPostSerializable=new PostSerializable(ContentArea.getText(),
						false, new Date());
				newPostSerializable.setWeather(WeatherCombo.getSelectedIndex());
				IOPostSerializable.writeRecords(newPostSerializable, "./post");
				refresh();
				switchMode(true);
			}
		});
		ToolPanel_EDIT.add(Save_Button);
		
		Save_As_Button = new JButton("\u53E6\u5B58\u65B0\u6A94");
		Save_As_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IOTextFile.addRecords(ContentArea.getText()
						, getFilePath(false).toString()+".txt");
			}
		});
		ToolPanel_EDIT.add(Save_As_Button);
		
		Import_Button = new JButton("\u532F\u5165\u5167\u5BB9");
		Import_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str=ContentArea.getText();
				str+="\n"+IOTextFile.readRecords(getFilePath(true).toString());
				ContentArea.setText(str);
			}
		});
		ToolPanel_EDIT.add(Import_Button);
		
		Cancel_Button = new JButton("\u53D6\u6D88");
		Cancel_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switchMode(true);
				refresh();
			}
		});
		ToolPanel_EDIT.add(Cancel_Button);
		
		refresh();
		
		int Modeset=JOptionPane.showConfirmDialog(null,"是否為發布者","登入",JOptionPane.YES_NO_OPTION);
		switch(Modeset) {
			case JOptionPane.NO_OPTION:
				EditButton.setVisible(false);
				NewButton.setVisible(false);
				LoveLabel.setEnabled(true);
				break;
			case JOptionPane.YES_OPTION:
				LoveLabel.setEnabled(false);
				break;
			case JOptionPane.CLOSED_OPTION:
				System.exit(1);
				break;
		}
	}
	
	private void refresh() {
		currentPostSerializable=IOPostSerializable.readRecords("./post");
		switchLove(currentPostSerializable.getIsLike());
		ContentArea.setText(currentPostSerializable.getContent());
		DateLabel.setText(currentPostSerializable.getEditTime().toString());
		switchWeather(currentPostSerializable.getWeather());
	}
	
	private void switchMode(boolean is_View)
	{
		if(is_View==true) {
			ToolPanel_VIEW.setVisible(true);
			ToolPanel_EDIT.setVisible(false);
			WeatherLabel.setVisible(true);
			WeatherCombo.setVisible(false);
			ContentArea.setEditable(false);
			ContentArea.setBackground(new Color(50, 205, 50));
		}else {
			ToolPanel_VIEW.setVisible(false);
			ToolPanel_EDIT.setVisible(true);
			WeatherLabel.setVisible(false);
			WeatherCombo.setVisible(true);
			ContentArea.setEditable(true);
			ContentArea.setBackground(Color.white);
		}
	}
	
	private void switchWeather(int i) {
		switch(i) {
			case 0:
				WeatherLabel.setIcon(new ImageIcon("./sunny.png"));
				break;
			case 1:
				WeatherLabel.setIcon(new ImageIcon("./cloudy.png"));
				break;
			case 2:
				WeatherLabel.setIcon(new ImageIcon("./rainy.png"));
				break;
		}
		
	}
	
	private void switchLove(boolean is_like)
	{
		if(is_like==true)
			LoveLabel.setIcon(
					new ImageIcon(
						getScaledImage(new ImageIcon("./like.png").getImage()
								, 20, 20)
						)
					);
		else {
			LoveLabel.setIcon(
					new ImageIcon(
						getScaledImage(new ImageIcon("./unlike.png").getImage()
								, 20, 20)
						)
					);
		}
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	private Path getFilePath(boolean is_Open)
	   {
		JFileChooser fileChooser = new JFileChooser("\u9078\u64c7\u5132\u5b58\u8cc7\u6599\u593e");
		int result;
	      // configure dialog allowing selection of a file or directory
		if(is_Open==false) {
	      
	      fileChooser.setFileSelectionMode(
	         JFileChooser.FILES_AND_DIRECTORIES);
	      result=fileChooser.showSaveDialog(frame);
		}else {
			
		      fileChooser.setFileSelectionMode(
		         JFileChooser.FILES_AND_DIRECTORIES);
		      result=fileChooser.showOpenDialog(frame);
		}
		
		if(result!=JOptionPane.CANCEL_OPTION)
	      return fileChooser.getSelectedFile().toPath();
		else {
			return null;
		}
	   } 

}
