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

public class Controller_DiamondLookup_Sales implements Initializable {
    @FXML
    private TableView<DiamondDataModel> myTableView;
    @FXML
    private TableColumn<DiamondDataModel,String> listColumn;
    @FXML
    private TableColumn<DiamondDataModel,String> dataColumn;
    @FXML
    private Button lookup_Sales_ReturnButton;
    @FXML
    private Button lookupDia_OKButton;
    @FXML
    private Button lookupDia_NOKButton;


    ObservableList<DiamondDataModel> myList = FXCollections.observableArrayList(
            new DiamondDataModel(new SimpleStringProperty("Date"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Number"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Shape & Cut"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Measurement"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("UV fluorescence"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Carat"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Color"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Clarity"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Cut"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Table size"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Pavilion depth"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Total depth"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Crown angle"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Gridle state"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Gridle thickness"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Culet"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Polish"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Symmetry"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Symbols"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Comments"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("The facet diagram"),new SimpleStringProperty("...")),
            new DiamondDataModel(new SimpleStringProperty("Laser inscription"),new SimpleStringProperty("..."))
    );

    @FXML
    public void lookup_ReturnBack(ActionEvent actionEvent){
        try {
            Parent deal = FXMLLoader.load(getClass().getResource("MainScreen_Sales.fxml"));
            Scene scene = new Scene(deal);
            Stage primaryStage = (Stage) lookup_Sales_ReturnButton.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<판매소> 메인화면");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void lookup_Lookup(ActionEvent actionEvent) {
        try {
            Parent deal = FXMLLoader.load(getClass().getResource("Popup_LookupDia_OK.fxml"));
            Scene scene = new Scene(deal);

            Stage primaryStage = new Stage();

            primaryStage.setScene(scene);
            primaryStage.setTitle("조회 완료");
            primaryStage.show();

            listColumn.setCellValueFactory(cellData->cellData.getValue().getList());
            dataColumn.setCellValueFactory(cellData->cellData.getValue().getData());
            myTableView.setItems(myList);


            /*Parent deal = FXMLLoader.load(getClass().getResource("Popup_LookupDia_NOK.fxml"));
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
    public void lookupDia_OK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) lookupDia_OKButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    public void lookupDia_NOK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) lookupDia_NOKButton.getScene().getWindow();
        primaryStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
