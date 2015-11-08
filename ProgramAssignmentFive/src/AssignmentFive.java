/*/
    Nate Bowers
    C202 Dr.H  -  M/W 1PM Class
    Updated 11.8.2015
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Nathan Bowers
 * 
 * pre condition/ post condition the program is designed to read in two files, 
 * covert them to be for comparison, count the number of words that were found, 
 * and not found.
 * User - no input required
 */
public class AssignmentFive {
    BinarySearchTree[] list = new BinarySearchTree[26];
    long countWNF = 0;//countWordNotFound
    long countWF = 0;//countWordFound
    long countCWNF = 0;//countCompareWordNotFound - Spelled Incorrectly
    long countCWF = 0;//countCompareWordFound - Spelled Correctly
    long comparison = 0;//comparison of the words and number counted
        
        /**
         * 
         * @param args 
         * 
         * Pre/Post Condition - the main calls the methods to be executed.
         * User - not required to input any data
         */
    public static void main(String[] args) {
               
        AssignmentFive ex = new AssignmentFive();
        ex.readFile();
        ex.readOliver();
        ex.printOutput();
                          
    }//main
    
    /**
     * pre/post condition- creates the Linked List
     * User- not required to input any data
     */
    public AssignmentFive(){
        for(int i = 0 ; i < list.length; i++)    
            list[i] = new BinarySearchTree<>();
    }//Assignment Four
    
    /**
     *  readFile - Reads the dictionary file, changes the words to lower case
     *              and stores it in a Binary Search Tree.
     *           - Counts the comparison of words spelled correctly/incorrectly
     *           - Closes the file when completed.
     *           - Pre Condition read in the file.
     *           - Post Condition the file will be trimmed, and all characters 
     *              converted to lower case. 
     *           - No required information from User.
     */
    public void readFile(){
        File f = new File("random_dictionary.txt");
        try {
            Scanner inf = new Scanner(f);
            while(inf.hasNext()){
                String word = inf.nextLine().trim().toLowerCase();
                list[word.charAt(0) - 97].insert(word);
               // System.out.println("word = " + word);
            }//while
           
            inf.close();
            
        }//try     
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }//catch
    }//readFile 
    
    /**
     * pre condition - reads in the Oliver file, sets the characters to lowercase.
     * post condition- completes the comparisons of the files,keeps count of the 
     *                 words found and not found.
     * User - No input required by user.
     * 
     */
    public void readOliver(){ 
        int[] count = new int[1];
        try{ 
           FileInputStream inx = new FileInputStream(new File("oliver.txt")); 
            char let;
            String str = "";//word to be processed
            int n = 0;
           while((n = inx.read())!= -1){
                let = (char)n;
                if(Character.isLetter(let))
                    str += Character.toLowerCase(let);
                if((Character.isWhitespace(let) || let == '-') && !str.isEmpty()){ 
                    count[0] = 0;
                    if (list[(int)str.charAt(0) - 97].search(str, count)){
                        countCWF += count[0];
                        countWF++;
                    }//if
                    else{
                        countCWNF += count[0];
                        countWNF++;
                    }//else
                    str = "";
                }//if
            }//while
          inx.close();
        }//try
        catch(IOException e){    
            e.printStackTrace();
        }//catch
    }//readFile  


    /**
     * This method is only used to print the results. 
     * Pre/Post Condition - only displays the output required for the program.
     * User - No input required by User.
     */
    public void printOutput(){
        
        System.out.printf("The average number of comparison words found "
                + ": %4.4f \n" , ((double)countCWF/countWF));
        System.out.printf("The average number of comparison words not found "
                + ": %4.4f \n" , ((double)countCWNF/countWNF));
        
    }//printOutput
}//class
/**run:
The average number of comparison words found : 16.3475 
The average number of comparison words not found : 10.3977 
BUILD SUCCESSFUL (total time: 27 seconds)

 * 
 */