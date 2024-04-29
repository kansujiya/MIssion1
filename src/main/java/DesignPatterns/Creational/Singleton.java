package DesignPatterns.Creational;

import java.io.Serializable;

//Ensures a class has only one instance and provides a global point of access to it.
// Often used for logging, caching, and thread pool management.

//Types to create singleton
//1. Eager
//2. lazy
//3. Synchronised method
//4. Double locking

class DBConnection1 {
    private static DBConnection1 dbConnection1 = new DBConnection1();

    private DBConnection1() {
    }

    public static DBConnection1 getInstance() {
        return dbConnection1;
    }
}

class DBConnection2 {
    private static DBConnection2 dbConnection2;

    private DBConnection2() {
    }

    public static DBConnection2 getInstance() { //2 thread comes same time then 2 object will be created
        if(dbConnection2 == null) {
            dbConnection2 = new DBConnection2();
        }
        return dbConnection2;
    }
}

class DBConnection3 {
    private static DBConnection3 dbConnection3;

    private DBConnection3() {
    }

    public static DBConnection3 getInstance() { //3 with sync its become expensive operation
        if(dbConnection3 == null) {
            synchronized (DBConnection3.class) {
                dbConnection3 = new DBConnection3();
            }
        }
        return dbConnection3;
    }
}

class DBConnection4 {
    private static DBConnection4 dbConnection4;

    private DBConnection4() {
    }

    public static DBConnection4 getInstance() {
        if(dbConnection4 == null) {
            synchronized (DBConnection4.class) {
                if(dbConnection4 == null) {
                    dbConnection4 = new DBConnection4();
                }
            }
        }
        return dbConnection4;
    }
}


public class Singleton {

    public static void main(String[] args) {
        //1.Eager
        DBConnection1.getInstance();

        //2. lazy
        DBConnection2.getInstance();

        //3. Synchronised method
        DBConnection3.getInstance();

        //4. Double locking
        DBConnection4.getInstance();
    }
}
