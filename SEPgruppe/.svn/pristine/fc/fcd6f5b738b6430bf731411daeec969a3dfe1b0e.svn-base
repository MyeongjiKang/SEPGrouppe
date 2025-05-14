package kr.or.ddit.works.schedule.controller;

import java.security.Provider.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.security.RealUserWrapper;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.works.mybatis.mappers.OrganizationMapper;
import kr.or.ddit.works.organization.vo.OrganizationVO;
import kr.or.ddit.works.reservation.vo.ReservationVO;
import kr.or.ddit.works.schedule.service.ScheduleService;
import kr.or.ddit.works.schedule.vo.ScheduleTypeVO;
import kr.or.ddit.works.schedule.vo.ScheduleVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 일정관리 컨트롤러
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
@Slf4j
@Controller
@RequestMapping("/{companyNo}/schedule")
public class ScheduleController {
	
	public static final String MODELNAME = "schedule";
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private OrganizationMapper organizationMapper;
	
	// ----------------------------전체 조회-------------------------
	@GetMapping("")
	public String readScheduleList(@PathVariable("companyNo") String companyNo
		, Authentication authentication	
		, Model model
		, HttpSession session) {
	    List<ScheduleVO> scheduleList = scheduleService.readScheduleList();	
	    List<ScheduleTypeVO> scheduleType = scheduleService.readScheduleType();
	    
	    RealUserWrapper userWrapper = (RealUserWrapper) authentication.getPrincipal();
	    session.setAttribute("realUser", userWrapper.getRealUser());
	    
	    OrganizationVO employee = organizationMapper.selectOrganization(authentication.getName());
	    String department = employee.getDeptCd();
	    
	    ScheduleVO scheduleVo = new ScheduleVO();
	    scheduleVo.setDepartment(department);
	    
	    ScheduleTypeVO scheduleTypeVo = new ScheduleTypeVO();
	    scheduleTypeVo.setDepartment(department);
	    
	 // 권한 검사 및 일정 필터링
	    List<ScheduleVO> filteredScheduleList = new ArrayList<>();
	    for (ScheduleVO schedule : scheduleList) {
	        String scheduleTypeNm = scheduleType.stream()
	                .filter(type -> schedule.getScheduleTypeNo().equals(type.getScheduleTypeNo()))
	                .findFirst()
	                .map(ScheduleTypeVO::getScheduleTypeNm)
	                .orElse("");

	        if ("개인".equals(scheduleTypeNm)) {
	            // 개인 일정: 작성자만 조회 가능
	            if (authentication.getName().equals(schedule.getEmpId())) {
	                filteredScheduleList.add(schedule);
	            }
//	        } else if ("부서".equals(scheduleTypeNm)) {
//	            // 부서 일정: 부서원만 조회 가능
//	            if (department.equals(schedule.getDepartment())) {
//	                filteredScheduleList.add(schedule);
//	            }
	        } else if ("사내".equals(scheduleTypeNm) || "프로젝트".equals(scheduleTypeNm)) {
	            // 사내/프로젝트 일정: 모든 사용자 조회 가능
	            filteredScheduleList.add(schedule);
	        }
	    }
		    
	    // scheduleList에 scheduleTypeNm 추가
//	    for (ScheduleVO schedule : scheduleList) {
//	        for (ScheduleTypeVO type : scheduleType) {
//	            if (schedule.getScheduleTypeNo().equals(type.getScheduleTypeNo())) {
//	                schedule.setScheduleTypeNm(type.getScheduleTypeNm()); // ✅ scheduleTypeNm 추가
//	                break;
//	            }
//	        }
//	    }
	    
//	    if (scheduleList == null || scheduleList.isEmpty()) {
//	        System.out.println("일정이 없습니다");
//	    } else {
//	        System.out.println("일정있음");
//	    }
	    if (filteredScheduleList.isEmpty()) {
	        System.out.println("일정이 없습니다");
	    } else {
	        System.out.println("일정있음");
	    }
	    
	    System.out.println("================================mh");
	    for(int i=0;i<scheduleList.size();i++) {
	    	System.out.println(scheduleList.get(i));
	    	
	    }
	    for (ScheduleVO schedule : filteredScheduleList) {
	        System.out.println(schedule);
	    }
	    
	    
//	    model.addAttribute("scheduleList", scheduleList);
	    model.addAttribute("scheduleList", filteredScheduleList);
	    model.addAttribute("scheduleType", scheduleType);
	    
	    model.addAttribute("companyNo", companyNo);
	    return "gw:schedule/scheduleList";
	}
	
//	@GetMapping("/{schdlNo}.do")
//	public ScheduleVO readSchedule(@PathVariable("schdlNo") int schdlNo) {
//		
//		ScheduleVO sch = scheduleService.readSchedule(schdlNo);
//		
//		return scheduleService.readSchedule(schdlNo);
//	}
	
