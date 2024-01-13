package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE USERS " +
                "(id INTEGER not NULL, " +
                " name VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query insertQuery = session.createSQLQuery("" + sql);
        try {
            insertQuery.executeUpdate();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        String sql = "drop table USERS";
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("" + sql);
        try {
            query.executeUpdate();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into USERS (name, lastName, age) values (?, ?, ?)";
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("" + sql);
        query.setParameter(1, name);
        query.setParameter(2, lastName);
        query.setParameter(3, age);
        try {
            query.executeUpdate();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM USERS WHERE id = " + id;
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        try {
            query.executeUpdate();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "FROM User";
        Session session = HibernateUtil.getSession();
        session.getTransaction();
        Query query = session.createQuery(sql);
        try {
             users = (List<User>) query.list();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "delete from users";
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("" + sql);
        try {
            query.executeUpdate();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
//
    }
}
