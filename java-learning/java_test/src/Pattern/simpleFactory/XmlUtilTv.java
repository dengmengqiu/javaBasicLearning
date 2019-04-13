package Pattern.simpleFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlUtilTv {
    public static String getBrandName(){
        try{
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("\\src\\Pattern\\simpleFactory\\configTV.xml"));
            System.out.println(new File("\\src\\Pattern\\simpleFactory\\configTV.xml").getPath());
            NodeList nodeList = doc.getElementsByTagName("brand");
            Node node = nodeList.item(0);
            String brand = node.getNodeValue();

            return brand;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
