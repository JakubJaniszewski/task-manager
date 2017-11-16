package com.capg.task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private final Connection connection;
    private PreparedStatement stmt = null;

    public Database() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();

        String sqlQuery = "SELECT * FROM tasks;";

        stmt = connection.prepareStatement(sqlQuery);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            Integer usersId = rs.getInt("owners_id");
            tasks.add(new Task(id, name, usersId));
        }

        return tasks;
    }

    public void updateTask(Task task) {
        Integer id = task.getId();
        String name = task.getName();
        Integer ownersId = task.getOwnersId();

        String sqlQuery = "UPDATE tasks "
                        + "SET name = ?, owners_id = ? "
                        + "WHERE id = ?;";

        try {
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, name);
            stmt.setInt(2, ownersId);
            stmt.setInt(3, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(this.getClass().getName() + " class caused a problem!");
        }
    }
}