package ru.job4j.bank;

import java.util.*;

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
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            List<Account> accounts = users.get(user.get());
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
    public Optional<User> findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(u -> passport.equals(u.getPassport()))
                .findFirst();
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
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            List<Account> accounts = users.get(user.get());
            return accounts.stream()
                    .filter(a -> requisite.equals(a.getRequisite()))
                    .findFirst();
        }
        return Optional.empty();
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
        Optional<Account> account1 = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> account2 = findByRequisite(destPassport, destRequisite);
        if (account1.isPresent() && account2.isPresent() && account1.get().getBalance() >= amount) {
            account1.get().setBalance(account1.get().getBalance() - amount);
            account2.get().setBalance(account2.get().getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
