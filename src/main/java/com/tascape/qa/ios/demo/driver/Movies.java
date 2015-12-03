package com.tascape.qa.ios.demo.driver;

import com.tascape.qa.th.driver.EntityDriver;
import com.tascape.qa.th.exception.EntityDriverException;
import com.tascape.qa.th.ios.driver.UiAutomationDevice;
import com.tascape.qa.th.ios.model.UIAButton;
import com.tascape.qa.th.ios.model.UIAException;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class Movies extends EntityDriver {
    private static final Logger LOG = LoggerFactory.getLogger(Movies.class);

    public static final String BUNDLE_ID = "com.ikodeapps.Movies";

    public static final String NAME = "Movies";

    private UiAutomationDevice device;

    public void attachTo(UiAutomationDevice device) {
        this.device = device;
    }

    public void launch() throws Exception {
        device.start(Movies.NAME);
    }

    public void backToStart() throws UIAException {
        if (device.doesElementExist("window.buttons()['back icon']", UIAButton.class)) {
            device.sendJavaScript("window.buttons()['back icon'].tap()");
        }
    }

    public void showDetail() throws UIAException, EntityDriverException {
        int index = new Random().nextInt(8);
        String e = "window.tableViews()[0].cells()[" + index + "]";
        device.sendJavaScript(e + ".scrollToVisible();");
        device.takeDeviceScreenshot();
        device.sendJavaScript(e + ".tap();");
        device.mainWindow().logElement().forEach(l -> LOG.debug(l));
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void reset() throws Exception {
        LOG.debug("na");
    }

    public UiAutomationDevice getUiaDevice() {
        return device;
    }
}
