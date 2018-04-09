package sample;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Appraise implements Initializable {
    private int loginInfo;
    @FXML
    private TableView<Model_DiamondData> myTableView;
    @FXML
    private TableColumn<Model_DiamondData,String> listColumn;
    @FXML
    private TableColumn<Model_DiamondData,String> dataColumn;
    @FXML
    private Button btn_AppraiseScreen_Update;
    @FXML
    private Button btn_AppraiseScreen_Update_ReturnBack;
    @FXML
    private Button btn_AppraiseScreen_Add_ReturnBack;
    @FXML
    private Button btn_Popup_Appraise_OK;
    @FXML
    private Button btn_Popup_Appraise_NOK;


    ObservableList<Model_DiamondData> myList = FXCollections.observableArrayList(
            new Model_DiamondData(new SimpleStringProperty("Date"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Number"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Shape & Cut"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Measurement"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("UV fluorescence"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Carat"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Color"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Clarity"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Cut"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Table size"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Pavilion depth"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Total depth"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Crown angle"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Gridle state"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Gridle thickness"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Culet"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Polish"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Symmetry"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Symbols"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Comments"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("The facet diagram"),new SimpleStringProperty("...")),
            new Model_DiamondData(new SimpleStringProperty("Laser inscription"),new SimpleStringProperty("..."))
    );


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setLoginInfo(int loginInfo){
        this.loginInfo = loginInfo;
    }

    @FXML
    public void appraiseScreen_Select_Update(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AppraiseScreen_Update.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);

            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) btn_AppraiseScreen_Update.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 기존 다이아 업데이트");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Select_Add(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AppraiseScreen_Add.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);

            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) btn_AppraiseScreen_Update.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 신규 다이아 등록");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Select_ReturnBack(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen_Appraise.fxml"));
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);

            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) btn_AppraiseScreen_Update.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 메인화면");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Update_Update(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Appraise_OK.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);

            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) btn_AppraiseScreen_Update_ReturnBack.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("다이아 업데이트 완료!");

            primaryStage.show();


            /*Parent deal = FXMLLoader.load(getClass().getResource("Popup_Appraise_NOK.fxml"));
            Scene scene = new Scene(deal);

            Stage primaryStage = new Stage();

            primaryStage.setScene(scene);
            primaryStage.setTitle("다이아 업데이트 실패");
            primaryStage.show();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Update_ReturnBack(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AppraiseScreen_Select.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);

            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) btn_AppraiseScreen_Update_ReturnBack.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 메인화면");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Update_DiaCheck(ActionEvent actionEvent) {
        try {
            Parent deal = FXMLLoader.load(getClass().getResource("Popup_Lookup_OK.fxml"));
            Scene scene = new Scene(deal);

            Stage primaryStage = new Stage();

            primaryStage.setScene(scene);
            primaryStage.setTitle("조회 완료");
            primaryStage.show();

            listColumn.setCellValueFactory(cellData->cellData.getValue().getList());
            dataColumn.setCellValueFactory(cellData->cellData.getValue().getData());
            myTableView.setItems(myList);


            /*Parent deal = FXMLLoader.load(getClass().getResource("Popup_Lookup_NOK.fxml"));
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
    public void appraiseScreen_Add_ReturnBack(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AppraiseScreen_Select.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);

            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) btn_AppraiseScreen_Add_ReturnBack.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 메인화면");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Add_Add(ActionEvent actionEvent) {
        try {
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Appraise_OK.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);

            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) btn_AppraiseScreen_Add_ReturnBack.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("신규 등록 완료!");

            primaryStage.show();*/


            Parent deal = FXMLLoader.load(getClass().getResource("Popup_Appraise_NOK.fxml"));
            Scene scene = new Scene(deal);

            Stage primaryStage = new Stage();

            primaryStage.setScene(scene);
            primaryStage.setTitle("신규 등록 실패");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Add_DiaCheck(ActionEvent actionEvent) {
        try {
            Parent deal = FXMLLoader.load(getClass().getResource("Popup_Lookup_OK.fxml"));
            Scene scene = new Scene(deal);

            Stage primaryStage = new Stage();

            primaryStage.setScene(scene);
            primaryStage.setTitle("조회 완료");
            primaryStage.show();

            listColumn.setCellValueFactory(cellData->cellData.getValue().getList());
            dataColumn.setCellValueFactory(cellData->cellData.getValue().getData());
            myTableView.setItems(myList);


            /*Parent deal = FXMLLoader.load(getClass().getResource("Popup_Lookup_NOK.fxml"));
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
    public void popup_Appraise_OK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) btn_Popup_Appraise_OK.getScene().getWindow();
        primaryStage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen_Appraise.fxml"));     // 어짜피 다이아 감정은 감정원밖에 못하니까.
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);

            Scene scene = new Scene(parent);

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 메인화면");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void popup_Appraise_NOK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) btn_Popup_Appraise_NOK.getScene().getWindow();
        primaryStage.close();
    }
}
