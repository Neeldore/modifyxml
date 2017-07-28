package modifyit;

import java.io.File;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/** @author Neelkant K */

public class Changexml {
	// Variables
	private static final Logger logger = Logger.getLogger(Changexml.class.getName());
	private static final File file = new File("out.xml");
	private static final String EXAMPLE_NODE = "Lastname";
	private static final String ENCOUNTER = "Encountered";
	private static int length=0;
	private Changexml(){ } 
	
	/**
	 * @main
	 * @param args
	*/
	
	public static void main(String[] args){ 
		try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.parse(file);
		//Adding attribute
		addAttribute(doc , "attr1" , "value1" );
		//modifying an attribute
		modifyAttribute(doc , "id" , "200");
		//adding an element
		addElement(doc , "new_node" , "new_text");
		//deleting an element
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer() ;
		DOMSource doms = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		trans.transform(doms, result);
		}
		catch(Exception e){
			logger.info(ENCOUNTER+e+"while creating the doc");
		}
	} 
	/**
	 * to add Attribute to the xml
	 * @param doc the document reference
	 * @param key the attribute key
	 * @param value the value of the attribute
	*/
	public static void addAttribute(Document doc , String key , String value){
		try{
			for(int i = 0 ; i<length ; i++){
			Node temp = doc.getElementsByTagName(EXAMPLE_NODE).item(i);
			Element el = (Element) temp;
			el.setAttribute(key, value);
			}
		}
		catch(Exception e){
			logger.info(ENCOUNTER+e+"while adding attribute");
		}
	}
	/**
	 * to modify an Attribute in the xml
	 * @param doc the document reference
	 * @param key the key to be modified
	 * @param value the modifying value
	*/
	public static void modifyAttribute(Document doc , String key , String value){
		try{
			for(int i=0 ; i<length ;i++){
				Node temp = doc.getElementsByTagName(EXAMPLE_NODE).item(i);
				NamedNodeMap attr = temp.getAttributes();
				Node change = attr.getNamedItem(key);
				change.setNodeValue(value);
			}
		}
		catch(Exception e){
			logger.info(ENCOUNTER+e+"while modifying attribute");
		}
	}
	/**
	 * to add an element to the xml
	 * @param doc the document reference
	 * @param elementname the name of the element to be created
	 * @param text the text of the 
	*/
	public static void addElement(Document doc , String elementname , String text){
		try{
			Node root = doc.getFirstChild();
			Element el = doc.createElement(elementname);
			addTextToElement(el , text);
			root.appendChild(el);
		}
		catch(Exception e){
			logger.info(ENCOUNTER+e+"while adding element");
		}
	}
	/**
	 * to add text to the element to the xml
	 * @param el element whose text is to be added
	 * @param changer the string used to change 
	*/
	public static void addTextToElement(Element el , String changer){
		try{
			el.setTextContent(changer);
		}
		catch(Exception e){
			logger.info(ENCOUNTER+e+"while adding attribute");
		}
	}
}
