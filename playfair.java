import java.util.*;

public class Playfair {

    // Insert X between double letters when necessary
    public static String insertX(String text) {
        String output = text;
        int x = 0;
        while (x < output.length() - 1) {
            if (output.charAt(x) == output.charAt(x + 1)) {
                if (x % 2 == 0) {
                    output = output.substring(0, x + 1) + "X" + output.substring(x + 1, output.length());
                }
            }
            x++;
        }
        return output;
    }

    // Encode when two letters are on the same row by using the letters below them
    public static String verticalEncode(String letterPair) {
        return "";
    }

    // Encode when two letters are on the same column by using the letters to the right of them
    public static String horizontalEncode(String letterPair) {
        return "";
    }

    // Encode by using the letters on the same row but in the column of the other letter
    public static String regularEncode(String letterPair) {
        return "";
    }

    public static void main (String[] args) {
        if (args.length != 3) {
            System.exit(0);
        }

        boolean encode = false, decode = false;
        String inputText, outputText, key;

        if (args[0].equals("encode")) {
            encode = true;
        }
        if (args[0].equals("decode")) {
            decode = true;
        }

        inputText = args[1];
        key = args[2];

        inputText = insertX(inputText);
        // Add a z to the end if there's an odd # of letters
        if ((inputText.length() % 2) != 0) {
            inputText = inputText + "Z";
        }

        // Separate text into pairs of letters
        List<String> letterPairList = new ArrayList<String>();
        for (int x = 0; x < inputText.length(); x += 2) {
            letterPairList.add(inputText.substring(x, x + 2));
        }

        System.out.println(letterPairList);
    }
}