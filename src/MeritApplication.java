import enums.*;

public class MeritApplication extends Application {

    public MeritApplication(){
        super();
    }

    public MeritApplication(String applicantID, String name, double GPA,
                            TranscriptStatus transcriptStatus) {
        super(applicantID, name, GPA, transcriptStatus);
    }

    @Override
    public void evaluate(){
        if (hasGeneralReq()) {
            return;
        }
        
        if (getGPA() < 3){
            setScholarshipStatus(ScholarshipStatus.REJECTED);
            setRejectionReason(RejectionReason.GPA_BELOW_3_0);
            return;
        }
        
        setScholarshipStatus(ScholarshipStatus.ACCEPTED);
        
        if (3 <= getGPA() && getGPA() < 3.20) setScholarshipType(ScholarshipType.HALF);
        else setScholarshipType(ScholarshipType.FULL);

        for (Document doc : getDocumentArrayList()){
            if (doc.getDocumentType() == DocumentTypes.REC){
                setScholarshipDuration("2 years");
                return;
            }  
        }

        setScholarshipDuration("1 year");
    }
    @Override
    public ScholarshipName getScholarshipName() {
        return ScholarshipName.MERIT;
    }
}