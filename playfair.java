public class Playfair {
    // Insert X between double letters when necessary
    public static String insertX(String input) {
        String output = input;
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

    public static void main (String[] args) {
        if (args.length != 3) {
            System.exit(0);
        }

        boolean encode = false, decode = false;
        String input, output, key;

        if (args[0].equals("encode")) {
            encode = true;
        }
        if (args[0].equals("decode")) {
            decode = true;
        }

        input = args[1];
        key = args[2];
        
        String inputX = insertX(input);

        System.out.println(inputX);
    }
}