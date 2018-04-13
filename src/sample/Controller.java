package sample;

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
    private int loginInfo;                          // 0이면 감정원, 1이면 판매소
    private Diamond_DB_ForMidterm myDB;
    private boolean userCheck1;
    private boolean userCheck2;
    private String checked_User1;
    private String checked_User2;

    @FXML
    private TextField text_Deal_User1;
    @FXML
    private TextField text_Deal_User2;
    @FXML
    private TextField text_Deal_Diamond;
    @FXML
    public Text text_Popup_Deal_NOK;
    @FXML
    public Text text_Popup_User_NOK;
    @FXML
    public Text text_Popup_User_OK_1stLine;
    @FXML
    public Text text_Popup_User_NOK_1stLine;

    @FXML
    private Button btn_Login_Appraise;
    @FXML
    private Button btn_MainScreen_Deal_Sales;
    @FXML
    private Button btn_MainScreen_Deal_Appraise;
    @FXML
    private Button btn_DealScreen_UserCheck;
    @FXML
    private Button btn_Popup_Deal_OK;
    @FXML
    private Button btn_Popup_Deal_NOK;
    @FXML
    private Button btn_Popup_User_OK;
    @FXML
    private Button btn_Popup_User_NOK;

    @FXML
    public void mainScreen_Deal(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DealScreen.fxml"));
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyDB(myDB);

            Scene scene = new Scene(parent);

            if (loginInfo == 0) {
                Stage primaryStage = (Stage) btn_MainScreen_Deal_Appraise.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 거래진행");

                primaryStage.show();
            } else {
                Stage primaryStage = (Stage) btn_MainScreen_Deal_Sales.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<판매소> 거래진행");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mainScreen_Lookup(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LookupScreen.fxml"));
            Parent parent = loader.load();

            Controller_Lookup controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyDB(myDB);

            Scene scene = new Scene(parent);

            if (loginInfo == 0) {
                Stage primaryStage = (Stage) btn_MainScreen_Deal_Appraise.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 다이아 정보 조회");

                primaryStage.show();
            } else {
                Stage primaryStage = (Stage) btn_MainScreen_Deal_Sales.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<판매소> 다이아 정보 조회");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mainScreen_Appraise(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AppraiseScreen_Select.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyDB(myDB);

            Scene scene = new Scene(parent);


            Stage primaryStage = (Stage) btn_MainScreen_Deal_Appraise.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 다이아 감정");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dealScreen_UserCheck1(ActionEvent actionEvent) {
        try {
            if (myDB.checkUser(text_Deal_User1.getText())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_User_OK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller controller = loader.getController();
                controller.text_Popup_User_OK_1stLine.setText("다이아의 소유자 정보");

                userCheck1=true;
                checked_User1 = text_Deal_User1.getText();

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("조회 완료");

                primaryStage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_User_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller controller = loader.getController();
                controller.text_Popup_User_NOK_1stLine.setText("다이아의 소유자 정보");
                controller.text_Popup_User_NOK.setText("※해당 ID는 존재하지 않습니다.");

                Scene scene = new Scene(parent);

                userCheck1 = false;

                primaryStage.setScene(scene);
                primaryStage.setTitle("조회 실패");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dealScreen_UserCheck2(ActionEvent actionEvent) {
        try {
            if (myDB.checkUser(text_Deal_User2.getText())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_User_OK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller controller = loader.getController();
                controller.text_Popup_User_OK_1stLine.setText("다이아의 소유자 정보");

                userCheck2=true;
                checked_User2 = text_Deal_User2.getText();

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("조회 완료");

                primaryStage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_User_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller controller = loader.getController();
                controller.text_Popup_User_NOK_1stLine.setText("다이아의 소유자 정보");
                controller.text_Popup_User_NOK.setText("※해당 ID는 존재하지 않습니다.");

                userCheck2=false;

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("조회 실패");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dealScreen_ReturnBack(ActionEvent actionEvent) {
        try {
            if (loginInfo == 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen_Appraise.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyDB(myDB);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) btn_DealScreen_UserCheck.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 메인화면");

                primaryStage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen_Sales.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyDB(myDB);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) btn_DealScreen_UserCheck.getScene().getWindow();
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
    public void dealScreen_Deal(ActionEvent actionEvent) {
        if (!checked_User1.equals(text_Deal_User1.getText())) {
            userCheck1 = false;
        }
        if (!checked_User2.equals(text_Deal_User2.getText())) {
            userCheck2 = false;
        }
        String message = "";
        boolean check = false;
        try {
            if (userCheck1 && userCheck2) {
                if (myDB.checkDia_Deal_Owner(text_Deal_User1.getText(), text_Deal_Diamond.getText())) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Deal_OK.fxml"));
                    Parent parent = loader.load();

                    Controller controller = loader.getController();
                    controller.setLoginInfo(loginInfo);
                    controller.setMyDB(myDB);

                    Scene scene = new Scene(parent);
                    Stage primaryStage = (Stage) btn_DealScreen_UserCheck.getScene().getWindow();
                    primaryStage.close();

                    primaryStage.setScene(scene);
                    primaryStage.setTitle("거래 완료!");

                    primaryStage.show();

                    myDB.changeOwner_Deal(text_Deal_User2.getText(), text_Deal_Diamond.getText());

                    check = true;
                } else {
                    // 소유자와 다이아 정보 일치X
                    message = "※해당 다이아를 소유하고 있지 않습니다.";
                }

            } else if (userCheck1) {
                // userCheck2 안됨.
                message = "※다음 소유자 조회를 해주세요.";
            } else if (userCheck2) {
                // userCheck1 안됨.
                message = "※기존 소유자 조회를 해주세요.";
            } else {
                // 둘 다 안됨.
                message = "※소유자 조회를 진행해주세요.";

            }

            if (!check) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Deal_NOK.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.text_Popup_Deal_NOK.setText(message);

                Scene scene = new Scene(parent);

                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("거래 실패");

                primaryStage.show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void popup_Deal_OK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) btn_Popup_Deal_OK.getScene().getWindow();
        primaryStage.close();

        try {
            if (loginInfo == 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen_Appraise.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyDB(myDB);

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 메인화면");

                primaryStage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen_Sales.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyDB(myDB);

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
    public void popup_Deal_NOK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) btn_Popup_Deal_NOK.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    public void popup_User_OK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) btn_Popup_User_OK.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    public void popup_User_NOK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) btn_Popup_User_NOK.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    public void login_Appraise(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen_Appraise.fxml"));
            Parent parent = (Parent) loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(0);

            Diamond_DB_ForMidterm testDB = new Diamond_DB_ForMidterm();
            controller.setMyDB(testDB);

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen_Sales.fxml"));
            Parent parent = (Parent) loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(1);

            Diamond_DB_ForMidterm testDB = new Diamond_DB_ForMidterm();
            controller.setMyDB(testDB);

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userCheck1 = false;
        userCheck2 = false;
    }

    public void setLoginInfo(int loginInfo) {
        this.loginInfo = loginInfo;
    }

    public void setMyDB(Diamond_DB_ForMidterm myDB) {
        this.myDB = myDB;
    }
}
