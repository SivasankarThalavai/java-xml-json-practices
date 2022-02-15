package json.pojos;

import java.util.List;

public class Bookstore {

	private List<Book> bookList;

	private String name;

	private String location;

	public Bookstore(List<Book> bookList, String name, String location) {
		super();
		this.bookList = bookList;
		this.name = name;
		this.location = location;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}