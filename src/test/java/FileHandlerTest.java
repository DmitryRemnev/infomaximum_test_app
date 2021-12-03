import entities.Address;
import entities.Floor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FileHandlerTest {
    List<Address> notDuplicateList;
    Map<Address, Integer> emptyDuplicateMap;
    List<Address> oneDuplicateList;
    Map<Address, Integer> oneDuplicateMap;
    List<Address> twoDuplicateList;
    Map<Address, Integer> twoDuplicateMap;
    Map<String, Floor> citiesFloorMap;

    FileHandler handler;
    Floor emptyFloor;
    Floor firstFloorOne;
    Floor secondFloorOne;
    Floor thirdFloorOne;
    Floor fourthFloorOne;
    Floor fifthFloorOne;

    @Before
    public void setUp() {
        handler = new FileHandler() {
            @Override
            void readFile(String path) {
            }
        };

        Address address1 = new Address("Барнаул", "Дальняя улица", 56, 2);
        Address address2 = new Address("Братск", "Большая Октябрьская улица", 65, 5);
        Address address3 = new Address("Барнаул", "Дальняя улица", 56, 2);
        Address address4 = new Address("Братск", "Большая Октябрьская улица", 65, 5);
        Address address5 = new Address("Барнаул", "Дальняя улица", 56, 2);

        notDuplicateList = new ArrayList<>();
        notDuplicateList.add(address1);
        notDuplicateList.add(address2);
        emptyDuplicateMap = new HashMap<>();

        oneDuplicateList = new ArrayList<>();
        oneDuplicateList.add(address3);
        oneDuplicateList.add(address4);
        oneDuplicateList.add(address5);
        oneDuplicateMap = new HashMap<>();
        oneDuplicateMap.put(address3, 2);

        twoDuplicateList = new ArrayList<>();
        twoDuplicateList.add(address1);
        twoDuplicateList.add(address2);
        twoDuplicateList.add(address3);
        twoDuplicateList.add(address4);
        twoDuplicateList.add(address5);
        twoDuplicateMap = new HashMap<>();
        twoDuplicateMap.put(address1, 3);
        twoDuplicateMap.put(address2, 2);

        citiesFloorMap = new HashMap<>();
        var floor1 = new Floor();
        floor1.setTwoStory(3);
        citiesFloorMap.put("Барнаул", floor1);
        var floor2 = new Floor();
        floor2.setFiveStory(2);
        citiesFloorMap.put("Братск", floor2);

        emptyFloor = new Floor();

        firstFloorOne = new Floor();
        firstFloorOne.setOneStory(1);

        secondFloorOne = new Floor();
        secondFloorOne.setTwoStory(1);

        thirdFloorOne = new Floor();
        thirdFloorOne.setThreeStory(1);

        fourthFloorOne = new Floor();
        fourthFloorOne.setFourStory(1);

        fifthFloorOne = new Floor();
        fifthFloorOne.setFiveStory(1);
    }

    @Test
    public void testDuplicateProcessing_not_duplicate() {
        Map<Address, Integer> actual = new HashMap<>(handler.duplicateProcessing(notDuplicateList));

        assertEquals(emptyDuplicateMap, actual);
    }

    @Test
    public void testDuplicateProcessing_one_duplicate() {
        Map<Address, Integer> actual = new HashMap<>(handler.duplicateProcessing(oneDuplicateList));

        assertEquals(oneDuplicateMap, actual);
    }

    @Test
    public void testDuplicateProcessing_two_duplicate() {
        Map<Address, Integer> actual = new HashMap<>(handler.duplicateProcessing(twoDuplicateList));

        assertEquals(twoDuplicateMap, actual);
    }

    @Test
    public void testDuplicateProcessing_error() {
        Map<Address, Integer> actual = new HashMap<>(handler.duplicateProcessing(twoDuplicateList));

        assertNotEquals(oneDuplicateMap, actual);
    }

    @Test
    public void testFloorProcessing() {
        Map<String, Floor> actual = new HashMap<>(handler.floorProcessing(twoDuplicateList));

        assertEquals(citiesFloorMap, actual);
    }

    @Test
    public void testFloorProcessing_error() {
        Map<String, Floor> actual = new HashMap<>(handler.floorProcessing(oneDuplicateList));

        assertNotEquals(citiesFloorMap, actual);
    }

    @Test
    public void testAddFloor_zero_empty() {
        Floor actual = handler.addFloor(emptyFloor, 0);

        assertEquals(emptyFloor, actual);
    }

    @Test
    public void testAddFloor_sixth_empty() {
        Floor actual = handler.addFloor(emptyFloor, 6);

        assertEquals(emptyFloor, actual);
    }

    @Test
    public void testAddFloor_first_one() {
        Floor actual = handler.addFloor(emptyFloor, 1);

        assertEquals(firstFloorOne, actual);
    }

    @Test
    public void testAddFloor_second_one() {
        Floor actual = handler.addFloor(emptyFloor, 2);

        assertEquals(secondFloorOne, actual);
    }

    @Test
    public void testAddFloor_third_one() {
        Floor actual = handler.addFloor(emptyFloor, 3);

        assertEquals(thirdFloorOne, actual);
    }

    @Test
    public void testAddFloor_fourth_one() {
        Floor actual = handler.addFloor(emptyFloor, 4);

        assertEquals(fourthFloorOne, actual);
    }

    @Test
    public void testAddFloor_fifth_one() {
        Floor actual = handler.addFloor(emptyFloor, 5);

        assertEquals(fifthFloorOne, actual);
    }

    @Test
    public void testAddFloor_fifth_error() {
        Floor actual = handler.addFloor(emptyFloor, 4);

        assertNotEquals(fifthFloorOne, actual);
    }

    @Test
    public void testUpdateFloor_zero_empty() {
        Floor actual = handler.updateFloor(emptyFloor, 0);

        assertEquals(emptyFloor, actual);
    }

    @Test
    public void testUpdateFloor_sixth_empty() {
        Floor actual = handler.updateFloor(emptyFloor, 6);

        assertEquals(emptyFloor, actual);
    }

    @Test
    public void testUpdateFloor_first_one() {
        Floor actual = handler.updateFloor(emptyFloor, 1);

        assertEquals(firstFloorOne, actual);
    }

    @Test
    public void testUpdateFloor_second_one() {
        Floor actual = handler.updateFloor(emptyFloor, 2);

        assertEquals(secondFloorOne, actual);
    }

    @Test
    public void testUpdateFloor_third_one() {
        Floor actual = handler.updateFloor(emptyFloor, 3);

        assertEquals(thirdFloorOne, actual);
    }

    @Test
    public void testUpdateFloor_fourth_one() {
        Floor actual = handler.updateFloor(emptyFloor, 4);

        assertEquals(fourthFloorOne, actual);
    }

    @Test
    public void testUpdateFloor_fifth_one() {
        Floor actual = handler.updateFloor(emptyFloor, 5);

        assertEquals(fifthFloorOne, actual);
    }

    @Test
    public void testUpdateFloor_fifth_error() {
        Floor actual = handler.updateFloor(emptyFloor, 5);

        assertNotEquals(fourthFloorOne, actual);
    }
}