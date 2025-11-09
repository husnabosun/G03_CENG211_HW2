import enums.DocumentTypes;

public class Document{
    private DocumentTypes documentType;
    private int durationInMonths;

    public Document(){

    }
    public Document(DocumentTypes documentType, int durationInMonths){
        this.documentType = documentType;
        this.durationInMonths = durationInMonths;
    }

    public DocumentTypes getDocumentType(){
        return documentType;
    }
    public int getDurationInMonths(){
        return durationInMonths;
    }
    public String toString(){
        return ("Type of document: " + documentType + "\nDuration in months: " + durationInMonths);
    }
}
