package com.example.builderpc.Controllers;

import com.example.builderpc.Classes.*;
import com.example.builderpc.DataBase.DataBase;
import com.example.builderpc.HelloApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ComputerMainController implements Initializable {
    public Button btnClose;

    public TableView<CPU> tableCPU;
    public TableColumn<CPU, Integer> tcCPUnumber;
    public TableColumn<CPU, String> tcCPUmanufacture;
    public TableColumn<CPU, String> tcCPUtitle;
    public TableColumn<CPU, Integer> tcCPUfrequency;
    public TableColumn<CPU, Integer> tcCPUPower;
    public TableColumn<CPU, String> tcCPUsocket;
    public TableColumn<CPU, String> tcCPUarchetype;

    public TableView<VideoCard> tableVideoCard;
    public TableColumn<VideoCard, Integer> tcVideoCardId;
    public TableColumn<VideoCard, String> tcVideoCardGCPU;
    public TableColumn<VideoCard, Integer> tcVideoCardVolumeMemory;
    public TableColumn<VideoCard, String> tcVideoCardTypeMemory;
    public TableColumn<VideoCard, Float> tcVideoCardFrequencyMemory;
    public TableColumn<VideoCard, Integer> tcVideoCardPower;
    public TableColumn<VideoCard, String> tcVideoCardTitle;
    public TableColumn<VideoCard, String> tcVideoCardManufacture;

    public TableView<PowerBlock> tablePowerBlock;
    public TableColumn<PowerBlock, Integer> tcPowerBlockId;
    public TableColumn<PowerBlock, String> tcPowerBlockManufacturer;
    public TableColumn<PowerBlock, String> tcPowerBlockTitle;
    public TableColumn<PowerBlock, Integer> tcPowerBlockPower;

    public TableView<Storage> tableStorage;
    public TableColumn<Storage, Integer> tcStorageId;
    public TableColumn<Storage, String> tcStorageManufacturer;
    public TableColumn<Storage, String> tcStorageTitle;
    public TableColumn<Storage, Integer> tcStorageVolume;
    public TableColumn<Storage, String> tcStorageType;
    public TableColumn<Storage, Integer> tcStorageSteedOfWrite;
    public TableColumn<Storage, Integer> tcStorageSpeedOfRead;

    public TableView<RAM> tableRAM;
    public TableColumn<RAM, Integer> tcRAMId;
    public TableColumn<RAM, String> tcRAMManufacturer;
    public TableColumn<RAM, String> tcRAMTitle;
    public TableColumn<RAM, String> tcRAMTypeMemory;
    public TableColumn<RAM, Integer> tcRAMFrequency;
    public TableColumn<RAM, Integer> tcRAMVolume;

    public TableView<Motherboard> tableMotherboard;
    public TableColumn<Motherboard, Integer> tcMotherboardId;
    public TableColumn<Motherboard, String> tcMotherboardManufacturer;
    public TableColumn<Motherboard, String> tcMotherboardTitle;
    public TableColumn<Motherboard, String> tcMotherboardSocket;
    public TableColumn<Motherboard, String> tcMotherboardGCPUType;
    public TableColumn<Motherboard, String> tcMotherboardRAMType;
    public TextField txtCPU;
    public TextField txtVideoCard;
    public TextField txtRAM;
    public TextField txtStorage;
    public TextField txtPowerBlock;
    public TextField txtMotherboard;
    public TitledPane titCPU;
    public TitledPane titVideoCard;
    public TitledPane titRAM;
    public TitledPane titStorage;
    public TitledPane titPowerBlock;
    public TitledPane titMotherboard;
    public Button btnAdd;
    public TextField txtTitle;
    public ListView<Computer> pcListView;
    public Button btnDelete;
    public Button btnEnd;
    ObservableList<TextField> textFields = FXCollections.observableArrayList();

    void initTable(){
        initCPUTable();
        initVideoCardTable();
        initPowerBlockTable();
        initStorageTable();
        initRAMTable();
        initMotherboardTable();
        initListView();
        textFields.add(txtCPU);
        textFields.add(txtVideoCard);
        textFields.add(txtRAM);
        textFields.add(txtStorage);
        textFields.add(txtPowerBlock);
        textFields.add(txtMotherboard);
        textFields.add(txtTitle);

    }
    CPU cpuAdd = null;
    void initCPUTable(){
        //Создание таблицы процессора
        tcCPUnumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCPUmanufacture.setCellValueFactory(new PropertyValueFactory<>("manufacture"));
        tcCPUfrequency.setCellValueFactory(new PropertyValueFactory<>("frequency"));
        tcCPUPower.setCellValueFactory(new PropertyValueFactory<>("power"));
        tcCPUsocket.setCellValueFactory(new PropertyValueFactory<CPU, String>("socket"));
        tcCPUarchetype.setCellValueFactory(new PropertyValueFactory<>("archetype"));
        tcCPUtitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableCPU.setItems(DataBase.getCPU());
        TableView.TableViewSelectionModel<CPU> selectionModelCPU = tableCPU.getSelectionModel();
        selectionModelCPU.selectedItemProperty().addListener(new ChangeListener<CPU>() {
            @Override
            public void changed(ObservableValue<? extends CPU> observableValue, CPU cpu, CPU t1) {
                if(t1!=null){
                    txtCPU.setText(t1.toLineString());
                    titCPU.setExpanded(false);
                    cpuAdd = t1;
                    tableMotherboard.setItems(DataBase.getMotherboard("WHERE socket = '" + t1.getSocket()+"'"));
                    if(ramAdd != null && videoCardAdd!=null){
                        tableMotherboard.setItems(DataBase.getMotherboard(String.format("""
                                WHERE socket = '%s' AND RAMtype = '%s' AND GCPUtype = '%s'
                                """, t1.getSocket(), ramAdd.getTypeMemory(), videoCardAdd.getGCPU())));
                        return;
                    }
                    if(ramAdd != null){
                        tableMotherboard.setItems(DataBase.getMotherboard(String.format("""
                                WHERE socket = '%s' and RAMtype = '%s'
                                """, t1.getSocket(), ramAdd.getTypeMemory())));
                    }
                    if(videoCardAdd!=null){
                        tableMotherboard.setItems(DataBase.getMotherboard(String.format("""
                                WHERE socket = '%s' and GCPUtype = '%s'
                                """, t1.getSocket(), videoCardAdd.getTypeMemory())));
                    }
                    if(videoCardAdd == null) {
                        tablePowerBlock.setItems(DataBase.getPowerBlock(t1.getPower()));
                    }
                    else{
                        tablePowerBlock.setItems(DataBase.getPowerBlock(t1.getPower() + videoCardAdd.getPower()));
                    }

                }
            }
        });
    }
    void initListView(){
        pcListView.setItems(DataBase.getComputer());
        var selectionModelList = pcListView.getSelectionModel();
        selectionModelList.selectedItemProperty().addListener(new ChangeListener<Computer>() {
            @Override
            public void changed(ObservableValue<? extends Computer> observableValue, Computer computer, Computer t1) {
                if(t1!=null){
                    txtCPU.setText(t1.getCpuInfo());
                    txtMotherboard.setText(t1.getMbInfo());
                    txtPowerBlock.setText(t1.getPbInfo());
                    txtRAM.setText(t1.getRamInfo());
                    txtStorage.setText(t1.getStorageInfo());
                    txtVideoCard.setText(t1.getVcInfo());
                    computerDel = t1;
                    btnEnd.setDisable(false);
                }
            }
        });
    }

    VideoCard videoCardAdd = null;
    void initVideoCardTable(){
        tcVideoCardGCPU.setCellValueFactory(new PropertyValueFactory<>("GCPU"));
        tcVideoCardId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcVideoCardFrequencyMemory.setCellValueFactory(new PropertyValueFactory<>("FrequencyMemory"));
        tcVideoCardPower.setCellValueFactory(new PropertyValueFactory<>("power"));
        tcVideoCardManufacture.setCellValueFactory(new PropertyValueFactory<>("manufacture"));
        tcVideoCardTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcVideoCardTypeMemory.setCellValueFactory(new PropertyValueFactory<>("typeMemory"));
        tcVideoCardVolumeMemory.setCellValueFactory(new PropertyValueFactory<>("volumeMemory"));
        tableVideoCard.setItems(DataBase.getVideoCard());
        TableView.TableViewSelectionModel<VideoCard> selectionModelVideoCard = tableVideoCard.getSelectionModel();
        selectionModelVideoCard.selectedItemProperty().addListener(new ChangeListener<VideoCard>() {
            @Override
            public void changed(ObservableValue<? extends VideoCard> observableValue, VideoCard videoCard, VideoCard t1) {
                txtVideoCard.setText(t1.toLineString());
                titVideoCard.setExpanded(false);
                videoCardAdd = t1;
                tableMotherboard.setItems(DataBase.getMotherboard("WHERE GCPUtype = '" + t1.getGCPU()+"'"));
                if(ramAdd != null && cpuAdd!=null){
                    tableMotherboard.setItems(DataBase.getMotherboard(String.format("""
                                WHERE socket = '%s' AND RAMtype = '%s' AND GCPUtype = '%s'
                                """, cpuAdd.getSocket(), ramAdd.getTypeMemory(), t1.getGCPU())));
                    return;
                }
                if(ramAdd != null){
                    tableMotherboard.setItems(DataBase.getMotherboard(String.format("""
                                WHERE GCPUtype = '%s' and RAMtype = '%s'
                                """, t1.getGCPU(), ramAdd.getTypeMemory())));
                }
                if(cpuAdd!=null){
                    tableMotherboard.setItems(DataBase.getMotherboard(String.format("""
                                WHERE socket = '%s' and GCPUtype = '%s'
                                """, cpuAdd.getSocket(), t1.getGCPU())));
                }
                if(cpuAdd == null) {
                    tablePowerBlock.setItems(DataBase.getPowerBlock(t1.getPower()));
                }
                else{
                    tablePowerBlock.setItems(DataBase.getPowerBlock(t1.getPower() + cpuAdd.getPower()));
                }
            }
        });
    }
    PowerBlock powerBlockAdd = null;
    void initPowerBlockTable(){
        tcPowerBlockId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcPowerBlockPower.setCellValueFactory(new PropertyValueFactory<>("power"));
        tcPowerBlockTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcPowerBlockManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacture"));
        tablePowerBlock.setItems(DataBase.getPowerBlock());
        TableView.TableViewSelectionModel<PowerBlock> selectionModelPowerBlock = tablePowerBlock.getSelectionModel();
        selectionModelPowerBlock.selectedItemProperty().addListener(new ChangeListener<PowerBlock>() {
            @Override
            public void changed(ObservableValue<? extends PowerBlock> observableValue, PowerBlock powerBlock, PowerBlock t1) {
                if(t1 != null){
                    txtPowerBlock.setText(t1.toLineString());
                    titPowerBlock.setExpanded(false);
                    powerBlockAdd = t1;
                    if(videoCardAdd!=null && cpuAdd!=null){
                        return;
                    }
                    if(videoCardAdd!=null){
                        tableCPU.setItems(DataBase.getCPU("where power <=" + (t1.getPower()-videoCardAdd.getPower())));
                    }
                    if(cpuAdd!=null){
                        tableVideoCard.setItems(DataBase.getVideoCard("where power <=" + (t1.getPower()-cpuAdd.getPower())));
                    }
                }
            }
        });
    }
    Storage storageAdd = null;
    void initStorageTable(){
        tcStorageId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcStorageManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacture"));
        tcStorageTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcStorageType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcStorageVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        tcStorageSpeedOfRead.setCellValueFactory(new PropertyValueFactory<>("speedOfRead"));
        tcStorageSteedOfWrite.setCellValueFactory(new PropertyValueFactory<>("speedOfWrite"));
        tableStorage.setItems(DataBase.getStorage());
        TableView.TableViewSelectionModel<Storage> selectionModelStorage = tableStorage.getSelectionModel();
        selectionModelStorage.selectedItemProperty().addListener(new ChangeListener<Storage>() {
            @Override
            public void changed(ObservableValue<? extends Storage> observableValue, Storage storage, Storage t1) {
                if(t1 != null){
                    txtStorage.setText(t1.toLineString());
                    titStorage.setExpanded(false);
                    storageAdd = t1;
                }
            }
        });
    }
    RAM ramAdd = null;
    void initRAMTable(){
        tcRAMFrequency.setCellValueFactory(new PropertyValueFactory<>("frequency"));
        tcRAMId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcRAMManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacture"));
        tcRAMTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcRAMVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        tcRAMTypeMemory.setCellValueFactory(new PropertyValueFactory<>("typeMemory"));
        tableRAM.setItems(DataBase.getRAM());
        TableView.TableViewSelectionModel<RAM> selectionModelRAM = tableRAM.getSelectionModel();
        selectionModelRAM.selectedItemProperty().addListener(new ChangeListener<RAM>() {
            @Override
            public void changed(ObservableValue<? extends RAM> observableValue, RAM ram, RAM t1) {
                if(t1 != null){
                    txtRAM.setText(t1.toLineString());
                    titRAM.setExpanded(false);
                    ramAdd = t1;
                    tableMotherboard.setItems(DataBase.getMotherboard("WHERE RAMtype = '" + t1.getTypeMemory()+"'"));
                    if(videoCardAdd != null && cpuAdd!=null){
                        tableMotherboard.setItems(DataBase.getMotherboard(String.format("""
                                WHERE socket = '%s' AND RAMtype = '%s' AND GCPUtype = '%s'
                                """, cpuAdd.getSocket(), t1.getTypeMemory(), videoCardAdd.getGCPU())));
                        return;
                    }
                    if(videoCardAdd != null){
                        tableMotherboard.setItems(DataBase.getMotherboard(String.format("""
                                WHERE GCPUtype = '%s' and RAMtype = '%s'
                                """, videoCardAdd.getGCPU(), t1.getTypeMemory())));
                    }
                    if(cpuAdd!=null){
                        tableMotherboard.setItems(DataBase.getMotherboard(String.format("""
                                WHERE socket = '%s' and RAMtype = '%s'
                                """, cpuAdd.getSocket(), t1.getTypeMemory())));
                    }
                }
            }
        });
    }
    Motherboard motherboardAdd = null;
    void initMotherboardTable(){
        tcMotherboardId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcMotherboardTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcMotherboardManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacture"));
        tcMotherboardGCPUType.setCellValueFactory(new PropertyValueFactory<>("GCPUtype"));
        tcMotherboardSocket.setCellValueFactory(new PropertyValueFactory<>("socket"));
        tcMotherboardRAMType.setCellValueFactory(new PropertyValueFactory<>("RAMtype"));
        tableMotherboard.setItems(DataBase.getMotherboard());
        TableView.TableViewSelectionModel<Motherboard> selectionModelMotherboard = tableMotherboard.getSelectionModel();
        selectionModelMotherboard.selectedItemProperty().addListener(new ChangeListener<Motherboard>() {
            @Override
            public void changed(ObservableValue<? extends Motherboard> observableValue, Motherboard motherboard, Motherboard t1) {
                if(t1 != null){
                    txtMotherboard.setText(t1.toLineString());
                    titMotherboard.setExpanded(false);
                    motherboardAdd = t1;
                    if(cpuAdd == null){
                        tableCPU.setItems(DataBase.getCPU("where socket = '" + t1.getSocket()+"'"));
                    }
                    if(videoCardAdd == null){
                        tableVideoCard.setItems(DataBase.getVideoCard("where GCPU = '" + t1.getGCPUtype()+"'"));
                    }
                    if(ramAdd == null){
                        tableRAM.setItems(DataBase.getRAM("where typeMemory = '" + t1.getRAMtype()+"'"));
                    }
                }
            }
        });
    }
    Computer computerDel = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }

    public void btnCloseClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnClose.getScene().getWindow();
        DataBase.createDataBase();
        DataBase.createTable();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        totalStage.setTitle("Главная страница");
        totalStage.setScene(scene);
        totalStage.show();
    }

    public void btnAddClick(ActionEvent actionEvent) {
        Alert alert =new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        Computer computer = new Computer();
        try {
            computer.setTitle(txtTitle.getText());
        } catch (Exception e) {
            alert.setHeaderText("Вы не ввели название");
            alert.show();
            return;
        }
        if(cpuAdd == null){
            alert.setHeaderText("Вы не выбрали процессор");
            alert.show();
            return;
        }
        if(videoCardAdd == null){
            alert.setHeaderText("Вы не выбрали материнскую плату");
            alert.show();
            return;
        }
        if(ramAdd == null){
            alert.setHeaderText("Вы не выбрали ОЗУ");
            alert.show();
            return;
        }
        if(storageAdd == null){
            alert.setHeaderText("Вы не выбрали ПЗУ");
            alert.show();
            return;
        }
        if(powerBlockAdd == null){
            alert.setHeaderText("Вы не выбрали блок питания");
            alert.show();
            return;
        }
        if(motherboardAdd == null){
            alert.setHeaderText("Вы не выбрали материнскую плату");
            alert.show();
            return;
        }
        computer.setCpu(cpuAdd);
        cpuAdd = null;
        computer.setVideoCard(videoCardAdd);
        videoCardAdd = null;
        computer.setRam(ramAdd);
        ramAdd = null;
        computer.setStorage(storageAdd);
        storageAdd = null;
        computer.setPowerBlock(powerBlockAdd);
        powerBlockAdd = null;
        computer.setMotherboard(motherboardAdd);
        motherboardAdd = null;
        DataBase.addComputer(computer);
        for(var text : textFields){
            text.setText("");
        }
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успешно!");
        alert.setHeaderText("Компьютер успешно добавлен");
        alert.show();
        initListView();
    }

    public void btnDeleteClick(ActionEvent actionEvent) {
        if(computerDel != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удаление");
            alert.setHeaderText("Вы точно хотите удалить сборку?");
            ButtonType ok = new ButtonType("Да");
            ButtonType cancel = new ButtonType("Нет");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ok, cancel);
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get() == ok){
                DataBase.deleteComputer(computerDel.getId());
                initListView();
                for(var text : textFields){
                    text.setText("");
                }
            }
        }
    }

    public void btnEndClick(ActionEvent actionEvent) {
        btnEnd.setDisable(true);
        for(var text : textFields){
            text.setText("");
        }
        int index = pcListView.getSelectionModel().getSelectedIndex();
        pcListView.getSelectionModel().clearSelection(index);
    }
}
