/***************************
 * Written by: Simon Cicek
 * Last changed: 2012-03-28
 ***************************/

public class Word implements Comparable
{
    String word;
    int freq;
    
    public Word(String word, int freq)
    {
        this.word = word;
        this.freq = freq;
    }
    
    @Override
    public String toString()
    {
        return "Word: " + word + "  " + "Frequency: " + freq + "\n";
    }

    @Override
    public int compareTo(Object o) 
    {
        Word word = (Word) o;
        if(this.freq == word.freq)
            return 0;
        else if(this.freq < word.freq)
            return -1;
        else
            return 1;
    }
}
