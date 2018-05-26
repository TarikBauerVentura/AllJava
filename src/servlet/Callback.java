package servlet;

import db.Methods;
import db.Model;
import interfaces.Fibonacci;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Callback extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder dumped_json = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        PrintWriter out = response.getWriter();
        int input;
        while ((line = reader.readLine()) != null) {
            dumped_json.append(line);
        }
        if (dumped_json.substring(0, 6).equals("value=")) {
           input = Integer.parseInt(dumped_json.substring(6));
        }
        else {
            input = Integer.parseInt(dumped_json.substring(dumped_json.indexOf(":") + 2, dumped_json.length() - 2));
        }
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1998);
            Fibonacci stub = (Fibonacci) registry.lookup("RMIServer");
            int rmi_response = stub.getValue(input);
            Methods insert = new Methods();
            insert.Insert(new Model(input, rmi_response));
            out.println("{\"inserted\": \"true\", \"result\": " + Integer.toString(rmi_response) + "}");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}