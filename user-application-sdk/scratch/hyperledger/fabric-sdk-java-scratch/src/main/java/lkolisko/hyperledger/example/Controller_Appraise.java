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
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_Appraise implements Initializable {
    private int loginInfo;
    private String nowState;
    private Boolean checked;
    private Boolean modified;
    private String randomDiamond;

    private String nowState_Add;
    private Boolean added[] = new Boolean[15];

    @FXML
    private MenuButton menu_Appraise_Update;
    @FXML
    private MenuButton menu_Appraise_Add;
    @FXML
    public Text text_AppraiseScreen_Modify_NOK;
    @FXML
    public Text text_AppraiseScreen_Add_CheckNewOwner_NewID;
    @FXML
    public Text text_AppraiseScreen_Add_CheckNewOwner_NewName;
    @FXML
    public Text text_AppraiseScreen_Add_Complete_NewID;
    @FXML
    public Text text_AppraiseScreen_Add_Complete_NewName;
    @FXML
    public Text text_AppraiseScreen_Add_Complete_Diamond;
    @FXML
    public TextField textfield_AppraiseScreen_Add_RegisterOwner_NewOwnerName;
    @FXML
    public TextField textfield_AppraiseScreen_Add_RegisterOwner_NewOwnerID;
    @FXML
    private TextField text_Appraise_AddData;
    @FXML
    private TextField text_Appraise_ModifyData;
    @FXML
    private TextField text_Appraise_LookupDiamond;
    @FXML
    private TableView<Model_DiamondData> myTableView;
    @FXML
    private TableColumn<Model_DiamondData, String> listColumn;
    @FXML
    private TableColumn<Model_DiamondData, String> dataColumn;
    @FXML
    private Button btn_AppraiseScreen_Update;
    @FXML
    private Button btn_AppraiseScreen_Update_ReturnBack;
    @FXML
    private Button btn_AppraiseScreen_Add_ReturnBack;
    @FXML
    private Button btn_AppraiseScreen_Popup_Appraise_OK;
    @FXML
    private Button btn_AppraiseScreen_Popup_Appraise_NOK;
    @FXML
    private Button btn_AppraiseScreen_Modify_NOK;


    HFJavaSDKBasicExample myQuery = new HFJavaSDKBasicExample();
    ObservableList<Model_DiamondData> myList;
    ArrayList<String> updateDia;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nowState = "Null";
        checked = false;
        modified = false;

        nowState_Add = "Null";
        for (int a = 0; a < 15; a++) {
            added[a] = false;
        }
    }

    public void setLoginInfo(int loginInfo) {
        this.loginInfo = loginInfo;
    }

    // TODO : 다이아 감정의 선택 창에서 일어나는 Action들
    @FXML
    public void appraiseScreen_Select_Update() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Update.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);

            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) btn_AppraiseScreen_Update.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("기존 다이아 업데이트");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Select_Add() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Add.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);
            controller.setupNullData();

            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) btn_AppraiseScreen_Update.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("신규 다이아 등록");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Select_ReturnBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Appraise.fxml"));
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);

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


    // TODO : 기존 다이아 업데이트 창에서 일어나는 Action들
    @FXML
    public void appraiseScreen_Update_Modify() {
        if (!checked) {
            // 다이아가 조회되지 않음
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Popup_Modify_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_AppraiseScreen_Modify_NOK.setText("※다이아를 조회해주세요.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("수정 실패");

                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (nowState.equals("Null")) {
            // 메뉴가 선택되지 않음
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Popup_Modify_NOK.fxml"));
                Parent parent = null;

                parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_AppraiseScreen_Modify_NOK.setText("※수정할 항목을 선택해주세요.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("수정 실패");

                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (text_Appraise_ModifyData.getText().equals("")) {
            // 수정할 사항이 입력되지 않음.
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Popup_Modify_NOK.fxml"));
                Parent parent = null;

                parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_AppraiseScreen_Modify_NOK.setText("※수정할 내용을 입력해주세요.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("수정 실패");

                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            modified = true;
            switch (nowState) {
                case "Number":
                    myList.remove(3);
                    myList.add(3, new Model_DiamondData(new SimpleStringProperty(nowState), new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Shape & Cut":
                    myList.remove(4);
                    myList.add(4, new Model_DiamondData(new SimpleStringProperty(nowState), new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Min Radius":
                    myList.remove(5);
                    myList.add(5, new Model_DiamondData(new SimpleStringProperty(nowState), new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Max Radius":
                    myList.remove(6);
                    myList.add(6, new Model_DiamondData(new SimpleStringProperty(nowState), new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Height":
                    myList.remove(7);
                    myList.add(7, new Model_DiamondData(new SimpleStringProperty(nowState), new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Carat":
                    myList.remove(8);
                    myList.add(8, new Model_DiamondData(new SimpleStringProperty(nowState), new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Color":
                    myList.remove(9);
                    myList.add(9, new Model_DiamondData(new SimpleStringProperty(nowState), new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Clarity":
                    myList.remove(10);
                    myList.add(10, new Model_DiamondData(new SimpleStringProperty(nowState), new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Cut":
                    myList.remove(11);
                    myList.add(11, new Model_DiamondData(new SimpleStringProperty(nowState), new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Table Size":
                    myList.remove(12);
                    myList.add(12, new Model_DiamondData(new SimpleStringProperty(nowState), new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Total Depth":
                    myList.remove(13);
                    myList.add(13, new Model_DiamondData(new SimpleStringProperty(nowState), new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Min Girdle":
                    myList.remove(14);
                    myList.add(14, new Model_DiamondData(new SimpleStringProperty(nowState), new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                case "Max Girdle":
                    myList.remove(15);
                    myList.add(15, new Model_DiamondData(new SimpleStringProperty(nowState), new SimpleStringProperty(text_Appraise_ModifyData.getText())));
                    break;
                default:
                    break;
            }

            myTableView.setItems(myList);
        }
    }

    @FXML
    public void appraiseScreen_Update_ForDemo(){
        text_Appraise_LookupDiamond.setText(randomDiamond);
    }

    @FXML
    public void appraiseScreen_Update_Update() {
        try {
            if (modified) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Popup_Appraise_OK.fxml"));
                Parent parent = loader.load();

                Controller_Appraise controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyQuery(myQuery,randomDiamond);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) btn_AppraiseScreen_Update_ReturnBack.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("다이아 업데이트 완료");

                primaryStage.show();

                myQuery.updateDiamond(myList.get(1).getData().getValue(),myList.get(2).getData().getValue(),myList.get(3).getData().getValue(),
                        myList.get(4).getData().getValue(), myList.get(5).getData().getValue(), myList.get(6).getData().getValue(),
                        myList.get(7).getData().getValue(), myList.get(8).getData().getValue(), myList.get(9).getData().getValue(),
                        myList.get(10).getData().getValue(), myList.get(11).getData().getValue(), myList.get(12).getData().getValue(),
                        myList.get(13).getData().getValue(), myList.get(14).getData().getValue(), myList.get(15).getData().getValue(),
                        myList.get(16).getData().getValue());
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Popup_Appraise_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

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
    public void appraiseScreen_Update_ReturnBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Select.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);

            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) btn_AppraiseScreen_Update_ReturnBack.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("다이아 감정");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Update_DiaCheck() {
        try {
            if (myQuery.checkDia_Lookup(text_Appraise_LookupDiamond.getText())) {
                Parent deal = FXMLLoader.load(getClass().getClassLoader().getResource("LookupScreen_Popup_OK.fxml"));
                Scene scene = new Scene(deal);

                Stage primaryStage = new Stage();

                primaryStage.setScene(scene);
                primaryStage.setTitle("조회 완료");
                primaryStage.show();

                checked = true;

                listColumn.setCellValueFactory(cellData -> cellData.getValue().getList());
                dataColumn.setCellValueFactory(cellData -> cellData.getValue().getData());

                updateDia = myQuery.getDiamondData(text_Appraise_LookupDiamond.getText());
                myList = FXCollections.observableArrayList(
                        new Model_DiamondData(new SimpleStringProperty("Date"), new SimpleStringProperty(updateDia.get(0))),
                        new Model_DiamondData(new SimpleStringProperty("User Name"), new SimpleStringProperty(updateDia.get(1))),
                        new Model_DiamondData(new SimpleStringProperty("User ID"), new SimpleStringProperty(updateDia.get(2))),
                        new Model_DiamondData(new SimpleStringProperty("Number"), new SimpleStringProperty(updateDia.get(3))),
                        new Model_DiamondData(new SimpleStringProperty("Shape & Cut"), new SimpleStringProperty(updateDia.get(4))),
                        new Model_DiamondData(new SimpleStringProperty("Min Radius"), new SimpleStringProperty(updateDia.get(5))),
                        new Model_DiamondData(new SimpleStringProperty("Max Radius"), new SimpleStringProperty(updateDia.get(6))),
                        new Model_DiamondData(new SimpleStringProperty("Height"), new SimpleStringProperty(updateDia.get(7))),
                        new Model_DiamondData(new SimpleStringProperty("Carat"), new SimpleStringProperty(updateDia.get(8))),
                        new Model_DiamondData(new SimpleStringProperty("Color"), new SimpleStringProperty(updateDia.get(9))),
                        new Model_DiamondData(new SimpleStringProperty("Clarity"), new SimpleStringProperty(updateDia.get(10))),
                        new Model_DiamondData(new SimpleStringProperty("Cut"), new SimpleStringProperty(updateDia.get(11))),
                        new Model_DiamondData(new SimpleStringProperty("Table Size"), new SimpleStringProperty(updateDia.get(12))),
                        new Model_DiamondData(new SimpleStringProperty("Total Depth"), new SimpleStringProperty(updateDia.get(13))),
                        new Model_DiamondData(new SimpleStringProperty("Min Girdle"), new SimpleStringProperty(updateDia.get(14))),
                        new Model_DiamondData(new SimpleStringProperty("Max Girdle"), new SimpleStringProperty(updateDia.get(15))),
                        new Model_DiamondData(new SimpleStringProperty("Laser Inscription"), new SimpleStringProperty(updateDia.get(16))),
                        new Model_DiamondData(new SimpleStringProperty("Check Theft"), new SimpleStringProperty(updateDia.get(17)))
                );
                myTableView.setItems(myList);
            } else {
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


    // TODO : 새로운 다이아 감정 창에서 일어나는 Action들
    public void setupNullData() {
        listColumn.setCellValueFactory(cellData -> cellData.getValue().getList());
        dataColumn.setCellValueFactory(cellData -> cellData.getValue().getData());

        myList = FXCollections.observableArrayList(
                new Model_DiamondData(new SimpleStringProperty("Date"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("User Name"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("User ID"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Number"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Shape & Cut"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Min Radius"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Max Radius"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Height"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Carat"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Color"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Clarity"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Cut"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Table Size"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Total Depth"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Min Girdle"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Max Girdle"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Laser Inscription"), new SimpleStringProperty("")),
                new Model_DiamondData(new SimpleStringProperty("Check Theft"), new SimpleStringProperty(""))
        );
        myTableView.setItems(myList);

    }

    @FXML
    public void appraiseScreen_Add_Modify() {
        if (nowState_Add.equals("Null")) {
            // 메뉴가 선택되지 않음
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Popup_Modify_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_AppraiseScreen_Modify_NOK.setText("※등록할 항목을 선택해주세요.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("정보 등록 실패");

                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (text_Appraise_AddData.getText().equals("")) {
            // 수정할 사항이 입력되지 않음.
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Popup_Modify_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_AppraiseScreen_Modify_NOK.setText("※등록할 내용을 입력해주세요.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("정보 등록 실패");

                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (nowState_Add.equals("Laser Inscription") && myQuery.checkDia_Lookup(text_Appraise_AddData.getText())) {
            // 다이아 번호 중복
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Popup_Modify_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Controller_Appraise controller = loader.getController();
                controller.text_AppraiseScreen_Modify_NOK.setText("※중복된 다이아 일련번호입니다.");

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("정보 등록 실패");

                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            switch (nowState_Add) {
                case "Date":
                    added[0] = true;
                    myList.remove(0);
                    myList.add(0, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Number":
                    added[1] = true;
                    myList.remove(3);
                    myList.add(3, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Shape & Cut":
                    added[2] = true;
                    myList.remove(4);
                    myList.add(4, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Min Radius":
                    added[3] = true;
                    myList.remove(5);
                    myList.add(5, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Max Radius":
                    added[4] = true;
                    myList.remove(6);
                    myList.add(6, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Height":
                    added[5] = true;
                    myList.remove(7);
                    myList.add(7, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Carat":
                    added[6] = true;
                    myList.remove(8);
                    myList.add(8, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Color":
                    added[7] = true;
                    myList.remove(9);
                    myList.add(9, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Clarity":
                    added[8] = true;
                    myList.remove(10);
                    myList.add(10, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Cut":
                    added[9] = true;
                    myList.remove(11);
                    myList.add(11, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Table Size":
                    added[10] = true;
                    myList.remove(12);
                    myList.add(12, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Total Depth":
                    added[11] = true;
                    myList.remove(13);
                    myList.add(13, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Min Girdle":
                    added[12] = true;
                    myList.remove(14);
                    myList.add(14, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Max Girdle":
                    added[13] = true;
                    myList.remove(15);
                    myList.add(15, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                case "Laser Inscription":
                    added[14] = true;
                    myList.remove(16);
                    myList.add(16, new Model_DiamondData(new SimpleStringProperty(nowState_Add), new SimpleStringProperty(text_Appraise_AddData.getText())));
                    break;
                default:
                    break;
            }

            myTableView.setItems(myList);
        }
    }

    @FXML
    public void appraiseScreen_Add_ReturnBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Select.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);

            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) btn_AppraiseScreen_Add_ReturnBack.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("다이아 감정");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Add_Add() {
        int count = 0;
        for (int a = 0; a < 15; a++) {
            if (added[a]) {
                count++;
            }
        }
        try {
            if (count == 15) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Add_RegisterOwner.fxml"));
                Parent parent = loader.load();

                Controller_Appraise controller = loader.getController();
                controller.setLoginInfo(loginInfo);
                controller.setMyQuery(myQuery,randomDiamond);
                controller.setMyList(myList);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) btn_AppraiseScreen_Add_ReturnBack.getScene().getWindow();
                primaryStage.close();

                primaryStage.setScene(scene);
                primaryStage.setTitle("소유자 정보 입력");

                primaryStage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Popup_Appraise_NOK.fxml"));
                Parent parent = loader.load();

                Stage primaryStage = new Stage();

                Scene scene = new Scene(parent);

                primaryStage.setScene(scene);
                primaryStage.setTitle("등록 실패");

                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Add_ForDemo() {
        myList.remove(0);
        myList.add(0, new Model_DiamondData(new SimpleStringProperty("Date"), new SimpleStringProperty("2018/06/01")));
        myList.remove(3);
        myList.add(3, new Model_DiamondData(new SimpleStringProperty("Number"), new SimpleStringProperty("80020010")));
        myList.remove(4);
        myList.add(4, new Model_DiamondData(new SimpleStringProperty("Shape & Cut"), new SimpleStringProperty("Princess")));
        myList.remove(5);
        myList.add(5, new Model_DiamondData(new SimpleStringProperty("Min Radius"), new SimpleStringProperty("6.52")));
        myList.remove(6);
        myList.add(6, new Model_DiamondData(new SimpleStringProperty("Max Radius"), new SimpleStringProperty("6.53")));
        myList.remove(7);
        myList.add(7, new Model_DiamondData(new SimpleStringProperty("Height"), new SimpleStringProperty("1.00")));
        myList.remove(8);
        myList.add(8, new Model_DiamondData(new SimpleStringProperty("Carat"), new SimpleStringProperty("1.03")));
        myList.remove(9);
        myList.add(9, new Model_DiamondData(new SimpleStringProperty("Color"), new SimpleStringProperty("E")));
        myList.remove(10);
        myList.add(10, new Model_DiamondData(new SimpleStringProperty("Clarity"), new SimpleStringProperty("VVS1")));
        myList.remove(11);
        myList.add(11, new Model_DiamondData(new SimpleStringProperty("Cut"), new SimpleStringProperty("Excellent")));
        myList.remove(12);
        myList.add(12, new Model_DiamondData(new SimpleStringProperty("Table Size"), new SimpleStringProperty("64")));
        myList.remove(13);
        myList.add(13, new Model_DiamondData(new SimpleStringProperty("Total Depth"), new SimpleStringProperty("66.4")));
        myList.remove(14);
        myList.add(14, new Model_DiamondData(new SimpleStringProperty("Min Girdle"), new SimpleStringProperty("Thin")));
        myList.remove(15);
        myList.add(15, new Model_DiamondData(new SimpleStringProperty("Max Girdle"), new SimpleStringProperty("Medium")));
        myList.remove(16);
        myList.add(16, new Model_DiamondData(new SimpleStringProperty("Laser Inscription"), new SimpleStringProperty(randomDiamond)));

        for(int a=0;a<15;a++){
            added[a]=true;
        }

        myTableView.setItems(myList);
    }


    // TODO : 새로운 다이아 감정에서 입력 이후 새로운 사용자 입력을 위한 화면의 Action들
    public void setupData(ObservableList<Model_DiamondData> myList) {
        listColumn.setCellValueFactory(cellData -> cellData.getValue().getList());
        dataColumn.setCellValueFactory(cellData -> cellData.getValue().getData());

        this.myList = myList;
        myTableView.setItems(myList);

        for (int a = 0; a < 15; a++) {
            added[a] = true;
        }
    }

    public void setMyList(ObservableList<Model_DiamondData> myList) {
        this.myList = myList;
    }

    @FXML
    public void appraiseScreen_Add_RegisterOwner_ReturnBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Add.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);
            controller.setupData(myList);

            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) textfield_AppraiseScreen_Add_RegisterOwner_NewOwnerID.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("신규 다이아 등록");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Add_RegisterOwner_Register() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Add_CheckNewOwner.fxml"));
            Parent parent = loader.load();

            myList.remove(1);
            myList.add(1, new Model_DiamondData(new SimpleStringProperty("User Name"), new SimpleStringProperty(textfield_AppraiseScreen_Add_RegisterOwner_NewOwnerName.getText())));

            myList.remove(2);
            myList.add(2, new Model_DiamondData(new SimpleStringProperty("User ID"), new SimpleStringProperty(textfield_AppraiseScreen_Add_RegisterOwner_NewOwnerID.getText())));

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);
            controller.setMyList(myList);
            controller.setNewOwner(myList.get(2).getData().getValue(), myList.get(1).getData().getValue());

            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) textfield_AppraiseScreen_Add_RegisterOwner_NewOwnerID.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("새 다이아 소유자 정보 확인");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Add_RegisterOwner_ForDemo(){
        textfield_AppraiseScreen_Add_RegisterOwner_NewOwnerID.setText("9606301478963");
        textfield_AppraiseScreen_Add_RegisterOwner_NewOwnerName.setText("Jang GunHee");
    }


    // TODO : 새로운 사용자 입력을 받은 후, 체크를 하기 위한 화면에서의 Action들
    public void setNewOwner(String id, String name) {
        text_AppraiseScreen_Add_CheckNewOwner_NewID.setText(id);
        text_AppraiseScreen_Add_CheckNewOwner_NewName.setText(name);
    }

    @FXML
    public void appraiseScreen_Add_CheckNewOwner_ReturnBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Add_RegisterOwner.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);
            controller.setMyList(myList);

            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) text_AppraiseScreen_Add_CheckNewOwner_NewID.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("소유자 정보 입력");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Add_CheckNewOwner_OK() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppraiseScreen_Add_Complete.fxml"));
            Parent parent = loader.load();

            Controller_Appraise controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);
            controller.setFinalData(myList.get(2).getData().getValue(), myList.get(1).getData().getValue(), myList.get(16).getData().getValue());

            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) text_AppraiseScreen_Add_CheckNewOwner_NewID.getScene().getWindow();
            primaryStage.close();

            primaryStage.setScene(scene);
            primaryStage.setTitle("신규 다이아 등록 완료");

            primaryStage.show();

            myQuery.setNewDiamond(myList.get(1).getData().getValue(), myList.get(2).getData().getValue(), myList.get(3).getData().getValue(), myList.get(4).getData().getValue(), myList.get(5).getData().getValue(),
                    myList.get(6).getData().getValue(), myList.get(7).getData().getValue(), myList.get(8).getData().getValue(), myList.get(9).getData().getValue(),
                    myList.get(10).getData().getValue(), myList.get(11).getData().getValue(), myList.get(12).getData().getValue(), myList.get(13).getData().getValue(),
                    myList.get(14).getData().getValue(), myList.get(15).getData().getValue(), myList.get(16).getData().getValue(), myList.get(0).getData().getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // TODO : 새로운 다이아 등록 마무리 화면
    public void setFinalData(String newID, String newName, String diamond) {
        text_AppraiseScreen_Add_Complete_NewID.setText(newID);
        text_AppraiseScreen_Add_Complete_NewName.setText(newName);
        text_AppraiseScreen_Add_Complete_Diamond.setText(diamond);
    }

    @FXML
    public void appraiseScreen_Add_Complete_OK() {
        try {
            if (loginInfo == 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Appraise.fxml"));
                Parent parent = loader.load();

                Controller controller = loader.getController();
                controller.setMyQuery(myQuery,randomDiamond);
                controller.setLoginInfo(loginInfo);

                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) text_AppraiseScreen_Add_Complete_NewID.getScene().getWindow();
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
                Stage primaryStage = (Stage) text_AppraiseScreen_Add_Complete_NewID.getScene().getWindow();
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
    public void appraiseScreen_Popup_Appraise_OK_OK() {
        Stage primaryStage = (Stage) btn_AppraiseScreen_Popup_Appraise_OK.getScene().getWindow();
        primaryStage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen_Appraise.fxml"));     // 어짜피 다이아 감정은 감정원밖에 못하니까.
            Parent parent = loader.load();

            Controller controller = loader.getController();
            controller.setLoginInfo(loginInfo);
            controller.setMyQuery(myQuery,randomDiamond);

            Scene scene = new Scene(parent);

            primaryStage.setScene(scene);
            primaryStage.setTitle("<감정원> 메인화면");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appraiseScreen_Popup_Appraise_NOK_OK() {
        Stage primaryStage = (Stage) btn_AppraiseScreen_Popup_Appraise_NOK.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    public void appraiseScreen_Modify_NOK_OK() {
        Stage primaryStage = (Stage) btn_AppraiseScreen_Modify_NOK.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    public void update_menuitem1() {
        nowState = "Number";
        menu_Appraise_Update.setText(nowState);
    }

    @FXML
    public void update_menuitem2() {
        nowState = "Shape & Cut";
        menu_Appraise_Update.setText(nowState);
    }

    @FXML
    public void update_menuitem3() {
        nowState = "Min Radius";
        menu_Appraise_Update.setText(nowState);
    }

    @FXML
    public void update_menuitem4() {
        nowState = "Max Radius";
        menu_Appraise_Update.setText(nowState);
    }

    @FXML
    public void update_menuitem5() {
        nowState = "Height";
        menu_Appraise_Update.setText(nowState);
    }

    @FXML
    public void update_menuitem6() {
        nowState = "Carat";
        menu_Appraise_Update.setText(nowState);
    }

    @FXML
    public void update_menuitem7() {
        nowState = "Color";
        menu_Appraise_Update.setText(nowState);
    }

    @FXML
    public void update_menuitem8() {
        nowState = "Clarity";
        menu_Appraise_Update.setText(nowState);
    }

    @FXML
    public void update_menuitem9() {
        nowState = "Cut";
        menu_Appraise_Update.setText(nowState);
    }

    @FXML
    public void update_menuitem10() {
        nowState = "Table Size";
        menu_Appraise_Update.setText(nowState);
    }

    @FXML
    public void update_menuitem11() {
        nowState = "Total Depth";
        menu_Appraise_Update.setText(nowState);
    }

    @FXML
    public void update_menuitem12() {
        nowState = "Min Girdle";
        menu_Appraise_Update.setText(nowState);
    }

    @FXML
    public void update_menuitem13() {
        nowState = "Max Girdle";
        menu_Appraise_Update.setText(nowState);
    }


    @FXML
    public void add_menuitem1() {
        nowState_Add = "Date";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem2() {
        nowState_Add = "Number";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem3() {
        nowState_Add = "Shape & Cut";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem4() {
        nowState_Add = "Min Radius";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem5() {
        nowState_Add = "Max Radius";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem6() {
        nowState_Add = "Height";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem7() {
        nowState_Add = "Carat";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem8() {
        nowState_Add = "Color";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem9() {
        nowState_Add = "Clarity";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem10() {
        nowState_Add = "Cut";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem11() {
        nowState_Add = "Table Size";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem12() {
        nowState_Add = "Total Depth";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem13() {
        nowState_Add = "Min Girdle";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem14() {
        nowState_Add = "Max Girdle";
        menu_Appraise_Add.setText(nowState_Add);
    }

    @FXML
    public void add_menuitem15() {
        nowState_Add = "Laser Inscription";
        menu_Appraise_Add.setText(nowState_Add);
    }

    public void setMyQuery(HFJavaSDKBasicExample myQuery,String randomDiamond) {
        this.myQuery = myQuery;
        this.randomDiamond = randomDiamond;
    }

}
