package lkolisko.hyperledger.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    // TODO : 전역변수로 사용하는 변수들
    private int loginInfo;                                                  // 로그인 정보, 0이면 감정원, 1이면 판매소
    private String newOwnerID;                                              // 거래 시 새로운 사용자로 들어갈 ID
    private String newOwnerName;                                            // 거래 시 새로운 사용자로 들어갈 Name
    private String basicOwnerID;                                            // 거래 시 기존 사용자의 ID
    private String basicOwnerDiamond;                                       // 거래 시 기존 사용자가 소유하던 다이아몬드
    private String stealedOwnerID;                                          // 도난당한 다이아의 주인 ID
    private String stealedDiamond;                                          // 도난당한 다이아의 번호
    private String randomDiamond;                                           // ForDemo 에서 쓰일, 랜덤 다이아 번호를 저장한 값
    private HFJavaSDKBasicExample myQuery;

    //TODO : DealScreen_CheckOwner 화면에서 쓰는 변수들
    @FXML
    private TextField textfield_DealScreen_CheckOwner_BasicOwner;       // 기존 소유자 번호 입력 받은 것
    @FXML
    private TextField textfield_DealScreen_CheckOwner_Diamond;          // 다이아 번호 입력 받은 것
    @FXML
    private Button btn_DealScreen_Popup_OK;                                // 조회 성공 시 뜨는 팝업에서의 확인 버튼
    @FXML
    private Button btn_DealScreen_Popup_NOK;                               // 조회 실패 시 뜨는 팝업에서의 확인 버튼

    //TODO : DealScreen_RegisterNewOwner 화면에서 쓰는 변수들
    @FXML
    private TextField textfield_DealScreen_RegisterNewOwner_ID;         // 신규 사용자 등록에서의 사용자 번호
    @FXML
    private TextField textfield_DealScreen_RegisterNewOwner_Name;       // 신규 사용자 등록에서의 사용자 이름

    //TODO : DealScreen_CheckNewOwner 화면에서 쓰는 변수들
    @FXML
    private Text text_DealScreen_CheckNewOwner_ID;                        // 신규 사용자 확인에서의 사용자 번호
    @FXML
    private Text text_DealScreen_CheckNewOwner_Name;                      // 신규 사용자 확인에서의 사용자 이름

    //TODO : DealScreen_Complete 화면에서 쓰는 변수들
    @FXML
    private Text text_DealScreen_Complete_BasicID;                         // 거래 확인에서의 기존 사용자 번호
    @FXML
    private Text text_DealScreen_Complete_NewID;                            // 거래 확인에서의 새로운 사용자 번호
    @FXML
    private Text text_DealScreen_Complete_NewName;                          // 거래 확인에서의 새로운 사용자 이름
    @FXML
    private Text text_DealScreen_Complete_Diamond;                          // 거래 확인에서의 다이아몬드 번호

    //TODO : MainScreen 화면에서 쓰는 변수들
    @FXML
    private Button btn_MainScreen_Deal_Sales;                                // 판매소의 메인화면에서의 거래하기 버튼
    @FXML
    private Button btn_MainScreen_Deal_Appraise;                            // 감정원의 메인화면에서의 거래하기 버튼
    @FXML
    private Button btn_Login_Appraise;

    //TODO : StealedScreen_CheckOwner 화면에서 쓰는 변수들
    @FXML
    private TextField textfield_StealedScreen_CheckOwner_BasicOwner;     // 도난신고 화면에서의 기존 소유자 번호
    @FXML
    private TextField textfield_StealedScreen_CheckOwner_Diamond;         // 도난신고 화면에서의 다이아 번호

    // TODO : StealedScreen_Complete 화면에서 쓰는 변수들
    @FXML
    private Text text_StealedScreen_Complete_OwnerID;
    @FXML
    private Text text_StealedScreen_Complete_Diamond;
    @FXML
    private Text text_StealedScreen_Complete_OwnerName;
    @FXML
    private Text text_StealedScreen_Complete_Date;
    @FXML
    private Button btn_StealedScreen_Popup_OK;




    // TODO : 최초 실행 시 Login 화면에서 작동하는 Action들
    @FXML
    public void login_Appraise(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Appraise.fxml"));
            Parent parent = (Parent) loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(0);

            double temp = (Math.random()*1000000);
            int num = (int)temp;
            double temp2 = (Math.random()*100000);
            int num2 = (int)temp2;

            HFJavaSDKBasicExample myQuery = new HFJavaSDKBasicExample();
            controller.setMyQuery(myQuery,"D"+String.valueOf(num)+String.valueOf(num2));

            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) btn_Login_Appraise.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 메인화면");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void login_Sales(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Sales.fxml"));
            Parent parent = (Parent) loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(1);

            double temp = (Math.random()*1000000);
            int num = (int)temp;
            double temp2 = (Math.random()*100000);
            int num2 = (int)temp2;

            HFJavaSDKBasicExample myQuery = new HFJavaSDKBasicExample();
            controller.setMyQuery(myQuery,"D"+String.valueOf(num)+String.valueOf(num2));

            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) btn_Login_Appraise.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<판매소> 메인화면");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // TODO : 컨트롤러 initialize 및, putExtra를 위한 함수들
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newOwnerID = "";
        newOwnerName = "";
        basicOwnerDiamond = "";
        basicOwnerID = "";
    }

    public void setLoginInfo(int loginInfo) {
        this.loginInfo = loginInfo;
    }

    public void setMyQuery(HFJavaSDKBasicExample myQuery,String randomDiamond) {
        this.myQuery = myQuery;
        this.randomDiamond = randomDiamond;
    }

    public void setOwner(String basicID, String diamond, String newID, String newName) {
        this.newOwnerID = newID;
        this.newOwnerName = newName;
        this.basicOwnerID = basicID;
        this.basicOwnerDiamond = diamond;
    }


    // TODO : MainScreen 화면에서 작동하는 Action들
    @FXML
    public void mainScreen_Deal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("DealScreen_CheckOwner.fxml"));
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);

            Scene scene = new Scene(parent);

            Stage primaryStage;
            if (loginInfo == 0) {
                primaryStage = (Stage) btn_MainScreen_Deal_Appraise.getScene().getWindow();
            } else {
                primaryStage = (Stage) btn_MainScreen_Deal_Sales.getScene().getWindow();
            }

            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("기존 소유자 확인");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mainScreen_Lookup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("LookupScreen.fxml"));
            Parent parent = loader.load();

            Controller_Lookup controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);

            Scene scene = new Scene(parent);

            if (loginInfo == 0) {
                Stage primaryStage = (Stage) btn_MainScreen_Deal_Appraise.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("다이아 정보 조회");

                primaryStage.show();
            } else {
                Stage primaryStage = (Stage) btn_MainScreen_Deal_Sales.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("다이아 정보 조회");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mainScreen_Appraise() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Select.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);

            Scene scene = new Scene(parent);


            Stage primaryStage = (Stage) btn_MainScreen_Deal_Appraise.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("다이아 감정");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mainScreen_Stealed() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("StealedScreen_CheckOwner.fxml"));
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);

            Scene scene = new Scene(parent);

            if (loginInfo == 0) {
                Stage primaryStage = (Stage) btn_MainScreen_Deal_Appraise.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("도난 신고");

                primaryStage.show();
            } else {
                Stage primaryStage = (Stage) btn_MainScreen_Deal_Sales.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("도난 신고");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // TODO : DealScreen_CheckOwner 화면에서 작동하는 Action들 (Popup 포함)
    @FXML
    public void dealScreen_CheckOwner_Lookup() {
        basicOwnerID = textfield_DealScreen_CheckOwner_BasicOwner.getText();
        basicOwnerDiamond = textfield_DealScreen_CheckOwner_Diamond.getText();
        try {
            if (myQuery.checkBasicOwner(basicOwnerID, basicOwnerDiamond)) {
                if(!myQuery.checkTheftState(basicOwnerDiamond)){
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("DealScreen_Popup_OK.fxml"));
                    Parent parent = loader.load();

                    Controller controller = loader.getController();
                    controller.setLoginInfo(loginInfo);
                    controller.setMyQuery(myQuery,randomDiamond);
                    controller.setOwner(basicOwnerID, basicOwnerDiamond, "", "");

                    Scene scene = new Scene(parent);
                    Stage primaryStage = (Stage) textfield_DealScreen_CheckOwner_BasicOwner.getScene().getWindow();
                    primaryStage.close();

                    primaryStage.setScene(scene);
                    primaryStage.setTitle("소유자 조회 완료");

                    primaryStage.show();
                }
                else{
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("LookupScreen_Popup_NOK.fxml"));
                    Parent parent = loader.load();

                    Controller_Lookup controller = loader.getController();
                    controller.setup_Text_ByTheft();

                    Scene scene = new Scene(parent);

                    Stage primaryStage = new Stage();
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("거래 불가");

                    primaryStage.show();
                }
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("DealScreen_Popup_NOK.fxml"));
                Parent parent = loader.load();

                Scene scene = new Scene(parent);

                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("소유자 조회 실패");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dealScreen_CheckOwner_ReturnBack() {
        try {
            if (loginInfo == 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Appraise.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setMyQuery(myQuery,randomDiamond);
                controller.setLoginInfo(loginInfo);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) textfield_DealScreen_CheckOwner_Diamond.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 메인화면");

                primaryStage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Sales.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyQuery(myQuery,randomDiamond);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) textfield_DealScreen_CheckOwner_Diamond.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<판매소> 메인화면");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dealScreen_Popup_OK_OK() {
        Stage primaryStage = (Stage) btn_DealScreen_Popup_OK.getScene().getWindow();
        primaryStage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("DealScreen_RegisterNewOwner.fxml"));
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);
            controller.setOwner(basicOwnerID, basicOwnerDiamond, "", "");

            Scene scene = new Scene(parent);

            primaryStage.setScene(scene);
            primaryStage.setTitle("신규 소유자 입력");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dealScreen_Popup_NOK_OK() {
        Stage primaryStage = (Stage) btn_DealScreen_Popup_NOK.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    public void dealScreen_CheckOwner_ForDemo(){
        textfield_DealScreen_CheckOwner_BasicOwner.setText("9606301478963");
        textfield_DealScreen_CheckOwner_Diamond.setText(randomDiamond);
    }


    //TODO : DealScreen_Register 화면에서 작동하는 Action들
    @FXML
    public void dealScreen_RegisterNewOwner_Register() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("DealScreen_CheckNewOwner.fxml"));
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);
            controller.setOwner(basicOwnerID, basicOwnerDiamond, textfield_DealScreen_RegisterNewOwner_ID.getText(), textfield_DealScreen_RegisterNewOwner_Name.getText());
            controller.setNewOwner(textfield_DealScreen_RegisterNewOwner_ID.getText(), textfield_DealScreen_RegisterNewOwner_Name.getText());

            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) textfield_DealScreen_RegisterNewOwner_ID.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("신규 사용자 정보 확인");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dealScreen_RegisterNewOwner_ReturnBack() {
        try {
            if (loginInfo == 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Appraise.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setMyQuery(myQuery,randomDiamond);
                controller.setLoginInfo(loginInfo);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) textfield_DealScreen_RegisterNewOwner_ID.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 메인화면");

                primaryStage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Sales.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyQuery(myQuery,randomDiamond);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) textfield_DealScreen_RegisterNewOwner_ID.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<판매소> 메인화면");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dealScreen_RegisterNewOwner_ForDemo(){
        textfield_DealScreen_RegisterNewOwner_ID.setText("9911252014819");
        textfield_DealScreen_RegisterNewOwner_Name.setText("Kim JunHee");
    }


    //TODO : DealScreen_CheckNewOwner 화면에서 작동하는 Action들
    public void setNewOwner(String id, String name) {
        text_DealScreen_CheckNewOwner_ID.setText(id);
        text_DealScreen_CheckNewOwner_Name.setText(name);
    }

    @FXML
    public void dealScreen_CheckNewOwner_OK() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("DealScreen_Complete.fxml"));
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);
            controller.setData(basicOwnerID, basicOwnerDiamond, newOwnerID, newOwnerName);

            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) text_DealScreen_CheckNewOwner_ID.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("거래 완료");
            primaryStage.show();

            myQuery.changeOwner_Deal(newOwnerID,newOwnerName,basicOwnerDiamond);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dealScreen_CheckNewOwner_ReturnBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("DealScreen_RegisterNewOwner.fxml"));
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);
            controller.setOwner(basicOwnerID, basicOwnerDiamond, "", "");

            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) text_DealScreen_CheckNewOwner_ID.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("신규 소유자 입력");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //TODO : DealScreen_Complete 화면에서 작동하는 Action들
    public void setData(String basicID, String diamond, String newID, String newName) {
        text_DealScreen_Complete_NewID.setText(newID);
        text_DealScreen_Complete_NewName.setText(newName);
        text_DealScreen_Complete_BasicID.setText(basicID);
        text_DealScreen_Complete_Diamond.setText(diamond);
    }
    @FXML
    public void dealScreen_Complete_OK(){
        try {
            if (loginInfo == 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Appraise.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setMyQuery(myQuery,randomDiamond);
                controller.setLoginInfo(loginInfo);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) text_DealScreen_Complete_BasicID.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 메인화면");

                primaryStage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Sales.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyQuery(myQuery,randomDiamond);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) text_DealScreen_Complete_BasicID.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<판매소> 메인화면");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // TODO : StealedScreen_CheckOwner 화면에서 작동하는 Action들
    @FXML
    public void stealedScreen_CheckOwner_ReturnBack(){
        try {
            if (loginInfo == 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Appraise.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setMyQuery(myQuery,randomDiamond);
                controller.setLoginInfo(loginInfo);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) textfield_StealedScreen_CheckOwner_BasicOwner.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 메인화면");

                primaryStage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Sales.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyQuery(myQuery,randomDiamond);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) textfield_StealedScreen_CheckOwner_BasicOwner.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<판매소> 메인화면");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void stealedScreen_CheckOwner_Lookup(){
        stealedOwnerID = textfield_StealedScreen_CheckOwner_BasicOwner.getText();
        stealedDiamond = textfield_StealedScreen_CheckOwner_Diamond.getText();
        try {
            if (myQuery.checkBasicOwner(stealedOwnerID, stealedDiamond)) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("StealedScreen_Complete.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyQuery(myQuery,randomDiamond);
                controller.setupText(stealedOwnerID,myQuery.getUserName(stealedDiamond),stealedDiamond,myQuery.getDate(stealedDiamond));

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) textfield_StealedScreen_CheckOwner_Diamond.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("도난신고 진행");

                primaryStage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("DealScreen_Popup_NOK.fxml"));
                Parent parent = loader.load();

                Scene scene = new Scene(parent);

                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("소유자 조회 실패");

                primaryStage.show();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // TODO : StealedScreen_Complete 화면에서 작동하는 Action들
    public void setupText(String ownerID,String ownerName, String diamond, String date){
        text_StealedScreen_Complete_OwnerID.setText(ownerID);
        text_StealedScreen_Complete_OwnerName.setText(ownerName);
        text_StealedScreen_Complete_Diamond.setText(diamond);
        text_StealedScreen_Complete_Date.setText(date);

        stealedDiamond = diamond;
    }
    @FXML
    public void stealedScreen_Complete_ReturnBack(){
        try {
            if (loginInfo == 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Appraise.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setMyQuery(myQuery,randomDiamond);
                controller.setLoginInfo(loginInfo);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) text_StealedScreen_Complete_OwnerID.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 메인화면");

                primaryStage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Sales.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyQuery(myQuery,randomDiamond);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) text_StealedScreen_Complete_OwnerID.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<판매소> 메인화면");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void stealedScreen_Complete_Complain(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("StealedScreen_Popup_OK.fxml"));
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);

            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) text_StealedScreen_Complete_OwnerID.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("도난 신고 완료");

            primaryStage.show();

            myQuery.setStealed(stealedDiamond);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void stealedScreen_Popup_OK_OK(){
        Stage primaryStage = (Stage) btn_StealedScreen_Popup_OK.getScene().getWindow();
        primaryStage.close();

        try {
            if (loginInfo == 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Appraise.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setMyQuery(myQuery,randomDiamond);
                controller.setLoginInfo(loginInfo);

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 메인화면");

                primaryStage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Sales.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setMyQuery(myQuery,randomDiamond);
                controller.setLoginInfo(loginInfo);

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("<판매소> 메인화면");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void stealedScreen_CheckOwner_ForDemo(){
        textfield_StealedScreen_CheckOwner_BasicOwner.setText("9911252014819");
        textfield_StealedScreen_CheckOwner_Diamond.setText(randomDiamond);
    }
}
