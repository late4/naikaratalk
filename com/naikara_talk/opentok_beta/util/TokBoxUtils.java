package com.naikara_talk.opentok_beta.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.naikara_talk.opentok_beta.exception.OpenTokException;

public class TokBoxUtils {

	private static int counter = 0;
	public static Node parseXML(String matchToken, NodeList nodelist) {
		Node token = null;
		int index = 0;
		while((token = nodelist.item(index)) != null) {
			if(token.getNodeName().equals(matchToken)) {
				break;
			}

			index++;
		}

		return token;
	}

	public static String dummy(){
		return "dummy";
	}

	public static Document setupDocument(String xmlResponse) throws ParserConfigurationException, IOException, OpenTokException, SAXException {

		String str = "Error Out";
		Document document2= null;
		//if(counter < 10){
	//		System.out.println("The XMLResponse is : " + xmlResponse);
	//	}
		//try{
			if(null == xmlResponse) {

			throw new OpenTokException("There was an error in retrieving the response. Please make sure that you are pointing to the correct server");
			}

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document document;

		builder = dbFactory.newDocumentBuilder();
		document = builder.parse(new InputSource(new StringReader(xmlResponse)));
		//document = builder.parse(new InputSource(new StringReader(str)));
		/*if(counter <=10){
			System.out.println("The XMLResponse is : " + xmlResponse);
		}*/
		Node errorNodes = TokBoxUtils.parseXML("error", document.getElementsByTagName("error"));

		if(null != errorNodes) {
			throw new OpenTokException(xmlResponse);
		}

		return document;

		//}
		/*catch(SAXException sae){
			counter ++;
			System.out.println("xmlResponse when SAXParseException happens is " + xmlResponse);
			//return null;
			if(counter > 0){
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder;
				//Document document;

				builder = dbFactory.newDocumentBuilder();
				document2 = builder.parse(new InputSource(new StringReader("Error Out")));
				counter = 0;
				return document2;
			}

			//throw sae;
			sae.printStackTrace();


		}
		return document2;*/

	}

}
