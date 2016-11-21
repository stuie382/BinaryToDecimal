public class DecimalConverter implements Converter {

  @Override
  public String convert(String input) {
    int binary;
    String answer1 = "";
    try {
      binary = Integer.parseInt(input);
    } catch (NumberFormatException nfe) {
      System.out.println("Input is not a valid number:: " + nfe.getMessage());
      return null;
    }
    if (binary >= 0) {
      do {
        int answer = binary % 2;
        answer1 = "" + answer + "" + answer1;
        binary = binary / 2;
      } while (binary != 0);
    }
    return answer1;
  }
}
