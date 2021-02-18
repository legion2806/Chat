import junit.framework.TestCase;
import org.junit.Assert;

public class ClientTest extends TestCase {

    public void testReadPortNumber() {
        String nameSettings = "settings.txt";
        int portNumberExpected = 56000;
        int portNumberResult = Client.readPort();
        Assert.assertEquals(portNumberExpected, portNumberResult);
    }
}