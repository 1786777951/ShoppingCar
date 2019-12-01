package shopping.dao;

import shopping.bean.Order;

import java.sql.*;

public class OrderDAO {
    public void insert(Order o){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cart?useSSL=false","root","123456");

            String sql = "insert into order_ values(null,?)";

            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,o.getUser().getId());

            p.execute();

            ResultSet r = p.getGeneratedKeys();
            if(r.next()){
                int id = r.getInt(1);
                o.setId(id);
            }
            p.close();
            c.close();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
