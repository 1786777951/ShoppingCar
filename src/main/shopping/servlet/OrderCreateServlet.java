package shopping.servlet;

import shopping.bean.Order;
import shopping.bean.OrderItem;
import shopping.bean.User;
import shopping.dao.OrderDAO;
import shopping.dao.OrderItemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderCreateServlet")
public class OrderCreateServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        if(null == u){
            response.sendRedirect("login.jsp");
            return;
        }

        Order o = new Order();
        o.setUser(u);

        new OrderDAO().insert(o);

        List<OrderItem> ois = (List<OrderItem>)request.getSession().getAttribute("ois");
        for(OrderItem oi:ois){
            oi.setOrder(o);
            new OrderItemDao().insert(oi);
        }

        ois.clear();

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("订单创建成功");

    }

}
