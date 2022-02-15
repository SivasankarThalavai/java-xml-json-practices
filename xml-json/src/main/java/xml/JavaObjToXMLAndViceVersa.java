package xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import xml.pojos.Book;
import xml.pojos.Bookstore;

//Some basic and useful JAXB annotations are:
//@XmlRootElement: This is a must-have an annotation for the Object to be used in JAXB. It defines the root element for the XML content.
//@XmlType: It maps the class to the XML schema type. We can use it for ordering the elements in the XML.
//@XmlTransient: This will make sure that the Object property is not written to the XML.
//@XmlAttribute: This will create the Object property as an attribute.
//@XmlElement(name = “ABC”): This will create the element with name “ABC”

public class JavaObjToXMLAndViceVersa {

	private static final String BOOKSTORE_XML = "bookstore.xml";

	public static void main(String[] args) throws JAXBException, IOException {

		List<Book> bookList = new ArrayList<Book>();

		// create books
		Book book1 = new Book();
		book1.setIsbn("978-0134685991");
		book1.setName("Effective Java");
		book1.setAuthor("Joshua Bloch");
		book1.setPublisher("Amazon");
		bookList.add(book1);

		Book book2 = new Book();
		book2.setIsbn("978-0596009205");
		book2.setName("Head First Java, 2nd Edition");
		book2.setAuthor("Kathy Sierra");
		book2.setPublisher("amazon");
		bookList.add(book2);

		// create bookstore, assigning book
		Bookstore bookstore = new Bookstore();
		bookstore.setName("Amazon Bookstore");
		bookstore.setLocation("Newyorkt");
		bookstore.setBookList(bookList);

		convertObjectToXML(bookstore);
		convertXMLToObject();
	}

	private static void convertObjectToXML(Bookstore bookstore) throws JAXBException, FileNotFoundException {

		// create JAXB context and instantiate marshaller
		JAXBContext context = JAXBContext.newInstance(Bookstore.class);
		Marshaller m = context.createMarshaller();
		// m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Write to System.out
		m.marshal(bookstore, System.out);

		// Write to File
		m.marshal(bookstore, new File(BOOKSTORE_XML));
	}

	private static Bookstore convertXMLToObject() {
		try {
			JAXBContext context = JAXBContext.newInstance(Bookstore.class);
			Unmarshaller un = context.createUnmarshaller();
			Bookstore bookstore = (Bookstore) un.unmarshal(new File(BOOKSTORE_XML));
			List<Book> list = bookstore.getBooksList();
			for (Book book : list) {
				System.out.println("Book: " + book.getName() + " from " + book.getAuthor());
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

}
