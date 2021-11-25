import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var scanner = new Scanner(System.in);
        FileHandler fileHandler;

        System.out.println(Constants.INFO_TEXT);

        while (true) {

            String input = scanner.nextLine();

            if (input.equals(Constants.COMMAND_EXIT)) {
                break;
            }

            if (input.contains(Constants.TYPE_CSV)) {
                fileHandler = new FileHandler();
                fileHandler.readCvsFile(input);
                fileHandler.processingFile();
                fileHandler.printResults();

            } else if (input.contains(Constants.TYPE_XML)) {
                fileHandler = new FileHandler();
                fileHandler.readXmlFile(input);
                fileHandler.processingFile();
                fileHandler.printResults();

            } else {
                System.out.println(Constants.INFO_TEXT);
            }
        }
    }
}