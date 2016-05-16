package ru.parsentev.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.Item;
import ru.parsentev.models.Role;
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
            ResultSet rs = statement.executeQuery("select u.id, u.name, u.login, u.password, u.email, u.create_date, u.role_id, r.name as role_name from users as u join roles as r on u.role_id = r.id order by id");
            while(rs.next()) {
                //todo work with date not good
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(rs.getTimestamp("create_date").getTime());

                users.add(new User(rs.getInt("id"), new Role(rs.getInt("role_id"), rs.getString("role_name")), rs.getString("name"), rs.getString("login"),
                        rs.getString("password"), rs.getString("email"), calendar));
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public int addUser(User user) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("insert into users(name, login, password, email, create_date, role_id) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setTimestamp(5, new Timestamp(user.getCreateDate().getTime().getTime()));
            statement.setInt(6, user.getRole().getId());

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
            PreparedStatement statement = this.connection.prepareStatement("update users set name = ?, login = ?, password = ?, email = ?, role_id = ? where id = ?");
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getRole().getId());
            statement.setInt(6,user.getId());
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
            PreparedStatement statement = this.connection.prepareStatement("select u.id, u.name, u.login, u.password, u.email, u.create_date, u.role_id, r.name as role_name from users as u join roles as r on u.role_id = r.id where u.id = ?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(rs.getTimestamp("create_date").getTime());

                user = new User(rs.getInt("id"), new Role(rs.getInt("role_id"), rs.getString("role_name")), rs.getString("name"),
                        rs.getString("login"), rs.getString("password"), rs.getString("email"), calendar);
                return user;
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        throw new IllegalStateException(String.format("User %s does not exists", id));
    }

    @Override
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        try {
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from roles");
            while(rs.next()) {
                roles.add(new Role(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return roles;
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
