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


@WebServlet(name = "additem", value = "/additem")
public class AddItemServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String category = request.getParameter("category");
        String item_name = request.getParameter("name");
        Integer item_amount = Integer.valueOf(request.getParameter("amount"));

        HttpSession session = request.getSession();
        HashMap<String, HashMap<String, ShoppingItem>> cart = (HashMap<String, HashMap<String, ShoppingItem>>) session.getAttribute("cart");
        cart.get(category).put(item_name, new ShoppingItem(item_name, category, item_amount));
        session.setAttribute("cart", cart);

        request.getRequestDispatcher("/shoppinglist").forward(request, response);
    }

    public void destroy() {
    }
}