package com.uww.messaging.service;

import com.uww.messaging.contract.UserService;
import com.uww.messaging.contract.WikiService;
import com.uww.messaging.display.UserDisplay;
import com.uww.messaging.display.WikiOutput;
import com.uww.messaging.model.user.User;
import com.uww.messaging.model.wiki.Wiki;
import com.uww.messaging.repository.wiki.WikiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by horvste on 4/9/16.
 */
@Service
public class WikiServiceImpl implements WikiService {
    private WikiRepository wikiRepository;
    private UserService userService;

    @Autowired
    public WikiServiceImpl(WikiRepository wikiRepository, UserService userService) {
        this.wikiRepository = wikiRepository;
        this.userService = userService;
    }

    @Override
    public List<WikiOutput> findWikiOutputByTimeStampAsc() {
        List<Wiki> wikiList = wikiRepository.findAllByOrderByEditTime();
        List<WikiOutput> wikiOutputs = new ArrayList<>();
        wikiList.forEach(wiki -> {
            int currentUserId = wiki.getUserId();
            User user = userService.findUserById(currentUserId);
            UserDisplay userDisplay = new UserDisplay(user.getUserId(), user.getUsername());
            wikiOutputs.add(new WikiOutput(userDisplay, wiki));
        });
        return wikiOutputs;
    }

    @Override
    public void pushWiki(int loggedInUserId, String content) {
        Wiki wiki = new Wiki();
        Timestamp currentStamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
        wiki.setEditTime(currentStamp);
        wiki.setContent(content);
        wiki.setUserId(loggedInUserId);
        wikiRepository.save(wiki);
    }
}
