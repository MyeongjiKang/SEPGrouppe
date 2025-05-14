package kr.or.ddit.works.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import kr.or.ddit.works.chat.vo.ChatRoomUserVO;
import kr.or.ddit.works.chat.vo.ChatRoomVO;
import kr.or.ddit.works.chat.vo.MessageVO;
import kr.or.ddit.works.mybatis.mappers.ChatMapper;
//
@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatMapper chatMapper;

	/**
	 * 채팅방 전체 목록 조회
	 * @return List<ChatRoomVO>
	 */
	@Override
	public List<ChatRoomUserVO> selectListAllChatRoom(String empId) {
		return chatMapper.selectListAllChatRoom(empId);
	}

	/**
	 * 채팅방 상세 조회
	 * @param chatRoomNo 채팅방 번호
	 * @return ChatRoomVO
	 */
	@Override
	public ChatRoomVO selectChatRoomDetail(long chatRoomNo) {
		return chatMapper.selectChatRoomDetail(chatRoomNo);
	}

	/**
	 * 채팅방 추가
	 * @param chatRoomVO 추가할 채팅방 정보
	 * @return 성공 여부 (true: 성공, false: 실패)
	 */
	@Override
	public boolean insertChatRoom(ChatRoomVO chatRoomVO) {

		try {
	        return chatMapper.insertChatRoom(chatRoomVO) > 0;
	    } catch (DataIntegrityViolationException e) {
	        // 중복키 예외가 발생하면 이미 존재하는 것으로 판단하여 성공 처리
	        return true;
	    }
	}

	@Override
	public boolean insertChatRoomUser(ChatRoomUserVO roomUser) {
		try {
	        return chatMapper.insertChatRoomUser(roomUser) > 0;
	    } catch (DataIntegrityViolationException e) {
	        return true;
	    }
	}

	@Override
	public MessageVO insertMessage(MessageVO message) {
		chatMapper.insertMessage(message);
		return message;
	}

	@Override
	public List<MessageVO> findAllMessageByRoomId(String roomId) {
		return chatMapper.findAllMessageByRoomId(roomId);
	}

	@Override
	public boolean deleteChatRoom(String roomId) {
		return chatMapper.deleteChatRoom(roomId) > 0;
	}

	@Override
	public boolean deleteChatRoomUser(String roomId, String empId) {
		return chatMapper.deleteChatRoomUser(roomId, empId) > 0;
	}

	@Override
	public List<ChatRoomUserVO> selectChatRoomUser(String roomId) {
		return chatMapper.selectChatRoomUser(roomId);
	}

}
