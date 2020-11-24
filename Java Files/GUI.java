// Used for the Graphical User Interface
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI implements ActionListener  {
    JFrame frame;
    JMenu fileMenu;
    JButton aboutButton;  
    JMenuItem i1, i2, i3, i4;  
    JPanel mainPanel;
    JTable table;
    JScrollPane scrollPane;

    public void Model() {
        //create frame and main panel
        frame = new JFrame("CSE360 Final Project");
        mainPanel = new JPanel(new BorderLayout());
        
        //create menu bar
        JMenuBar mb=new JMenuBar();  
        fileMenu=new JMenu("File");   
        aboutButton = new JButton("About");

        //add items to file menu
        i1=new JMenuItem("Load a Roster");
        i2=new JMenuItem("Add Attendance");  
        i3=new JMenuItem("Save");  
        i4=new JMenuItem("Plot Data"); 

        //add items to menubar
        fileMenu.add(i1);
        fileMenu.add(i2); 
        fileMenu.add(i3);  
        fileMenu.add(i4); 
        mb.add(fileMenu);
        mb.add(aboutButton);
        mb.setBackground(Color.red);

        //create JTable
        table = new JTable();
        table.setBackground(Color.magenta);
        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.blue);

        //add menu bar to menu bar panel
        mainPanel.add(mb);
        mainPanel.add(scrollPane);
        mainPanel.setBackground(Color.yellow);

        //set frame constraints
        frame.add(mb, BorderLayout.NORTH);
        frame.setSize(1000, 600);
        frame.setBackground(Color.green);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){  
        if(e.getSource()==i1){
            System.out.println("Load a Roster");
            String path = Roster.openFileChooser(); //open a file chooser and save the selected file's path to path
            ArrayList<Student> studentArr;   //create array list of students
            studentArr = Roster.parseFile(path);    //fill studentArr using parseFile function in Roster.java
            
            //create JTable
            String col[] = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};    //column headers
            String data[][] = new String[studentArr.size()][6]; //stores data to be put into table
            
            for(int i = 0; i < studentArr.size(); i++){ //fill data[][] with arraylist data
                System.out.print(studentArr.get(i).asuRiteID);
                System.out.print(studentArr.get(i).firstName);
                System.out.print(studentArr.get(i).lastName);
                System.out.print(studentArr.get(i).program);
                System.out.print(studentArr.get(i).level);
                System.out.print(studentArr.get(i).asuRiteID);
                System.out.println("");
                
                data[i][0] = studentArr.get(i).asuRiteID;
                data[i][1] = studentArr.get(i).firstName;
                data[i][2] = studentArr.get(i).lastName;
                data[i][3] = studentArr.get(i).program;
                data[i][4] = studentArr.get(i).level;
                data[i][5] = studentArr.get(i).asuRiteID;
            }
            
            table = new JTable(data, col);   //create Jtable
            scrollPane = new JScrollPane(table);    //create ScrollPane
            //TODO: ADD JTABLE/SCROLLPANE TO WINDOW             
        }

        else if(e.getSource()==i2){ //user wants to add attendance
            System.out.println("Add Attendance");
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