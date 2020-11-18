//Load a Roster function under File tab

import java.io.*; 
import javax.swing.*; 
import java.awt.event.*; 
import javax.swing.filechooser.*; 

class Roster {
    //the main function is used for testing purposes only
    public static void main(String args[]){
        openFileChooser();
    }

    public static void openFileChooser(){
        JFileChooser jFile = new JFileChooser();
        jFile.showOpenDialog(null); //show open dialog
    }
}
