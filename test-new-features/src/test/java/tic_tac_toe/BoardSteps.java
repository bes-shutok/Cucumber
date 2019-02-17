package tic_tac_toe;

import cucumber.api.*;
import cucumber.api.java.en.*;

import java.util.ArrayList;
import java.util.List;

public class BoardSteps {
    private List<List<String>> board;
    @Given("^a board like this:$")
    public void aBoardLikeThis(DataTable table) {
        this.board = new ArrayList<List<String>>();
        for (List<String> row : table.raw()) {
            this.board.add(new ArrayList<String>(row));
        }
    }

    @When("^player x plays in row (\\d+), column (\\d+)$")
    public void playerXPlaysInRowColumn(int row, int col) {
        System.out.println(board.toString());
        board.get(row).set(col, "x");
        System.out.println(board.toString());
    }

    @Then("^the board should look like this:$")
    public void theBoardShouldLookLikeThis(DataTable expectedTable){
        expectedTable.diff(board);
    }

}
