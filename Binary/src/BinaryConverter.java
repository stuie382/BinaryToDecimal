public class BinaryConverter implements Converter {

  @Override
  public String convert(final String input) {
    int answer = 0;
    try {
      validateBinaryInput(input);
    } catch (IllegalStateException ex) {
      System.out.println("Binary format incorrect:: " + ex.getMessage());
      return null;
    }
    // adjust value to count from 0
    final int length = input.length() - 1;
    // set variable to increment by power of 2 for each pass through the loop
    int k = (int) Math.pow(2, length);
    // cast the double as an integer
    for (int j = 0; j <= length; j++) {
      // set through binary string
      if (input.charAt(j) == 49) {
        answer += k; // add power of answer to the answer
        k = k / 2;
      } else {
        k = k / 2;
      }
    }
    return String.valueOf(answer);
  }

  private void validateBinaryInput(final String binary) {
    char[] letters = binary.toCharArray();
    for (char c : letters) {
      if ((c != '0') && (c != '1')) {
        throw new IllegalStateException("Binary input can only be '1' or '0'.");
      }
    }
  }
}
