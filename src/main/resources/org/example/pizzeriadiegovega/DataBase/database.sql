CREATE DATABASE IF NOT EXISTS pizzeriadiego DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE pizzeriadiego;

CREATE TABLE IF NOT EXISTS pizzas (
  nombre VARCHAR(45) NOT NULL PRIMARY KEY,
  descripcion VARCHAR(90) NOT NULL,
  precio DOUBLE NOT NULL
);

INSERT INTO pizzas (nombre, descripcion, precio) VALUES
('Margherita', 'Pizza clásica con tomate, mozzarella y albahaca fresca.', 8.50),
('Pepperoni', 'Pizza con salsa de tomate, mozzarella y rodajas de pepperoni.', 9.00),
('Cuatro Quesos', 'Deliciosa mezcla de mozzarella, gorgonzola, parmesano y ricotta.', 10.50),
('Hawaiiana', 'Pizza con jamón, piña y queso mozzarella.', 9.50),
('Vegetariana', 'Pizza con verduras frescas, como pimientos, cebolla y champiñones.', 9.00),
('Barbacoa', 'Pizza con salsa barbacoa, pollo, cebolla y queso cheddar.', 11.00),
('Diabla', 'Pizza picante con salsa de tomate, jalapeños y pepperoni.', 10.00),
('Mariscos', 'Pizza con una variedad de mariscos, ajo y perejil.', 12.00),
('Bacon y Cebolla', 'Pizza con bacon crujiente, cebolla caramelizada y mozzarella.', 10.50),
('Prosciutto y Rúcula', 'Pizza con jamón crudo, rúcula fresca y lascas de parmesano.', 12.50);
