package rmi;

import interfaces.Fibonacci;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Fibonacci {

    private Server() {}

    public int getValue(int value) {
        int fn_2 = 1;
        int fn_1 = 0;
        int fn = 0;

        for (int i = 1; i <= value; i++) {
            fn = fn_2 + fn_1;
            fn_2 = fn_1;
            fn_1 = fn;
        }
        return fn;
    }

    public static void main(String args[]) {
        try {
            Server obj = new Server();
            Fibonacci stub = (Fibonacci) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.getRegistry(1998);
            registry.bind("RMIServer", stub);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
