import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(Constants.INFO_TEXT);
        while (true) {

            String input = scanner.nextLine();

            if (input.equals(Constants.COMMAND_EXIT)) {
                break;
            } else {
                var fileHandler = new FileHandler(input);
                fileHandler.readFile();
                fileHandler.printDuplicate();
                fileHandler.printCitiesFloor();
            }
        }
    }
}