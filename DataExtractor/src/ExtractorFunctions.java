import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.touch.FlickAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



import net.sourceforge.htmlunit.corejs.javascript.Function;

public class ExtractorFunctions extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JLabel THETITLE, SUBTITLE1, SUBTITLE2, SUBTITLE3,SUBTITLE4,SUBTITLE5,SUBTITLE6,SUBTITLE7,SUBTITLE8,SUBTITLE9,SUBTITLE10,SUBTITLE11,SUBTITLE12,SUBTITLE13,SUBTITLE14,SUBTITLE15,SUBTITLE16;
	
	public JTextField airportname;
	
	private JComboBox<String> monthsboxfrom;  private JComboBox<String> daysboxfrom;  private JComboBox<String> yearsboxfrom;
    private JComboBox<String> monthsboxto;    private JComboBox<String> daysboxto;    private JComboBox<String> yearsboxto;
	
	private JComboBox<String> timebox;
	
	public String [] months = {"January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December" };
	
	public String [] days = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15", "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31" };
	
	public String [] years = {"2019","2018","2017","2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006","2005" ,"2004" ,"2003" ,"2002","2001","2000"};
	
	public String [] time = {"ALL", "12:00 AM","1:00 AM","2:00 AM","3:00 AM","4:00 AM","5:00 AM","6:00 AM","7:00 AM","8:00 AM","9:00 AM","10:00 AM","11:00 AM", "12:00 PM","1:00 PM","2:00 PM","3:00 PM","4:00 PM","5:00 PM","6:00 PM","7:00 PM","8:00 PM","9:00 PM","10:00 PM","11:00 PM", "editable"};
	
    public JButton calculate;
	
    public JTextArea finalresultAverageTemperature,MaximumTemperaturearea, AverageTemperaturearea, MinimumTemperaturearea;
	public JTextArea MaximumofMaximumTemperaturearea, AverageofMaximumTemperaturearea,MinimumofMaximumTemperaturearea;
	public JTextArea MaximumofMinimumTemperaturearea, AverageMinimumTemperaturearea, MinimumofMinimumTemperaturearea;

	public JTextArea MaximumPressurearea, averagePressurearea,MinimumPressurearea;

    public JTextArea Date, ObteiningTemperature;
	
    public String selectedairport;
    
	public String selectedmonthfrom;  public String selecteddayfrom;  public String selectedyearfrom;
	public String selectedmonthto;    public String selecteddayto;    public String selectedyearto;
	
	
	List<String> listofmonths = new ArrayList<>();  List<String> listofdays = new ArrayList<>();  List<String> listofyears = new ArrayList<>();
	
	List<String> listoftemperatures = new ArrayList<>();   List<Double> listoftMaximumemperatures = new ArrayList<>();   List<Double> listoftMinimumemperatures = new ArrayList<>();
	
	List<Double> listofPressures = new ArrayList<>();	
	
	public double MaximumPressure;     public double MinimumPressure;
	
	public double MaximumTemperature;  public double MinimumTemperature;
	
	public double MaximumTemperaturefromlistoftMinimumemperatures;  public double MinimumTemperaturefromlistoftMinimumemperatures;
	
	public double MaximumTemperaturefromlistoftMaximumemperatures;  public double MinimumTemperaturefromlistoftMaximumemperatures;
	
	public String [] arrayofdate = {};
	
	public  double  listoftMaximumemperaturess;    public double  listoftMinimumemperaturess;
	
	
	double averagetemperature;   double averagetemperatureofMaximum;   double averagetemperatureofMinimum;
	
	double averagepressure;
	
	public String selectedtime;
	
	public Formatter file;
	
	int ATX = 32;  int ATY = 42;           int Xto =   -61;  int Xfrom = -61;               int Yto = 125;   int Yfrom = 100;
	
	
	
    WebDriver driver;
	
	public  ExtractorFunctions ()
	{    

		super("Weather Application");
		setLayout(null);
		
		try {
			setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("fav.png")));
			
			
			}catch(Exception e){
			   System.out.println("Application icon not found");
			}
		
		
		   THETITLE = new JLabel("Weather Data Extractor (CM)");
		   THETITLE.setFont(new Font("Dialog", Font.BOLD, 15));
		   THETITLE.setBounds(18, 7, 295, 20);
	       add(THETITLE);
	       
	       SUBTITLE1 = new JLabel("AIRPORT");
	       SUBTITLE1.setFont(new Font("Dialog", Font.PLAIN, 9));
	       SUBTITLE1.setBounds(22+ATX, -10+ATY, 295, 24);
	       add(SUBTITLE1); 
	       
	       SUBTITLE9 = new JLabel("       TIME");
	       SUBTITLE9.setFont(new Font("Dialog", Font.PLAIN, 9));
	       SUBTITLE9.setBounds(115+ATX, -10+ATY, 295, 24);
	       add(SUBTITLE9); 
	       
	       SUBTITLE2 = new JLabel("HOUR: ");
	       SUBTITLE2.setFont(new Font("Dialog", Font.PLAIN, 9));
	       SUBTITLE2.setBounds(77+ATX , 8+ATY, 295, 24);
	       add(SUBTITLE2);
	       
	       
	       SUBTITLE3 = new JLabel("ICAO ID:");
	       SUBTITLE3.setFont(new Font("Dialog", Font.PLAIN, 9));
	       SUBTITLE3.setBounds(ATX-22 , 8+ATY, 295, 24);
	       add(SUBTITLE3); 
	       
	       SUBTITLE4 = new JLabel("    Month");
	       SUBTITLE4.setFont(new Font("Dialog", Font.PLAIN, 10));
	       SUBTITLE4.setBounds(100+Xfrom , Yfrom-23, 295, 24);
	       add(SUBTITLE4); 
	       
	       SUBTITLE5 = new JLabel("Day");
	       SUBTITLE5.setFont(new Font("Dialog", Font.PLAIN, 10));
	       SUBTITLE5.setBounds(191+Xfrom, Yfrom-23, 295, 24);
	       add(SUBTITLE5); 
	       
	       
	       SUBTITLE6 = new JLabel("  Year");
	       SUBTITLE6.setFont(new Font("Dialog", Font.PLAIN, 10));
	       SUBTITLE6.setBounds(237+Xfrom , Yfrom-23, 295, 24);
	       add(SUBTITLE6); 	       
	       
	       //fstWeightRIGHTin.setFont(new Font("DialogInput", Font.PLAIN, 12 ));
	       SUBTITLE7 = new JLabel(" FROM:");
	       SUBTITLE7.setFont(new Font("Dialog", Font.PLAIN, 8));
	       SUBTITLE7.setBounds(2 , Yfrom-5, 295, 24);
	       add(SUBTITLE7);
	       
	       
	       SUBTITLE8 = new JLabel("      TO:");
	       SUBTITLE8.setFont(new Font("Dialog", Font.PLAIN, 8));
	       SUBTITLE8.setBounds(2 , Yto-5, 295, 24);
	       add(SUBTITLE8); 
		
		airportname = new JTextField();
		airportname.setHorizontalAlignment(JTextField.CENTER);
		airportname.setForeground(Color.BLUE);
		airportname.setBounds(22+ATX, 10+ATY, 45, 18);
		add(airportname);
		airportname.addKeyListener(new KeyAdapter() 
		   {
			   public void keyTyped(KeyEvent e) 
			   {
				   char i = e.getKeyChar();
				   if ((i == '0' ||  i == '2' ||  i == '3' ||  i == '4' ||  i == '5' ||  i == '6' ||  i == '7' ||  i == '8' ||  i == '9')  && i != '\b') 
				   {
					   e.consume(); 
					   //JOptionPane.showMessageDialog(null, "INVALID INPUT");
				   }
				   
				   if (airportname.getText().length() >= 4) 
				   {
					   e.consume(); 
					   //JOptionPane.showMessageDialog(null, "INVALID INPUT");
				   }
				   
				   if (Character.isLowerCase(i)) {
					      e.setKeyChar(Character.toUpperCase(i));
					    }
				   
			   }
				   
		   }                         );
		
		
		airportname.addKeyListener(new KeyAdapter() 
		   {
			   public void keyTyped(KeyEvent e) 
			   {
	 		    		    
	 		    					
	 		    	
	                finalresultAverageTemperature.setBackground(Color.WHITE);
	                
	                MaximumofMaximumTemperaturearea.setBackground(Color.WHITE);
	                AverageofMaximumTemperaturearea.setBackground(Color.WHITE);
	                MinimumofMaximumTemperaturearea.setBackground(Color.WHITE);
	                
	 		    	MaximumTemperaturearea.setBackground(Color.WHITE);
	                AverageTemperaturearea.setBackground(Color.WHITE);
	 		    	MinimumTemperaturearea.setBackground(Color.WHITE);
	                
	                MaximumofMinimumTemperaturearea.setBackground(Color.WHITE);
	                AverageMinimumTemperaturearea.setBackground(Color.WHITE);
	                MinimumofMinimumTemperaturearea.setBackground(Color.WHITE);
	                
	                MaximumPressurearea.setBackground(Color.WHITE);
	                averagePressurearea.setBackground(Color.WHITE);
	                MinimumPressurearea.setBackground(Color.WHITE);
	 		    	
	 		    	}
	 		    	
	 		    	
	 		    	
	 		      }
	 		    		                               );
	 		  
		

		
		
		
		   timebox = new JComboBox<>(time);
		//   timebox.setEditable(true);
		//   timebox.setEnabled(true);
		   ((JLabel)timebox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		   timebox.setBounds(115+ATX, 10+ATY, 80, 18);
		   timebox.setFont(new Font("Sherif", Font.BOLD, 11));
		   timebox.setForeground(Color.BLUE);
		   add(timebox);
		
		   timebox.addActionListener
		    (
		 		      new ActionListener() 
		 		      {
		 		    	public void actionPerformed(ActionEvent e) 
		 		    	{
		 		    		
			                finalresultAverageTemperature.setBackground(Color.WHITE);
			                
			                MaximumofMaximumTemperaturearea.setBackground(Color.WHITE);
			                AverageofMaximumTemperaturearea.setBackground(Color.WHITE);
			                MinimumofMaximumTemperaturearea.setBackground(Color.WHITE);
			                
			 		    	MaximumTemperaturearea.setBackground(Color.WHITE);
			                AverageTemperaturearea.setBackground(Color.WHITE);
			 		    	MinimumTemperaturearea.setBackground(Color.WHITE);
			                
			                MaximumofMinimumTemperaturearea.setBackground(Color.WHITE);
			                AverageMinimumTemperaturearea.setBackground(Color.WHITE);
			                MinimumofMinimumTemperaturearea.setBackground(Color.WHITE);
			                
			                MaximumPressurearea.setBackground(Color.WHITE);
			                averagePressurearea.setBackground(Color.WHITE);
			                MinimumPressurearea.setBackground(Color.WHITE);
	 	
		 		    	}
		 		    	
	                    }
		   );
		   timebox.addActionListener
		    (
		 		      new ActionListener() 
		 		      {
		 		    	public void actionPerformed(ActionEvent e) 
		 		    	{
		 		    		String selectedchecktime = (String) timebox.getSelectedItem();
		 		    		if (selectedchecktime.equals("editable"))
		 		    	    {
		 		    		 
		 		    		 timebox.setEditable(true);
		 		   	         timebox.setEnabled(true);


		 		    		}else 
		 		    		{
			 		   		     timebox.setEditable(false);
			 		   	         timebox.setEnabled(true);	
		 		    		}
		 		    	}
		 		    	
	                    }
		   );		   
		   
	    	monthsboxto = new JComboBox<>(months);
		   ((JLabel)monthsboxto.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		   monthsboxto.setBounds(93+Xto,Yto-3, 87, 18);
		   monthsboxto.setFont(new Font("Sherif", Font.BOLD, 11));
		   monthsboxto.setForeground(Color.BLUE);
		   add(monthsboxto);
		   monthsboxto.addActionListener
		    (
		 		      new ActionListener() 
		 		      {
		 		    	public void actionPerformed(ActionEvent e) 
		 		    	{
		 		    		
			                finalresultAverageTemperature.setBackground(Color.WHITE);
			                
			                MaximumofMaximumTemperaturearea.setBackground(Color.WHITE);
			                AverageofMaximumTemperaturearea.setBackground(Color.WHITE);
			                MinimumofMaximumTemperaturearea.setBackground(Color.WHITE);
			                
			 		    	MaximumTemperaturearea.setBackground(Color.WHITE);
			                AverageTemperaturearea.setBackground(Color.WHITE);
			 		    	MinimumTemperaturearea.setBackground(Color.WHITE);
			                
			                MaximumofMinimumTemperaturearea.setBackground(Color.WHITE);
			                AverageMinimumTemperaturearea.setBackground(Color.WHITE);
			                MinimumofMinimumTemperaturearea.setBackground(Color.WHITE);
			                
			                MaximumPressurearea.setBackground(Color.WHITE);
			                averagePressurearea.setBackground(Color.WHITE);
			                MinimumPressurearea.setBackground(Color.WHITE);
	 	
		 		    	}
		 		    	
	                    }
		   );
		   
		   
		   daysboxto = new JComboBox<>(days);
		   ((JLabel)daysboxto.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		   daysboxto.setBounds(191+Xto, Yto-3, 39, 18);
		   daysboxto.setFont(new Font("Sherif", Font.BOLD, 11));
		   daysboxto.setForeground(Color.BLUE);
		   add(daysboxto);
		   daysboxto.addActionListener
		    (
		 		      new ActionListener() 
		 		      {
		 		    	public void actionPerformed(ActionEvent e) 
		 		    	{
		 		    		
			                finalresultAverageTemperature.setBackground(Color.WHITE);
			                
			                MaximumofMaximumTemperaturearea.setBackground(Color.WHITE);
			                AverageofMaximumTemperaturearea.setBackground(Color.WHITE);
			                MinimumofMaximumTemperaturearea.setBackground(Color.WHITE);
			                
			 		    	MaximumTemperaturearea.setBackground(Color.WHITE);
			                AverageTemperaturearea.setBackground(Color.WHITE);
			 		    	MinimumTemperaturearea.setBackground(Color.WHITE);
			                
			                MaximumofMinimumTemperaturearea.setBackground(Color.WHITE);
			                AverageMinimumTemperaturearea.setBackground(Color.WHITE);
			                MinimumofMinimumTemperaturearea.setBackground(Color.WHITE);
			                
			                MaximumPressurearea.setBackground(Color.WHITE);
			                averagePressurearea.setBackground(Color.WHITE);
			                MinimumPressurearea.setBackground(Color.WHITE);
		 		    	}
		 		    	
	                    }
		   );
		   
		   yearsboxto = new JComboBox<>(years);
		   ((JLabel)yearsboxto.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		   yearsboxto.setBounds(240+Xto, Yto-3, 56, 18);
		   yearsboxto.setFont(new Font("Sherif", Font.BOLD, 11));
		   yearsboxto.setForeground(Color.BLUE);
		   add(yearsboxto);
		   yearsboxto.addActionListener
		    (
		 		      new ActionListener() 
		 		      {
		 		    	public void actionPerformed(ActionEvent e) 
		 		    	{
		 		    		
			                finalresultAverageTemperature.setBackground(Color.WHITE);
			                
			                MaximumofMaximumTemperaturearea.setBackground(Color.WHITE);
			                AverageofMaximumTemperaturearea.setBackground(Color.WHITE);
			                MinimumofMaximumTemperaturearea.setBackground(Color.WHITE);
			                
			 		    	MaximumTemperaturearea.setBackground(Color.WHITE);
			                AverageTemperaturearea.setBackground(Color.WHITE);
			 		    	MinimumTemperaturearea.setBackground(Color.WHITE);
			                
			                MaximumofMinimumTemperaturearea.setBackground(Color.WHITE);
			                AverageMinimumTemperaturearea.setBackground(Color.WHITE);
			                MinimumofMinimumTemperaturearea.setBackground(Color.WHITE);
			                
			                MaximumPressurearea.setBackground(Color.WHITE);
			                averagePressurearea.setBackground(Color.WHITE);
			                MinimumPressurearea.setBackground(Color.WHITE);
	 	
		 		    	}
		 		    	
	                    }
		   );
		   
		   

	
		   
		   
		   
	    	monthsboxfrom = new JComboBox<>(months);
		   ((JLabel)monthsboxfrom.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		   monthsboxfrom.setBounds(93+Xfrom,Yfrom-3, 87, 18);
		   monthsboxfrom.setFont(new Font("Sherif", Font.BOLD, 11));
		   monthsboxfrom.setForeground(Color.BLUE);
		   add(monthsboxfrom);
		   monthsboxfrom.addActionListener
		    (
		 		      new ActionListener() 
		 		      {
		 		    	public void actionPerformed(ActionEvent e) 
		 		    	{
		 		    		
			                finalresultAverageTemperature.setBackground(Color.WHITE);
			                
			                MaximumofMaximumTemperaturearea.setBackground(Color.WHITE);
			                AverageofMaximumTemperaturearea.setBackground(Color.WHITE);
			                MinimumofMaximumTemperaturearea.setBackground(Color.WHITE);
			                
			 		    	MaximumTemperaturearea.setBackground(Color.WHITE);
			                AverageTemperaturearea.setBackground(Color.WHITE);
			 		    	MinimumTemperaturearea.setBackground(Color.WHITE);
			                
			                MaximumofMinimumTemperaturearea.setBackground(Color.WHITE);
			                AverageMinimumTemperaturearea.setBackground(Color.WHITE);
			                MinimumofMinimumTemperaturearea.setBackground(Color.WHITE);
			                
			                MaximumPressurearea.setBackground(Color.WHITE);
			                averagePressurearea.setBackground(Color.WHITE);
			                MinimumPressurearea.setBackground(Color.WHITE);
	 	
		 		    	}
		 		    	
	                    }
		    );
		   
		   
		   daysboxfrom = new JComboBox<>(days);
		   ((JLabel)daysboxfrom.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		   daysboxfrom.setBounds(191+Xfrom, Yfrom-3, 39, 18);
		   daysboxfrom.setFont(new Font("Sherif", Font.BOLD, 11));
		   daysboxfrom.setForeground(Color.BLUE);
		   add(daysboxfrom);
		   daysboxfrom.addActionListener
		    (
		 		      new ActionListener() 
		 		      {
		 		    	public void actionPerformed(ActionEvent e) 
		 		    	{
		 		    		
			                finalresultAverageTemperature.setBackground(Color.WHITE);
			                
			                MaximumofMaximumTemperaturearea.setBackground(Color.WHITE);
			                AverageofMaximumTemperaturearea.setBackground(Color.WHITE);
			                MinimumofMaximumTemperaturearea.setBackground(Color.WHITE);
			                
			 		    	MaximumTemperaturearea.setBackground(Color.WHITE);
			                AverageTemperaturearea.setBackground(Color.WHITE);
			 		    	MinimumTemperaturearea.setBackground(Color.WHITE);
			                
			                MaximumofMinimumTemperaturearea.setBackground(Color.WHITE);
			                AverageMinimumTemperaturearea.setBackground(Color.WHITE);
			                MinimumofMinimumTemperaturearea.setBackground(Color.WHITE);
			                
			                MaximumPressurearea.setBackground(Color.WHITE);
			                averagePressurearea.setBackground(Color.WHITE);
			                MinimumPressurearea.setBackground(Color.WHITE);
	 	
		 		    	}
		 		    	
	                    }
		    );
		   
		   yearsboxfrom = new JComboBox<>(years);
		   ((JLabel)yearsboxfrom.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		   yearsboxfrom.setBounds(240+Xfrom, Yfrom-3, 56, 18);
		   yearsboxfrom.setFont(new Font("Sherif", Font.BOLD, 11));
		   yearsboxfrom.setForeground(Color.BLUE);
		   add(yearsboxfrom);
		   yearsboxfrom.addActionListener
		    (
		 		      new ActionListener() 
		 		      {
		 		    	public void actionPerformed(ActionEvent e) 
		 		    	{
		 		    		
		                finalresultAverageTemperature.setBackground(Color.WHITE);
		                
		                MaximumofMaximumTemperaturearea.setBackground(Color.WHITE);
		                AverageofMaximumTemperaturearea.setBackground(Color.WHITE);
		                MinimumofMaximumTemperaturearea.setBackground(Color.WHITE);
		                
		 		    	MaximumTemperaturearea.setBackground(Color.WHITE);
		                AverageTemperaturearea.setBackground(Color.WHITE);
		 		    	MinimumTemperaturearea.setBackground(Color.WHITE);
		                
		                MaximumofMinimumTemperaturearea.setBackground(Color.WHITE);
		                AverageMinimumTemperaturearea.setBackground(Color.WHITE);
		                MinimumofMinimumTemperaturearea.setBackground(Color.WHITE);
		                
		                MaximumPressurearea.setBackground(Color.WHITE);
		                averagePressurearea.setBackground(Color.WHITE);
		                MinimumPressurearea.setBackground(Color.WHITE);
		 		    	
	 	
		 		    	}
		 		    	
	                    }
		   );
		   

	        SUBTITLE10 = new JLabel("Average Temp (Cº) ");
	        SUBTITLE10.setFont(new Font("Dialog", Font.BOLD, 12));
	        SUBTITLE10.setBounds(182+Xfrom,Yto+28 , 375, 13);
            add(SUBTITLE10);
            finalresultAverageTemperature = new JTextArea();
            finalresultAverageTemperature.setText("");
            finalresultAverageTemperature.setBounds(220+Xfrom, Yto+50, 25, 22);
            finalresultAverageTemperature.setEditable(false);
			add(finalresultAverageTemperature);
			Border borderFRAT = BorderFactory.createLineBorder(Color.BLACK);
			finalresultAverageTemperature.setBorder(BorderFactory.createCompoundBorder(borderFRAT,
            BorderFactory.createEmptyBorder(1, 3, 10, 10)));
			
			
	        SUBTITLE11 = new JLabel("Temperature (°C)     Max      Average      Min");
	        SUBTITLE11.setFont(new Font("Dialog", Font.ITALIC, 11));
	        SUBTITLE11.setBounds(10,Yto+80 , 375, 13);
            add(SUBTITLE11);
            
	        SUBTITLE12 = new JLabel("Max Temperature:");
	        SUBTITLE12.setFont(new Font("Dialog", Font.ITALIC, 11));
	        SUBTITLE12.setBounds(10,Yto+105 , 375, 13);
            add(SUBTITLE12);
            
	        SUBTITLE13 = new JLabel("Avg Temperature:");
	        SUBTITLE13.setFont(new Font("Dialog", Font.ITALIC, 11));
	        SUBTITLE13.setBounds(10,Yto+133 , 375, 13);
            add(SUBTITLE13);
            
	        SUBTITLE14 = new JLabel("Min Temperature:");
	        SUBTITLE14.setFont(new Font("Dialog", Font.ITALIC, 11));
	        SUBTITLE14.setBounds(10,Yto+160 , 375, 13);
            add(SUBTITLE14);
            
            
            MaximumofMaximumTemperaturearea = new JTextArea();
            MaximumofMaximumTemperaturearea.setText("");
            MaximumofMaximumTemperaturearea.setBounds(110, Yto+105, 25, 20);
            MaximumofMaximumTemperaturearea.setEditable(false);
			add(MaximumofMaximumTemperaturearea);
			Border borderMMaxTA = BorderFactory.createLineBorder(Color.BLACK);
			MaximumofMaximumTemperaturearea.setBorder(BorderFactory.createCompoundBorder(borderMMaxTA,
            BorderFactory.createEmptyBorder(1, 5, 10, 10)));
			
            AverageofMaximumTemperaturearea = new JTextArea();
            AverageofMaximumTemperaturearea.setText("");
            AverageofMaximumTemperaturearea.setBounds(110, Yto+133, 25, 20);
            AverageofMaximumTemperaturearea.setEditable(false);
			add(AverageofMaximumTemperaturearea);
			Border borderAMMaxTA = BorderFactory.createLineBorder(Color.BLACK);
			AverageofMaximumTemperaturearea.setBorder(BorderFactory.createCompoundBorder(borderAMMaxTA,
            BorderFactory.createEmptyBorder(1, 5, 10, 10)));			
			
			MinimumofMaximumTemperaturearea = new JTextArea();
            MinimumofMaximumTemperaturearea.setText("");
            MinimumofMaximumTemperaturearea.setBounds(110, Yto+160, 25, 20);
            MinimumofMaximumTemperaturearea.setEditable(false);
			add( MinimumofMaximumTemperaturearea);
			Border borderMMMinTA = BorderFactory.createLineBorder(Color.BLACK);
			MinimumofMaximumTemperaturearea.setBorder(BorderFactory.createCompoundBorder(borderMMMinTA,
            BorderFactory.createEmptyBorder(1, 5, 10, 10)));
            
            
            
            MaximumTemperaturearea = new JTextArea();
            MaximumTemperaturearea.setText("");
            MaximumTemperaturearea.setBounds(220+Xfrom, Yto+105, 25, 20);
            MaximumTemperaturearea.setEditable(false);
			add(MaximumTemperaturearea);
			Border borderMaxTA = BorderFactory.createLineBorder(Color.BLACK);
			MaximumTemperaturearea.setBorder(BorderFactory.createCompoundBorder(borderMaxTA,
            BorderFactory.createEmptyBorder(1, 5, 10, 10)));
			
            AverageTemperaturearea = new JTextArea();
            AverageTemperaturearea.setText("");
            AverageTemperaturearea.setBounds(220+Xfrom, Yto+133, 25, 20);
            AverageTemperaturearea.setEditable(false);
			add(AverageTemperaturearea);
			Border borderMaxTAb = BorderFactory.createLineBorder(Color.BLACK);
			AverageTemperaturearea.setBorder(BorderFactory.createCompoundBorder(borderMaxTAb,
            BorderFactory.createEmptyBorder(1, 5, 10, 10)));			
			
			
			
            MinimumTemperaturearea = new JTextArea();
            MinimumTemperaturearea.setText("");
            MinimumTemperaturearea.setBounds(220+Xfrom, Yto+160, 25, 20);
            MinimumTemperaturearea.setEditable(false);
			add( MinimumTemperaturearea);
			Border borderMinTA = BorderFactory.createLineBorder(Color.BLACK);
			MinimumTemperaturearea.setBorder(BorderFactory.createCompoundBorder(borderMinTA,
            BorderFactory.createEmptyBorder(1, 5, 10, 10)));
			
			
			
            MaximumofMinimumTemperaturearea = new JTextArea();
            MaximumofMinimumTemperaturearea.setText("");
            MaximumofMinimumTemperaturearea.setBounds(205, Yto+105, 25, 20);
            MaximumofMinimumTemperaturearea.setEditable(false);
			add(MaximumofMinimumTemperaturearea);
			Border borderMMMMaxTA = BorderFactory.createLineBorder(Color.BLACK);
			MaximumofMinimumTemperaturearea.setBorder(BorderFactory.createCompoundBorder(borderMMMMaxTA,
            BorderFactory.createEmptyBorder(1, 5, 10, 10)));
			
            AverageMinimumTemperaturearea = new JTextArea();
            AverageMinimumTemperaturearea.setText("");
            AverageMinimumTemperaturearea.setBounds(205, Yto+133, 25, 20);
            AverageMinimumTemperaturearea.setEditable(false);
			add(AverageMinimumTemperaturearea);
			Border borderMaxTAC = BorderFactory.createLineBorder(Color.BLACK);
			AverageMinimumTemperaturearea.setBorder(BorderFactory.createCompoundBorder(borderMaxTAC,
            BorderFactory.createEmptyBorder(1, 5, 10, 10)));			
			
			
			
            MinimumofMinimumTemperaturearea = new JTextArea();
            MinimumofMinimumTemperaturearea.setText("");
            MinimumofMinimumTemperaturearea.setBounds(205, Yto+160, 25, 20);
            MinimumofMinimumTemperaturearea.setEditable(false);
			add(MinimumofMinimumTemperaturearea);
			Border borderMinTAv = BorderFactory.createLineBorder(Color.BLACK);
			MinimumofMinimumTemperaturearea.setBorder(BorderFactory.createCompoundBorder(borderMinTAv,
            BorderFactory.createEmptyBorder(1, 5, 10, 10)));
			
			
	        SUBTITLE15 = new JLabel(" Sea Level Pressure (Hg)");
	        SUBTITLE15.setFont(new Font("Dialog", Font.ITALIC, 12));
	        SUBTITLE15.setBounds(50,Yto+190 , 375, 15);
            add(SUBTITLE15);
            
	        SUBTITLE16 = new JLabel("       Max                 Average                Min");
	        SUBTITLE16.setFont(new Font("Dialog", Font.ITALIC, 11));
	        SUBTITLE16.setBounds(10,Yto+210 , 375, 13);
            add(SUBTITLE16);
			
			
			
            MaximumPressurearea = new JTextArea();
            MaximumPressurearea.setText("");
            MaximumPressurearea.setBounds(15, Yto+225, 50, 20);
            MaximumPressurearea.setEditable(false);
			add(MaximumPressurearea);
			Border borderMinTAvP = BorderFactory.createLineBorder(Color.BLACK);
			MaximumPressurearea.setBorder(BorderFactory.createCompoundBorder(borderMinTAvP,
            BorderFactory.createEmptyBorder(1, 5, 10, 10)));
			
            averagePressurearea = new JTextArea();
            averagePressurearea.setText("");
            averagePressurearea.setBounds(100, Yto+225, 50, 20);
            averagePressurearea.setEditable(false);
			add(averagePressurearea);
			Border borderMinTAvAP = BorderFactory.createLineBorder(Color.BLACK);
			averagePressurearea.setBorder(BorderFactory.createCompoundBorder(borderMinTAvAP,
            BorderFactory.createEmptyBorder(1, 5, 10, 10)));
			
			
			
            MinimumPressurearea = new JTextArea();
            MinimumPressurearea.setText("");
            MinimumPressurearea.setBounds(175, Yto+225, 50, 20);
            MinimumPressurearea.setEditable(false);
			add(MinimumPressurearea);
			Border borderMinTAvmAP = BorderFactory.createLineBorder(Color.BLACK);
			MinimumPressurearea.setBorder(BorderFactory.createCompoundBorder(borderMinTAvmAP,
            BorderFactory.createEmptyBorder(1, 5, 10, 10)));
			
			
			
			
			
			
			
			
			
			
//            Date = new JTextArea();
//            Date.setText("");
//            Date.setBounds(40, Yto+135, 50, 15);
//            Date.setEditable(false);
//   			add(Date);
//			Border borderdate = BorderFactory.createLineBorder(Color.GRAY);
//			Date.setBorder(BorderFactory.createCompoundBorder(borderdate,
//            BorderFactory.createEmptyBorder(1, 3, 10, 10)));	
			
//			ObteiningTemperature = new JTextArea();
//			ObteiningTemperature.setText("");
//			ObteiningTemperature.setBounds(210+Xfrom, Yto+135, 50, 15);
//			ObteiningTemperature.setEditable(false);
//			add(ObteiningTemperature);
//			Border borderOT = BorderFactory.createLineBorder(Color.GRAY);
//			ObteiningTemperature.setBorder(BorderFactory.createCompoundBorder(borderOT,
 //           BorderFactory.createEmptyBorder(1, 3, 10, 10)));	
			
			
		   
		   
		   calculate = new JButton("Calculate");
		    calculate.setBounds(20, Yto+42, 90, 28);
			   add(calculate);
			   calculate.addActionListener
			    (
			 		      new ActionListener() 
			 		      {
			 		    	 public void actionPerformed(ActionEvent e) 
				 		    	
			 		    	{
			 		    		 
			 		    		 
			 		    		selectedairport = airportname.getText().trim();
			 		    		
			 		    		selectedmonthfrom = (String) monthsboxfrom.getSelectedItem();
			 		    		selecteddayfrom = (String) daysboxfrom.getSelectedItem();
			 		    		selectedyearfrom = (String) yearsboxfrom.getSelectedItem();
			 		    		
			 		    		 selectedmonthto = (String) monthsboxto.getSelectedItem();
			 		    		selecteddayto = (String) daysboxto.getSelectedItem();
			 		    		selectedyearto = (String) yearsboxto.getSelectedItem();
			 		    		

		 		    		
			 		    		selectedtime = (String) timebox.getSelectedItem();
			 		    		
			 		    		 listofmonths.clear();
			 					 listofdays.clear();
			 				     listofyears.clear();
									getarrayofdates();
							
			 		    		
			 		    		InvokeBrowser();
			 		    		

			 		    		gotohistoricalwatherpageandairport();
			 		    		

			 			       
			 		    		
			 			      listoftemperatures.clear();
			 			      listoftMaximumemperatures.clear();
			 			      listoftMinimumemperatures.clear();
			 			      
			 			      listoftMaximumemperatures.clear();
			 			      listoftMinimumemperatures.clear();
			 			      listofPressures.clear();
			 			      createfiles();
			 			       getdate();
			 		    		closefile();
			 				
			 		    		
					//			Date.setText(String.format("%s, %s, %s ",listofmonths.get(m),listofdays.get(m),listofyears.get(m)));
				  //			Date.setEditable(false);
				//				add(Date);
				//				Date.setBackground(Color.CYAN);
				//				Date.setFont(new Font("sherif", Font.BOLD, 8));
			 		    		
 		 						
 			 		// 				     }
 		 			//		             }
					//	                          );
			 		    		
				 	//		       SUBTITLE13 = new JLabel("finished");
				 	//		       SUBTITLE13.setFont(new Font("Dialog", Font.PLAIN, 9));
				 	//		       SUBTITLE13.setBounds(25,Yto+25, 50, 15);
				 	//		       add(SUBTITLE13); 
			 		    		
			 		    		
			 		    		finalresultAverageTemperature.setText(String.format("%.0f",averagetemperature));
			 		    		finalresultAverageTemperature.setEditable(false);
 		 						add(finalresultAverageTemperature);
 		 						finalresultAverageTemperature.setBackground(Color.CYAN);
 		 						finalresultAverageTemperature.setFont(new Font("sherif", Font.BOLD, 13));
 		 						
			 		    		
			 		    		
 		 						MaximumofMaximumTemperaturearea.setText(String.format("%.0f",MaximumTemperaturefromlistoftMaximumemperatures));
 		 						MaximumofMaximumTemperaturearea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
 		 						MaximumofMaximumTemperaturearea.setEditable(false);
 		 						add(MaximumofMaximumTemperaturearea);
 		 						MaximumofMaximumTemperaturearea.setBackground(Color.CYAN);
 		 						MaximumofMaximumTemperaturearea.setFont(new Font("sherif", Font.PLAIN, 11));
 		 						
 		 						AverageofMaximumTemperaturearea.setText(String.format("%.0f",averagetemperatureofMaximum));
 		 						AverageofMaximumTemperaturearea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
 		 						AverageofMaximumTemperaturearea.setEditable(false);
 		 						add(AverageofMaximumTemperaturearea);
 		 						AverageofMaximumTemperaturearea.setBackground(Color.CYAN);
 		 						AverageofMaximumTemperaturearea.setFont(new Font("sherif", Font.PLAIN, 11));
 		 						
 		 						MinimumofMaximumTemperaturearea.setText(String.format("%.0f",MinimumTemperaturefromlistoftMaximumemperatures));
 		 						MinimumofMaximumTemperaturearea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
 		 						MinimumofMaximumTemperaturearea.setEditable(false);
 		 						add(MinimumofMaximumTemperaturearea);
 		 						MinimumofMaximumTemperaturearea.setBackground(Color.CYAN);
 		 						MinimumofMaximumTemperaturearea.setFont(new Font("sherif", Font.PLAIN, 11));

 		 						
 		 						
 		 						
 		 						
 		 						MaximumTemperaturearea.setText(String.format("%.0f",MaximumTemperature));
 		 						MaximumTemperaturearea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			 		    		MaximumTemperaturearea.setEditable(false);
 		 						add(MaximumTemperaturearea);
 		 						MaximumTemperaturearea.setBackground(Color.CYAN);
 		 						MaximumTemperaturearea.setFont(new Font("sherif", Font.PLAIN, 11));
 		 						
 		 						AverageTemperaturearea.setText(String.format("%.0f",averagetemperature));
 		 						AverageTemperaturearea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
 		 						AverageTemperaturearea.setEditable(false);
 		 						add(AverageTemperaturearea);
 		 						AverageTemperaturearea.setBackground(Color.CYAN);
 		 						AverageTemperaturearea.setFont(new Font("sherif", Font.PLAIN, 11)); 

			 		    		
 		 						MinimumTemperaturearea.setText(String.format("%.0f",MinimumTemperature));
 		 						MinimumTemperaturearea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
 		 						MinimumTemperaturearea.setEditable(false);
 		 						add(MinimumTemperaturearea);
 		 						MinimumTemperaturearea.setBackground(Color.CYAN);
 		 						MinimumTemperaturearea.setFont(new Font("sherif", Font.PLAIN,11));
 		 						
 		 						
 		 						
 		 						MaximumofMinimumTemperaturearea.setText(String.format("%.0f",MaximumTemperaturefromlistoftMinimumemperatures));
 		 						MaximumofMinimumTemperaturearea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
 		 						MaximumofMinimumTemperaturearea.setEditable(false);
 		 						add(MaximumofMinimumTemperaturearea);
 		 						MaximumofMinimumTemperaturearea.setBackground(Color.CYAN);
 		 						MaximumofMinimumTemperaturearea.setFont(new Font("sherif", Font.PLAIN,11));

 		 						AverageMinimumTemperaturearea.setText(String.format("%.0f",averagetemperatureofMinimum));
 		 						AverageMinimumTemperaturearea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
 		 						AverageMinimumTemperaturearea.setEditable(false);
 		 						add(AverageMinimumTemperaturearea);
 		 						AverageMinimumTemperaturearea.setBackground(Color.CYAN);
 		 						AverageMinimumTemperaturearea.setFont(new Font("sherif", Font.PLAIN,11));

 		 						MinimumofMinimumTemperaturearea.setText(String.format("%.0f",MinimumTemperaturefromlistoftMinimumemperatures));
 		 						MinimumofMinimumTemperaturearea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
 		 						MinimumofMinimumTemperaturearea.setEditable(false);
 		 						add(MinimumofMinimumTemperaturearea);
 		 						MinimumofMinimumTemperaturearea.setBackground(Color.CYAN);
 		 						MinimumofMinimumTemperaturearea.setFont(new Font("sherif", Font.PLAIN,11));

 		 						
 		 						MaximumPressurearea.setText(String.format("%.2f",MaximumPressure));
 		 						MaximumPressurearea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
 		 						MaximumPressurearea.setEditable(false);
 		 						add(MaximumPressurearea);
 		 						MaximumPressurearea.setBackground(Color.CYAN);
 		 						MaximumPressurearea.setFont(new Font("sherif", Font.PLAIN,11));
 		 						
 		 						averagePressurearea.setText(String.format("%.2f",averagepressure));
 		 						averagePressurearea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
 		 						averagePressurearea.setEditable(false);
 		 						add(averagePressurearea);
 		 						averagePressurearea.setBackground(Color.CYAN);
 		 						averagePressurearea.setFont(new Font("sherif", Font.PLAIN,11));
 		 						
 		 						MinimumPressurearea.setText(String.format("%.2f",MinimumPressure));
 		 						MinimumPressurearea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
 		 						MinimumPressurearea.setEditable(false);
 		 						add(MinimumPressurearea);
 		 						MinimumPressurearea.setBackground(Color.CYAN);
 		 						MinimumPressurearea.setFont(new Font("sherif", Font.PLAIN,11));
 		 						
 		 						
 		 						

 		 						//		Date.setText(String.format("%s, %s, %s ",listofmonths.get(m),listofdays.get(m),listofyears.get(m)));
 		 						//		Date.setEditable(false);
 		 						//		add(Date);
 		 						//		Date.setBackground(Color.CYAN);
 		 						//		Date.setFont(new Font("sherif", Font.BOLD, 8));
 		 						//		Date.revalidate();
 		 						//		Date.repaint();
 		 						
			 		    		 
 		 						
 		 						//ObteiningTemperature.setText(String.format("%.0f",listoftemperatures.get(c)));
 		 						//ObteiningTemperature.setEditable(false);
 		 						//add(ObteiningTemperature);
 		 						//ObteiningTemperature.setBackground(Color.CYAN);
 		 						//ObteiningTemperature.setFont(new Font("sherif", Font.BOLD, 13));
		
 		 						driver.close();
 		 						
 		 						
			 		    	}
				 		     }
				 	);
			   
				  

			
		   
		
		
	}
	
	
	public void getarrayofdates() {
		
		String joined1 = String.join(" ", selectedmonthfrom, selecteddayfrom, selectedyearfrom );
		String string1 = joined1;
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.US);
		LocalDate stardate = LocalDate.parse(string1, formatter1);
		System.out.println(stardate);
		
		String joined2 = String.join(" ", selectedmonthto, selecteddayto, selectedyearto );
		String string2 = joined2;
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.US);
		LocalDate enddate = LocalDate.parse(string2, formatter2);
		System.out.println(enddate);
		
		
		LocalDate start = stardate;
		LocalDate end =  enddate;
		List<LocalDate> totalDates = new ArrayList<>();
		

		end = end.plusDays(1);
		while(start.isBefore(end)){
			

			
			DateTimeFormatter formatters1 = DateTimeFormatter.ofPattern("MMMM", Locale.US);
			String text1 = start.format(formatters1);
			
			DateTimeFormatter formatters2 = DateTimeFormatter.ofPattern("d", Locale.US);
			String text2 = start.format(formatters2);
			
			DateTimeFormatter formatters3 = DateTimeFormatter.ofPattern("yyyy", Locale.US);
			String text3 = start.format(formatters3);
			
			
			listofmonths.add(text1);
			listofdays.add(text2);
			listofyears.add(text3);
			

			
		    start = start.plusDays(1);
		    
		}
		
		System.out.print(listofmonths);
		System.out.print(listofdays);
		System.out.print(listofyears);
	
		
	//	arrayofdate = listofdate.toArray(arrayofdate);
		

	}
	
	
	public void InvokeBrowser() 
	{
		
		try {
			
			  String projectLocation = System.getProperty("user.dir");
			  System.setProperty("webdriver.chrome.driver", projectLocation+"\\libs\\chromedriver.exe");
			
			
			  String username = System.getProperty("user.name");
			  String userProfile = "C:\\Users\\"+username+"\\AppData\\Local\\Google\\Chrome\\User Data\\Default";

			
			  ChromeOptions options = new ChromeOptions();  
			  options.addArguments("--disable-notifications","disable-client-side-phishing-detection","safebrowsing-disable-auto-update", "safebrowsing-disable-download-protection","excludeSwitches", "user-data-dir={}".format(userProfile), "--disable-gpu","--ignore-certificate-errors", "Proxy = null", "--start-maximized","--incognito");
			  options.setCapability("capability_name", "capability_value");
			  // "--headless",

			  HashMap<String, Object> images = new HashMap<String, Object>();
			  images.put("images", 2);
			  HashMap<String, Object> prefs = new HashMap<String, Object>();
			  prefs.put("profile.default_content_setting_values", images);	
			  options.setExperimentalOption("prefs", prefs);
			
			  driver = new ChromeDriver(options);
			
			//driver = new HtmlUnitDriver();
			
		    //driver = new HtmlUnitDriver((BrowserVersion.CHROME), true);
			
			  driver.manage().deleteAllCookies();
			//  driver.manage().window().maximize();
			  driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			  driver.manage().timeouts().pageLoadTimeout(400, TimeUnit.SECONDS);
			
			  boolean success0  = true;
	          while( success0) 
	          {	 
       	 
	        	    try 
	        	    {
			          // driver.get("https://wunderground.com/");
	        	       	 driver.get("https://english.wunderground.com/");
			             success0  = false;
			        } 
	        	    catch (Exception e1) 
	        	    {
		 			  // driver.get("https://wunderground.com/");
		 				 driver.get("https://english.wunderground.com/");
		 				 success0  = true;
		 				 e1.printStackTrace();
		 				 System.out.println("this failed in charging wunderground main page ");
		 			}
		 	  }	

			
			  Thread.sleep(1000);
	
		     } 
		     catch (Exception e) 
		    {
			       e.printStackTrace();
		    }
		
	  }
	
	
	public void gotohistoricalwatherpageandairport() 
	{
		
		 boolean success  = true;
	     while( success) 
	     {	 
       	 
	        	 try 
	        	 {
	//	 WebDriverWait wait = new WebDriverWait(driver, 15);
		// wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\\\"feature-menu\\\"]/ul/li[6]/label")));
	        		 
	           //       driver.findElement(By.xpath("//*[@id=\"wuSettings\"]/i")).click(); 
	    //driver.findElement(By.className("fi-widget")).click(); 
	       //         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wuSettings-quick\"]/div/a[2]")));  
		      //      driver.findElement(By.xpath("//*[@id=\"wuSettings-quick\"]/div/a[2]")).click(); 
		      //      System.out.println("paso aqui? ");
			          try 
			          {
				           Thread.sleep(500);
			          } 
			          catch (InterruptedException e) 
			          {
				           e.printStackTrace();
			          }
			         
		              driver.findElement(By.xpath("//*[@id=\"feature-menu\"]/ul/li[6]/label")).click();
		            //  driver.findElement(By.xpath("//*[@id=\"feature-menu\"]/div[6]/ul/li[9]/a")).click();
		              driver.findElement(By.xpath("//*[@id=\"feature-menu\"]/div[6]/ul/li[6]/a")).click();	
		  
		               success  = false;
			 
		 		   } 
	        	   catch (Exception e1) 
	        	   {
		 				// driver.navigate().refresh();
		 				driver.get("https://www.wunderground.com/");
		 				success  = true;
		 				e1.printStackTrace();
		 				System.out.println("this failed in selected ºC or clicking in WeatherHistory link ");
		 			}
		   }	
		  
	     
	       boolean success1  = true;
	       while( success1) 
	       {	 
       	 
	        	 try 
	        	 {
		 // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\\\"histSearch\\\"]")));
		               driver.findElement(By.xpath("//*[@id=\"histSearch\"]")).sendKeys(selectedairport);
		               driver.findElement(By.xpath("//*[@id=\"trip\"]/input[5]")).click();
			
			           String notairport = driver.getCurrentUrl();
			           System.out.println(notairport);
			
			         //  if ( notairport.equals("https://www.wunderground.com/history/index.html?error=NOTFOUND"))
			           if ( notairport.equals("https://www.wunderground.com/history/index.html?error=NOTFOUND"))   
                       {
				              driver.close();
				       }
			
			           success1  = false;
			      } 
	        	  catch (Exception e1) 
	        	  {
		             // driver.navigate().refresh();
		 			//	driver.get("https://wunderground.com/history/");
	        		    driver.get("https://www.wunderground.com/history/");
		 				success1  = true;
		 				e1.printStackTrace();
		 				System.out.println("this failed in introducing the airport name or making click");
		 		   }
		  }	

		  
		  try 
		  {
				Thread.sleep(1000);
		  } 
		  catch (InterruptedException e) 
		  {
				e.printStackTrace();
		  }
		  
		  
    }
			  		
		
	public  void getdate()   
		{
		   boolean success25  = true;
           while( success25) 
           {   
		
		
		try {
				
		    	 
		    	 String AirportCompleteName = driver.findElement(By.xpath("//*[@id=\"inner-content\"]/div[1]/div/div/city-header/div/h1")).getText();
				 airporttitle(AirportCompleteName);
				 
                 driver.findElement(By.xpath("//*[@id=\"wuSettings\"]/i")).click(); 
                 driver.findElement(By.xpath("//*[@id=\"wuSettings-quick\"]/div/a[2]")).click(); 
                 
		    	
		    	 
		    	 success25  = false;
			} catch (NoSuchElementException e2) {
				// TODO Auto-generated catch block
				//e2.printStackTrace(); 
				driver.navigate().refresh();
				System.out.println ("no such element found"); 
				success25  = true;
				
			}
           }	
		     
		     
		     
		     
		     try 
			  {
					Thread.sleep(1000);
			  } 
			  catch (InterruptedException e) 
			  {
					e.printStackTrace();
			  }
		     
		     
		   for (int m=0;m < listofmonths.size();m++)
			   {
			
			     WebDriverWait wait = new WebDriverWait(driver, 40);
			               String isitempty = null;
		        	       
		        	       boolean success3  = true;
			               while( success3) 
			               {	 
		        	                     try
			        	                 {
				                                   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[1]/div/div[1]/div/div/date-selector/div/select[1]")));
				                              //   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[5]/div[1]/div[1]/div[2]/form/div[1]/select[1]")));
					                            success3  = false;
				                         }catch (Exception e1) 
			        	                 {
				                        	 driver.navigate().refresh();
				                        	 String whichweb = driver.getCurrentUrl();
				      			             System.out.println(whichweb);
				      			       //  if ( notairport.equals("https://www.wunderground.com/history/index.html?error=NOTFOUND"))
				      			           if ( whichweb.equals("https://www.wunderground.com/history/"))   
				                             {
				      			        	   
				      			               driver.findElement(By.xpath("//*[@id=\"histSearch\"]")).sendKeys(selectedairport);
				      			               driver.findElement(By.xpath("//*[@id=\"trip\"]/input[5]")).click();
				      				
				      				           String notairportt = driver.getCurrentUrl();
				      				           System.out.println(notairportt);
				      				
				      				         //  if ( notairport.equals("https://www.wunderground.com/history/index.html?error=NOTFOUND"))
				      				           if ( notairportt.equals("https://www.wunderground.com/history/index.html?error=NOTFOUND"))   
				      	                       {
				      					              driver.close();
				      					       }
				      				         System.out.println("this failed in success 3.1");
				      				           success3  = true;
				      				           e1.printStackTrace();
				      				             
				      				         }
				      			           else 
				      			           {
				                        	    driver.navigate().refresh();
			 				                    success3  = true;
			 				                    e1.printStackTrace();
			 				                    System.out.println("this failed in success 3.2");
				      				       }
			 			                }
			 		        }	
				
				
			         
		        	       boolean success4  = true;
			               while( success4) 
			               {	 
		        	 
			        	               try 
			        	               {    
			                                   WebElement tomonth = driver.findElement(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[1]/div/div[1]/div/div/date-selector/div/select[1]"));
			                             //    WebElement tomonth = driver.findElement(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[5]/div[1]/div[1]/div[2]/form/div[1]/select[1]"));
			        	            	         Select  month = new Select(tomonth);
				                                 month.selectByVisibleText(listofmonths.get(m));
				                                 success4  = false;
					  				   }catch (Exception e1) 
			        	               {
				 				                driver.navigate().refresh();
				 				                success4  = true;
				 				                e1.printStackTrace();
				 				                System.out.println("this failed occurred selecting the month");
				 			           }
				 		   }
				 
			               
			               
			               boolean success455  = true;            
						   boolean success5  = true;
						   while( success5) 
						   {	 
						        	 
							          try 
							          { 				                          
				                                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[1]/div/div[1]/div/div/date-selector/div/select[2]")));	 
				                                WebElement today = driver.findElement(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[1]/div/div[1]/div/div/date-selector/div/select[2]"));
				                          //    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[5]/div[1]/div[1]/div[2]/form/div[1]/select[2]")));	 
				                          //      WebElement today = driver.findElement(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[5]/div[1]/div[1]/div[2]/form/div[1]/select[2]"));
				                                Select  day = new Select(today);
				                                day.selectByVisibleText(listofdays.get(m));	
				                                success5  = false;
				                           
				                      }catch (Exception e1) 
							          {
				                        	    if (listofmonths.get(m).equals("January") && ( listofdays.get(m).equals("30")  || listofdays.get(m).equals("31") )) 
		                                        {
			 
			                                                 
	                                                         while( success455) 
	                                                         {	 
      	                                                               try 
      	                                                               {
	        		                                                        driver.get("https://wunderground.com/history/");
	        		                                                   //   driver.get("https://english.wunderground.com/history/");
		  
		                                                                       driver.findElement(By.xpath("//*[@id=\"histSearch\"]")).sendKeys(selectedairport);
		                                                                 //    driver.findElement(By.xpath("//*[@id=\"histSearch\"]")).sendKeys(selectedairport);
		                                                                    
	                                                                        WebElement tomonth2 = driver.findElement(By.xpath("//*[@id=\"trip\"]/div[3]/select[1]"));
	                                                                 // 	WebElement tomonth2 = driver.findElement(By.xpath("//*[@id=\"trip\"]/div[3]/select[1]"));
		                                                                    Select  month2 = new Select(tomonth2);
		                                                                    month2.selectByVisibleText(listofmonths.get(m));
		
		
		                                                                    WebElement today2 = driver.findElement(By.xpath("//*[@id=\"trip\"]/div[3]/select[2]"));
		                                                               //   WebElement today2 = driver.findElement(By.xpath("//*[@id=\"trip\"]/div[3]/select[2]"));
		                                                                    Select  day2 = new Select(today2);
		                                                                    day2.selectByVisibleText(listofdays.get(m));
		                                                                    
		 
		                                                                   WebElement toyear2 = driver.findElement(By.xpath("//*[@id=\"trip\"]/div[3]/select[3]"));
		                                                            //     WebElement toyear2 = driver.findElement(By.xpath("//*[@id=\"trip\"]/div[3]/select[3]"));
		                                                                    Select  year2 = new Select(toyear2);
		                                                                    year2.selectByVisibleText(listofyears.get(m));	
		
		                                                                    driver.findElement(By.xpath("//*[@id=\"trip\"]/input[5]")).click();
		                                                              //    driver.findElement(By.xpath("//*[@id=\"trip\"]/input[5]")).click();
		
		                                                                    success455  = false;
		                                                                    success5  = false;
			                                                           }
      	                                                               catch (Exception e1l) 
      	                                                               {
		 				                                                    driver.navigate().refresh();
		 				                                                    success455  = true;
		 				                                                    e1.printStackTrace();
		 				                                                    System.out.println("this failed in success 455");
		 			                                                    }
		 		                                            }	
			 
		                                       }
				                        	   else 
				                        	   {
				                        	              driver.navigate().refresh();
						 				                  success5  = true;
						 				                  e1.printStackTrace();
						 				                  System.out.println("this failed occurred selecting the day");
						 			           }
						 		      }	
				                           
						 }
							  
							  
						if(success455  ==  true)
					     {
								  
							 
							  
					    boolean success6  = true;
					    while( success6) 
					    {	 
						        	 
							        	try 
							        	{ 				                           
				                          wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[1]/div/div[1]/div/div/date-selector/div/select[3]")));	 
				                          WebElement toyear = driver.findElement(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[1]/div/div[1]/div/div/date-selector/div/select[3]"));
				                    //    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[5]/div[1]/div[1]/div[2]/form/div[1]/select[3]")));	 
				                    //    WebElement toyear = driver.findElement(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[5]/div[1]/div[1]/div[2]/form/div[1]/select[3]"));
				                           Select  year = new Select(toyear);
				                           year.selectByVisibleText(listofyears.get(m));	
				                           success6  = false;
				                        }
							        	catch (Exception e1) 
							        	{
						 				   driver.navigate().refresh();
						 				   success6  = true;
						 				   e1.printStackTrace();
						 				   System.out.println("this failed occurred selecting the year");
						 			    }
						 }	
				 

				 
				 
					     boolean success7  = true;
					     while( success7) 
					     {
				 
				                        try 
				                        {
				                           wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[1]/div/div[1]/div/div/date-selector/div/input")));	 
				                           driver.findElement(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[1]/div/div[1]/div/div/date-selector/div/input")).click();
				                    //     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[5]/div[1]/div[1]/div[2]/form/div[2]/input")));	 
				                    //     driver.findElement(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[5]/div[1]/div[1]/div[2]/form/div[2]/input")).click();
				                           success7  = false;
				                       }
				                       catch (Exception e1) 
				                       {
				                           driver.navigate().refresh();
				                           success7  = true;
				                           e1.printStackTrace();
				                           System.out.println("this failed occurred making click to find the page for the date previusly selected");
			                          }
		                 }	 
		         
		         
		         
							  }
		         
			 
			            try 
			            {
				            Thread.sleep(1000);
			            }
			            catch (InterruptedException e) 
			            {
			                e.printStackTrace();
			            }
			 
			 
			            List<Double> listoftMaximumemperaturesperday = new ArrayList<>();
			            listoftMaximumemperaturesperday.clear();
			  


			            boolean success9  = true;
			            boolean success8  = true;
	                    while( success8) 
	                    {
			 
			                          try 
			                          {
			                            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"history-observation-table\"]/tbody/tr/td[1]/ng-saw-cell-parser/div/span")));
			                            List<WebElement> firstcolumn = driver.findElements(By.xpath("//*[@id=\"history-observation-table\"]/tbody/tr/td[1]/ng-saw-cell-parser/div/span"));
			                              
			                            //     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"obsTable\"]/tbody/tr/td[1]")));
			                            //  List<WebElement> firstcolumn = driver.findElements(By.xpath("//*[@id=\"obsTable\"]/tbody/tr/td[1]"));


			                              
			                              for (int c=0; c<firstcolumn.size(); c++) 
			                              {
			                            	        
				                                if (firstcolumn.get(c).getText().equals(selectedtime)) 
			                                    {
					                                  int v=c+1;
				                                  //  String secondcolumn = driver.findElement(By.xpath("//*[@id=\"history-observation-table\"]/tbody/tr["+v+"]/td[2]/ng-saw-cell-parser/display-unit/span/span[1]")).getText();
					                                  
					                                  
					                                  String secondcolumn;
											        try 
													{
														secondcolumn = driver.findElement(By.xpath("//*[@id=\"obsTable\"]/tbody/tr["+v+"]/td[2]/span/span[1]")).getText();
													} 
													catch (Exception e) 
											        {
														secondcolumn = driver.findElement(By.xpath("//*[@id=\"obsTable\"]/tbody/tr["+v+"]/td[2]")).getText();
													}
						                                
						                            System.out.println(firstcolumn.get(c).getText());
						                            listoftemperatures.add(secondcolumn);
						                            System.out.println(secondcolumn);
						                            String second = secondcolumn.replaceAll(" ",""); 
						                            if(!second.equals("-"))
						                            {
						                              listoftMaximumemperaturesperday.add(Double.parseDouble(second));
						                              isitempty = second;
						                            }
						                             
				                                  //  String thirdcolumn = driver.findElement(By.xpath("//*[@id=\"history-observation-table\"]/tbody/tr["+v+"]/td[8]/ng-saw-cell-parser/display-unit/span/span[1]")).getText();
						                            int l; 
					                                 String pressurecolumnumber = driver.findElement(By.xpath("//*[@id=\"obsTable\"]/thead/tr/th[6]")).getText();;
					                                 if(pressurecolumnumber.equals("Pressure"))
					                                 {
					                                	 l=6;
					                                 }else
					                                 {
					                                	 l=5;
					                                 } 
				                                      
					                                 String thirdcolumn;
													try 
													{
														thirdcolumn = driver.findElement(By.xpath("//*[@id=\"obsTable\"]/tbody/tr["+v+"]/td["+l+"]/span/span[1]")).getText();
													} catch (Exception e) 
													{
														thirdcolumn = driver.findElement(By.xpath("//*[@id=\"obsTable\"]/tbody/tr["+v+"]/td["+l+"]")).getText();
													} 
													String thirdcolumnn = thirdcolumn.replace("-",""); 
													if(!thirdcolumnn.equals(""))
					                                {
					                                     listofPressures.add(Double.parseDouble(thirdcolumnn));
					                                }
				                                      
				                                      
				                                  // listofPressures.add(Double.parseDouble(thirdcolumn));
				  
				                                    addRecords(m,c, firstcolumn, secondcolumn, thirdcolumn);
				                               }  
			                                   if (selectedtime.equals("ALL")) 
			                                   {
					                                 int v=c+1;
					                             //  String secondcolumn = driver.findElement(By.xpath("//*[@id=\"history-observation-table\"]/tbody/tr["+v+"]/td[2]/ng-saw-cell-parser/display-unit/span/span[1]")).getText();
					                                 
					                                 String secondcolumn;
													try 
													{
														secondcolumn = driver.findElement(By.xpath("//*[@id=\"history-observation-table\"]/tbody/tr["+v+"]/td[2]/ng-saw-cell-parser/display-unit/span/span[1]")).getText();
														// secondcolumn = driver.findElement(By.xpath("//*[@id=\"obsTable\"]/tbody/tr["+v+"]/td[2]/span/span[1]")).getText();
													} 
													 catch (Exception e) {
														 System.out.println("the first failed1");
														secondcolumn = driver.findElement(By.xpath("//*[@id=\"obsTable\"]/tbody/tr["+v+"]/td[2]")).getText();
														
													 }
					                                
					                                 System.out.println(firstcolumn.get(c).getText());
					                               //*[@id="obsTable"]/tbody/tr[2]/td[2]
					                                 listoftemperatures.add(secondcolumn);
					                                 System.out.println(secondcolumn);
					                                 String second = secondcolumn.replaceAll(" ",""); 
					                                // String second = secondcolumn;
					                                 if(!second.equals("-"))
					                                 {
					                                	 System.out.println(second);
					                                	 System.out.println("ENTRO?");
					                                 listoftMaximumemperaturesperday.add(Double.parseDouble(second));
					                                 isitempty = second;
					                                 }
					                                
					                                 System.out.println("SALIDO?");
					                             //  String thirdcolumn = driver.findElement(By.xpath("//*[@id=\"history-observation-table\"]/tbody/tr["+v+"]/td[8]/ng-saw-cell-parser/display-unit/span/span[1]")).getText();
					                                 int l; 
					                                 String pressurecolumnumber = driver.findElement(By.xpath("//*[@id=\"history-observation-table\"]/thead/tr/th[8]/ngsaw-header/span/button")).getText();;
					                                 if(pressurecolumnumber.equals("Pressure"))
					                                 {
					                                	 l=8;
					                                 }else
					                                 {
					                                	 l=7;
					                                 }
					                                 
					                                 
					                                 String thirdcolumn;
													try 
													{
														// thirdcolumn = driver.findElement(By.xpath("//*[@id=\"obsTable\"]/tbody/tr["+v+"]/td["+l+"]/span/span[1]")).getText();
														   thirdcolumn = driver.findElement(By.xpath("//*[@id=\"history-observation-table\"]/tbody/tr["+v+"]/td[8]/ng-saw-cell-parser/display-unit/span/span[1]")).getText();
													} catch (Exception e) 
													{   System.out.println("the first failed2");
														thirdcolumn = driver.findElement(By.xpath("//*[@id=\"obsTable\"]/tbody/tr["+v+"]/td["+l+"]")).getText();
														thirdcolumn = driver.findElement(By.xpath("//*[@id=\"history-observation-table\"]/tbody/tr[+v+]/td[+l+]/ng-saw-cell-parser/display-unit/span/span[1]")).getText();
													} 
													 String thirdcolumnn = thirdcolumn.replace("-",""); 
													 if(!thirdcolumnn.equals(""))
					                                 {
					                                 listofPressures.add(Double.parseDouble(thirdcolumnn));
					                                 }
					  
												//	 listofPressures.add(Double.parseDouble(thirdcolumn));
					                                 addRecords(m,c, firstcolumn, secondcolumn, thirdcolumn);
					                           }
			  
			                             }
				  
				
					                     System.out.printf("Date : %s, %s, %s: ",listofmonths.get(m),listofdays.get(m),listofyears.get(m));
					                     System.out.println(listoftemperatures);
				 
				 
                                         success8  = false;
                                         success9  = true;
		                           } 
			                       catch (Exception e1) 
			                       {
			                    	   String dataavailableornot = null;
			                    	   String dataavailableornott = "notienevalor";
			                    	   boolean thisentry = true;
			                    	   try 
										{
			                    	   
			                    	      dataavailableornot = driver.findElement(By.xpath("//*[@id=\"inner-content\"]/div[2]/div[3]/div/div[1]/div/div/city-history-observation/div/..")).getText();
			                    	      thisentry = true ;
										} catch (Exception e)
			                    	   {     
											try 
											{	
												thisentry = false;	
										  dataavailableornot = driver.findElement(By.xpath("/html/body/font/b/..")).getText();	
										  
											} catch (Exception eee)
					                    	   {  try 
												{	
					                    		   thisentry = false;
					                    		   dataavailableornot = driver.findElement(By.xpath("/html/body/center[1]/h1/..")).getText();	
												 
												} catch (Exception ecc)
					                    	   {
													try 
													{	
														thisentry = false;
					                    	   dataavailableornot = driver.findElement(By.xpath("//*[@id=\"main-message\"]/h1/span/span/..")).getText();
													}catch (Exception eeey)
							                    	   {
														thisentry = false;
														driver.navigate().refresh();
							                    	   }
					                    	   }
					                    	   }
								        }
			                    	   
			                    	   if (thisentry)
			                    	   {
			                    	   System.out.println(dataavailableornot);
			                    	   dataavailableornott = dataavailableornot.substring(dataavailableornot.indexOf(" ")+14);
			                    	  System.out.println(dataavailableornott);
			                    	   }

			                    //	    String dataavailableornot = null;
			                    	  //		try 
			                    	  //		{
			                    	  //			dataavailableornot = driver.findElement(By.xpath("//*[@id=\"inner-content\"]/div[2]/center[1]")).getText();
										//	dataavailableornot = driver.findElement(By.xpath("//*[@id=\"historyTable\"]/tbody/tr[2]/td[2]")).getText();	
			                    	  //				} catch (Exception e) 
			                    	  //			{

			                    	  //				e.printStackTrace();
											//			}
			                    	    
			                    	    System.out.println(dataavailableornot);
			    			
			    			         if ( dataavailableornott.equals("No Data Recorded"))
			                   //    if ( dataavailableornot.equals("No daily or hourly history data available") || dataavailableornot.equals("-") )	
			                    	    {
			    			        	    success8  = false;
			    			        	    System.out.println("No Data Recorded");
			    			        	    success9  = false;
			                    	    }
			    			            else
			    			            {
			                    	    e1.printStackTrace();
			                            driver.navigate().refresh();
			                            success8  = true;
			                            System.out.println("this failed recollecting the data");
			    			            }
		                           }
			          
	                    }
	         
	         
	                    System.out.println(isitempty);
 			           if (isitempty != null) {
 			           System.out.println("entro en null");
 			           if (success9 == true)
 			          { System.out.println("entro en success9");
	                   listoftMaximumemperaturess  = Collections.max(listoftMaximumemperaturesperday);
	                   listoftMaximumemperatures.add(listoftMaximumemperaturess);
	         
                       listoftMinimumemperaturess  = Collections.min(listoftMaximumemperaturesperday);
	                   listoftMinimumemperatures.add(listoftMinimumemperaturess);
 			          } 
 			            }
		       } 

			
			           listoftemperatures.removeAll(Arrays.asList("", null,"-"," "));

			
			           String[] arr = listoftemperatures.toArray(new String[listoftemperatures.size()]);
			
			
	                   double[] parsed = new double[arr.length];
			           for (int e = 0; e<arr.length; e++) 
			           { 
				           parsed[e] = Double.valueOf(arr[e]);
                       }
			           
			           double suma=0;
			           for (int q = 0; q<parsed.length; q++) 
			           { 
				           suma = suma + parsed[q];
				       }
			           
			           System.out.println(" ");
			           averagetemperature=suma/parsed.length;
			           System.out.println(suma);
			           System.out.println(averagetemperature);
			           System.out.println(" ");
			 
			           System.out.println(" ");
			           System.out.println("this is the list of  listoftMaximumemperatures");
			           System.out.println(listoftMaximumemperatures);
			           System.out.println(" ");
			 
			 //   listoftMaximumemperatures.removeAll(Arrays.asList(null));
			 //   listoftMinimumemperatures.removeAll(Arrays.asList("", null));
			    
			          List<String> listoftMaximumemperaturestoString = new ArrayList<String>();
			          for (Double d:listoftMaximumemperatures) 
			          {
			    	      listoftMaximumemperaturestoString.add(d.toString());
			          }
			    
			          listoftMaximumemperaturestoString.removeAll(Arrays.asList("", null, "-"," "));
			           
			    
			          List<String> listoftMinimumemperaturestoString = new ArrayList<String>();
			          for (Double d:listoftMinimumemperatures) 
			          {
			    	      listoftMinimumemperaturestoString.add(d.toString());
			          }
			    
			          listoftMinimumemperaturestoString.removeAll(Arrays.asList("", null,"-"," "));
			    
				
			          MaximumTemperaturefromlistoftMaximumemperatures = Collections.max(listoftMaximumemperatures);
				      MinimumTemperaturefromlistoftMaximumemperatures = Collections.min(listoftMaximumemperatures);
				
				      MaximumTemperaturefromlistoftMinimumemperatures = Collections.max(listoftMinimumemperatures);
				      MinimumTemperaturefromlistoftMinimumemperatures = Collections.min(listoftMinimumemperatures);
				
				
				      String[] arrofMaximumTemperaturefromlistoftMaximumemperatures = listoftMaximumemperaturestoString.toArray(new String[listoftMaximumemperaturestoString.size()]);
				      String[] arrofMinimumTemperaturefromlistoftMinimumemperatures = listoftMinimumemperaturestoString.toArray(new String[listoftMinimumemperaturestoString.size()]);
				
				
				     double[] parsedarrofMaximumTemperaturefromlistoftMaximumemperatures = new double[arrofMaximumTemperaturefromlistoftMaximumemperatures.length];
				     for (int e = 0; e<arrofMaximumTemperaturefromlistoftMaximumemperatures.length; e++) 
				     { 
				     	parsedarrofMaximumTemperaturefromlistoftMaximumemperatures[e] = Double.valueOf(arrofMaximumTemperaturefromlistoftMaximumemperatures[e]);
	                 }
				
		             double[] parsedarrofMinimumTemperaturefromlistoftMinimumemperatures = new double[arrofMinimumTemperaturefromlistoftMinimumemperatures.length];
				     for (int e = 0; e<arrofMinimumTemperaturefromlistoftMinimumemperatures.length; e++) 
				     { 
					    parsedarrofMinimumTemperaturefromlistoftMinimumemperatures[e] = Double.valueOf(arrofMinimumTemperaturefromlistoftMinimumemperatures[e]);
	                 }				
				
							
				     double sumaofmaximum=0;
				     for (int q = 0; q<parsedarrofMaximumTemperaturefromlistoftMaximumemperatures.length; q++) 
				     { 
					       sumaofmaximum = sumaofmaximum + parsedarrofMaximumTemperaturefromlistoftMaximumemperatures[q];
					 }
				
				     double sumaofminimum=0;
				     for (int q = 0; q<parsedarrofMinimumTemperaturefromlistoftMinimumemperatures.length; q++) 
				     { 
					       sumaofminimum = sumaofminimum + parsedarrofMinimumTemperaturefromlistoftMinimumemperatures[q];
					 }				
				
				
				
				     System.out.println(" ");
				     averagetemperatureofMaximum=sumaofmaximum/parsedarrofMaximumTemperaturefromlistoftMaximumemperatures.length;
				     System.out.println(sumaofmaximum);
				     System.out.println(averagetemperatureofMaximum);
				     System.out.println(" ");
				     System.out.printf("Maximum temperaturefromlistoftMaximumemperatures: %s ", MaximumTemperaturefromlistoftMaximumemperatures);
				     System.out.printf("Mainimum temperaturefromlistoftMaximumemperatures: %s ",MinimumTemperaturefromlistoftMaximumemperatures);
				 

			 
				     System.out.println(" ");
				     averagetemperatureofMinimum=sumaofminimum/parsedarrofMinimumTemperaturefromlistoftMinimumemperatures.length;
				     System.out.println(sumaofminimum);
				     System.out.println(averagetemperatureofMinimum);
				     System.out.println(" ");
				     System.out.printf("Maximum temperaturefromlistoftMinimumemperatures: %s ", MaximumTemperaturefromlistoftMinimumemperatures);
				     System.out.printf("Mainimum temperaturefromlistoftMinimumemperatures: %s ",MinimumTemperaturefromlistoftMinimumemperatures);
				 
				 
			         MaximumTemperature = (MaximumTemperaturefromlistoftMaximumemperatures+MaximumTemperaturefromlistoftMinimumemperatures)/2;
				     MinimumTemperature = (MinimumTemperaturefromlistoftMaximumemperatures+MinimumTemperaturefromlistoftMinimumemperatures)/2;
				     System.out.printf("Maximum temperature: %s ", MaximumTemperature);
				     System.out.printf("Mainimum temperature: %s ", MinimumTemperature);
				 
				 
				 
				// listofPressures.removeAll(Arrays.asList("", null));
					 MaximumPressure = Collections.max(listofPressures);
					 MinimumPressure = Collections.min(listofPressures);
					
					
				     List<String> listofPressurestoString = new ArrayList<String>();
				     for (Double d:listofPressures) 
				     {
				    	listofPressurestoString.add(d.toString());
				     }
				    
				    
				    listofPressurestoString.removeAll(Arrays.asList("", null, "-"," "));

					String[] arraylistofPressures = listofPressurestoString.toArray(new String[listofPressurestoString.size()]);

			        double[] parsedarraylistofPressures = new double[arraylistofPressures.length];
					for (int e = 0; e<arraylistofPressures.length; e++) 
					{ 
						parsedarraylistofPressures[e] = Double.valueOf(arraylistofPressures[e]);
		            }
					
					double sumalistofPressures=0;
					for (int q = 0; q<parsedarraylistofPressures.length; q++) 
					{ 
						sumalistofPressures = sumalistofPressures + parsedarraylistofPressures[q];
					}
					
					System.out.println(" ");
					averagepressure=sumalistofPressures/parsedarraylistofPressures.length;
					System.out.println(sumalistofPressures);
					System.out.println(averagepressure);
					System.out.println(" ");

					System.out.printf("MaximumPressure: %s ", MaximumPressure);
					System.out.printf("MinimumPressure: %s ", MinimumPressure);
					
					
			 
		}
			

	public void createfiles() 
	   {
	               try 
	                  {
		                
		                String name =String.format("Weather Data records_%s_(%s,%s,%s-%s,%s,%s).txt",selectedairport, selectedmonthfrom, selecteddayfrom, selectedyearfrom, selectedmonthto, selecteddayto, selectedyearto);
		                file = new Formatter(name);
	                  }
	                catch(Exception e) 
	                     {
		                   JOptionPane.showMessageDialog(null, "Weather Data redords not created or not found");
	                     }
	   }
	   
	
	public  void   addRecords(int m , int c, List<WebElement> firstcolumn, String secondcolumn, String thirdcolumn) 
	  {
	              int j=m;
	              int h=c;
	              List<WebElement> fcl = firstcolumn;
	              String scl = secondcolumn;
	              String  tcl = thirdcolumn;
	
	              file.format("%s%5s%8s%9s%9s    %10s %n", listofmonths.get(j), listofdays.get(j), listofyears.get(j),scl,tcl, fcl.get(h).getText());
	  }
	  	
	  
	public void closefile() 
	   {
                 file.close();
       }
	   
	public void airporttitle(String AirportCompleteName) 
	   {
	               try 
	                  {
	            	   
		                file.format("%15s %n", AirportCompleteName);
		                file.format("%s%5s%8s%9s%9s    %10s %n", "Month","Day","Year","Temp","QNH","Hour");
	                  }
	                catch(Exception e) 
	                     {
		                   JOptionPane.showMessageDialog(null, "Weather Data redords not created or not found");
	                     }	
	   }  
	
}





