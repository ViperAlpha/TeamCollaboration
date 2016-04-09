package com.uww.messaging.controller.user;

import com.google.gson.Gson;
import com.uww.messaging.MessagingApplication;
import com.uww.messaging.contract.MessageService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.display.IndividualMessageRequestBody;
import com.uww.messaging.display.Response;
import com.uww.messaging.display.UserMessageDisplay;
import com.uww.messaging.model.user.User;
import com.uww.messaging.model.user.UserMessageChat;

import com.uww.messaging.model.user.UserUploadedFile;
import com.uww.messaging.util.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by horvste on 3/1/16.
 */
@Controller
@RequestMapping(value = "/user/message")
public class UserMessageController {
	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private MessagingApplication messagingApplication;

	/**
	 * Access this method after logging in like this:
	 * <p>
	 * http://localhost:8080/user/message/individual-message/list/?firstUserId={sampleUserId}&secondUserId={sampleUserIdTwo}
	 *
	 * @param userId
	 * @param secondUserId
	 * @return string of json which represents a list of messages between users. In the event that there are no messages, we return
	 * an empty list.
	 */
	@RequestMapping(value = "/individual-message/list", method = RequestMethod.GET)
	@ResponseBody
	public String listIndividualMessages(@RequestParam("firstUserId") int userId, @RequestParam("secondUserId") int secondUserId) {
		Gson gson = new Gson();
		List<UserMessageDisplay> messagesBetweenUsers = messageService.findMessagesBetweenUsers(userId, secondUserId);
		return gson.toJson(messagesBetweenUsers);
	}

	@RequestMapping(value = "/individual-message/listBySingleUserId", method = RequestMethod.GET)
	@ResponseBody
	public String listIndividualMessages(Authentication authentication, @RequestParam("userId") int firstUserId) {
		Gson gson = new Gson();
		int userId = userService.userByAuthentication(authentication).getUserId();
		List<UserMessageDisplay> userMessageDisplays = messageService.findMessagesBetweenUsers(userId, firstUserId);
		return gson.toJson(userMessageDisplays);
	}

	@RequestMapping(value = "/individual-message/listUsers", method = RequestMethod.GET)
	@ResponseBody
	public String listIndividualMessages(Authentication authentication) {
		Gson gson = new Gson();
		int userId = userService.userByAuthentication(authentication).getUserId();
		List<User> users = new ArrayList<>();
		List<UserMessageChat> messagesBetweenUsers = messageService.findUserMessages(userId);
		messagesBetweenUsers.forEach(userMessageChat -> {
			if (userMessageChat.getFromUserId() == userId) {
				users.add(userService.findUserById(userMessageChat.getToUserId()));
			} else {
				users.add(userService.findUserById(userMessageChat.getFromUserId()));
			}
		});
		return gson.toJson(users);
	}


	@RequestMapping(value = "/individual-message/insert", method = RequestMethod.POST)
	@ResponseBody
	public Response insertIndividualMessages(Authentication authentication, @RequestBody IndividualMessageRequestBody param) {
		int currentUserId = userService.userByAuthentication(authentication).getUserId();

		messageService.haveIndividualConversation(currentUserId, param.getToUserId(), param.getMessage());

		return new Response(true, "Message sent from " + currentUserId + " to " + param.getToUserId(), " ");
	}

	@RequestMapping(value = "/individual-message/insertWithFile", method = RequestMethod.POST)
	@ResponseBody
	public String insertIndividualMessages(Authentication authentication, @RequestParam("toUserId") int toUserId, @RequestParam("message") String message,
	                                       @RequestParam("fileUpload") MultipartFile multiPartFile) throws IOException {
		int currentUserId = userService.userByAuthentication(authentication).getUserId();
		if (multiPartFile.isEmpty()) {
			messageService.haveIndividualConversation(
					currentUserId,
					toUserId,
					message
			);
			return "";
		}


		String fileName = UtilString.toValidFilePathString(multiPartFile.getOriginalFilename());
		if (!new File(messagingApplication.userDownloadDir).exists()) {
			throw new RuntimeException("You did not setup a user download directory in your application.properties file. This directory does not exist: "
					                           + messagingApplication.userDownloadDir);
		}
		String fileSavedPath = messagingApplication.userDownloadDir + "/" + fileName;
		File file = new File(fileSavedPath);
		multiPartFile.transferTo(file);
		UserUploadedFile userUploadedFile = new UserUploadedFile(currentUserId, multiPartFile.getOriginalFilename(), fileSavedPath, -10);
		messageService.haveIndividualConversation(currentUserId, toUserId, message, userUploadedFile);
		return "";
	}



}
