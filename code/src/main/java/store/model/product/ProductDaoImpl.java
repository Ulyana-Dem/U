package store.model.product;

import store.exceptions.ProductNotFoundException;
import store.sql.DBProcessor;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductDaoImpl implements ProductDao {

    private static volatile ProductDaoImpl instance = null;
    private DBProcessor dbProcessor;


    private ProductDaoImpl() {
        try {
            dbProcessor = new DBProcessor();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static ProductDaoImpl getInstance() {

        ProductDaoImpl localInstance = instance;

        if (instance == null) {
            synchronized (ProductDaoImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ProductDaoImpl();
                }

            }

        }
        return instance;

    }


    private Stream<Product> getActualProducts() throws SQLException {
        String query = "Select * from business.products where stock > 0";
        Statement statement = dbProcessor.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Product> actualProducts = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String description = resultSet.getString("description");
            int price = resultSet.getInt("price");
            int stock = resultSet.getInt("stock");
            String imageUrl = resultSet.getString("image_url");
            actualProducts.add(new Product(new Long(id), description, new BigDecimal(price), stock, imageUrl));
        }
        return actualProducts.stream();
    }

    @Override
    public Product getProduct(Long id) {
        try {
            return getActualProducts().filter(p -> p.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new ProductNotFoundException("Product with  id: " + id.toString() + " not exists"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> findProducts(String query, String order, String sort) {
        List<Product> foundProducts;
        try {
            foundProducts = getActualProducts().collect(Collectors.toList());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<Product>();
        }
        if (query != null) {
            String[] queries = query.toUpperCase().split("\\s");

            foundProducts = foundProducts.stream()
                    .collect(Collectors.toMap(Function.identity(), product -> Arrays.stream(queries)
                            .filter(word -> product.getDescription().toUpperCase().contains(word)).count()))
                    .entrySet().stream()
                    .filter(entry -> entry.getValue() > 0)
                    .sorted(Comparator.comparing(Map.Entry<Product, Long>::getValue).reversed())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
        if (sort != null)
            return sortProducts(foundProducts, order, sort);
        return foundProducts;
    }


    private List<Product> sortProducts(List<Product> productList, String order, String sort) {
        Comparator<Product> comparator = (o1, o2) -> 0;
        if (sort.equals("description")) {
            comparator = Comparator.comparing(Product::getDescription);
        }

        if (sort.equals("price")) {
            comparator = Comparator.comparing(Product::getPrice);
        }

        if (order.equals("desc")) {
            comparator = comparator.reversed();
        }

        return productList.stream().sorted(comparator).collect(Collectors.toList());
    }


    @Override
    public void setNewStock(long productId, int newStock) {
        String query = "update business.products " +
                "set stock=" + newStock + " where id=" + productId;
        try {
            Statement statement = dbProcessor.getConnection().createStatement();
            statement.execute(query);
        }catch (SQLException ex){
            ex.printStackTrace();
            return;
        }
    }
}
