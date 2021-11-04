public class App {
    public static void main(String[] args) {

        boolean step = false;
        boolean print = false;
        String filePath = null;

        for (String s: args) {
            System.out.println(s);
            if (s.equals("--step") || s.equals("-s")) {
                step = true;
            } else if (s.equals("--print") || s.equals("-p")) {
                print = true;
            } else if (s.contains(".bb") || s.contains(".barebones")) {
                filePath = s;
            }
        }

        if (filePath == null) {
            System.err.println("No file path provided in program arguments");
            System.exit(1);
        }

        System.out.println("Started barebones interpreter.\nUsing file " + filePath);
        Interpreter interpreter = new Interpreter(step, print, filePath);
    }
}