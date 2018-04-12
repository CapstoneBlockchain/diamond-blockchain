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
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Appraise implements Initializable {
    private int loginInfo;
    private Diamond_DB_ForMidterm myDB;
    private String nowState;
    private Boolean checked;
    private Boolean modified;

    private String nowState_Add;
    private Boolean checked_Add;
    private Boolean added[] = new Boolean[15];

    @FXML
    private MenuButton menu_Appraise_Update;
    @FXML
    private MenuButton menu_Appraise_Add;
    @FXML
    public Text text_Popup_Modify_NOK;
    @FXML
    public Text text_Popup_Appraise_NOK;
    @FXML
    private TextField text_Appraise_AddData;
    @FXML
    private TextField text_Appraise_ModifyData;
    @FXML
    private TextField text_Appraise_LookupDiamond;
    @FXML
    private TextField text_Appraise_LookupUser;
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
    @FXML
    private Button btn_Popup_Modify_NOK;


    ObservableList<Model_DiamondData> myList;
    DB_Diamond updateDia;
    DB_User user;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nowState="Null";
        checked=false;
        modified=false;

        nowState_Add="Null";
        checked_Add=false;
        for(int a=0;a<15;a++){
            added[a]=false;
        }
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
            controller.setMyDB(myDB);

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
            controller.setMyDB(myDB);

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
            controller.setMyDB(myDB);

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
    public void appraiseScreen_Update_Modify(ActionEvent actionEvent){
        if(!checked){
            // 다이아가 조회되지 않음
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Modify_NOK.fxml"));
                Parent parent = null;

                parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_Popup_Modify_NOK.setText("※다이아를 조회해주세요.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("수정 실패");

                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(nowState.equals("Null")){
            // 메뉴가 선택되지 않음
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Modify_NOK.fxml"));
                Parent parent = null;

                parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_Popup_Modify_NOK.setText("※수정할 항목을 선택해주세요.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("수정 실패");

                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(text_Appraise_ModifyData.getText().equals("")){
            // 수정할 사항이 입력되지 않음.
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Modify_NOK.fxml"));
                Parent parent = null;

                parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_Popup_Modify_NOK.setText("※수정할 내용을 입력해주세요.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("수정 실패");

                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            modified=true;
            switch (nowState){
                case "Number":
                    myList.remove(3);
                    myList.add(3,new Model_DiamondData(new SimpleStringProperty(nowState),new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Shape & Cut":
                    myList.remove(4);
                    myList.add(4,new Model_DiamondData(new SimpleStringProperty(nowState),new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Min Radius":
                    myList.remove(5);
                    myList.add(5,new Model_DiamondData(new SimpleStringProperty(nowState),new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Max Radius":
                    myList.remove(6);
                    myList.add(6,new Model_DiamondData(new SimpleStringProperty(nowState),new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Height":
                    myList.remove(7);
                    myList.add(7,new Model_DiamondData(new SimpleStringProperty(nowState),new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Carat":
                    myList.remove(8);
                    myList.add(8,new Model_DiamondData(new SimpleStringProperty(nowState),new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Color":
                    myList.remove(9);
                    myList.add(9,new Model_DiamondData(new SimpleStringProperty(nowState),new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Clarity":
                    myList.remove(10);
                    myList.add(10,new Model_DiamondData(new SimpleStringProperty(nowState),new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Cut":
                    myList.remove(11);
                    myList.add(11,new Model_DiamondData(new SimpleStringProperty(nowState),new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Table Size":
                    myList.remove(12);
                    myList.add(12,new Model_DiamondData(new SimpleStringProperty(nowState),new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Total Depth":
                    myList.remove(13);
                    myList.add(13,new Model_DiamondData(new SimpleStringProperty(nowState),new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Min Girdle":
                    myList.remove(14);
                    myList.add(14,new Model_DiamondData(new SimpleStringProperty(nowState),new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Max Girdle":
                    myList.remove(15);
                    myList.add(15,new Model_DiamondData(new SimpleStringProperty(nowState),new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                default:
                    break;
            }

            myTableView.setItems(myList);
        }
    }

    @FXML
    public void appraiseScreen_Update_Update(ActionEvent actionEvent) {
        try {
            if(modified){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Appraise_OK.fxml"));
                Parent parent = loader.load();

                Controller_Appraise controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyDB(myDB);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) btn_AppraiseScreen_Update_ReturnBack.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("다이아 업데이트 완료!");

                primaryStage.show();

                updateDia.modifyData(myList.get(3).getData().getValue(),myList.get(4).getData().getValue(),myList.get(5).getData().getValue(),
                        myList.get(6).getData().getValue(),myList.get(7).getData().getValue(),myList.get(8).getData().getValue(),myList.get(9).getData().getValue(),
                        myList.get(10).getData().getValue(),myList.get(11).getData().getValue(),myList.get(12).getData().getValue(),myList.get(13).getData().getValue(),
                        myList.get(14).getData().getValue(),myList.get(15).getData().getValue());
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Appraise_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_Popup_Appraise_NOK.setText("※수정된 내용이 없습니다.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("다이아 업데이트 실패");

                primaryStage.show();
            }
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
            controller.setMyDB(myDB);

            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) btn_AppraiseScreen_Update_ReturnBack.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 다이아 감정");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Update_DiaCheck(ActionEvent actionEvent) {
        try {
            if(myDB.checkDia_Lookup(text_Appraise_LookupDiamond.getText())){
                Parent deal = FXMLLoader.load(getClass().getResource("Popup_Lookup_OK.fxml"));
                Scene scene = new Scene(deal);

                Stage primaryStage = new Stage();

                primaryStage.setScene(scene);
                primaryStage.setTitle("조회 완료");
                primaryStage.show();

                checked=true;

                listColumn.setCellValueFactory(cellData->cellData.getValue().getList());
                dataColumn.setCellValueFactory(cellData->cellData.getValue().getData());

                updateDia = myDB.getDia_Lookup(text_Appraise_LookupDiamond.getText());
                myList = FXCollections.observableArrayList(
                        new Model_DiamondData(new SimpleStringProperty("Date"),new SimpleStringProperty(updateDia.getDate())),
                        new Model_DiamondData(new SimpleStringProperty("User Name"),new SimpleStringProperty(updateDia.getUserName())),
                        new Model_DiamondData(new SimpleStringProperty("User ID"),new SimpleStringProperty(updateDia.getUserID())),
                        new Model_DiamondData(new SimpleStringProperty("Number"),new SimpleStringProperty(updateDia.getNumber())),
                        new Model_DiamondData(new SimpleStringProperty("Shape & Cut"),new SimpleStringProperty(updateDia.getShapeAndCut())),
                        new Model_DiamondData(new SimpleStringProperty("Min Radius"),new SimpleStringProperty(updateDia.getMinR())),
                        new Model_DiamondData(new SimpleStringProperty("Max Radius"),new SimpleStringProperty(updateDia.getMaxR())),
                        new Model_DiamondData(new SimpleStringProperty("Height"),new SimpleStringProperty(updateDia.getHeight())),
                        new Model_DiamondData(new SimpleStringProperty("Carat"),new SimpleStringProperty(updateDia.getCarat())),
                        new Model_DiamondData(new SimpleStringProperty("Color"),new SimpleStringProperty(updateDia.getColor())),
                        new Model_DiamondData(new SimpleStringProperty("Clarity"),new SimpleStringProperty(updateDia.getClarity())),
                        new Model_DiamondData(new SimpleStringProperty("Cut"),new SimpleStringProperty(updateDia.getCut())),
                        new Model_DiamondData(new SimpleStringProperty("Table Size"),new SimpleStringProperty(updateDia.getTableSize())),
                        new Model_DiamondData(new SimpleStringProperty("Total Depth"),new SimpleStringProperty(updateDia.getTotalDepth())),
                        new Model_DiamondData(new SimpleStringProperty("Min Girdle"),new SimpleStringProperty(updateDia.getMinGirdle())),
                        new Model_DiamondData(new SimpleStringProperty("Max Girdle"),new SimpleStringProperty(updateDia.getMaxGirdle())),
                        new Model_DiamondData(new SimpleStringProperty("Laser Inscription"),new SimpleStringProperty(updateDia.getLaserInscription()))
                );
                myTableView.setItems(myList);
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Lookup_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Lookup controller = loader.getController();
                controller.text_Popup_Lookup_NOK.setText("※해당 다이아는 존재하지 않습니다.");

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
    public void appraiseScreen_Add_Modify(){
        if(!checked_Add){
            // 유저가 조회되지 않음
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Modify_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_Popup_Modify_NOK.setText("※유저를 조회해주세요.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("정보 등록 실패");

                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(nowState_Add.equals("Null")){
            // 메뉴가 선택되지 않음
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Modify_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_Popup_Modify_NOK.setText("※등록할 항목을 선택해주세요.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("정보 등록 실패");

                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(text_Appraise_AddData.getText().equals("")){
            // 수정할 사항이 입력되지 않음.
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Modify_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_Popup_Modify_NOK.setText("※등록할 내용을 입력해주세요.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("정보 등록 실패");

                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(nowState_Add.equals("Laser Inscription") && myDB.checkDia_Lookup(text_Appraise_AddData.getText())){
            // 다이아 번호 중복
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Modify_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_Popup_Modify_NOK.setText("※이미 존재하는 다이아 일련번호입니다.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("정보 등록 실패");

                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            switch (nowState_Add){
                case "Date":
                    added[0]=true;
                    myList.remove(0);
                    myList.add(0,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Number":
                    added[1]=true;
                    myList.remove(3);
                    myList.add(3,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Shape & Cut":
                    added[2]=true;
                    myList.remove(4);
                    myList.add(4,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Min Radius":
                    added[3]=true;
                    myList.remove(5);
                    myList.add(5,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Max Radius":
                    added[4]=true;
                    myList.remove(6);
                    myList.add(6,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Height":
                    added[5]=true;
                    myList.remove(7);
                    myList.add(7,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Carat":
                    added[6]=true;
                    myList.remove(8);
                    myList.add(8,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Color":
                    added[7]=true;
                    myList.remove(9);
                    myList.add(9,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Clarity":
                    added[8]=true;
                    myList.remove(10);
                    myList.add(10,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Cut":
                    added[9]=true;
                    myList.remove(11);
                    myList.add(11,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Table Size":
                    added[10]=true;
                    myList.remove(12);
                    myList.add(12,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Total Depth":
                    added[11]=true;
                    myList.remove(13);
                    myList.add(13,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Min Girdle":
                    added[12]=true;
                    myList.remove(14);
                    myList.add(14,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Max Girdle":
                    added[13]=true;
                    myList.remove(15);
                    myList.add(15,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Laser Inscription":
                    added[14]=true;
                    myList.remove(16);
                    myList.add(16,new Model_DiamondData(new SimpleStringProperty(nowState_Add),new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                default:
                    break;
            }

            myTableView.setItems(myList);
        }
    }
    @FXML
    public void appraiseScreen_Add_ReturnBack(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AppraiseScreen_Select.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyDB(myDB);

            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) btn_AppraiseScreen_Add_ReturnBack.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 다이아 감정");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Add_Add(ActionEvent actionEvent) {
        int count=0;
        for(int a=0;a<15;a++){
            if(added[a]){
                count++;
            }
        }
        try {
            if(count==15){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Appraise_OK.fxml"));
                Parent parent = loader.load();

                Controller_Appraise controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyDB(myDB);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) btn_AppraiseScreen_Add_ReturnBack.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("신규 등록 완료!");

                primaryStage.show();

                myDB.addData(user,myList.get(3).getData().getValue(),myList.get(4).getData().getValue(),myList.get(5).getData().getValue(),
                        myList.get(6).getData().getValue(),myList.get(7).getData().getValue(),myList.get(8).getData().getValue(),myList.get(9).getData().getValue(),
                        myList.get(10).getData().getValue(),myList.get(11).getData().getValue(),myList.get(12).getData().getValue(),myList.get(13).getData().getValue(),
                        myList.get(14).getData().getValue(),myList.get(15).getData().getValue(),myList.get(16).getData().getValue(),myList.get(0).getData().getValue());
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_Appraise_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_Popup_Appraise_NOK.setText("※등록되지 않은 정보가 있습니다.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("신규 등록 실패");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Add_UserCheck(ActionEvent actionEvent) {
        try {
            if(myDB.checkUser(text_Appraise_LookupUser.getText())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_User_OK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();
                Scene scene = new Scene(parent);

                Controller controller = loader.getController();
                controller.text_Popup_User_OK_1stLine.setText("등록된 유저 정보");

                primaryStage.setScene(scene);
                primaryStage.setTitle("조회 완료");
                primaryStage.show();

                checked_Add=true;

                listColumn.setCellValueFactory(cellData->cellData.getValue().getList());
                dataColumn.setCellValueFactory(cellData->cellData.getValue().getData());

                user = myDB.getUser(text_Appraise_LookupUser.getText());
                myList = FXCollections.observableArrayList(
                        new Model_DiamondData(new SimpleStringProperty("Date"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("User Name"),new SimpleStringProperty(user.getUserName())),
                        new Model_DiamondData(new SimpleStringProperty("User ID"),new SimpleStringProperty(user.getUserID())),
                        new Model_DiamondData(new SimpleStringProperty("Number"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("Shape & Cut"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("Min Radius"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("Max Radius"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("Height"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("Carat"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("Color"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("Clarity"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("Cut"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("Table Size"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("Total Depth"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("Min Girdle"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("Max Girdle"),new SimpleStringProperty("")),
                        new Model_DiamondData(new SimpleStringProperty("Laser Inscription"),new SimpleStringProperty(""))
                );
                myTableView.setItems(myList);
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup_User_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller controller = loader.getController();
                controller.text_Popup_User_NOK_1stLine.setText("등록된 유저 정보");
                controller.text_Popup_User_NOK.setText("※해당 ID는 존재하지 않습니다.");

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
    public void popup_Appraise_OK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) btn_Popup_Appraise_OK.getScene().getWindow();
        primaryStage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen_Appraise.fxml"));     // 어짜피 다이아 감정은 감정원밖에 못하니까.
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyDB(myDB);

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

    @FXML
    public void popup_Modify_NOK(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) btn_Popup_Modify_NOK.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    public void update_menuitem1(ActionEvent actionEvent) {
        nowState = "Number";
        menu_Appraise_Update.setText(nowState);
    }
    @FXML
    public void update_menuitem2(ActionEvent actionEvent) {
        nowState = "Shape & Cut";
        menu_Appraise_Update.setText(nowState);
    }
    @FXML
    public void update_menuitem3(ActionEvent actionEvent) {
        nowState = "Min Radius";
        menu_Appraise_Update.setText(nowState);
    }
    @FXML
    public void update_menuitem4(ActionEvent actionEvent) {
        nowState = "Max Radius";
        menu_Appraise_Update.setText(nowState);
    }
    @FXML
    public void update_menuitem5(ActionEvent actionEvent) {
        nowState = "Height";
        menu_Appraise_Update.setText(nowState);
    }
    @FXML
    public void update_menuitem6(ActionEvent actionEvent) {
        nowState = "Carat";
        menu_Appraise_Update.setText(nowState);
    }
    @FXML
    public void update_menuitem7(ActionEvent actionEvent) {
        nowState = "Color";
        menu_Appraise_Update.setText(nowState);
    }
    @FXML
    public void update_menuitem8(ActionEvent actionEvent) {
        nowState = "Clarity";
        menu_Appraise_Update.setText(nowState);
    }
    @FXML
    public void update_menuitem9(ActionEvent actionEvent) {
        nowState = "Cut";
        menu_Appraise_Update.setText(nowState);
    }
    @FXML
    public void update_menuitem10(ActionEvent actionEvent) {
        nowState = "Table Size";
        menu_Appraise_Update.setText(nowState);
    }
    @FXML
    public void update_menuitem11(ActionEvent actionEvent) {
        nowState = "Total Depth";
        menu_Appraise_Update.setText(nowState);
    }
    @FXML
    public void update_menuitem12(ActionEvent actionEvent) {
        nowState = "Min Girdle";
        menu_Appraise_Update.setText(nowState);
    }
    @FXML
    public void update_menuitem13(ActionEvent actionEvent) {
        nowState = "Max Girdle";
        menu_Appraise_Update.setText(nowState);
    }


    @FXML
    public void add_menuitem1(){
        nowState_Add = "Date";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem2(){
        nowState_Add = "Number";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem3(){
        nowState_Add = "Shape & Cut";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem4(){
        nowState_Add = "Min Radius";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem5(){
        nowState_Add = "Max Radius";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem6(){
        nowState_Add = "Height";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem7(){
        nowState_Add = "Carat";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem8(){
        nowState_Add = "Color";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem9(){
        nowState_Add = "Clarity";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem10(){
        nowState_Add = "Cut";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem11(){
        nowState_Add = "Table Size";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem12(){
        nowState_Add = "Total Depth";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem13(){
        nowState_Add = "Min Girdle";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem14(){
        nowState_Add = "Max Girdle";
        menu_Appraise_Add.setText(nowState_Add);
    }
    @FXML
    public void add_menuitem15(){
        nowState_Add = "Laser Inscription";
        menu_Appraise_Add.setText(nowState_Add);
    }

    public void setMyDB(Diamond_DB_ForMidterm myDB){
        this.myDB = myDB;
    }

}
