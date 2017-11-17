package com.capg.task;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import org.json.JSONObject;

import java.io.*;
import java.sql.SQLException;

import java.util.List;

public class TaskManager implements HttpHandler {

    private Database database = new Database();

    TaskManager() throws SQLException {
    }

    public void handle(HttpExchange httpExchange) throws IOException {

        String response = "";
        String method = httpExchange.getRequestMethod();

        // Send a view to the browser
        if(method.equals("GET")) {
            try {
                List<Task> tasks = database.getAllTasks();
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/task-manager.twig");
                JtwigModel model = JtwigModel.newModel().with("tasks", tasks);
                response = template.render(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } // If the task was moved to another taskbox, update database
        else if(method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            executeFormData(formData);
        }

        // Send response to the browser
        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void executeFormData(String formData) throws UnsupportedEncodingException {
        // Parse formData
        JSONObject object = new JSONObject(formData);
        Integer taskId = object.getInt("buttonId");
        String taskboxName = object.getString("taskboxName");

        // Update task in the database
        Task task = database.getTask(taskId);
        task.setTaskboxId(taskboxName);
        database.updateTask(task);
    }
}