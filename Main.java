import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    

    public static void main(String[] args) throws FileNotFoundException {
    File file = new File("C:\\Users\\MyPC\\OneDrive\\VS Code Projects\\317project//"+"CourseFile.txt");
    Scanner myReader = new Scanner(file);


    while(myReader.hasNextLine()) {
        String i = myReader.nextLine();
        System.out.println(i);
    }

    myReader.close();






        
}

}
