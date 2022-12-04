package com.example.builderpc.Controllers;

import com.example.builderpc.Classes.Storage;
import com.example.builderpc.Classes.VideoCard;
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

public class VideoCardAddController implements Initializable {
    public ComboBox<String> cmbManufacturer = new ComboBox<>();
    public TextField txtTitle;
    public ComboBox<String> cmbGCPU = new ComboBox<>();
    public ComboBox<String> cmbTypeMemory = new ComboBox<>();
    public ComboBox<Integer> cmbFrequencyMemory = new ComboBox<>();
    public ComboBox<Integer> cmbPower = new ComboBox<>();
    public Button btnAdd;
    public Button btnClose;
    public Label Errors;
    public ComboBox<Integer> cmbVolumeMemory = new ComboBox<>();
    public Button btnGen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
    void init(){
        ObservableList<String> manufacturer = FXCollections.observableArrayList();
        manufacturer.add("NVIDIA");
        manufacturer.add("AMD");
        cmbManufacturer.setItems(manufacturer);
        cmbManufacturer.setValue(manufacturer.get(0));

        ObservableList<Integer> volumeMemory = FXCollections.observableArrayList();
        for(int i = 128; i <= 16384; i *= 2){
            volumeMemory.add(i);
        }
        cmbVolumeMemory.setItems(volumeMemory);
        cmbVolumeMemory.setValue(volumeMemory.get(0));

        ObservableList<String> memoryType = FXCollections.observableArrayList();
        memoryType.add("DDR");
        memoryType.add("DDR2");
        memoryType.add("GDDR3");
        memoryType.add("GDDR4");
        memoryType.add("GDDR5");
        memoryType.add("GDDR6");
        cmbTypeMemory.setItems(memoryType);
        cmbTypeMemory.setValue(memoryType.get(0));

        ObservableList<Integer> power = FXCollections.observableArrayList();
        for(int i = 40; i <= 600; i+=40){
            power.add(i);
        }
        cmbPower.setItems(power);
        cmbPower.setValue(power.get(0));

        ObservableList<Integer> FrequencyMemory = FXCollections.observableArrayList();
        for(int i = 500; i <= 5000; i+=250){
            FrequencyMemory.add(i);
        }
        cmbFrequencyMemory.setItems(FrequencyMemory);
        cmbFrequencyMemory.setValue(FrequencyMemory.get(0));

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
        cmbGCPU.setItems(GSPU);
        cmbGCPU.setValue(GSPU.get(0));
    }

    public void cmbManufacturerSwitch(ActionEvent actionEvent) {
        ObservableList<String> GSPU = FXCollections.observableArrayList();
        switch (cmbManufacturer.getValue()){
            case "NVIDIA":{
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
            }
            break;
            case "AMD":{
                for(int i = 100; i <= 800; i+=100){
                    GSPU.add(String.format("Radeon R%s", i));
                }
            }
            break;
        }
        cmbGCPU.setItems(GSPU);
        cmbGCPU.setValue(GSPU.get(0));
    }

    public void btnAddClick(ActionEvent actionEvent) {
        VideoCard vc = new VideoCard();
        vc.setVolumeMemory(cmbVolumeMemory.getValue());
        try {
            vc.setManufacture(cmbManufacturer.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        vc.setPower(cmbPower.getValue());
        try {
            vc.setGCPU(cmbGCPU.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        vc.setTypeMemory(cmbTypeMemory.getValue());
        vc.setFrequencyMemory(cmbFrequencyMemory.getValue());
        try {
            vc.setTitle(txtTitle.getText());
        } catch (Exception e) {
            Errors.setText("Вы не ввели название");
            return;
        }
        DataBase.addVideoCard(vc);
        Errors.setText("Видеокарта успешно добавлена");
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
            VideoCard videoCard = new VideoCard();
            videoCard.setTitle(title);
            videoCard.setManufacture(cmbManufacturer.getItems().get(new Random().nextInt(cmbManufacturer.getItems().size())));
            videoCard.setPower(cmbPower.getItems().get(new Random().nextInt(cmbPower.getItems().size())));
            videoCard.setFrequencyMemory(cmbFrequencyMemory.getItems().get(new Random().nextInt(cmbFrequencyMemory.getItems().size())));
            videoCard.setTypeMemory(cmbTypeMemory.getItems().get(new Random().nextInt(cmbTypeMemory.getItems().size())));
            videoCard.setVolumeMemory(cmbVolumeMemory.getItems().get(new Random().nextInt(cmbVolumeMemory.getItems().size())));
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
            videoCard.setGCPU(GSPU.get(new Random().nextInt(GSPU.size())));
            DataBase.addVideoCard(videoCard);
        }
        Errors.setText("Видеокарты сгенерированы");
    }
}
