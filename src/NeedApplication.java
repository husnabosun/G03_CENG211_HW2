import enums.*;

public class NeedApplication extends Application{
    private double familyIncome;
    private int dependents;
    private final int[] thresholds = {10000,15000}; 
    private final double twentyPercentAdjustment = 1.2;
    private final double tenPercentAdjustment = 1.1;

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
        if (!hasGeneralReq()) {
            return;
        } 

        int[] tempThresholds = new int[2];
        int i;
        for (i = 0; i < 2; i++){
            tempThresholds[i] = thresholds[i]; 
        } 

        if (this.hasDocument(DocumentTypes.SAV)){
            adjustThresholds(tempThresholds, twentyPercentAdjustment);
        }

        if (getDependents() >= 3){
            adjustThresholds(tempThresholds, tenPercentAdjustment);
        }

        if (getFamilyIncome() > tempThresholds[1]){
            setRejectionReason(RejectionReason.FINANCIAL_STATUS_UNSTABLE);
            setScholarshipStatus(ScholarshipStatus.REJECTED);
            return;
        }

        setScholarshipStatus(ScholarshipStatus.ACCEPTED);
        
        if (tempThresholds[0] < getFamilyIncome() && getFamilyIncome() <= tempThresholds[1]) setScholarshipType(ScholarshipType.HALF);
        else setScholarshipType(ScholarshipType.FULL);

        setScholarshipDuration("1 year");
    }   

    private void adjustThresholds(int[] thresholds, double adjustingFactor){
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
