import enums.DocumentTypes;
import enums.TranscriptStatus;

import java.util.ArrayList;

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
        publications.add(publication);
    }

    @Override
    public boolean verifyDocuments() {
        return hasDocument(DocumentTypes.ENR) &&
                (hasDocument(DocumentTypes.GRP) || !publications.isEmpty());
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
}
