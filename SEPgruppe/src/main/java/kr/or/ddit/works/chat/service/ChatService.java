package kr.or.ddit.works.chat.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.works.chat.vo.ChatRoomUserVO;
import kr.or.ddit.works.chat.vo.ChatRoomVO;
import kr.or.ddit.works.chat.vo.MessageVO;

//
public interface ChatService {

	/**
	 * 본인이 속한 채팅방 목록 조회
	 * @return
	 */
	public List<ChatRoomUserVO> selectListAllChatRoom(String empId);


	/**
	 * 채팅방 상세보기
	 * @param chatRoomNo 채팅방 번호
	 * @return
	 */
	public ChatRoomVO selectChatRoomDetail(long chatRoomNo);

	/**
	 * 채팅방 생성
	 * @return
	 */
	public boolean insertChatRoom(ChatRoomVO chatRoomVO);

	public MessageVO insertMessage(MessageVO message);

	public boolean insertChatRoomUser(ChatRoomUserVO roomUser);

	public List<MessageVO> findAllMessageByRoomId(String roomId);

	public boolean deleteChatRoom( String roomId);

	public List<ChatRoomUserVO> selectChatRoomUser(String roomId);

	public boolean deleteChatRoomUser(String roomId, String empId);

}
