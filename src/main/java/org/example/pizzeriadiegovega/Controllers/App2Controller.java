package org.example.pizzeriadiegovega.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.pizzeriadiegovega.appDAO;

public class App2Controller {

    @FXML
    public ScrollPane contenedor;
    @FXML
    public VBox vbox;

    public void initialize() {
        generarPaneles();
    }

    public void generarPaneles () {
        for (int i = 1; i < appDAO.numPizzas(); i++) {
            Pane panel = new Pane(); //panel que contendra la informacion sobre cada pizza
            panel.setPrefHeight(30);
            Button b = new Button();
            b.setLayoutX(panel.getWidth());
            b.setText("Eliminar");
            Label a = new Label();
            a.setLayoutX(60);
            a.setText(appDAO.nomPizza(i) + ": " + appDAO.descPizza(i) + " | " + appDAO.precioPizza(i) + "€");

            int finalI = i;
            b.setOnAction(event -> { //boton para eliminar una pizza del menu
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setContentText("Seguro que desea borrarlo");
                confirmacion.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        appDAO.eliminarPizza(appDAO.nomPizza(finalI));
                        vbox.getChildren().clear();
                        generarPaneles();
                    }
                });
            });

            panel.getChildren().addAll(a, b);
            vbox.getChildren().add(panel);
        }
    }
}