
/*
 * 資管三 107403549 涂建名
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

	private JFrame frame;
	private JButton remove_choose_button;
	private JButton new_fish_button;
	private JButton new_turtle_button;
	private JButton cancel_all_button;
	private JPanel bottenPanel;
	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel aquarium_panel;
	private JPanel status_panel;
	private static JLabel current_button_label;
	private static JLabel current_fish_panel;
	private static JLabel current_turtle_panel;

	private static String current_button_text = "        ";
	private ArrayList<Fish> fishs = new ArrayList<Fish>();
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private static int fish_num[] = { 0 };
	private static int turtle_num[] = { 0 };
	private static int shark_num[]= {0};
	private static int fork_num[]= {0};
	private static int catch_num[]= {0};
	private boolean removeswitch = false;

	ExecutorService executorService = Executors.newCachedThreadPool();
	private JButton newSharkBtn;
	private JButton removeForkBtn;
	private static JLabel current_shark_panel;
	private static JLabel catchFishPanel;
	private static JLabel current_fork_panel;

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
		frame.setAlwaysOnTop(true);
		frame.setTitle("\u6C34\u65CF\u7BB1");
		frame.setBounds(100, 100, 801, 547);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

		bottenPanel = new JPanel();
		northPanel.add(bottenPanel);
		bottenPanel.setLayout(new GridLayout(2, 2, 0, 0));

		new_fish_button = new JButton("\u65B0\u589E\u9B5A");
		new_fish_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Fork.removeswitch = false;
				Fork.removechoose=false;
				Animal.setRemoveswitch(false);
				current_button_text = new_fish_button.getText();
				UpdateStatus();
			}
		});
		bottenPanel.add(new_fish_button);
		
		new_turtle_button = new JButton("新增烏龜");
		new_turtle_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Fork.removeswitch = false;
				Fork.removechoose=false;
				Animal.setRemoveswitch(false);
				current_button_text = new_turtle_button.getText();
				UpdateStatus();
			}
		});
		bottenPanel.add(new_turtle_button);
		
		newSharkBtn = new JButton("新增鯊魚");
		newSharkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fork.removeswitch = false;
				Fork.removechoose=false;
				Animal.setRemoveswitch(false);
				current_button_text = newSharkBtn.getText();
				UpdateStatus();
			}
		});
		bottenPanel.add(newSharkBtn);
		
		JButton newForkBtn = new JButton("放釣竿");
		newForkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fork.removeswitch = false;
				Fork.removechoose=false;
				current_button_text = newForkBtn.getText();
				UpdateStatus();
			}
		});
		bottenPanel.add(newForkBtn);
		
		removeForkBtn = new JButton("收釣竿");
		removeForkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current_button_text =removeForkBtn.getText();
				Fork.removeswitch = true;
				Fork.removechoose=false;
				UpdateStatus();
			}
		});
		bottenPanel.add(removeForkBtn);

		remove_choose_button = new JButton("\u79FB\u9664\u9078\u53D6");
		bottenPanel.add(remove_choose_button);
		remove_choose_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Animal.setRemoveswitch(true);
				Fork.removeswitch=true;
				current_button_text = remove_choose_button.getText();
				UpdateStatus();
			}
		});

		cancel_all_button = new JButton("移除全部");
		bottenPanel.add(cancel_all_button);
		cancel_all_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Animal.setRemoveswitch(false);
				aquarium_panel.removeAll();
				aquarium_panel.updateUI();
				fish_num[0] = 0;
				turtle_num[0] = 0;
				shark_num[0] = 0;
				catch_num[0]=0;
				fork_num[0]=0;
				current_button_text = cancel_all_button.getText();
				executorService.shutdownNow();
				UpdateStatus();
				executorService = Executors.newCachedThreadPool();
			}
		});

		centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		aquarium_panel = new JPanel();
		aquarium_panel.setForeground(Color.DARK_GRAY);
		aquarium_panel.setBackground(SystemColor.textHighlight);
		centerPanel.add(aquarium_panel, BorderLayout.CENTER);
		aquarium_panel.setLayout(null);
		aquarium_panel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (current_button_text == "\u65B0\u589E\u9B5A") {
					Fish new_fish = new Fish(aquarium_panel, e.getPoint());
					fish_num[0]++;
					fishs.add(new_fish);
					executorService.execute(new_fish);
					animals.add(new_fish);
					UpdateStatus();
				} else if (current_button_text == "新增烏龜") {
					Turtle new_turtle = new Turtle(aquarium_panel, e.getPoint());
					turtle_num[0]++;
					executorService.execute(new_turtle);
					animals.add(new_turtle);
					UpdateStatus();
				}else if(current_button_text == "新增鯊魚") {
					Shark new_shark = new Shark(aquarium_panel, e.getPoint(),fishs);
					shark_num[0]++;
					executorService.execute(new_shark);
					animals.add(new_shark);
					UpdateStatus();
				}else if(current_button_text == "放釣竿") {
					Fork new_fork = new Fork(aquarium_panel, e.getPoint(),animals);
					fork_num[0]++;
					executorService.execute(new_fork);
					UpdateStatus();
				}
				
			}
		});

		status_panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) status_panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		centerPanel.add(status_panel, BorderLayout.NORTH);

		current_button_label = new JLabel();
		current_button_label.setForeground(Color.BLUE);
		status_panel.add(current_button_label);

		current_fish_panel = new JLabel();
		current_fish_panel.setForeground(Color.BLUE);
		status_panel.add(current_fish_panel);

		current_turtle_panel = new JLabel();
		current_turtle_panel.setForeground(Color.BLUE);
		status_panel.add(current_turtle_panel);
		
		current_shark_panel = new JLabel("鯊魚數量：0");
		current_shark_panel.setForeground(Color.BLUE);
		status_panel.add(current_shark_panel);
		
		current_fork_panel = new JLabel("釣竿數量：0");
		current_fork_panel.setForeground(Color.BLUE);
		status_panel.add(current_fork_panel);
		
		catchFishPanel = new JLabel("捕獲數量：0");
		catchFishPanel.setForeground(Color.BLUE);
		status_panel.add(catchFishPanel);

		UpdateStatus();

		Animal.setRemoveswitch(false);
		Animal.connectNum(fish_num, turtle_num,shark_num);
		Fork.connectNum(fish_num, turtle_num, shark_num, fork_num, catch_num);
	}

	public static void UpdateStatus() {
		current_button_label.setText(String.format("目前功能 :  %s    ", current_button_text));
		current_fish_panel.setText(String.format("魚的數量 : %d  ", fish_num[0]));
		current_turtle_panel.setText(String.format("烏龜數量 : %d", turtle_num[0]));
		current_shark_panel.setText(String.format("鯊魚數量 : %d", shark_num[0]));
		current_fork_panel.setText(String.format("釣竿數量 : %d", fork_num[0]));
		catchFishPanel.setText(String.format("捕獲數量 : %d", catch_num[0]));
	}
}
