package kr.or.ddit.works.chat.vo;

import java.io.Serializable;

import kr.or.ddit.works.organization.vo.EmployeeVO;
import kr.or.ddit.works.organization.vo.OrganizationVO;
import lombok.Data;


@Data
public class MessageVO implements Serializable{

	private Long msgId;
	private String roomId;
	private String senderEmpId;
	private String msgContent;
	private String sendDate;
	private String sendTime;    //보낸시간

	private EmployeeVO employee;
	private ChatRoomVO chatRoom;
	private OrganizationVO organization;

}
