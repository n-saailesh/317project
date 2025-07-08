import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class OutputWriter {
    public void write(List<FinalRecord> list, String outFile) {
        // sort ascending by student ID
        list.sort(Comparator.comparing(FinalRecord::getStudentId));
        try (PrintWriter pw = new PrintWriter(new FileWriter(outFile))) {
            DecimalFormat df = new DecimalFormat("0.0");
            for (FinalRecord fr : list) {
                pw.printf("%s,%s,%s,%s%n",
                    fr.getStudentId(),
                    fr.getStudentName(),
                    fr.getCourseCode(),
                    df.format(fr.getFinalGrade())
                );
            }
        } catch (IOException e) {
            System.err.println("Cannot write output: " + e.getMessage());
            System.exit(1);
        }
    }
}
