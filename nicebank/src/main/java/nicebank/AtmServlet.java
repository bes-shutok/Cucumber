package nicebank;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class AtmServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(
                "<html><head><title>ATM</title></head><body>" +
                        "<form action=\"/withdraw\" method=\"post\">" +
                        "<label for=\"amount\">Amount</label>" +
                        "<input type=\"text\" id=\"amount\" name=\"amount\">" +
                        "<button type=\"submit\" id=\"withdraw\">Withdraw</button>" +
                        "</form>" +
                        "<form action=\"/withdraw\" method=\"post\">" +
                        "<input type=\"hidden\" id=\"amount\" value=\"getBalance\" name=\"amount\">" +
                        "<button type=\"submit\"id=\"displayBalance\">Display Balance</button>" +
                        "</form>" +
                        "<form action=\"/withdraw\" method=\"post\">" +
                        "<input type=\"hidden\" id=\"amount\" value=\"$10.00\" name=\"amount\">" +
                        "<button type=\"submit\"  id=\"withdraw10\">Withdraw $10</button>" +
                        "</form>" +
                        "<form action=\"/withdraw\" method=\"post\">" +
                        "<input type=\"hidden\" id=\"amount\" value=\"$20.00\" name=\"amount\">" +
                        "<button type=\"submit\"id=\"withdraw20\">Withdraw $20</button>" +
                        "</form></body></html>");
    }
}