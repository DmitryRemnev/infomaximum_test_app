import entities.Address;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.FileNotFoundException;

public class XMLFileHandler extends FileHandler {

    @Override
    void readFile(String path) {

        var handler = new DefaultHandler() {

            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if (attributes != null && qName.equals(Constants.ITEM)) {
                    addressList.add(createAddress(attributes));
                }
            }

        };

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(path), handler);

            System.out.println(Constants.PLEASE_WAIT);

        } catch (FileNotFoundException e) {
            System.out.println(Constants.FILE_NOT_FOUND);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Address createAddress(Attributes attributes) {
        if (attributes.getLength() != 4) throw new IllegalArgumentException(Constants.NOT_EQUAL_FOUR);

        String city = attributes.getValue(0);
        if (city.isBlank()) throw new IllegalArgumentException(Constants.STRING_IS_BLANK);

        String street = attributes.getValue(1);
        if (street.isBlank()) throw new IllegalArgumentException(Constants.STRING_IS_BLANK);

        int house = Integer.parseInt(attributes.getValue(2));
        if (house < 1) throw new IllegalArgumentException(Constants.LESS_THAN_ONE);

        int floor = Integer.parseInt(attributes.getValue(3));
        if (floor < 1) throw new IllegalArgumentException(Constants.LESS_THAN_ONE);

        return new Address(city, street, house, floor);
    }
}