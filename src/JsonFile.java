import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFile {
	
	private static String filePath = ".passwords";
	
	//adds user's json data to file
	@SuppressWarnings("unchecked")
	public static void addUser(String username, String hash){
		JSONObject obj = new JSONObject();
		obj.put(username, hash);
		try (FileWriter file = new FileWriter(filePath)) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }		
	}
	
	//returns user's salt:hash
	public static String getUser(String username){
		JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(filePath));

            JSONObject jsonObject = (JSONObject) obj;   
            String hash = (String) jsonObject.get(username);            
            return hash;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();        
        } catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}	
}