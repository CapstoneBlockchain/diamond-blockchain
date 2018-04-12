package sample;

import java.util.ArrayList;

public class Diamond_DB_ForMidterm {
    private ArrayList<DB_Diamond> diaList;
    private ArrayList<DB_User> userList;

    public Diamond_DB_ForMidterm(){
        diaList = new ArrayList<DB_Diamond>();
        userList = new ArrayList<DB_User>();

        userList.add(new DB_User("Lee MinSoo","0007043889472"));
        userList.add(new DB_User("Kim JunHee","9911252014819"));
        userList.add(new DB_User("Jang GunHee","9606301478963"));

        // 민수꺼
        diaList.add(new DB_Diamond(userList.get(0),"80020124","Round","6.52","6.53",
                "4.00","1.03","E","VVS1","Excellent","64","66.4","Thin","Medium",
                "D231487613820","2018/04/12"));

        // 건희꺼
        diaList.add(new DB_Diamond(userList.get(2),"80020110","Princess","4.35","4.39",
                "2.67","0.31","F","VVS1","Good","58","61.1","Med","S.Thick",
                "D31498713986","2018/04/02"));

        // 준희꺼
        diaList.add(new DB_Diamond(userList.get(1),"80020119","Emerald","4.67","4.69",
                "2.85","0.43","F","VVS2","Good","59","63.2","Med","S.Thick",
                "D23984723948","2018/04/07"));

        // 민수꺼
        diaList.add(new DB_Diamond(userList.get(0),"80020121","Asscher","4.89","4.93",
                "3.03","0.57","D","IF","V.Good","57","62.4","Med","S.Thick",
                "D23479238471","2018/04/08"));
    }

    public boolean checkUser(String userID){
        boolean check=false;

        for(int a=0;a<userList.size();a++){
            if (userList.get(a).getUserID().equals(userID)){
                check=true;
                break;
            }
        }

        return check;
    }

    public boolean checkDia_Deal_Owner(String userID, String diamond){
        boolean check=false;

        for(int a=0;a<diaList.size();a++){
            if(diaList.get(a).getLaserInscription().equals(diamond)){
                if(diaList.get(a).getUserID().equals(userID)){
                    check=true;
                    break;
                }
            }
        }

        return check;
    }

    public boolean checkDia_Lookup(String diamond){
        boolean check=false;

        for(int a=0;a<diaList.size();a++){
            if(diaList.get(a).getLaserInscription().equals(diamond)){
                check=true;
                break;
            }
        }

        return check;
    }

    public DB_Diamond getDia_Lookup(String diamond){
        for(int a=0;a<diaList.size();a++){
            if(diaList.get(a).getLaserInscription().equals(diamond)){
                return diaList.get(a);
            }
        }
        return null;
    }

    public DB_User getUser(String userID){
        for(int a=0;a<userList.size();a++){
            if(userList.get(a).getUserID().equals(userID)){
                return userList.get(a);
            }
        }
        return null;
    }

    public void changeOwner_Deal(String newOwner,String diamond){
        for(int a=0;a<diaList.size();a++){
            if(diaList.get(a).getLaserInscription().equals(diamond)){

                for(int b=0;a<userList.size();b++){
                    if (userList.get(b).getUserID().equals(newOwner)){
                        diaList.get(a).changeOwner(userList.get(b));
                        break;
                    }
                }
                break;
            }
        }
    }

    public void addData(DB_User user, String number, String shapeAndCut,String minR, String maxR, String height, String carat, String color,
                        String clarity, String cut, String tableSize, String totalDepth, String minGirdle, String maxGirdle, String laserInscription,String date){
        diaList.add(new DB_Diamond(user,number,shapeAndCut,minR,maxR,height,carat,color,clarity,cut,tableSize,totalDepth,minGirdle,maxGirdle,laserInscription,date));
    }
}
