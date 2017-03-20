public class Interpreter
{
    Reader in = new Reader();
    private final String FILE = in.getFile();
    private String[] line = new String[7];
    
    public Interpreter()
    {
        line[0] = null;
        line[1] = FILE.substring(0);
        line[2] = line[1].substring(FILE.indexOf("\n")+1);
        line[1] = line[1].substring(0,line[1].indexOf("\n"));
        line[3] = line[2].substring(line[2].indexOf("\n")+1);
        line[2] = line[2].substring(0,line[2].indexOf("\n"));
        line[4] = line[3].substring(line[3].indexOf("\n")+1);
        line[3] = line[3].substring(0,line[3].indexOf("\n"));
        line[5] = line[4].substring(line[4].indexOf("\n")+1);
        line[4] = line[4].substring(0,line[4].indexOf("\n"));
        line[6] = line[5].substring(line[5].indexOf("\n")+1);
        line[5] = line[5].substring(0,line[5].indexOf("\n"));
    }
    
    public String[] getParsedLetters()
    {
        String[] toReturn = new String[4];
        String temp = line[1];
        for(int i = 0; i<3; i++)
        {
            toReturn[i] = temp.substring(0,temp.indexOf(","));
            temp = temp.substring(temp.indexOf(",")+1);
        }
        toReturn[3] = temp;
        return toReturn;
    }
    
    public String[][] getParsedLocations()
    {
        String[][] toReturn = new String[5][6];
        String[] l = new String[5];
        for(int j = 0; j<5; j++)
        {
            l[j] = line[j+2];
            for(int i = 0; i<5; i++)
            {
                toReturn[j][i] = l[j].substring(0,l[j].indexOf(","));
                l[j] = l[j].substring(l[j].indexOf(",")+1);
            }
            toReturn[j][5] = l[j];
        }
        return toReturn;
    }
    
    public String getLine(int l)
    {
        return line[l];
    }
    
    public String getFILE()
    {
        return FILE;
    }
}