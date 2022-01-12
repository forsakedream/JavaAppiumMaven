package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    private WelcomePageObject WelcomePageObject;

    public void setUp() throws Exception
    {
        super.setUp();
        WelcomePageObject = new WelcomePageObject(driver);
    }

    @Test
    public void testPassThroughWelcome()
    {
        if (Platform.getInstance().isAndroid())
        {
            return;
        }
        WelcomePageObject.skip();
    }
}
