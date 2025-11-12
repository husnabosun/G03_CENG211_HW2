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
        if (hasGeneralReq()) {
            return;
        }
        
        int[] thresholds = {10000,15000}; 

        if (this.hasDocument(DocumentTypes.SAV)){
            adjustThresholds(thresholds, 6/5);
        }

        if (getDependents() >= 3){
            adjustThresholds(thresholds, 11/10);
        }

        if (getFamilyIncome() > thresholds[1]){
            setRejectionReason(RejectionReason.FINANCIAL_STATUS_UNSTABLE);
            setScholarshipStatus(ScholarshipStatus.REJECTED);
            return;
        }

        setScholarshipStatus(ScholarshipStatus.ACCEPTED);
        
        if (thresholds[0] < getFamilyIncome() && getFamilyIncome() <= thresholds[1]) setScholarshipType(ScholarshipType.HALF);
        else setScholarshipType(ScholarshipType.FULL);

        setScholarshipDuration("1 year");
    }   

    private void adjustThresholds(int[] thresholds, float adjustingFactor){
        thresholds[0] *= adjustingFactor;
        thresholds[1] *= adjustingFactor;
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
