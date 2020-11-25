// Add Attendance function under File tab

import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
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

class Attendance extends JFrame implements ActionListener{
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

    public String datePicker(){
        String date = "";
        JFrame frame = new JFrame("Pick a date");
        
        //string arrays to hold all possible selections
        String[] dayStrings = {"Select a day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}
        String[] monthStrings = {"Select a month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec", };
        
        //create the combo boxes
        JComboBox dayList = new JComboBox(dayStrings);
        JComboBox monthList = new JComboBox(monthStrings);
        JButton setButton = new JButton("Set");
        setButton.addActionListener(this);

        //set default item
        dayList.setSelectedIndex(0);
        dayList.setEditable(true);
        monthList.setSelectedIndex(0);
        monthList.setEditable(true);

        frame.add(dayList, BorderLayout.WEST);
        frame.add(monthList, BorderLayout.EAST);
        frame.add(setButton, BorderLayout.SOUTH);
    }

    //when set button is pressed
    public void actionPerformed(ActionEvent e){
        //set 2 strings to currnetly selected day and month
        //get file
    }
}