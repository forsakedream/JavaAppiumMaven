package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;

@RunWith(Suite.class)

@Suite.SuiteClasses( {
        article.class,
        GetStartedTest.class,
        search.class
})
public class TestSuite {
}
