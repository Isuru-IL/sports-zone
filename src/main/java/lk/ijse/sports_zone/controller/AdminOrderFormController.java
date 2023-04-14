package lk.ijse.sports_zone.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sports_zone.dto.tm.CashierOrderTM;
import lk.ijse.sports_zone.dto.tm.SupplierTM;
import lk.ijse.sports_zone.model.CashierOrderModel;
import lk.ijse.sports_zone.model.SupplierModel;

public class AdminOrderFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDeliveryStatus;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colPayment;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private AnchorPane ordersAnchorPane;

    @FXML
    private TableView<CashierOrderTM> tblOrder;

    @FXML
    private TextField txtSearch;

    @FXML
    void SearchBarOnAction(ActionEvent event) {

    }

    @FXML
    void searchIconOnMouseClickedAction(MouseEvent event) {

    }

    @FXML
    void txtSearchOnKeyTypedAction(KeyEvent event) {
        String searchValue = txtSearch.getText().trim();

        try {
            ObservableList<CashierOrderTM> obList= CashierOrderModel.getAll();

            if (!searchValue.isEmpty()) {
                ObservableList<CashierOrderTM> filteredData = obList.filtered(new Predicate<CashierOrderTM>(){
                    @Override
                    public boolean test(CashierOrderTM cashierOrderTM) {
                        return String.valueOf(cashierOrderTM.getOrderId()).toLowerCase().contains(searchValue.toLowerCase());
                    }
                });
                tblOrder.setItems(filteredData);
            } else {
                tblOrder.setItems(obList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("cudId"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDeliveryStatus.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
    }

    private void getAll() {
        try {
            ObservableList<CashierOrderTM> oblist = CashierOrderModel.getAll();
            tblOrder.setItems(oblist);
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            System.out.println("Orders getAll = "+throwables);
        }
    }

    @FXML
    void initialize() {

        setCellValueFactory();
        getAll();
    }

}
