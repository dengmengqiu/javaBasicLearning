package Pattern.Bridge.test;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;
public class XMLUtilPen
{
	public static Object getBean(String args)
	{
		try
		{
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;							
			doc = builder.parse(new File("src\\Pattern\\Bridge\\test\\configPen.xml"));
			NodeList nl=null;
			Node classNode=null;
			String cName=null;
			nl = doc.getElementsByTagName("className");
			
			if(args.equals("C"))
			{
	            classNode = nl.item(0).getFirstChild();
			}else if(args.equals("fillstyle")){
				classNode = nl.item(1).getFirstChild();
			}else if(args.equals("pen"))
			{

	            classNode=nl.item(2).getFirstChild();
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
