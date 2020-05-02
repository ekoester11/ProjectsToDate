/**
 * A program that runs the word ladder puzzle. The search for a word ladder is
 * implemented using a queue of stacks.
 *
 * @author Eli Koester and Pranav Vaidyanathan
 */

// You may only use the classes in this list of imports.
// DO NOT add more import statements.
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class WordLadder {

	/** The dictionary for this word ladder. The words in the dictionary are not sorted. */
	// YOU MUST USE HashSet as the dictionary data structure
	private HashSet<String> dictionary;

	/**
	 * Constructs a word ladder object using the given dictionary file. Inserts
	 * each word from the dictionary file into the dictionary HashSet data member.
	 *
	 * @param dictionaryFile The path for the dictionary file
	 */
	public WordLadder(String dictionaryFile) throws FileNotFoundException{
		// Programming Assignment 3: implement this method

		if(new File(dictionaryFile).exists() == false) {
			throw new FileNotFoundException("No such file");
		}

		// put the dictionary into the hashset
		dictionary = new HashSet<String>();
		File dic = new File(dictionaryFile);
		Scanner scan = new Scanner(dic);
		while(scan.hasNext()) {
			dictionary.add(scan.next());
		}
	}

	/**
	 * takes a word and returns a hashset of words that are the same length from the 
	 * dictionary
	 * @param start: the word to perform this on
	 * @return hashset of words that are the same length
	 */
	private HashSet<String> sameNumOfLetters(String start){
		HashSet<String> returnThis = new HashSet<String>(); // hashset to return
		// loop through dictionary and if same length then add to the hashset
		for (String s : dictionary) {
			if (start.length()==s.length()) {
				returnThis.add(s);
			}

		}
		return returnThis;
	}

	/**
	 * creates a collection of words one letter off 
	 * @param word: to find words that are one letter off from. usedWords: hashset of used words. 
	 * sameLetters: hashset of words from the dictionary that are the same length as word
	 * 
	 * @return a hashset of words that are one letter off from the starting word
	 */
	private HashSet<String> oneLetterOff(String word, HashSet<String> usedWords, HashSet<String> sameLetters) {
		HashSet<String> set = new HashSet<String>();

		for(String s : sameLetters) { // start looping through dictionary
			if(usedWords.contains(s) == false) { // as long as the word isn't already used then enter this if statement
				int numSame = 0;
				for(int i = 0; i < s.length(); i++) { // for loop to loop through the characters
					if(s.charAt(i) == word.charAt(i)) { // if the characters are the same, increment numSame
						numSame++;
					}
				}
				if(numSame == s.length()-1) { // if numSame is equal to 1 less than the original length, 
					// then the words are 1 letter off and so add to HashSet
					set.add(s);

				}
			} 
		} // stop looping through dictionary

		return set;

	}

	/**
	 * Finds a shortest word ladder that connects the start and end words.
	 * Returns null if no word ladder exists between the start and end words.
	 *
	 * @param start The starting word
	 * @param end The ending word
	 * @return A shortest word ladder from start to end, or null if no word ladder exists
	 */
	public Stack<String> buildLadder(String start, String end) {
		// Programming Assignment 3: implement this method
		
		Queue<Stack<String>> finalQueue = new Queue<Stack<String>>(); // queue that we keep adding to
		HashSet<String> set = new HashSet<String>(); // hashset to store words that are 1 letter off
		Stack<String> stack = new Stack<String>(); // initial stack to hold start word
		stack.push(start); 
		finalQueue.enqueue(stack);

		HashSet<String> usedWords = new HashSet<String>(); // hashset for used words
		usedWords.add(start);
		HashSet<String> sameNoLetters = sameNumOfLetters(start);

		while(finalQueue.isEmpty() == false) { // go through the queue until it's empty
			Stack<String> temp = new Stack<String>();
			temp = finalQueue.dequeue(); // the stack dequeued

			ArrayList<String> list = new ArrayList<String>();
			// copy words in temp into arrayList
			while(temp.isEmpty()==false) { 
				list.add(temp.pop());
			}
			for(int a = list.size() - 1; a >= 0; a--) { 
				temp.push(list.get(a));
			}
			
			String topWord = temp.peek(); // the top word in that stack

			if(topWord.equals(end)) { 
				return temp;
			}
			
			set = oneLetterOff(topWord, usedWords, sameNoLetters); // a hashset of words one letter different from that word
			for(String s : set) { // for each word in that set...
					Stack<String> copy = new Stack<String>();
					
					// copy temp to copy
					for(int j = list.size() - 1; j >= 0; j--) {
						copy.push(list.get(j));
					}

					copy.push(s); // push the new word onto it
					usedWords.add(s);
					finalQueue.enqueue(copy); // enqueue that new stack				

				}			
			

			
			}

		





		return null;
	}


}
