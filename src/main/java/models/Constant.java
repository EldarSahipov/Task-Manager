package models;

import org.apache.log4j.Logger;
import repo.TaskDao;
import java.time.format.DateTimeFormatter;

public class Constant {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    public static final String FORMAT_PATTERN = "dd.MM.yyyy HH:mm";
    public static final Logger LOGGER = Logger.getLogger(TaskDao.class);
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String FILE_NAME_JSON = "src/main/resources/text.json";
    public static final String FILE_NAME_IMAGE = "src/main/resources/icon.jpg";
}
