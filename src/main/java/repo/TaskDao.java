package repo;

import config.HibernateSessionFactoryUtil;
import models.Status;
import models.Task;
import models.TaskBuilderImpl;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TaskDao {
    private static Connection connect;
    private static final Logger log = Logger.getLogger(TaskDao.class);
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    static {
        try {
            Class.forName(DRIVER);
            try {
                connect = getConnection();
            } catch (SQLException | IOException throwables) {
                log.error(throwables);
            }
        } catch (ClassNotFoundException e) {
            log.error(e);
        }
    }

    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("C:\\Users\\homie\\Desktop\\Task-Manager\\src\\main\\resources\\database.properties"))){
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    } // в отдельный класс!

    public List<Task> getTaskByName(String name) {
        List<Task> taskList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connect.prepareStatement("select * from Task where name = ?")) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
//                Task task1 = new Task();
//                task.setId(resultSet.getInt(1));
//                task.setName(resultSet.getString(2));
//                task.setDescription(resultSet.getString(3));
//                task.setTime(resultSet.getObject(4, Calendar.class));
//                task.setContacts(resultSet.getString(5));
//                taskList.add(task);
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

    public List<Task> getCompletedTasks() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Task> listTask  = session
                .createQuery("FROM Task WHERE status = 'COMPLETED'", Task.class).list();
        session.close();
        return listTask;
    }

    public List<Task> getExpiredTasks() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Task> listTask  = session
                .createQuery("FROM Task WHERE status = 'EXPIRED'", Task.class).list();
        session.close();
        return listTask;
    }

    public List<Task> getNotExpiredTasks() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Task> listTask  = session
                .createQuery("FROM Task WHERE status = 'ACTIVE'", Task.class).list();
        session.close();
        return listTask;
    }

    public Task searchTaskClosestInTime () {
        Task task = null;

        if (connect != null) {
            try(PreparedStatement ps = connect.prepareStatement("SELECT * FROM task WHERE time >= NOW() and status = 'ACTIVE' order by time asc limit 1")) {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next()) {
                    task = new TaskBuilderImpl()
                            .setId(resultSet.getInt(1))
                            .setName(resultSet.getString(2))
                            .setDescription(resultSet.getString(3))
                            .setTime(resultSet.getObject(4, LocalDateTime.class))
                            .setContacts(resultSet.getString(5))
                            .setStatus(resultSet.getString(6)).build();
                    return task;
                }
                return null;
            } catch (SQLException throwables) {
                log.error(throwables);
            }
        }
        return null;
    }

    public void completeTask(Task task) {
        task.status = Status.COMPLETED;
        update(task);
    }

    public void postponeTask(Task task, LocalDateTime dateTime) {
        task.time = dateTime;
        update(task);
    }

    public void deleteAndCompleteTask(Task task) {
        delete(task);
    }


    public void expiredTask(Task task) {
        task.status = Status.EXPIRED;
        update(task);
    }
}
