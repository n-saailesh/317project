public class CourseRecord {
    private final String studentId;
    private final String courseCode;
    private final double t1, t2, t3, exam;

    public CourseRecord(String studentId, String courseCode,
                        double t1, double t2, double t3, double exam) {
        this.studentId  = studentId;
        this.courseCode = courseCode;
        this.t1         = t1;
        this.t2         = t2;
        this.t3         = t3;
        this.exam       = exam;
    }

    public String getStudentId()  { return studentId; }
    public String getCourseCode() { return courseCode; }
    public double getT1()         { return t1; }
    public double getT2()         { return t2; }
    public double getT3()         { return t3; }
    public double getExam()       { return exam; }
}
