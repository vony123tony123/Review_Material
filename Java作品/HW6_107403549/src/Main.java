
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
	private static int fish_num[] = { 0 };
	private static int turtle_num[] = { 0 };
	private boolean removeswitch = false;

	ExecutorService executorService = Executors.newCachedThreadPool();

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
				Animal.setRemoveswitch(false);
				current_button_text = new_fish_button.getText();
				UpdateStatus();
			}
		});
		bottenPanel.add(new_fish_button);

		remove_choose_button = new JButton("\u79FB\u9664\u9078\u53D6");
		bottenPanel.add(remove_choose_button);
		remove_choose_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Animal.setRemoveswitch(true);
				current_button_text = remove_choose_button.getText();
				UpdateStatus();
			}
		});

		new_turtle_button = new JButton("新增烏龜");
		new_turtle_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Animal.setRemoveswitch(false);
				current_button_text = new_turtle_button.getText();
				UpdateStatus();
			}
		});
		bottenPanel.add(new_turtle_button);

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
					executorService.execute(new_fish);
					UpdateStatus();
				} else if (current_button_text == "新增烏龜") {
					Turtle new_turtle = new Turtle(aquarium_panel, e.getPoint());
					turtle_num[0]++;
					executorService.execute(new_turtle);
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

		UpdateStatus();

		Animal.setRemoveswitch(false);
		Animal.connectNum(fish_num, turtle_num);
	}

	public static void UpdateStatus() {
		current_button_label.setText(String.format("目前功能 :  %s    ", current_button_text));
		current_fish_panel.setText(String.format("魚的數量 : %d  ", fish_num[0]));
		current_turtle_panel.setText(String.format("烏龜數量 : %d", turtle_num[0]));
	}

}
