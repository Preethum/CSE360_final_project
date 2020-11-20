// Add Attendance function under File tab

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
class Date{
    public String day;
    public String month;
    public String year;

    Date(){
        day = "";
        month = "";
        year = "";
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
        StudentAttendance studentAttendance = new StudentAttendance();

        while(scanner.hasNextLine()){   //add each line of the file to fileLines
            line = scanner.nextLine();
            String[] studentAttendanceInfo = line.split(","); //create array to store each student info parsed using a comma

            //update student to have the correct info
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
}