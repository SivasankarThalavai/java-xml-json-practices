package json.pojos;

public class Book {

	private String name;
	private String author;
	private String publisher;
	private String isbn;

	public Book(String name, String author, String publisher, String isbn) {
		super();
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}