package nicebank;

import org.apache.log4j.Logger;
import org.javalite.activejdbc.Base;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


class WithdrawalServlet extends HttpServlet {
    private Account account;
    private CashSlot cashSlot;
    private Money amount;
    private static final Logger logger = Logger.getLogger(WithdrawalServlet.class);

    WithdrawalServlet(CashSlot cashSlot, Account account){
        this.account=account;
        this.cashSlot= cashSlot;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!Base.hasConnection()){
            logger.info("WithdrawalServlet initializing DB connection");
            Base.open(
                    "com.mysql.cj.jdbc.Driver",
                    "jdbc:mysql://localhost/bank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "teller", "password");
        }
        logger.info("Starting doPost of WithdrawalServlet");
        boolean overdraw;
        String amountString;
        Teller teller = new AutomatedTeller(cashSlot);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        amountString=request.getParameter("amount");


        if (amountString !=null && amountString.equals("getBalance")) {response.getWriter().println(
                "<html><head><title>ATM</title></head>" + "<body><header><h1>Your balance: " + account.getBalance() + "</h1>" +
                    // for debugging purpose:
                    // "<br>Request: " + request.toString() + "<br>Request parameters: " + request.getParameterMap().toString() +
                 "</header></body></html>");
        return;
        }

        this.amount = new Money(amountString);

        overdraw=!teller.withdrawFrom(account, amount);
        if (overdraw) {response.getWriter().println(
                "<html><head><title>ATM</title></head>" + "<body><header><h1>You have insufficient funds in your account!<br>" +
                        "<br> Your account has " + account.getBalance() + " and you tried to withdraw " + amount + "</h1>" +
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
