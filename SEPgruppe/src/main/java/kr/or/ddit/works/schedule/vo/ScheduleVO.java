package kr.or.ddit.works.schedule.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.works.organization.vo.OrganizationVO;
import lombok.Data;

/**
 * 일정 SCHEDULE VO
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
@Data
public class ScheduleVO implements Serializable {
	
//  schdlNo=&
//  empId=test001&
//  scheduleTypeNo=1&
//  schdlTypeNm=&
//  schdlNm=11&
//  schdlCn=11&
//  schdlLocation=11&
//  schdlCreateDate=2025-04-14&
//  schdlStatus=%EC%B0%B8%EC%84%&
	
	private String sschdlBgngYmd;  
	private String sschdlEndYmd;  
	private String sschdlCreateDate;  
	
	
	private Long schdlTypeNm;      			//일정순번
	
	private Long schdlNo;      			//일정순번
	@NotBlank(groups = InsertGroup.class)
	private Long scheduleTypeNo;      	//일정유형번호
	@NotBlank(groups = InsertGroup.class)
	private String empId;      			//사원 아이디
	@NotBlank(groups = InsertGroup.class)
	private String schdlNm;      		//일정명
	@NotBlank(groups = InsertGroup.class)
	private String schdlCn;      		//일정설명
//	@NotBlank(groups = InsertGroup.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date schdlBgngYmd;      	//일정시작일
//	@NotBlank(groups = InsertGroup.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date schdlEndYmd;      	//일정종료일
	@NotBlank(groups = InsertGroup.class)
	private String schdlLocation;      	//일정 장소
	private Date schdlCreateDate;     //최초등록일시
	private String notifyTime;      	//알림시간
	@NotBlank(groups = InsertGroup.class)
	private String schdlStatus;      	//일정상태 (참석/불참/확정/변경/취소)
	private String schdlUpdateDate;     //일정 수정일시
	private long projectNo;             // 프로젝트 번호
	private String scheduleTypeNm;
	
	private int cnt;      			//적용열수
	
	private ScheduleTypeVO scheduleType;  // 일정 유형 VO 객체
    private String department;
    private OrganizationVO employee;

}
