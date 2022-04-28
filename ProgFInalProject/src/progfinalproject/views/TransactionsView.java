/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject.views;

import progfinalproject.models.TransactionsModel;
import java.util.Map;

/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class TransactionsView {
    public void printTransactionsTable(Map<Integer, String> map) {
        System.out.println(map);
    }

    public void printTransactions(TransactionsModel tm) {
        System.out.println(tm);
    }
}
