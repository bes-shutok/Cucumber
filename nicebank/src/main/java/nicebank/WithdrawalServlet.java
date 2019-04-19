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
        String amountString;
        Teller teller = new AutomatedTeller(cashSlot);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        amountString=request.getParameter("amount");


        if (amountString !=null && amountString.equals("getBalance")) {response.getWriter().println(
                "<html><head><title>ATM</title></head>" + "<body><header><h1>Your balance: " + account.getBallance() + "</h1>" +
                    // for debugging purpose:
                    // "<br>Request: " + request.toString() + "<br>Request parameters: " + request.getParameterMap().toString() +
                 "</header></body></html>");
        return;
        }

        this.amount = new Money(amountString);

        overdraw=!teller.withdrawFrom(account, amount);
        if (overdraw) {response.getWriter().println(
                "<html><head><title>ATM</title></head>" + "<body><header><h1>You have insufficient funds in your account!<br>" +
                        "<br> Your account has " + account.getBallance() + " and you tried to withdraw " + amount + "</h1>" +
                        // for debugging purpose:
                        // "<br>Request: " + request.toString() + "<br>Request parameters: " + request.getParameterMap().toString() +
                        "</header></body></html>");
        return;
        }

        response.getWriter().println(
                "<html><head><title>ATM</title></head>" + "<body><header><h1>Please take your " + amount + "</h1>" +
                        // for debugging purpose:
                        // "<br>Request: " + request.toString() + "<br>Request parameters: " + request.getParameterMap().toString() +
                        "</header></body></html>");

    }
}
