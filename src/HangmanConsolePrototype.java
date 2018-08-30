import acm.program.*;

public class HangmanConsolePrototype extends ConsoleProgram {	
	public void init() {
		hc = new HangmanCanvas();
		add(hc);
	}
	
	public void run() {	
		String team;
		Categ categ = new Categ();
		do {
			team = readLine("Type-in team: ");
			word = categ.getWord(team);
		} while(word == null);
		
		
		
		// DEBUG
		println(word);
		println();
		attempts = new char[word.length() + guessLife];
		println("***initialized the size of the array:" + attempts.length);
		println();
		// DEBUG
		
		for(int i=0; i<word.length(); i++) {
			wordGuess = '-' + wordGuess;
		}
		
		// DEBUG -- need this because acm has STUPID BUG that doesn't let you
		// put stuffs on the right place without cushion time...
		try {
			Thread.sleep(2000);
		} catch(Exception e) {}
		// DEBUG
		
		hc.reset();
		// DEBUG
		println("reset() called");
		println();
		// DEBUG
		
		println("Welcome to Hangman!");
		do {
			promptUser();			
			getChar();
			feedback = evalChar(charGuess);
			updateStatus(feedback);
			
			// DEBUG
			for(int i=0; i<attempts.length; i++) {
				print("[" + attempts[i] + "]" + "  ");				
			}
			println();
			// DEBUG
			
			
		} while(!word.equals(wordGuess) && guessLife != 0);
		
		if(guessLife == 0)
			println("You Loser");
		else
			println("You guessed the word " + wordGuess + "!");
	}
	
	private void updateStatus(String str) {
		switch(str) {
			case "repeated":
				println("You already attempted that.");
				break;			
			case "correct":
				for(int i=0; i<word.length(); i++) {
					if(word.charAt(i) == charGuess) {
						wordGuess = wordGuess.substring(0, i) + 
									charGuess + wordGuess.substring(i + 1);
					}
				}				
				putChar(charGuess);
				println("Correct!");
				hc.displayWord(wordGuess); // <-- for the canvas
				break;			
			case "dne":
				putChar(charGuess);
				--guessLife;
				println("Wrong!");
				hc.noteIncorrectGuess(charGuess); // <-- for the canvas
				break;
		}
	}
	
	private void putChar(char ch) {
		attempts[index++] = ch;
	}
	
	private void getChar() {
		char ch;
		String str;
		
		while(true) {
			str = readLine("Your guess:");
			ch = str.charAt(0);
			
			if(Character.isLetter(ch)) {
				if(Character.isUpperCase(ch)) {
					ch = Character.toLowerCase(ch);
				}
				charGuess = ch;
				break;
			}
		}
	}
	
	private void promptUser() {		
		println("The word now looks like this:" + wordGuess);
		println("You have " + guessLife + " guesses left.");
	}
	
	private String evalChar(char ch) {
		String comment = "dne";
		block:
		{
			for(int i=0; i<attempts.length; i++) {			
				if(attempts[i] == ch) {
					comment = "repeated";
					break block;
				}
			}
				
			for(int i=0; i<word.length(); i++) {
				if(word.charAt(i) == ch) {
					comment = "correct";
					break block;
				}		
			}
		} // break to block
		return comment;
	}
	
	// INSTANCE VARIABLES
	private String word;
	private String wordGuess = "";
	private String feedback;
	private char charGuess;
	public static int guessLife = 8;
	
	private char attempts[];
	private int index = 0;
	
	private HangmanCanvas hc;
	
}
