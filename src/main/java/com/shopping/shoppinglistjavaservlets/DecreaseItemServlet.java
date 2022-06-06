package com.shopping.shoppinglistjavaservlets;

import com.shopping.helperobjects.ShoppingItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


@WebServlet(name = "decreaseitem", value = "/decreaseitem")
public class DecreaseItemServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String category = request.getParameter("category");
        String item_name = request.getParameter("item");

        HttpSession session = request.getSession();
        HashMap<String, HashMap<String, ShoppingItem>> cart = (HashMap<String, HashMap<String, ShoppingItem>>) session.getAttribute("cart");
        cart.get(category).get(item_name).amount = Math.max(cart.get(category).get(item_name).amount - 1, 0);
        session.setAttribute("cart", cart);


        request.getRequestDispatcher("/shoppinglist").forward(request, response);
    }

    public void destroy() {
    }
}