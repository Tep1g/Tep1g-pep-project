package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public boolean login(Account account) {
        return accountDAO.getAccountIdByUserAndPass(account);
    }

    public boolean register(Account account) {
        if ((account.getUsername().length() >= 4) && (!accountDAO.checkAccountExists(account)) ) {
            return accountDAO.insertAccount(account);
        }
        return false;
    }
}
