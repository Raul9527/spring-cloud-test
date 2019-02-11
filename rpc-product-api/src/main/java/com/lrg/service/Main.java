package com.lrg.service;

import com.lrg.service.bean.Product;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Main {
    public static void main(String[] args){
        IProductService service = (IProductService)rpc(IProductService.class);
        Product product = service.getProductByID(new Long(1381438438));
        System.out.println(product);
    }

    public static Object rpc(final Class clazz){
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("127.0.0.1",8888);

                String classNeme = clazz.getName();
                System.out.println(classNeme);
                String methodName = method.getName();
                System.out.println(methodName);
                Class[] parameterTypes = method.getParameterTypes();

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeUTF(classNeme);
                objectOutputStream.writeUTF(methodName);
                objectOutputStream.writeObject(parameterTypes);
                objectOutputStream.writeObject(args);
                objectOutputStream.flush();

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Object o = objectInputStream.readObject();
                objectInputStream.close();
                objectOutputStream.close();
                System.out.println(o);
                socket.close();
                return o;
            }
        });
    }
}
