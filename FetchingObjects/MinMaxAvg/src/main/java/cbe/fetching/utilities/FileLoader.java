package cbe.fetching.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to load a file via the classpath.
 *
 * @author mrg
 */
public class FileLoader
{
    private static FileLoader instance = null;

    private FileLoader() { }

    public static FileLoader getInstance()
    {
        if (instance == null)
            instance = new FileLoader();

        return instance;
    }

    public List<String> loadLines(String filename)
    {
        BufferedReader    bufferedReader    = null;
        InputStream       inputStream       = null;
        InputStreamReader inputStreamReader = null;
        List<String>      lines             = new ArrayList<String>();

        try
        {
            inputStream       = getClass().getClassLoader().getResourceAsStream(filename);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader    = new BufferedReader(inputStreamReader);

            String line = null;

            while ((line = bufferedReader.readLine()) != null)
                lines.add(line);

            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        }
        catch (IOException e)
        {
            System.err.println("Failed to read and/or close file:");
            e.printStackTrace();
        }

        return lines;
    }
}
