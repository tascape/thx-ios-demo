/*
 * Copyright 2016 tascape.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tascape.qa.ios.demo.driver;

import com.tascape.qa.th.exception.EntityDriverException;
import com.tascape.qa.th.ios.driver.App;
import com.tascape.qa.th.ios.model.UIAButton;
import com.tascape.qa.th.ios.model.UIAElement;
import com.tascape.qa.th.ios.model.UIAException;
import com.tascape.qa.th.ios.model.UIANavigationBar;
import com.tascape.qa.th.ios.model.UIATableCell;
import com.tascape.qa.th.ios.model.UIAWindow;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class Movies extends App {
    private static final Logger LOG = LoggerFactory.getLogger(Movies.class);

    public static final String BUNDLE_ID = "com.ikodeapps.Movies";

    public static final String NAME = "Movies";

    @Override
    public String getBundleId() {
        return BUNDLE_ID;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public void backToStart() throws UIAException {
        if (device.doesElementExist("window.buttons()['back icon']", UIAButton.class)) {
            device.runJavaScript("window.buttons()['back icon'].tap()");
        }
    }

    public void showDetail() throws UIAException, EntityDriverException {
        int index = new Random().nextInt(8);
        String e = "window.tableViews()[0].cells()[" + index + "]";
        device.runJavaScript(e + ".scrollToVisible();");
        device.takeDeviceScreenshot();
        device.runJavaScript(e + ".tap();");
        UIAWindow window = device.mainWindow();
        LOG.info("title {}", window.elements()[1].name());
        window.logElement().forEach(l -> LOG.debug(l));
    }

    public void showASimilarMovie() throws UIAException {
        UIATableCell cell = device.mainWindow().findElement(UIATableCell.class, "View all similar movies");
        UIAElement image = cell.elements()[1 + new Random().nextInt(4)];
        image.tapOn();
    }

    public boolean isHomeScreen() throws UIAException {
        return device.mainWindow().findElement(UIANavigationBar.class, "Discover") != null;
    }

    @Override
    public void reset() throws Exception {
        LOG.debug("na");
    }

    @Override
    public int getLaunchDelayMillis() {
        return 5000;
    }
}
