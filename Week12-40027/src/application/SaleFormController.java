package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.NoSuchElementException;

public class SaleFormController {
    @FXML
    protected ComboBox<String> cboItem;
    @FXML
    protected TextField txtSellingPrice;
    @FXML
    protected TextField txtAmount;
    @FXML
    protected Button saleItem;

    protected User user;
    protected List<Item> itemList;

    public void setSaleFormData(User user, List<Item> itemList) {
        this.user = user;
        this.itemList = itemList;
        itemList.stream().forEach(item -> cboItem.getItems().add(item.getName()));
    }

    public void addSale() {
        String title = "Warning Dialog";

        try {
            String selectedItemName = cboItem.getSelectionModel().getSelectedItem();
            Item item = itemList
                    .stream()
                    .filter(i -> i.getName().equals(selectedItemName))
                    .findFirst()
                    .get();
            Integer amount = Integer.parseInt(txtAmount.getText());
            Integer price = Integer.parseInt(txtSellingPrice.getText());
            Sale sale = new Sale(item, amount, price);
            user.addTransaction(sale);
            Stage stage = (Stage) cboItem.getScene().getWindow();
            stage.close();
        } catch (NoSuchElementException e) {
            String header = "No selected item";
            String content = "Please select item from the item options";
            presentAlert(title, header, content);
        } catch (NumberFormatException e) {
            String header = "Amount must be a number!";
            String content = "Please input number for amount field";
            presentAlert(title, header, content);
        }
    }

    private void presentAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
