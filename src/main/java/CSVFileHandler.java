import entities.Address;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVFileHandler extends FileHandler {

    @Override
    void readFile(String path) {

        try (Stream<String> stream = Files.lines(Paths.get(path)).parallel()) {
            System.out.println(Constants.PLEASE_WAIT);

            addressList.addAll(stream
                    .skip(1)
                    .filter(line -> (line != null && !line.isEmpty()))
                    .map(this::createAddress)
                    .collect(Collectors.toList()));

        } catch (FileNotFoundException e) {
            System.out.println(Constants.FILE_NOT_FOUND);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Address createAddress(String line) {
        String[] values = line.split(Constants.DELIMITER);
        if (values.length != 4) throw new IllegalArgumentException(Constants.NOT_EQUAL_FOUR);

        String city = values[0].replaceAll(Constants.REG_QUOTES, Constants.SIGN_EMPTY);
        if (city.isBlank()) throw new IllegalArgumentException(Constants.STRING_IS_BLANK);

        String street = values[1].replaceAll(Constants.REG_QUOTES, Constants.SIGN_EMPTY);
        if (street.isBlank()) throw new IllegalArgumentException(Constants.STRING_IS_BLANK);

        int house = Integer.parseInt(values[2]);
        if (house < 1) throw new IllegalArgumentException(Constants.LESS_THAN_ONE);

        int floor = Integer.parseInt(values[3]);
        if (floor < 1) throw new IllegalArgumentException(Constants.LESS_THAN_ONE);

        return new Address(city, street, house, floor);
    }
}