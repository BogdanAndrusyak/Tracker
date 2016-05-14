package ru.parsentev.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.Item;
import ru.parsentev.models.User;
import ru.parsentev.service.Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;

/**
 * TODO: comment..
 * Created by Bogdan on 4/20/2016.
 */
public class JdbcStorage implements Storage{
    private static final Logger Log = LoggerFactory.getLogger(JdbcStorage.class);
    private final Connection connection;

    public JdbcStorage() {
        final Settings settings = Settings.getInstance();
        try {
            Class.forName("org.postgresql.Driver");
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
            this.connection.createStatement().executeUpdate(query);
    }

    @Override
    public Collection<User> users() {
        final List<User> users = new ArrayList<>();
        try {
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while(rs.next()) {
                //todo work with date not good
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(rs.getTimestamp("create_date").getTime());

                users.add(new User(rs.getInt("id"), rs.getString("name"),
                        rs.getString("login"), rs.getString("email"), calendar));
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public int addUser(User user) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("insert into users(name, login, email, create_date) values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setTimestamp(4, new Timestamp(user.getCreateDate().getTime().getTime()));

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
            PreparedStatement statement = this.connection.prepareStatement("update users set name = ?, login = ?, email = ? where id = ?");
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setInt(4,user.getId());
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
    public User get(int id) {
        User user;
        try {
            PreparedStatement statement = this.connection.prepareStatement("select * from users where id = ?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(rs.getTimestamp("create_date").getTime());

                user = new User(rs.getInt("id"), rs.getString("name"),
                        rs.getString("login"), rs.getString("email"), calendar);
                return user;
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        throw new IllegalStateException(String.format("User %s does not exists", id));
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

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

    public void deleteItem(int id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("delete from items where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
