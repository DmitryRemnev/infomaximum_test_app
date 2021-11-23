import java.io.*;
import java.util.*;

public class FileHandler {
    String path;
    Set<String> addressSet;
    Map<String, Integer> duplicateMap;
    Map<String, Floor> citiesFloorMap;

    public FileHandler(String path) {
        this.path = path;
        addressSet = new TreeSet<>();
        duplicateMap = new HashMap<>();
        citiesFloorMap = new HashMap<>();
    }

    public void readFile() {
        long start = System.currentTimeMillis();

        try (var reader = new BufferedReader(new FileReader(path))) {
            String line;

            reader.readLine();
            while ((line = reader.readLine()) != null) {
                lineProcessing(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println(Constants.FILE_NOT_FOUND);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private void lineProcessing(String line) {

        if (addressSet.contains(line)) {
            if (duplicateMap.containsKey(line)) {
                duplicateMap.put(line, duplicateMap.get(line) + 1);
            } else {
                duplicateMap.put(line, 2);
            }
        } else {
            addressSet.add(line);
        }

        String[] values = line.split(Constants.DELIMITER);
        String city = values[0];
        int floor = Integer.parseInt(values[3]);

        if (citiesFloorMap.containsKey(city)) {
            citiesFloorMap.put(city, updateFloor(citiesFloorMap.get(city), floor));
        } else {
            citiesFloorMap.put(city, addFloor(new Floor(), floor));
        }
    }

    private Floor addFloor(Floor floor, int value) {

        switch (value) {
            case 1:
                floor.setOneStory(1);
                break;
            case 2:
                floor.setTwoStory(1);
                break;
            case 3:
                floor.setThreeStory(1);
                break;
            case 4:
                floor.setFourStory(1);
                break;
            case 5:
                floor.setFiveStory(1);
                break;
        }

        return floor;
    }

    private Floor updateFloor(Floor floor, int value) {

        switch (value) {
            case 1:
                floor.setOneStory(floor.getOneStory() + 1);
                break;
            case 2:
                floor.setTwoStory(floor.getTwoStory() + 1);
                break;
            case 3:
                floor.setThreeStory(floor.getThreeStory() + 1);
                break;
            case 4:
                floor.setFourStory(floor.getFourStory() + 1);
                break;
            case 5:
                floor.setFiveStory(floor.getFiveStory() + 1);
                break;
        }

        return floor;
    }

    public void printDuplicate() {
        for (Map.Entry<String, Integer> item : duplicateMap.entrySet()) {
            System.out.println(Constants.RECORD + item.getKey() + Constants.REPEATS + item.getValue() + Constants.TIMES);
            System.out.println();
        }
    }

    public void printCitiesFloor() {
        for (Map.Entry<String, Floor> item : citiesFloorMap.entrySet()) {
            System.out.println(Constants.CITY + item.getKey());
            System.out.println(Constants.ONE_FLOOR + item.getValue().getOneStory());
            System.out.println(Constants.TWO_FLOOR + item.getValue().getTwoStory());
            System.out.println(Constants.THREE_FLOOR + item.getValue().getThreeStory());
            System.out.println(Constants.FOUR_FLOOR + item.getValue().getFourStory());
            System.out.println(Constants.FIVE_FLOOR + item.getValue().getFiveStory());
            System.out.println();
        }
    }
}