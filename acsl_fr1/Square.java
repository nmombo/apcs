public class Square
{
    private int myNumber;
    private int mySpecial;
    /*
     * special:
     * 0 = nothing
     * 1 = double letter
     * 2 = triple letter
     * 3 = double word
     * 4 = triple word
     */
    private String myLetter;
    private int myValue;
    private boolean hasTile = false;
    
    /**/
    public Square()
    {
        myNumber = 0;
        mySpecial = 0;
    }
    
    public Square(int num)
    {
        myNumber = num;
        mySpecial = 0;
        if(myNumber == 3 || myNumber == 9 || myNumber == 15 || myNumber == 21 || myNumber == 27 || myNumber == 33 || myNumber == 39)
            mySpecial = 1;
        if(myNumber%5 == 0 && mySpecial == 0)
            mySpecial = 2;
        if(myNumber%7 == 0 && mySpecial == 0)
            mySpecial = 3;
        if(myNumber%8 == 0 && mySpecial == 0)
            mySpecial = 4;      
    }
    
    public Square(Tile tile)
    {
        myNumber = tile.getNumber();
        mySpecial = 0;
        if(myNumber == 3 || myNumber == 9 || myNumber == 15 || myNumber == 21 || myNumber == 27 || myNumber == 33 || myNumber == 39)
            mySpecial = 1;
        if(myNumber%5 == 0 && mySpecial == 0)
            mySpecial = 2;
        if(myNumber%7 == 0 && mySpecial == 0)
            mySpecial = 3;
        if(myNumber%8 == 0 && mySpecial == 0)
            mySpecial = 4;      
        myLetter = tile.getLetter();
        myValue = tile.getValue();
        hasTile = true;
    }
    
    public int getSpecial()
    {
        return mySpecial;
    }
    
    public int getNumber()
    {
        return myNumber;
    }
    
    public String getLetter()
    {
        return myLetter;
    }
    
    public int getValue()
    {
        if(mySpecial == 1)
            return myValue * 2;
        else if(mySpecial == 2)
            return myValue * 3;
        else return myValue;
    }
    
    public boolean hasTile()
    {
        return hasTile;
    }
    
    public String toString()
    {
        String special;
        if(mySpecial == 1)
            special = "Double Letter";
        else if(mySpecial == 2)
            special = "Triple Letter";
        else if(mySpecial == 3)
            special = "Double Word";
        else if(mySpecial == 4)
            special = "Triple Word";
        else special = "Normal";
        return "Square " + myNumber + " is a " + special + " score.\nIt contains a(n) " + getLetter() + " and it's value is " + getValue() + ".\n";
    }
}