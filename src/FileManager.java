import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class FileManager implements Serializable {
    
   /*  public static void saveuser() {
        List<user> userList = new ArrayList<>();
        userList.add(new user("John Doe", "john.doe@email.com", "password123", 123));
        userList.add(new user("Jane Smith", "jane.smith@email.com", "MGR456", 124));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("user_data.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(userList);
            objectOutputStream.flush();
            objectOutputStream.close();
            
        } catch (IOException e) {
            
        }
    }
*/

    public static void wirtUsers(TreeMap<Integer,ArrayList  <String>> user) {
        try { 
            FileOutputStream fileOutputStream = new FileOutputStream("user_data.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            
        }
    }
    

    public static TreeMap<Integer,ArrayList  <String>> readuser() throws IOException {
        File file = new File("user_data.dat");
        try{
            if (!file.exists()) {
                
                 // Create the file
                 // Write initial user data (optional)
            } else if (file.exists()) {
                    ObjectInputStream object = new ObjectInputStream(new FileInputStream(file));
                   
                    TreeMap<Integer,ArrayList  <String>>  userList = (TreeMap<Integer,ArrayList  <String>> ) object.readObject();
                    object.close();
                    return userList;
                } else {
                
                 
                }
            
        } catch (IOException | ClassNotFoundException e) {
            
        }
        return new TreeMap<Integer,ArrayList  <String>> (); 
    }



    public static TreeMap<Integer,ArrayList  <String>> readitem() throws IOException {
        File file = new File("item.dat");
        try{
            if (!file.exists()) {
    
            } else if (file.exists()) {
                    ObjectInputStream object = new ObjectInputStream(new FileInputStream(file));
                   
                    TreeMap<Integer,ArrayList  <String>>  itlemList = (TreeMap<Integer,ArrayList  <String>> ) object.readObject();
                    object.close();
                    return itlemList;
                } else {
                
                 
                }
            
        } catch (IOException | ClassNotFoundException e) {
            
        }
        return new TreeMap<Integer,ArrayList  <String>> (); 
    }
    public static void wirtitem(TreeMap<Integer,ArrayList  <String>> item) {
        try { 
            FileOutputStream fileOutputStream = new FileOutputStream("item.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(item);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            
        }
    }
    
    public static void wirtcategory(ArrayList  <String> category) {
        try { 
            FileOutputStream fileOutputStream = new FileOutputStream("category.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(category);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            
        }
    }
    public static ArrayList  <String> readcategory() throws IOException {
        File file = new File("category.dat");
        try{
            if (!file.exists()) {
    
            } else if (file.exists()) {
                    ObjectInputStream object = new ObjectInputStream(new FileInputStream(file));
                   
                    ArrayList  <String> categoryList = (ArrayList  <String> ) object.readObject();
                    object.close();
                    return categoryList;
                } else {
                
                 
                }
            
        } catch (IOException | ClassNotFoundException e) {
            
        }
        return new ArrayList  <String>(); 
    }
}
