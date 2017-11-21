import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class UI {
	   
    public static String[] splitCommand(String input){
    	return input.split(" |\\:");    	
    }
    
    public static int addUser(String username, String password){
    	try {
    		if(JsonFile.getUser(username)==null) {
				JsonFile.addUser(username, PassHashing.createHash(password));
				return 0;
    		}	else System.err.println("This name already exsists!");

		} catch (NoSuchAlgorithmException e) {			
			System.err.println(e);
		} catch (InvalidKeySpecException e) {
			System.err.println(e);
		}
    	return 1;
    }
    
    private static int authUser(String username, String password){
    	String hash = JsonFile.getUser(username);    	
    	try {
			if(hash!=null && PassHashing.validatePassword(password, hash)) {
				return 0;    		
			} else {
				System.err.println("Wrong username or paasword!");
				return 1;
			}
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e);
		} catch (InvalidKeySpecException e) {
			System.err.println(e);
		} 
    	return 1;    	
    }
    
	public static void main(String[] args) {		
		Scanner reader = new Scanner(System.in);  
		while(true){		
			try{
				String userInput = reader.nextLine();
				String[] splitInput = splitCommand(userInput);	
								
				if(splitInput[0].equals("add")){					
					System.out.println(UI.addUser(splitInput[1],splitInput[2]));					
				}
				else if(splitInput[0].equals("auth")){
					System.out.println(UI.authUser(splitInput[1],splitInput[2]));					
				}
				else if(splitInput[0].equals("exit")){
					break;
				}				
	        } 
	        catch(Exception ex)
	        {
	            System.err.println("ERROR: " + ex);
	        }
		} 
		reader.close();
	}

}
