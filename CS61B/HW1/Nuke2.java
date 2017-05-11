import java.net.*;
import java.io.*;

class Nuke2{
	
public static void main(String arg[]) throws IOException{
	System.out.println("Please Input the String Via the Keyboard:");
	BufferedReader keyboard= new BufferedReader(new InputStreamReader(System.in));
	String input= keyboard.readLine();
	
	/* Use substring */
	String output;
	output=input.substring(0, 1)+input.substring(2);
	
	/* Output results */
	System.out.println(output);
 }
}