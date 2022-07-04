package phone;

import java.time.LocalDateTime;

public class MissedCall{
	private char[] phoneNumber;
	LocalDateTime timestamp;
	public MissedCall(char[] phoneNumber,LocalDateTime timestamp){
		setPhoneNumber(phoneNumber);
		setTimestamp(timestamp);
	}
	private void setPhoneNumber(char[] phoneNumber)throws IllegalArgumentException{
		if(phoneNumber!=null && phoneNumber.length==10){
			this.phoneNumber=phoneNumber;
		}else{
			throw new IllegalArgumentException("Phone number should consist of 10 digits!");
		}
	}
	private void setTimestamp(LocalDateTime timestamp)throws IllegalArgumentException{
		if(timestamp!=null){
			this.timestamp=timestamp;
		}else{
			throw new IllegalArgumentException("Timestamp should not be null!");
		}
	}
	public char[] getPhoneNumber(){
		return this.phoneNumber;
	}
	public LocalDateTime getTimestamp(){
		return this.timestamp;
	}
	public String toString(){
		String str=
			"Phone Number:"+new String(this.phoneNumber)
			+"\nTimestamp:"+this.timestamp;
		return str;
	}
}
