package repository;

import Model.BankAccount;
import Model.Director;
import enums.AccountStatus;
import enums.AccountType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountRepositoryImpl implements AccountRepository {
    private static final AccountRepositoryImpl accountRepository;
    static {
        System.out.println("Singleton initialisation ");
        accountRepository=new AccountRepositoryImpl();
    }
    private AccountRepositoryImpl(){}
    Map<Long,BankAccount>bankAccountMap = new HashMap<>();
    private long accountCount=0;
    @Override
    public BankAccount save(BankAccount bankAccount) {
        Long account_Id=++accountCount;
        bankAccount.setAccountId(account_Id);
        bankAccountMap.put(account_Id,bankAccount);
        return bankAccount;
    }

    @Override
    public List<BankAccount> findAll() {
        return bankAccountMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        BankAccount account=bankAccountMap.get(id);
        if(account==null){
            return Optional.empty();
        }
       else
           return Optional.of(account);
    }

    @Override
    public List<BankAccount> searchAccount(Predicate<BankAccount> predicate) {

        return bankAccountMap.values().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public BankAccount update(BankAccount account) {
        bankAccountMap.put(account.getAccountId(),account);
        return account;
    }

    @Override
    public void deleteById(Long id) {
        bankAccountMap.remove(id);

    }
    public void  populateData(){
        for (int i = 0; i < 10; i++) {
            BankAccount bankAccount= Director.accountBuilder()
                    .balance(10000*Math.random()*900)
                    .type(Math.random()>0.5? AccountType.SAVING_ACCOUNT:AccountType.CURRENT_ACCOUNT)
                    .status(Math.random()>0.5? AccountStatus.ACTIVATED:AccountStatus.CREATED)
                    .currency(Math.random()>0.5?"MAD":"$")
                    .build();
            save(bankAccount);

        }
    }
    public static AccountRepositoryImpl getInstance(){
        return accountRepository;
    }
}
