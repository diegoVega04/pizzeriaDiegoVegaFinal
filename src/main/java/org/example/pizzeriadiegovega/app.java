package org.example.pizzeriadiegovega;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class app extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 535, 300);
        stage.setTitle("Administracion Pizzeria");
        stage.setScene(scene);
        stage.show();
        appDAO.conectar();
    }

    public static void main(String[] args) {
        launch();
    }
}