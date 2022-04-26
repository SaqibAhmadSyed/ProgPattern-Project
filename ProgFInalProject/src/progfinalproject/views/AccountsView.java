/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progfinalproject.views;

import progfinalproject.models.AccountsModel;
import java.util.Map;

/**
 *
 * @author Kosta Nikopoulos and Saqib Ahmad Syed
 */
public class AccountsView {
    public void printAccountsTable(Map map) {
        System.out.println(map);
    }

    public void printAccounts(AccountsModel am) {
        System.out.println(am);
    }
}
