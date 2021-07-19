package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    @FXML
    public Pagination pagination;
    @FXML
    protected TextField txtFullName;
    @FXML
    protected TextField txtGender;
    @FXML
    protected TextField txtAddress;
    @FXML
    protected TextField txtIncome;
    @FXML
    protected ListView<String> lvPurchase;
    @FXML
    protected ListView<String> lvSale;
    @FXML
    protected Button loadInfo;
    @FXML
    protected Button purchaseItem;
    @FXML
    protected Button saleItem;

    protected int contentPerPage = 7;

    protected String selectedName;
    protected User selectedUser;

    List<User> userList = new ArrayList<>();
    List<Item> itemList = new ArrayList<>();

    public void seedUser() {
        userList.add(new User("John", "Doe", 'M', "Dove Street"));
        userList.add(new User("Van", "Doe", 'M', "Crow Street"));
        userList.add(new User("Tom", "Doe", 'M', "Eagle Street"));
        userList.add(new User("Chuan", "Doe", 'M', "Dove Street"));
        userList.add(new User("Pan", "Doe", 'M', "Dove Street"));
        userList.add(new User("San", "Doe", 'M', "Dove Street"));
        userList.add(new User("Ran", "Doe", 'M', "Dove Street"));
        userList.add(new User("Sam", "Doe", 'M', "Dove Street"));
    }

    public void seedItem() {
        itemList.add(new Item("Helm", 125000));
        itemList.add(new Item("Obeng", 12000));
        itemList.add(new Item("Spion", 18000));
        itemList.add(new Item("Oli", 30000));
    }

    @FXML
    public void initialize() {
        selectedName = "";
        seedUser();
        seedItem();
        double pageCount = (double) userList.size() / contentPerPage;
        pageCount = Math.ceil(pageCount);
        pagination.setPageCount((int) pageCount);

        pagination.setPageFactory(param -> createPage(param));
    }

    public ListView<String> createPage(int pageIndex) {
        ListView<String> lvUser = new ListView<>();

        lvUser.setOnMouseClicked(event -> selectedName = lvUser.getSelectionModel().getSelectedItem());
        int minIndex = pageIndex * contentPerPage;
        int maxIndex = pageIndex + 1 * contentPerPage;
        maxIndex = Math.min(maxIndex, userList.size());
        LinkedList<String> nameList = userList
                .subList(minIndex, maxIndex)
                .stream()
                .map(user -> user.getFullName())
                .collect(Collectors.toCollection(LinkedList::new));

        ObservableList<String> items = FXCollections.observableArrayList(nameList);
        lvUser.setItems(items);
        return lvUser;
    }

    public void handleLoadInfo() {
        try {
            Optional<User> userOptional = userList
                    .stream()
                    .filter(user -> user.getFullName().equals(selectedName))
                    .findFirst();
            selectedUser = userOptional.get();
            txtFullName.setText(selectedUser.getFullName());
            txtGender.setText(selectedUser.getGender());
            txtAddress.setText(selectedUser.getAddress());
            refreshSaleListView();
            refreshPurchaseListView();
            refreshIncome();
        } catch (NoSuchElementException ex) {
            System.out.println("No selected element");
        }
    }

    public void refreshPurchaseListView() {
        lvPurchase.getItems().clear();
        for (Transaction p : selectedUser.getPurchases()) {
            lvPurchase.getItems().add(p.getTransactionInfo());
        }
    }

    public void refreshSaleListView() {
        try {
            lvSale.getItems().clear();
            for (Transaction p : selectedUser.getSales()) {
                lvSale.getItems().add(p.getTransactionInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshIncome() {
        txtIncome.setText(selectedUser.getIncome().toString());
    }

    public void handlePurchase() {
        if(selectedUser == null) {
            String title = "Warning";
            String header = "No selected user";
            String content = "Please select user from the list";
            presentAlert(title, header, content);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("purchaseForm.fxml"));
            Parent root = loader.load();
            Stage purchaseStage = new Stage();
            purchaseStage.initModality(Modality.APPLICATION_MODAL);
            purchaseStage.setTitle("Purchase");
            purchaseStage.setScene(new Scene(root, 333, 222));

            PurchaseFormController controller = loader.getController();
            controller.setPurchaseFormData(selectedUser, itemList);
            purchaseStage.showAndWait();
            refreshPurchaseListView();
            refreshIncome();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to open Form!");
        }
    }

    public void handleSale() {
        if(selectedUser == null) {
            String title = "Warning";
            String header = "No selected user";
            String content = "Please select user from the list";
            presentAlert(title, header, content);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("saleForm.fxml"));
            Parent root = loader.load();
            Stage saleStage = new Stage();
            saleStage.initModality(Modality.APPLICATION_MODAL);
            saleStage.setTitle("Sale");
            saleStage.setScene(new Scene(root, 333, 222));

            SaleFormController controller = loader.getController();
            controller.setSaleFormData(selectedUser, itemList);
            saleStage.showAndWait();
            refreshPurchaseListView();
            refreshIncome();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to open Form!");
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
