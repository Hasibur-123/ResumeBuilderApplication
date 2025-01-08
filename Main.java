import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ResumeBuilder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Welcome to the Resume Builder!");

            // Collect personal information
            System.out.print("Enter your full name: ");
            String fullName = scanner.nextLine();

            System.out.print("Enter your email address: ");
            String email = scanner.nextLine();

            System.out.print("Enter your phone number: ");
            String phone = scanner.nextLine();

            // Collect education details
            System.out.print("Enter your highest qualification: ");
            String qualification = scanner.nextLine();

            System.out.print("Enter your university/institution name: ");
            String institution = scanner.nextLine();

            // Collect skills
            System.out.print("Enter your key skills (comma-separated): ");
            String skills = scanner.nextLine();

            // Save details to the database
            saveToDatabase(fullName, email, phone, qualification, institution, skills);

            // Generate resume
            String resumeContent = generateResume(fullName, email, phone, qualification, institution, skills);
            saveResumeToFile(resumeContent);

            System.out.println("\nResume generated successfully! Check the 'resume.txt' file.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        scanner.close();
    }

    // Method to generate the resume content
    public static String generateResume(String fullName, String email, String phone, String qualification, String institution, String skills) {
        return "==========================\n" +
               "          RESUME          \n" +
               "==========================\n" +
               "Name: " + fullName + "\n" +
               "Email: " + email + "\n" +
               "Phone: " + phone + "\n" +
               "--------------------------\n" +
               "Education:\n" +
               qualification + " from " + institution + "\n" +
               "--------------------------\n" +
               "Skills:\n" +
               skills + "\n" +
               "==========================";
    }

    // Method to save the resume to a text file
    public static void saveResumeToFile(String content) throws IOException {
        FileWriter writer = new FileWriter("resume.txt");
        writer.write(content);
        writer.close();
    }

    // Method to save user details to the database
    public static void saveToDatabase(String name, String email, String phone, String qualification, String institution, String skills) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO resumes (name, email, phone, qualification, institution, skills) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, qualification);
            statement.setString(5, institution);
            statement.setString(6, skills);

            statement.executeUpdate();
            System.out.println("Details saved to the database successfully!");
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
