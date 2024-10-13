package org.example.pizzeriadiegovega.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.pizzeriadiegovega.Pizza;
import org.example.pizzeriadiegovega.appDAO;

import java.io.IOException;
import java.sql.SQLException;

public class appController {
    @FXML
    public TextField nombre;
    @FXML
    public TextArea descripcion;
    @FXML
    public TextField precio;
    @FXML
    public Button btnAñadir;
    @FXML
    public Button btnMenu;
    @FXML
    public Button btnSalir;
    @FXML
    public Label texto;

    @FXML
    public void onBtnAñadir(ActionEvent event) { //añadir una pizza al menu
        if (!nombre.getText().isEmpty()) { //verificacion de campos no vacios
            if (!descripcion.getText().isEmpty()) {
                try {
                    double pr = Double.parseDouble(precio.getText());
                    Pizza a = new Pizza(nombre.getText(), descripcion.getText(), pr);
                    appDAO.añadirPizza(a);
                    //limpiar los campos despues de añadir la pizza
                    nombre.setText("");
                    descripcion.setText("");
                    precio.setText("");
                    texto.setText("");
                } catch (NumberFormatException e) {
                    texto.setText("Formato de precio invalido (X.XX)");
                }
            } else {
                texto.setText("Rellene la descripcion");
            }
        } else {
            texto.setText("Rellene el nombre de la pizza");
        }
    }

    @FXML
    public void onBtnMenu(ActionEvent event) throws IOException {
        try { //abrir la segunda ventana de la aplicacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("app2.fxml"));
            Scene newScene = new Scene(loader.load(),350, 600);
            Stage newStage = new Stage();
            newStage.setTitle("Administracion Pizzeria");
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onBtnSalir(ActionEvent event) throws SQLException {
        appDAO.desconectar();
        Platform.exit();
    }
}