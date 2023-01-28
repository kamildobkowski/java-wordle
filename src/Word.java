public class Word {
	public String word;
	Word(String word) {
		this.word=word;
	}
	int compareTo (Word word2) {
		return this.word.compareTo(word2.word);
	}
	int positionComp(char c, int pos) {
		if(this.word.charAt(pos)==c) return 1;
		for(int i = 0; i<5; i++) {
			if(this.word.charAt(i)==c) return 2;
		}
		return 0;
	}

}
