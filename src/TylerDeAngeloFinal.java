import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TylerDeAngeloFinal extends JApplet {
	
	// Wouldn't stfu about it
	private static final long serialVersionUID = 1L;
	// HARDCODED INDEX
	private static final int DELIVERY_FEE = 0;
	private static final int DINNER_ITEM = 1;
	private static final int APPETIZER_ITEM = 2;
	private static final int BEVERAGE_ITEM = 3;
	private static final int DESSERT_ITEM = 4;
	
	private static final String FOUR_SPACES = "    ";
	private static final String newLine = "\n";
		
	// MAIN PANELS
	private JPanel mainPanel, centerPanel, headerPanel, footerPanel, westPanel,
			eastPanel;
	
	// Used for selection list
	private Vector<String> orderData;
   
	String[] drinksMenuItemsArr = {
			  "     Pepsi"
			, "     Dr. Pepper"
			, "     Coca Cola" 
			, "     Sparkling water"
			, "     Beer"
			, "     Wine"};
	
	String[] dessertMenuItemsArr = {
			 "    Apple Pie"
			,"    Cherry Pie"
			,"    Pumpkin Pie"
			,"    Pecan Pie"
			,"    Buttermilk Pie"
			,"    Coconut Cream Pie"};
	
	String[] dinnerMenuItemsArr = {
			 "    Lasagna" 
			,"    Linguini"
			,"    Spagetti"
			,"    Pizza"
			,"    Calzone"
			,"    Veal Parmesan"};
	
	String[] appetizerMenuItemsArr = {
			  "    Side Salad"
			, "    Bread Sticks"
			, "    Chips and Salsa"
			, "    Spinach Dip"
			, "    Bruschetta"
			, "    Stuffed Mushroom"};
	
	double[] drinksCostArr = {2.95, 2.95, 2.95, 2.95, 4.95, 4.95};
	double[] dessertCostArr = {7.95, 7.95, 7.95, 7.95, 8.95, 8.95};
	double[] dinnerCostArr = {12.95, 12.95, 12.95, 14.95, 15.95, 15.95};
	double[] appetizerCostArr = {6.95, 5.95, 8.95, 8.95, 9.95, 8.95};
	
	// String costs to display for the menu items
	String[] drinksCostArrString = { "$2.95  ", "$2.95  ", "$2.95  ", "$2.95  ",
			"$4.95  ", "$4.95  " };
	String[] dessertCostArrString = { "$7.95  ", "$7.95  ", "$7.95  ",
			"$7.95  ", "$8.95  ", "$8.95  " };
	String[] dinnerCostArrString = { "$12.95  ", "$12.95  ", "$12.95  ",
			"$14.95  ", "$15.95  ", "$15.95  " };
	String[] appetizerCostArrString = { "$6.95  ", "$5.95  ", "$8.95  ",
			"$8.95  ", "$9.95  ", "$8.95  " };

	// LABELS
	private JLabel headerLabel, drinksLabel, dessertLabel, dinnerLabel,
			appetizerLabel;

	private ImageIcon restPic, dinnerPic, dessertPic, headerPic;

	// BUTTONS
	private JButton btnClear, btnTotal;

	// TEXT FIELDS
	private JTextField fNameTextBox, lNameTextBox, addrTextBox;

	// Radio Button
	private JRadioButton pickUp, deliv;

	// Button Group
	private ButtonGroup btnGroup;

	// Check Boxes
	private JCheckBox tipBoxCheckBox;

	// Final detail Text Area
	private JTextArea finalDetailsList;

	private JList<String> drinkFoodItems_JList, drinkFoodCosts_JList,
			dessertFoodItems_JList, dessertFoodCosts_JList, dinnerItems_JList,
			dinnerItemsCosts_JList, appetizerItems_JList,
			appetizerItemsCosts_JList, orderDetailsList;

	private JScrollPane scrollPanel_menuDetailsList, scrollPanel_finalDetails;

	private JComboBox<String> tipAdd_ComboBox;

	private double tip;

	// COLOR PALLETTE
	Color redBkrg = new Color(255, 153, 153);
	Color yellowBkrg = new Color(255, 255, 153);
	Color greenBkrg = new Color(204, 255, 153);
	Color DgrayBkrg = new Color(150, 150, 150);
	Color LgrayBkrg = new Color(204, 204, 204);

	// FONTS
	Font largeFont = new Font("TimesNewRoman", Font.BOLD, 24);
	Font menuFont = new Font("TimesNewRoman", Font.PLAIN, 14);
	Font largeMenuFont = new Font("TimesNewRoman", Font.BOLD, 16);

	// BORDER
	Border lineBorder = new LineBorder(LgrayBkrg, 3);
	
	@Override
	public void init() {
		// Image Resources
		restPic = new ImageIcon(getClass().getResource("image/RestPic.jpg"));
		dinnerPic = new ImageIcon(getClass().getResource("image/Dinner.jpg"));
		dessertPic = new ImageIcon(getClass().getResource("image/Dessert.jpg"));
		headerPic = new ImageIcon(getClass().getResource("image/HeaderPic.jpg"));
		
		// Giving vector a purpose
		orderData = new Vector<String>(5,0);
		
		// Filling up with blank data to prevent ArrayOutOfBoundsException - DeAngelo
		orderData.add(DELIVERY_FEE, "Delivery Fee: $0.00");
		orderData.add(DINNER_ITEM, "Dinner Selection: $0.00");
		orderData.add(APPETIZER_ITEM, "Appetizer Selection: $0.00");
		orderData.add(BEVERAGE_ITEM, "Beverage Selection: $0.00");
		orderData.add(DESSERT_ITEM, "Dessert Item: $0.00");
		
		// Create Menu Layout - DeAngelo
		createHeaderPanel();
		createWestPanel();
		createEastPanel();
		createCenterPanel();
		createFooterPanel();
		createGUI();
		
		orderDetailsList.setListData(orderData);
	}

	private void createHeaderPanel() {
		headerPanel = new JPanel(new GridLayout(1, 3));
		headerPanel.setBackground(LgrayBkrg);

		// Header panel components
		headerLabel = new JLabel("Tyler Hunt and DeAngelo Mannie's Italian Restaurant");
		headerLabel.setHorizontalAlignment(0);
		headerLabel.setFont(largeFont);
		headerLabel.setForeground(Color.gray);
		headerPanel.add(headerLabel);
	}

	private void createWestPanel() {
		// West Panel
		westPanel = new JPanel(new GridLayout(3, 1));
		westPanel.setBackground(redBkrg);

		// West Sub Panels
		JPanel sub0_westPanel = new JPanel();
		sub0_westPanel.setBackground(redBkrg);

		JPanel sub1_westPanel = new JPanel(new GridLayout(6, 1));
		sub1_westPanel.setBackground(redBkrg);

		final JPanel sub2_westPanel = new JPanel(new GridLayout(6, 1));
		sub2_westPanel.setBackground(redBkrg);

		// Sub Panel for panel Pic
		sub0_westPanel.add(new JLabel(restPic));

		// West Panel Text Fields
		fNameTextBox = new JTextField();
		lNameTextBox = new JTextField();
		addrTextBox = new JTextField();

		// Add textbox fields to west sub panel 2
		sub1_westPanel.add(new JLabel("  First Name: "));
		sub1_westPanel.add(fNameTextBox);
		sub1_westPanel.add(new JLabel("  Last Name: "));
		sub1_westPanel.add(lNameTextBox);
		final JLabel address = new JLabel("  Address");
		sub1_westPanel.add(address);
		sub1_westPanel.add(addrTextBox);

		// Radio buttons for Pick-up or delivery
		pickUp = new JRadioButton("Pick-Up");
		deliv = new JRadioButton("Delivery");

		// default options for pickUp or deliv
		pickUp.setSelected(true);
		addrTextBox.setVisible(false);
		address.setVisible(false);
		
		// Button Group
		btnGroup = new ButtonGroup();
		btnGroup.add(deliv);
		btnGroup.add(pickUp);

		// Action Listener for switching to pick up checkbox
		pickUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pickUp.isSelected()) {
					addrTextBox.setVisible(false);
					address.setVisible(false);
					
					orderData.set(DELIVERY_FEE, "Delivery Fee: " + FOUR_SPACES + "$" + "0.00");
					orderDetailsList.setListData(orderData);
					updateGUI();
				}
			}
		});

		// Action Listener for switching to delivery checkbox
		deliv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (deliv.isSelected() && !addrTextBox.isVisible()) {
					addrTextBox.setVisible(true);
					address.setVisible(true);
					
					orderData.set(DELIVERY_FEE, "Delivery Fee: " + FOUR_SPACES + "$" + "4.00");
					orderDetailsList.setListData(orderData);
					updateGUI();
				}
			}
		});

		tipBoxCheckBox = new JCheckBox("Tip?");

		tipBoxCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox jCheckBox = (JCheckBox) e.getSource();
				if (jCheckBox.isSelected()) {
					tipAdd_ComboBox = new JComboBox<String>();
					tipAdd_ComboBox.addItem("No Additional Tip");
					tipAdd_ComboBox.addItem("5% Tip");
					tipAdd_ComboBox.addItem("10% Tip");
					tipAdd_ComboBox.addItem("15% Tip");
					sub2_westPanel.add(tipAdd_ComboBox);
					sub2_westPanel.setVisible(true);
				} else {
					sub2_westPanel.remove(tipAdd_ComboBox);
					tip = 0;
				}
				updateGUI();
				
				// Reactivated item listener for tip calculation - DeAngelo
				tipAdd_ComboBox.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						JComboBox<?> cB = (JComboBox<?>) e.getSource();

						if (e.getStateChange() == ItemEvent.SELECTED) {
							switch (cB.getSelectedIndex()) {
							case 0:
								tip = 0;
								break;
							case 1:
								tip = 0.05;
								break;
							case 2:
								tip = 0.10;
								break;
							case 3:
								tip = 0.15;
								break;
							}
						}
					}
				});
			}
		});

		// ADD COMPONENTS TO SUB WEST PANEL
		sub2_westPanel.add(new JLabel("  Additional Menu Options"));
		sub2_westPanel.add(deliv);
		sub2_westPanel.add(pickUp);
		sub2_westPanel.add(tipBoxCheckBox);

		// ADD SUB PANELS TO THE MAIN WEST PANEL
		westPanel.add(sub0_westPanel);
		westPanel.add(sub1_westPanel);
		westPanel.add(sub2_westPanel);
		westPanel.setBorder(lineBorder);
	}

	private void createEastPanel() {
		// East Panel
		eastPanel = new JPanel(new GridLayout(3, 1));
		eastPanel.setBackground(greenBkrg);

		// East Sub Panels
		JPanel sub0_eastPanel = new JPanel();
		sub0_eastPanel.setBackground(greenBkrg);

		JPanel sub1_eastPanel = new JPanel(new BorderLayout());
		sub1_eastPanel.setBackground(greenBkrg);

		JPanel sub2_eastPanel = new JPanel(new BorderLayout());
		sub1_eastPanel.setBackground(greenBkrg);

		// East Panel Components
		// East sub Panel for components
		sub0_eastPanel.add(new JLabel(dessertPic));

		// Menu Items
		// Label for Beverages
		drinksLabel = new JLabel(" Beverages Selections");
		drinksLabel.setBackground(greenBkrg);
		drinksLabel.setForeground(DgrayBkrg);
		drinksLabel.setFont(largeMenuFont);

		// List of beverages
		drinkFoodItems_JList = new JList<String>(drinksMenuItemsArr);
		drinkFoodItems_JList.setBackground(greenBkrg);
		drinkFoodItems_JList.setFont(menuFont);
		drinkFoodItems_JList.setForeground(DgrayBkrg);

		// List of beverage costs
		drinkFoodCosts_JList = new JList<String>(drinksCostArrString);
		drinkFoodCosts_JList.setBackground(greenBkrg);
		drinkFoodCosts_JList.setFont(menuFont);
		drinkFoodCosts_JList.setForeground(DgrayBkrg);

		// Requested listener activated - DeAngelo
		// Selects reflected price value
		drinkFoodItems_JList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList<?> jList = (JList<?>) e.getSource();
				
				if (jList.getSelectedIndex() != -1 && !e.getValueIsAdjusting()) {
					drinkFoodCosts_JList.setSelectedIndex(jList.getSelectedIndex());
					
					// Extra space detected: substring set to 5
					orderData.set(BEVERAGE_ITEM, jList.getSelectedValue().toString().substring(5) 
							+ ":" + FOUR_SPACES 
							+ drinkFoodCosts_JList.getSelectedValue().toString());
					orderDetailsList.setListData(orderData);
					updateGUI();
				}
			}
		});

		// Additional Listener to catch price selection and correct it - DeAngelo
		drinkFoodCosts_JList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList<?> jList = (JList<?>) e.getSource();

				if (jList.getSelectedIndex() != -1) {
					drinkFoodItems_JList.setSelectedIndex(jList.getSelectedIndex());
				}
			}
		});

		// Label for Desserts
		dessertLabel = new JLabel(" Dessert Selections");
		dessertLabel.setBackground(greenBkrg);
		dessertLabel.setForeground(DgrayBkrg);
		dessertLabel.setFont(largeMenuFont);

		// List for Desserts
		dessertFoodItems_JList = new JList<String>(dessertMenuItemsArr);
		dessertFoodItems_JList.setBackground(greenBkrg);
		dessertFoodItems_JList.setFont(menuFont);
		dessertFoodItems_JList.setForeground(DgrayBkrg);

		// List for Dessert Costs
		dessertFoodCosts_JList = new JList<String>(dessertCostArrString);
		dessertFoodCosts_JList.setBackground(greenBkrg);
		dessertFoodCosts_JList.setFont(menuFont);
		dessertFoodCosts_JList.setForeground(DgrayBkrg);

		// Requested listener activated - DeAngelo
		// Selects reflected price value
		dessertFoodItems_JList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList<?> jList = (JList<?>) e.getSource();
				
				if (jList.getSelectedIndex() != -1 && !e.getValueIsAdjusting()) {
					dessertFoodCosts_JList.setSelectedIndex(jList.getSelectedIndex());
					
					orderData.set(DESSERT_ITEM, jList.getSelectedValue().toString().substring(4) 
							+ ":" + FOUR_SPACES 
							+ dessertFoodCosts_JList.getSelectedValue().toString());
					orderDetailsList.setListData(orderData);
					updateGUI();
				}
			}
		});

		// Additional Listener to catch price selection and correct it - DeAngelo
		dessertFoodCosts_JList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList<?> jList = (JList<?>) e.getSource();
				
				if (jList.getSelectedIndex() != -1) {
					dessertFoodItems_JList.setSelectedIndex(jList.getSelectedIndex());
				}
			}
		});

		// Add drinks label and list to east sub panel
		sub1_eastPanel.add(drinksLabel, BorderLayout.NORTH);
		sub1_eastPanel.add(drinkFoodItems_JList, BorderLayout.CENTER);
		sub1_eastPanel.add(drinkFoodCosts_JList, BorderLayout.EAST);

		// Add Dessert label and List to east sub panel
		sub2_eastPanel.add(dessertLabel, BorderLayout.NORTH);
		sub2_eastPanel.add(dessertFoodItems_JList, BorderLayout.CENTER);
		sub2_eastPanel.add(dessertFoodCosts_JList, BorderLayout.EAST);
		sub2_eastPanel.setBackground(greenBkrg);

		// Add sub panels to the main east panel
		eastPanel.add(sub0_eastPanel, BorderLayout.NORTH);
		eastPanel.add(sub1_eastPanel, BorderLayout.CENTER);
		eastPanel.add(sub2_eastPanel, BorderLayout.CENTER);
		eastPanel.setBorder(lineBorder);
	}
	
	private void createCenterPanel() {
		// Add Panels to center panel
		centerPanel = new JPanel(new GridLayout(3, 1));
		centerPanel.setBackground(yellowBkrg);

		// Add east sub panel
		JPanel sub0_centerPanel = new JPanel();
		sub0_centerPanel.setBackground(yellowBkrg);

		JPanel sub1_centerPanel = new JPanel(new BorderLayout());
		sub1_centerPanel.setBackground(yellowBkrg);

		JPanel sub2_centerPanel = new JPanel(new BorderLayout());
		sub1_centerPanel.setBackground(yellowBkrg);

		// Center Panel Components
		// Panel for Pics
		sub0_centerPanel.add(new JLabel(dinnerPic));

		// Menu Items
		// Add dinner Label
		dinnerLabel = new JLabel("  Dinner Selections\n");
		dinnerLabel.setBackground(yellowBkrg);
		dinnerLabel.setForeground(DgrayBkrg);
		dinnerLabel.setFont(largeMenuFont);

		// Add dinner menu items to list
		dinnerItems_JList = new JList<String>(dinnerMenuItemsArr);
		dinnerItems_JList.setBackground(yellowBkrg);
		dinnerItems_JList.setFont(menuFont);
		dinnerItems_JList.setForeground(DgrayBkrg);

		// Add dinner item costs to list
		dinnerItemsCosts_JList = new JList<String>(dinnerCostArrString);
		dinnerItemsCosts_JList.setBackground(yellowBkrg);
		dinnerItemsCosts_JList.setFont(menuFont);
		dinnerItemsCosts_JList.setForeground(DgrayBkrg);

		// Requested listener activated - DeAngelo
		// Selects reflected price value
		dinnerItems_JList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList<?> jList = (JList<?>) e.getSource();

				if (jList.getSelectedIndex() != -1 && !e.getValueIsAdjusting()) {
					dinnerItemsCosts_JList.setSelectedIndex(jList.getSelectedIndex());
					
					orderData.set(DINNER_ITEM, jList.getSelectedValue().toString().substring(4) 
							+ ":" + FOUR_SPACES 
							+ dinnerItemsCosts_JList.getSelectedValue().toString());
					orderDetailsList.setListData(orderData);
					updateGUI();
				}
			}
		});

		// Additional Listener to catch price selection and correct it -
		// DeAngelo
		dinnerItemsCosts_JList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList<?> jList = (JList<?>) e.getSource();
				
				if (jList.getSelectedIndex() != -1) {
					dinnerItems_JList.setSelectedIndex(jList.getSelectedIndex());
				}
			}
		});

		// Add aptz Label
		appetizerLabel = new JLabel("  Appertizers Selections");
		appetizerLabel.setBackground(yellowBkrg);
		appetizerLabel.setFont(largeMenuFont);
		appetizerLabel.setForeground(DgrayBkrg);

		// Add aptz menu items to list
		appetizerItems_JList = new JList<String>(appetizerMenuItemsArr);
		appetizerItems_JList.setBackground(yellowBkrg);
		appetizerItems_JList.setFont(menuFont);
		appetizerItems_JList.setForeground(DgrayBkrg);

		// Add dinner item costs to list
		appetizerItemsCosts_JList = new JList<String>(appetizerCostArrString);
		appetizerItemsCosts_JList.setBackground(yellowBkrg);
		appetizerItemsCosts_JList.setFont(menuFont);
		appetizerItemsCosts_JList.setForeground(DgrayBkrg);

		// Requested listener activated - DeAngelo
		// Selects reflected price value
		appetizerItems_JList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList<?> jList = (JList<?>) e.getSource();
				
				if (jList.getSelectedIndex() != -1 & !e.getValueIsAdjusting()) {
					appetizerItemsCosts_JList.setSelectedIndex(jList.getSelectedIndex());
					
					orderData.set(APPETIZER_ITEM, jList.getSelectedValue().toString().substring(4) 
							+ ":" + FOUR_SPACES 
							+ appetizerItemsCosts_JList.getSelectedValue().toString());
					orderDetailsList.setListData(orderData);
					updateGUI();
				}
			}
		});

		// Additional Listener to catch price selection and correct it - DeAngelo
		appetizerItemsCosts_JList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList<?> jList = (JList<?>) e.getSource();
				
				if (jList.getSelectedIndex() != -1) {
					appetizerItems_JList.setSelectedIndex(jList.getSelectedIndex());

				}
			}
		});

		// Add items to the sub panels
		sub1_centerPanel.add(dinnerLabel, BorderLayout.NORTH);
		sub1_centerPanel.add(dinnerItems_JList, BorderLayout.CENTER);
		sub1_centerPanel.add(dinnerItemsCosts_JList, BorderLayout.EAST);

		sub2_centerPanel.add(appetizerLabel, BorderLayout.NORTH);
		sub2_centerPanel.add(appetizerItems_JList, BorderLayout.CENTER);
		sub2_centerPanel.add(appetizerItemsCosts_JList, BorderLayout.EAST);
		sub2_centerPanel.setBackground(yellowBkrg);

		// Add items to the main panel
		centerPanel.add(sub0_centerPanel);
		centerPanel.add(sub1_centerPanel);
		centerPanel.add(sub2_centerPanel);
		centerPanel.setBorder(lineBorder);
	}

	private void createFooterPanel() {
		// Add footer panel
		footerPanel = new JPanel(new GridLayout(1, 2));
		footerPanel.setBackground(LgrayBkrg);

		// Add sub panels
		JPanel sub0_footerPanel = new JPanel(new BorderLayout());
		sub0_footerPanel.setBackground(LgrayBkrg);

		JPanel sub1_footerPanel = new JPanel(new BorderLayout());
		sub1_footerPanel.setBackground(LgrayBkrg);

		JPanel sub2_footerPanel = new JPanel(new BorderLayout());
		sub2_footerPanel.setBackground(LgrayBkrg);

		// Footer panel components
		orderDetailsList = new JList<String>();
		orderDetailsList.setBorder(lineBorder);
		scrollPanel_menuDetailsList = new JScrollPane(orderDetailsList);

		finalDetailsList = new JTextArea();
		scrollPanel_finalDetails = new JScrollPane(finalDetailsList);
		finalDetailsList.setLineWrap(true);
		finalDetailsList.setWrapStyleWord(true);
		finalDetailsList.setBorder(lineBorder);
		finalDetailsList.setEditable(false);
		scrollPanel_finalDetails.setBorder(lineBorder);

		// Add components to footer panel
		sub0_footerPanel.add(scrollPanel_menuDetailsList, BorderLayout.CENTER);

		// Clear Button Creation and Action Listener - DeAngelo
		btnClear = new JButton("Clear Menu");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "Clear Menu") {
					clearOrderedArrayList();
				}
			}
		});

		// Get Total Button
		btnTotal = new JButton("Get Total");
		btnTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "Get Total") {
					if (deliv.isSelected()) {
						finalDetailsList.setText("Delivery Fee: " + "$" + "4.00" + newLine);
					} else {
						finalDetailsList.setText("Delivery Fee: " + "$" + "0.00" + newLine);
					}
					finalDetailsList.append("Items Before Tax: " + "$" + totalCostWithoutTax() + newLine);
					finalDetailsList.append("Tax: " + "$" + calculateTax() + newLine);
					finalDetailsList.append("Tip: " + tipCalculation() + newLine);
					finalDetailsList.append("Total: " + "$" + totalCostWithTax() + newLine);
				}
			}
		});

		sub0_footerPanel.add(btnClear, BorderLayout.SOUTH);

		// Add pic to footer panel
		sub1_footerPanel.add(new JLabel(headerPic));

		// Add final details list to the footer panel
		sub2_footerPanel.add(finalDetailsList, BorderLayout.CENTER);
		sub2_footerPanel.add(btnTotal, BorderLayout.SOUTH);

		// Add all sub panels to the footer panel
		footerPanel.add(sub0_footerPanel);
		footerPanel.add(sub1_footerPanel);
		footerPanel.add(sub2_footerPanel);
	}

	private void createGUI() {
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(footerPanel, BorderLayout.SOUTH);
		mainPanel.add(westPanel, BorderLayout.WEST);
		mainPanel.add(eastPanel, BorderLayout.EAST);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// ADD MAIN PANEL TO THE APPLET
		add(mainPanel);
	}

	// Method to clear the Ordered Array list
	private void clearOrderedArrayList() {
		//finalDetailsList.removeAll();
		finalDetailsList.setText("");

		// Clear menu list items
		dinnerItems_JList.clearSelection();
		dinnerItemsCosts_JList.clearSelection();
		appetizerItems_JList.clearSelection();
		appetizerItemsCosts_JList.clearSelection();
		drinkFoodItems_JList.clearSelection();
		drinkFoodCosts_JList.clearSelection();
		dessertFoodItems_JList.clearSelection();
		dessertFoodCosts_JList.clearSelection();
		
		if (tipBoxCheckBox.isSelected()) {
			tipBoxCheckBox.doClick();
		}
		pickUp.setSelected(true);
		
		resetDiningList();
	}
	
	// Method to reset view back to zero technically..
	private void resetDiningList() {
		orderData.set(DELIVERY_FEE, "Delivery Fee: $0.00");
		orderData.set(DINNER_ITEM, "Dinner Selection: $0.00");
		orderData.set(APPETIZER_ITEM, "Appetizer Selection: $0.00");
		orderData.set(BEVERAGE_ITEM, "Beverage Selection: $0.00");
		orderData.set(DESSERT_ITEM, "Dessert Item: $0.00");
		
		orderDetailsList.repaint();
	}
	
	// Calculates tax
	private String calculateTax() {
		String total = totalCostWithoutTax();
		double number = Double.parseDouble(total);
		
		number = number * 0.075;
	
		DecimalFormat df = new DecimalFormat("0.00");
		
		return df.format(number);
	}
	
	// Remade tip calculation based on Tyler's Original - DeAngelo
	private String tipCalculation() {
		double total = 0;
		
		String temp = "";
		
		if (drinkFoodCosts_JList.getSelectedIndex() != -1) {
			temp = drinkFoodCosts_JList.getSelectedValue();
			temp = temp.replace("$", "");
			total += Double.parseDouble(temp);
		}

		if (dessertFoodCosts_JList.getSelectedIndex() != -1) {
			temp = dessertFoodCosts_JList.getSelectedValue();
			temp = temp.replace("$", "");
			total += Double.parseDouble(temp);
		}

		if (dinnerItemsCosts_JList.getSelectedIndex() != -1) {
			temp = dinnerItemsCosts_JList.getSelectedValue();
			temp = temp.replace("$", "");
			total += Double.parseDouble(temp);
		}

		if (appetizerItemsCosts_JList.getSelectedIndex() != -1) {
			temp = appetizerItemsCosts_JList.getSelectedValue();
			temp = temp.replace("$", "");
			total += Double.parseDouble(temp);
		}
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		return df.format(total * tip);
	}
	
	// Calculates total without tax - DeAngelo
	private String totalCostWithoutTax() {
		double total = 0.00;
		
		String temp = "";
		
		if (drinkFoodCosts_JList.getSelectedIndex() != -1) {
			temp = drinkFoodCosts_JList.getSelectedValue();
			temp = temp.replace("$", "");
			total += Double.parseDouble(temp);
		}

		if (dessertFoodCosts_JList.getSelectedIndex() != -1) {
			temp = dessertFoodCosts_JList.getSelectedValue();
			temp = temp.replace("$", "");
			total += Double.parseDouble(temp);
		}

		if (dinnerItemsCosts_JList.getSelectedIndex() != -1) {
			temp = dinnerItemsCosts_JList.getSelectedValue();
			temp = temp.replace("$", "");
			total += Double.parseDouble(temp);
		}

		if (appetizerItemsCosts_JList.getSelectedIndex() != -1) {
			temp = appetizerItemsCosts_JList.getSelectedValue();
			temp = temp.replace("$", "");
			total += Double.parseDouble(temp);
		}

		DecimalFormat df = new DecimalFormat("0.00");

		return df.format(total);
	}
	
	// Final total for everything
	private String totalCostWithTax() {
		double total = 0.00;
		double taxRate = 0.075;
		double tipAmount = 0;
		
		String temp = "";
		
		if (drinkFoodCosts_JList.getSelectedIndex() != -1) {
			temp = drinkFoodCosts_JList.getSelectedValue();
			temp = temp.replace("$", "");
			total += Double.parseDouble(temp);
		}

		if (dessertFoodCosts_JList.getSelectedIndex() != -1) {
			temp = dessertFoodCosts_JList.getSelectedValue();
			temp = temp.replace("$", "");
			total += Double.parseDouble(temp);
		}

		if (dinnerItemsCosts_JList.getSelectedIndex() != -1) {
			temp = dinnerItemsCosts_JList.getSelectedValue();
			temp = temp.replace("$", "");
			total += Double.parseDouble(temp);
		}

		if (appetizerItemsCosts_JList.getSelectedIndex() != -1) {
			temp = appetizerItemsCosts_JList.getSelectedValue();
			temp = temp.replace("$", "");
			total += Double.parseDouble(temp);
		}
		
		// Tip is untaxed and value is only changed when tip is active
		if (tip != 0) {
			tipAmount = total * tip;
			total += tipAmount;
		}

		// Add tax carefully
		double taxAmount = (total - tipAmount) * taxRate;
		total += taxAmount;

		// Delivery fee is untaxed and is only added when necessary
		if (deliv.isSelected()) {
			total += 4;
		}
		
		DecimalFormat df = new DecimalFormat("0.00");

		return df.format(total);
	}

	// Update and repaint the GUI
	private void updateGUI() {
		revalidate();
		repaint();
	}
}