import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Binary extends JPanel implements ActionListener {
    JPanel board; // Panel for buttons
    JPanel canvas; // Panel for drawing on
    JButton convert; // buttons for the GUI
    JButton reset;
    JButton bindec;
    JTextField decimalstring; // Input box
    JLabel outputarea; // Label for printing binary string to. (checking
		       // algorithm works)
    ImageIcon num0 = new ImageIcon("0.gif"); // images for the output
    ImageIcon num1 = new ImageIcon("1.gif");
    ImageIcon num2 = new ImageIcon("2.gif");
    ImageIcon num3 = new ImageIcon("3.gif");
    ImageIcon num4 = new ImageIcon("4.gif");
    ImageIcon num5 = new ImageIcon("5.gif");
    ImageIcon num6 = new ImageIcon("6.gif");
    ImageIcon num7 = new ImageIcon("7.gif");
    ImageIcon num8 = new ImageIcon("8.gif");
    ImageIcon num9 = new ImageIcon("9.gif");
    ImageIcon blank = new ImageIcon("blank.gif");

    public Binary() {
	super(true); // Call constructor of parent
		     // Standard layout (flow)
	setLayout(new FlowLayout());
	// Set up two panels, control board and canvas
	board = new JPanel(true); // declare the new panels
	canvas = new JPanel(true);
	board.setPreferredSize(new Dimension(150, 200)); // set size of the
							 // panels on the frame
	canvas.setPreferredSize(new Dimension(500, 250));
	board.setBorder(BorderFactory.createLineBorder(Color.black)); // set
								      // line
								      // colour
								      // of the
								      // borders
	canvas.setBorder(BorderFactory.createLineBorder(Color.blue));
	// Create buttons and attach listeners
	convert = new JButton("Decimal Convert"); // declare decimal convert
						  // button
	convert.addActionListener(this); // add action listener
	bindec = new JButton("Binary Convert"); // declare binary convert button
	bindec.addActionListener(this); // add action listener
	reset = new JButton("Reset"); // declare reset button
	reset.addActionListener(this); // add action listener
				       // add text input box and printing label
	decimalstring = new JTextField(8); // declare textfield
	decimalstring.setBorder(BorderFactory.createLineBorder(Color.black)); // give
									      // border
									      // to
									      // input
									      // box
	outputarea = new JLabel(
		"Please enter either a positive integer or a binary string"); // declare
									      // label
	add(canvas);
	add(board);
	board.add(convert); // add button to control panel
	board.add(bindec); // add button to control panel
	board.add(decimalstring); // add label to control panel
	board.add(reset); // add reset button
	canvas.add(outputarea); // add label to canvas
    }

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == convert) // when convert button is pressed
	{ // code for decimal-binary conversion
	    try // try-catch to prevent invalid data type entry
	    {
		int binary, answer; // declare variables for binary conversion
				    // algorithm
		String answer1 = ""; // initialise string for the answer of the
				     // conversion
		String intbinary = decimalstring.getText(); // put input from
							    // user into a
							    // string
		binary = Integer.parseInt(intbinary); // convert the string into
						      // an integer
		if (binary >= 0) // check for a 0 or greater entry
		{
		    do {
			answer = binary % 2; // see if modulus of answer is 0 or
					     // not 0
			answer1 = "" + answer + "" + answer1; // add value to
							      // string
			binary = binary / 2; // divide value remaining by 2
		    } while (binary != 0); // come out of loop when the division
					   // results in 0
		    outputarea.setText(answer1);
		    outputarea.setVisible(false);
		    for (int i = 0; i <= answer1.length() - 1; i++) // drawing
								    // the
								    // binary
								    // code
		    {
			char c = answer1.charAt(i); // ASCII value for the
						    // integer 1
			ImageIcon image = new ImageIcon(answer1.charAt(i)
				+ ".gif"); // convert character in string to
					   // numbered .gif file
			JLabel label = new JLabel(image); // rename images in
							  // src folder to
							  // numbers.
			canvas.add(label);
			canvas.validate();
		    }
		} else {
		    outputarea
			    .setText("Invalid entry. Please enter a positive decimal number.");
		    decimalstring.setText("");
		}
	    }

	    catch (Exception e1) // code to display when invalid entry into the
				 // text box happens
	    {
		outputarea
			.setText("Invalid entry. Please enter a positive decimal number."); // error
											    // message
		decimalstring.setText(""); // set input box to blank ready for
					   // next input
	    }
	}
	if (e.getSource() == reset) // reset button functionality
	{
	    outputarea.setText(""); // set text area's to blank
	    decimalstring.setText("");
	    canvas.removeAll();
	    canvas.repaint();
	    repaint();
	}
	if (e.getSource() == bindec) // code to convert a binary string to
				     // decimal number
	{
	    int j, k, answer = 0; // declare variables for binary to decimal
				  // conversion
	    double length, i;
	    String binary, answer2;
	    binary = decimalstring.getText(); // get binary string from user
					      // input
	    length = binary.length() - 1; // adjust value to count from 0
	    i = Math.pow(2, length); // set variable to increment by power of 2
				     // for each pass through the loop
	    k = (int) i; // cast the double as an integer
	    for (j = 0; j <= length; j++) {
		if (binary.charAt(j) == 49) // set through binary string
		{
		    answer = answer + k; // add power of answer to the answer
		    k = k / 2;
		} else {
		    k = k / 2;
		}
	    }
	    answer2 = "" + answer; // create final answer
	    outputarea.setText(answer2);
	    outputarea.setVisible(false);
	    for (int count = 0; count <= answer2.length() - 1; count++) // loop
									// to
									// print
									// images
									// for
									// the
									// answer
	    {
		char c = answer2.charAt(count); //
		ImageIcon image = new ImageIcon(answer2.charAt(count) + ".gif"); // convert
										 // character
										 // in
										 // string
										 // to
										 // match
										 // numbered
										 // .gif
										 // file
		JLabel label = new JLabel(image); // display the number image in
						  // a label
		canvas.add(label); // add the label to the canvas
		canvas.validate(); // needed to allow the reset button to
				   // function
	    }
	}
    }

    public static void main(String[] args) {
	Binary b = new Binary();
	// Set up outer frame, and its exit behaviour
	JFrame frame = new JFrame("Number conversion program.");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// Set the main content frame
	// size the frame (pack) and make it visible
	frame.setContentPane(b);
	frame.pack();
	frame.setVisible(true); // set application window so it can be seen
	frame.setLocationRelativeTo(null); // center application window when it
					   // loads
    }
}
