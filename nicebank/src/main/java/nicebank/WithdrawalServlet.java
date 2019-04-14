package nicebank;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


class WithdrawalServlet extends HttpServlet {
    private Account account;
    private CashSlot cashSlot;
    private Money amount;

    WithdrawalServlet(Account account, CashSlot cashSlot){
        this.account=account;
        this.cashSlot= cashSlot;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Teller teller = new AutomatedTeller(cashSlot);
        System.out.println("Request: " + request.toString());
        this.amount = new Money(request.getParameter("amount"));
        teller.withdrawFrom(account, amount);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(
                "<html><head><title>ATM</title></head>" + "<body>Please take your " + amount +
                        // for debugging purpose:
                        //.append("<br>Request: " + request.toString()).append("<br>Request parameters: " + request.getParameterMap().toString())
                        "</body>" + "</html>");

    }
}
