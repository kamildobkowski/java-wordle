import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Vector;
import java.util.Scanner;
import java.io.File;
import java.util.Random;
public class WordList {
	Vector<Word> wordList;
	File file;
	int size;
	String fileName;
	WordList(String fileName) {
		this.fileName=fileName;
		wordList=new Vector<>();
		file=new File(fileName);
		size=0;
		getFromFile();
	}
	void getFromFile() {
		try{
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				wordList.add(new Word(sc.next()));
				size++;
			}
		}
		catch (FileNotFoundException ex) {
			throw new RuntimeException(ex);
		}
		//System.out.println(size);
	}
	Word getRandomWord() {
		Random rnd = new Random();
		int rand = rnd.nextInt(size);
		return wordList.elementAt(rand);
	}
	boolean isWordValid(String input) {
		for(int i = 0; i<5; i++) {
			if(!Character.isLetter(input.charAt(i))) return false;
		}
		Word word = new Word(input);
		boolean result = false;
		for(Word i : wordList) {
			//System.out.println('o');
			if(i.compareTo(word)==0) {
				result=true;
				break;
			}
		}
		//if(wordList.elementAt(lowerbound)==word) result=true;
		return result;
	}
}
