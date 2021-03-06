package play.test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import org.fluentlenium.core.*;

import com.google.common.base.Function;

import java.util.concurrent.TimeUnit;

/**
 * A test browser (Using Selenium WebDriver) with the FluentLenium API (https://github.com/Fluentlenium/FluentLenium).
 */
public class TestBrowser extends FluentAdapter {

    /**
     * A test browser (Using Selenium WebDriver) with the FluentLenium API (https://github.com/Fluentlenium/FluentLenium).
     *
     * @param webDriver The WebDriver instance to use.
     */
    public TestBrowser(Class<? extends WebDriver> webDriver) throws Exception {
        this(play.api.test.WebDriverFactory.apply(webDriver));
    }


    /**
     * A test browser (Using Selenium WebDriver) with the FluentLenium API (https://github.com/Fluentlenium/FluentLenium).
     *
     * @param webDriver The WebDriver instance to use.
     */
    public TestBrowser(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Creates a generic FluentWait<WebDriver> instance 
     * using the underlying web driver
     */
    public FluentWait<WebDriver> fluentWait() {
        return new FluentWait<WebDriver>(super.getDriver());
    }

    /**
     * Repeatedly applies this instance's input value to the given function until one of the following occurs:
     * the function returns neither null nor false,
     * the function throws an unignored exception,
     * the timeout expires
     *
     * Useful in situations where FluentAdapter#await is too specific
     * (for example to check against page source)
     *
     * @param wait generic FluentWait<WebDriver> instance
     * @param f function to execute
     */
    public <T>T waitUntil(FluentWait<WebDriver> wait, Function<WebDriver, T> f) {
        return wait.until(f);
    }

    /**
     * Repeatedly applies this instance's input value to the given function until one of the following occurs:
     * the function returns neither null nor false,
     * the function throws an unignored exception,
     * the default timeout expires
     *
     * useful in situations where FluentAdapter#await is too specific
     * (for example to check against page source or title)
     *
     * @param wait generic FluentWait<WebDriver> instance
     * @param f function to execute
     */
    public <T>T waitUntil(Function<WebDriver, T> f) {
        FluentWait<WebDriver> wait = fluentWait().withTimeout(3000, TimeUnit.MILLISECONDS);
        return waitUntil(wait,f);
    }

    /**
     * retrieves the underlying option interface that can be used
     * to set cookies, manage timeouts among other things
     */
    public WebDriver.Options manage() {
        return super.getDriver().manage();
    }

}