package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Fibonacci extends Remote {
    int getValue(int value) throws RemoteException;
}