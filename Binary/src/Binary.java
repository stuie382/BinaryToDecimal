import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Binary extends JPanel {

  private static final long serialVersionUID = -2803257217038020759L;
  private static final String
      INSTRUCTIONS =
      "Please enter either a positive integer or a binary string";
  // Panel for drawing on
  private JPanel canvas;
  private JTextField userInput;
  private JLabel outputAreaLbl;

  public Binary() {
    super(true);
    setupGUI();
  }

  private void setupGUI() {
    setLayout(new FlowLayout());
    // Set up two panels, control board and canvas
    JPanel board = new JPanel(true);
    canvas = new JPanel(true);
    board.setPreferredSize(new Dimension(150, 200));
    canvas.setPreferredSize(new Dimension(500, 250));
    board.setBorder(BorderFactory.createLineBorder(Color.black));
    canvas.setBorder(BorderFactory.createLineBorder(Color.blue));
    // Create buttons and attach listeners
    JButton decToBinary = new JButton("Decimal To Binary Convert");
    decToBinary.addActionListener(e -> decimalConvert());
    JButton binaryToDec = new JButton("Binary To Decimal Convert");
    binaryToDec.addActionListener(e -> binaryConvert());
    JButton reset = new JButton("Reset");
    reset.addActionListener(e -> resetGui());      // add text input box and printing label
    userInput = new JTextField(8);
    userInput.setBorder(BorderFactory.createLineBorder(Color.black));
    outputAreaLbl = new JLabel(INSTRUCTIONS);
    add(canvas);
    add(board);
    board.add(decToBinary); // add button to control panel
    board.add(binaryToDec); // add button to control panel
    board.add(userInput); // add label to control panel
    board.add(reset); // add reset button
    canvas.add(outputAreaLbl); // add label to canvas
  }

  protected void binaryConvert() {
    final Converter converter = new BinaryConverter();
    final String answer2 = converter.convert(userInput.getText());
    // loop to print images for the answer
    if (answer2 != null) {
      printToScreen(answer2);
    }
  }

  private void printToScreen(final String answer2) {
    for (int count = 0; count < answer2.length(); count++) {
      // convert character in string to match numbered .gif file
      final String fileToLoad = answer2.charAt(count) + ".gif";
      ImageIcon temp = new ImageIcon(Binary.class.getResource(fileToLoad));
      JLabel label = new JLabel(temp);
      canvas.add(label);
      canvas.validate();
    }
  }
  protected void decimalConvert() {
    final Converter converter = new DecimalConverter();
    final String answer1 = converter.convert(userInput.getText());
    // drawing the binary code
    if (answer1 != null) {
      printToScreen(answer1);
    }
  }

  protected void resetGui() {
    outputAreaLbl.setText("");
    userInput.setText("");
    canvas.removeAll();
    canvas.repaint();
    canvas.add(outputAreaLbl);
  }

  public static void main(String[] args) {
    // Set up outer frame, and its exit behaviour
    JFrame frame = new JFrame("Number conversion program.");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    // Set the main content frame
    // size the frame (pack) and make it visible
    frame.setContentPane(new Binary());
    frame.pack();
    frame.setVisible(true); // set application window so it can be seen
    frame.setLocationRelativeTo(null); // centre application window when it loads
  }
}
