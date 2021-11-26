import entities.Address;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CSVFileHandler extends FileHandler {

    @Override
    void readFile(String path) {

        try (var reader = new BufferedReader(new FileReader(path))) {
            System.out.println(Constants.PLEASE_WAIT);
            String line;

            reader.readLine();
            while ((line = reader.readLine()) != null) {

                String[] values = line.split(Constants.DELIMITER);
                String city = values[0].replaceAll(Constants.REG_QUOTES, Constants.SIGN_EMPTY);
                String street = values[1].replaceAll(Constants.REG_QUOTES, Constants.SIGN_EMPTY);
                String house = values[2];
                String floor = values[3];
                var address = new Address(city, street, house, floor);

                addressList.add(address);
            }

        } catch (FileNotFoundException e) {
            System.out.println(Constants.FILE_NOT_FOUND);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}