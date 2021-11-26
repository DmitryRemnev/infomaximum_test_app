import entities.Address;
import entities.Floor;

import java.util.*;

public abstract class FileHandler {
    protected final List<Address> addressList = new ArrayList<>();
    protected final Map<Address, Integer> duplicateMap = new HashMap<>();
    protected final Map<String, Floor> citiesFloorMap = new HashMap<>();

    abstract void readFile(String path);

    private List<Address> getAddressList() {
        return addressList;
    }

    private Map<Address, Integer> duplicateProcessing(List<Address> addressList) {
        Set<Address> addressSet = new TreeSet<>();

        for (Address address : addressList) {

            if (addressSet.contains(address)) {
                if (duplicateMap.containsKey(address)) {
                    duplicateMap.put(address, duplicateMap.get(address) + 1);

                } else {
                    duplicateMap.put(address, 2);
                }

            } else {
                addressSet.add(address);
            }
        }

        return duplicateMap;
    }

    private Map<String, Floor> floorProcessing(List<Address> addressList) {
        for (Address address : addressList) {

            String city = address.getCity();
            int floor = Integer.parseInt(address.getFloor());

            if (citiesFloorMap.containsKey(city)) {
                citiesFloorMap.put(city, updateFloor(citiesFloorMap.get(city), floor));
            } else {
                citiesFloorMap.put(city, addFloor(new Floor(), floor));
            }
        }

        return citiesFloorMap;
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

    void printDuplicate(Map<Address, Integer> duplicateMap) {
        for (Map.Entry<Address, Integer> item : duplicateMap.entrySet()) {
            System.out.println(Constants.RECORD + item.getKey().getCity() + " " +
                    item.getKey().getStreet() + " " +
                    item.getKey().getHouse() + " " +
                    item.getKey().getFloor() + " " +
                    Constants.REPEATS + item.getValue() + Constants.TIMES);
            System.out.println();
        }
    }

    void printCitiesFloor(Map<String, Floor> citiesFloorMap) {
        for (Map.Entry<String, Floor> item : citiesFloorMap.entrySet()) {
            System.out.println(Constants.CITY_RU + item.getKey());
            System.out.println(Constants.ONE_FLOOR + item.getValue().getOneStory());
            System.out.println(Constants.TWO_FLOOR + item.getValue().getTwoStory());
            System.out.println(Constants.THREE_FLOOR + item.getValue().getThreeStory());
            System.out.println(Constants.FOUR_FLOOR + item.getValue().getFourStory());
            System.out.println(Constants.FIVE_FLOOR + item.getValue().getFiveStory());
            System.out.println();
        }
    }

    void printResults() {
        printDuplicate(duplicateProcessing(getAddressList()));
        printCitiesFloor(floorProcessing(getAddressList()));
    }
}