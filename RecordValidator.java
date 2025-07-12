import java.io.*;
import java.util.*;

public abstract class RecordValidator<T> {
    protected String filename;
    protected List<T> records = new ArrayList<>();

    public RecordValidator(String filename) {
        this.filename = filename;
    }

    public void parse() {
        File file = new File(filename);
        if (!file.exists() || !file.canRead()) {
            System.err.println("Error: cannot read " + filename);
            System.exit(1);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    T rec = parseLine(line);
                    if (rec != null) records.add(rec);
                } catch (Exception e) {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            System.exit(1);
        }
    }

    protected abstract T parseLine(String line);
    public List<T> getRecords() { return records; }
}
