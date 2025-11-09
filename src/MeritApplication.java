import enums.TranscriptStatus;

public class MeritApplication extends Application {

    public MeritApplication(String applicantID, String name, double GPA,
                            TranscriptStatus transcriptStatus) {
        super(applicantID, name, GPA, transcriptStatus);
    }

    @Override
    public void evaluate(){
        // ege
    }
}