// Used for the Graphical User Interface
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;



public class GUI implements ActionListener  {
    JMenu menu1;
    JButton menu2;  
    JMenuItem i1, i2, i3, i4;  
    public void Model() {
        
         
        JFrame f= new JFrame("CSE360 Final Project");  
        JMenuBar mb=new JMenuBar();  
        menu1=new JMenu("File");   

        i1=new JMenuItem("Load a Roster");
        i2=new JMenuItem("Add Attendance");  
        i3=new JMenuItem("Save");  
        i4=new JMenuItem("Plot Data"); 

        
        
        menu2 = new JButton("About");
     
        menu1.add(i1);
        menu1.add(i2); 
        menu1.add(i3);  
        menu1.add(i4); 
        mb.add(menu1);
        mb.add(menu2);  

        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);
        menu2.addActionListener(this);

        
        f.setJMenuBar(mb);  
        f.setSize(400,400);  
        f.setLayout(null);  
        f.setVisible(true);

        

        
        
    }

    public void actionPerformed(ActionEvent e){  
        if(e.getSource()==i1){
            System.out.println("Load a Roster");
            
            Roster ros = new Roster();
            ros.openFileChooser();
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

        else if(e.getSource()==menu2){
            System.out.println("About");
            JFrame frame = new JFrame("About");
            JLabel lblFName = new JLabel("TEAM MEMBERS: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            // JLabel lblFName1 = new JLabel("TEAM MEMBERS:");
            // lblFName1.setHorizontalTextPosition(5);
            
            JPanel panel = new JPanel();
            
            
            panel.add(lblFName);
            // panel.add(lblFName1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 70);
            frame.getContentPane().add(panel);
            frame.setVisible(true);
        }

    }
    
    
   
    
    
}

