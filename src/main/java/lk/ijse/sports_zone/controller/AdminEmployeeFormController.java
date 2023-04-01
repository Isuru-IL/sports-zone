package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sports_zone.dto.Employee;
import lk.ijse.sports_zone.dto.tm.EmployeeTM;
import lk.ijse.sports_zone.model.EmployeeModel;
import lk.ijse.sports_zone.util.AlertController;
import lk.ijse.sports_zone.util.ValidateController;

public class AdminEmployeeFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchrpEmployee;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ComboBox<String> cmbJobTitle;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colJobTitle;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private Label lblInvalidContacktNo;

    @FXML
    private Label lblInvalidEmail;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNo;

    @FXML
    private DatePicker txtDob;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtEmpName;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSearch;

    private Employee employee;

    @FXML
    void jobTitleCmbOnAction(ActionEvent event) {
        employee.setJobTitle(cmbJobTitle.getValue());
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        boolean result = AlertController.okconfirmmessage("Are you sure you want to delete ?");
        if(result) {
            btnSave.setVisible(true);
            try {
                String empId = txtEmpId.getText();

                boolean isDeleted = EmployeeModel.delete(empId);
                if (isDeleted) {
                    setCellValueFactory();
                    getAll();
                    clearTxtField();
                } else {
                    AlertController.errormessage("Not Deleted");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                AlertController.exceptionMessage("Something went wrong");
            }
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        //Employee employee = new Employee();


        if (ValidateController.emailCheck(txtEmail.getText()) || ValidateController.contactCheck(txtContactNo.getText())) {
            if (ValidateController.contactCheck(txtContactNo.getText())) {
                //lblInvalidContacktNo.setVisible(false);

                if (ValidateController.emailCheck(txtEmail.getText())) {
                    //lblInvalidEmail.setVisible(false);

                    try {
                        employee.setEmpId(txtEmpId.getText());
                        employee.setEmpName(txtEmpName.getText());
                        employee.setAddress(txtAddress.getText());
                        employee.setDob(String.valueOf(txtDob.getValue()));
                        employee.setContactNo(txtContactNo.getText());
                        employee.setSalary(Double.valueOf(txtSalary.getText()));
                        employee.setEmail(txtEmail.getText());
                        employee.setNic(txtNIC.getText());
                        employee.setJobTitle(cmbJobTitle.getValue());

                        boolean isSaved = EmployeeModel.save(employee);
                        if(isSaved){
                            setCellValueFactory();
                            getAll();
                            clearTxtField();
                            AlertController.successfulMessage("Saved");
                        }
                    } catch(Exception exception){
                        AlertController.exceptionMessage("Something went wrong");
                        System.out.println("EmpSave ="+exception);
                    }

                } else {
                    lblInvalidEmail.setVisible(true);
                }
            } else {
                lblInvalidContacktNo.setVisible(true);
            }
        } else {
            lblInvalidEmail.setVisible(true);
            lblInvalidContacktNo.setVisible(true);
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        //Employee employee = new Employee();
        boolean result = AlertController.okconfirmmessage("Are you sure you want to delete ?");
        if(result){
            btnSave.setVisible(true);

            if (ValidateController.emailCheck(txtEmail.getText()) || ValidateController.contactCheck(txtContactNo.getText())) {
                if (ValidateController.contactCheck(txtContactNo.getText())) {
                    //lblInvalidContacktNo.setVisible(false);

                    if (ValidateController.emailCheck(txtEmail.getText())) {
                        //lblInvalidEmail.setVisible(false);

                        employee.setEmpId(txtEmpId.getText());
                        employee.setEmpName(txtEmpName.getText());
                        employee.setAddress(txtAddress.getText());
                        employee.setDob(String.valueOf(txtDob.getValue()));
                        employee.setContactNo(txtContactNo.getText());
                        employee.setSalary(Double.valueOf(txtSalary.getText()));
                        employee.setEmail(txtEmail.getText());
                        employee.setNic(txtNIC.getText());
                        employee.setJobTitle(cmbJobTitle.getValue());

                        try {
                            boolean isUpdated = EmployeeModel.update(employee);
                            if(isUpdated){
                                AlertController.okMassage("updated");
                                setCellValueFactory();
                                getAll();
                                clearTxtField();
                                btnSave.setDisable(false);
                            }else {
                                AlertController.errormessage("Not Updated");
                            }

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                            AlertController.exceptionMessage("Something went wrong");
                        }

                    } else {
                        lblInvalidEmail.setVisible(true);
                    }
                } else {
                    lblInvalidContacktNo.setVisible(true);
                }
            } else {
                lblInvalidEmail.setVisible(true);
                lblInvalidContacktNo.setVisible(true);
            }
        }

    }


    @FXML
    void empIdSearchOnAction(ActionEvent event) {
        String empId = txtEmpId.getText();
        btnSave.setDisable(true);

        try {
            Employee employee = EmployeeModel.search(empId);

            if(employee != null){
                txtEmpId.setText(employee.getEmpId());
                txtEmpName.setText(employee.getEmpName());
                txtAddress.setText(employee.getAddress());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(employee.getDob(), formatter);
                txtDob.setValue(date);
                //txtDob.setValue(employee.getDob());


                txtContactNo.setText(employee.getContactNo());
                txtSalary.setText(String.valueOf(employee.getSalary()));
                txtEmail.setText(employee.getEmail());
                txtNIC.setText(employee.getNic());
                cmbJobTitle.setValue(employee.getJobTitle());
            }else{
                AlertController.errormessage("Invalid Id");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            AlertController.exceptionMessage("Something went wrong");
        }
    }

    @FXML
    void empIdSearchBarOnAction(ActionEvent event) {
        String empId = txtSearch.getText();
        btnSave.setDisable(true);

        try {
            Employee employee = EmployeeModel.search(empId);

            if(employee != null){
                txtEmpId.setText(employee.getEmpId());
                txtEmpName.setText(employee.getEmpName());
                txtAddress.setText(employee.getAddress());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(employee.getDob(), formatter);
                txtDob.setValue(date);
                //txtDob.setValue(employee.getDob());


                txtContactNo.setText(employee.getContactNo());
                txtSalary.setText(String.valueOf(employee.getSalary()));
                txtEmail.setText(employee.getEmail());
                txtNIC.setText(employee.getNic());
                cmbJobTitle.setValue(employee.getJobTitle());
            }else{
                AlertController.errormessage("Invalid Id");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            AlertController.exceptionMessage("Something went wrong");
        }
    }

    @FXML
    void searchIconOnMouseClickedAction(MouseEvent event) {
        String empId = txtSearch.getText();
        btnSave.setDisable(true);

        try {
            Employee employee = EmployeeModel.search(empId);

            if(employee != null){
                txtEmpId.setText(employee.getEmpId());
                txtEmpName.setText(employee.getEmpName());
                txtAddress.setText(employee.getAddress());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(employee.getDob(), formatter);
                txtDob.setValue(date);
                //txtDob.setValue(employee.getDob());


                txtContactNo.setText(employee.getContactNo());
                txtSalary.setText(String.valueOf(employee.getSalary()));
                txtEmail.setText(employee.getEmail());
                txtNIC.setText(employee.getNic());
                cmbJobTitle.setValue(employee.getJobTitle());
            }else{
                AlertController.errormessage("Invalid Id");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            AlertController.exceptionMessage("Something went wrong");
        }
    }

    @FXML
    void txtSearchOnKeyTypedAction(KeyEvent event) throws SQLException {
        String searchValue = txtSearch.getText().trim();

        ObservableList<EmployeeTM> obList= FXCollections.observableArrayList();

        List<Employee> data = EmployeeModel.getAll();

        for(Employee employee : data){
            obList.add(new EmployeeTM(
                    employee.getEmpId(),
                    employee.getEmpName(),
                    employee.getAddress(),
                    employee.getDob(),
                    employee.getContactNo(),
                    employee.getSalary(),
                    employee.getEmail(),
                    employee.getNic(),
                    employee.getJobTitle()
            ));
        }


        if (!searchValue.isEmpty()) {
            ObservableList<EmployeeTM> filteredData = obList.filtered(new Predicate<EmployeeTM>(){
                @Override
                public boolean test(EmployeeTM employeetm) {
                    return String.valueOf(employeetm.getEmpId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblEmployee.setItems(filteredData);
        } else {
            tblEmployee.setItems(obList);
        }
    }

    @FXML
    void tableOnMouseClicked(MouseEvent event) {
        TablePosition pos=tblEmployee.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<EmployeeTM,?>> columns=tblEmployee.getColumns();

        txtEmpId.setText(columns.get(0).getCellData(row).toString());
        txtEmpName.setText(columns.get(1).getCellData(row).toString());
        txtAddress.setText(columns.get(2).getCellData(row).toString());
        txtDob.setValue(LocalDate.parse(columns.get(8).getCellData(row).toString()));
        txtContactNo.setText(columns.get(3).getCellData(row).toString());
        txtSalary.setText(columns.get(4).getCellData(row).toString());
        txtEmail.setText(columns.get(5).getCellData(row).toString());
        txtNIC.setText(columns.get(6).getCellData(row).toString());
        cmbJobTitle.setValue(columns.get(7).getCellData(row).toString());
    }

    @FXML
    void txtContactNoOnMouseClickedAction(MouseEvent event) {
        lblInvalidContacktNo.setVisible(false);
    }

    @FXML
    void txtEmailOnMouseClickedAction(MouseEvent event) {
        lblInvalidEmail.setVisible(false);
    }

    private void generateNextOrderId() {
        try {
            String id = EmployeeModel.getNextEmpId();
            txtEmpId.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }


    @FXML
    void initialize() {
        assert anchrpEmployee != null : "fx:id=\"anchrpEmployee\" was not injected: check your FXML file 'adminEmployee_form.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'adminEmployee_form.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'adminEmployee_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'adminEmployee_form.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'adminEmployee_form.fxml'.";
        assert txtContactNo != null : "fx:id=\"txtContactNo\" was not injected: check your FXML file 'adminEmployee_form.fxml'.";
        assert txtDob != null : "fx:id=\"txtDob\" was not injected: check your FXML file 'adminEmployee_form.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'adminEmployee_form.fxml'.";
        assert txtEmpId != null : "fx:id=\"txtEmpId\" was not injected: check your FXML file 'adminEmployee_form.fxml'.";
        assert txtEmpName != null : "fx:id=\"txtEmpName\" was not injected: check your FXML file 'adminEmployee_form.fxml'.";
        assert cmbJobTitle != null : "fx:id=\"txtJobTItle\" was not injected: check your FXML file 'adminEmployee_form.fxml'.";
        assert txtNIC != null : "fx:id=\"txtNIC\" was not injected: check your FXML file 'adminEmployee_form.fxml'.";
        assert txtSalary != null : "fx:id=\"txtSalary\" was not injected: check your FXML file 'adminEmployee_form.fxml'.";


        employee = new Employee();
        cmbJobTitle.getItems().add("Admin");
        cmbJobTitle.getItems().add("Cashier");

        generateNextOrderId();
        setCellValueFactory();
        getAll();

        lblInvalidEmail.setVisible(false);
        lblInvalidContacktNo.setVisible(false);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colJobTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));

    }

    private void getAll() {
        try {
            ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();
            List<Employee>allData = EmployeeModel.getAll();

                for(Employee employee : allData){
                    obList.add(new EmployeeTM(
                            employee.getEmpId(),
                            employee.getEmpName(),
                            employee.getAddress(),
                            employee.getDob(),
                            employee.getContactNo(),
                            employee.getSalary(),
                            employee.getEmail(),
                            employee.getNic(),
                            employee.getJobTitle()
                    ));
                }
                tblEmployee.setItems(obList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void clearTxtField() {
        txtEmpId.setText("");
        txtEmpName.setText("");
        txtAddress.setText("");
        txtContactNo.setText("");
        txtSalary.setText("");
        txtEmail.setText("");
        txtNIC.setText("");
        //cmbJobTitle.setItems(null);
        txtDob.setValue(null);
    }
}
