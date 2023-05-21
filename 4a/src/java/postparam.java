
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class postparam extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        Enumeration e = request.getParameterNames();
        HashMap<String, String> hm = new LinkedHashMap<String, String>();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = request.getParameter(key);
            hm.put(key, value);
        }
        
        pw.println("<html><head><title>Post Parameters</title></head>");
        pw.println("<body style=\"font-family: Arial, sans-serif;\">");
        pw.println("<h1 style=\"text-align: center;\">Post Parameters</h1>");
        pw.println("<table style=\"width: 40%; margin: 0 auto;\" border=\"1\" cellpadding=\"5\" cellspacing=\"0\">");
        pw.println("<tr><th>Parameter</th><th>Value</th></tr>");
        
        for (String key : hm.keySet()) {
            pw.println("<tr><td>" + key + "</td><td>" + hm.get(key) + "</td></tr>");
        }

        pw.println("</table>");
        pw.println("</body></html>");     
        pw.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
