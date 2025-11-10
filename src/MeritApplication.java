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
        // ege
    }
    @Override
    public ScholarshipName getScholarshipName() {
        return ScholarshipName.MERIT;
    }
}