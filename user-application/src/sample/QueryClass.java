package sample;

import java.util.ArrayList;

public class QueryClass {
    HFClient client;

    static void setDiamond(HFClient client, String userName, String userID, String certificateNumber, String shapeAndCut, String minMeasurement, String maxMeasurement,
                           String heightMeasurement, String carat, String color, String clarity, String cut, String totalSize, String totalDepth,
                           String minGirdleThickness, String maxGirdleThickness, String laserScription, String isTheft) {
        Channel channel = client.getChannel("mychannel");

        QueryByChaincodeRequest qpr = client.newQueryProposalRequest();
        TransactionProposalRequest tpr = client.newTransactionProposalRequest();

        ChaincodeID fabcarCCId = ChaincodeID.newBuilder().setName("fabcar").build();
        qpr.setChaincodeID(fabcarCCId);
        tpr.setChaincodeID(fabcarCCId);

        qpr.setFcn("setDiamond");
        tpr.setFcn("setDiamond");

        qpr.setArgs(userName, userID, certificateNumber, shapeAndCut, minMeasurement, maxMeasurement, heightMeasurement
                , carat, color, clarity, cut, totalSize, totalDepth, minGirdleThickness, maxGirdleThickness
                , laserScription, isTheft);
        tpr.setArgs(userName, userID, certificateNumber, shapeAndCut, minMeasurement, maxMeasurement, heightMeasurement
                , carat, color, clarity, cut, totalSize, totalDepth, minGirdleThickness, maxGirdleThickness
                , laserScription, isTheft);

        Collection<ProposalResponse> res = channel.queryByChaincode(qpr);
        channel.sendTransaction(res);

        for (ProposalResponse pres : res) {
            String stringResponse = new String(pres.getChaincodeActionResponsePayload());
            log.info(stringResponse);
        }
    }

    static ArrayList<ArrayList<String>> getDiamond(HFClient client, String laserInscription) {
        Channel channel = client.getChannel("mychannel");

        QueryByChaincodeRequest qpr = client.newQueryProposalRequest();
        TransactionProposalRequest tpr = client.newTransactionProposalRequest();

        ChaincodeID fabcarCCId = ChaincodeID.newBuilder().setName("fabcar").build();
        qpr.setChaincodeID(fabcarCCId);
        tpr.setChaincodeID(fabcarCCId);

        qpr.setFcn("get");
        tpr.setFcn("get");

        qpr.setArgs(laserInscription);
        tpr.setArgs(laserInscription);

        Collection<ProposalResponse> res = channel.queryByChaincode(qpr);
        channel.sendTransaction(res);

        ArrayList<String> returnStrings = new ArayList<String>();
        for (ProposalResponse pres : res) {
            String stringResponse = new String(pres.getChaincodeActionResponsePayload());
            returnStrings.add(stringResponse);
            log.info(stringResponse);
        }

        ArrayList<JsonObject> returnJson = new ArrayList<JsonObject>();
        for (String str : returnStrings) {
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(json);
            returnJson.add(jsonObject);
        }

        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        for (JsonObject j : returnJson) {
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(j.get("createTime"));
            temp.add(j.get("userName"));
            temp.add(j.get("userID"));
            temp.add(j.get("registerNumber"));
            temp.add(j.get("shapeAndCut"));
            temp.add(j.get("minR"));
            temp.add(j.get("maxR"));
            temp.add(j.get("height"));
            temp.add(j.get("carat"));
            temp.add(j.get("color"));
            temp.add(j.get("clarity"));
            temp.add(j.get("cut"));
            temp.add(j.get("tableSize"));
            temp.add(j.get("totalDepth"));
            temp.add(j.get("minT"));
            temp.add(j.get("maxT"));
            temp.add(j.get("laserInscription"));
            temp.add(j.get("checkTheft"));
            result.add(temp);
        }

        return result;
    }

    static void changeOwner(HFClient client, String laserInscription, String userName, String userID) {
        Channel channel = client.getChannel("mychannel");

        QueryByChaincodeRequest qpr = client.newQueryProposalRequest();
        TransactionProposalRequest tpr = client.newTransactionProposalRequest();

        ChaincodeID fabcarCCId = ChaincodeID.newBuilder().setName("fabcar").build();
        qpr.setChaincodeID(fabcarCCId);
        tpr.setChaincodeID(fabcarCCId);

        qpr.setFcn("changeOwner");
        tpr.setFcn("changeOwner");

        qpr.setArgs(laserInscription, userName, userID);
        tpr.setArgs(laserInscription, userName, userID);

        Collection<ProposalResponse> res = channel.queryByChaincode(qpr);
        channel.sendTransaction(res);

        for (ProposalResponse pres : res) {
            String stringResponse = new String(pres.getChaincodeActionResponsePayload());
            log.info(stringResponse);
        }
    }

