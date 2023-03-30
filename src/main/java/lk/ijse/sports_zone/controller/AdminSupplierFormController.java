package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sports_zone.dto.Employee;
import lk.ijse.sports_zone.dto.Supplier;
import lk.ijse.sports_zone.dto.tm.EmployeeTM;
import lk.ijse.sports_zone.dto.tm.SupplierTM;
import lk.ijse.sports_zone.model.EmployeeModel;
import lk.ijse.sports_zone.model.SupplierModel;
import lk.ijse.sports_zone.util.AlertController;
import lk.ijse.sports_zone.util.NotificationController;
import lk.ijse.sports_zone.util.ValidateController;

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
    private Label lblInvalidContacktNo;

    @FXML
    private Label lblInvalidEmail;

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
    private TextField txtSearch;

    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            String supId = txtSupId.getText();

            boolean isDeleted = SupplierModel.delete(supId);
            if(isDeleted){
                AlertController.successfulMessage("Deleted");

                setCellValueFactory();
                getAll();
                clearTxtField();

            }else {
                AlertController.errormessage("Invalid details");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables);                                             //temp
            AlertController.exceptionMessage("SQLException");
        }catch(Exception exception){
            System.out.println(exception);                                              //temp
            AlertController.exceptionMessage("Something went wrong");
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        Supplier supplier = new Supplier();

        if(ValidateController.emailCheck(txtEmail.getText()) || ValidateController.contactCheck(txtContactNo.getText())) {
            if(ValidateController.emailCheck(txtEmail.getText())) {
                if(ValidateController.contactCheck(txtContactNo.getText())) {

                    try {
                        supplier.setSupId(txtSupId.getText());
                        supplier.setSupName(txtSupName.getText());
                        supplier.setAddress(txtAddress.getText());
                        supplier.setEmail(txtEmail.getText());
                        supplier.setContactNo(txtContactNo.getText());

                        boolean isSaved = SupplierModel.save(supplier);
                        if (isSaved) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully").show();

                            setCellValueFactory();
                            getAll();
                            clearTxtField();

                        } else {
                            new Alert(Alert.AlertType.ERROR, "Not Saved").show();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Somthing Went Wrong").show();
                    }
                }else{
                    lblInvalidContacktNo.setVisible(true);
                }
            }else {
                lblInvalidEmail.setVisible(true);
            }
        }else{
            lblInvalidContacktNo.setVisible(true);
            lblInvalidEmail.setVisible(true);
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        Supplier supplier = new Supplier();

        if(ValidateController.emailCheck(txtEmail.getText()) || ValidateController.contactCheck(txtContactNo.getText())) {
            if(ValidateController.emailCheck(txtEmail.getText())) {
                if(ValidateController.contactCheck(txtContactNo.getText())) {

                    supplier.setSupId(txtSupId.getText());
                    supplier.setSupName(txtSupName.getText());
                    supplier.setAddress(txtAddress.getText());
                    supplier.setEmail(txtEmail.getText());
                    supplier.setContactNo(txtContactNo.getText());

                    try {
                        boolean isUpdated = SupplierModel.update(supplier);
                        if(isUpdated){
                            NotificationController.successful("update successful");
                            setCellValueFactory();
                            getAll();
                            clearTxtField();
                        }else{
                            NotificationController.unSuccessful("update unSuccessful");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        NotificationController.catchException(throwables);
                    }
                }else{
                    lblInvalidContacktNo.setVisible(true);
                }
            }else {
                lblInvalidEmail.setVisible(true);
            }
        }else{
            lblInvalidContacktNo.setVisible(true);
            lblInvalidEmail.setVisible(true);
        }
    }

    @FXML
    void supIdSearchOnAction(ActionEvent event) {
        String supId = txtSupId.getText();
        System.out.println(supId);

        try {
            Supplier supplier = SupplierModel.search(supId);

            if(supplier != null){
                txtSupId.setText(supplier.getSupId());
                txtSupName.setText(supplier.getSupName());
                txtAddress.setText(supplier.getAddress());
                txtEmail.setText(supplier.getEmail());
                txtContactNo.setText(supplier.getContactNo());

            }else{
                AlertController.errormessage("Invalid ID");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            AlertController.exceptionMessage("Somthing went wrong");
        }
    }

    @FXML
    void SearchBarOnAction(ActionEvent event) {
        try {
            Supplier supplier = SupplierModel.search(txtSearch.getText());
            if(supplier != null){
                txtSupId.setText(supplier.getSupId());
                txtSupName.setText(supplier.getSupName());
                txtAddress.setText(supplier.getAddress());
                txtEmail.setText(supplier.getEmail());
                txtContactNo.setText(supplier.getContactNo());

            }else{
                NotificationController.unSuccessful("Invalid Id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void searchIconOnMouseClickedAction(MouseEvent event) {
        try {
            Supplier supplier = SupplierModel.search(txtSearch.getText());
            if(supplier != null){
                txtSupId.setText(supplier.getSupId());
                txtSupName.setText(supplier.getSupName());
                txtAddress.setText(supplier.getAddress());
                txtEmail.setText(supplier.getEmail());
                txtContactNo.setText(supplier.getContactNo());

            }else{
                NotificationController.unSuccessful("Invalid Id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void tableOnMouseClicked(MouseEvent event) {
        TablePosition pos=tblSupplier.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<SupplierTM,?>> columns=tblSupplier.getColumns();

        txtSupId.setText(columns.get(0).getCellData(row).toString());
        txtSupName.setText(columns.get(1).getCellData(row).toString());
        txtAddress.setText(columns.get(2).getCellData(row).toString());
        txtContactNo.setText(columns.get(3).getCellData(row).toString());
        txtEmail.setText(columns.get(4).getCellData(row).toString());
    }

    @FXML
    void txtSearchOnKeyTypedAction(KeyEvent event) {
        String searchValue = txtSearch.getText().trim();

        try {
            ObservableList<SupplierTM> obList= SupplierModel.getAll();

            if (!searchValue.isEmpty()) {
                ObservableList<SupplierTM> filteredData = obList.filtered(new Predicate<SupplierTM>(){
                    @Override
                    public boolean test(SupplierTM supplierTM) {
                        return String.valueOf(supplierTM.getSupId()).toLowerCase().contains(searchValue.toLowerCase());
                    }
                });
                tblSupplier.setItems(filteredData);
            } else {
                tblSupplier.setItems(obList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void txtContactNoOnMouseClickedAction(MouseEvent event) {
        lblInvalidContacktNo.setVisible(false);
    }

    @FXML
    void txtEmailOnMouseClickedAction(MouseEvent event) {
        lblInvalidEmail.setVisible(false);
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

        lblInvalidEmail.setVisible(false);
        lblInvalidContacktNo.setVisible(false);
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
//            ObservableList<SupplierTM> obList = FXCollections.observableArrayList();
//                    List<SupplierTM> supplierAllData = SupplierModel.getAll();
//
//                    for(SupplierTM supplierTM : supplierAllData){
//                        obList.add(new SupplierTM(
//                                supplierTM.getSupId(),
//                                supplierTM.getSupName(),
//                                supplierTM.getAddress(),
//                                supplierTM.getEmail(),
//                                supplierTM.getContactNo()
//                        ));
//                    }



            ObservableList<SupplierTM> obList = SupplierModel.getAll();

                    tblSupplier.setItems(obList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            AlertController.exceptionMessage("SQLException");
        } catch (Exception exception){
            AlertController.exceptionMessage("Something went wrong");
        }
    }

    private void clearTxtField(){
        txtSupId.setText(null);
        txtSupName.setText(null);
        txtAddress.setText(null);
        txtEmail.setText(null);
        txtContactNo.setText(null);
        txtSearch.setText(null);
    }

}
