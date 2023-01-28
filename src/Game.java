import java.util.Objects;
import java.util.Scanner;

public class Game {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	Word word;
	WordList wordList;
	WordList answerList;
	Scanner sc;
	boolean isWon=false;
	String wordsFileName = "valid-wordle-words.txt";
	String answersFileName = "valid-wordle-answers.txt";
	Game() {
		wordList = new WordList(wordsFileName);
		answerList = new WordList(answersFileName);
		word=answerList.getRandomWord();
		sc = new Scanner(System.in);
		startGame();
	}
	void startGame() {
		System.out.println("WORDLE");
		String guess;
		for(int i = 0; i<5 && !this.isWon; i++) {
			guess=getGuess();
			printGuess(guess);
		}
		if(this.isWon) {
			System.out.println("You have won!");
		}
		else {
			System.out.println("You have lost! The word was: "+this.word.word);
		}
	}
	String getGuess() {
		String guess = "";
		while(guess.isEmpty()) {
			try{
				guess=sc.nextLine();
				if(guess.length()!=5) throw new Exception("Word must have length of 5");
				if(!wordList.isWordValid(guess)) throw new Exception("Invalid word!");
			}
			catch(Exception ex) {
				guess="";
				System.out.println("Invalid word!");
			}
		}
		return guess;
	}
	void printGuess(String guess) {
		if(Objects.equals(guess, this.word.word)) {
			win(guess);
			return;
		}
		for(int i = 0; i<5; i++) {
			int res=word.positionComp(guess.charAt(i), i);
			switch(res) {
				case 0 -> System.out.print(guess.charAt(i));
				case 1 -> System.out.print(ANSI_GREEN + guess.charAt(i) + ANSI_RESET);
				case 2 -> System.out.print(ANSI_YELLOW + guess.charAt(i) + ANSI_RESET);
			}
		}
		System.out.print('\n');

	}
	void win(String guess) {
		System.out.println(ANSI_GREEN + guess + ANSI_RESET);
		this.isWon=true;
	}
}