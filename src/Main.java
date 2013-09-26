
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***************************
 * Written by: Simon Cicek
 * Last changed: 2012-03-28
 ***************************/

public class Main 
{
    public static void main(String[] args)
    {
        HashTable<String,Integer> ht = new HashTable();
        String book = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " + 
                      "Nullam molestie luctus risus quis sagittis? " + 
                      "Ut a leo elit ligula dapibus venenatis volutpat non mauris. " +
                      "Donec volutpat sit eget leo laoreet in bibendum ipsum commodo. " + 
                      "Aenean feugiat dignissim rutrum, vestibulum enim ligula. " +
                      "Sagittis non ullamcorper sit, hendrerit quis nulla. " + 
                      "Cras condimentum hendrerit elit, sit amet dictum nulla convallis id. " + 
                      "Ut ornare nisl nec ante sollicitudin mollis.";
        String[] words = book.replaceAll("[.;,?]", " ").replaceAll(" {2,}", " ").split(" ");
        for(String s : words)
        {
            if(ht.contains(s))
            {
                Integer v = ht.get(s);
                ht.set(s, v + 1);
            }
            else
                ht.add(s, 1);
        }
        
        List keys = ht.keyView();
        List values = ht.valueView();
        ArrayList<Word> word = new ArrayList();
        
        for(int i = 0; i < keys.size();i++)
            word.add(new Word((String)keys.get(i), (int)values.get(i)));
        
        Collections.sort(word, Collections.reverseOrder());
        for(int i = 0; i < 10; i++)
            System.out.print(word.get(i));
    }
}
