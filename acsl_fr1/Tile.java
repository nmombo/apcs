public class Tile
{
    private String myLetter;
    private int myValue;
    private int myNumber;
    
    public Tile()
    {
        myLetter = null;
        myValue = 0;
        myNumber = 0;
    }
    
    public Tile(int num, String letter)
    {
        myNumber = num;
        myLetter = letter;
        myValue = setValue(myLetter);
    }
    
    public int setValue(String letter)
    {
        int value = 0;
        if(letter.equals("A")||letter.equals("E"))
            return 1;
        else if(letter.equals("D")||letter.equals("R"))
            return 2;
        else if(letter.equals("B")||letter.equals("M"))
            return 3;
        else if(letter.equals("V")||letter.equals("Y"))
            return 4;
        else if(letter.equals("J")||letter.equals("X"))
            return 8;
        else return 0;
    }
    
    public String getLetter()
    {
        return myLetter;
    }
    
    public int getValue()
    {
        return myValue;
    }
    public int getNumber()
    {
        return myNumber;
    }
}