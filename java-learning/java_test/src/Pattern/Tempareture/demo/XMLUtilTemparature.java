package Pattern.Tempareture.demo;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLUtilTemparature
{
	public static Object getBean(String args)
	{
		try
		{
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;							
			doc = builder.parse(new File("src\\Pattern\\Tempareture\\demo\\configTemparature.xml"));
			NodeList nl=null;
			Node classNode=null;
			String cName=null;
			nl = doc.getElementsByTagName("className");
			
			if(args.equals("China"))
			{
	            classNode = nl.item(0).getFirstChild();
			}
			
	         cName=classNode.getNodeValue();
	         Class c=Class.forName(cName);
		  	 Object obj=c.newInstance();
	         return obj;		
           }   
           	catch(Exception e)
           	{
           		e.printStackTrace();
           		return null;
           	}
		}
}
