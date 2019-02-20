package com.lrg.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("init........");
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                String apiClassName = objectInputStream.readUTF();
                String methodName = objectInputStream.readUTF();
                Class[] parameterTypes = (Class[]) objectInputStream.readObject();
                Object[] argsMethod = (Object[]) objectInputStream.readObject();

                Class clazz = null;
                if (IProductService.class.getName().equals(apiClassName)) {
                    clazz = ProductService.class;
                }

                Method method = clazz.getMethod(methodName, parameterTypes);
                Object invoke = method.invoke(clazz.newInstance(), argsMethod);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(invoke);
                objectOutputStream.flush();

                objectInputStream.close();
                objectOutputStream.close();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
