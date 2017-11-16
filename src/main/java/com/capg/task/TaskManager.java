package com.capg.task;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

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

        // Send a form if it wasn't submitted yet.
        if(method.equals("GET")) {
            try {
                List<Task> tasks = database.getAllTasks();
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/task-manager.twig");
                JtwigModel model = JtwigModel.newModel().with("tasks", tasks);
                response = template.render(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } // If the form was submitted, retrieve it's content.
        else if(method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Task task = parseFormData(formData);
            try {
                database.updateTask(task);
                List<Task> tasks = database.getAllTasks();
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/guestbook.twig");
                JtwigModel model = JtwigModel.newModel().with("tasks", tasks);
                response = template.render(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static Task parseFormData(String formData) throws UnsupportedEncodingException {
        return new Task(1, formData, 2);
    }
}