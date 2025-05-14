package kr.or.ddit.works.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.works.chat.vo.ChatRoomUserVO;
import kr.or.ddit.works.chat.vo.ChatRoomVO;
import kr.or.ddit.works.chat.vo.MessageVO;

/**
 * 채팅 매퍼
 * @author JYS
 * @since 2025. 3. 16.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 16.     	JYS	          최초 생성
 *
 * </pre>
 */
@Mapper
public interface ChatMapper {

	/**
	 * 본인이 속한 채팅방 목록 조회
	 * @return
	 */
	public List<ChatRoomUserVO> selectListAllChatRoom(@Param("empId") String empId);
	
	/**
	 * 채팅방 상세보기
	 * @param chatRoomNo 채팅방 번호
	 * @return
	 */
	public ChatRoomVO selectChatRoomDetail(long roomId);
	
	
	public int insertChatRoomUser(ChatRoomUserVO roomUser);
	
	/**
	 * 채팅방 생성
	 * @return
	 */
	public int insertChatRoom(ChatRoomVO chatRoomVO);
	
	public int insertMessage(MessageVO message);
	
	public List<MessageVO> findAllMessageByRoomId(@Param("roomId") String roomId);
	
	public int deleteChatRoom(@Param("roomId") String roomId);
	
	public List<ChatRoomUserVO> selectChatRoomUser(@Param("roomId") String roomId);
	
	public int deleteChatRoomUser(@Param("roomId") String roomId, @Param("empId") String empId);

}
