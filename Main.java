import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: java Main NameFile.txt CourseFile.txt OutputFile.txt");
            System.exit(1);
        }

        // parse inputs
        NameFileParser    np = new NameFileParser(args[0]);
        CourseFileParser  cp = new CourseFileParser(args[1]);
        np.parse();
        cp.parse();

        // map IDs to names
        Map<String,String> nameMap = new HashMap<>();
        for (Student s : np.getRecords()) {
            nameMap.put(s.getId(), s.getName());
        }

        // compute final grades
        List<FinalRecord> finals = new ArrayList<>();
        for (CourseRecord cr : cp.getRecords()) {
            String name = nameMap.getOrDefault(cr.getStudentId(), "Unknown");
            double grade = GradeCalculator.calculate(
                cr.getT1(), cr.getT2(), cr.getT3(), cr.getExam()
            );
            finals.add(new FinalRecord(
                cr.getStudentId(), name, cr.getCourseCode(), grade
            ));
        }

        // write output
        new OutputWriter().write(finals, args[2]);
        System.out.println("Done. Output in " + args[2]);
    }
}



        



