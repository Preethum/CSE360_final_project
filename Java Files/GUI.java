// Used for the Graphical User Interface
import javax.swing.*;
public class gui{
    public void Model(){
        JFrame f=new JFrame("CSE360 Final Project");
        JButton b=new JButton("click");
        b.setBounds(130,100,100, 40);
        f.add(b);
        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);
    }
    
}
