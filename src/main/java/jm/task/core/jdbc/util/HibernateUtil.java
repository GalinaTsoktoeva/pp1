package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory concreteSessionFactory;

    static {
        try {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url","jdbc:postgresql://localhost:5432/mailing");//"jdbc:postgresql://localhost/" + database.trim());
            prop.setProperty("hibernate.connection.username", "postgres");
            prop.setProperty("hibernate.connection.password", "12345");
            prop.setProperty("dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
            prop.setProperty("hibernate.show_sql", "true");
//            prop.setProperty("hibernate.hbm2ddl.auto", "create");

            concreteSessionFactory = new Configuration()
                    .addProperties(prop)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory()
            ;
        }
        catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() throws HibernateException {
        return concreteSessionFactory.openSession();
    }



}