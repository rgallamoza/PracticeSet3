package pkgLibrary;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Catalog {

	@XmlAttribute
	int id;	
	
	@XmlElement(name="book")
	ArrayList<Book> books;
	
	
	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Book> getBooks() {
		return books;
	}
	

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	public static Book GetBook(Catalog cat,String id) throws BookException{
		
		for(Book b : cat.getBooks()){
			if(b.getId()==id){
				return b;
			}
		}
		
		throw new BookException(id);
	}
	
/*	public void AddBook(String CatalogID, Book newBook) throws BookException{
		
		for(Catalog cat : ReadXMLFile()){
			if(b.getId()==newBook.getId()){
				throw new BookException(newBook);
			}
		}
		this.getBooks().add(newBook);
	}*/


	
	
	
	
}