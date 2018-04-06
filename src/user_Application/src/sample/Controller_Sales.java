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

// 판매소에서 사용하는 앱으로, 해당 앱에서 발생하는 모든 이벤트를 처리한다.
public class Controller_Sales implements Initializable {
    @FXML
    private Button login_Appraise;

    @FXML
    private Button sales_DealButton;
    @FXML
    private Button deal_LookupButton;
    @FXML
    private Button deal_Sales_OKButton;
    @FXML
    private Button deal_Sales_NOKButton;
    @FXML
    private Button user_Sales_OKButton;
    @FXML
    private Button user_Sales_NOKButton;

    // 판매소 메인 페이지에서 일어날 수 있는 2가지 액션. 거래 진행, 다이아 조회 순이다.
    @FXML
    public void sales_Deal(ActionEvent actionEvent) {
        try {
            Parent deal = FXMLLoader.load(getClass().getResource("DealScreen_Sales.fxml"));
            Scene scene = new Scene(deal);
            Stage primaryStage = (Stage) sales_DealButton.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<판매소> 거래진행");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sales_Lookup(ActionEvent actionEvent) {
        try {
            Parent deal = FXMLLoader.load(getClass().getResource("LookupDia_Sales.fxml"));
            Scene scene = new Scene(deal);
            Stage primaryStage = (Stage) sales_DealButton.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<판매소> 다이아 정보 조회");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 거래 진행 페이지에서 일어날 수 있는 4가지 액션. 1번 소유자 조회, 2번 소유자 조회, 돌아가기, 거래하기 순이다.
    @FXML
    public void deal_User1_Check(ActionEvent actionEvent) {
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
    public void deal_User2_Check(ActionEvent actionEvent) {
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
    public void deal_ReturnBack(ActionEvent actionEvent) {
        try {
            Parent deal = FXMLLoader.load(getClass().getResource("MainScreen_Sales.fxml"));
            Scene scene = new Scene(deal);
            Stage primaryStage = (Stage) deal_LookupButton.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<판매소> 메인화면");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deal_Deal(ActionEvent actionEvent) {
        try {
            /*Parent deal = FXMLLoader.load(getClass().getResource("Popup_Deal_OK_Sales.fxml"));
            Scene scene = new Scene(deal);

            Stage primaryStage = (Stage) deal_LookupButton.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("거래 완료!");
            primaryStage.show();*/



            Parent deal = FXMLLoader.load(getClass().getResource("Popup_Deal_NOK_Sales.fxml"));
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
    public void deal_Sales_OK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) deal_Sales_OKButton.getScene().getWindow();
        primaryStage.close();

        try {
            Parent deal = FXMLLoader.load(getClass().getResource("MainScreen_Sales.fxml"));
            Scene scene = new Scene(deal);

            primaryStage.setScene(scene);
            primaryStage.setTitle("<판매소> 메인화면");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deal_Sales_NOK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) deal_Sales_NOKButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    public void user_Sales_OK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) user_Sales_OKButton.getScene().getWindow();
        primaryStage.close();
    }
    @FXML
    public void user_Sales_NOK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) user_Sales_NOKButton.getScene().getWindow();
        primaryStage.close();
    }


    @FXML
    public void login_Appraise(ActionEvent actionEvent) {
        try {
            Parent deal = FXMLLoader.load(getClass().getResource("MainScreen_Appraise.fxml"));
            Scene scene = new Scene(deal);
            Stage primaryStage = (Stage) login_Appraise.getScene().getWindow();
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
            Parent deal = FXMLLoader.load(getClass().getResource("MainScreen_Sales.fxml"));
            Scene scene = new Scene(deal);
            Stage primaryStage = (Stage) login_Appraise.getScene().getWindow();
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
}
