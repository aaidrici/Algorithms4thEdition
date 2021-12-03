package Chapter_1_3;

import edu.princeton.cs.algs4.StdIn;

import java.io.File;

public class FileTreeStructure {

    File rootFile;
    Queue<String> queueFileName = new Queue<String>();

    public FileTreeStructure(String filePathName){
        rootFile = new File(filePathName);
        populateQueue(rootFile, "");
    }

    public void populateQueue(File file, String indent){

        if(file.isDirectory()){
            //System.out.print("we got here");
            queueFileName.enqueue(indent + file.getName());
            File[] arrayOfFiles = file.listFiles();
            for (File x : arrayOfFiles)
                populateQueue(x, indent + "   ");
        }
        else{
            queueFileName.enqueue(indent + file.getName());
        }
    }

    public void displayTree(){
        for (String x : queueFileName) System.out.println(x);
    }



    public static void main(String[] args){
        String filePath = StdIn.readString();
        FileTreeStructure fileTreeStructure = new FileTreeStructure(filePath);
        fileTreeStructure.displayTree();

    }


}
