// Add Attendance function under File tab
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;v

import javax.swing.*;
import java.util.*;
import java.io.*;

//used to keep track of each student's time logged in
class StudentAttendance{
    public String ASURITE;
    public int time;

    StudentAttendance(){
        ASURITE = "";
        time = 0;
    }
}

//date class to keep track of the date that the user selects
class Date {
    private int day;
    private String month;
    private int year;

    Date() {
        month = "";
        day = 0;
        year = 0;
    }

    Date(String monthNew, int dayNew, int yearNew) {
        month = monthNew;
        day = dayNew;
        year = yearNew;
    }

    // returns an array of the date values for easy access in the format of {month,
    // day, year}
    public Date getDate() {
        return new Date(month, day, year);
    }

    // set the full date
    public void setDate(String monthNew, int dayNew, int yearNew) {
        month = monthNew;
        day = dayNew;
        year = yearNew;
    }

    // set the month
    public void setMonth(String monthNew) {
        month = monthNew;
    }

    // set the day
    public void setDay(int dayNew) {
        day = dayNew;
    }

    // set the year
    public void setYear(int yearNew) {
        year = yearNew;
    }

    // returns the month as a string
    public String getMonth() {
        return month;
    }

    // returns the day as an integer
    public int getDay() {
        return day;
    }

    // returns the year as an integer
    public int getYear() {
        return year;
    }

    // returns the date as a string in the format of MM/DD/YYYY
    public String toString() {
        return month + "/" + day + "/" + year;
    }

}

class Attendance{
    //opens the file chooser and returns the chosen file's path
    public static String openFileChooser(){
        JFileChooser jFile = new JFileChooser();
        jFile.showOpenDialog(null); //show open dialog

        java.io.File file = jFile.getSelectedFile();
        return(file.getPath());
    }

    //opens and parses the CSV file to get each student's attendance info
    public static ArrayList<StudentAttendance> parseFile(String path){
        Scanner scanner = new Scanner(System.in);
        try{    //create scanner object
            scanner = new Scanner(new File(path));
        }
        catch(Exception e){ //handle PathNotFound exception
            System.out.println(e);
            scanner.close();
            return new ArrayList<StudentAttendance>();
        }
        
        String line = "";
        ArrayList<StudentAttendance> studentAttendanceArr = new ArrayList<StudentAttendance>();

        while(scanner.hasNextLine()){   //add each line of the file to fileLines
            line = scanner.nextLine();
            String[] studentAttendanceInfo = line.split(","); //create array to store each student info parsed using a comma

            //update student to have the correct info
            StudentAttendance studentAttendance = new StudentAttendance();
            studentAttendance.ASURITE = studentAttendanceInfo[0];
            studentAttendance.time = Integer.parseInt(studentAttendanceInfo[1]);
            //add student to arrayList
            studentAttendanceArr.add(studentAttendance);
        }
        scanner.close();

        for(int i = 0; i < studentAttendanceArr.size(); i++){   //scan array for duplicate ASURITEs, if found, combine the time
            for(int j = 0; j < studentAttendanceArr.size(); j++){   //scan is completed using nested for loop
                if(studentAttendanceArr.get(i).ASURITE.equals(studentAttendanceArr.get(j).ASURITE)){    //matching ASURITE ids are found, add the times together and remove duplicate
                    studentAttendanceArr.get(i).time += studentAttendanceArr.get(j).time;
                    studentAttendanceArr.remove(j);
                }
            }
        }

        return studentAttendanceArr;
    }
    public Date datePicker(){
        JDatePickerImpl datePicker;
            SqlDateModel model = new SqlDateModel();
            Properties p = new Properties();
            p.put("text.day", "Day");
            p.put("text.month", "Month");
            p.put("text.year", "Year");
            JDatePanelImpl panel = new JDatePanelImpl(model,p);
            
            datePicker = new JDatePickerImpl(panel, new AbstractFormatter() {
                
                @Override
                public String valueToString(Object arg0) throws ParseException {
                    // TODO Auto-generated method stub
                    if(arg0!= null) {
                        Calendar cal = (Calendar) arg0;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
                        String strDate = format.format(cal.getTime());
                        return strDate;
                    }
                    return "";
                }
                @Override
                public Object stringToValue(String arg0) throws ParseException {
                    // TODO Auto-generated method stub
                    return null;
                }
            }
        }
}

