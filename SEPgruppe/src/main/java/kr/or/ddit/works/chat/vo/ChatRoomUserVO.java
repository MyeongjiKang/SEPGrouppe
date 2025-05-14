package kr.or.ddit.works.chat.vo;

import java.io.Serializable;

import kr.or.ddit.works.organization.vo.EmployeeVO;
import lombok.Data;


@Data
public class ChatRoomUserVO implements Serializable{

	private String userId;
	private String roomId;
	private String empId;
	private String joinTime;
	private String role;

	private EmployeeVO employee;
	private ChatRoomVO chatRoom;


}
