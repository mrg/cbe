package cbe.inserting.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to load a file via the classpath.
 *
 * @author mrg
 */
public class FileLoader
{
    protected List<String> load(String filename)
    {
        List<String> items            = new ArrayList<String>();
        URL          resourceAsStream = getClass().getResource("/" + filename);

//        URL findResource = getClass().getClassLoader().findResource("path");

        try
        {
            File file = new File(resourceAsStream.toURI());

            BufferedReader bufferedReader = null;
            FileReader     fileReader     = null;

            try
            {
                fileReader     = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);

                String line = null;

                while ((line = bufferedReader.readLine()) != null)
                    items.add(line);
            }
            finally
            {
               if (fileReader != null)
                   fileReader.close();

               if (bufferedReader != null)
                   bufferedReader.close();
            }
        }
        catch (IOException exception)
        {
            System.err.println("Failed to read and/or close file:");
            exception.printStackTrace();
        }
        catch (URISyntaxException exception)
        {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }

        return items;
    }
}
