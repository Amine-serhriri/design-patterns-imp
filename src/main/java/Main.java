import Model.BankAccount;
import Util.JsonSerialize;
import enums.AccountType;
import repository.AccountRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        JsonSerialize<BankAccount>bankAccountJsonSerialize=new JsonSerialize<>();
        AccountRepositoryImpl accountRepository=new AccountRepositoryImpl();

        accountRepository.populateData();

        List<BankAccount> bankAccountList = accountRepository.
                searchAccount(bankAccount ->
                 bankAccount.getType().equals(AccountType.CURRENT_ACCOUNT));

        bankAccountList.stream().map(acc->bankAccountJsonSerialize.toJson(acc))
                .forEach(System.out::println);

        System.out.println("=============");
        BankAccount bankAccount = accountRepository.findById(1L).orElse(null);
        if(bankAccount!=null){
            System.out.println(bankAccountJsonSerialize.toJson(bankAccount));
        }


    }
}
