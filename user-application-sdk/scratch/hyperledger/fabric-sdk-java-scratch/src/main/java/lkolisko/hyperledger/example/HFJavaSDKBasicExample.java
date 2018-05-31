package lkolisko.hyperledger.example;

import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.*;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;

/**
 * <h1>HFJavaSDKBasicExample</h1>
 * <p>
 * Simple example showcasing basic fabric-ca and fabric actions.
 * The demo required fabcar fabric up and running.
 * <p>
 * The demo shows
 * <ul>
 * <li>connecting to fabric-ca</li>
 * <li>enrolling admin to get new key-pair, certificate</li>
 * <li>registering and enrolling a new user using admin</li>
 * <li>creating HF client and initializing channel</li>
 * <li>invoking chaincode query</li>
 * </ul>
 *
 * @author lkolisko
 */
public class HFJavaSDKBasicExample {
    static String myIP="165.194.104.200";
    HFClient client;

    private static final Logger log = Logger.getLogger(HFJavaSDKBasicExample.class);

    public HFJavaSDKBasicExample(){
        // create fabric-ca client
        HFCAClient caClient = null;
        try {
            caClient = getHfCaClient("http://"+myIP+":7054", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // enroll or load admin
        AppUser admin = null;
        try {
            admin = getAdmin(caClient);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // register and enroll new user
        AppUser appUser = null;
        try {
            appUser = getUser(caClient, admin, "hfuser");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // get HFC client instance
        try {
            client = getHfClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // set user context
        try {
            client.setUserContext(admin);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }

        // get HFC channel using the client
        Channel channel = null;
        try {
            channel = getChannel(client);
        } catch (InvalidArgumentException | TransactionException e) {
            e.printStackTrace();
        }
    }

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

        Collection<ProposalResponse> res = null;
        try {
            res = channel.queryByChaincode(qpr);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (ProposalException e) {
            e.printStackTrace();
        }
        channel.sendTransaction(res);

        for (ProposalResponse pres : res) {
            String stringResponse = null;
            try {
                stringResponse = new String(pres.getChaincodeActionResponsePayload());
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
            }
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

        Collection<ProposalResponse> res = null;
        try {
            res = channel.queryByChaincode(qpr);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (ProposalException e) {
            e.printStackTrace();
        }
        channel.sendTransaction(res);

        ArrayList<String> returnStrings = new ArrayList<String>();
        for (ProposalResponse pres : res) {
            String stringResponse = null;
            try {
                stringResponse = new String(pres.getChaincodeActionResponsePayload());
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
            }
            returnStrings.add(stringResponse);
        }

        ArrayList<JSONObject> returnJson = new ArrayList<JSONObject>();
        for (String str : returnStrings) {
            str = str.substring(11);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = null;
            try {
                jsonObject = (JSONObject) jsonParser.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            returnJson.add(jsonObject);
        }

        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        for (JSONObject j : returnJson) {
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(j.get("createTime").toString());

            JSONObject myTemp = (JSONObject) j.get("UserInfo");
            temp.add(myTemp.get("userName").toString());
            temp.add(myTemp.get("userID").toString());

            temp.add(j.get("registerNumber").toString());
            temp.add(j.get("shapeAndCut").toString());

            myTemp = (JSONObject) j.get("Measurement");
            temp.add(myTemp.get("minR").toString());
            temp.add(myTemp.get("maxR").toString());
            temp.add(myTemp.get("height").toString());

            temp.add(j.get("carat").toString());
            temp.add(j.get("color").toString());
            temp.add(j.get("clarity").toString());
            temp.add(j.get("cut").toString());
            temp.add(j.get("tableSize").toString());
            temp.add(j.get("totalDepth").toString());

            myTemp = (JSONObject) j.get("girdleThickness");
            temp.add(myTemp.get("minT").toString());
            temp.add(myTemp.get("maxT").toString());

            temp.add(j.get("laserInscription").toString());
            temp.add(j.get("checkTheft").toString());
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

        Collection<ProposalResponse> res = null;
        try {
            res = channel.queryByChaincode(qpr);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (ProposalException e) {
            e.printStackTrace();
        }
        channel.sendTransaction(res);

        for (ProposalResponse pres : res) {
            String stringResponse = null;
            try {
                stringResponse = new String(pres.getChaincodeActionResponsePayload());
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
            }
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

        Collection<ProposalResponse> res = null;
        try {
            res = channel.queryByChaincode(qpr);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (ProposalException e) {
            e.printStackTrace();
        }
        channel.sendTransaction(res);

        for (ProposalResponse pres : res) {
            String stringResponse = null;
            try {
                stringResponse = new String(pres.getChaincodeActionResponsePayload());
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
            }
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

        Collection<ProposalResponse> res = null;
        try {
            res = channel.queryByChaincode(qpr);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (ProposalException e) {
            e.printStackTrace();
        }
        channel.sendTransaction(res);

        for (ProposalResponse pres : res) {
            String stringResponse = null;
            try {
                stringResponse = new String(pres.getChaincodeActionResponsePayload());
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO : basicOwnerID 와 basicOwnerDiamond 를 받아서, 일치하면(존재하면) true, 불일치하면 false return
    Boolean checkBasicOwner(String basicOwnerID, String basicOwnerDiamond){
        try {
            ArrayList<ArrayList<String>> temp = getDiamond(client,basicOwnerDiamond);
            return temp.get(0).get(2).equals(basicOwnerID); // 이게 어떤 형태일지 모름.
        }
        catch (Exception e){
            return false;
        }

    }

    // TODO : 해당 다이아몬드의 도난상태를 확인해서 리턴해준다. 도난당한 상태면 true 리턴.
    Boolean checkTheftState(String diamond){
        ArrayList<ArrayList<String>> temp = getDiamond(client,diamond);
        if(temp.get(0).get(17).equals("true")){
            return true;
        }
        return false;
    }

    // TODO : 거래 완료 시, 블록체인 시스템에 저장하기 위해 사용하는 함수.
    void changeOwner_Deal(String newOwnerID,String newOwnerName,String basicOwnerDiamond){
        changeOwner(client,basicOwnerDiamond,newOwnerName,newOwnerID);
    }

    // TODO : 다이아몬드 일련번호 diamond 의 소유자 이름을 리턴시켜줌.
    String getUserName(String diamond){
        ArrayList<ArrayList<String>> temp = getDiamond(client,diamond);
        return temp.get(0).get(1); // 이게 어떤 형태일지 모름.
    }

    // TODO : 다이아몬드 일련번호 diamond 의 생성 시간을 리턴시켜줌.
    String getDate(String diamond){
        ArrayList<ArrayList<String>> temp = getDiamond(client,diamond);
        return temp.get(0).get(0); // 이게 어떤 형태일지 모름.
    }

    // TODO : 다이아몬드 일련번호 diamond 의 도난 상태를 도난으로 변경시켜줌.
    void setStealed(String diamond){
        setTheft(client,diamond,"true");
    }

    // TODO : 다이아몬드 일련번호 diamond 가 존재하면 true 를 , 없으면 false 를 리턴시켜줌.
    Boolean checkDia_Lookup(String diamond){
        try {
            ArrayList<ArrayList<String>> temp = getDiamond(client,diamond);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    // TODO : 다이아몬드 일련번호 diamond 의 데이터를 리턴시켜줌.
    ArrayList<String> getDiamondData(String diamond){
        ArrayList<ArrayList<String>> temp = getDiamond(client,diamond);
        return temp.get(0); // 이게 어떤 형태일지 모름.
    }

    // TODO : 다이아몬드 신규 등록. checkTheft 는 따로 설정해줘야함.
    void setNewDiamond(String userName,String userID, String number, String shapeAndCut,String minR, String maxR, String height, String carat, String color,
                       String clarity, String cut, String tableSize, String totalDepth, String minGirdle, String maxGirdle, String laserInscription,String date){
        setDiamond(client, userName, userID, number, shapeAndCut, minR, maxR, height, carat, color, clarity, cut, tableSize, totalDepth, minGirdle, maxGirdle,
                laserInscription, "false");
    }

    // TODO : 다이아몬드 업데이트. checkTheft 는 역시 따로 설정해줘야함.
    void updateDiamond(String userName,String userID,String number, String shapeAndCut, String minR, String maxR, String height, String carat, String color,
                       String clarity, String cut, String tableSize, String totalDepth, String minGirdle, String maxGirdle,String laserInscription){


        updateDiamond(client, userName, userID, number, shapeAndCut, minR, maxR, height, carat, color, clarity, cut, tableSize, totalDepth, minGirdle, maxGirdle,
                laserInscription, "false");
    }


    /**
     * Initialize and get HF channel
     *
     * @param client The HFC client
     * @return Initialized channel
     * @throws InvalidArgumentException
     * @throws TransactionException
     */
    static Channel getChannel(HFClient client) throws InvalidArgumentException, TransactionException {
        // initialize channel
        // peer name and endpoint in fabcar network
        Peer peer = client.newPeer("peer0.org1.example.com", "grpc://"+myIP+":7051");
        // eventhub name and endpoint in fabcar network
        EventHub eventHub = client.newEventHub("eventhub01", "grpc://"+myIP+":7053");
        // orderer name and endpoint in fabcar network
        Orderer orderer = client.newOrderer("orderer.example.com", "grpc://"+myIP+":7050");
        // channel name in fabcar network
        Channel channel = client.newChannel("mychannel");
        channel.addPeer(peer);
        channel.addEventHub(eventHub);
        channel.addOrderer(orderer);
        channel.initialize();
        return channel;
    }

    /**
     * Create new HLF client
     *
     * @return new HLF client instance. Never null.
     * @throws CryptoException
     * @throws InvalidArgumentException
     */
    static HFClient getHfClient() throws Exception {
        // initialize default cryptosuite
        CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
        // setup the client
        HFClient client = HFClient.createNewInstance();
        client.setCryptoSuite(cryptoSuite);
        return client;
    }


    /**
     * Register and enroll user with userId.
     * If AppUser object with the name already exist on fs it will be loaded and
     * registration and enrollment will be skipped.
     *
     * @param caClient  The fabric-ca client.
     * @param registrar The registrar to be used.
     * @param userId    The user id.
     * @return AppUser instance with userId, affiliation,mspId and enrollment set.
     * @throws Exception
     */
    static AppUser getUser(HFCAClient caClient, AppUser registrar, String userId) throws Exception {
        AppUser appUser = tryDeserialize(userId);
        if (appUser == null) {
            RegistrationRequest rr = new RegistrationRequest(userId, "org1");
            String enrollmentSecret = caClient.register(rr, registrar);
            Enrollment enrollment = caClient.enroll(userId, enrollmentSecret);
            appUser = new AppUser(userId, "org1", "Org1MSP", enrollment);
            serialize(appUser);
        }
        return appUser;
    }

    /**
     * Enroll admin into fabric-ca using {@code admin/adminpw} credentials.
     * If AppUser object already exist serialized on fs it will be loaded and
     * new enrollment will not be executed.
     *
     * @param caClient The fabric-ca client
     * @return AppUser instance with userid, affiliation, mspId and enrollment set
     * @throws Exception
     */
    static AppUser getAdmin(HFCAClient caClient) throws Exception {
        AppUser admin = tryDeserialize("admin");
        if (admin == null) {
            Enrollment adminEnrollment = caClient.enroll("admin", "adminpw");
            admin = new AppUser("admin", "org1", "Org1MSP", adminEnrollment);
            serialize(admin);
        }
        return admin;
    }

    /**
     * Get new fabic-ca client
     *
     * @param caUrl              The fabric-ca-server endpoint url
     * @param caClientProperties The fabri-ca client properties. Can be null.
     * @return new client instance. never null.
     * @throws Exception
     */
    static HFCAClient getHfCaClient(String caUrl, Properties caClientProperties) throws Exception {
        CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
        HFCAClient caClient = HFCAClient.createNewInstance(caUrl, caClientProperties);
        caClient.setCryptoSuite(cryptoSuite);
        return caClient;
    }


    // user serialization and deserialization utility functions
    // files are stored in the base directory

    /**
     * Serialize AppUser object to file
     *
     * @param appUser The object to be serialized
     * @throws IOException
     */
    static void serialize(AppUser appUser) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(
                Paths.get(appUser.getName() + ".jso")))) {
            oos.writeObject(appUser);
        }
    }

    /**
     * Deserialize AppUser object from file
     *
     * @param name The name of the user. Used to build file name ${name}.jso
     * @return
     * @throws Exception
     */
    static AppUser tryDeserialize(String name) throws Exception {
        if (Files.exists(Paths.get(name + ".jso"))) {
            return deserialize(name);
        }
        return null;
    }

    static AppUser deserialize(String name) throws Exception {
        try (ObjectInputStream decoder = new ObjectInputStream(
                Files.newInputStream(Paths.get(name + ".jso")))) {
            return (AppUser) decoder.readObject();
        }
    }
}
