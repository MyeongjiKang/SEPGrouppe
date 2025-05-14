package kr.or.ddit.works.common.vo;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.works.approval.vo.DocFormVO;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 통합 첨부파일 관리 ATTACH_FILE VO
 * @author JYS
 * @since 2025. 3. 17.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 17.     	JYS	          최초 생성
 *
 * </pre>
 */
@Slf4j
@Data
public class AttachFileVO implements Serializable{

	private String attachFileNo;      		//첨부파일번호
	private Long fileGroupNo;      			//파일그룹번호

	private String attachFileName;      	//파일명
	private String attachOrgFileName;      	//원본파일명
	private Long attachFileSize;      		//파일크기
	private String attachFilePath;      	//파일경로
	private String attachFileExt;      		//파일확장자
	private String attachFileDate;      	//업로드날짜
	private Long attachFilemaxSize;      	//파일제한크기(최대값정해두기)
	private String attachFileStatus;      	//상태여부(활성화,삭제 등)
	private String empId;					// 업로더

	private MultipartFile attachFile; // 클라이언트의 업로드 파일을 받기 위한 프로퍼티

	public void setAttachFile(MultipartFile attachFile) {
		if(attachFile == null || attachFile.isEmpty()) {
			return;
		}else {
			// 원본파일명 가져오기
			String originalFileName = attachFile.getOriginalFilename();

			// 파일명과 확장자 분리
			int dotIndex = originalFileName.lastIndexOf(".");

			this.attachFile = attachFile;
			this.attachFileName = attachFile.getOriginalFilename().substring(0,dotIndex);			// 파일명
			this.attachOrgFileName = originalFileName;												// 원본 파일명
			this.attachFileSize = attachFile.getSize();												// 파일 크기
			this.attachFilePath = UUID.randomUUID().toString();										// 파일 경로
			this.attachFileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // 파일 확장자

			log.info("attachFileVo에서 생성된 파일번호 {}", attachFileNo);
		}
	}
	private AttachFileGroupVO attachFileGroupVo;	//첨부파일그룹 has a 관계

	@Override
	public String toString() {
		return "AttachFileVO [" + (attachFileNo != null ? "attachFileNo=" + attachFileNo + ", " : "")
				+ (fileGroupNo != null ? "fileGroupNo=" + fileGroupNo + ", " : "")
				+ (attachFileName != null ? "attachFileName=" + attachFileName + ", " : "")
				+ (attachOrgFileName != null ? "attachOrgFileName=" + attachOrgFileName + ", " : "")
				+ (attachFileSize != null ? "attachFileSize=" + attachFileSize + ", " : "")
				+ (attachFilePath != null ? "attachFilePath=" + attachFilePath + ", " : "")
				+ (attachFileExt != null ? "attachFileExt=" + attachFileExt + ", " : "")
				+ (attachFileDate != null ? "attachFileDate=" + attachFileDate + ", " : "")
				+ (attachFilemaxSize != null ? "attachFilemaxSize=" + attachFilemaxSize + ", " : "")
				+ (attachFileStatus != null ? "attachFileStatus=" + attachFileStatus + ", " : "")
				+ (empId != null ? "empId=" + empId + ", " : "")
				+ (attachFile != null ? "attachFile=" + attachFile + ", " : "")
				+ (attachFileGroupVo != null ? "attachFileGroupVo=" + attachFileGroupVo : "") + "]";
	}


}