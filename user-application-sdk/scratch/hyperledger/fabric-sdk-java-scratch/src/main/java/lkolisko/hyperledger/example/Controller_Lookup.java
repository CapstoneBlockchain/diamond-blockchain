package lkolisko.hyperledger.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_Lookup implements Initializable {
    private int loginInfo;
    private String randomDiamond;

    @FXML
    private TextField text_Lookup_Diamond;
    @FXML
    private TableView<Model_DiamondData> myTableView;
    @FXML
    private TableColumn<Model_DiamondData,String> listColumn;
    @FXML
    private TableColumn<Model_DiamondData,String> dataColumn;
    @FXML
    private Button btn_LookupScreen_ReturnBack;
    @FXML
    private Button btn_LookupScreen_Popup_OK;
    @FXML
    private Button btn_LookupScreen_Popup_NOK;


    ObservableList<Model_DiamondData> myList;
    HFJavaSDKBasicExample myQuery = new HFJavaSDKBasicExample();

    @FXML
    public void lookupScreen_forDemo(){
        text_Lookup_Diamond.setText(randomDiamond);
    }

    @FXML
    public void lookupScreen_ReturnBack(){
        try {
            if(loginInfo==0){
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Appraise.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyQuery(myQuery,randomDiamond);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) btn_LookupScreen_ReturnBack.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("<감정원> 메인화면");

                primaryStage.show();
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Sales.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyQuery(myQuery,randomDiamond);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) btn_LookupScreen_ReturnBack.getScene().getWindow();
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
    public void lookupScreen_DiaCheck(ActionEvent actionEvent) {
        try {
            if(myQuery.checkDia_Lookup(text_Lookup_Diamond.getText())){
                Parent deal = FXMLLoader.load(getClass().getClassLoader().getResource("LookupScreen_Popup_OK.fxml"));
                Scene scene = new Scene(deal);

                Stage primaryStage = new Stage();

                primaryStage.setScene(scene);
                primaryStage.setTitle("조회 완료");
                primaryStage.show();

                listColumn.setCellValueFactory(cellData->cellData.getValue().getList());
                dataColumn.setCellValueFactory(cellData->cellData.getValue().getData());

                ArrayList<String> temp = myQuery.getDiamondData(text_Lookup_Diamond.getText());
                myList = FXCollections.observableArrayList(
                        new Model_DiamondData(new SimpleStringProperty("Date"),new SimpleStringProperty(temp.get(0))),
                        new Model_DiamondData(new SimpleStringProperty("User Name"),new SimpleStringProperty(temp.get(1))),
                        new Model_DiamondData(new SimpleStringProperty("User ID"),new SimpleStringProperty(temp.get(2))),
                        new Model_DiamondData(new SimpleStringProperty("Number"),new SimpleStringProperty(temp.get(3))),
                        new Model_DiamondData(new SimpleStringProperty("Shape & Cut"),new SimpleStringProperty(temp.get(4))),
                        new Model_DiamondData(new SimpleStringProperty("Min Radius"),new SimpleStringProperty(temp.get(5))),
                        new Model_DiamondData(new SimpleStringProperty("Max Radius"),new SimpleStringProperty(temp.get(6))),
                        new Model_DiamondData(new SimpleStringProperty("Height"),new SimpleStringProperty(temp.get(7))),
                        new Model_DiamondData(new SimpleStringProperty("Carat"),new SimpleStringProperty(temp.get(8))),
                        new Model_DiamondData(new SimpleStringProperty("Color"),new SimpleStringProperty(temp.get(9))),
                        new Model_DiamondData(new SimpleStringProperty("Clarity"),new SimpleStringProperty(temp.get(10))),
                        new Model_DiamondData(new SimpleStringProperty("Cut"),new SimpleStringProperty(temp.get(11))),
                        new Model_DiamondData(new SimpleStringProperty("Table Size"),new SimpleStringProperty(temp.get(12))),
                        new Model_DiamondData(new SimpleStringProperty("Total Depth"),new SimpleStringProperty(temp.get(13))),
                        new Model_DiamondData(new SimpleStringProperty("Min Girdle"),new SimpleStringProperty(temp.get(14))),
                        new Model_DiamondData(new SimpleStringProperty("Max Girdle"),new SimpleStringProperty(temp.get(15))),
                        new Model_DiamondData(new SimpleStringProperty("Laser Inscription"),new SimpleStringProperty(temp.get(16))),
                        new Model_DiamondData(new SimpleStringProperty("Check Theft"),new SimpleStringProperty(temp.get(17)))
                );

                myTableView.setItems(myList);
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("LookupScreen_Popup_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

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
    public void lookupScreen_Popup_OK_OK() {
        Stage primaryStage = (Stage) btn_LookupScreen_Popup_OK.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    public void lookupScreen_Popup_NOK_OK() {
        Stage primaryStage = (Stage) btn_LookupScreen_Popup_NOK.getScene().getWindow();
        primaryStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setLoginInfo(int loginInfo){
        this.loginInfo = loginInfo;
    }
    public void setMyQuery(HFJavaSDKBasicExample myQuery, String randomDiamond){
        this.myQuery = myQuery;
        this.randomDiamond=randomDiamond;
    }

}
