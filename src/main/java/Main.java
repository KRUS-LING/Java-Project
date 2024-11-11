import csvParser.CsvParser;


public class Main {
    public static void main(String[] args) {
        var file = "rawData\\basicprogramming_2.csv";
        var values = CsvParser.readCSVFile(file);
        var topics = CsvParser.parseToTopic(values);
        var students = CsvParser.parseToStudents(values);

        for(var student : students) {
            System.out.println(student.toString());
        }
        System.out.println(topics.toString());

    }
}

