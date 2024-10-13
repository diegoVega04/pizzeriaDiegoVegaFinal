package org.example.pizzeriadiegovega;

import javafx.scene.control.Alert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class appDAO {

    private static Connection conexion;

    public static void conectar () throws IOException, ClassNotFoundException, SQLException {
        Properties prop = new Properties();
        try (InputStream input = appDAO.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new FileNotFoundException("El archivo database.properties no se encontró en el classpath.");
            }
        prop.load(input);

        String name = prop.getProperty("name");
        String host = prop.getProperty("host");
        String port = prop.getProperty("port");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void desconectar () throws SQLException {
        conexion.close();
    }
    //metodo para comprobar si ya existe una pizza antes de añadirla
    public static boolean comprobar (Pizza pizza) {
        String sql = "SELECT COUNT(*) FROM pizzas WHERE nombre = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, pizza.getNombre());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void añadirPizza (Pizza pizza) {
        if (!comprobar(pizza)) {
            String sql = "INSERT INTO pizzas (nombre, descripcion, precio) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, pizza.getNombre());
                stmt.setString(2, pizza.getDesc());
                stmt.setDouble(3, pizza.getPrecio());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Ya existe una pizza con ese nombre");
        }
    }

    public static void eliminarPizza (String pizza) {
        String sql = "DELETE FROM pizzas WHERE nombre = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, pizza);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pizza eliminada: " + pizza);
            } else {
                System.out.println("No se encontró ninguna pizza con el nombre: " + pizza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int numPizzas () {
        String sql = "select count(*) as total from pizzeriadiego.pizzas;";
        int totalPizzas = 0;
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                totalPizzas = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPizzas;
    }

    public static String nomPizza (int i) {
        String sql = "SELECT nombre FROM pizzas LIMIT ?, 1";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, i - 1);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nombre");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String descPizza (int i) {
        String sql = "SELECT descripcion FROM pizzas LIMIT ?, 1";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, i - 1);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("descripcion");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static double precioPizza (int i) {
        String sql = "SELECT precio FROM pizzas LIMIT ?, 1";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, i - 1);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("precio");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
