import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileRenamerMain{
   public static void main(String[] args) throws IOException{
      try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e){
            e.printStackTrace();
      }
      String selectedFolder = "";
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int result = fileChooser.showOpenDialog(null);
      if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            selectedFolder = selectedFile.getAbsolutePath() + "\\";
        } else {
            System.out.println("File selection canceled.");
            System.exit(0);
        }
      File folder = new File(selectedFolder);
      File[] listOfFiles = folder.listFiles();
      for (int i = 0; i < listOfFiles.length; i++) {
         if (listOfFiles[i].isFile()) {
            File f = new File(selectedFolder+listOfFiles[i].getName());
            String newFile = f.getName();
            while(newFile.startsWith("Copy of ")){
               newFile = newFile.substring(8);
            }
            while(newFile.startsWith("~ ")){
               newFile = newFile.substring(2);
            }
            int c = 1;
            String copy = newFile;
            while(!f.renameTo(new File(selectedFolder+copy))){
               copy = newFile.substring(0, newFile.indexOf('.')) + "(" + c + ")" + newFile.substring(newFile.indexOf('.'));
               c++;
            }
         }
      }
      System.out.println("Done");
   }
}