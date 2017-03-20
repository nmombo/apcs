public class Main
{
    /*
     * Noah's Five Year Plan
     * Create Three Boards
     * Add numbers to each square on each board: setNumbers()
     * Add letters to the 4 squares on each board
     * Calculate the score of each letter
     * Calculate the score of each word 
     * Print the highest score
     */

    //Creates the three 4x10 boards   
    private Square[][][] board = new Square[15][4][10];
    
    //Sets the input to the String file
    Interpreter in = new Interpreter();
    private final String[] LETTERS = in.getParsedLetters();
    private final String[][] LOCATIONS = in.getParsedLocations();
    private Tile[][] tiles = new Tile[5][3];
    
    public Main()
    {
        //sets a number to each Square in the grid
        setNumbers();
        
        //adds the letters to the tiles
        createTiles();
        
        //adds the tiles to the board
        addTiles();
        
        //print the results that ACSL asks
        print();
    }
    
    public static void main(String[] args)
    {
        System.out.println("\f");
        Main main = new Main();
    }
    
    public String toString() //prints each Square's number and its special
    {
        String foo = "";
        for(int a = 0; a<15; a++)
        {
            for(int i = 0; i<4; i++)
            {
                for(int k = 0; k<10; k++)
                {
                    foo = foo + board[a][i][k].toString();
                }
            }
        } return foo;
    }
    
    public void setNumbers() //adds numbers to each square in each grid
    {
        int number = 0;
        for(int a = 0; a<15; a++)
        {
            for(int i = 0; i<4; i++)
            {
                for(int k = 0; k<10; k++)
                {
                    board[a][i][k] = new Square(number);
                    number++;
                }
            }
        }
    }
    
    public void createTiles()
    {
        String[][] numberOfTile = new String[5][3];
        String[][] letterOfTile = new String[5][3];
        int x;
        for(int i = 0; i<5; i++)
        {
            x = 0;
            for(int k = 0; k<3; k+=1)
            {
                numberOfTile[i][k] = LOCATIONS[i][x]; x++;
                letterOfTile[i][k] = LOCATIONS[i][x]; x++;
                tiles[i][k] = new Tile(Integer.parseInt(numberOfTile[i][k]), letterOfTile[i][k]);
            }
        }
    }
    
    public void addTiles()
    {
        //each Square requires a number and a Tile
        int x = 0; int y = 0;
        for(int a = 0; a<3; a++)
        {
            x = 0;
            for(int i = 0; i<4; i++)
            {
                y = 0;
                for(int k = 0; k<10; k++)
                {
                    if(board[a][i][k].getNumber() == tiles[x][y].getNumber())
                    {
                        board[a][i][k] = new Square(tiles[x][y]);
                        y++;
                    }
                }
                x++;
            }
        }
    }
    
    public void print()
    {
        int sum[][] = new int[3][5];
        for(int a = 0; a<3; a++)
        {
            for(int b = 0; b< 5; b++)
            {
                sum[a][b] = 0;
            }
        }
        int multiplier[][] = new int[3][5];
        for(int a = 0; a<3; a++)
        {
            for(int b = 0; b< 5; b++)
            {
                multiplier[a][b] = 0;
            }
        }
        for(int a = 0; a<3; a++)
        {
            for(int i = 0; i<4; i++)
            {
                for(int k = 0; k<10; k++)
                {
                    if(board[a][i][k].hasTile() == true)
                    {
                        sum[a][0] += board[a][i][k].getValue();
                        if(board[a][i][k].getSpecial() == 3)
                        {
                            multiplier[a][0] = 2;
                        }
                        if(board[a][i][k].getSpecial() == 4)
                        {
                            multiplier[a][0] = 3;
                        }
                    }
                }
            }
        }
        int score;
    }
}