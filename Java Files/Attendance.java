// Add Attendance function under File tab
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
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
/**class Date {
    public String day;
    public String month;
    public String year;

    Date() {
        month = "";
        day = "";
        year = "";
    }
}**/

class Attendance extends JFrame{
    //opens the file chooser and returns the chosen file's path
    public String openFileChooser(){
        JFileChooser jFile = new JFileChooser();
        jFile.showOpenDialog(null); //show open dialog

        java.io.File file = jFile.getSelectedFile();
        return(file.getPath());
    }

    //opens and parses the CSV file to get each student's attendance info
    public ArrayList<StudentAttendance> parseFile(String path){
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

    //opens a date picker and returns a date
    public String datePicker(){
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
                return null;
            }
        });
        this.add(datePicker);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        return "";
    }
}