/*
 * 資管三 107403549 涂建名
 */

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Main
{

	private JFrame contacts;
	private JTable resultTable;
	private static final String DEFAULT_QUERY = "SELECT name FROM people";
	private static ResultSetTableModel tableModel;

	private ArrayList<Member> result;
	private MemberHelper mh = MemberHelper.getHelper();
	private int numberOfResult;

	private JPanel addDialog = new JPanel(new GridLayout(3, 2, 10, 10));
	private JLabel nameLabel = new JLabel("姓名");
	private JLabel typeLabel = new JLabel("類型");
	private JLabel phoneLabel = new JLabel("電話");
	private JTextField nameField = new JTextField(15);
	private JTextField phoneField = new JTextField(15);
	private String typeArr[] = Member.type_arr;
	private JComboBox<String> typeComboBox = new JComboBox<String>(typeArr);
	private JPanel northPanel;
	private JScrollPane scrollPane;
	private JLabel contactsLabel;
	private JLabel plusLabel;
	private JTextField searchField;
	private JButton searchbtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{

		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Main window = new Main();
					window.contacts.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main()
	{
		try
		{
			tableModel = new ResultSetTableModel(DEFAULT_QUERY);
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		contacts = new JFrame();
		contacts.setTitle("Contacts");
		contacts.setBounds(100, 100, 533, 610);
		contacts.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		northPanel = new JPanel();
		contacts.getContentPane().add(northPanel, BorderLayout.NORTH);
		GridBagLayout gbl_northPanel = new GridBagLayout();
		gbl_northPanel.columnWidths = new int[] { 345, 175, 0 };
		gbl_northPanel.rowHeights = new int[] { 45, 33, 0 };
		gbl_northPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_northPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		northPanel.setLayout(gbl_northPanel);

		contactsLabel = new JLabel("Contacts");
		contactsLabel.setFont(new Font("標楷體", Font.PLAIN, 28));
		GridBagConstraints gbc_contactsLabel = new GridBagConstraints();
		gbc_contactsLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_contactsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_contactsLabel.gridx = 0;
		gbc_contactsLabel.gridy = 0;
		northPanel.add(contactsLabel, gbc_contactsLabel);

		plusLabel = new JLabel("+");
		plusLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		plusLabel.setFont(new Font("微軟正黑體", Font.BOLD, 50));
		GridBagConstraints gbc_plusLabel = new GridBagConstraints();
		gbc_plusLabel.insets = new Insets(0, 0, 5, 0);
		gbc_plusLabel.gridx = 1;
		gbc_plusLabel.gridy = 0;
		northPanel.add(plusLabel, gbc_plusLabel);

		searchField = new JTextField();
		GridBagConstraints gbc_searchField = new GridBagConstraints();
		gbc_searchField.insets = new Insets(0, 0, 0, 5);
		gbc_searchField.fill = GridBagConstraints.BOTH;
		gbc_searchField.gridx = 0;
		gbc_searchField.gridy = 1;
		northPanel.add(searchField, gbc_searchField);
		searchField.setColumns(10);

		searchbtn = new JButton("Search");
		searchbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				String executeSQL = "SELECT name FROM people WHERE phone LIKE \"" + searchField.getText()
						+ "%\" OR name LIKE \"" + searchField.getText() + "%\"";
				try
				{
					tableModel.setQuery(executeSQL);
				} catch (IllegalStateException | SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_searchbtn = new GridBagConstraints();
		gbc_searchbtn.gridx = 1;
		gbc_searchbtn.gridy = 1;
		northPanel.add(searchbtn, gbc_searchbtn);

		addDialog.add(nameLabel);
		addDialog.add(nameField);
		addDialog.add(typeLabel);
		addDialog.add(typeComboBox);
		addDialog.add(phoneLabel);
		addDialog.add(phoneField);

		resultTable = new JTable(tableModel);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
		resultTable.setRowSorter(sorter);
		resultTable.setRowHeight(50);

		scrollPane = new JScrollPane(
				resultTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
		);
		contacts.getContentPane().add(scrollPane, BorderLayout.CENTER);

		resultTable.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JTable table = (JTable) e.getSource();
				int rowIndex = table.rowAtPoint(e.getPoint());
				String name = table.getValueAt(rowIndex, 0).toString();

				if (table.getSelectedRow() != -1)
				{
					int optionSelected = showMemberDetails(e, name);
					if (optionSelected == 0)
					{
						result = mh.getMemberByName(name);
						numberOfResult = result.size();
						if (numberOfResult != 0)
						{
							Member m = result.get(0);
							nameField.setText(m.getName());
							typeComboBox.setSelectedItem(m.getType());
							phoneField.setText(m.getPhone());
						}
						showCreateAndUpdateDialog(0, name);
					} else if (optionSelected == 1)
					{
						mh.deleteByName(name);
						refreshTable();
					}
				}

			}
		});

		plusLabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				showCreateAndUpdateDialog(1, "");
			}
		});

	}

	private void showCreateAndUpdateDialog(int featureCode, String oldName)
	{
		int ok = -1;
		String featuresName[] = { "更新", "新增" };
		String option[] = { featuresName[featureCode], "取消" };
		boolean nameValid = false;
		boolean phoneValid = false;
		String PhoneRule = "09[0-9]{8}";
		String housePhoneRule = "0[0-9]{8}|0[0-9]{9}";
		int row = 0;
		System.out.println("test");

		if (featureCode == 1)
		{
			nameField.setText("");
			typeComboBox.setSelectedIndex(0);
			phoneField.setText("");
		}

		do
		{
			ok = JOptionPane.showOptionDialog(
					this.contacts, this.addDialog, "新增或更新欄位", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, option, null
			);

			if (ok == JOptionPane.OK_OPTION)
			{
				if (featureCode == 0)
				{
					nameValid = true;
				} else
				{
					if (mh.getMemberByName(nameField.getText()).size() == 0)
						nameValid = true;
					else
						JOptionPane.showMessageDialog(
								this.contacts, "聯絡人已存在，請重新輸入名稱", "名稱錯誤", JOptionPane.WARNING_MESSAGE
						);
				}

				if (
					(typeComboBox.getSelectedIndex() == 2 && phoneField.getText().matches(PhoneRule))
							|| (typeComboBox.getSelectedIndex() != 2 && phoneField.getText().matches(housePhoneRule))
				)
				{
					phoneValid = true;
				} else
				{
					JOptionPane.showMessageDialog(this.contacts, "您的電話格式錯誤，請重新輸入", "電話錯誤", JOptionPane.WARNING_MESSAGE);
				}
			}

		} while (ok == JOptionPane.OK_OPTION && (!phoneValid || !nameValid));

		if (ok == JOptionPane.OK_OPTION)
		{
			Member member = new Member(nameField.getText(), typeComboBox.getSelectedIndex(), phoneField.getText());

			if (featureCode == 0)
			{
				row = mh.update(member, oldName);
			} else
			{
				row = mh.create(member);
			}
		}
		refreshTable();
	}

	private int showMemberDetails(MouseEvent e, String name)
	{
		result = mh.getMemberByName(name);
		numberOfResult = result.size();
		int optionSelected = -1;

		if (numberOfResult != 0)
		{
			Member m = result.get(0);
			String options[] = { "Update", "Delete" };
			String message = m.getType() + "：" + m.getPhone();
			optionSelected = JOptionPane.showOptionDialog(
					this.contacts, message, name, JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
					options, null
			);
		}
		return optionSelected;
	}

	private void refreshTable()
	{
		try
		{
			tableModel.setQuery(DEFAULT_QUERY);
		} catch (IllegalStateException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
