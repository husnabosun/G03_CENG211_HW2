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
        // ege
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
