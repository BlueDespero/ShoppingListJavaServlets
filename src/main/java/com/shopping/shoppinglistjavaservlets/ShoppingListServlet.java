package com.shopping.shoppinglistjavaservlets;

import com.shopping.helperobjects.ShoppingItem;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "shoppingList", value = "/shoppinglist")
public class ShoppingListServlet extends HttpServlet {

    private PrintWriter out;

    private void add_category_form() {

    }

    private void add_item_form() {

    }

    private void list_category(Map.Entry<String, HashMap<String, ShoppingItem>> category) {
        this.out.println("<form id=payment>");
        this.out.println("<fieldset>");
        this.out.println("<legend>" + category.getKey() + "</legend>");
        this.out.println("<ul>");

        for (Map.Entry<String, ShoppingItem> item : category.getValue().entrySet())
            list_item(item.getValue());

        this.add_item_form();

        this.out.println("</ul>");
        this.out.println("</fieldset>");
        this.out.println("</form>");
    }

    private void list_item(ShoppingItem item) {
        this.out.println("<li>");
        this.out.println("<div>");

        this.out.println(item.name + "         ");

        this.out.println(String.format("<a href=\"/ShoppingListJavaServlets/increaseitem?category=%s&item=%s\">", item.category, item.name));
        this.out.println("<input type=\"button\" value=\"+\" />");
        this.out.println("</a>");
        this.out.println(item.amount);
        this.out.println(String.format("<a href=\"/ShoppingListJavaServlets/decreaseitem?category=%s&item=%s\">", item.category, item.name));
        this.out.println("<input type=\"button\" value=\"-\" />");
        this.out.println("</a>");

        this.out.println("</div>");
        this.out.println("</li");
    }

    private void list_all_categories(HashMap<String, HashMap<String, ShoppingItem>> cart) {
        for (Map.Entry<String, HashMap<String, ShoppingItem>> category : cart.entrySet())
            list_category(category);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        this.out = response.getWriter();

        HttpSession session = request.getSession();

        if (null == session.getAttribute("cart")) {
            HashMap<String, ShoppingItem> diary = new HashMap<>();
            diary.put("milk", new ShoppingItem("milk", "diary", 1));
            diary.put("cheese", new ShoppingItem("cheese", "diary", 2));
            HashMap<String, HashMap<String, ShoppingItem>> cart_put = new HashMap<>();
            cart_put.put("diary", diary);
            session.setAttribute("cart", cart_put);
        }


        HashMap<String, HashMap<String, ShoppingItem>> cart = (HashMap<String, HashMap<String, ShoppingItem>>) session.getAttribute("cart");


        this.out.println("<html><body>");

        this.list_all_categories(cart);
        this.add_category_form();

        this.out.println("</body></html>");
    }

    public void destroy() {
    }
}