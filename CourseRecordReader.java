public class CourseRecordReader extends RecordValidator<CourseRecord> {
    public CourseRecordReader(String filename) {
        super(filename);
    }

    @Override
    protected CourseRecord parseLine(String line) {
        String[] p = line.split(",");
        if (p.length != 6) throw new IllegalArgumentException();
        String id   = p[0].trim();
        String code = p[1].trim();
        double t1   = Double.parseDouble(p[2].trim());
        double t2   = Double.parseDouble(p[3].trim());
        double t3   = Double.parseDouble(p[4].trim());
        double ex   = Double.parseDouble(p[5].trim());
        return new CourseRecord(id, code, t1, t2, t3, ex);
    }
}
