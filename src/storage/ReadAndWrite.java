package storage;

import java.io.*;
import java.util.ArrayList;

public class ReadAndWrite implements IReadAndWrite{
    @Override
    public void writeFile(Object file){
        File f = new File("data/students.csv");
        OutputStream os = null;
        try {
            os = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            System.out.println("File không tồn tại, mời khởi tạo thông tin để khởi tạo file" +
                    ", sau đó khởi động lại chương trình.");
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            oos.writeObject(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Object readFile() {
        InputStream is = null;
        try {
            is = new FileInputStream("data/students.csv");
        } catch (FileNotFoundException e) {
            System.out.println("File không tồn tại, mời khởi tạo thông tin để khởi tạo file" +
                    ", sau đó khởi động lại chương trình.");
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(is);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ois != null) {
                Object writeObj = null;
                try {
                    writeObj = (Object) ois.readObject();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                return writeObj;
            } else {
                return (Object) new ArrayList<>();
            }
        }

    }
}
