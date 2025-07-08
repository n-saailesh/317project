public class FinalRecord {
    private final String studentId;
    private final String studentName;
    private final String courseCode;
    private final double finalGrade;

    public FinalRecord(String studentId, String studentName,
                       String courseCode, double finalGrade) {
        this.studentId  = studentId;
        this.studentName= studentName;
        this.courseCode = courseCode;
        this.finalGrade = finalGrade;
    }

    public String getStudentId()   { return studentId; }
    public String getStudentName() { return studentName; }
    public String getCourseCode()  { return courseCode; }
    public double getFinalGrade()  { return finalGrade; }
}
