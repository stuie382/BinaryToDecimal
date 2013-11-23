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

   private static final long serialVersionUID = -2803257217038020759L;
   // Panel for buttons
   JPanel                    board;
   // Panel for drawing on
   JPanel                    canvas;
   // buttons for the GUI
   JButton                   convert;
   JButton                   reset;
   JButton                   bindec;
   JTextField                decimalstring;
   JLabel                    outputarea;
   ImageIcon                 num0;
   ImageIcon                 num1;
   ImageIcon                 num2;
   ImageIcon                 num3;
   ImageIcon                 num4;
   ImageIcon                 num5;
   ImageIcon                 num6;
   ImageIcon                 num7;
   ImageIcon                 num8;
   ImageIcon                 num9;
   ImageIcon                 blank;

   public Binary() {
      super(true); // Call constructor of parent
      // Standard layout (flow)
      setupImages();
      setupGUI();
   }

   private void setupGUI() {
      setLayout(new FlowLayout());
      // Set up two panels, control board and canvas
      board = new JPanel(true);
      canvas = new JPanel(true);
      board.setPreferredSize(new Dimension(150, 200)); // set size of the
      // panels on the frame
      canvas.setPreferredSize(new Dimension(500, 250));
      // set line colour of the borders
      board.setBorder(BorderFactory.createLineBorder(Color.black));
      canvas.setBorder(BorderFactory.createLineBorder(Color.blue));
      // Create buttons and attach listeners
      convert = new JButton("Decimal Convert");
      convert.addActionListener(this);
      bindec = new JButton("Binary Convert");
      bindec.addActionListener(this);
      reset = new JButton("Reset");
      reset.addActionListener(this);
      // add text input box and printing label
      decimalstring = new JTextField(8);
      decimalstring.setBorder(BorderFactory.createLineBorder(Color.black));
      outputarea = new JLabel("Please enter either a positive integer or a binary string");
      add(canvas);
      add(board);
      board.add(convert); // add button to control panel
      board.add(bindec); // add button to control panel
      board.add(decimalstring); // add label to control panel
      board.add(reset); // add reset button
      canvas.add(outputarea); // add label to canvas
   }

   private void setupImages() {
      num0 = new ImageIcon("0.gif");
      num1 = new ImageIcon("1.gif");
      num2 = new ImageIcon("2.gif");
      num3 = new ImageIcon("3.gif");
      num4 = new ImageIcon("4.gif");
      num5 = new ImageIcon("5.gif");
      num6 = new ImageIcon("6.gif");
      num7 = new ImageIcon("7.gif");
      num8 = new ImageIcon("8.gif");
      num9 = new ImageIcon("9.gif");
      blank = new ImageIcon("blank.gif");
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == convert) {
         // code for decimal-binary conversion
         try {
            int binary, answer;
            String answer1 = "";
            String intbinary = decimalstring.getText();
            binary = Integer.parseInt(intbinary);
            if (binary >= 0) {
               do {
                  answer = binary % 2;
                  answer1 = "" + answer + "" + answer1;
                  binary = binary / 2;
               } while (binary != 0);

               outputarea.setText(answer1);
               outputarea.setVisible(true);
               // drawing the binary code
               for (int i = 0; i <= (answer1.length() - 1); i++) {
                  // answer1.charAt(i) is ASCII value for the integer 1
                  // convert character in string to numbered .gif file
                  System.out.println(answer1.charAt(i));
                  ImageIcon image = new ImageIcon(answer1.charAt(i) + ".gif");
                  JLabel label = new JLabel(image); // rename images in src folder to numbers.
                  canvas.add(label);
                  canvas.validate();
               }
            } else {
               outputarea.setText("Invalid entry. Please enter a positive decimal number.");
               decimalstring.setText("");
            }
         } catch (Exception e1) {
            outputarea.setText("Invalid entry. Please enter a positive decimal number.");
            decimalstring.setText("");
         }
      }
      if (e.getSource() == reset) {
         outputarea.setText(""); // set text area's to blank
         decimalstring.setText("");
         canvas.removeAll();
         canvas.repaint();
         repaint();
      }
      // code to convert a binary string to decimal number
      if (e.getSource() == bindec) {
         int j, k, answer = 0; // declare variables for binary to decimal conversion
         double length, i;
         String binary, answer2;
         binary = decimalstring.getText();
         length = binary.length() - 1; // adjust value to count from 0
         // set variable to increment by power of 2 for each pass through the loop
         i = Math.pow(2, length);
         k = (int) i; // cast the double as an integer
         for (j = 0; j <= length; j++) {
            // set through binary string
            if (binary.charAt(j) == 49) {
               answer = answer + k; // add power of answer to the answer
               k = k / 2;
            } else {
               k = k / 2;
            }
         }
         answer2 = "" + answer; // create final answer
         outputarea.setText(answer2);
         outputarea.setVisible(true);
         // loop to print images for the answer
         for (int count = 0; count <= (answer2.length() - 1); count++) {
            // convert character in string to match numbered .gif file
            System.out.println(answer2.charAt(count));
            ImageIcon image = new ImageIcon(answer2.charAt(count) + ".gif");
            JLabel label = new JLabel(image); // display the number image in a label
            canvas.add(label); // add the label to the canvas
            canvas.validate(); // needed to allow the reset button to function
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
      frame.setLocationRelativeTo(null); // centre application window when it loads
   }
}
