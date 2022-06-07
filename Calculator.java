// Advanced Java Calculator Application
// Helpful source: "Bro Code" Youtube channel with tutorial @ https://www.youtube.com/watch?v=dfhmTyRTCSQ

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Calculator implements ActionListener {
        boolean eq = false;
	boolean inter_mediate_lock = false;
	boolean decimal = false;
	
	JFrame frame;
	JTextField textfield;
	
	JButton[] functionButtons = new JButton[9];
	JButton[] numberButtons = new JButton[10];
	
	JButton addButton, subButton, mulButton, divButton;
	JButton decButton, equButton, delButton, clrButton, negButton;
	JPanel panel;
	int count = 0;

	Font myFont = new Font("PLAIN", Font.BOLD, 25);

	double num1 = 0, num2 = 0, result = 0;
	char operator;
	private double inter_mediate;
	
	void inter_hold() {
		if(inter_mediate_lock == false) {
			inter_mediate_lock = true;
		}
	}
	void release_hold() {
		inter_mediate_lock = false;
	}

	Calculator() {
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(720, 800);
		frame.setLayout(null);

		textfield = new JTextField();
		textfield.setBounds(50, 25, 600, 50);
		textfield.setFont(myFont);
		textfield.setEditable(false);

		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("Del");
		clrButton = new JButton("Cl");
		negButton = new JButton("(-)");

		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;

		for (int i = 0; i < 9; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
		}
		for (int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
		}

		negButton.setBounds(30, 430, 80, 50);
		delButton.setBounds(100, 430, 80, 50);
		clrButton.setBounds(170, 430, 80, 50);

		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4, 4, 10, 10));
		// panel.setBackground(Color.GRAY);
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		panel.add(decButton);
		panel.add(numberButtons[0]);
		panel.add(equButton);
		panel.add(divButton);
		panel.add(delButton);

		frame.add(panel);
		frame.add(negButton);
		frame.add(delButton);
		frame.add(clrButton);
		frame.add(textfield);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Calculator calc = new Calculator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 10; i++) {
			if (e.getSource() == numberButtons[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
			}
		}
		if (e.getSource() == decButton && decimal == false) {
			textfield.setText(textfield.getText().concat("."));
			release_hold();
			decimal = true;
			count = 0;
		}
		if (e.getSource() == addButton) {
			release_hold();
			num1 = Double.parseDouble(textfield.getText());
			operator = '+';
			textfield.setText("");
			release_hold();
			decimal = false;
			count = 0;
		}
		if (e.getSource() == subButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '-';
			textfield.setText("");
			release_hold();
			decimal = false;
			count = 0;
		}
		if (e.getSource() == mulButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '*';
			textfield.setText("");
			release_hold();
			decimal = false;
			count = 0;
		}
		if (e.getSource() == divButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '/';
			textfield.setText("");
			release_hold();
			decimal = false;
			count = 0;
		}
		if (e.getSource() == equButton) {
			count++;
			String text_result = "";
			inter_hold();
			if(count == 1) {
				num2 = Double.parseDouble(textfield.getText());
				inter_mediate = Double.parseDouble(textfield.getText());
			}
			else if(count > 1) {
				num2 = inter_mediate;
			}
			/*
			 * if(eq == false) { num2 = Double.parseDouble(textfield.getText()); } else {
			 * num2 = Double.parseDouble(textfield.getText()); }
			 */
			
				switch (operator) {
				case '+':
					result = num1 + num2;
					text_result = String.valueOf(result);
					break;
				case '-':
					result = num1 - num2;
					text_result = String.valueOf(result);
					break;
				case '*':
					result = num1 * num2;
					text_result = String.valueOf(result);
					break;
				case '/':
					if(num2 == 0) {
						text_result = "Cannot divide by zero";
					}
					else {
						result = num1 / num2;
						text_result = String.valueOf(result);
					}
					break;
				}
				textfield.setText(text_result);
				num1 = result;
				decimal = false;		
		}
		

		if (e.getSource() == clrButton) {
			textfield.setText("");
			decimal = false;
			count = 0;
		}
		if (e.getSource() == delButton) {
			String string = textfield.getText();
			textfield.setText("");
			for (int i = 0; i < string.length() - 1; i++) {
				textfield.setText(textfield.getText() + string.charAt(i));
			}
			decimal = false;
			count = 0;
		}
		if (e.getSource() == negButton) {
			double temp = Double.parseDouble(textfield.getText());
			temp = temp * (-1);
			textfield.setText(String.valueOf(temp));
			decimal = false;
			count = 0;
		}
	}
}
