import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var scanner = new Scanner(System.in);
        FileHandler handler;

        System.out.println(Constants.INFO_TEXT);

        while (true) {

            String input = scanner.nextLine();

            if (input.equals(Constants.COMMAND_EXIT)) {
                break;
            }

            if (input.contains(Constants.TYPE_CSV)) {
                handler = new FileHandler();
                handler.readCvsFile(input);
                handler.printResults();

            } else if (input.contains(Constants.TYPE_XML)) {
                handler = new FileHandler();
                handler.readXmlFile(input);
                handler.printResults();

            } else {
                System.out.println(Constants.INFO_TEXT);
            }
        }
    }
}