package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * функционал Банка
 *
 * @version 0.1 alpha
 */
public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Дабавление нового клиента
     * Автоматическая проверка на наличие пользователя в базе
     *
     * @param user - Клиент @see {@link User}
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Привязка счетов клиенту
     * Если клиент существует, присваиваем ему счёт.
     * Работа происходит с ссылочными переменными. Поэтому изменения
     * происходят без использования put
     *
     * @param passport - номер паспорта клиента
     * @param account  - номер счета клиента
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * поиск клиента по номеру паспорта
     *
     * @param passport - номер паспорта клиента
     * @return {@link User}
     */
    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (passport.equals(user.getPassport())) {
                return user;
            }
        }
        return null;
    }

    /**
     * Поиск счетов принадлижащих клиенту, по паспорту и номеру счета
     * 1. проверяем данный человек клиент банка
     * 2. Находим его счёта.
     *
     * @param passport  - номер паспорта клиента
     * @param requisite - номер счета клиента
     * @return {@link Account}
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            for (Account account : accounts) {
                if (requisite.equals(account.getRequisite())) {
                    return account;
                }
            }
        }
        return null;
    }

    /**
     * Перевод денег с счета на счет
     * 1. Определяем являются ли клиентами банка
     * 2. Проверяем достаточно ли денег на счете для перевода
     * 3. Осуществляем превод.
     *
     * @param srcPassport   - Номер паспорт отправителя
     * @param srcRequisite  - Номер счета отправителя
     * @param destPassport  - Номер паспорт получателя
     * @param destRequisite - Номер счета получателя
     * @param amount        - Сумма перевода
     * @return true - перевод успешено завершён
     * false - перевод не удалось совершить
     * TO DO: 29.12.2021 логирование причин отказа в переводе
     * TO DO не пропускается stylecheck
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account account1 = findByRequisite(srcPassport, srcRequisite);
        Account account2 = findByRequisite(destPassport, destRequisite);

        if (account1 != null && account2 != null && account1.getBalance() >= amount) {
            account1.setBalance(account1.getBalance() - amount);
            account2.setBalance(account2.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
