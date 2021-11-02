package dao;

import config.HibernateSessionFactoryUtil;
import models.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TaskDao {

    public Task get(String name) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(Task.class, name);
    }

    public List<Task> finByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Task where name = " + "'" + name + "'");
        List<Task> taskList = query.list();
        session.close();
        return taskList;
    }

    public List<Task> getAll() {
        return (List<Task>) HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("FROM Task")
                .list();
    }

    public void save(Task t) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
    }

    public void update(Task t) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
        session.close();
    }

    public void delete(Task t) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        transaction.commit();
        session.close();
    }



}
