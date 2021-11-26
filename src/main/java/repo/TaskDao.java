package repo;

import com.sun.istack.Nullable;
import config.DatabaseConnection;
import config.HibernateSessionFactoryUtil;
import models.Status;
import models.Task;
import models.TaskBuilderImpl;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {
    private static Connection connect;
    private static final Logger log = Logger.getLogger(TaskDao.class);
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(DRIVER);
            try {
                connect = DatabaseConnection.getConnection();
            } catch (SQLException | IOException throwables) {
                log.error(throwables);
            }
        } catch (ClassNotFoundException e) {
            log.error(e);
        }
    }

    public List<Task> getTaskByName(String name) {
        List<Task> taskList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connect.prepareStatement("select * from Task where name = ?")) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Task task = new TaskBuilderImpl()
                        .setId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setDescription(resultSet.getString(3))
                        .setTime(resultSet.getObject(4, LocalDateTime.class))
                        .setContacts(resultSet.getString(5))
                        .setStatus(resultSet.getString(6))
                        .build();
                taskList.add(task);
            }
        } catch (SQLException e) {
            log.error(e);
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
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
    }

    public void update(Task t) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            session.close();
        }
    }

    public void delete(Task t) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            session.close();
        }
    }

    public void deleteCompletedTask() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.createQuery("DELETE FROM Task WHERE status = '" + Status.COMPLETED + "'");
            session.close();
        }
    }

    public List<Task> getCompletedTasks() {
        List<Task> listTask;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            listTask = session
                    .createQuery("FROM Task WHERE status = '" + Status.COMPLETED + "'", Task.class).list();
            session.close();
        }
        return listTask;
    }

    public List<Task> getExpiredTasks() {
        List<Task> listTask;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            listTask = session
                    .createQuery("FROM Task WHERE status = '" + Status.EXPIRED + "'", Task.class).list();
            session.close();
        }
        return listTask;
    }

    public List<Task> getActiveTasks() {
        List<Task> listTask;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
             listTask = session
                     .createQuery("FROM Task WHERE status = '" + Status.ACTIVE + "'", Task.class).list();
            session.close();
        }
        return listTask;
    }

    @Nullable
    public List<Task> searchTasksClosestInTime() {
        List<Task> taskList = new ArrayList<>();
        if (connect != null) {
            try(PreparedStatement ps = connect
                    .prepareStatement("SELECT * FROM task WHERE " +
                            "time >= NOW() and " +
                            "status = '"+ Status.ACTIVE + "' order by time asc")) {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next()) {
                    Task task = new TaskBuilderImpl()
                            .setId(resultSet.getInt(1))
                            .setName(resultSet.getString(2))
                            .setDescription(resultSet.getString(3))
                            .setTime(resultSet.getObject(4, LocalDateTime.class))
                            .setContacts(resultSet.getString(5))
                            .setStatus(resultSet.getString(6)).build();
                    taskList.add(task);
                    return taskList;
                }
                return null;
            } catch (SQLException throwables) {
                log.error(throwables);
            }
        }
        return null;
    }
}
