package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sports_zone.dto.Employee;
import lk.ijse.sports_zone.dto.tm.EmployeeTM;
import lk.ijse.sports_zone.model.EmployeeModel;

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

//    @FXML
//    private TextField txtJobTItle;

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
        String empId = txtEmpId.getText();

        try {
            boolean isDeleted = EmployeeModel.delete(empId);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "deleted Successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Not deleted").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "somthing went wrong").show();
        }
        setCellValueFactory();
        getAll();

        txtEmpId.setText("");
        txtEmpName.setText("");
        txtAddress.setText("");
        txtContactNo.setText("");
        txtSalary.setText("");
        txtEmail.setText("");
        txtNIC.setText("");
        //txtJobTItle.setText("");
        txtDob.setValue(null);
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        //Employee employee = new Employee();

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
            boolean isSaved = EmployeeModel.save(employee);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"saved").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "somthing went wrong").show();
        }
        setCellValueFactory();
        getAll();

        txtEmpId.setText("");
        txtEmpName.setText("");
        txtAddress.setText("");
        txtContactNo.setText("");
        txtSalary.setText("");
        txtEmail.setText("");
        txtNIC.setText("");
        //cmbJobTitle.setAccessibleText("hi");
        txtDob.setValue(null);
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        //Employee employee = new Employee();

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
                new Alert(Alert.AlertType.CONFIRMATION, "updated Successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Not updated").show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong");
        }
        setCellValueFactory();
        getAll();

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

    @FXML
    void empIdSearchOnAction(ActionEvent event) {
        String empId = txtEmpId.getText();

        try {
            Employee employee = EmployeeModel.search(empId);

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
            cmbJobTitle.setAccessibleText(employee.getJobTitle());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong");
        }
    }

    @FXML
    void empIdSearchBarOnAction(ActionEvent event) {
        String empId = txtSearch.getText();

        try {
            Employee employee = EmployeeModel.search(empId);

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
            cmbJobTitle.setAccessibleText(employee.getJobTitle());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong").show();
        }
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

}
