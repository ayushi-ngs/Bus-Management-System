import java.io.*;

public class FileService {

    public static void saveDetails(Object obj, String filename) {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(obj);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object loadDetails(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return null;
        }
        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
            return ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}
