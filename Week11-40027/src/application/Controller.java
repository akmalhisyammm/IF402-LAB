package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Controller {
	@FXML
	TextField txtKeyword;

	@FXML private TableView<Item> tableItem;

	@FXML
	private TableColumn colId, colProductName, colPrice, colCategory, colStock;

	private ObservableList<Item> items = FXCollections.observableArrayList(
			new Item(1, "Minyak Goreng Bimoli 1.5L", 25800, "Sembako", 100),
			new Item(2, "Beras Ramos 5kg", 66500, "Sembako", 50),
			new Item(3, "Kratindeng", 5300, "Minuman", 200),
			new Item(4, "Sarden ABC Ukuran Besar", 22300, "Makanan Kaleng", 40),
			new Item(5, "Miatos Rasa Jagung Bakar 120g", 9800, "Makanan Ringan", 200),
			new Item(6, "Indomie Goreng Rasa Ayam", 2200, "Sembako", 2000),
			new Item(7, "Ultramilk UHT 1L", 13200, "Minuman", 50),
			new Item(8, "Roti Tawar 12 Potong", 12300, "Bakery", 10),
			new Item(9, "Roti Tawar 24 Potong", 22000, "Bakery", 15));

	private ObservableList<Item> filteredData = FXCollections.observableArrayList();

	public void initialize() {
		colId.setCellValueFactory(new PropertyValueFactory<Item,Integer>("id"));
		colProductName.setCellValueFactory(new PropertyValueFactory<Item,String>("productName"));
		colPrice.setCellValueFactory(new PropertyValueFactory<Item,Integer>("price"));
		colCategory.setCellValueFactory(new PropertyValueFactory<Item,String>("category"));
		colStock.setCellValueFactory(new PropertyValueFactory<Item,Integer>("stock"));
		tableItem.setItems(items);
	}

	@FXML void handleBtnEntry() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("entryForm.fxml"));
			Parent entryForm = loader.load();
			Stage entryStage = new Stage();

			entryStage.setTitle("New Item");
			entryStage.setResizable(false);
			entryStage.setScene(new Scene(entryForm, 384, 347));
			entryStage.show();
			entryStage.requestFocus();

			InputFormController ec = loader.getController();
			ec.setListItem(items);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void handleBtnDelete() {
		Item selectedItem = tableItem.getSelectionModel().getSelectedItem();
		items.remove(selectedItem);

		filterTableByKeyword(getSearchKeyword());
	}

	@FXML
	public void handleSearch() {
		filterTableByKeyword(getSearchKeyword());
	}

	public String getSearchKeyword() {
		return txtKeyword.getText().toLowerCase();
	}

	public void filterTableByKeyword(String keyword) {
		filteredData.clear();

		for (Item item : items) {
			if (item.getProductName().toLowerCase().contains(keyword)) {
				filteredData.add(item);
			}
		}

		if (!keyword.isEmpty()) {
			tableItem.setItems(filteredData);
		} else {
			tableItem.setItems(items);
		}
	}

}
