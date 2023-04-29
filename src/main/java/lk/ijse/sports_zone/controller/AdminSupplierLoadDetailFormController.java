package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sports_zone.dto.Inventory;
import lk.ijse.sports_zone.dto.Supplier;
import lk.ijse.sports_zone.dto.SupplyLoadDetailDTO;
import lk.ijse.sports_zone.dto.tm.SupplyLoadDetailTM;
import lk.ijse.sports_zone.model.InventoryModel;
import lk.ijse.sports_zone.model.PlaceSupplyModel;
import lk.ijse.sports_zone.model.SupplierloadDetailModel;
import lk.ijse.sports_zone.model.SupplierModel;
import lk.ijse.sports_zone.util.AlertController;
import lk.ijse.sports_zone.util.ValidateController;

public class AdminSupplierLoadDetailFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchrpSupplyLoad;

    @FXML
    private JFXButton btnAddNewSupplier;

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnPlaceSupply;

    @FXML
    private ComboBox<String> cmbItemCode;

    @FXML
    private ComboBox<String> cmbSupId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCatagory;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblCatagory;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblLoadId;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblSaleUnitPrice;

    @FXML
    private Label lblSupplierName;

    @FXML
    private Label lblSupplyDate;

    @FXML
    private Label lblInvalidBuyUnitPrice;

    @FXML
    private Label lblInvalidQuantity;

    @FXML
    private Label lblEmptyBuyUnitPrice;

    @FXML
    private Label lblEmptyPaidAmount;

    @FXML
    private Label lblEmptyQuantity;

    @FXML
    private TableView<SupplyLoadDetailTM> tblPlaceSupply;

    @FXML
    private TextField txtBuyUnitPrice;

    @FXML
    private TextField txtPaidAmount;

    @FXML
    private TextField txtQty;

    private ObservableList<SupplyLoadDetailTM> obList = FXCollections.observableArrayList();
    private Inventory inventory;
    Button btnRemove;

    private void loadSupplierIds() {
        ObservableList<String> supids = FXCollections.observableArrayList();
        try {
            List<String> ids = SupplierModel.loadSupplierIds();
            for (String id : ids){
                supids.add(id);
            }
            cmbSupId.setItems(supids);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadItemCodes() {
        ObservableList<String> itemCodes = FXCollections.observableArrayList();

        try {
            List<String> codes = InventoryModel.loadItemCodes();
            for (String code : codes){
                itemCodes.add(code);
            }
            cmbItemCode.setItems(itemCodes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void cmbItemCodeOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();

        try {
            inventory = InventoryModel.search(code);
            lblItemName.setText(inventory.getItemName());
            lblCatagory.setText(inventory.getCategory());
            lblSaleUnitPrice.setText(String.valueOf(inventory.getUnitPrice()));
            lblQtyOnHand.setText(String.valueOf(inventory.getQtyOnHand()));

        } catch (Exception throwables) {
            //throwables.printStackTrace();
            //System.out.println(throwables);
        }
    }

    @FXML
    void cmbSupplierIdOnAction(ActionEvent event) {
        String id = cmbSupId.getValue();

        try {
            Supplier supplier = SupplierModel.search(id);
            lblSupplierName.setText(supplier.getSupName());
        } catch (Exception throwables) {
            //throwables.printStackTrace();
            //System.out.println(throwables);
        }
    }

    private void generateNextLoadId() {
        try {
            String id = SupplierloadDetailModel.genaratenextloadId();
            lblLoadId.setText(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @FXML
    void btnAddNewSupplierOnAction(ActionEvent event) {
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/adminSupplier_form.fxml"));
            anchrpSupplyLoad.getChildren().clear();
            anchrpSupplyLoad.getChildren().add(load);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        if(lblSupplierName.getText().isEmpty() || lblItemName.getText().isEmpty()){
            AlertController.errormessage("Please make sure to fill out all the required fields.");
        }else {
            if(!txtBuyUnitPrice.getText().isEmpty() || !txtQty.getText().isEmpty()){
                if(!txtQty.getText().isEmpty()){
                    if(!txtBuyUnitPrice.getText().isEmpty()){

                        if(ValidateController.doubleValueCheck(txtBuyUnitPrice.getText()) || ValidateController.intValueCheck(txtQty.getText())){
                            if(ValidateController.intValueCheck(txtQty.getText())){
                                if(ValidateController.doubleValueCheck(txtBuyUnitPrice.getText())){
                                    String code = cmbItemCode.getValue();
                                    String itemName = lblItemName.getText();
                                    String catagory = lblCatagory.getText();
                                    Integer buyQty = Integer.valueOf(txtQty.getText());
                                    Double buyUnitPrice = Double.valueOf(txtBuyUnitPrice.getText());

                                    Double total = buyQty*buyUnitPrice;

                                    btnRemove = new Button("Remove");
                                    btnRemove.setCursor(Cursor.HAND);

                                    setRemoveBtnOnAction(btnRemove);

                                    calculateNetTotal();
                                    clearTxtField();

                                    if (!obList.isEmpty()) {
                                        for (int i = 0; i < tblPlaceSupply.getItems().size(); i++) {
                                            if (colCode.getCellData(i).equals(code)) {
                                                buyQty += (int) colQty.getCellData(i);
                                                total = buyQty * buyUnitPrice;

                                                obList.get(i).setQty(buyQty);
                                                obList.get(i).setTotal(total);

                                                tblPlaceSupply.refresh();
                                                calculateNetTotal();
                                                return;
                                            }
                                        }
                                    }

                                    SupplyLoadDetailTM tm = new SupplyLoadDetailTM(code, itemName, catagory, buyQty, buyUnitPrice, total, btnRemove);

                                    obList.add(tm);
                                    tblPlaceSupply.setItems(obList);
//                                    calculateNetTotal();
//                                    clearTxtField();

//                                    btnRemove.setOnAction(e -> {
//                                        // Get the row that contains the button
//                                        TableRow row = (TableRow) btnRemove.getParent().getParent();
//                                        int index = tblPlaceSupply.getItems().indexOf(row.getItem());
//                                        tblPlaceSupply.getSelectionModel().select(index);
//
//                                        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
//                                        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//                                        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
//
//                                        if (result.orElse(no) == yes) {
//                                            int index1 = tblPlaceSupply.getSelectionModel().getSelectedIndex();
//                                            obList.remove(index1);
//
//                                            tblPlaceSupply.refresh();
//                                        }
//
//                                    });
                                }else {
                                    lblInvalidBuyUnitPrice.setVisible(true);
                                }
                            }else {
                                lblInvalidQuantity.setVisible(true);
                            }
                        }else {
                            lblInvalidQuantity.setVisible(true);
                            lblInvalidBuyUnitPrice.setVisible(true);
                        }
                    }else {
                        lblEmptyBuyUnitPrice.setVisible(true);
                    }
                }else {
                    lblEmptyQuantity.setVisible(true);
                }
            }else {
                lblEmptyBuyUnitPrice.setVisible(true);
                lblEmptyQuantity.setVisible(true);
            }
        }
    }


    private void setRemoveBtnOnAction(Button btnRemove) {
        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = tblPlaceSupply.getSelectionModel().getSelectedIndex();
                obList.remove(index);

                tblPlaceSupply.refresh();
                calculateNetTotal();
            }

        });
    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i <tblPlaceSupply.getItems().size() ; i++) {
            double total = (double) colTotal.getCellData(i);
            netTotal += total;
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void btnPlaceSupplyOnAction(ActionEvent event) {

        if(txtPaidAmount.getText().isEmpty()){
            lblEmptyPaidAmount.setVisible(true);
            AlertController.errormessage("Please enter Amount");
        }else {

            double netTotal = Double.parseDouble(lblNetTotal.getText());
            double paidAmount = Double.parseDouble(txtPaidAmount.getText());

            if(paidAmount > netTotal){

                String loadId = lblLoadId.getText();
                String supId = cmbSupId.getValue();

                List<SupplyLoadDetailDTO> supplyLoadDetailDTOList = new ArrayList<>();

                for (int i = 0; i < tblPlaceSupply.getItems().size(); i++) {
                    SupplyLoadDetailTM supplyLoadDetailTM = obList.get(i);

                    SupplyLoadDetailDTO dto = new SupplyLoadDetailDTO(
                            supplyLoadDetailTM.getItemCode(),
                            supplyLoadDetailTM.getTotal(),
                            supplyLoadDetailTM.getQty()
                    );
                    supplyLoadDetailDTOList.add(dto);
                }

                try {
                    boolean isPlaced = PlaceSupplyModel.placeSupply(loadId, supId, netTotal, supplyLoadDetailDTOList);
                    if(isPlaced){
                        generateNextLoadId();
                        clearTxtField();
                        //tblPlaceSupply.refresh();
                        tblPlaceSupply.getItems().clear();
                        lblNetTotal.setText("");
                        lblBalance.setText("");
                        txtPaidAmount.setText("");
                        AlertController.successfulMessage("Supply placed");
                    }
                } catch (SQLException throwables) {
                    //throwables.printStackTrace();
                    System.out.println("supController => "+ throwables);
                    AlertController.errormessage("Supply not placed");
                }
            }else {
                AlertController.errormessage("Amount not enough");
            }
        }
    }


    @FXML
    void tableOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtPaidAmountOnKeyTypedAction(KeyEvent event) {
        lblEmptyPaidAmount.setVisible(false);
        if(!txtPaidAmount.getText().isEmpty() && !lblNetTotal.getText().isEmpty()){
            double netTotal = Double.parseDouble(lblNetTotal.getText());
            double paidAmount = Double.parseDouble(txtPaidAmount.getText());

            if(netTotal < paidAmount){
                lblBalance.setVisible(true);
                lblBalance.setText(String.valueOf(paidAmount - netTotal));
            }else if(netTotal > paidAmount){
                lblBalance.setVisible(false);
            }
        }
    }

    @FXML
    void txtBuyUnitPriceOnMouseClickedAction(MouseEvent event) {
        lblInvalidBuyUnitPrice.setVisible(false);
        lblEmptyBuyUnitPrice.setVisible(false);
    }

    @FXML
    void txtQtyOnMouseClickedAction(MouseEvent event) {
        lblInvalidQuantity.setVisible(false);
        lblEmptyQuantity.setVisible(false);
    }

    @FXML
    void initialize() {
        loadItemCodes();
        loadSupplierIds();
        generateNextLoadId();
        setCellValueFactory();

        lblSupplyDate.setText(String.valueOf(LocalDate.now()));
        lblInvalidBuyUnitPrice.setVisible(false);
        lblInvalidQuantity.setVisible(false);
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colCatagory.setCellValueFactory(new PropertyValueFactory<>("catagory"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("buyUnitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnAction"));
    }

    private void clearTxtField() {
        //lblSupplierName.setText("");
        cmbItemCode.setValue(null);
        lblItemName.setText("");
        lblSaleUnitPrice.setText("");
        lblQtyOnHand.setText("");
        lblCatagory.setText("");
        txtBuyUnitPrice.setText("");
        txtQty.setText("");
    }
}
