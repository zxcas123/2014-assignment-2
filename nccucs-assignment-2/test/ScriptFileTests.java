package ps3.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URISyntaxException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * This class, along with a complete PS3TestDriver implementation, can be used
 * to test the your implementations of Graph and the path finding algorithm
 * using the script file format described in the problem set. It will find all
 * script files in the same directory as this class file (with names ending in
 * <tt>.test</tt>) and execute them.
 */
public class ScriptFileTests extends TestCase {
        private final File testDriver;

        /**
         * Creates a new ScriptFileTests case, which runs the given test file.
         * @param testDriver
         */
    public ScriptFileTests(File testDriver) {
        super("testWithScriptFile");

        this.testDriver = testDriver;
    }

    /**
     * @throws FileNotFoundException, IOException
     * @requires that the specified File exists
         * @returns the contents of that file
     */
    protected String fileContents(File f) throws IOException {
        if (f == null) {
            throw new RuntimeException("No file specified");
        }

        BufferedReader input = new BufferedReader(new FileReader(f));
                  String inputLine;
                  StringBuffer result = new StringBuffer();
                while ((inputLine = input.readLine()) != null) {
                        result.append(inputLine);
                        result.append("\n");
        }

        return result.toString();
    }

    /**
     * @throws IOException
     * @requires there exists a test file indicated by testDriver
     *
     * @effects runs the test in filename, and output its results to a file in
     * the same directory with name filename+".actual"; if that file already
     * exists, it will be overwritten.
     * @returns the contents of the output file
     */
    protected String runScriptFile() throws IOException {
        if (testDriver == null) {
            throw new RuntimeException("No file specified");
        }

        File actual = fileWithSuffix("actual");

        Reader r = new FileReader(testDriver);
        Writer w = new FileWriter(actual);

        PS3TestDriver td = new PS3TestDriver(r, w);
        td.runTests();

        return fileContents(actual);
    }

    /**
         * @param newSuffix
         * @return a File with the same name as testDriver, except that the test
         *         suffix is replaced by the given suffix
         */
    protected File fileWithSuffix(String newSuffix) {
        File parent = testDriver.getParentFile();
        String driverName = testDriver.getName();
        String baseName = driverName.substring(0, driverName.length() - "test".length());

        return new File(parent, baseName + newSuffix);
    }

    /**
     * The only test that is run: run a script file and test its output.
     * @throws IOException
     */
    public void testWithScriptFile() throws IOException {
        File expected = fileWithSuffix("expected");

        assertEquals(testDriver.getName(), fileContents(expected), runScriptFile());
    }

    /**
     * Build a test suite of all of the script files in the directory.
     * @return the test suite
     * @throws URISyntaxException
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite();

        // Hack to get at the directory where the files are: they are in the
                // same directory as the compiled ScriptFileTests class,
        try {
            File myDirectory = new File(ScriptFileTests.class.getResource("ScriptFileTests.class").toURI()).getParentFile();
            for (File f : myDirectory.listFiles()) {
                if (f.getName().endsWith(".test")) {
                    suite.addTest(new ScriptFileTests(f));
                }
            }
            return suite;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
