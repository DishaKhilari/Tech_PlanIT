package starter.StartBrowser;

public class RunDriver extends BaseClass{


    public void setUp(String URL) throws InterruptedException {
        BaseClass.openbrowser("Firefox");
        if (!options.setHeadless(true).is(String.valueOf(true)))
        {
            BaseClass.maximize();
        }
        BaseClass.deletecookies();
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofDays(40000)); //calculated as milliseconds
        BaseClass.SiteURL(URL);
        Thread.sleep(5000);
    }

}
