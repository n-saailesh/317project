public class GradeCalculator {
    /** 
     * (t1 + t2 + t3) * 20% + exam * 40%, rounded to one decimal
     */
    public static double calculate(double t1, double t2, double t3, double exam) {
        double total = (t1 + t2 + t3) * 0.2 + exam * 0.4;
        return Math.round(total * 10) / 10.0;
    }
}
