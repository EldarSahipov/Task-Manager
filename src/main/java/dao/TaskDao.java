package dao;

import config.HibernateSessionFactoryUtil;
import models.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TaskDao {
    private static Connection connect;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/task", "root", "kali");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Task> getTaskByName(String name) {
        List<Task> taskList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connect.prepareStatement("select * from Task where name = ?");

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt(1));
                task.setName(resultSet.getString(2));
                task.setDescription(resultSet.getString(3));
                task.setTime(resultSet.getObject(4, Calendar.class));
                task.setContacts(resultSet.getString(5));
                taskList.add(task);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public List<Task> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Task> list = session.createQuery("FROM Task", Task.class).list();
        session.close();
        return list;
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

    public List<Task> getCompletedTask() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Task> listTask  = session
                .createQuery("FROM Task WHERE status = 'COMPLETED'", Task.class).list();
        transaction.commit();
        session.close();
        return listTask;
    }

    public List<Task> getExpiredTask() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Task> listTask  = session
                .createQuery("FROM Task WHERE status = 'EXPIRED'", Task.class).list();
        transaction.commit();
        session.close();
        return listTask;
    }

    public List<Task> getNotExpiredTask() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Task> listTask  = session
                .createQuery("FROM Task WHERE status = 'NOT_EXPIRED'", Task.class).list();
        transaction.commit();
        session.close();
        return listTask;
    }
}
