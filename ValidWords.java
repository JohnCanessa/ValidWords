import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 
 */
public class ValidWords {


    /**
     * Return a list with the words that can be generated with the 
     * provided set of lowercase characters.
     * A letter may be used multiple times.
     */
    static public List<String> validWords0(String[] words, char[] letters) {

        // **** initialization ****
        List<String> validList = new ArrayList<>();
        boolean addToList   = true;

        // **** sanity check(s) ****
        if (words.length == 0) return validList;
        if (letters.length == 0) return validList;

        // **** populate array with character count ****
        int[] map = new int[26];
        for (char c : letters)
            map[Character.valueOf(c) - 'a']++;

        // ???? ????
        System.out.println("validWords <<< map: " + Arrays.toString(map));

        // **** loop once per word ****
        for (String word : words) {

            // ???? ????
            System.out.println("validWords <<< word ==>" + word + "<==");

            // **** loop once per character in word ****
            addToList = true;
            for (char c : word.toCharArray()) {
                if (map[c - 'a'] == 0) {
                    addToList = false;
                    break;
                }
            }

            // **** add this word to the validList list (if needed) ****
            if (addToList)
                validList.add(word);
        }

        // **** return list of valid words ****
        return validList;
    }


    /**
     * Return a list with the words that can be generated with the 
     * provided set of lowercase characters.
     * A letter may NOT be used multiple times.
     */
    static public List<String> validWords1(String[] words, char[] letters) {

        // **** initialization ****
        List<String> validList  = new ArrayList<>();
        boolean addToList       = true;
        int[] map               = null;

        // **** sanity check(s) ****
        if (words.length == 0) return validList;
        if (letters.length == 0) return validList;

        // **** populate array with character count ****
        int[] baseMap = new int[26];
        for (char c : letters)
            baseMap[Character.valueOf(c) - 'a']++;

        // ???? ????
        System.out.println("validWords <<< baseMap: " + Arrays.toString(baseMap));

        // **** loop once per word ****
        for (String word : words) {

            // ???? ????
            System.out.println("validWords <<< word ==>" + word + "<==");

            // **** make a fresh copy of the base map ****
            map = Arrays.copyOf(baseMap, baseMap.length);

            // **** loop once per character in word ****
            addToList = true;
            for (char c : word.toCharArray()) {
                if (map[c - 'a'] == 0) {
                    addToList = false;
                    break;
                } else {
                    map[c - 'a']--;
                }

                // ???? ????
                // System.out.println("validWords <<< map: " + Arrays.toString(map));
            }

            // **** add this word to the validList list (if needed) ****
            if (addToList)
                validList.add(word);
        }

        // **** return list of valid words ****
        return validList;
    }


    /**
     * Test scaffold.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** populate array of letters ****
        String str = br.readLine().trim().replace(" ", "");
        char[] letters = str.toCharArray();

        // **** populate array of words ****
        String[] words = Arrays.stream(br.readLine().trim().split(" "))
                                    .toArray(String[]::new);

        // **** close buffered reader ****
        br.close();
        
        // ???? ????
        System.out.println("main <<< letters: " + Arrays.toString(letters));
        System.out.println("main <<< words: " + Arrays.toString(words));

        // **** invoke function of interest and display valid list of words ****
        System.out.println("main <<< validList: " + validWords0(words, letters).toString());

        // **** invoke function of interest and display valid list of words ****
        System.out.println("main <<< validList: " + validWords1(words, letters).toString());
    }
}