package ru.blogspot.feomatr.dao.hibernate;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import ru.blogspot.feomatr.dao.AccountDAO;
import ru.blogspot.feomatr.entity.Account;

import java.util.List;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
public class AccountDAOHibImpl implements AccountDAO {

    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public List<Account> getAll() {
        List<Account> l;
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            l = session.createCriteria(Account.class).list();
        } finally {
            tx.commit();
        }
        return l;
    }

    @Override
    public Account getAccountById(Long id) {
        Account a;
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            a = (Account) session.get(Account.class, id);
        } finally {
            tx.commit();
        }
        return a;
    }

    @Override
    public Account create(Account account) {
        if (account == null) {
            return account;
        }
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(account);
        } finally {
            tx.commit();
        }
        return account;
    }

    @Override
    public boolean delete(Account account) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(account);
        } finally {
            tx.commit();
        }
        return true;
    }

    @Override
    public void update(Account account) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(account);
        } finally {
            tx.commit();
        }
    }
}
