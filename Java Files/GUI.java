// Used for the Graphical User Interface
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI implements ActionListener  {
    JFrame frame;
    JMenu fileMenu;
    JButton aboutButton;  
    JMenuItem i1, i2, i3, i4;
    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;

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
        mb.setBackground(Color.red);

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
        frame.setBackground(Color.green);
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
            
        }

        else if(e.getSource()==i3){ //save info 
            System.out.println("Save");
        }

        else if(e.getSource()==i4){ //user wants to plot data
            System.out.println("Plot Data");
        }

        else if(e.getSource()==aboutButton){    //about button handler
            System.out.println("About");
            JFrame aboutFrame = new JFrame("About");
            JLabel lblFName = new JLabel("<html>TEAM MEMBERS:<br> Brent Li, Preet Patel, Martin Ople, Andrew Lauricella, Chase Kimball</html>");
            
            JPanel panel = new JPanel();
            
            panel.add(lblFName);
            aboutFrame.setSize(500, 300);
            aboutFrame.getContentPane().add(panel);
            aboutFrame.setVisible(true);
            aboutFrame.setLocationRelativeTo(null);
        }
    }
}