package sample;

public class DB_Diamond {
    private boolean isStealed;
    private String userID;
    private String userName;
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

    public DB_Diamond(String userName,String userID, String number, String shapeAndCut,String minR, String maxR, String height, String carat, String color,
                      String clarity, String cut, String tableSize, String totalDepth, String minGirdle, String maxGirdle, String laserInscription,String date) {
        this.userID = userID;
        this.userName=userName;
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
        return userID;
    }
    public String getUserName(){
        return userName;
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

    public void setStealed(){
        isStealed=true;
    }
    public boolean isStealed(){
        return isStealed;
    }

    public void changeOwner(String userID,String userName){
        this.userName = userName;
        this.userID = userID;
    }
}
