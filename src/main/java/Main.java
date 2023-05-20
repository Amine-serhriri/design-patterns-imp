import Model.BankAccount;
import Util.JsonSerialize;
import enums.AccountStatus;
import repository.AccountRepositoryImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) throws IOException {
        JsonSerialize<BankAccount>bankAccountJsonSerialize=new JsonSerialize<>();
        AccountRepositoryImpl accountRepository=AccountRepositoryImpl.getInstance();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                accountRepository.populateData();
            }).start();
        }

        System.out.println("tape a character");
        System.in.read();

        List<BankAccount> bankAccountList = accountRepository.findAll();

        bankAccountList.stream().map(acc->bankAccountJsonSerialize.toJson(acc))
                .forEach(System.out::println);

        System.out.println("=============");
        BankAccount bankAccount = accountRepository.findById(1L).orElse(null);
        if(bankAccount!=null){
            System.out.println(bankAccountJsonSerialize.toJson(bankAccount));
        }


    }
}
