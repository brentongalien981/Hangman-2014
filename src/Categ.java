import java.io.*;
import java.util.*;

public class Categ {
	String loc = "C:\\Users\\Chust\\Documents\\Eclipse\\Hangman\\src\\";
	ArrayList<String> players = new ArrayList<String>();
	Random rgen = new Random();
	BufferedReader br;
	String player = null;
	String str;
	
	public String getWord(String team) {		
		try {			
			br = new BufferedReader(new FileReader(loc + team + ".txt"));
			while((str = br.readLine()) != null) {
				players.add(str);
			}
		} 
		catch(Exception ioex) {
			System.out.println("Exception---> " + ioex);
			return player;
		}		
		return player = players.get(rgen.nextInt(10));
	}
}
