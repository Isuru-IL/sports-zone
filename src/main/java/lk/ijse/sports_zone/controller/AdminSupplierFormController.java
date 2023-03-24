package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sports_zone.dto.Supplier;
import lk.ijse.sports_zone.dto.tm.SupplierTM;
import lk.ijse.sports_zone.model.SupplierModel;

public class AdminSupplierFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchrpSupplier;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSupId;

    @FXML
    private TextField txtSupName;

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        Supplier supplier = new Supplier();
        supplier.setSupId(txtSupId.getId());
        supplier.setSupName(txtSupName.getId());
        supplier.setAddress(txtAddress.getText());
        supplier.setEmail(txtEmail.getText());
        supplier.setContactNo(txtContactNo.getText());

        try {
            boolean isSaved = SupplierModel.save(supplier);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Not Saved").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Somthing Went Wrong").show();
        }
    }

    @FXML
    void supIdSearchOnAction(ActionEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert anchrpSupplier != null : "fx:id=\"anchrpSupplier\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert colAddress != null : "fx:id=\"colAddress\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert colContactNo != null : "fx:id=\"colContactNo\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert colEmail != null : "fx:id=\"colEmail\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert colName != null : "fx:id=\"colName\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert tblSupplier != null : "fx:id=\"tblSupplier\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert txtContactNo != null : "fx:id=\"txtContactNo\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert txtSupId != null : "fx:id=\"txtSupId\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";
        assert txtSupName != null : "fx:id=\"txtSupName\" was not injected: check your FXML file 'adminSupplier_form.fxml'.";

        setCellValueFactory();
        getAll();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("supName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
    }

    private void getAll(){
        try {
            ObservableList<SupplierTM> obList = FXCollections.observableArrayList();
                    List<SupplierTM> supplierAllData = SupplierModel.getAll();

                    for(SupplierTM supplierTM : supplierAllData){
                        obList.add(new SupplierTM(
                                supplierTM.getSupId(),
                                supplierTM.getSupName(),
                                supplierTM.getAddress(),
                                supplierTM.getEmail(),
                                supplierTM.getContactNo()
                        ));
                    }

                    tblSupplier.setItems(obList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong").show();
        }
    }

}
