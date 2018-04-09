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

public class Controller_Appraise implements Initializable {
    @FXML
    private Button appraise_DealButton;
    @FXML
    private Button deal_LookupButton;
    @FXML
    private Button deal_Appraise_OKButton;

    // 감정원 메인 페이지에서 일어날 수 있는 3가지 액션. 거래 진행, 다이아 조회, 감정 진행 순이다.
    @FXML
    public void appraise_Deal(ActionEvent actionEvent) {
        try {
            Parent deal = FXMLLoader.load(getClass().getResource("DealScreen_Appraise.fxml"));
            Scene scene = new Scene(deal);
            Stage primaryStage = (Stage) appraise_DealButton.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 거래진행");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void appraise_Lookup(ActionEvent actionEvent) {
        try {
            Parent deal = FXMLLoader.load(getClass().getResource("LookupDia_Appraise.fxml"));
            Scene scene = new Scene(deal);
            Stage primaryStage = (Stage) appraise_DealButton.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 다이아 정보 조회");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void appraise_Appraise(ActionEvent actionEvent){

    }



    // 거래 진행 페이지에서 일어날 수 있는 4가지 액션. 1번 소유자 조회, 2번 소유자 조회, 돌아가기, 거래하기 순이다.
    @FXML
    public void deal_User1_Check(ActionEvent actionEvent){
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
    public void deal_User2_Check(ActionEvent actionEvent){
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
    public void deal_ReturnBack(ActionEvent actionEvent){
        try {
            Parent deal = FXMLLoader.load(getClass().getResource("MainScreen_Appraise.fxml"));
            Scene scene = new Scene(deal);
            Stage primaryStage = (Stage) deal_LookupButton.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 메인화면");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void deal_Deal(ActionEvent actionEvent){
        try {
            /*Parent deal = FXMLLoader.load(getClass().getResource("Popup_Deal_OK_Appraise.fxml"));
            Scene scene = new Scene(deal);

            Stage primaryStage = (Stage) deal_LookupButton.getScene().getWindow();
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
    public void deal_Appraise_OK(ActionEvent actionEvent){
        Stage primaryStage = (Stage) deal_Appraise_OKButton.getScene().getWindow();
        primaryStage.close();

        try {
            Parent deal = FXMLLoader.load(getClass().getResource("MainScreen_Appraise.fxml"));
            Scene scene = new Scene(deal);

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 메인화면");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
