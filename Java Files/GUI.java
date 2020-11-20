// Used for the Graphical User Interface
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI implements ActionListener  {
    JFrame f;
    JMenu fileMenu;
    JButton aboutButton;  
    JMenuItem i1, i2, i3, i4;  

    public void Model() {
        f = new JFrame("CSE360 Final Project");
        JMenuBar mb=new JMenuBar();  
        fileMenu=new JMenu("File");   
        aboutButton = new JButton("About");

        i1=new JMenuItem("Load a Roster");
        i2=new JMenuItem("Add Attendance");  
        i3=new JMenuItem("Save");  
        i4=new JMenuItem("Plot Data"); 
         
        fileMenu.add(i1);
        fileMenu.add(i2); 
        fileMenu.add(i3);  
        fileMenu.add(i4); 
        mb.add(fileMenu);
        mb.add(aboutButton);  

        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);
        aboutButton.addActionListener(this);

        f.setJMenuBar(mb);  
        f.setSize(1000,600);  
        f.setLayout(null);  
        f.setVisible(true);
        f.setLocationRelativeTo(null);
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
                data[i][0] = studentArr.get(i).asuRiteID;
                data[i][1] = studentArr.get(i).firstName;
                data[i][2] = studentArr.get(i).lastName;
                data[i][3] = studentArr.get(i).program;
                data[i][4] = studentArr.get(i).level;
                data[i][5] = studentArr.get(i).asuRiteID;
            }
            
            JTable table = new JTable(data, col);   //create Jtable
            JScrollPane scrollPane = new JScrollPane(table);    //create ScrollPane
            //TODO: ADD JTABLE/SCROLLPANE TO WINDOW
             
        }

        else if(e.getSource()==i2){
            System.out.println("Add Attendance");
        }

        else if(e.getSource()==i3){
            System.out.println("Save");
        }

        else if(e.getSource()==i4){
            System.out.println("Plot Data");
        }

        else if(e.getSource()==aboutButton){
            System.out.println("About");
            JFrame frame = new JFrame("About");
            JLabel lblFName = new JLabel("TEAM MEMBERS: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            // JLabel lblFName1 = new JLabel("TEAM MEMBERS:");
            // lblFName1.setHorizontalTextPosition(5);
            
            JPanel panel = new JPanel();
            
            panel.add(lblFName);
            // panel.add(lblFName1);
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 70);
            frame.getContentPane().add(panel);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        }
    }
}