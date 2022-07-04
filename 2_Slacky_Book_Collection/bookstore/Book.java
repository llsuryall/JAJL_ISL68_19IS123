package bookstore;

public class Book{
	private String title,author,publisher;
	private float price;
	public Book(String title,String author,String publisher,float price){
		setTitle(title);
		setAuthor(author);
		setPublisher(publisher);
		setPrice(price);
	}
	private void setTitle(String title)throws IllegalArgumentException{
		try{
			checkNotNullThrow(title);
			this.title=title;
		}catch(Exception e){
			throw new IllegalArgumentException("Title",e);
		}
	}
	private void setAuthor(String author)throws IllegalArgumentException{
		try{
			checkNotNullThrow(author);
			this.author=author;
		}catch(Exception e){
			throw new IllegalArgumentException("Author",e);
		}
		
	}
	public void setPublisher(String publisher)throws IllegalArgumentException{
		try{
			checkNotNullThrow(publisher);
			this.publisher=publisher;
		}catch(Exception e){
			throw new IllegalArgumentException("Publisher",e);
		}
	}
	private void setPrice(float price)throws IllegalArgumentException{
		if(price>=0){
			this.price=price;
		}else{
			throw new IllegalArgumentException("Price should be greater than or equal to 0!");
		}
	}
	public static void checkNotNullThrow(String str)throws IllegalArgumentException{
		if(str==null){
			throw new IllegalArgumentException("String should not be null!");
		}
	}
	public String getTitle(){
		return this.title;
	}
	public String getAuthor(){
		return this.author;
	}
	public String getPublisher(){
		return this.publisher;
	}
	public float getPrice(){
		return this.price;
	}
	public String toString(){
		String str=
		"Title : "+this.title+
		"\nAuthor : "+this.author+
		"\nPublisher : "+this.publisher+
		"\nPrice : "+this.price;
		return str;
	}
}
