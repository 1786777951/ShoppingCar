package shopping.servlet;

import com.sun.org.apache.xpath.internal.operations.Or;
import shopping.bean.OrderItem;
import shopping.bean.Product;
import shopping.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderItemAddServlet")
public class OrderItemAddServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //数量
        int num = Integer.parseInt( request.getParameter("num"));
        //商品ID
        int pid = Integer.parseInt(request.getParameter("pid"));
        Product p = new ProductDAO().getProduct(pid);

        OrderItem oi = new OrderItem();

        oi.setNum(num);
        oi.setProduct(p);

        List<OrderItem> ois = (List<OrderItem>)request.getSession().getAttribute("ois");

        if(null == ois){
            ois = new ArrayList<OrderItem>();
            request.getSession().setAttribute("ois",ois);
        }
        //遍历session中所有的OrderItem
        //
        //如果找到对应的product.id一样的条目，就调整其数量
        //如果没有找到，就新增加一条
        boolean found = false;
        for(OrderItem orderItem:ois){
            if(orderItem.getProduct().getId() == oi.getProduct().getId()){
                orderItem.setNum(orderItem.getNum()+oi.getNum());
                found = true;
                break;
            }
        }
        if(!found){
            ois.add(oi);
        }


        response.sendRedirect("listOrderItem");
    }
}
