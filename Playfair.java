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

    // When two letters are on the same row, encode by using the letters below them or decode by using the letters above them
    public static String verticalEncode(String letterPair, String key, int direction) {
            String letterOne, letterTwo, newLetterOne, newLetterTwo, output;
            int shift = direction;
            letterOne = String.valueOf(letterPair.charAt(0));
            letterTwo = String.valueOf(letterPair.charAt(1));
            
            // 0 = Encode, 1 = Decode
            if (shift == 0) {
                newLetterOne = String.valueOf(key.charAt((key.indexOf(letterOne) + 5) % 25));
                newLetterTwo = String.valueOf(key.charAt((key.indexOf(letterTwo) + 5) % 25));
            }
            else {
                if ((key.indexOf(letterOne) - 5 < 0) || (key.indexOf(letterTwo) - 5 < 0)) {
                    newLetterOne = String.valueOf(key.charAt(25 + key.indexOf(letterOne) - 5));
                    newLetterTwo = String.valueOf(key.charAt(25 + key.indexOf(letterTwo) - 5));
                }
                else {
                    newLetterOne = String.valueOf(key.charAt(key.indexOf(letterOne) - 5));
                    newLetterTwo = String.valueOf(key.charAt(key.indexOf(letterTwo) - 5));
                }
            }

            output = newLetterOne + newLetterTwo;
        return output;
    }

    // When two letters are on the same column, encode by using the letters to the right of them or decode by using the letters to the left of them
    public static String horizontalEncode(String letterPair, String key, int direction) {
        String letterOne, letterTwo, newLetterOne, newLetterTwo, output;
        int oneRow, twoRow, shift = direction;
        letterOne = String.valueOf(letterPair.charAt(0));
        letterTwo = String.valueOf(letterPair.charAt(1));
        oneRow = key.indexOf(letterOne) / 5;
        twoRow = key.indexOf(letterTwo) / 5;

        // 0 = Encode, 1 = Decode
        if (shift == 0) {
            if (((key.indexOf(letterOne) + 1) / 5 != oneRow) || ((key.indexOf(letterTwo) + 1) / 5 != twoRow)) {
                newLetterOne = String.valueOf(key.charAt(key.indexOf(letterOne) + 1 - 5));
                newLetterTwo = String.valueOf(key.charAt(key.indexOf(letterTwo) + 1 - 5));
            }
            else {
                newLetterOne = String.valueOf(key.charAt(key.indexOf(letterOne) + 1));
                newLetterTwo = String.valueOf(key.charAt(key.indexOf(letterTwo) + 1));
            }
        }
        else {
            if (((key.indexOf(letterOne) - 1) / 5 != oneRow) || ((key.indexOf(letterTwo) - 1) / 5 != twoRow)) {
                newLetterOne = String.valueOf(key.charAt(key.indexOf(letterOne) + 4));
                newLetterTwo = String.valueOf(key.charAt(key.indexOf(letterTwo) + 4));
            }
            else {
                newLetterOne = String.valueOf(key.charAt(key.indexOf(letterOne) - 1));
                newLetterTwo = String.valueOf(key.charAt(key.indexOf(letterTwo) - 1));
            }
        }

        output = newLetterOne + newLetterTwo;
        return output;
    }

    // Encode or decode by using the letters on the same row but in the column of the other letter
    public static String regularEncode(String letterPair, String key) {
        String letterOne, letterTwo, newLetterOne, newLetterTwo, output;
        int oneRow, oneColumn, twoRow, twoColumn;
        letterOne = String.valueOf(letterPair.charAt(0));
        letterTwo = String.valueOf(letterPair.charAt(1));
        oneRow = key.indexOf(letterOne) / 5;
        oneColumn = key.indexOf(letterOne) % 5;
        twoRow = key.indexOf(letterTwo) / 5;
        twoColumn = key.indexOf(letterTwo) % 5;

        newLetterOne = String.valueOf(key.charAt((oneRow * 5) + twoColumn));
        newLetterTwo = String.valueOf(key.charAt((twoRow * 5) + oneColumn)); 

        output = newLetterOne + newLetterTwo;
        return output;
    }

    public static void main (String[] args) {
        if (args.length != 3) {
            System.exit(0);
        }

        boolean encode = false, decode = false;
        String inputText, outputText = "", key;

        if (args[0].equals("encode")) {
            encode = true;
        }
        if (args[0].equals("decode")) {
            decode = true;
        }

        inputText = args[1];
        key = args[2];

        inputText = inputText.toUpperCase();
        if (encode) {
            while (inputText.indexOf("J") >= 0) {
                int replaceIndex = inputText.indexOf("J");
                inputText = inputText.substring(0, replaceIndex) + "I" + inputText.substring(replaceIndex + 1, inputText.length());
            }
            inputText = insertX(inputText);
            // Add a z to the end if there's an odd # of letters
            if ((inputText.length() % 2) != 0) {
                inputText = inputText + "Z";
            }
        }

        // Separate text into pairs of letters
        List<String> letterPairList = new ArrayList<String>();
        for (int x = 0; x < inputText.length(); x += 2) {
            letterPairList.add(inputText.substring(x, x + 2));
        }

        List<String> letterPairOutput = new ArrayList<String>();
        // Goes through pairs and encode or decode them
        for (int x =0; x < letterPairList.size(); x++) {
            String letterOne, letterTwo;
            int oneRow, oneColumn, twoRow, twoColumn, shiftValue;
            letterOne = String.valueOf(letterPairList.get(x).charAt(0));
            letterTwo = String.valueOf(letterPairList.get(x).charAt(1));
            oneRow = key.indexOf(letterOne) / 5;
            oneColumn = key.indexOf(letterOne) % 5;
            twoRow = key.indexOf(letterTwo) / 5;
            twoColumn = key.indexOf(letterTwo) % 5;
            if (encode) {
                shiftValue = 0;
            }
            else {
                shiftValue = 1;
            }
            if (oneRow == twoRow) {
                    letterPairOutput.add(verticalEncode(letterPairList.get(x), key, shiftValue));
                }
                else {
                    if (oneColumn == twoColumn) {
                        letterPairOutput.add(horizontalEncode(letterPairList.get(x), key, shiftValue));
                    }
                    else {
                        letterPairOutput.add(regularEncode(letterPairList.get(x), key));
                    }
                }
        }

        // Put together pairs of letter to create the output
        for (int x = 0; x < letterPairOutput.size(); x++) {
            outputText = outputText + letterPairOutput.get(x);
        }

        System.out.println(outputText);
    }
}
