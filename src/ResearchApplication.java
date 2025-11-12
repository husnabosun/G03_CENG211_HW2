import enums.*;
import java.util.ArrayList;
import java.util.Objects;

public class ResearchApplication extends Application{
    private ArrayList<Publication> publications;

    // constructors
    public ResearchApplication(){
        super();
    }

    public ResearchApplication(String applicantID, String name, double GPA,
                               TranscriptStatus transcriptStatus){
        super(applicantID, name, GPA, transcriptStatus);
        this.publications = new ArrayList<>();
    }

    // getters
    public ArrayList<Publication> getPublications() {
       return new ArrayList<>(publications);
    }

    // methods
    public void addPublication(Publication publication){
        if(publication != null) {
            publications.add(publication);
        }
    }


    public double calculateAverageImpactFactor(){
        if (publications.isEmpty()){
            return 0.0;
        }
        double sum = 0.0;
        for (Publication publication : publications){
            sum += publication.getImpactFactor();
        }
        return sum / publications.size();

    }

    @Override
    public void evaluate(){
        if (!hasGeneralReq()) {
            return;
        }
    
        boolean grpCheck = this.hasDocument(DocumentTypes.GRP);
            
        if (!grpCheck){
            if (publications.isEmpty()){
                setScholarshipStatus(ScholarshipStatus.REJECTED);
                setRejectionReason(RejectionReason.MISSING_PUBLICATION_OR_PROPOSAL);
                return;
            }
        }
        
        double averageImpactFactor = calculateAverageImpactFactor();

        if (averageImpactFactor < 1){
            setScholarshipStatus(ScholarshipStatus.REJECTED);
            setRejectionReason(RejectionReason.PUBLICATION_IMPACT_TOO_LOW);
            return;
        }

        setScholarshipStatus(ScholarshipStatus.ACCEPTED);

        if (1 <= averageImpactFactor && averageImpactFactor < 1.5){
            setScholarshipType(ScholarshipType.HALF);
            setScholarshipDuration(rsvCheck("6 months"));
        }  
        else{
            setScholarshipType(ScholarshipType.FULL);
            setScholarshipDuration(rsvCheck("1 year"));
        }
    }

    private String rsvCheck(String duration){
        if (hasDocument(DocumentTypes.RSV)){
            if (duration == "1 year"){
                return "2 years";
            }

            return "1 years and 6 months";
        }
        
        return duration;
    }

    @Override
    public ScholarshipName getScholarshipName() {
        return ScholarshipName.RESEARCH;
    }

    @Override
    public String toString(){
        return ("Applicant ID: " + getApplicantID() +
                "\nName: " + getName() +
                "\nGPA: " + getGPA() +
                "\nTranscript Status: " + getTranscriptStatus() +
                "\nDocument List: " + getDocumentArrayList().toString() +
                "\nPublications: " + getPublications().toString());

    }
}
