package application;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class InputFormController {
	@FXML
	TextField txtId, txtProductName, txtPrice, txtStock;
	@FXML
	ComboBox<String> cboCategory;

	ObservableList<Item> items;

	@FXML
	public void handleBtnAdd() {
		int id;
		int price;
		int stock;

		try {
			id = Integer.parseInt(txtId.getText());
		} catch (NumberFormatException ex) {
			id = 0;
		}

		try {
			price = Integer.parseInt(txtPrice.getText());
		} catch (NumberFormatException ex) {
			price = 0;
		}

		try {
			stock = Integer.parseInt(txtStock.getText());
		} catch (NumberFormatException ex) {
			stock = 0;
		}

		String productName = txtProductName.getText();
		String category = cboCategory.getValue().toString();

		Item i = new Item(id, productName, price, category, stock);
		items.add(i);
	}

	public void setListItem(ObservableList<Item> items) {
		this.items = items;
	}
}
