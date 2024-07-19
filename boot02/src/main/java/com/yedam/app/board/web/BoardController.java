package com.yedam.app.board.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

//@AllArgsConstructor //매개변수로 생성자 받는 방식(DI 어려우면 이 방법으로 하기)
@Controller
public class BoardController {
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	private BoardService boardService;
	
	//DI(필드주입말고 "생성자" 또는 getter,setter로 하기 - BoardServiceImpl참고해서 하기)
	@Autowired //*잊지말자-생성자가 2개이상일때는 @Autowired해줘야함 하나라서 필요없다고 노란색뜨는거
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("boardList")
	public String boardList(Model model) { //Model = "Req"uest + Response
		List<BoardVO> list = boardService.boardList();
		model.addAttribute("boards", list); //경로랑 다른거니까 주의!
		return "board/boardList";
		//classpath:/templates/	board/boardList	.html
	}
	
	// 단건조회 : URI - boardInfo / PARAMETER - BoardVO(QueryString)
	//          RETURN - board/boardInfo
	@GetMapping("boardInfo")
	public String boardInfo(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("board", findVO);
		return "board/boardInfo";
		//classpath:/templates/	board/boardInfo	.html
	}

	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertForm() { //일반적인 <form/> 활용
		return "board/boardInsert";
	}
	
	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
//	@GetMapping("boardInsert")
//	public String boardInsertForm(Model model) { //일반적인 <form/> 활용
//		model.addAttribute("board", new BoardVO()); //**수정이 아니라 등록에서 model써봄..
//		return "board/boardInsert";
//	}
	
	//교수님 방법 static(프로젝트 내부)
	// 등록 - 처리 : URI - boardInsert / PARAMETER - BoardVO(QueryString)
	//             RETURN - 단건조회 다시 호출
	@PostMapping("boardInsert")
	public String boardInsertProcess(BoardVO boardVO) {
		int bno = boardService.insertBoard(boardVO);
		return "redirect:boardInfo?boardNo=" + bno;
	}
	
	// 등록 - 처리 : URI - boardInsert / PARAMETER - BoardVO(QueryString)
	//             RETURN - 단건조회 다시 호출
	//@PostMapping("boardInsert")
	public String boardInsertProcess(BoardVO boardVO, @RequestPart MultipartFile[] images) {
		//log.info(images[0].getOriginalFilename()); //파일 이름만 가져온거
		for(MultipartFile image : images) {
			//1)원래 파일이름
			String fileName = image.getOriginalFilename();
			
			//고유한 식별자로 이미지 저장해서 클라이언트가 업로드했을때 파일이름이 겹치지 않도록 하는거
			UUID uuid = UUID.randomUUID();
			String uniqueFileName = uuid + "_" + fileName;
			
			//2)실제로 저장할 경로를 생성 : 서버의 업로드 경로 + 파일이름
			String saveName = uploadPath + File.separator + uniqueFileName; //""가 /와 같아
			
			Path savePath = Paths.get(saveName); //여기에 경로 담았음
			
			boardVO.setImage(uniqueFileName); //파일의 정보를 가져와서 boardVO에 파일의 이름을 넣어줌
			//3)*파일 작성(파일 업로드)
			try {
				image.transferTo(savePath); //*실제 경로 지정 /Path는 경로/transferTo=햇살
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		int bno = boardService.insertBoard(boardVO);
		return "redirect:boardInfo?boardNo=" + bno;
	}

	// 수정 - 페이지 : URI - boardUpdate / PARAMETER - BoardVO(QueryString)
	//               RETURN - board/boardUpdate
	// => 단건조회
	@GetMapping("boardUpdate")
	public String boardUpdateForm(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);
		return "board/boardUpdate";
	}
	
	// 수정 - 처리 : URI - boardUpdate / PARAMETER - BoardVO(JSON)
	//             RETURN - 수정결과 데이터(Map) => 이러면 리턴하는게 데이터면 아작스임 => @ResponseBody
	// => 등록(내부에서 수행하는 쿼리문 - UPDATE문)
	@PostMapping("boardUpdate")
	@ResponseBody
	public Map<String, Object> boardUpdateProcess(@RequestBody BoardVO boardVO){ //@RequestBody면 html에서 JSON으로 보내는거 잊지망
		return boardService.updateBoard(boardVO);
	}
	
	// 삭제 - 처리 : URI - boardDelete / PARAMETER - Integer-데이터 타입으로 Integer넣어
	//             RETURN - 전체조회 다시 호출
	@GetMapping("boardDelete")
	public String boardDelete(@RequestParam Integer boardNo) {
		boardService.deleteBoard(boardNo);
		return "redirect:boardList";
	}

}
