package com.vandal.dbconnect.stuff;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO {
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO products (partNumber, name, description, isForSale, price) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_PRODUCTS_SQL = "SELECT * FROM products";
    private static final String SELECT_PRODUCT_SQL_BY_ID = "SELECT * FROM products WHERE partNumber = ?";
    private static final String UPDATE_PRIZE = "UPDATE products SET price = ? WHERE productId = ?";
    private static final String DELETE_NOT_FOR_SALE = "DELETE FROM products WHERE isForSale = 0";


    public void saveProduct(Product product) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            statement.setInt(1, product.getPartNumber());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setBoolean(4, product.isForSale());
            statement.setDouble(5, product.getPrice());

            statement.executeUpdate();
            System.out.println("Product saved successfully.");
        } catch (SQLException e) {
            System.out.println("Error saving product: " + e.getMessage());
        }
    }

    public List<Product> loadAllAvailableItems() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_PRODUCTS_SQL)) {
            while (resultSet.next()) {
                int partNumber = resultSet.getInt("partNumber");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                boolean isForSale = resultSet.getBoolean("isForSale");
                double price = resultSet.getDouble("price");

                Product product = new Product(partNumber, name, description, isForSale, price);
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products: " + e.getMessage());
        }
        return products;
    }

    public void updateProductPrice(int productId, double price) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRIZE)) {
            statement.setDouble(1, price);
            statement.setInt(2, productId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product price updated successfully.");
            } else {
                System.out.println("No product found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating product price: " + e.getMessage());
        }
    }

    public Product loadProductById(int partNumber) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_SQL_BY_ID)) {
            statement.setInt(1, partNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int retrievedPartNumber = resultSet.getInt("partNumber");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    boolean isForSale = resultSet.getBoolean("isForSale");
                    double price = resultSet.getDouble("price");

                    return new Product(retrievedPartNumber, name, description, isForSale, price);
                } else {
                    System.out.println("No product found with the given ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving product: " + e.getMessage());
        }

        return null;
    }

    public void deleteNotForSaleProducts() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_NOT_FOR_SALE)) {

            int rowsDeleted = statement.executeUpdate();
            System.out.println(rowsDeleted + " products deleted.");

        } catch (SQLException e) {
            System.out.println("Error deleting products: " + e.getMessage());
        }
    }
}
