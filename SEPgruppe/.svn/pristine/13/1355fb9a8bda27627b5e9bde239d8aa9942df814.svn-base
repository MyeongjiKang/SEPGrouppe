package kr.or.ddit.works.schedule.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.works.mybatis.mappers.ScheduleMapper;
import kr.or.ddit.works.schedule.vo.ScheduleTypeVO;
import kr.or.ddit.works.schedule.vo.ScheduleVO;

/**
 * 일정관리 서비스 임플
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
@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleMapper scheduleMapper;

	@Override
	public List<ScheduleVO> readScheduleList() {
		return scheduleMapper.selectScheduleList();
	}

	@Override
	public ScheduleVO readSchedule(int schdlNo) {
		return (ScheduleVO) scheduleMapper.selectSchedule(schdlNo);
	}

	@Transactional
	@Override
	public int createSchedule(ScheduleVO schedule) {
		if (schedule == null || schedule.getSchdlBgngYmd() == null || schedule.getSchdlEndYmd() == null) {
            throw new IllegalArgumentException("다시 입력해주세요.");
        }
		return scheduleMapper.insertSchedule(schedule);
		
	}

	@Transactional
	@Override
	public int updateSchedule(ScheduleVO schedule) {
		if (schedule.getScheduleTypeNo() == null && schedule.getScheduleType() != null) {
	        schedule.setScheduleTypeNo(schedule.getScheduleType().getScheduleTypeNo());
	    }
		return scheduleMapper.updateSchedule(schedule);
		
	}

	@Transactional
	@Override
	public int deleteSchedule(int schdlNo) {
		return scheduleMapper.deleteSchedule(schdlNo);
		
	}
	
	@Transactional
	@Override
	public int deleteSchedulesByProjectNos(List<Long> projectNos) {
	    if (projectNos == null || projectNos.isEmpty()) {
	        return 0; // 삭제할 대상이 없으면 0 반환
	    }

	    return scheduleMapper.deleteSchedulesByProjectNos(projectNos);
	}

	@Override
	public List<ScheduleTypeVO> readScheduleType() {
		return scheduleMapper.selectScheduleType();
	}
	
	@Override
	public List<ScheduleVO> selectThisMonthSchedules(String empId, LocalDate start, LocalDate end) {
		return scheduleMapper.selectThisMonthSchedules(empId, start, end);
	}
}
