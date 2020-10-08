public class playfair {
    public static void main (String[] args) {

        if (args.length != 3) {
            System.exit();
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
        


        System.out.println(output);
    }
}
    