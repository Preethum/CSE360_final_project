//Saves the contents of the JTable to a CSV file

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

class Save extends JFrame {
    public void file_exp(JTable table, String file_loc) {
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
                bw.write("\n");
            }

            //add contents of JTable to save file
            for(int i = 0; i < table.getRowCount(); i++){
                for(int j = 0; j < table.getColumnCount(); j++){
                    bw.write((String) table.getModel().getValueAt(i, j));
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