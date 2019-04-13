package Pattern.Adapter.test;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import java.lang.reflect.Constructor;

public class XMLUtil
{
	public static Object getBean()
	{
		try
		{
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;							
			doc = builder.parse(new File("src\\Pattern\\Adapter\\test\\config.xml"));

			NodeList nl = doc.getElementsByTagName("Adapter");
            Node classNode1 = nl.item(0).getFirstChild();
            String cName1 = classNode1.getNodeValue();

			NodeList n2 = doc.getElementsByTagName("Adaptee");
			Node classNode2 = n2.item(0).getFirstChild();
			String cName2 = classNode2.getNodeValue();

			Class c = Class.forName(cName1);
			Constructor constructor = c.getConstructor(cName2.getClass());
			Object obj = constructor.newInstance(cName2);
			// AnimalAdapter obj = new AnimalAdapter(cName2);
            return obj;
           }   
           	catch(Exception e)
           	{
           		e.printStackTrace();
           		return null;
           	}
		}
}
