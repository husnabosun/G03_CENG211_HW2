import enums.*;

import java.util.ArrayList;

public class Application {
    // for prefix control and further implementations, applicantID is defined as String
    private String applicantID;
    private String name;
    private double GPA;
    private TranscriptStatus transcriptStatus;
    private ArrayList<Document> documentArrayList;

    private ScholarshipStatus scholarshipStatus;
    private ScholarshipName scholarshipName;
    private ScholarshipType scholarshipType;
    private String scholarshipDuration;
    private RejectionReason rejectionReason;

    public Application(){

    }

    public Application(String applicantID, String name, double GPA,
                       TranscriptStatus transcriptStatus){

        this.applicantID = applicantID;
        this.name = name;
        this.GPA = GPA;
        this.transcriptStatus = transcriptStatus;

        this.documentArrayList = new ArrayList<>();

        this.scholarshipStatus = null;
        this.scholarshipName = null;
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
    public ScholarshipName getScholarshipName(){
        return scholarshipName;
    }
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
    protected void setScholarshipName(ScholarshipName scholarshipName){
        this.scholarshipName = scholarshipName;
    }
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
        documentArrayList.add(document);
    }

    public boolean hasDocument(DocumentTypes type){
        for (Document doc : documentArrayList){
            if(doc.getDocumentType() == type) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyDocuments(){
        return !documentArrayList.isEmpty();
    }


    public boolean runGeneralChecks(){
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

    /*
     public abstract void evaluate();
     */
    public void evaluate(){
        //  bu method overwrite edilmeli for merit need and research grant classes by ege!!!!
    }

    public void printResult(){
        System.out.print("Applicant ID: " + getApplicantID() + ", Name: " + getName() + ", Scholarship: " + getScholarshipName() + ", Status: " + getScholarshipStatus());

        if (scholarshipStatus == ScholarshipStatus.ACCEPTED){
            System.out.println(", Type: " + getScholarshipType() + ", Duration: " + getScholarshipDuration());
        }
        else if(scholarshipStatus == ScholarshipStatus.REJECTED){
            System.out.println(", Reason: " + getRejectionReason().getMessage());
        }
    }


}




