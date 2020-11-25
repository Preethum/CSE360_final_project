// Used for the Graphical User Interface
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class GUI implements ActionListener  {
    JFrame frame;
    JMenu fileMenu;
    JButton aboutButton, setButton;  
    JMenuItem i1, i2, i3, i4;
    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;
    JComboBox dayList, monthList;

    public void Model() {
        //create frame and main panel
        frame = new JFrame("CSE360 Final Project");
        
        //create menu bar and about button
        JMenuBar mb=new JMenuBar();  
        fileMenu=new JMenu("File");   
        aboutButton = new JButton("About");
        aboutButton.addActionListener(this);

        //add items to file menu
        i1=new JMenuItem("Load a Roster");
        i2=new JMenuItem("Add Attendance");  
        i3=new JMenuItem("Save");  
        i4=new JMenuItem("Plot Data");
        
        //add action listeners to menu items
        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);

        //add items to menubar
        fileMenu.add(i1);
        fileMenu.add(i2); 
        fileMenu.add(i3);  
        fileMenu.add(i4); 

        mb.add(fileMenu);
        mb.add(aboutButton);

        //create JTable
        String col[] = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};    //column headers
        String data[][] = new String[0][6]; //stores data to be put into table
        model = new DefaultTableModel(data, col);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane = new JScrollPane(table);

        //set frame constraints
        frame.add(mb, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //date picker creation
    public void datePicker(){
        String date = "";
        JFrame frame = new JFrame("Pick a date");
        
        //string arrays to hold all possible selections
        String[] dayStrings = {"Select a day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] monthStrings = {"Select a month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec", };
        
        //create the combo boxes
        dayList = new JComboBox(dayStrings);
        monthList = new JComboBox(monthStrings);
        setButton = new JButton("Set");
        setButton.addActionListener(this);

        //set default item
        dayList.setSelectedIndex(0);
        dayList.setEditable(true);
        monthList.setSelectedIndex(0);
        monthList.setEditable(true);

        frame.add(dayList, BorderLayout.WEST);
        frame.add(monthList, BorderLayout.EAST);
        frame.add(setButton, BorderLayout.SOUTH);
        frame.setSize(255,100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);        
    }
    
    public void actionPerformed(ActionEvent e){  
        if(e.getSource()==i1){
            String path = Roster.openFileChooser(); //open a file chooser and save the selected file's path to path
            ArrayList<Student> studentArr;   //create array list of students
            studentArr = Roster.parseFile(path);    //fill studentArr using parseFile function in Roster.java

            for(int i = 0; i < studentArr.size(); i++){ //add rows to JTable
                model.insertRow(model.getRowCount(), new Object[] {studentArr.get(i).id, studentArr.get(i).firstName, studentArr.get(i).lastName, studentArr.get(i).program, studentArr.get(i).level, studentArr.get(i).asuRiteID});
            }
        }

        else if(e.getSource()==i2){ //user wants to add attendance
            //date handling
            datePicker();
        }

        else if(e.getSource()==i3){ //save info
            String path = Roster.openFileChooser(); //get file path to save to

            //save file
            try{
                file_exp(path);
            }catch(Exception ex){
                System.out.println("done");
            }
        }

        else if(e.getSource()==i4){ //user wants to plot data
            //TODO
        }

        else if(e.getSource()==aboutButton){    //about button handler
            JFrame aboutFrame = new JFrame("About");
            JLabel lblFName = new JLabel("<html>TEAM MEMBERS:<br> Brent Li, Preet Patel, Martin Ople, Andrew Lauricella, Chase Kimball</html>");
            
            JPanel panel = new JPanel();
            
            panel.add(lblFName);
            aboutFrame.setSize(500, 300);
            aboutFrame.getContentPane().add(panel);
            aboutFrame.setVisible(true);
            aboutFrame.setLocationRelativeTo(null);
        }

        else if(e.getSource() == setButton){    //date picker set
            String newDate = monthList.getSelectedItem() + " " + dayList.getSelectedItem(); //create new date header
            model.addColumn(newDate);   //add column to the table

            //file handling
            Attendance attendance = new Attendance();
            String path = attendance.openFileChooser();
            ArrayList<StudentAttendance> studentAttendanceArr = attendance.parseFile(path);

            //adding data to the table
            for(int i = 0; i < table.getRowCount(); i++){   //for each row
                table.getModel().setValueAt(0, i, table.getColumnCount() - 1);  //initialize to 0
                for(int j = 0; j < studentAttendanceArr.size(); j++){   //for each item in the arraylist
                    if(table.getValueAt(i, 5).equals(studentAttendanceArr.get(j).ASURITE)){ //current student in the table is the same student at the current index in the arraylist
                        table.getModel().setValueAt(studentAttendanceArr.get(j).time, i, table.getColumnCount() - 1);   //set new value in the table
                    }
                }
            }
        }
    }

    public void file_exp(String file_loc) {
        FileWriter fw=null;
        BufferedWriter bw=null;
        try{
            File file = new File(file_loc);

            //create new file only if it doesnt already exist
            if(!file.exists()){
                file.createNewFile();
            }

            //create file and and buffered writer
            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            //add column headers to save file
            for(int i = 0; i < table.getColumnCount(); i++){
                bw.write(table.getColumnName(i));
                if(i<table.getColumnCount() - 1){   //if current column is not the last one
                    bw.write(",");
                }
            }
            bw.write("\n");

            //add contents of JTable to save file
            for(int i = 0; i < table.getRowCount(); i++){
                for(int j = 0; j < table.getColumnCount(); j++){
                    bw.write(table.getModel().getValueAt(i, j).toString());
                    if(j<table.getColumnCount() - 1){   //if currnent column is not the last one
                        bw.write(",");
                    }
                }
                bw.write("\n");
            }

        }catch(IOException ex){
            System.out.println("");
        }finally{
            try{
                if(bw!=null){
                    bw.close();
                }
                    
                if(fw!=null){
                    fw.close();
                }   
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        } 
    }
}