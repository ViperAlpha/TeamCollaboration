package com.uww.messaging.display;

import com.uww.messaging.model.user.User;
import com.uww.messaging.model.wiki.Wiki;

import java.util.List;

/**
 * Created by horvste on 4/9/16.
 */
public class WikiOutput {
    private UserDisplay userDisplay;
    private Wiki wiki;

    public WikiOutput(UserDisplay userDisplay, Wiki wiki) {
        this.userDisplay = userDisplay;
        this.wiki = wiki;
    }

    public UserDisplay getUserDisplay() {
        return userDisplay;
    }

    public void setUserDisplay(UserDisplay userDisplay) {
        this.userDisplay = userDisplay;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }
}
