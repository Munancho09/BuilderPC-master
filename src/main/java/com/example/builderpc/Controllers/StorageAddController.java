package com.example.builderpc.Controllers;

import com.example.builderpc.Classes.RAM;
import com.example.builderpc.Classes.Storage;
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

public class StorageAddController implements Initializable {
    public ComboBox<String> cmbManufacturer = new ComboBox<>();
    public Label errors;
    public TextField txtTitle;
    public ComboBox<Integer> cmbVolume = new ComboBox<>();
    public ComboBox<String> cmbType = new ComboBox<>();
    public ComboBox<Integer> cmbWrite = new ComboBox<>();
    public ComboBox<Integer> cmbRead = new ComboBox<>();
    public Button btnAdd;
    public Button btnClose;
    public Button btnGen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
    void init(){
        ObservableList<String> manufacturer = FXCollections.observableArrayList();
        manufacturer.add("Western Digital");
        manufacturer.add("Seagate");
        manufacturer.add("Hitachi HGST");
        manufacturer.add("Toshiba");
        manufacturer.add("Samsung ");
        cmbManufacturer.setItems(manufacturer);
        cmbManufacturer.setValue(manufacturer.get(0));

        ObservableList<String> type = FXCollections.observableArrayList();
        type.add("HHD");
        type.add("SSD");
        type.add("M2");
        cmbType.setItems(type);
        cmbType.setValue(type.get(0));

        ObservableList<Integer> volume = FXCollections.observableArrayList();
        for(int i = 128; i <= 8192; i*=2){
            volume.add(i);
        }
        cmbVolume.setItems(volume);
        cmbVolume.setValue(volume.get(0));

        ObservableList<Integer> write = FXCollections.observableArrayList();
        ObservableList<Integer> read = FXCollections.observableArrayList();
        for(int i = 50; i <=300; i+=50){
            read.add(i);
        }
        for(int i = 10; i <= 60; i+=10){
            write.add(i);
        }
        cmbRead.setItems(read);
        cmbRead.setValue(read.get(0));
        cmbWrite.setItems(write);
        cmbWrite.setValue(write.get(0));
    }

    public void btnAddClick(ActionEvent actionEvent) {
        Storage storage = new Storage();
        try {
            storage.setManufacture(cmbManufacturer.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            storage.setTitle(txtTitle.getText());
        } catch (Exception e) {
            errors.setText("Вы не ввели название");
            return;
        }
        storage.setVolume(cmbVolume.getValue());
        storage.setType(cmbType.getValue());
        storage.setSpeedOfWrite(cmbWrite.getValue());
        storage.setSpeedOfRead(cmbRead.getValue());
        DataBase.addStorage(storage);
        errors.setText("Память успешно добавлена");
        txtTitle.setText("");
    }

    public void btnCloseClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnAdd.getScene().getWindow();
        DataBase.createDataBase();
        DataBase.createTable();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        totalStage.setTitle("Главная страница");
        totalStage.setScene(scene);
        totalStage.show();
    }

    public void cmbTypeSwitch(ActionEvent actionEvent) {
        ObservableList<Integer> write = FXCollections.observableArrayList();
        ObservableList<Integer> read = FXCollections.observableArrayList();
        switch (cmbType.getValue()){
            case "HHD":{
                for(int i = 50; i <=300; i+=50){
                    read.add(i);
                }
                for(int i = 10; i <= 60; i+=10){
                    write.add(i);
                }
            }
            break;
            case "SSD":{
                for(int i = 100; i <= 550; i+=50){
                    write.add(i);
                    read.add(i);
                }
            }
            break;
            case "M2":{
                for(int i = 300; i <=1800 ; i+=100){
                    read.add(i);
                }
                for(int i = 400; i <= 2400; i+=200){
                    write.add(i);
                }
            }
            break;
        }
        cmbRead.setItems(read);
        cmbRead.setValue(read.get(0));
        cmbWrite.setItems(write);
        cmbWrite.setValue(write.get(0));
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
            Storage storage = new Storage();
            storage.setTitle(title);
            storage.setManufacture(cmbManufacturer.getItems().get(new Random().nextInt(cmbManufacturer.getItems().size())));
            storage.setSpeedOfRead(cmbRead.getItems().get(new Random().nextInt(cmbRead.getItems().size())));
            storage.setVolume(cmbVolume.getItems().get(new Random().nextInt(cmbVolume.getItems().size())));
            storage.setType(cmbType.getItems().get(new Random().nextInt(cmbType.getItems().size())));
            storage.setSpeedOfWrite(cmbWrite.getItems().get(new Random().nextInt(cmbWrite.getItems().size())));
            DataBase.addStorage(storage);
        }
        errors.setText("ПЗУ сгенерированы");
    }
}
