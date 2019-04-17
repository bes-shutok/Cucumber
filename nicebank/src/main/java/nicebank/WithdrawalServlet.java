package nicebank;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean overdraw;
        Teller teller = new AutomatedTeller(cashSlot);
        System.out.println("Request: " + request.toString());
        this.amount = new Money(request.getParameter("amount"));
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        overdraw=!teller.withdrawFrom(account, amount);
        if (overdraw) {response.getWriter().println(
                "<html><head><title>ATM</title></head>" + "<body>You have insufficient funds in your account! <br>" +
                        "<br> Your account has " + account.getBallance() + " and you tried to withdraw " + amount +
                        // for debugging purpose:
                        //.append("<br>Request: " + request.toString()).append("<br>Request parameters: " + request.getParameterMap().toString())
                        "</body>" + "</html>");
        return;
        }

        response.getWriter().println(
                "<html><head><title>ATM</title></head>" + "<body>Please take your " + amount +
                        // for debugging purpose:
                        //.append("<br>Request: " + request.toString()).append("<br>Request parameters: " + request.getParameterMap().toString())
                        "</body>" + "</html>");

    }
}
