package kr.or.ddit.works.schedule.service;

import java.time.LocalDate;
import java.util.List;

import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.works.schedule.vo.ScheduleTypeVO;
import kr.or.ddit.works.schedule.vo.ScheduleVO;

/**
 * 일정관리서비스
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
public interface ScheduleService {
   
    public List<ScheduleVO> readScheduleList();
    
//     List<ScheduleVO> readSchedule(int schdlNo);
    
    public ScheduleVO readSchedule(int schdlNo);
    
    public int createSchedule(ScheduleVO schedule);
    
    public int updateSchedule(ScheduleVO schedule);
    
    public int deleteSchedule(int schdlNo);
    
    public int deleteSchedulesByProjectNos(List<Long> projectNos);
    
    public List<ScheduleTypeVO> readScheduleType();
    
    public List<ScheduleVO> selectThisMonthSchedules(String empId, LocalDate start, LocalDate end);

}