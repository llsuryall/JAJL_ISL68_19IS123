package student_info;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentInformationGUI extends JFrame implements ActionListener{
	private Container mainContentPane;
	private JTextField usnField,nameField,ageField,addressField;
	private JTextField[] sgpas;
	private JButton submitButton;
	private float cgpa;
	public StudentInformationGUI(){
		setTitle("Student Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500,300));
		setResizable(false);
		mainContentPane=getContentPane();
		mainContentPane.setLayout(new BoxLayout(mainContentPane, BoxLayout.PAGE_AXIS));
		//Main Info Panel
		JPanel mainInfoPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,30));
		mainInfoPanel.add(usnField=new JTextField("USN",10));
		mainInfoPanel.add(nameField=new JTextField("Name",10));
		mainInfoPanel.add(ageField=new JTextField("Age",10));
		mainInfoPanel.add(addressField=new JTextField("Address",10));
		mainContentPane.add(mainInfoPanel);
		Font defaultFont=new Font(Font.SANS_SERIF,Font.PLAIN,16);
		usnField.setFont(defaultFont);
		nameField.setFont(defaultFont);
		ageField.setFont(defaultFont);
		addressField.setFont(defaultFont);
		//SGPA Panel
		JPanel sgpaPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,15,30));
		sgpas=new JTextField[8];
		for(int i=0;i<8;i++){
			sgpas[i]=new JTextField("SEM "+(i+1));
			sgpaPanel.add(sgpas[i]);
		}
		mainContentPane.add(sgpaPanel);
		//Buttons Panel
		JPanel buttonsPanel=new JPanel();
		submitButton=new JButton("Submit");
		submitButton.addActionListener(this);
		buttonsPanel.add(submitButton);
		mainContentPane.add(buttonsPanel);
		pack();
	}
	public void actionPerformed(ActionEvent event){
		if(event.getSource()==submitButton){
			try{
				validateAll();
			}catch(Exception e){
				JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid input!", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String result=
				"USN : "+usnField.getText()+"\n"+
				"Name : "+nameField.getText()+"\n"+
				"Age : "+ageField.getText()+"\n"+
				"Address : "+addressField.getText()+"\n"+
				"CGPA : "+cgpa;
			mainContentPane.removeAll();
			JTextArea resultTextArea=new JTextArea(result);
			resultTextArea.setEditable(false);
			mainContentPane.add(resultTextArea);
			invalidate();
			validate();
			repaint();
		}
	}
	public void validateAll()throws Exception{
		if(usnField.getText().length()!=10){
			throw new Exception("Invalid USN!");
		}
		if(nameField.getText().length()<=0){
			throw new Exception("Invalid Name!");
		}
		if(Integer.parseInt(ageField.getText())<=0){
			throw new Exception("Invalid Age!");
		}
		if(addressField.getText().length()<=0){
			throw new Exception("Invalid Address!");
		}
		cgpa=0;
		for(int i=0;i<8;i++){
			float sgpa=Float.parseFloat(sgpas[i].getText());
			if(sgpa<0 || sgpa>10){
				throw new Exception("Invalid SGPA of Semester "+(i+1)+"!");
			}
			cgpa+=sgpa;
		}
		cgpa/=8;
	}
	public static void main(String[] args){
		JFrame studentGUI=new StudentInformationGUI();
		studentGUI.setVisible(true);
	}
}
