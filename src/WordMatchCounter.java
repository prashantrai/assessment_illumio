import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class WordMatchCounter {
    public static void main(String[] args) {
    	
    	if(args.length != 2) {
        	System.err.println("ERROR: Both predefined_words and input_file are required.");
        	System.out.println("Syntax: java src/WordMatchCounter.java <path to predefined_words.txt> <path to input_file.txt>");
        	return;
    	}
    	
        String predefinedWordsFile = args[0];
        String inputFile = args[1];
        
        
        // Read predefined words into a HashSet
        Map<String, String> predefinedWordsMap = readPredefinedWords(predefinedWordsFile);

        // Count matches by processing the file line by line
        Map<String, Integer> matchCountsMap = countMatches(inputFile, predefinedWordsMap);
        
        // Print the results
        printMatchCounts(matchCountsMap, predefinedWordsMap);
        
    }
    
    
    /*
	Time Complexity: O(N*L), where N is the number of lines in the input file, 
		and  L is the average number of words per line. 
		
	Space Complexity:  O(W+P), where  W is the number of predefined words that 
		have at least one match, and P is the number of predefined words.
     * */
    
    /**
     * Reads predefined words from a file and stores them in a HashMap for O(1) lookup.
     * @param predefinedWordsFile Path to the file containing predefined words.
     * @return A HashMap containing all predefined words in lowercase as key and original 
     * case as value as defined in 'predefined_words.txt'.
     */
    private static Map<String, String> readPredefinedWords(String predefinedWordsFile) {
    	// to keep the exact case of predefined words, this will help print the output
        Map<String, String> wordsMap = new HashMap<>(); 
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(predefinedWordsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordsMap.put(line.toLowerCase(), line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsMap;
    }
    

    /**
     * Counts matches of predefined words in the input file by processing it line by line.
     * This method ensures that memory usage is kept low by only holding a single line in memory at a time.
     * 
     * @param inputFile Path to the input file.
     * @param predefinedWords A map of predefined words to match against.
     * @return A map containing the match counts for each predefined word.
     */
    private static Map<String, Integer> countMatches(String inputFile, Map<String, String> predefinedWordsMap) {
        Map<String, Integer> matchCounts = new HashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	/* 
            	 * \\W+: Matches one or more consecutive non-word characters, 
            	 * effectively identifying separators between words.
            	 */
                String[] words = line.split("\\W+"); // Split by non-word characters
                for (String word : words) {
                    String lowerCaseWord = word.toLowerCase();
                    if (predefinedWordsMap.containsKey(lowerCaseWord)) {
                        matchCounts.put(lowerCaseWord, matchCounts.getOrDefault(lowerCaseWord, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchCounts;
    }

    
    /**
     * Prints the match counts in the specified format.
     * @param matchCounts A map containing the match counts for each predefined word.
     * @param predefinedWordsMap A map containing predefined word. 
     * Mapping lowercase to it's original case as value as defined in 'predefined_words.txt'. 
     */
    private static void printMatchCounts(Map<String, Integer> matchCounts, Map<String, String> predefinedWordsMap) {
        System.out.printf("%-20s %s%n", "Predefined word", "Match count");
        for (Map.Entry<String, Integer> entry : matchCounts.entrySet()) {
        	String originalCaseKey = predefinedWordsMap.get(entry.getKey());
            System.out.printf("%-20s %d%n", originalCaseKey, entry.getValue());
        }
    }
}
