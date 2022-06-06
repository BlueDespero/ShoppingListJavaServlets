package com.shopping.shoppinglistjavaservlets;

import com.shopping.helperobjects.ShoppingItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;


@WebServlet(name = "addcategory", value = "/addcategory")
public class AddCategoryServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String category_name = request.getParameter("name");

        HttpSession session = request.getSession();
        HashMap<String, HashMap<String, ShoppingItem>> cart = (HashMap<String, HashMap<String, ShoppingItem>>) session.getAttribute("cart");
        cart.put(category_name, new HashMap<>());
        session.setAttribute("cart", cart);

        request.getRequestDispatcher("/shoppinglist").forward(request, response);
    }

    public void destroy() {
    }
}