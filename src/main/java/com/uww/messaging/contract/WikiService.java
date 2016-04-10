package com.uww.messaging.contract;

import com.uww.messaging.display.WikiOutput;

import java.util.List;

/**
 * Created by horvste on 4/9/16.
 */
public interface WikiService {
    List<WikiOutput> findWikiOutputByTimeStampAsc();

    void pushWiki(int loggedInUserId, String content);
}
