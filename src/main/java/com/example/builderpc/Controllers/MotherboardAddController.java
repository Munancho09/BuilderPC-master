package com.example.builderpc.Controllers;

import com.example.builderpc.Classes.CPU;
import com.example.builderpc.Classes.Motherboard;
import com.example.builderpc.Classes.PowerBlock;
import com.example.builderpc.DataBase.DataBase;
import com.example.builderpc.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MotherboardAddController implements Initializable {
    public ComboBox<String> cmbManufacturer = new ComboBox<>();
    public TextField txtTitle;
    public Label errors;
    public Button btnAdd;
    public Button btnClose;
    public ComboBox<String> cmbSocket = new ComboBox<>();
    public ComboBox<String> cmbVideoCard = new ComboBox<>();
    public ComboBox<String> cmbRAM = new ComboBox<>();
    public Button btnGen;

    public void btnAddClick(ActionEvent actionEvent) {
        Motherboard motherboard = new Motherboard();
        motherboard.setRAMtype(cmbRAM.getValue());
        try {
            motherboard.setManufacture(cmbManufacturer.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        motherboard.setSocket(cmbSocket.getValue());
        try {
            motherboard.setTitle(txtTitle.getText());
        } catch (Exception e) {
            errors.setText("Вы не ввели название");
            return;
        }
        motherboard.setGCPUtype(cmbVideoCard.getValue());
        DataBase.addMotherboard(motherboard);
        errors.setText("Материнская плата добавлена");
        txtTitle.setText("");
    }

    public void btnCloseClose(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnAdd.getScene().getWindow();
        DataBase.createDataBase();
        DataBase.createTable();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        totalStage.setTitle("Главная страница");
        totalStage.setScene(scene);
        totalStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
    void init(){
        ObservableList<String> manufacturer = FXCollections.observableArrayList();
        manufacturer.add("ASRock");
        manufacturer.add("MSI");
        manufacturer.add("ASUS");
        manufacturer.add("Gigabyte");
        cmbManufacturer.setItems(manufacturer);
        cmbManufacturer.setValue(manufacturer.get(0));

        ObservableList<String> socket = FXCollections.observableArrayList();
        socket.add("Socket 8");
        socket.add("Socket 370");
        socket.add("Socket 423");
        socket.add("Socket 478");
        socket.add("LGA 775");
        socket.add("LGA 771");
        socket.add("LGA 1366");
        socket.add("LGA 1156");
        socket.add("LGA 1567");
        socket.add("LGA 1155");
        socket.add("LGA 2011");
        socket.add("LGA 1356");
        socket.add("LGA 1150");
        socket.add("LGA2011-3");
        socket.add("LGA 1151");
        socket.add("LGA 3647");
        socket.add("LGA 2066");
        socket.add("LGA 1200");
        socket.add("LGA 1700");
        socket.add("Super Socket 7");
        socket.add("Slot A");
        socket.add("Socket A");
        socket.add("Socket 754");
        socket.add("Socket 940");
        socket.add("Socket 939");
        socket.add("Socket S1");
        socket.add("Socket AM2");
        socket.add("Socket F");
        socket.add("Socket AM2+");
        socket.add("Socket AM3");
        socket.add("Socket G34");
        socket.add("Socket C32");
        socket.add("Socket AM3+");
        socket.add("Socket FM1");
        socket.add("Socket FS1");
        socket.add("Socket FM2");
        socket.add("Socket FM2+");
        socket.add("Socket AM1");
        socket.add("АМ4");
        socket.add("Socket SP3");
        socket.add("Socket TR4");
        socket.add("Socket sTRX4");
        cmbSocket.setItems(socket);
        cmbSocket.setValue(socket.get(0));

        ObservableList<String> GSPU = FXCollections.observableArrayList();
        GSPU.add("GeForce series");
        for(int i = 2; i < 10; i++){
            GSPU.add(String.format("GeForce %s series", i));
        }
        for(int i = 100; i <= 900; i+=100){
            GSPU.add(String.format("GeForce %s series", i));
        }
        GSPU.add("GeForce 10 series");
        GSPU.add("GeForce 16 series");
        GSPU.add("GeForce 20 series");
        for(int i = 100; i <= 800; i+=100){
            GSPU.add(String.format("Radeon R%s", i));
        }
        cmbVideoCard.setItems(GSPU);
        cmbVideoCard.setValue(GSPU.get(0));

        ObservableList<String> type = FXCollections.observableArrayList();
        for (int i = 1; i <= 4; i++ ){
            type.add(String.format("DDR%s", i));
        }
        cmbRAM.setItems(type);
        cmbRAM.setValue(type.get(0));
    }

    public void btnGenClick(ActionEvent actionEvent) throws Exception {
        char[] charsArray = new char[33];
        int[] numberArray = new int[10];
        int j = 0;
        for (var i = 'а'; i <= 'я'; i++){
            charsArray[j] = i;
            j++;
        }
        for(var i = 0; i <= 9; i++){
            numberArray[i] = i;
        }
        for(int k = 0; k <10; k++) {
            String title = "";
            int count = 2 + new Random().nextInt(8);
            for (int i = 0; i < count; i++) {
                if (new Random().nextInt(10) % 2 == 0) {
                    title += charsArray[new Random().nextInt(charsArray.length)];
                } else {
                    title += numberArray[new Random().nextInt(numberArray.length)];
                }
            }
            Motherboard motherboard = new Motherboard();
            motherboard.setTitle(title);
            motherboard.setManufacture(cmbManufacturer.getItems().get(new Random().nextInt(cmbManufacturer.getItems().size())));
            motherboard.setGCPUtype(cmbVideoCard.getItems().get(new Random().nextInt(cmbVideoCard.getItems().size())));
            motherboard.setSocket(cmbSocket.getItems().get(new Random().nextInt(cmbSocket.getItems().size())));
            motherboard.setRAMtype(cmbRAM.getItems().get(new Random().nextInt(cmbRAM.getItems().size())));
            DataBase.addMotherboard(motherboard);
        }
        errors.setText("Материнские платы сгенерированы");
    }
}
