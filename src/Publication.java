public class Publication {
    private String title;
    private double impactFactor;

    public Publication(){
        System.out.println("An empty Publication object is created");
    }
    public Publication(String title, double impactFactor){
        this.title = title;
        this.impactFactor = impactFactor;
    }
    public String getTitle(){
        return title;
    }
    public double getImpactFactor(){
        return impactFactor;
    }
    public String toString(){
        return "Publication => Title: " + title + " Impact Factor: " + impactFactor;
    }

}
