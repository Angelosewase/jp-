package mysql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSV_reader {

    public void readCSV(String filePath) {
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            System.out.println("Headers: " + headerLine);

            while ((line = br.readLine()) != null) {
                String[] student = line.split(csvSplitBy);
                String name = student[0];
                String nationality = student[1];
                String city = student[2];
                double latitude = Double.parseDouble(student[3]);
                double longitude = Double.parseDouble(student[4]);
                String gender = student[5];
                String ethnicGroup = student[6];
                int age = Integer.parseInt(student[7]);
                double englishGrade = Double.parseDouble(student[8]);
                double mathGrade = Double.parseDouble(student[9]);
                double sciencesGrade = Double.parseDouble(student[10]);
                double languageGrade = Double.parseDouble(student[11]);
                double portfolioRating = Double.parseDouble(student[12]);
                double coverLetterRating = Double.parseDouble(student[13]);
                double refLetterRating = Double.parseDouble(student[14]);

                System.out.println("Name: " + name + ", Nationality: " + nationality + ", City: " + city
                        + ", Latitude: " + latitude + ", Longitude: " + longitude + ", Gender: " + gender
                        + ", Ethnic Group: " + ethnicGroup + ", Age: " + age
                        + ", English Grade: " + englishGrade + ", Math Grade: " + mathGrade
                        + ", Sciences Grade: " + sciencesGrade + ", Language Grade: " + languageGrade
                        + ", Portfolio Rating: " + portfolioRating + ", Cover Letter Rating: " + coverLetterRating
                        + ", Reference Letter Rating: " + refLetterRating);

                // Optionally, insert into database
                // new StudentAction().addStudent(name, nationality, city, latitude, longitude, gender,
                //         ethnicGroup, age, englishGrade, mathGrade, sciencesGrade, languageGrade,
                //         portfolioRating, coverLetterRating, refLetterRating);
            }

        } catch (IOException e) {
            System.err.println("Error while reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error while parsing number values: " + e.getMessage());
        }
    }

}
