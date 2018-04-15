/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class CoffeeController implements Initializable {

    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtnomor;
    @FXML
    private TextField txtnama1;
    @FXML
    private TextField txtnomor1;
    @FXML
    private Button btnSimpan;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private AnchorPane pane;
    @FXML
    TableView<Kopi> tablekopi;
    @FXML
    TableColumn<Kopi, String> columkode;
    @FXML
    TableColumn<Kopi, String> columnamabrg;
    @FXML
    TableColumn<Kopi, String> columnamapembeli;
    @FXML
    TableColumn<Kopi, String> columjumlah;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> {
            txtnama.requestFocus();
        });

        // table property
        columkode.setCellValueFactory(new PropertyValueFactory<>("kode"));
        columnamabrg.setCellValueFactory(new PropertyValueFactory<>("namabrg"));
        columnamapembeli.setCellValueFactory(new PropertyValueFactory<>("namapembeli"));
        columjumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));

        tablekopi.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Kopi m = inputDataKopi(tablekopi.getSelectionModel().getSelectedItem(), true).orElse(null);
                txtnama.requestFocus();
            }
        });
    }

    private String getKodepblFromInput() throws IllegalArgumentException {
        String kodepbl = txtnomor.getText().trim();
        if (kodepbl.equals("")) {
            throw new IllegalArgumentException("Kode pembelian tidak boleh kosong");
        }
        return kodepbl;
    }

    private String getNamapblFromInput() throws IllegalArgumentException {
        String namapbl = txtnama.getText().trim();
        if (namapbl.equals("")) {
            throw new IllegalArgumentException("Nama pembeli tidak boleh kosong");
        }
        return namapbl;
    }

    private String getNamabrgFromInput() throws IllegalArgumentException {
        String namabrg = txtnama1.getText().trim();
                
        if (namabrg.equals("")) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong");
        }
        return namabrg;
    }

    private String getHargabrgFromInput() throws IllegalArgumentException {
        String hargabrg = txtnomor1.getText().trim();
        int hrga = Integer.parseInt(txtnomor1.getText());
        if (hargabrg.equals("")) {
            throw new IllegalArgumentException("Jumlah produk tidak boleh kosong");
        }else if (hrga > 0 && hrga <= 100000 ) {
            return hargabrg;
        }else
            throw new IllegalArgumentException("Jumlah produk harus angka yang > 0 dan <=100000");
    }

    private Optional<Kopi> inputDataKopi(Kopi kopi, boolean edit) {
        Dialog<Kopi> dialog = new Dialog<>();
        dialog.setTitle("Input Data Kopi");

        // Set the button types.
        ButtonType okButtonType = new ButtonType("Simpan", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField kode = new TextField();
        kode.setPromptText("Kode ");
        kode.setText(kopi.getKode());
        kode.requestFocus();
        TextField namapembeli = new TextField();
        namapembeli.setPromptText("Nama ");
        namapembeli.setText(kopi.getNamapembeli());
        TextField namabrg = new TextField();
        namabrg.setPromptText("Kode ");
        namabrg.setText(kopi.getNamabrg());
        TextField jumlah = new TextField();
        jumlah.setPromptText("Nama ");
        jumlah.setText(kopi.getJumlah());


        grid.add(new Label("Kode:"), 0, 0);
        grid.add(kode, 1, 0);
        grid.add(new Label("Nama Pembeli:"), 0, 1);
        grid.add(namapembeli, 1, 1);
        grid.add(new Label("Nama Barang:"), 0, 2);
        grid.add(namabrg, 1, 2);
        grid.add(new Label("Jumlah:"), 0, 3);
        grid.add(jumlah, 1, 3);

        dialog.getDialogPane().setContent(grid);
        

        Optional<Kopi> result = dialog.showAndWait();
        return result;
    }

    public void onBtnClearClick(ActionEvent event) {
        txtnama1.clear();
        txtnama.clear();
        txtnomor.clear();
        txtnomor1.clear();
        txtnama.requestFocus();
        messageBox("Message", "Cleared");

    }

    public void onBtnSimpanClick(ActionEvent event) {
        String namapembeli = "";
        try {
            namapembeli = getNamapblFromInput();
        } catch (IllegalArgumentException e) {
            messageBox("Coffee Table View", e.getMessage()).showAndWait();
            txtnama.requestFocus();
        }
        String kode = "";
        try {
            kode = getKodepblFromInput();
        } catch (NumberFormatException e) {
            messageBox("Coffee Tale View", "Kode hanya boleh angka").showAndWait();
            txtnomor.selectAll();
            txtnomor.requestFocus();
            return;
        } catch (IllegalArgumentException e) {
            messageBox("Coffee Table View", e.getMessage()).showAndWait();
            txtnomor.selectAll();
            txtnomor.requestFocus();
            txtnomor.selectAll();
            return;
        }
        String namabrg = "";
        try {
            namabrg = getNamabrgFromInput();
        } catch (IllegalArgumentException e) {
            messageBox("Coffee Table View", e.getMessage()).showAndWait();
            txtnama1.requestFocus();
        }
        String jumlah = "";
        try {
            jumlah = getHargabrgFromInput();
        } catch (NumberFormatException e) {
            messageBox("Coffee Tale View", "Kode hanya boleh angka").showAndWait();
            txtnomor1.selectAll();
            txtnomor1.requestFocus();
            return;
        } catch (IllegalArgumentException e) {
            messageBox("Coffee Table View", e.getMessage()).showAndWait();
            txtnomor1.selectAll();
            txtnomor1.requestFocus();
            txtnomor1.selectAll();
            return;
        }

        tablekopi.getItems().add(new Kopi(kode, namapembeli, namabrg, jumlah));
        txtnama.clear();
        txtnomor.clear();
        txtnama1.clear();
        txtnomor1.clear();

        txtnama.requestFocus();
    }

    public void onBtnDeleteClick(ActionEvent event) {
        int index = tablekopi.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Kopi data = tablekopi.getItems().get(index);
            // tampilkan form konfirmasi
            if (confirm("Hapus", "Yakin hapus " + data + "?")
                    .orElse(ButtonType.CANCEL).equals(ButtonType.OK)) {
                // hapus
                deleteItem(index);
            }
        }
    }

    public void onTableViewKeyPressed(KeyEvent event) {
        // jika tombol DELETE/BACKSPACE hapus item
        if (event.getCode().equals(KeyCode.DELETE) || event.getCode().equals(KeyCode.BACK_SPACE)) {
            int index = tablekopi.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                Kopi data = tablekopi.getItems().get(index);
                // tampilkan form konfirmasi
                if (confirm("Hapus", "Yakin hapus " + data + "?")
                        .orElse(ButtonType.CANCEL).equals(ButtonType.OK)) {
                    // hapus
                    deleteItem(index);
                }
            }
        }
    }
// dialog box untuk menampilkan pesan konfirmasi (OK, CANCEL)

    private Optional<ButtonType> confirm(String title, String message) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle(title);
        confirm.setContentText(message);
        Optional<ButtonType> result = confirm.showAndWait();
        return result;
    }

    private void deleteItem(int index) {
        if (index >= 0 && index < tablekopi.getItems().size()) {
            tablekopi.getItems().remove(index);
        }
    }

    private Alert messageBox(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }

}
