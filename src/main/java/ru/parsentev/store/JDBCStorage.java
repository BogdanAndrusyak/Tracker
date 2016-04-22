package ru.parsentev.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.Item;
import ru.parsentev.service.Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * TODO: comment.
 * Created by Bogdan on 4/20/2016.
 */
public class JDBCStorage implements Storage{
    private static final Logger Log = LoggerFactory.getLogger(JDBCStorage.class);
    private final Connection connection;

    public JDBCStorage() {
        final Settings settings = Settings.getInstance();
        try {
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
            this.checkDb();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    private void checkDb() throws FileNotFoundException, SQLException {
            // if tables not exist
            String query = new Scanner(new File(this.getClass().getClassLoader().getResource("testDb.sql").getFile())).useDelimiter("\\Z").next();
            System.out.println(query);
            this.connection.createStatement().executeUpdate(query);
    }

    @Override
    public Collection<User> users() {
        final List<User> users = new ArrayList<>();
        try {
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while(rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public int addUser(User user) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("insert into users(name) values(?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        throw new IllegalStateException("Could not create new user");
    }

    @Override
    public void editUser(User user) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("update users set name = ? where id = ?");
            statement.setString(1, user.getName());
            statement.setInt(2,user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("delete from users where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    @Override
    public int addItem(Item item) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("insert into items(name, description, create_date, user_id) values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getName());
            statement.setString(1, item.getDescription());
            statement.setTimestamp(1, Timestamp.valueOf(item.getCreate().toString()));
            statement.setInt(1, item.author.getId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            while(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        throw new IllegalStateException("Could not create new item");
    }

    @Override
    public void editItem(Item item) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("update items set name = ?, description = ? where id = ?");
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setInt(3, Integer.valueOf(item.getId())); //todo change id on int type
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    @Override
    public void deleteItem(int id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("delete from items where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
