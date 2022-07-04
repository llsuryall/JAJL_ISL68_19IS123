package phone;

import java.util.Scanner;
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Arrays;

class Client{
	private static LinkedList<MissedCall> missedCallsList;
	private static Contact[] contacts;
	private static Scanner sc;
	private static int inputPositiveInteger(){
		int n=-1;
		while(true){
			n=sc.nextInt();
			sc.nextLine();
			if(n<=0){
				System.out.print("Enter a positive number - ");
			}else{
				break;
			}
		}
		return n;
	}
	private static char[] inputPhoneNumber(){
		String inp;
		while(true){
			inp=sc.nextLine();
			if(inp==null || inp.length()!=10){
				System.out.print("Enter a 10 digit number - ");
			}else{
				break;
			}
		}
		return inp.toCharArray();
	}
	private static LocalDateTime inputDateTime(){
		LocalDateTime dateTime;
		while(true){
			try{
				dateTime=LocalDateTime.parse(sc.nextLine());
				break;
			}catch(DateTimeParseException e){
				System.out.print("Enter date time in the format: yyyy-MM-ddTHH:mm:ss -");
			}
		}
		return dateTime;
	}
	public static String findContactName(char[] phoneNumber){
		for(Contact contact:contacts){
			if(Arrays.equals(contact.getPhoneNumber(),phoneNumber)){
				return contact.getName();
			}
		}
		return null;
	}
	public static void main(String[] args)throws FileNotFoundException{
		sc=new Scanner(System.in);
		contacts=Contact.readContactsCSV("data/contacts.txt");
		missedCallsList=new LinkedList<MissedCall>();
		System.out.print("Enter the number of calls to record(only 10 latest will be recorded) - ");
		int n=inputPositiveInteger();
		char[] phoneNumber;
		LocalDateTime timestamp;
		for(int i=0;i<n;i++){
			System.out.print("Enter the phone number - ");
			phoneNumber=inputPhoneNumber();
			System.out.print("Enter the timestamp - ");
			timestamp=inputDateTime();
			int j=0;
			for(MissedCall missedCall:missedCallsList){
				if(missedCall.timestamp.isBefore(timestamp)){
					break;
				}
				j++;
			}
			missedCallsList.add(j,new MissedCall(phoneNumber,timestamp));
			if(missedCallsList.size()>10){
				missedCallsList.removeLast();
			}
		}
		Iterator<MissedCall> missedCallsIterator;
		String callerName;
		MissedCall missedCall=null;
		int inp;
		int i;
		boolean shouldMove;
		System.out.println("1 - to delete the missed call");
		System.out.println("2 - to display the details of the missed call");
		System.out.println("3>= - to move to next missed call");
		while(missedCallsList.size()>0){
			missedCallsIterator=missedCallsList.iterator();
			i=0;
			shouldMove=true;
			System.out.println("Starting from the beginning of the list-");
			while(missedCallsIterator.hasNext()||(!shouldMove)){
				if(shouldMove){
					missedCall=missedCallsIterator.next();
					i++;
				}
				System.out.print("Position "+i+"->");
				inp=inputPositiveInteger();
				if(inp==1){
					missedCallsIterator.remove();
					i--;
					shouldMove=true;
				}else if(inp==2){
					callerName=findContactName(missedCall.getPhoneNumber());
					if(callerName==null){
						callerName="Private Caller";
					}
					System.out.println("Caller Name: "+callerName);
					System.out.println(missedCall);
					shouldMove=false;
				}else{
					shouldMove=true;
				}
			}
		}
		System.out.println("The list is empty! Exiting...");
	}
}
