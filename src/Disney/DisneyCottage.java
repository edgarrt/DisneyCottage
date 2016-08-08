package Disney;


import javax.swing.*;

import java.awt.Checkbox;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class DisneyCottage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel p;
	JLabel roominfo;
	JLabel title;	
	JLabel desc;	
	JLabel length;
	JLabel rowboat;	
	JTextField weeks;
	Checkbox single;
	Checkbox doublebed;
	Checkbox boat;
	JButton total; 
	JLabel arrival;
	JTextField month;
	JTextField day;
	JTextField year;
	JLabel departure;
	JTextField endmonth;
	JTextField endday;
	JTextField endyear;
	JLabel blank;
	
	
	DisneyCottage() {
		setTitle("Disney Cottage Reservation");
		setSize(600,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildPanel();
		add(p);
		setVisible(true);
	}

	void buildPanel() {
		
		title = new JLabel("This application serves to assist you in reserving a cottage and row boats for your stay.");
		roominfo = new JLabel(" The 1 bed room is $600.00 per week and the 2 bedroom is $850.00 per week");
		rowboat = new JLabel("You also have an option to rent a row boat for $60.00 per week");
		
		arrival = new JLabel("Please enter your arrival date");
		month = new JTextField("month");
		day = new JTextField("day");
		year = new JTextField("year");
		
		departure = new JLabel("Please enter your departure date");
		endmonth = new JTextField("month");
		endday = new JTextField("day");
		endyear = new JTextField("year");
		boat = new Checkbox("Would you like to reserve a rowboat too?");
		single = new Checkbox("Save me a 1 bedroom!!");
		doublebed = new Checkbox("Save me a 2 bedroom!!");
		total = new JButton("Book it now.");
		total.addActionListener(new buttonClick());
	
		p = new JPanel();
		p.add(title);
		p.add(roominfo);
		p.add(rowboat);
		p.add(arrival);
		p.add(month);
		p.add(day);
		p.add(year);
		p.add(departure);
		p.add(endmonth);
		p.add(endday);
		p.add(endyear);
		p.add(boat);
		p.add(single);
		p.add(doublebed);
		p.add(total);
		

	}

	
	public static void main(String [] a)
	{
		DisneyCottage sim = new DisneyCottage();	
	}
	
	


public class buttonClick implements ActionListener {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	JTextField weeks;
	
	public void actionPerformed(ActionEvent e)
	{
//		Object source = e.getSource();	
		String A = month.getText();
		String B = day.getText();
		String C = year.getText();
		
		String D = endmonth.getText();
		String E = endday.getText();
		String F = endyear.getText();
		
		String arrival = String.format("%s %s %s",B,A,C );
		String departure = String.format("%s %s %s",E,D,F );
		
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        final LocalDate firstDate = LocalDate.parse(arrival, formatter);
        final LocalDate secondDate = LocalDate.parse(departure, formatter);
        final long days = ChronoUnit.DAYS.between(firstDate, secondDate);
//        System.out.println("Days between: " + days);
        double daysLeft = days % 7; 
        double numweeks = (days - daysLeft) / 7;
       
        
        boolean rowboat = boat.getState();
        boolean singlebed = single.getState();
        
        
        if (rowboat && singlebed ){
        	//Reserved singlebed 
        	//Rowboat was added to reservation
        	double boatTotal = numweeks * 60.00; 
        	double dayRent = (daysLeft / 7 ) * 60.00;
        	
        	double roomTotal = numweeks * 600.00;
        	double dayRoom = (daysLeft / 7 ) * 600.00; 
        	
        	double finalTotal = boatTotal + dayRent + roomTotal + dayRoom; 
        	

        	JOptionPane.showMessageDialog(null, String.format("Congratulations you reserved your stay for %.1f weeks and %.1f days and your final total will be $%.2f ", numweeks, daysLeft, finalTotal));
            
        	 
        }
        else if ( rowboat && !singlebed){
        	// Reserved double bedroom
        	//Rowboat was added to reservation
        	
        	double boatTotal = numweeks * 60.00; 
        	double dayRent = (daysLeft / 7 ) * 60.00;
        	
        	double roomTotal = numweeks * 850.00;
        	double dayRoom = (daysLeft / 7 ) * 850.00; 
        	
        	double finalTotal = boatTotal + dayRent + roomTotal + dayRoom; 
        	
        	JOptionPane.showMessageDialog(null, String.format("Congratulations you reserved your stay for %.1f weeks and %.1f days and your final total will be $%.2f ", numweeks, daysLeft, finalTotal));
            
        	
        
        }
        else if (!rowboat && singlebed) {
        	// reserved single bedroom only 
        	

        	double roomTotal = numweeks * 600.00;
        	double dayRoom = (daysLeft / 7 ) * 600.00; 
        	
        	double finalTotal =  roomTotal + dayRoom; 
        	

        	JOptionPane.showMessageDialog(null, String.format("Congratulations you reserved your stay for %.1f weeks and %.1f days and your final total will be $%.2f ", numweeks, daysLeft, finalTotal));
            
        	
        }
        else{
        	//no rowboat and double bed 

        	double roomTotal = numweeks * 850.00;
        	double dayRoom = (daysLeft / 7 ) * 850.00; 
        	
        	double finalTotal =  roomTotal + dayRoom; 
        	

        	JOptionPane.showMessageDialog(null, String.format("Congratulations you reserved your stay for %.1f weeks and %.1f days and your final total will be $%.2f ", numweeks, daysLeft, finalTotal));
            
        	
        }
           
    }
	
	}

}
