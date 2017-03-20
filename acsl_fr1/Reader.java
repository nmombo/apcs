// @Noah Momblanco
import java.io.BufferedReader; import java.io.*;
import java.io.File; import java.io.FileNotFoundException;
import java.io.PrintWriter; import java.util.Scanner;
import java.util.logging.Level; import java.util.logging.Logger;
public class Reader
{
    private static String words;
    public Reader()
    {}

    public static String getFile()
    {
        String line = "";
        try
        {
            BufferedReader in = new BufferedReader(new FileReader("./ACSL.DAT"));
            line = in.readLine();
            words = line + "\n";
            while((line = in.readLine()) != null)
            {
                words += line + "\n";
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return words;
    }
}
