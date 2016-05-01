import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.nio.file.attribute.GroupPrincipal;
/*
 * @author Tanapon Meesat
 */
public class ConverterUI extends JFrame {
	/**
	 * attribute
	 */
	private JPanel mainPanel;
	private JButton convertButton;
	private UnitConverter unitconverter;
	private UnitFactory singleton = UnitFactory.getInstance();
	private JTextField leftField;
	private JTextField rightField;
	private JComboBox<Unit> leftUnitBox = new JComboBox<>();
	private JComboBox<Unit> rightUnitBox = new JComboBox<>();
	private JRadioButton RtoLButton;
	private JRadioButton LtoRButton;
	private JRadioButton autoButton;
	private JMenuItem lengthMenu;
	private JMenuItem areaMenu;
	private JMenuItem weightMenu;
	private JMenuItem tempMenu;
	UnitType utype = UnitType.Length;


	/**
	 * setTitle and run method init
	 */
	public ConverterUI(UnitConverter uc) {
		this.unitconverter = uc;
		this.setTitle("Distance Converter");
		initComponents();
	}
	/**
	 * Init GUI all compinents 
	 */
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 130);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUnitType = new JMenu("Unit Type");
		menuBar.add(mnUnitType);
		
		lengthMenu = new JMenuItem("Length");
		lengthMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				utype = UnitType.Length;
				Unit[] a = singleton.getUnits(utype);
				leftUnitBox.setModel(new DefaultComboBoxModel(a));
				rightUnitBox.setModel(new DefaultComboBoxModel(a));
				
			}
		});
		mnUnitType.add(lengthMenu);
		
		areaMenu = new JMenuItem("Area");
		areaMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				utype = UnitType.Area;
				Unit[] a = singleton.getUnits(utype);
				leftUnitBox.setModel(new DefaultComboBoxModel(a));
				rightUnitBox.setModel(new DefaultComboBoxModel(a));
				
			}
		});
		mnUnitType.add(areaMenu);
		
		weightMenu = new JMenuItem("Weight");
		weightMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				utype = UnitType.Weight;
				Unit[] a = singleton.getUnits(utype);
				leftUnitBox.setModel(new DefaultComboBoxModel(a));
				rightUnitBox.setModel(new DefaultComboBoxModel(a));
				
			}
		});
		mnUnitType.add(weightMenu);
		
		tempMenu = new JMenuItem("Time");
		tempMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				utype = UnitType.Time;
				Unit[] a = singleton.getUnits(utype);
				leftUnitBox.setModel(new DefaultComboBoxModel(a));
				rightUnitBox.setModel(new DefaultComboBoxModel(a));
				
			}
		});
		mnUnitType.add(tempMenu);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		Unit[] lengths = singleton.getUnits(utype);

		leftField = new JTextField();
		leftField.setBounds(10, 11, 150, 23);
		mainPanel.add(leftField);
		leftField.setColumns(10);

		leftUnitBox = new JComboBox<Unit>(lengths);
		leftUnitBox.setBounds(163, 11, 89, 23);
		mainPanel.add(leftUnitBox);
		
		JLabel equalLb = new JLabel("=");
		equalLb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		equalLb.setBounds(260, 16, 16, 12);
		mainPanel.add(equalLb);

		rightField = new JTextField();
		rightField.setBounds(284, 11, 150, 23);
		rightField.setEditable(false);
		mainPanel.add(rightField);
		rightField.setColumns(10);

		rightUnitBox = new JComboBox<Unit>(lengths);
		rightUnitBox.setBounds(437, 11, 89, 23);
		mainPanel.add(rightUnitBox);

		JButton convertButton = new JButton("Convert!");
		convertButton.setBounds(536, 11, 89, 23);
		mainPanel.add(convertButton);

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		clearButton.setBounds(635, 11, 89, 23);
		mainPanel.add(clearButton);

		LtoRButton = new JRadioButton("Left -> Right");
		LtoRButton.setBounds(120, 41, 130, 23);
		mainPanel.add(LtoRButton);

		RtoLButton = new JRadioButton("Right -> Left");
		RtoLButton.setBounds(260, 41, 130, 23);
		mainPanel.add(RtoLButton);

		autoButton = new JRadioButton("Auto-Detection");
		autoButton.setBounds(400, 41, 150, 23);
		mainPanel.add(autoButton);

		ButtonGroup groupRadio = new ButtonGroup();
		groupRadio.add(RtoLButton);
		groupRadio.add(LtoRButton);
		groupRadio.add(autoButton);

		this.setVisible(true);

		ActionListener convertLst = new ConvertButtonListener();
		ActionListener claerLst = new ClearButtonListener();
		ActionListener rightToLeft = new RightToLeftListener();
		ActionListener leftToRight = new LeftToRightListener();
		ActionListener autoDetect = new autoListener();
		KeyListener keyboardLst = new ConvertButtonListener();
		convertButton.addActionListener(convertLst);
		leftField.addKeyListener(keyboardLst);
		rightField.addKeyListener(keyboardLst);
		clearButton.addActionListener(claerLst);	
		RtoLButton.addActionListener(rightToLeft);
		LtoRButton.addActionListener(leftToRight);
		autoButton.addActionListener(autoDetect);
		LtoRButton.setSelected(true);
	}
	/**
	 * 
	 * @author tanapon
	 *	invoke ActionListener and KeyListener
	 */
	class ConvertButtonListener implements ActionListener, KeyListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (LtoRButton.isSelected()) {
				String s = leftField.getText().trim();
				if (s.length() > 0) {
					try {
						double value = Double.valueOf(s);
						Unit leftUnit = (Unit) leftUnitBox.getSelectedItem();
						Unit rightUnit = (Unit) rightUnitBox.getSelectedItem();
						double valueOut = unitconverter.convert(value, leftUnit, rightUnit);
						rightField.setText(String.valueOf(valueOut));
					} catch (NumberFormatException exception) {
						System.out.println("Wrong input!");
					} catch (NullPointerException exception) {
						System.out.println("null");
					}
				}
			} else {
				String s = rightField.getText().trim();
				if (s.length() > 0) {
					try {
						double value = Double.valueOf(s);
						Unit leftUnit = (Unit) leftUnitBox.getSelectedItem();
						Unit rightUnit = (Unit) rightUnitBox.getSelectedItem();
						double valueOut = unitconverter.convert(value, rightUnit, leftUnit);
						leftField.setText(String.valueOf(valueOut));
					} catch (NumberFormatException exception) {
						System.out.println("Wrong input!");
					} catch (NullPointerException exception) {
						System.out.println("null");
					}
				}
			}
		}
		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {
			if(autoButton.isSelected()){
				if (leftField.hasFocus()) {
					String s = leftField.getText().trim();
					if (s.length() > 0) {
						try {
							double value = Double.parseDouble(s);
							Unit leftUnit = (Unit) leftUnitBox.getSelectedItem();
							Unit rightUnit = (Unit) rightUnitBox.getSelectedItem();
							double valueOut = unitconverter.convert(value, leftUnit, rightUnit);
							rightField.setText(String.valueOf(valueOut));
						} catch (NumberFormatException exception) {
							System.out.println("Wrong input!");
						} catch (NullPointerException exception) {
							System.out.println("Null");
						}
					}
				} else {
					String s = rightField.getText().trim();
					if (s.length() > 0) {
						try {
							double value = Double.valueOf(s);
							Unit leftUnit = (Unit) leftUnitBox.getSelectedItem();
							Unit rightUnit = (Unit) rightUnitBox.getSelectedItem();
							double valueOut = unitconverter.convert(value, rightUnit, leftUnit);
							leftField.setText(String.valueOf(valueOut));
						} catch (NumberFormatException exception) {
							System.out.println("Wrong input!");
						} catch (NullPointerException exception) {
							System.out.println("Null");
						}
					}
				}	
			}

		}
	}
	/**
	 * 
	 * @author tanapon
	 *	run this method by invoking to clear textfield
	 */
	class ClearButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			leftField.setText("");
			rightField.setText("");
		}
	}
	/**
	 * 
	 * @author tanapon
	 *	run this method to convert rightside to leftside
	 */
	class RightToLeftListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			leftField.setText("");
			leftField.setEditable(false);
			rightField.setText("");
			rightField.setEditable(true);
		}
	}
	/**
	 * 
	 * @author tanapon
	 *	run this method to convert leftside to rightside
	 */
	class LeftToRightListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			leftField.setText("");
			leftField.setEditable(true);
			rightField.setText("");
			rightField.setEditable(false);
		}

	}
	/**
	 * 
	 * @author tanapon
	 *	run this method to make it convert automatically from anyside
	 */
	class autoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			leftField.setText("");
			leftField.setEditable(true);
			rightField.setText("");
			rightField.setEditable(true);
		}

	}
	/*
	 * method main to run this program
	 */
	public static void main(String[] args) {
		ConverterUI run = new ConverterUI(new UnitConverter());
	}
}