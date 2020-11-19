//Save function under File tab

import javax.swing.*;

public class Save{
    //opens the file chooser and returns the chosen file's path
    public static String openFileChooser(){
        JFileChooser jFile = new JFileChooser();
        jFile.showOpenDialog(null); //show open dialog

        java.io.File file = jFile.getSelectedFile();
        return(file.getPath());
    }

    //TODO: create a date picker
}
