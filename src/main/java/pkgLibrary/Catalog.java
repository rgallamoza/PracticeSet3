package pkgLibrary;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
	
	public static Book GetBook(Catalog cat,String BookID) throws BookException{
		Book FoundBook = null;
		
		for(Book b : cat.getBooks()){
			if(b.getId().equals(BookID)){
				FoundBook = b;
			}
		}
		if(FoundBook instanceof Book){
			return FoundBook;
		}
		else{
			throw new BookException(BookID);
		}
	}
	
	public static void AddBook(int CatalogID, Book newBook) throws BookException{
		
		boolean BookExists = false;
		
		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = ((Catalog) jaxbUnmarshaller.unmarshal(file));
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		if(cat.getId()==CatalogID){
			
			for(Book b : cat.getBooks()){
				
				if(b.getId().equals(newBook.getId())){
					
					BookExists = true;
				}
			}
			if(BookExists){
				
				throw new BookException(newBook);
			}
			else{
				cat.getBooks().add(newBook);
				
				try {

					basePath = new File("").getAbsolutePath();
					basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
					file = new File(basePath);

					JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
					Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

					// output pretty printed
					jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

					jaxbMarshaller.marshal(cat, file);
					jaxbMarshaller.marshal(cat, System.out);

				} catch (JAXBException e) {
					e.printStackTrace();
				}
			}
		}
		else{
			
			System.out.println("Catalog ID not found.");
		}

		
	}


	
	
	
	
}
