package sample;

import java.util.ArrayList;

public class Diamond_DB_ForMidterm {
    private ArrayList<DB_Diamond> diaList;

    // TODO : 생성자, 다이아에 대한 정보를 임의로 저장해 두었다.
    public Diamond_DB_ForMidterm(){
        diaList = new ArrayList<DB_Diamond>();

        // 민수꺼
        diaList.add(new DB_Diamond("Lee MinSoo","0007043889472","80020124","Round","6.52","6.53",
                "4.00","1.03","E","VVS1","Excellent","64","66.4","Thin","Medium",
                "D231487613820","2018/04/12"));

        // 건희꺼
        diaList.add(new DB_Diamond("Jang GunHee","9606301478963","80020110","Princess","4.35","4.39",
                "2.67","0.31","F","VVS1","Good","58","61.1","Med","S.Thick",
                "D31498713986","2018/04/02"));

        // 준희꺼
        diaList.add(new DB_Diamond("Kim JunHee","9911252014819","80020119","Emerald","4.67","4.69",
                "2.85","0.43","F","VVS2","Good","59","63.2","Med","S.Thick",
                "D23984723948","2018/04/07"));

        // 민수꺼
        diaList.add(new DB_Diamond("Lee MinSoo","0007043889472","80020121","Asscher","4.89","4.93",
                "3.03","0.57","D","IF","V.Good","57","62.4","Med","S.Thick",
                "D23479238471","2018/04/08"));
    }

    // TODO : 다이아 신규 감정에서 사용. 이미 있는 다이아몬드인지 확인하기 위해 사용한다.
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

    // TODO : 다이아 도난에서 사용, 다이아몬드를 입력받아 정보를 받아간다.
    public DB_Diamond getDiamond(String diamond){
        for(int a=0;a<diaList.size();a++){
            if(diaList.get(a).getLaserInscription().equals(diamond)){
                return diaList.get(a);
            }
        }
        return null;
    }

    // TODO : 다이아 정보 신규 등록 시, 해당 메소드를 통해 받아간다.
    public void addData(String userName,String userID, String number, String shapeAndCut,String minR, String maxR, String height, String carat, String color,
                        String clarity, String cut, String tableSize, String totalDepth, String minGirdle, String maxGirdle, String laserInscription,String date){
        diaList.add(new DB_Diamond(userName,userID,number,shapeAndCut,minR,maxR,height,carat,color,clarity,cut,tableSize,totalDepth,minGirdle,maxGirdle,laserInscription,date));
    }


    //TODO : DealScreen_CheckOwner 화면에서 사용하는, 조회에 대한 체크
    public boolean checkBasicOwner(String userID, String diamond){
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

    //TODO : DealScreen 에서 거래가 완료 되었을 때 사용. 소유자 정보를 교환해준다.
    public void changeOwner_Deal(String userID,String userName,String diamond){
        for(int a=0;a<diaList.size();a++){
            if(diaList.get(a).getLaserInscription().equals(diamond)){
                diaList.get(a).changeOwner(userID,userName);
                break;
            }
        }
    }
}
