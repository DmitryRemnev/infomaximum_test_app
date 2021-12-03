import entities.Address;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileNotFoundException;

public class XMLFileHandler extends FileHandler {

    @Override
    void readFile(String path) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(path);

            System.out.println(Constants.PLEASE_WAIT);

            NodeList list = document.getElementsByTagName(Constants.ITEM);

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String city = element.getAttribute(Constants.CITY);
                    String street = element.getAttribute(Constants.STREET);
                    int house = Integer.parseInt(element.getAttribute(Constants.HOUSE));
                    int floor = Integer.parseInt(element.getAttribute(Constants.FLOOR));
                    var address = new Address(city, street, house, floor);

                    addressList.add(address);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(Constants.FILE_NOT_FOUND);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}