	// ----------------------------상세 조회-------------------------
	@GetMapping("/scheduleDetail.do")
	public String doGet(
			@RequestParam("what") String schdlNo
			, Model model
	) {
		int scheduleId = Integer.parseInt(schdlNo);
		ScheduleVO schedule = scheduleService.readSchedule(scheduleId);
		model.addAttribute("schedule", schedule);
		
		return "groupware/schedule/scheduleDetail";
		
	}
	
	
	 // 일정 등록 폼 반환 (JSP)
    @GetMapping("/form")
    public String scheduleForm(@PathVariable("companyNo") String companyNo
    		, Model model) {
    	System.out.println("ffffffffffffffffffffffffffffffff  mj");
    	System.out.println(companyNo);
    	model.addAttribute("companyNo", companyNo);
        return "groupware/schedule/scheduleForm";  // scheduleForm.jsp 반환
    }
	
    
//    // ----------------------------일정 등록-------------------------
//	@ResponseBody
//	@PostMapping("/createSchedule.ajax")
//	public ScheduleVO mytest(
////			@ModelAttribute(MODELNAME) ScheduleVO pvo,
//			HttpSession session,
//			Model model,
//			Authentication authentication){
////		List<ScheduleVO> schedule = scheduleService.readSchedule(scheduleId);
////		model.addAttribute("schedule", schedule);
////		pvo.setEmpId(authentication.getName());
//
//        System.out.println("=============================================mh1");
////        System.out.println(pvo);
////        System.out.println(authentication.getName());
////		
////		
////        int cnt = scheduleService.createSchedule(pvo);
////        
////        System.out.println("=============================================mh2");
////        System.out.println(cnt);
//        ScheduleVO rvo = new ScheduleVO();
//        rvo.setCnt(1);
//		
//		return rvo;
//	}

    
    // ----------------------------일정 등록-------------------------
	@ResponseBody
	@PostMapping("/createSchedule.ajax")
	public ScheduleVO createSchedule(
			@ModelAttribute(MODELNAME) ScheduleVO pvo,
			HttpSession session,
			Model model,
			Authentication authentication){

		pvo.setEmpId(authentication.getName());

        System.out.println("=============================================mh1");
//        System.out.println(pvo);
//        System.out.println(authentication.getName());
//		
        int cnt = scheduleService.createSchedule(pvo);
//        
//        System.out.println("=============================================mh2");
//        System.out.println(cnt);
        
        ScheduleVO rvo = new ScheduleVO();
        rvo.setCnt(cnt);
		
		return rvo;
	}
	
	
	
	// ---------------------------- 일정 수정 폼 반환 ----------------
    @GetMapping("/formEdit")
    public String scheduleFormEdit(
    		@RequestParam("schdlNo") String schdlNo
			, Model model
    ) {
		
		int scheduleId = Integer.parseInt(schdlNo);
		ScheduleVO schedule = scheduleService.readSchedule(scheduleId);
		model.addAttribute("schedule", schedule);
        System.out.println("=============================================mh1");
        System.out.println(schedule);
        
        
        
        return "groupware/schedule/scheduleEdit"; 
    }
    

	// ----------------------------일정 수정-------------------------
	@PutMapping("/updateSchedule.ajax")
	@ResponseBody
	public ScheduleVO updateSchedule(@RequestBody ScheduleVO pvo, Authentication authentication) {
	    if (pvo.getSchdlNo() == 0) { // URL에서 받은 schdlNo 검증
	        throw new IllegalArgumentException("일정 번호가 올바르지 않습니다.");
	    }

	    if (pvo.getSchdlNo() != pvo.getSchdlNo()) {
	        throw new IllegalArgumentException("요청 데이터와 URL의 일정 번호가 일치하지 않습니다.");
	    }

	    // 현재 로그인한 사용자의 ID 설정
	    pvo.setEmpId(authentication.getName());

	    int cnt = scheduleService.updateSchedule(pvo);

	    ScheduleVO rvo = new ScheduleVO();
	    rvo.setCnt(cnt);
	    return rvo;
	}
	
	// ----------------------------일정 삭제-------------------------
	@ResponseBody
	@DeleteMapping("/deleteSchedule.ajax/{schdlNo}")
	public ScheduleVO deleteSchedule(@PathVariable("schdlNo") int schdlNo) {
	    int cnt = scheduleService.deleteSchedule(schdlNo);
	    
	    System.out.println("=============================================mh2");
	    System.out.println("삭제된 일정 개수: " + cnt);

	    ScheduleVO rvo = new ScheduleVO();
	    rvo.setCnt(cnt); // 삭제된 개수 설정

	    return rvo; 
	}
	
	// ----------------------------권한 조회-------------------------
	
	
}
