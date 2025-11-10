import enums.*;

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

    @Override
    public ScholarshipName getScholarshipName() {
        return ScholarshipName.NEED;
    }


    @Override
    public String toString(){
        return ("Applicant ID: " + getApplicantID() +
                "\nName: " + getName() +
                "\nGPA: " + getGPA() +
                "\nTranscript Status: " + getTranscriptStatus() +
                "\nDocument List: " + getDocumentArrayList().toString() +
                "\nFamily Income: " + getFamilyIncome() +
                "\nDependents: " + getDependents());

    }
}
