package shopping.dao;

import shopping.bean.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static void main(String []args){
        System.out.println(new ProductDAO().ListProduct().size());
    }

    //获取商品信息
    public List<Product> ListProduct(){
        List<Product> products = new ArrayList<Product>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cart?useSSL=false","root","123456");

            String sql = "select * from product order by id desc";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet r = ps.executeQuery();

            while(r.next()){
                Product product = new Product();
                product.setId(r.getInt(1));
                product.setName(r.getString(2));
                product.setPrice(r.getFloat(3));
                products.add(product);
            }

            ps.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return products;
    }

    public Product getProduct(int id){
        Product result = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cart?useSSL=false","root","123456");

            String sql = "select * from product where id = ?";

            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,id);
            ResultSet r = p.executeQuery();

            if(r.next()){
                result = new Product();
                result.setId(id);

                String name = r.getString(2);
                float price = r.getFloat(3);

                result.setName(name);
                result.setPrice(price);
            }
            p.close();
            c.close();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
