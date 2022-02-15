package json;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import xml.pojos.Book;
import xml.pojos.Bookstore;

public class OjectToJSONAndViceVersa {

	private static final String BOOKSTORE_JSON = "bookstore.json";

	public static void main(String[] args) throws IOException {

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

		convertObjectToJSON(bookstore);
		convertJSONToObject();

	}

	private static void convertObjectToJSON(Bookstore bookstore) throws IOException {

		// Create ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		// Convert object to JSON string
		String bookStoreJson = mapper.writeValueAsString(bookstore);
		System.out.println(bookStoreJson);

		// Save JSON string to file
		FileOutputStream fileOutputStream = new FileOutputStream(BOOKSTORE_JSON);
		mapper.writeValue(fileOutputStream, bookstore);
		fileOutputStream.close();
	}

	private static Bookstore convertJSONToObject() {

		try {
			ObjectMapper mapper = new ObjectMapper();

			// Read JSON file and convert to java object
			InputStream fileInputStream = new FileInputStream(BOOKSTORE_JSON);
			Bookstore bookStore = mapper.readValue(fileInputStream, Bookstore.class);
			fileInputStream.close();

			List<Book> list = bookStore.getBooksList();
			for (Book book : list) {
				System.out.println("Book: " + book.getName() + " from " + book.getAuthor());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
