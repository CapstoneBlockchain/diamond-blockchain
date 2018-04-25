package sample;

public class DB_Diamond {
    private boolean isStealed;
    private DB_User user;
    private String number;
    private String shapeAndCut;
    private String minR;
    private String maxR;
    private String height;
    private String carat;
    private String color;
    private String clarity;
    private String cut;
    private String tableSize;
    private String totalDepth;
    private String minGirdle;
    private String maxGirdle;
    private String laserInscription;
    private String date;

    public DB_Diamond(DB_User user, String number, String shapeAndCut,String minR, String maxR, String height, String carat, String color,
                      String clarity, String cut, String tableSize, String totalDepth, String minGirdle, String maxGirdle, String laserInscription,String date) {
        this.user = user;
        this.number = number;
        this.shapeAndCut = shapeAndCut;
        this.minR = minR;
        this.maxR = maxR;
        this.height = height;
        this.carat = carat;
        this.color = color;
        this.clarity = clarity;
        this.cut = cut;
        this.tableSize = tableSize;
        this.totalDepth = totalDepth;
        this.minGirdle = minGirdle;
        this.maxGirdle = maxGirdle;
        this.laserInscription = laserInscription;
        this.date = date;
        this.isStealed=false;
    }

    public void modifyData(String number, String shapeAndCut, String minR, String maxR, String height, String carat, String color, String clarity,
                           String cut, String tableSize, String totalDepth, String minGirdle, String maxGirdle){
        this.number = number;
        this.shapeAndCut = shapeAndCut;
        this.minR = minR;
        this.maxR = maxR;
        this.height = height;
        this.carat = carat;
        this.color = color;
        this.clarity = clarity;
        this.cut = cut;
        this.tableSize = tableSize;
        this.totalDepth = totalDepth;
        this.minGirdle = minGirdle;
        this.maxGirdle = maxGirdle;
    }

    public String getUserID(){
        return user.getUserID();
    }
    public String getUserName(){
        return user.getUserName();
    }
    public String getNumber(){
        return number;
    }
    public String getShapeAndCut(){
        return shapeAndCut;
    }
    public String getMinR(){
        return minR;
    }
    public String getMaxR(){
        return maxR;
    }
    public String getHeight(){
        return height;
    }
    public String getCarat(){
        return carat;
    }
    public String getColor(){
        return color;
    }
    public String getClarity(){
        return clarity;
    }
    public String getCut(){
        return cut;
    }
    public String getTableSize(){
        return tableSize;
    }
    public String getTotalDepth(){
        return totalDepth;
    }
    public String getMinGirdle(){
        return minGirdle;
    }
    public String getMaxGirdle(){
        return maxGirdle;
    }
    public String getLaserInscription(){
        return laserInscription;
    }
    public String getDate(){
        return date;
    }
    public void changeOwner(DB_User newOwner){
        this.user = newOwner;
    }
    public void setStealed(){
        isStealed=true;
    }
    public boolean isStealed(){
        return isStealed;
    }
}
