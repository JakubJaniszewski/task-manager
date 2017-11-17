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
            Integer taskboxId = rs.getInt("taskbox_id");
            tasks.add(new Task(id, name, taskboxId));
        }

        return tasks;
    }

    public Task getTask(Integer id) {
        Task task = null;
        String sqlQuery = "SELECT * FROM tasks WHERE id = ? ORDER BY id;";

        try {
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, id);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                task = createTaskFromDB(result);
            }

            result.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(this.getClass().getName() + " class caused a problem!");
        }

        return task;
    }

    private Task createTaskFromDB(ResultSet result) throws SQLException {
        Integer id = result.getInt("id");
        String name = result.getString("name");
        Integer taskboxId =result.getInt("taskbox_id");

        return new Task(id, name, taskboxId);
    }

    public void updateTask(Task task) {
        Integer id = task.getId();
        String name = task.getName();
        Integer taskboxId = task.getTaskboxId();

        String sqlQuery = "UPDATE tasks "
                        + "SET name = ?, taskbox_id = ? "
                        + "WHERE id = ?;";

        try {
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, name);
            stmt.setInt(2, taskboxId);
            stmt.setInt(3, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(this.getClass().getName() + " class caused a problem!");
        }
    }
}