package com.example.builderpc.Controllers;

import com.example.builderpc.Classes.PowerBlock;
import com.example.builderpc.Classes.RAM;
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

public class RAMAddController implements Initializable {
    public ComboBox<String> cmbManufacturer = new ComboBox<>();
    public TextField txtTitle;
    public Label errors;
    public Button btnAdd;
    public Button btnClose;
    public ComboBox<Integer> cmbVolume = new ComboBox<>();
    public ComboBox<Integer> cmbFrequency = new ComboBox<>();
    public ComboBox<String> cmbType = new ComboBox<>();
    public Button btnGen;

    public void btnAddClick(ActionEvent actionEvent) {
        RAM ram = new RAM();
        ram.setVolume(cmbVolume.getValue());
        ram.setFrequency(cmbFrequency.getValue());
        ram.setTypeMemory(cmbType.getValue());
        try {
            ram.setManufacture(cmbManufacturer.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ram.setTitle(txtTitle.getText());
        } catch (Exception e) {
            errors.setText("Вы не ввели название");
            return;
        }
        DataBase.addRAM(ram);
        errors.setText("Оперативная память добавлена");
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
        manufacturer.add("Kingston");
        manufacturer.add("Hynix");
        manufacturer.add("Crucial");
        manufacturer.add("Patriot Memory");
        manufacturer.add("Samsung");
        manufacturer.add("Corsair");
        manufacturer.add("G.Skill");
        manufacturer.add("Apacer");
        manufacturer.add("GoodRAM");
        manufacturer.add("Silicon Power");
        cmbManufacturer.setItems(manufacturer);
        cmbManufacturer.setValue(manufacturer.get(0));

        ObservableList<String> type = FXCollections.observableArrayList();
        for (int i = 1; i <= 4; i++ ){
            type.add(String.format("DDR%s", i));
        }
        cmbType.setItems(type);
        cmbType.setValue(type.get(0));

        ObservableList<Integer> frequency = FXCollections.observableArrayList();
        for(int i = 200; i <= 400; i+=50){
            frequency.add(i);
        }
        cmbFrequency.setItems(frequency);
        cmbFrequency.setValue(frequency.get(0));

        ObservableList<Integer> volume = FXCollections.observableArrayList();
        for(int i = 512; i <= 16384; i*=2){
            volume.add(i);
        }
        cmbVolume.setItems(volume);
        cmbVolume.setValue(volume.get(0));
    }

    public void cmbTypeSwitch(ActionEvent actionEvent) {
        ObservableList<Integer> frequency = FXCollections.observableArrayList();
        int from = 0;
        int to = 0;
        int step = 0;
        switch (cmbType.getValue()){
            case "DDR1":{
                from = 200;
                to = 400;
                step = 50;
            }
            break;
            case "DDR2":{
                from = 500;
                to = 1200;
                step = 100;
            }
            break;
            case "DDR3":{
                from = 800;
                to = 2400;
                step = 200;
            }
            break;
            case "DDR4":{
                from = 1600;
                to = 3200;
                step = 400;
            }
            break;
        }
        for(int i = from; i <= to; i+=step){
            frequency.add(i);
        }
        cmbFrequency.setItems(frequency);
        cmbFrequency.setValue(frequency.get(0));
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
            RAM ram = new RAM();
            ram.setTitle(title);
            ram.setManufacture(cmbManufacturer.getItems().get(new Random().nextInt(cmbManufacturer.getItems().size())));
            ram.setFrequency(cmbFrequency.getItems().get(new Random().nextInt(cmbFrequency.getItems().size())));
            ram.setVolume(cmbVolume.getItems().get(new Random().nextInt(cmbVolume.getItems().size())));
            ram.setTypeMemory(cmbType.getItems().get(new Random().nextInt(cmbType.getItems().size())));
            DataBase.addRAM(ram);
        }
        errors.setText("ОЗУ сгенерированы");
    }
}
