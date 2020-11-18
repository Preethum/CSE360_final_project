// Used for the Graphical User Interface
import javax.swing.*;
public class GUI{
    public void Model(){
        JMenu menu1, menu2;  
        JMenuItem i1, i2, i3, i4, i5;  
         
        JFrame f= new JFrame("CSE360 Final Project");  
        JMenuBar mb=new JMenuBar();  
        menu1=new JMenu("File");   
        i1=new JMenuItem("Load a Roster");  
        i2=new JMenuItem("Add Attendance");  
        i3=new JMenuItem("Save");  
        i4=new JMenuItem("Plot Data"); 
        
        menu2 = new JMenu("About");
     
        menu1.add(i1); 
        menu1.add(i2); 
        menu1.add(i3);  
        menu1.add(i4); 
        mb.add(menu1);
        mb.add(menu2);  

        f.setJMenuBar(mb);  
        f.setSize(1600,800);  
        f.setLayout(null);  
        f.setVisible(true);  

        
        
    }
    
}
