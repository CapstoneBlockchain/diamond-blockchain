package sample;

public class DB_User {
    private String userName;
    private String userID;

    public DB_User(String userName, String userID) {
        this.userID = userID;
        this.userName = userName;
    }

    public String getUserID(){
        return userID;
    }

    public String getUserName(){
        return userName;
    }
}
