package utils;

import java.io.*;

/**
 * 文件工具类(File Utility Class)
 * <p>
 * 提供对象序列化和反序列化的功能，用于文件读写操作
 * (Provides object serialization and deserialization functionality for file read/write operations)
 */
public class FileUtils {
    /**
     * 从文件读取对象(Read object from file)
     * <p>
     * 使用Java对象序列化机制从指定文件中读取对象
     * (Reads an object from the specified file using Java object serialization mechanism)
     *
     * @param filePath 文件路径(File path)
     * @return 读取到的对象，如果出错则返回null
     * (The read object, or null if an error occurs)
     */
    public static Object readObjectFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            // 从文件读取对象并返回(Read object from file and return)
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // 处理文件读取或类找不到的异常
            // (Handle exceptions related to file reading or class not found)
            System.err.println("从文件读取错误(Error reading from file): " + e.getMessage());
            return null;
        }
    }

    /**
     * 将对象写入文件(Write object to file)
     * <p>
     * 使用Java对象序列化机制将对象写入指定文件
     * (Writes an object to the specified file using Java object serialization mechanism)
     *
     * @param filePath 文件路径(File path)
     * @param object   要写入的对象(Object to write)
     * @return 写入成功返回true，失败返回false
     * (true if write is successful, false otherwise)
     */
    public static boolean writeObjectToFile(String filePath, Object object) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            // 将对象写入文件(Write object to file)
            oos.writeObject(object);
            return true;
        } catch (IOException e) {
            // 处理文件写入异常(Handle exceptions related to file writing)
            System.err.println("写入文件错误(Error writing to file): " + e.getMessage());
            return false;
        }
    }
}
