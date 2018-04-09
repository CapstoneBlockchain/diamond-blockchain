package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private int loginInfo;                          // 0이면 감정원, 1이면 판매소
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

            Scene scene = new Scene(parent);

            if(loginInfo==0){
                Stage primaryStage = (Stage) btn_MainScreen_Deal_Appraise.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 다이아 정보 조회");

                primaryStage.show();
            }
            else{
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
            Parent deal = FXMLLoader.load(getClass().getResource("Popup_User_OK.fxml"));
            Scene scene = new Scene(deal);

            Stage primaryStage = new Stage();

            primaryStage.setScene(scene);
            primaryStage.setTitle("조회 완료");
            primaryStage.show();


            /*Parent deal = FXMLLoader.load(getClass().getResource("Popup_User_NOK.fxml"));
            Scene scene = new Scene(deal);

            Stage primaryStage = new Stage();

            primaryStage.setScene(scene);
            primaryStage.setTitle("조회 실패");
            primaryStage.show();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dealScreen_UserCheck2(ActionEvent actionEvent) {
        try {
            /*Parent deal = FXMLLoader.load(getClass().getResource("Popup_User_OK.fxml"));
            Scene scene = new Scene(deal);

            Stage primaryStage = new Stage();

            primaryStage.setScene(scene);
            primaryStage.setTitle("조회 완료");
            primaryStage.show();*/


            Parent deal = FXMLLoader.load(getClass().getResource("Popup_User_NOK.fxml"));
            Scene scene = new Scene(deal);

            Stage primaryStage = new Stage();

            primaryStage.setScene(scene);
            primaryStage.setTitle("조회 실패");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dealScreen_ReturnBack(ActionEvent actionEvent) {
        try {
            if(loginInfo==0){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen_Appraise.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) btn_DealScreen_UserCheck.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 메인화면");

                primaryStage.show();
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen_Sales.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);

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
        try {
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Deal_OK.fxml"));
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);

            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) btn_DealScreen_UserCheck.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("거래 완료!");

            primaryStage.show();*/


            Parent deal = FXMLLoader.load(getClass().getResource("Popup_Deal_NOK.fxml"));
            Scene scene = new Scene(deal);

            Stage primaryStage = new Stage();

            primaryStage.setScene(scene);
            primaryStage.setTitle("거래 실패");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void popup_Deal_OK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) btn_Popup_Deal_OK.getScene().getWindow();
        primaryStage.close();

        try {
            if(loginInfo==0){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen_Appraise.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 메인화면");

                primaryStage.show();
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen_Sales.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
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
            Parent parent = (Parent)loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(0);

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
            Parent parent = (Parent)loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(1);

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

    }

    public void setLoginInfo(int loginInfo){
        this.loginInfo = loginInfo;
    }
}
