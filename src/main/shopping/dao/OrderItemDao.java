package shopping.dao;

import shopping.bean.OrderItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDao {
    public void insert(OrderItem oi){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cart?useSSL=false","root","123456");

            String sql = "insert into orderitem values(null,?,?,?)";

            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,oi.getProduct().getId());
            p.setInt(2, oi.getNum());
            p.setInt(3,oi.getOrder().getId());

            p.execute();

            p.close();
            c.close();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
