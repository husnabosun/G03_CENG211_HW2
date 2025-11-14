import enums.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// CSVParser reads and parses the scholarship applications CSV file.
// Creates appropriate Application objects based on applicant ID prefix.
public class CSVParser {
    
    private ArrayList<Application> applicationsList;
    
    public CSVParser() {
        this.applicationsList = new ArrayList<>();
    }
    
    
    // Parses the CSV file and returns a list of Application objects.
    public ArrayList<Application> parseFile(String filePath) {
        // First pass: collect all data by applicant ID
        ArrayList<ApplicantData> dataList = new ArrayList<>();
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                parseLine(line, dataList);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(filePath + " not found.");
            System.out.println("Exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An I/O error occurred during file processing.");
            System.out.println("Exception: " + e.getMessage());
        }
        finally {
            if (reader != null) {
            	try {
            		reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing the reader.");
                    System.out.println("Exception: " + e.getMessage());                }
            }
        }
        
        for (ApplicantData data : dataList) {
            Application app = createApplication(data);
            applicationsList.add(app);
        }
        
        return applicationsList;
    }
    
    // Parses a single line from the CSV file based on its prefix.
    private void parseLine(String line, ArrayList<ApplicantData> dataList) {
        String[] parts = line.split(",");
        
        if (parts.length == 0) {
            return;
        }
        
        String prefix = parts[0].trim();
        
        switch (prefix) {
            case "A":
                parseApplicantInfo(parts, dataList);
                break;
            case "T":
                parseTranscript(parts, dataList);
                break;
            case "I":
                parseFamilyInfo(parts, dataList);
                break;
            case "D":
                parseDocument(parts, dataList);
                break;
            case "P":
                parsePublication(parts, dataList);
                break;
            default:
                break;
        }
    }
    
    // Parses applicant basic information: A, applicantID, name, GPA, income
    private void parseApplicantInfo(String[] parts, ArrayList<ApplicantData> dataList) {
        if (parts.length < 5) {
            return;
        }
        
        String applicantID = parts[1].trim();
        String name = parts[2].trim();
        double gpa = Double.parseDouble(parts[3].trim());
        double income = Double.parseDouble(parts[4].trim());
        
        ApplicantData data = findOrCreateApplicant(applicantID, dataList);
        data.name = name;
        data.gpa = gpa;
        data.income = income;
    }
    
    // Parses transcript status: T, applicantID, transcriptStatus (Y/N)
    private void parseTranscript(String[] parts, ArrayList<ApplicantData> dataList) {
        if (parts.length < 3) {
            return;
        }
        
        String applicantID = parts[1].trim();
        String status = parts[2].trim();
        
        ApplicantData data = findOrCreateApplicant(applicantID, dataList);
        data.transcriptStatus = status.equalsIgnoreCase("Y") ? 
                                TranscriptStatus.Y : TranscriptStatus.N;
    }
    
    // Parses family information: I, applicantID, familyIncome, dependents
    private void parseFamilyInfo(String[] parts, ArrayList<ApplicantData> dataList) {
        if (parts.length < 4) {
            return;
        }
        
        String applicantID = parts[1].trim();
        double familyIncome = Double.parseDouble(parts[2].trim());
        int dependents = Integer.parseInt(parts[3].trim());
        
        ApplicantData data = findOrCreateApplicant(applicantID, dataList);
        data.familyIncome = familyIncome;
        data.dependents = dependents;
    }
    
    // Parses document information: D, applicantID, documentType, durationInMonths
    private void parseDocument(String[] parts, ArrayList<ApplicantData> dataList) {
        if (parts.length < 4) {
            return;
        }
        
        String applicantID = parts[1].trim();
        String documentType = parts[2].trim();
        int duration = Integer.parseInt(parts[3].trim());
        
        ApplicantData data = findOrCreateApplicant(applicantID, dataList);
        
        // Convert string to DocumentTypes enum
        DocumentTypes docType = DocumentTypes.valueOf(documentType);
        data.documents.add(new Document(docType, duration));
    }
    
    // Parses publication information: P, applicantID, title, impactFactor
    private void parsePublication(String[] parts, ArrayList<ApplicantData> dataList) {
        if (parts.length < 4) {
            return;
        }
        
        String applicantID = parts[1].trim();
        String title = parts[2].trim();
        double impactFactor = Double.parseDouble(parts[3].trim());
        
        ApplicantData data = findOrCreateApplicant(applicantID, dataList);
        data.publications.add(new Publication(title, impactFactor));
    }
    
    // Finds an existing ApplicantData or creates a new one.
    private ApplicantData findOrCreateApplicant(String applicantID, ArrayList<ApplicantData> dataList) {
        for (ApplicantData data : dataList) {
            if (data.applicantID.equals(applicantID)) {
                return data;
            }
        }
        
        ApplicantData newData = new ApplicantData();
        newData.applicantID = applicantID;
        dataList.add(newData);
        return newData;
    }
    
     // Creates the appropriate Application object based on applicant ID prefix.
    private Application createApplication(ApplicantData data) {
        String id = data.applicantID;
        Application app;
        
        // Create appropriate application type based on ID prefix
        if (id.startsWith("11")) {
            // Merit-based scholarship
            app = new MeritApplication(
                data.applicantID, 
                data.name, 
                data.gpa, 
                data.transcriptStatus
            );
            
        } else if (id.startsWith("22")) {
            // Need-based scholarship
            app = new NeedApplication(
                data.applicantID, 
                data.name, 
                data.gpa, 
                data.transcriptStatus,
                data.familyIncome,
                data.dependents
            );
            
        } else if (id.startsWith("33")) {
            // Research grant
            app = new ResearchApplication(
                data.applicantID, 
                data.name, 
                data.gpa, 
                data.transcriptStatus
            );
            
            // Add publications to research application
            for (Publication pub : data.publications) {
                ((ResearchApplication) app).addPublication(pub);
            }
            
        } else {
            // Default to Merit if unknown prefix
            app = new MeritApplication(
                data.applicantID, 
                data.name, 
                data.gpa, 
                data.transcriptStatus
            );
        }
        
        // Add documents to all application types
        for (Document doc : data.documents) {
            app.addDocument(doc);
        }
        
        return app;
    }
    
    // Temporary data holder for parsing CSV rows.
    // Used to collect all information about an applicant before creating the Application object.
    private static class ApplicantData {
        String applicantID;
        String name;
        double gpa;
        double income;
        TranscriptStatus transcriptStatus;
        double familyIncome;
        int dependents;
        ArrayList<Document> documents;
        ArrayList<Publication> publications;
        
        ApplicantData() {
            this.transcriptStatus = TranscriptStatus.N;
            this.documents = new ArrayList<>();
            this.publications = new ArrayList<>();
            this.dependents = 0;
            this.familyIncome = 0.0;
            this.income = 0.0;
        }
    }
}
