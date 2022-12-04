package com.example.builderpc.Controllers;

import com.example.builderpc.Classes.CPU;
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

public class CPUAddController implements Initializable {
    public ComboBox<String> cmbManufacture = new ComboBox<>();
    public ComboBox<String> cmbSocket = new ComboBox<>();
    public ComboBox<String> cmbArchetype = new ComboBox<>();
    public Button btnAdd;
    public Button btnClose;
    public Label response;
    public ComboBox<Float> cmbFrequency = new ComboBox<>();
    public ComboBox<Integer> cmbPower = new ComboBox<>();
    public TextField txtTitle;
    public Button btnGen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCombos();
    }
    void initCombos(){
        ObservableList<String> manufacture = FXCollections.observableArrayList();
        manufacture.add("Intel");
        manufacture.add("AMD");
        cmbManufacture.setItems(manufacture);
        cmbManufacture.setValue(manufacture.get(0));

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
        cmbSocket.setItems(socket);
        cmbSocket.setValue(socket.get(0));

        ObservableList<String> archetype = FXCollections.observableArrayList();
        archetype.add("Nehalem");
        archetype.add("Westmere");
        archetype.add("Sandy Bridge");
        archetype.add("Ivy Bridge");
        archetype.add("Haswell");
        archetype.add("Broadwell");
        archetype.add("Skylake");
        archetype.add("Kaby Lake");
        archetype.add("Coffee Lake");
        archetype.add("Coffee Lake Refresh");
        cmbArchetype.setItems(archetype);
        cmbArchetype.setValue(archetype.get(0));

        ObservableList<Integer> powers = FXCollections.observableArrayList();
        for (int i = 15; i <= 150; i+=15){
            powers.add(i);
        }
        cmbPower.setItems(powers);
        cmbPower.setValue(powers.get(0));

        ObservableList<Float> frequency = FXCollections.observableArrayList();
        for(float i = 1.0f; i <= 4.0f; i+=0.5f){
            frequency.add(i);
        }
        cmbFrequency.setItems(frequency);
        cmbFrequency.setValue(frequency.get(0));
    }

    public void btnAddClick(ActionEvent actionEvent) {
        var cpu = new CPU();
        try {
            cpu.setArchetype(cmbArchetype.getValue());
            cpu.setFrequency(cmbFrequency.getValue());
            cpu.setPower(cmbPower.getValue());
            cpu.setManufacture(cmbManufacture.getValue());
            cpu.setSocket(cmbSocket.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cpu.setTitle(txtTitle.getText());
        }
        catch (Exception ex){
            response.setText(ex.getMessage());
            return;
        }
        DataBase.addCPU(cpu);
        txtTitle.setText("");
        response.setText("Процессор успешно добавлен");
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

    public void cmbManufactureSwitch(ActionEvent actionEvent) {
        ObservableList<String> socket = FXCollections.observableArrayList();
        ObservableList<String> archetype = FXCollections.observableArrayList();
        switch (cmbManufacture.getValue()){
            case "Intel":{
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

                archetype.add("Nehalem");
                archetype.add("Westmere");
                archetype.add("Sandy Bridge");
                archetype.add("Ivy Bridge");
                archetype.add("Haswell");
                archetype.add("Broadwell");
                archetype.add("Skylake");
                archetype.add("Kaby Lake");
                archetype.add("Coffee Lake");
                archetype.add("Coffee Lake Refresh");
            }
            break;
            case "AMD":{
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

                archetype.add("Dali");
                archetype.add("Zen 2");
                archetype.add("Zen 3");
                archetype.add("Raven Ridge");
                archetype.add("Zen");
                archetype.add("Bristol Ridge");
                archetype.add("Seattle");
                archetype.add("Merlin Falcon");
                archetype.add("Kyoto");
                archetype.add("Kabini");
            }
            break;
        }
        cmbSocket.setItems(socket);
        cmbSocket.setValue(socket.get(0));

        cmbArchetype.setItems(archetype);
        cmbArchetype.setValue(archetype.get(0));
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
            CPU cpu = new CPU();
            cpu.setTitle(title);
            cpu.setManufacture(cmbManufacture.getItems().get(new Random().nextInt(cmbManufacture.getItems().size())));
            ObservableList<String> archetype = FXCollections.observableArrayList();
            archetype.add("Nehalem");
            archetype.add("Westmere");
            archetype.add("Sandy Bridge");
            archetype.add("Ivy Bridge");
            archetype.add("Haswell");
            archetype.add("Broadwell");
            archetype.add("Skylake");
            archetype.add("Kaby Lake");
            archetype.add("Coffee Lake");
            archetype.add("Coffee Lake Refresh");
            archetype.add("Dali");
            archetype.add("Zen 2");
            archetype.add("Zen 3");
            archetype.add("Raven Ridge");
            archetype.add("Zen");
            archetype.add("Bristol Ridge");
            archetype.add("Seattle");
            archetype.add("Merlin Falcon");
            archetype.add("Kyoto");
            archetype.add("Kabini");
            cpu.setArchetype(archetype.get(new Random().nextInt(archetype.size())));
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
            cpu.setSocket(socket.get(new Random().nextInt(socket.size())));
            cpu.setPower(cmbPower.getItems().get(new Random().nextInt(cmbPower.getItems().size())));
            cpu.setFrequency(cmbFrequency.getItems().get(new Random().nextInt(cmbFrequency.getItems().size())));
            DataBase.addCPU(cpu);
        }
        response.setText("Процессоры сгенерированы");
    }
}
