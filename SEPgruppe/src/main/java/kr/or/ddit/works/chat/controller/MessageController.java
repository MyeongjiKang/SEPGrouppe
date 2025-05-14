package kr.or.ddit.works.chat.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.works.chat.service.ChatService;
import kr.or.ddit.works.chat.vo.ChatRoomUserVO;
import kr.or.ddit.works.chat.vo.MessageVO;
import kr.or.ddit.works.mybatis.mappers.OrganizationMapper;
import kr.or.ddit.works.organization.vo.OrganizationVO;
import lombok.extern.slf4j.Slf4j;
//
@Slf4j
@Controller
@RequestMapping("/chat")
public class MessageController {

	@Autowired
	private ChatService chatService;

	@Autowired
	private OrganizationMapper organiMapper;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/{roomId}")
    @SendTo("/topic/{roomId}")
    public MessageVO broadcastMessage(@DestinationVariable String roomId, MessageVO message) {
        log.info("Received message: {}", message);
        String empId = message.getSenderEmpId();
        OrganizationVO organi = organiMapper.selectOrganization(empId);
        message.setOrganization(organi);
        chatService.insertMessage(message);
        String reId = roomId.replace(empId, "").replace("_", "");

        messagingTemplate.convertAndSend("/topic/alarm/" + reId, message);

        return message;
    }

	@SubscribeMapping("/{roomId}")
	public String subscribeHandler(@Headers Map<String, Object> headers, Authentication authentication) {
		String empId = authentication.getName();
		log.info("headers : {}", headers);
		String sub_id = empId;
		return sub_id;
	}

	@GetMapping("{roomId}")
	public String selectChatRoomDetail(
		@PathVariable("roomId") String roomId
		, Model model
		,  @RequestParam(required = false) String toName
	) {

		List<MessageVO> messageList = chatService.findAllMessageByRoomId(roomId);
		System.out.println(messageList);
		model.addAttribute("roomId", roomId);
		model.addAttribute("toName", toName);
		model.addAttribute("messageList", messageList);
		return "groupware/chat/chatHome";
	}

	@GetMapping("list")
	public String selectListAllChatRoom(
		Model model, Authentication authentication
	) {
		String empId = authentication.getName();
		List<ChatRoomUserVO> rooms = chatService.selectListAllChatRoom(empId);
		model.addAttribute("rooms", rooms);
		return "groupware/chat/chatList";
	}

	@DeleteMapping("{roomId}")
	public ResponseEntity<?> deleteChatRoom(@PathVariable String roomId, Authentication authentication) {
		String empId = authentication.getName();
		chatService.deleteChatRoomUser(roomId, empId);

		List<ChatRoomUserVO> remainingUser = chatService.selectChatRoomUser(roomId);
		if (remainingUser == null || remainingUser.isEmpty()) {
	        chatService.deleteChatRoom(roomId);
	        return ResponseEntity.ok("마지막 사용자 - 방 삭제됨");
	    }

	return ResponseEntity.ok("나가기 성공");
	}
}
