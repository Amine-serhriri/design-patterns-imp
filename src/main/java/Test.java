import Model.BankAccount;
import enums.AccountStatus;
import enums.AccountType;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        BankAccount bankAccount=new BankAccount();
        bankAccount.setAccountId(1L);
        bankAccount.setCurrency("Mad");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        bankAccount.setStatus(AccountStatus.CREATED);

        BankAccount bankAccount1=bankAccount.clone();
        System.out.println(bankAccount1);


    }
}
