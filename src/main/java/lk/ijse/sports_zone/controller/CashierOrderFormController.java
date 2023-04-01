package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.sports_zone.dto.CartDTO;
import lk.ijse.sports_zone.dto.Customer;
import lk.ijse.sports_zone.dto.Inventory;
import lk.ijse.sports_zone.dto.tm.CartTM;
import lk.ijse.sports_zone.model.CashierOrderModel;
import lk.ijse.sports_zone.model.CustomerModel;
import lk.ijse.sports_zone.model.InventoryModel;
import lk.ijse.sports_zone.model.PlaceOrderModel;
import lk.ijse.sports_zone.util.AlertController;
import lk.ijse.sports_zone.util.DateAndTimeConntroller;

public class CashierOrderFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchrpCashierOrder;

    @FXML
    private JFXButton btnAddNewCust;

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private ComboBox<String> cmbCustId;

    @FXML
    private ComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCatagory;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDelivery;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblCustName;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblorderTime;

    @FXML
    private Label lblBalance;

    @FXML
    private RadioButton radioBtnNo;

    @FXML
    private RadioButton radioBtnYes;

    @FXML
    private TableView<CartTM> tblPlaceOrder;

    @FXML
    private TextField txtPaidAmount;

    @FXML
    private TextField txtQty;

    private ObservableList<CartTM> obList = FXCollections.observableArrayList();
    private Inventory inventory;
    private String delivery = "No";

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCatagory.setCellValueFactory(new PropertyValueFactory<>("catagory"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDelivery.setCellValueFactory(new PropertyValueFactory<>("delivery"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnAction"));
    }

    private void loadCustomerIds(){
        try {
            ObservableList<String> custIds = FXCollections.observableArrayList();
            List<String> ids = CustomerModel.loadCustomerIds();

            for(String id : ids){
                custIds.add(id);
            }

            cmbCustId.setItems(custIds);
        } catch (SQLException throwables) {
            AlertController.exceptionMessage("Something went wrong");
        }
    }

    private void loadItemCodes() {
        ObservableList<String> itemCodes = FXCollections.observableArrayList();
        try {
            List<String> cods = InventoryModel.loadItemCodes();

            for(String cod : cods){
                itemCodes.add(cod);
            }
            cmbItemCode.setItems(itemCodes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void generateNextOrderId() {
        try {
            String id = CashierOrderModel.getNextOrderId();
            lblOrderId.setText(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void btnAddNewCustOnAction(ActionEvent event) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(false);
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cashierCustomer02_form.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();
        String itemName = lblItemName.getText();
        String catagory = inventory.getCategory();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());

        double total = unitPrice*qty;

        Button btnRemove = new Button("Remove");
        btnRemove.setCursor(Cursor.HAND);

        setRemoveBtnOnAction(btnRemove);

        if(qty>inventory.getQtyOnHand()){
            AlertController.errormessage("Item "+itemName+" out of stock or not enough stock");
        }else {
            if (!obList.isEmpty()) {
                for (int i = 0; i < tblPlaceOrder.getItems().size(); i++) {
                    if (colCode.getCellData(i).equals(code)) {
                        qty += (int) colQty.getCellData(i);
                        total = qty * unitPrice;

                        obList.get(i).setQty(qty);
                        obList.get(i).setTotal(total);

                        tblPlaceOrder.refresh();
                        calculateNetTotal();
                        return;
                    }
                }
            }

            CartTM tm = new CartTM(code, itemName, catagory, qty, unitPrice, total, delivery, btnRemove);

            obList.add(tm);
            tblPlaceOrder.setItems(obList);
            calculateNetTotal();

            txtQty.setText("");
        }
    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblPlaceOrder.getItems().size(); i++) {
            double total = (double) colTotal.getCellData(i);
            netTotal += total;
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    private void setRemoveBtnOnAction(Button btnRemove) {
        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = tblPlaceOrder.getSelectionModel().getSelectedIndex();
                obList.remove(index);

                tblPlaceOrder.refresh();
                calculateNetTotal();
            }

        });
    }

    @FXML
    void radioBtnYesOnAction(ActionEvent event) {
        delivery = "Yes";

        if(radioBtnYes.isSelected()){
            Stage stage = new Stage();
            stage.resizableProperty().setValue(false);
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cashierDelivery02_form.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }
            stage.centerOnScreen();
            stage.show();
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String ordrId = lblOrderId.getText();
        String customerId = cmbCustId.getValue();
        double netTotal = Double.parseDouble(lblNetTotal.getText());


        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        Time time = new Time(calendar.getTimeInMillis());

        List<CartDTO> cartDTOList = new ArrayList<>();

        for (int i = 0; i < tblPlaceOrder.getItems().size(); i++) {
            CartTM cartTM = obList.get(i);

            CartDTO dto = new CartDTO(
                    cartTM.getCode(),
                    cartTM.getQty(),
                    netTotal,
                    cartTM.getDelivery()
            );
            cartDTOList.add(dto);
        }

        try {
            boolean isPlaced = PlaceOrderModel.placeOrder(ordrId, customerId, date, time, cartDTOList);
            if(isPlaced){
                generateNextOrderId();
                AlertController.successfulMessage("Order Placed");
                System.out.println("isPlaced");
                clearTxtFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            AlertController.errormessage("Order Not Placed");
        }
    }

    @FXML
    void cmbCustIdOnAction(ActionEvent event) {
        String id = cmbCustId.getValue();
        try {
            Customer customer = CustomerModel.searchByCustId(id);

            lblCustName.setText(customer.getCustName());

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("custId "+e);
        }
    }

    @FXML
    void cmbItemCodeOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();

        try {
            inventory = InventoryModel.searchByItemCode(code);
            lblItemName.setText(inventory.getItemName());
            lblUnitPrice.setText(String.valueOf(inventory.getUnitPrice()));
            lblQtyOnHand.setText(String.valueOf(inventory.getQtyOnHand()));

            if(inventory.getQtyOnHand()>0){
                lblQtyOnHand.setText(String.valueOf(inventory.getQtyOnHand()));
            }else{
                lblQtyOnHand.setText("Out Of Stock");
                AlertController.errormessage("item "+inventory.getItemName()+" out of stock");
            }
        } catch (Exception e) {
            //throwables.printStackTrace();
            System.out.println("item code " +e);
        }
    }

    @FXML
    void tableOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtPaidAmountOnKeyTypedAction(KeyEvent event) {
        double netTotal = Double.parseDouble(lblNetTotal.getText());
        double paidAmount = Double.parseDouble(txtPaidAmount.getText());

        lblBalance.setText(String.valueOf(paidAmount - netTotal));
        if(netTotal>paidAmount){

        }
    }


    @FXML
    void initialize() {
        assert anchrpCashierOrder != null : "fx:id=\"anchrpCashierOrder\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert btnAddNewCust != null : "fx:id=\"btnAddNewCust\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert btnAddToCart != null : "fx:id=\"btnAddToCart\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert btnPlaceOrder != null : "fx:id=\"btnPlaceOrder\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert cmbCustId != null : "fx:id=\"cmbCustId\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert cmbItemCode != null : "fx:id=\"cmbItemCode\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert colAction != null : "fx:id=\"colAction\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert colCatagory != null : "fx:id=\"colCatagory\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert colCode != null : "fx:id=\"colCode\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert colDelivery != null : "fx:id=\"colDelivery\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert colName != null : "fx:id=\"colName\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert colQty != null : "fx:id=\"colQty\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert colTotal != null : "fx:id=\"colTotal\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert colUnitPrice != null : "fx:id=\"colUnitPrice\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert lblCustName != null : "fx:id=\"lblCustName\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert lblItemName != null : "fx:id=\"lblItemName\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert lblNetTotal != null : "fx:id=\"lblNetTotal\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert lblOrderDate != null : "fx:id=\"lblOrderDate\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert lblOrderId != null : "fx:id=\"lblOrderId\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert lblQtyOnHand != null : "fx:id=\"lblQtyOnHand\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert lblUnitPrice != null : "fx:id=\"lblUnitPrice\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert lblorderTime != null : "fx:id=\"lblorderTime\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert radioBtnNo != null : "fx:id=\"radioBtnNo\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert radioBtnYes != null : "fx:id=\"radioBtnYes\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert tblPlaceOrder != null : "fx:id=\"tblPlaceOrder\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";
        assert txtQty != null : "fx:id=\"txtQty\" was not injected: check your FXML file 'cashierOrder_form.fxml'.";

        loadCustomerIds();
        loadItemCodes();
        generateNextOrderId();

        DateAndTimeConntroller dateTime = new DateAndTimeConntroller();
        dateTime.Timenow(lblorderTime, lblOrderDate);

        setCellValueFactory();
    }

    public void clearTxtFields(){
        lblCustName.setText("");
        cmbCustId.setValue(null);
        cmbItemCode.setValue(null);
        lblItemName.setText("");
        lblUnitPrice.setText("");
        lblQtyOnHand.setText("");
        tblPlaceOrder.getItems().clear();
        radioBtnYes.setSelected(false);
    }

}
