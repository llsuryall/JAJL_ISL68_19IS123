package phone;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Contact{
	private char[] phoneNumber;
	private String name;
	public Contact(char[] phoneNumber,String name){
		setPhoneNumber(phoneNumber);
		setName(name);
	}
	private void setPhoneNumber(char[] phoneNumber)throws IllegalArgumentException{
		if(phoneNumber!=null && phoneNumber.length==10){
			this.phoneNumber=phoneNumber;
		}else{
			throw new IllegalArgumentException("Phone number should consist of 10 digits!");
		}
	}
	private void setName(String name)throws IllegalArgumentException{
		if(name!=null && name.length()>0){
			this.name=name;
		}else{
			throw new IllegalArgumentException("The name should have one or more characters!");
		}
	}
	public String getName(){
		return this.name;
	}
	public char[] getPhoneNumber(){
		return this.phoneNumber;
	}
	public static Contact[] readContactsCSV(String file_path)throws FileNotFoundException{
		File csvFile=new File(file_path);
		Scanner fileScanner=new Scanner(csvFile);
		fileScanner.useDelimiter(",|\n");
		ArrayList<Contact> contacts=new ArrayList<Contact>();
		char[] phoneNumber;
		String name;
		String temp;
		while(true){
			if(fileScanner.hasNext()){
				phoneNumber=fileScanner.next().toCharArray();
			}else{
				break;
			}
			if(fileScanner.hasNext()){
				name=fileScanner.next();
			}else{
				break;
			}
			contacts.add(new Contact(phoneNumber,name));
		}
		Contact[] contacts_arr=new Contact[contacts.size()];
		contacts.toArray(contacts_arr);
		return contacts_arr;
	}
	public String toString(){
		String str=this.name+" : ";
		str+=new String(phoneNumber);
		return str;
	}
}
