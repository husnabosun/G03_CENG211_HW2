import enums.TranscriptStatus;

public class NeedApplication extends Application{
    private double familyIncome;
    private int dependents;

    public NeedApplication(){
        super();
    }
    public NeedApplication(String applicantID, String name, double GPA,
                           TranscriptStatus transcriptStatus, double familyIncome, int dependents){
        super(applicantID, name, GPA, transcriptStatus);
        this.familyIncome = familyIncome;
        this.dependents = dependents;
    }

    // getters
    public double getFamilyIncome(){
        return familyIncome;
    }
    public int getDependents(){
        return dependents;
    }

    // methods
    @Override
    public void evaluate(){
        // ege
    }

    public String toString(){
        return ("Applicant ID: " + this.getApplicantID() + "\nName: " + this.getName() + "\nGPA: " +
                this.getGPA() + "\nTranscript Status: " + this.getTranscriptStatus() + "Document List: " + this.getDocumentArrayList() +
                "Family Income: " + familyIncome + "Dependents: " + dependents);

    }
}
