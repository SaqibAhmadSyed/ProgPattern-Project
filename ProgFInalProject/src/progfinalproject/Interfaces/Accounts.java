package progfinalproject.Interfaces;

import progfinalproject.models.AccountsModel;

public interface Accounts {

    /**
     * Creates an account and stores it in the database
     * @param cId Client Id
     * @param accountType Account type
     */
    public void createAccount(int cId, String accountType);

    /**
     * reads an account based on user's account id
     * @param id account id
     * @return AccountModel object that stored data from database
     */
    public AccountsModel readAccount(int id);

    /**
     * deactivated an account only if the accounts balance is 0$. WIll put isActive data to true
     * @param id account id
     */
    public void deactivateAccount(int id);

    /**
     * deposit a desired amount of money into the account
     * @param id account id
     * @param depositAmount amount of money to deposit
     */
    public void addBalance(int id, double depositAmount);

    /**
     * displays all the account in the database
     */
    public void readAllAccounts();
}
