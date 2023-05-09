import Model.BankAccount;
import Util.JsonSerialize;
import repository.AccountRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        JsonSerialize<BankAccount>bankAccountJsonSerialize=new JsonSerialize<>();
        AccountRepositoryImpl accountRepository=new AccountRepositoryImpl();
        accountRepository.populateData();
        List<BankAccount> bankAccountList = accountRepository.findAll();
        bankAccountList.stream().map(acc->bankAccountJsonSerialize.toJson(acc))
                .forEach(System.out::println);

    }
}
