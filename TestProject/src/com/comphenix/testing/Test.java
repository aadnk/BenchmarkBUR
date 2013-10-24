package com.comphenix.testing;

import java.io.IOException;
import java.io.StringReader;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.futuredev.utilities.localisation.json.SpecialMessage;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class Test {
	private static final String BASIC = "&6<b>Hello!</b>";
	private static final String INTERMEDIATE = 
		"<tip=\"It\'s over 9,000!\"><prompt=\"It\'s over 9,000!\">" + 
		"Click here to send the message specified in the tooltip.</prompt></tip>";
	
	private static final String ADVANCED = 
		"&6Welcome to our server! If you are new here, please visit <tip=\"&ohttp://example.com/\">" + 
		"<href=\"http://example.com/\"><color=\"c\"><i>our website</i></color></href></tip>, register an account, and enjoy!";
	
	private static final String ADVANCED_XML = 
		"<message>Welcome to our server! If you are new here, please visit " + 
		"<tip show=\"http://example.com/\"><a href=\"http://example.com/\"><color code=\"c\"><i>our website</i></color></a></tip>, register an account, and enjoy!</message>";
	
	public static void main(String[] args) throws Exception {
		// Use Piccolo
		System.setProperty("org.xml.sax.driver", "com.bluecast.xml.Piccolo");
		
		Runner.main(SpecialMessageTest.class, new String[] { "-DtimeSlice=1,2,8,16" });
	}
	
	public static class SpecialMessageTest extends SimpleBenchmark {
		private XMLReader xmlReader;
		private ElementCountHandler handler;
		
		@Override
		protected void setUp() throws Exception {
			xmlReader = XMLReaderFactory.createXMLReader();
			handler = new ElementCountHandler();

			xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", handler);
			xmlReader.setProperty("http://xml.org/sax/properties/declaration-handler", handler);
			xmlReader.setContentHandler(handler);
		}
		
		public long timeBasic(int reps) {
			long count = 0;
			
			// We need to return the computation somehow, otherwise it MIGHT (but unlikely) be optimized away
			for (int i = 0; i < reps; i++) {
				count += new SpecialMessage(BASIC).toString().length();
			}
			return count;
		}
		
		public long timeIntermediate(int reps) {
			long count = 0;
			
			for (int i = 0; i < reps; i++) {
				count += new SpecialMessage(INTERMEDIATE).toString().length();
			}	
			return count;
		}
		
		public long timeComplex(int reps) {
			long count = 0;
			
			for (int i = 0; i < reps; i++) {
				count += new SpecialMessage(ADVANCED).toString().length();
			}
			return count;
		}
		
		// Just to compare against a fast XML parser
		public long timeXml(int reps) throws SAXException, IOException {
			handler.setElementCount(0);
			
			for (int i = 0; i < reps; i++) {
				xmlReader.parse(new InputSource(new StringReader(ADVANCED_XML)));
			}
			return handler.getElementCount();
		}
	}
	
	private static class ElementCountHandler implements ContentHandler, LexicalHandler, DeclHandler {
		private long elementCount;
		
		public void setElementCount(long elementCount) {
			this.elementCount = elementCount;
		}
		
		public long getElementCount() {
			return elementCount;
		}
		
		public void elementDecl(String name, String model) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void attributeDecl(String eName, String aName, String type, String mode, String value)
				throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void internalEntityDecl(String name, String value) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void externalEntityDecl(String name, String publicId, String systemId)
				throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void startDTD(String name, String publicId, String systemId) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void endDTD() throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void startEntity(String name) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void endEntity(String name) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void startCDATA() throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void endCDATA() throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void comment(char[] ch, int start, int length) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void characters(char[] ch, int start, int length) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void endElement(String uri, String localName, String qName) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void endPrefixMapping(String prefix) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void processingInstruction(String target, String data) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void setDocumentLocator(Locator locator) {
			// TODO Auto-generated method stub
			
		}

		public void skippedEntity(String name) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			
		}

		public void startElement(String uri, String localName, String qName, Attributes atts)
				throws SAXException {
		
			elementCount++;
		}

		public void startPrefixMapping(String prefix, String uri) throws SAXException {
			// TODO Auto-generated method stub
			
		}
	}
}
