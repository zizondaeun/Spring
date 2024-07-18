package com.yedam.app.board.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

//@AllArgsConstructor //매개변수로 생성자 받는 방식(DI 어려우면 이 방법으로 하기)
@Controller
public class BoardController {
	
	private BoardService boardService;
	
	//DI(필드주입말고 "생성자" 또는 getter,setter로 하기 - BoardServiceImpl참고해서 하기)
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("boardList")
	public String boardList(Model model) { //Model = Request + Response
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
//		model.addAttribute("board", new BoardVO()); //수정이 아니라 등록에서 model써봄..
//		return "board/boardInsert";
//	}
	
	// 등록 - 처리 : URI - boardInsert / PARAMETER - BoardVO(QueryString)
	//             RETURN - 단건조회 다시 호출
	@PostMapping("boardInsert")
	public String boardInsertProcess(BoardVO boardVO) {
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
	public Map<String, Object> boardUpdateProcess(@RequestBody BoardVO boardVO){
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
