package json;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import json.pojos.Post;
import json.pojos.Tag;


// mapper.writeValue(fileOutputStream, bookstore);		
// mapper.readValue(fileInputStream, Post.class);

public class OjectToJSONAndViceVersa {

	private static final String BOOKSTORE_JSON = "posts.json";

	public static void main(String[] args) throws IOException {

		// create a post
		Post post = new Post();
		post.setTitle("Jackson JSON API Guide");
		post.setId(100);
		post.setDescription("Post about Jackson JSON API");
		post.setContent("HTML content here");
		post.setLastUpdatedAt(new Date());
		post.setPostedAt(new Date());

		// create some predefined tags
		Set<Tag> tags = new HashSet<Tag>();
		Tag java = new Tag(1, "Java");
		Tag jackson = new Tag(2, "Jackson");
		Tag json = new Tag(3, "JSON");
		tags.add(java);
		tags.add(jackson);
		tags.add(json);

		// set tags to post
		post.setTags(tags);

		convertObjectToJSON(post);
		convertJSONToObject();

	}

	private static void convertObjectToJSON(Post bookstore) throws IOException {

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

	private static Post convertJSONToObject() {

		try {
			ObjectMapper mapper = new ObjectMapper();

			// Read JSON file and convert to java object
			InputStream fileInputStream = new FileInputStream(BOOKSTORE_JSON);
			Post post = mapper.readValue(fileInputStream, Post.class);
			fileInputStream.close();

			// print tags of this post
			System.out.println("Printing tag details of post :: " + post.getTitle());
			for (Iterator<Tag> iterator = post.getTags().iterator(); iterator.hasNext();) {
				Tag tag = (Tag) iterator.next();
				System.out.println(tag.getId() + " " + tag.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
