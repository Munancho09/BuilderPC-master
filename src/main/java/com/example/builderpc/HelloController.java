package com.example.builderpc;

import com.example.builderpc.Classes.*;
import com.example.builderpc.DataBase.DataBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public TableView<CPU> tableCPU;
    public TableColumn<CPU, Integer> tcCPUnumber;
    public TableColumn<CPU, String> tcCPUmanufacture;
    public TableColumn<CPU, String> tcCPUtitle;
    public TableColumn<CPU, Integer> tcCPUfrequency;
    public TableColumn<CPU, Integer> tcCPUPower;
    public TableColumn<CPU, String> tcCPUsocket;
    public TableColumn<CPU, String> tcCPUarchetype;
    public Button btnCPUadd;
    public Button btnCPDelete;
    public Button btnVideoCardAdd;
    public Button btnVideoCardDelete;

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
    public Button btnPowerBlockAdd;
    public Button btnPowerBlockDelete;

    public TableView<Storage> tableStorage;
    public TableColumn<Storage, Integer> tcStorageId;
    public TableColumn<Storage, String> tcStorageManufacturer;
    public TableColumn<Storage, String> tcStorageTitle;
    public TableColumn<Storage, Integer> tcStorageVolume;
    public TableColumn<Storage, String> tcStorageType;
    public TableColumn<Storage, Integer> tcStorageSteedOfWrite;
    public TableColumn<Storage, Integer> tcStorageSpeedOfRead;
    public Button btnStorageAdd;
    public Button btnStorageDelete;

    public TableView<RAM> tableRAM;
    public TableColumn<RAM, Integer> tcRAMId;
    public TableColumn<RAM, String> tcRAMManufacturer;
    public TableColumn<RAM, String> tcRAMTitle;
    public TableColumn<RAM, String> tcRAMTypeMemory;
    public TableColumn<RAM, Integer> tcRAMFrequency;
    public TableColumn<RAM, Integer> tcRAMVolume;
    public Button btnRAMAdd;
    public Button btnRAMDelete;

    public TableView<Motherboard> tableMotherboard;
    public TableColumn<Motherboard, Integer> tcMotherboardId;
    public TableColumn<Motherboard, String> tcMotherboardManufacturer;
    public TableColumn<Motherboard, String> tcMotherboardTitle;
    public TableColumn<Motherboard, String> tcMotherboardSocket;
    public TableColumn<Motherboard, String> tcMotherboardGCPUType;
    public TableColumn<Motherboard, String> tcMotherboardRAMType;
    public Button btnMotherboardAdd;
    public Button btnMotherboardDelete;
    public Button btnPCBuilder;
    public Button btnCPUGen;


    private CPU cpuDel = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }
    void initTable(){
        initCPUTable();
        initVideoCardTable();
        initPowerBlockTable();
        initStorageTable();
        initRAMTable();
        initMotherboardTable();
    }
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
                    cpuDel = t1;
                }
            }
        });
    }

    private VideoCard vcDel = null;

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
                vcDel = t1;
            }
        });
    }

    PowerBlock powerBlockDel = null;

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
                    powerBlockDel = t1;
                }
            }
        });
    }
    Storage storageDelete = null;
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
                    storageDelete = t1;
                }
            }
        });
    }

    private RAM RAMDel = null;
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
                    RAMDel = t1;
                }
            }
        });
    }

    Motherboard motherboardDelete = null;

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
                    motherboardDelete = t1;
                }
            }
        });
    }

    public void btnCPUaddClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnCPUadd.getScene().getWindow();
        DataBase.createDataBase();
        DataBase.createTable();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CPUadd.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        totalStage.setTitle("Добавить процессор");
        totalStage.setScene(scene);
        totalStage.show();
    }

    public void btnCPUdeleteClick(ActionEvent actionEvent) {
        if(cpuDel!=null) {;
            DataBase.deleteCPU(cpuDel.getId());
            initCPUTable();
            cpuDel = null;
        }
    }


    public void btnVideoCardAddClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnCPUadd.getScene().getWindow();
        DataBase.createDataBase();
        DataBase.createTable();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("VideoCardAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        totalStage.setTitle("Добавить видеокарту");
        totalStage.setScene(scene);
        totalStage.show();
    }

    public void btnVideoCardDeleteClick(ActionEvent actionEvent) {
        if(vcDel != null){
            DataBase.deleteVideoCard(vcDel.getId());
            initVideoCardTable();
            vcDel = null;
        }
    }

    public void btnPowerBlockAddClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnCPUadd.getScene().getWindow();
        DataBase.createDataBase();
        DataBase.createTable();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PowerBlockAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        totalStage.setTitle("Добавить блок питания");
        totalStage.setScene(scene);
        totalStage.show();
    }

    public void btnPowerBlockDeleteClick(ActionEvent actionEvent) {
        if(powerBlockDel!=null){
            DataBase.deletePowerBlock(powerBlockDel.getId());
            powerBlockDel = null;
            initPowerBlockTable();
        }
    }

    public void btnStorageAddClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnCPUadd.getScene().getWindow();
        DataBase.createDataBase();
        DataBase.createTable();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StorageAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        totalStage.setTitle("Добавить постоянную память");
        totalStage.setScene(scene);
        totalStage.show();
    }

    public void btnStorageDeleteClick(ActionEvent actionEvent) {
        if(storageDelete!=null){
            DataBase.deleteStorage(storageDelete.getId());
            storageDelete = null;
            initStorageTable();
        }
    }

    public void btnRAMAddClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnCPUadd.getScene().getWindow();
        DataBase.createDataBase();
        DataBase.createTable();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RAMAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        totalStage.setTitle("Добавить оперативную память");
        totalStage.setScene(scene);
        totalStage.show();
    }

    public void btnRAMDeleteClick(ActionEvent actionEvent) {
        if(RAMDel!=null){
            DataBase.deleteRAM(RAMDel.getId());
            RAMDel = null;
            initRAMTable();
        }
    }

    public void btnMotherboardAddClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnCPUadd.getScene().getWindow();
        DataBase.createDataBase();
        DataBase.createTable();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MotherboardAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        totalStage.setTitle("Добавить материнскую плату");
        totalStage.setScene(scene);
        totalStage.show();
    }

    public void btnMotherboardDeleteClick(ActionEvent actionEvent) {
        if(motherboardDelete!=null){
            DataBase.deleteMotherboard(motherboardDelete.getId());
            motherboardDelete = null;
            initMotherboardTable();
        }
    }

    public void btnPCBuilderClick(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        if(DataBase.getCPU().size() == 0){
            alert.setHeaderText("Нет процессоров");
            alert.show();
            return;
        }
        if(DataBase.getMotherboard().size() == 0){
            alert.setHeaderText("Нет материнских плат");
            alert.show();
            return;
        }
        if (DataBase.getRAM().size() == 0){
            alert.setHeaderText("Нет оперативной памяти");
            alert.show();
            return;
        }
        if (DataBase.getStorage().size() == 0){
            alert.setHeaderText("Нет постоянной памяти");
            alert.show();
            return;
        }
        if (DataBase.getPowerBlock().size() == 0){
            alert.setHeaderText("Нет блоков питания");
            alert.show();
            return;
        }
        if (DataBase.getVideoCard().size() == 0){
            alert.setHeaderText("Нет видеокарт");
            alert.show();
            return;
        }
        Stage totalStage = (Stage) btnCPUadd.getScene().getWindow();
        DataBase.createDataBase();
        DataBase.createTable();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ComputerMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        totalStage.setTitle("Сборка компьютера");
        totalStage.setScene(scene);
        totalStage.show();
    }
}