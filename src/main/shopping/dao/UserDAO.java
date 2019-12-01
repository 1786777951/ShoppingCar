package shopping.dao;

import shopping.bean.User;

import java.sql.*;

public class UserDAO {

    public User getUser(String name,String password){
        User result = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cart?useSSL=false","root","123456");

            String sql = "select * from user where name = ? and password = ?";

            PreparedStatement p = c.prepareStatement(sql);

            p.setString(1,name);
            p.setString(2,password);

            ResultSet r = p.executeQuery();

            if(r.next()){
                result = new User();
                result.setId(r.getInt(1));
                result.setName(name);
                result.setPassword(password);
            }
            p.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return result;
    }
}
