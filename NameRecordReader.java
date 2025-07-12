public class NameRecordReader extends RecordValidator<Student> {
    public NameRecordReader(String filename) {
        super(filename);
    }

    @Override
    protected Student parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 2) throw new IllegalArgumentException();
        String id   = parts[0].trim();
        String name = parts[1].trim();
        return new Student(id, name);
    }
}
