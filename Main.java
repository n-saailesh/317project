import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    

    public static void main(String[] args) throws FileNotFoundException {
    File file1 = new File("C:\\Users\\MyPC\\OneDrive\\VS Code Projects\\317project//"+"CourseFile.txt");
    File file2 = new File("C:\\Users\\MyPC\\OneDrive\\VS Code Projects\\317project//"+"NameFile.txt");

    Scanner myReader1 = new Scanner(file1);
    Scanner myReader2 = new Scanner(file2);


    // while(myReader.hasNextLine()) {
    //     String i = myReader.nextLine();
    //     System.out.println(i);
    // }

    while(myReader.hasNextLine()) {
        String i = myReader.nextLine();
        System.out.println(i);
    }

    myReader.close();






        
}

}
