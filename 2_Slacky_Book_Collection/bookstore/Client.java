package bookstore;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Client{
	private static HashMap<Integer,Book> booksCollection;
	private static Scanner inputScanner;
	private static Book[] sortByPrice(){
		ArrayList<Book> booksList=new ArrayList<Book>(booksCollection.size());
		for(Map.Entry<Integer,Book> bookEntry:booksCollection.entrySet()){
			booksList.add(bookEntry.getValue());
		}
		Collections.sort(booksList,new PriceComparator());
		Book[] temp_arr=new Book[booksList.size()];
		booksList.toArray(temp_arr);
		return temp_arr;
	}
	private static Book[] findBooksFromAuthor(String author){
		ArrayList<Book> booksList=new ArrayList<Book>(booksCollection.size());
		for(Map.Entry<Integer,Book> bookEntry:booksCollection.entrySet()){
			if(bookEntry.getValue().getAuthor().equals(author)){
				booksList.add(bookEntry.getValue());
			}
		}
		Book[] temp_arr=new Book[booksList.size()];
		booksList.toArray(temp_arr);
		return temp_arr;
	}
	private static Book[] findBooksFromPublisher(String publisher){
		ArrayList<Book> booksList=new ArrayList<Book>(booksCollection.size());
		for(Map.Entry<Integer,Book> bookEntry:booksCollection.entrySet()){
			if(bookEntry.getValue().getPublisher().equals(publisher)){
				booksList.add(bookEntry.getValue());
			}
		}
		Book[] temp_arr=new Book[booksList.size()];
		booksList.toArray(temp_arr);
		return temp_arr;
	}
	private static Book[] findBooksWorthMoreThanOrEqualTo(float price){
		ArrayList<Book> booksList=new ArrayList<Book>(booksCollection.size());
		for(Map.Entry<Integer,Book> bookEntry:booksCollection.entrySet()){
			if(bookEntry.getValue().getPrice()>=price){
				booksList.add(bookEntry.getValue());
			}
		}
		Book[] temp_arr=new Book[booksList.size()];
		booksList.toArray(temp_arr);
		return temp_arr;
	}
	private static Book[] searchBookTitles(String query){
		ArrayList<Book> booksList=new ArrayList<Book>(booksCollection.size());
		for(Map.Entry<Integer,Book> bookEntry:booksCollection.entrySet()){
			if(bookEntry.getValue().getTitle().contains(query)){
				booksList.add(bookEntry.getValue());
			}
		}
		Book[] temp_arr=new Book[booksList.size()];
		booksList.toArray(temp_arr);
		return temp_arr;
	}
	private static Book[] updatePublisherWithTitle(String title, String new_publisher_string){
		ArrayList<Book> booksList=new ArrayList<Book>(booksCollection.size());
		for(Map.Entry<Integer,Book> bookEntry:booksCollection.entrySet()){
			if(bookEntry.getValue().getTitle().equals(title)){
				bookEntry.getValue().setPublisher(new_publisher_string);
				booksList.add(bookEntry.getValue());
			}
		}
		Book[] temp_arr=new Book[booksList.size()];
		booksList.toArray(temp_arr);
		return temp_arr;
	}
	private static void printBooks(Book[] books){
		int count=1;
		for(Book book:books){
			System.out.println("Book "+count+" -");
			System.out.println(book);
			count++;
		}
		if(count==1){
			System.out.println("No books!");
		}
	}
	private static int inputPositiveInteger(){
		int n=-1;
		while(true){
			n=inputScanner.nextInt();
			inputScanner.nextLine();
			if(n<=0){
				System.out.print("Enter a positive number - ");
			}else{
				break;
			}
		}
		return n;
	}
	private static float inputPositiveDecimal(){
		float num=-1;
		while(true){
			num=inputScanner.nextFloat();
			inputScanner.nextLine();
			if(num<0){
				System.out.print("Enter a non negative number - ");
			}else{
				break;
			}
		}
		return num;
	}
	private static String inputNonNullString(){
		String str=null;
		while(true){
			str=inputScanner.nextLine();
			if(str==null || str.length()<=0){
				System.out.print("Enter a string with one or more characters - ");
			}else{
				break;
			}
		}
		return str;
	}
	public static void main(String[] args){
		inputScanner=new Scanner(System.in);
		System.out.print("Enter the number of books - ");
		int n=inputPositiveInteger();
		booksCollection=new HashMap<Integer,Book>();
		Book[] books;
		String title,author,publisher;
		float price;
		for(int i=0;i<n;i++){
			System.out.println("Book "+(i+1)+" -");
			System.out.print("Enter the title of the book - ");
			title=inputNonNullString();
			System.out.print("Enter the authors name - ");
			author=inputNonNullString();
			System.out.print("Enter the publisher's name - ");
			publisher=inputNonNullString();
			System.out.print("Enter the price of the book - ");
			price=inputPositiveDecimal();
			booksCollection.put(i+1,new Book(title,author,publisher,price));
		}
		System.out.println();
		System.out.println("Books in ascending order of price - ");
		printBooks(sortByPrice());
		System.out.println();
		System.out.print("Enter an author name to find all his books - ");
		author=inputNonNullString();
		books=findBooksFromAuthor(author);
		System.out.println("The books of this author are -");
		printBooks(books);
		System.out.println();
		System.out.print("Enter minimum price of books to filter - ");
		price=inputPositiveDecimal();
		books=findBooksWorthMoreThanOrEqualTo(price);
		System.out.println("Books with price greater than "+price+" -");
		printBooks(books);
		System.out.println();
		System.out.print("Enter a query to search through all available titles(case-sensitve) - ");
		title=inputNonNullString();
		System.out.println("Query results -");
		books=searchBookTitles(title);
		printBooks(books);
		System.out.println();
		System.out.print("Enter a publishers name to find all their books - ");
		publisher=inputNonNullString();
		books=findBooksFromPublisher(publisher);
		System.out.println("The books from this publisher are -");
		printBooks(books);
		System.out.println();
		System.out.print("Enter a title to update it's publisher(case-sensitve) - ");
		title=inputNonNullString();
		System.out.print("Enter the new publisher name - ");
		publisher=inputNonNullString();
		books=updatePublisherWithTitle(title,publisher);
		System.out.println("Update books -");
		printBooks(books);
	}
}

class PriceComparator implements Comparator<Book>{
	public int compare(Book a,Book b){
		return (int)(a.getPrice()-b.getPrice());
	}
}
