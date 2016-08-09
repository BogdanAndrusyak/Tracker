package ru.parsentev.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.*;
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
    public List<User> getUsers() {
        final List<User> users = new ArrayList<>();
        try {
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery("select u.id, u.name, u.login, u.password, u.email, u.country, u.city, u.create_date, u.role_id, r.name as role_name from users as u join roles as r on u.role_id = r.id order by u.id");
            while(rs.next()) {
                //todo work with date not good
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(rs.getTimestamp("create_date").getTime());

                users.add(new User(rs.getInt("id"), new Role(rs.getInt("role_id"), rs.getString("role_name")), rs.getString("name"), rs.getString("login"),
                        rs.getString("password"), rs.getString("email"), rs.getString("country"), rs.getString("city"), calendar, this.getItemsByUserId(rs.getInt("id"))));
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public int addUser(User user) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("insert into users(name, login, password, email, country, city, create_date, role_id) values(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getCountry());
            statement.setString(6, user.getCity());
            statement.setTimestamp(7, new Timestamp(user.getCreateDate().getTime().getTime()));
            statement.setInt(8, user.getRole().getId());

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
            PreparedStatement statement = this.connection.prepareStatement("update users set name = ?, login = ?, password = ?, email = ?, country = ?, city = ?,role_id = ? where id = ?");
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getCountry());
            statement.setString(6, user.getCity());
            statement.setInt(7, user.getRole().getId());
            statement.setInt(8,user.getId());
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
    public User getUserById(int id) {
        User user;
        try {
            PreparedStatement statement = this.connection.prepareStatement("select u.id, u.name, u.login, u.password, u.email, u.country, u.city, u.create_date, u.role_id, r.name as role_name from users as u join roles as r on u.role_id = r.id where u.id = ?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(rs.getTimestamp("create_date").getTime());

                user = new User(rs.getInt("id"), new Role(rs.getInt("role_id"), rs.getString("role_name")), rs.getString("name"),
                        rs.getString("login"), rs.getString("password"), rs.getString("email"), rs.getString("country"), rs.getString("city"),calendar, this.getItemsByUserId(rs.getInt("id")));
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

    @Override
    public int addItem(Item item) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("insert into items(name, description, create_date, user_id) values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setTimestamp(3, new Timestamp(item.getCreateDate().getTime().getTime()));
            statement.setInt(4, item.getAuthorId());
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

    // todo need realization with getUsers data for administration tools
    @Override
    public List<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<>();

        try {
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery("select i.id, i.name, i.description, i.create_date, i.user_id, " +
                    "c.id as comment_id, c.description as comment_description, c.create_date as comment_create_date, " +
                    "f.id as file_id, f.file from items as i \n" +
                    "left join users as u on i.user_id = u.id\n" +
                    "left join comments as c on c.item_id = i.id\n" +
                    "left join files as f on f.item_id = i.id order by i.id; ");
            while (rs.next()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(rs.getTimestamp("create_date").getTime());

                items.add(new Item(rs.getInt("id"), rs.getString("name"), rs.getString("description"), calendar, rs.getInt("user_id"), getCommentsByItemId(rs.getInt("id")), getFilesByItemId(rs.getInt("id"))));
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        return items;
    }

    @Override
    public List<Item> getItemsByUserId(int userId) {
        ArrayList<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = this.connection.prepareStatement("select i.id, i.name, i.description, i.create_date, i.user_id from items as i \n" +
                    "left join users as u on i.user_id = u.id where u.id = ? order by i.id;");

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(rs.getTimestamp("create_date").getTime());

                items.add(new Item(rs.getInt("id"), rs.getString("name"), rs.getString("description"), calendar, rs.getInt("user_id"), this.getCommentsByItemId(rs.getInt("id")), this.getFilesByItemId(rs.getInt("id"))));
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        return items;
    }

    //todo maybe will do this method private not Override?
    private Collection<Comment> getCommentsByItemId(int id) {
        Collection<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement statement = this.connection.prepareStatement("select c.id, c.description, c.create_date, c.item_id from comments as c left join items as i on c.item_id = i.id where i.id = ? order by c.id;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Calendar createDate = Calendar.getInstance();
                createDate.setTimeInMillis(rs.getTimestamp("create_date").getTime());

                comments.add(new Comment(rs.getInt("id"), rs.getString("description"), createDate));
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return comments;
    }

    // todo and this
    private Collection<ru.parsentev.models.File> getFilesByItemId(int id) {
        Collection<ru.parsentev.models.File> files = new ArrayList<>();
        try {
            PreparedStatement statement = this.connection.prepareStatement("select f.id, f.file, f.item_id from files as f left join items as i on f.item_id = i.id where i.id = ? order by f.id; ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                files.add(new ru.parsentev.models.File(rs.getInt("id"), rs.getBytes("file")));
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return files;
    }

    @Override
    public void deleteItem(int id) {
        try {
            //first must delete all comments and files whom references on this item
            PreparedStatement statement1 = this.connection.prepareStatement("delete from comments where item_id = ?");
            statement1.setInt(1, id);
            statement1.executeUpdate();

            PreparedStatement statement2 = this.connection.prepareStatement("delete from files where item_id = ?");
            statement1.setInt(1, id);
            statement1.executeUpdate();

            PreparedStatement statement = this.connection.prepareStatement("delete from items where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void editItem(Item item) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("update items set name = ?, description = ? where id = ?");
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setInt(3, Integer.valueOf(item.getId())); //todo change id on int type
            statement.executeUpdate();
            /*todo need add delete comments with item too*/
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    @Override
    public void addCommentToItem(int id, Comment comment) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("insert into comments(description, create_date, item_id) values(?, ?, ?)");
            statement.setString(1, comment.getDescription());
            statement.setTimestamp(2, new Timestamp(comment.getCreateDate().getTime().getTime()));
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    // todo realization not easy for me now, need rewrite all code, categories must be priory element than item in bd
    @Override
    public void addCategoryToUser(int id, Category category) {
//        PreparedStatement statement = this.connection.prepareStatement()
    }
}
