//Load a Roster function under File tab

import javax.swing.*; 
import java.util.*;
import java.io.*;

class Student{  //class to hold each students info
    String firstName;
    String lastName;
    String program;
    String level;
    String asuRiteID;
    String id;

    Student(){
        firstName = "";
        lastName = "";
        program = "";
        level = "";
        asuRiteID = "";
        id = "";
    }
}

class Roster {
    //open the file chooser box and return the selected file path
    public static String openFileChooser(){  
        JFileChooser jFile = new JFileChooser();
        jFile.showOpenDialog(null); //show open dialog

        java.io.File file = jFile.getSelectedFile();
        return(file.getPath());
    }

    //parse the selected file and return an array list of students, <path> is a CSV file containing student info
    public static ArrayList<Student> parseFile(String path){
        Scanner scanner = new Scanner(System.in);
        try{    //create scanner object
            scanner = new Scanner(new File(path));
        }
        catch(Exception e){ //handle PathNotFound exception
            System.out.println(e);
            scanner.close();
            return new ArrayList<Student>();
        }
        
        String line = "";
        ArrayList<Student> studentArr = new ArrayList<Student>();

        while(scanner.hasNextLine()){   //add each line of the file to fileLines
            line = scanner.nextLine();
            String[] studentInfo = line.split(","); //create array to store each student info parsed using a comma

            //update student to have the correct info
            Student student = new Student();
            student.id = studentInfo[0];
            student.firstName = studentInfo[1];
            student.lastName = studentInfo[2];
            student.program = studentInfo[3];
            student.level = studentInfo[4];
            student.asuRiteID = studentInfo[5];

            //add student to arrayList
            studentArr.add(student);
        }
        scanner.close();
        return studentArr;
    }
}
