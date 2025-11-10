import enums.*;

import java.util.ArrayList;

public abstract class Application {
    // for prefix control and further implementations, applicantID is defined as String
    private final String applicantID;
    private final String name;
    private final double GPA;
    private final TranscriptStatus transcriptStatus;
    private ArrayList<Document> documentArrayList;

    private ScholarshipStatus scholarshipStatus;
    // private ScholarshipName scholarshipName;
    // instead I used abstract getScholarShipName() method for each subclass of Application
    private ScholarshipType scholarshipType;
    private String scholarshipDuration;
    private RejectionReason rejectionReason;

    public Application(){
        this.applicantID = "UNKNOWN"; // Default value
        this.name = "UNKNOWN";
        this.GPA = 0.0;
        this.transcriptStatus = TranscriptStatus.N;
        this.documentArrayList = new ArrayList<>();
        this.scholarshipStatus = null;
        this.scholarshipType = null;
        this.scholarshipDuration = null;
        this.rejectionReason = RejectionReason.NONE;
    }

    public Application(String applicantID, String name, double GPA,
                       TranscriptStatus transcriptStatus){

        this.applicantID = applicantID;
        this.name = name;
        this.GPA = GPA;
        this.transcriptStatus = transcriptStatus;

        this.documentArrayList = new ArrayList<>();

        this.scholarshipStatus = null;
        this.scholarshipType = null;
        this.scholarshipDuration = null;
        this.rejectionReason = RejectionReason.NONE;

    }

    //  getters
    public String getApplicantID(){
        return applicantID;
    }
    public String getName(){
        return name;
    }
    public double getGPA(){
        return GPA;
    }
    public TranscriptStatus getTranscriptStatus(){
        return transcriptStatus;
    }

    public ArrayList<Document> getDocumentArrayList(){
        return new ArrayList<>(documentArrayList);
    }

    public abstract ScholarshipName getScholarshipName();
    /** overriden for each subclass of Application.
     * returns ScholarShipName.MERIT, ScholarShipName.NEED, ScholarShipName.RESEARCH */

    public ScholarshipType getScholarshipType(){
        return scholarshipType;
    }
    public ScholarshipStatus getScholarshipStatus(){
        return scholarshipStatus;
    }
    public String getScholarshipDuration(){
        return scholarshipDuration;
    }
    public RejectionReason getRejectionReason(){
        return rejectionReason;
    }


    // setters
    protected void setScholarshipType(ScholarshipType scholarshipType){
        this.scholarshipType = scholarshipType;
    }
    protected void setScholarshipStatus(ScholarshipStatus scholarshipStatus){
        this.scholarshipStatus = scholarshipStatus;
    }
    protected void setScholarshipDuration(String scholarshipDuration){
        this.scholarshipDuration = scholarshipDuration;
    }
    protected void setRejectionReason(RejectionReason rejectionReason){
        this.rejectionReason = rejectionReason;
    }




    // other methods
    public void addDocument(Document document){
        if(document != null){
            documentArrayList.add(document);
        }
    }

    public boolean hasDocument(DocumentTypes type){
        for (Document doc : documentArrayList){
            if(doc.getDocumentType() == type) {
                return true;
            }
        }
        return false;
    }

    public boolean hasGeneralReq(){
        if(!this.hasDocument(DocumentTypes.ENR)){
            setScholarshipStatus(ScholarshipStatus.REJECTED);
            setRejectionReason(RejectionReason.MISSING_ENROLLMENT_CERTIFICATE);
            return false;
        }
        else if( transcriptStatus != TranscriptStatus.Y){
            setScholarshipStatus(ScholarshipStatus.REJECTED);
            setRejectionReason(RejectionReason.MISSING_TRANSCRIPT);
            return false;
        }
        else if(GPA < 2.5){
            setScholarshipStatus(ScholarshipStatus.REJECTED);
            setRejectionReason(RejectionReason.GPA_BELOW_2_5);
            return false;
        }
        return true;
    }

    //  this method should be written for merit,  need and research grant classes by ege!!!!
     public abstract void evaluate();
    /**
     * Evaluates the scholarship application according to the specific rules
     * of the scholarship type (e.g. Merit Based, Need Based, Research).

     * Example usage:
     * Application app = new MeritApplication("1101", "John", 3.5, TranscriptStatus.Y);
     * app.addDocument(new Document(DocumentTypes.ENR, 0));
     * app.evaluate();
     * app.printResult();
     */

    /*
    public void evaluate(){
    }*/


    public void printResult(){
        System.out.print("Applicant ID: " + getApplicantID() + ", Name: " + getName() + ", Scholarship: " + getScholarshipName() + ", Status: " + getScholarshipStatus());

        if (scholarshipStatus == ScholarshipStatus.ACCEPTED){
            System.out.println(", Type: " + getScholarshipType() + ", Duration: " + getScholarshipDuration());
        }
        else if(scholarshipStatus == ScholarshipStatus.REJECTED){
            System.out.println(", Reason: " + getRejectionReason().getMessage());
        }
    }

    @Override
    public String toString(){
        return ("Applicant ID: " + getApplicantID() +
                "\nName: " + getName() +
                "\nGPA: " + getGPA() +
                "\nTranscript Status: " + getTranscriptStatus() +
                "\nDocument List: " + getDocumentArrayList().toString());

    }


}