    static void updateDiamond(HFClient client, String userName, String userID, String certificateNumber
            , String shapeAndCut, String minMeasurement, String maxMeasurement, String heightMeasurement, String carat
            , String color, String clarity, String cut, String totalSize, String totalDepth, String minGirdleThickness
            , String maxGirdleThickness, String laserScription, String isTheft) {
        Channel channel = client.getChannel("mychannel");

        QueryByChaincodeRequest qpr = client.newQueryProposalRequest();
        TransactionProposalRequest tpr = client.newTransactionProposalRequest();

        ChaincodeID fabcarCCId = ChaincodeID.newBuilder().setName("fabcar").build();
        qpr.setChaincodeID(fabcarCCId);
        tpr.setChaincodeID(fabcarCCId);

        qpr.setFcn("updateDiamond");
        tpr.setFcn("updateDiamond");

        qpr.setArgs(userName, userID, certificateNumber, shapeAndCut, minMeasurement, maxMeasurement, heightMeasurement
                , carat, color, clarity, cut, totalSize, totalDepth, minGirdleThickness, maxGirdleThickness
                , laserScription, isTheft);
        tpr.setArgs(userName, userID, certificateNumber, shapeAndCut, minMeasurement, maxMeasurement, heightMeasurement
                , carat, color, clarity, cut, totalSize, totalDepth, minGirdleThickness, maxGirdleThickness
                , laserScription, isTheft);

        Collection<ProposalResponse> res = channel.queryByChaincode(qpr);
        channel.sendTransaction(res);

        for (ProposalResponse pres : res) {
            String stringResponse = new String(pres.getChaincodeActionResponsePayload());
            log.info(stringResponse);
        }
    }

    static void setTheft(HFClient client, String laserInscription, String isTheft) {
        Channel channel = client.getChannel("mychannel");

        QueryByChaincodeRequest qpr = client.newQueryProposalRequest();
        TransactionProposalRequest tpr = client.newTransactionProposalRequest();

        ChaincodeID fabcarCCId = ChaincodeID.newBuilder().setName("fabcar").build();
        qpr.setChaincodeID(fabcarCCId);
        tpr.setChaincodeID(fabcarCCId);

        qpr.setFcn("setTheft");
        tpr.setFcn("setTheft");

        qpr.setArgs(laserInscription, isTheft);
        tpr.setArgs(laserInscription, isTheft);

        Collection<ProposalResponse> res = channel.queryByChaincode(qpr);
        channel.sendTransaction(res);

        for (ProposalResponse pres : res) {
            String stringResponse = new String(pres.getChaincodeActionResponsePayload());
            log.info(stringResponse);
        }
    }

    // TODO : basicOwnerID 와 basicOwnerDiamond 를 받아서, 일치하면(존재하면) true, 불일치하면 false return ##################################
    Boolean checkBasicOwner(String basicOwnerID, String basicOwnerDiamond){
        ArrayList<ArrayList<String>> temp = getDiamond(client,basicOwnerDiamond);

        return temp.get(0).get(2).equals(basicOwnerID); // 이게 어떤 형태일지 모름.
    }

    // TODO : 거래 완료 시, 블록체인 시스템에 저장하기 위해 사용하는 함수. @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    void changeOwner_Deal(String newOwnerID,String newOwnerName,String basicOwnerDiamond){
        changeOwner(client,basicOwnerDiamond,newOwnerName,newOwnerID);
    }

    // TODO : 다이아몬드 일련번호 diamond 의 소유자 이름을 리턴시켜줌. ################################################################
    String getUserName(String diamond){
        ArrayList<ArrayList<String>> temp = getDiamond(client,diamond);
        return temp.get(0).get(1); // 이게 어떤 형태일지 모름.
    }

    // TODO : 다이아몬드 일련번호 diamond 의 생성 시간을 리턴시켜줌. ###################################################################
    String getDate(String diamond){
        ArrayList<ArrayList<String>> temp = getDiamond(client,diamond);
        return temp.get(0).get(0); // 이게 어떤 형태일지 모름.
    }

    // TODO : 다이아몬드 일련번호 diamond 의 도난 상태를 도난으로 변경시켜줌.
    void setStealed(String diamond){
        setTheft(client,diamond,"true");
    }

    // TODO : 다이아몬드 일련번호 diamond 가 존재하면 true 를 , 없으면 false 를 리턴시켜줌. ############################################
    Boolean checkDia_Lookup(String diamond){
        try {
            ArrayList<ArrayList<String>> temp = getDiamond(client,diamond);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    // TODO : 다이아몬드 일련번호 diamond 의 데이터를 리턴시켜줌.####################################################################
    ArrayList<String> getDiamondData(String diamond){
        ArrayList<ArrayList<String>> temp = getDiamond(client,diamond);
        return temp.get(0); // 이게 어떤 형태일지 모름.
    }

    // TODO : 다이아몬드 신규 등록. checkTheft 는 따로 설정해줘야함.@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    void setNewDiamond(String userName,String userID, String number, String shapeAndCut,String minR, String maxR, String height, String carat, String color,
                       String clarity, String cut, String tableSize, String totalDepth, String minGirdle, String maxGirdle, String laserInscription,String date){
        setDiamond(client, userName, userID, number, shapeAndCut, minR, maxR, height, carat, color, clarity, cut, tableSize, totalDepth, minGirdle, maxGirdle,
                laserInscription, "false");
    }

    // TODO : 다이아몬드 업데이트. checkTheft 는 역시 따로 설정해줘야함. @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    void updateDiamond(String userName,String userID,String number, String shapeAndCut, String minR, String maxR, String height, String carat, String color,
                       String clarity, String cut, String tableSize, String totalDepth, String minGirdle, String maxGirdle,String laserInscription){


        updateDiamond(client, userName, userID, number, shapeAndCut, minR, maxR, height, carat, color, clarity, cut, tableSize, totalDepth, minGirdle, maxGirdle,
                laserInscription, "false");
    }
}
