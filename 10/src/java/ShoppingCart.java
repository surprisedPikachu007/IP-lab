import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.Enumeration;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = { "/ShoppingCart" })
public class ShoppingCart extends HttpServlet {
    String[] items = new String[] {
            "Katana", "Kimono", "Log Book", "Bean Soup" };

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<center><h3>SHOPPING CART </h3></center>");
        out.println("<hr>");
        HttpSession session = request.getSession();
        int[] purchases = (int[]) session.getAttribute("purchases");
        if (purchases == null) {
            purchases = new int[items.length];
            session.setAttribute("purchases", purchases);
        }
        out.println("<html><head><title>Shopping Cart</title>" + "</title></head><body><p>");
        if (request.getParameter("checkout") != null)
            out.println("<h1>Thanks for ordering!</h1>");
        else {
            if (request.getParameter("add") != null) {
                addPurchases(request, purchases);
                out.println("<h1>Purchase added. Please continue</h1>");
            } else {
                if (request.getParameter("clear") != null)
                    for (int i = 0; i < purchases.length; i++)
                        purchases[i] = 0;
                out.println("<h1> Please Select Your Items! </h1>");
            }
            doForm(out, request.getRequestURI());
        }
        showPurchases(out, purchases);
        out.close();
    }

    void addPurchases(HttpServletRequest request, int[] purchases) {
        for (int i = 0; i < items.length; i++) {
            String added = request.getParameter(items[i]);
            if (added != null && !added.equals(""))
                purchases[i] += Integer.parseInt(added);
        }
    }

    void doForm(PrintWriter out, String requestURI) {
        out.println("<form method=POST action=" + requestURI + ">");
        for (int i = 0; i < items.length; i++)
            out.println("Quantity <input name=\"" + items[i] + "\" value=0 size=3> of: " + items[i] +
                    "<br>");
        out.println("<p><input type=submit name=add value=\"Add To Cart\">"
                + "<input type=submit name=checkout value=\"Check Out\">"
                + "<input type=submit name=clear value=\"Clear Cart\">" + "</form>");
    }

    void showPurchases(PrintWriter out, int[] purchases)
            throws IOException {
        out.println("<hr><h2>Your Shopping Basket</h2>");
        for (int i = 0; i < items.length; i++)
            if (purchases[i] != 0)
                out.println(purchases[i] + " " + items[i] + "<br>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}