package db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import db.models.Student;

import java.sql.SQLException;
import java.util.List;

public class DbOrmRepository {

    private ConnectionSource connectionSource = null;
    private Dao<Student, Integer> studentDao = null;


    // Подключение к базе данных
    public void connect() throws SQLException {
        String DB_URL = "jdbc:sqlite:students.db";
        connectionSource = new JdbcConnectionSource(DB_URL);
        studentDao = DaoManager.createDao(connectionSource, Student.class);
        System.out.println("Соединение с базой данных установлено.\n");
    }

    // Отключение от базы данных
    public void disconnect() throws Exception {
        if (connectionSource != null) {
            connectionSource.close();
            System.out.println("Соединение с базой данных закрыто.\n");
        }
    }

    // Создание таблицы студентов
    public void createTableStudents() throws SQLException {
        TableUtils.createTable(connectionSource, Student.class);
        System.out.println("Таблица students проверена/создана.\n");
    }

    // Сохранение студентов в базу данных
    public void saveStudent(List<Student> students) throws SQLException {
        for (Student student : students) {
            studentDao.create(student); // Сохраняем каждого студента
        }
        System.out.println("Данные студентов сохранены в базе данных.\n");
    }

    // Получение всех студентов из базы данных
    public List<Student> getStudents() throws SQLException {
        return studentDao.queryForAll();
    }
}