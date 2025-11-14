import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EvaluationSystem {
    
    private static final String APPLICATIONS_FILE_PATH = "Files/ScholarshipApplications.csv";

    public static void main(String[] args) {
        // Create CSV parser
        CSVParser parser = new CSVParser();
        
        // Parse the CSV file
        ArrayList<Application> applications = parser.parseFile(APPLICATIONS_FILE_PATH);
        
        // Check if applications were loaded successfully
        if (applications == null || applications.isEmpty()) {
            System.out.println("No applications found or error reading file.");
            return;
        }
        
        // Evaluate all applications
        for (Application app : applications) {
            app.evaluate();
        }
        
        // Sort applications by Applicant ID
        Collections.sort(applications, new Comparator<Application>() {
            @Override
            public int compare(Application app1, Application app2) {
                return app1.getApplicantID().compareTo(app2.getApplicantID());
            }
        });
        
        // Print results for all applications
        for (Application app : applications) {
            app.printResult();
            System.out.println();
        }
    }
}
