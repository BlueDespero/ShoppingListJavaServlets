package com.shopping.shoppinglistjavaservlets;

import com.shopping.helperobjects.ShoppingItem;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "shoppingList", value = "/shoppinglist")
public class ShoppingListServlet extends HttpServlet {

    private PrintWriter out;

    private void add_category_form() {
        this.out.println("<div>");
        this.out.println("<form action=/ShoppingListJavaServlets/addcategory>");
        this.out.println("<label for=name>New category:</label>");
        this.out.println("<input id=name name=name type=text>");
        this.out.println("<button>Add</button>");
        this.out.println("</form>");

        this.out.println("</div>");
    }

    private void add_item_form(String category) {
        this.out.println("<div>");

        this.out.println(String.format("<input type = \"hidden\" name = \"category\" value = %s />\n", category));
        this.out.println("<label for=name>New item:</label>");
        this.out.println("<input id=name name=name type=text>");
        this.out.println("<label for=name>Initial amount:</label>");
        this.out.println("<input id=name name=amount type=number>");
        this.out.println("<button>Add</button>");

        this.out.println("</div>");
    }

    private void list_category(Map.Entry<String, HashMap<String, ShoppingItem>> category) {
        this.out.println(String.format("<form action=/ShoppingListJavaServlets/additem>", category.getKey()));
        this.out.println("<fieldset>");
        this.out.println("<legend>" + category.getKey() + "</legend>");

        for (Map.Entry<String, ShoppingItem> item : category.getValue().entrySet()) {
            list_item(item.getValue());
        }

        this.out.println("<br>");
        this.add_item_form(category.getKey());
        this.out.println("</fieldset>");
        this.out.println("</form>");
    }

    private void list_item(ShoppingItem item) {
        this.out.println("<div>");

        this.out.println(item.name);

        this.out.println(String.format("<a href=\"/ShoppingListJavaServlets/increaseitem?category=%s&item=%s\">", item.category, item.name));
        this.out.println("<input type=\"button\" value=\"+\" />");
        this.out.println("</a>");
        this.out.println(item.amount);
        this.out.println(String.format("<a href=\"/ShoppingListJavaServlets/decreaseitem?category=%s&item=%s\">", item.category, item.name));
        this.out.println("<input type=\"button\" value=\"-\" />");
        this.out.println("</a>");

        this.out.println("</div>");
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

        this.out.println("<head>");
        this.out.println("<link rel=\"stylesheet\" href=\"mainstyle.css\">");
        this.out.println("</head>");

        this.out.println("<html><body>");

        this.out.println("<img src=\"shopping_basket.png\" alt=\"Shopping backet\" width=\"500\" height=\"500\">");
        this.out.println("<div class=\"main\">");
        this.list_all_categories(cart);
        this.add_category_form();
        this.out.println("</div>");

        this.out.println("</body></html>");
    }

    public void destroy() {
    }
}