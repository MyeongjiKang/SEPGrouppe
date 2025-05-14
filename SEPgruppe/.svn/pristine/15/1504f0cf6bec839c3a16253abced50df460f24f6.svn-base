package kr.or.ddit.works.mybatis.mappers;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.works.schedule.vo.ScheduleTypeVO;
import kr.or.ddit.works.schedule.vo.ScheduleVO;

@Mapper
public interface ScheduleMapper {
    public List<ScheduleVO> selectScheduleList();
    public ScheduleVO selectSchedule(int schdlNo);
    public int insertSchedule(ScheduleVO schedule);
    public int updateSchedule(ScheduleVO schedule);
    public int deleteSchedule(int schdlNo);
    public int deleteSchedulesByProjectNos(List<Long> projectNos);
    
    public List<ScheduleTypeVO> selectScheduleType();
    
    public  List<ScheduleVO> selectThisMonthSchedules(@Param("empId") String empId,
											          @Param("start") LocalDate start,
											          @Param("end") LocalDate end);
}
