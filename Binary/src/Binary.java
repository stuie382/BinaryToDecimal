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

public class Binary extends JPanel {

   private static final long serialVersionUID = -2803257217038020759L;
   // Panel for buttons
   JPanel                    board;
   // Panel for drawing on
   JPanel                    canvas;
   // buttons for the GUI
   JButton                   decToBinary;
   JButton                   reset;
   JButton                   binaryToDec;
   JTextField                userInput;
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
      super(true);
      setupImages();
      setupGUI();
   }

   private void setupGUI() {
      setLayout(new FlowLayout());
      // Set up two panels, control board and canvas
      board = new JPanel(true);
      canvas = new JPanel(true);
      board.setPreferredSize(new Dimension(150, 200));
      canvas.setPreferredSize(new Dimension(500, 250));
      board.setBorder(BorderFactory.createLineBorder(Color.black));
      canvas.setBorder(BorderFactory.createLineBorder(Color.blue));
      // Create buttons and attach listeners
      decToBinary = new JButton("Decimal Convert");
      decToBinary.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            decimalConvert();
         }
      });
      binaryToDec = new JButton("Binary Convert");
      binaryToDec.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            binaryConvert();
         }
      });
      reset = new JButton("Reset");
      reset.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            resetGui();
         }
      });      // add text input box and printing label
      userInput = new JTextField(8);
      userInput.setBorder(BorderFactory.createLineBorder(Color.black));
      outputarea = new JLabel("Please enter either a positive integer or a binary string");
      add(canvas);
      add(board);
      board.add(decToBinary); // add button to control panel
      board.add(binaryToDec); // add button to control panel
      board.add(userInput); // add label to control panel
      board.add(reset); // add reset button
      canvas.add(outputarea); // add label to canvas
   }

   protected void binaryConvert() {
      int k;
      int answer = 0;
      double length;
      String binary;
      String answer2;
      binary = userInput.getText();
      resetGui();
      // adjust value to count from 0
      length = binary.length() - 1;
      // set variable to increment by power of 2 for each pass through the loop
      k = (int) Math.pow(2, length);
      ; // cast the double as an integer
      for (int j = 0; j <= length; j++) {
         // set through binary string
         if (binary.charAt(j) == 49) {
            answer += k; // add power of answer to the answer
            k = k / 2;
         } else {
            k = k / 2;
         }
      }
      answer2 = "" + answer; // create final answer
      // loop to print images for the answer
      for (int count = 0; count <= (answer2.length() - 1); count++) {
         // convert character in string to match numbered .gif file
         JLabel label = new JLabel(new ImageIcon(answer2.charAt(count) + ".gif"));
         canvas.add(label);
         canvas.validate();
      }
   }

   protected void decimalConvert() {
      try {
         int binary, answer;
         String answer1 = "";
         String intbinary = userInput.getText();
         binary = Integer.parseInt(intbinary);
         resetGui();
         if (binary >= 0) {
            do {
               answer = binary % 2;
               answer1 = "" + answer + "" + answer1;
               binary = binary / 2;
            } while (binary != 0);
            // drawing the binary code
            for (int i = 0; i <= (answer1.length() - 1); i++) {
               // answer1.charAt(i) is ASCII value for the integer 1
               // rename images in src folder to numbers.
               JLabel label = new JLabel(new ImageIcon(answer1.charAt(i) + ".gif"));
               canvas.add(label);
               canvas.validate();
            }
         } else {
            outputarea.setText("Invalid entry. Please enter a positive decimal number.");
            userInput.setText("");
         }
      } catch (Exception ex) {
         outputarea.setText("Invalid entry. Please enter a positive decimal number.");
         userInput.setText("");
         System.out.println("EXCEPTION:: " + ex);
      }
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

   protected void resetGui() {
      outputarea.setText("");
      userInput.setText("");
      canvas.removeAll();
      canvas.repaint();
      repaint();
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
