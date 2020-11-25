import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

//Saves the contents of the JTable to a CSV file

class Save extends JFrame {
   

    public void file_exp(JTable table, String file_loc) {
        FileWriter fw=null;
        BufferedWriter bw=null;
        try{
            File file = new File(file_loc);

            if(!file.exists()){
                file.createNewFile();
            }
            
                fw = new FileWriter(file.getAbsoluteFile());
                bw = new BufferedWriter(fw);
           
            
            for(int i=0;i<table.getRowCount();i++){
                for(int j=0;j<table.getColumnCount();j++){
                    bw.write((String) table.getModel().getValueAt(i, j));
                    if(j<table.getColumnCount()-1){
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

