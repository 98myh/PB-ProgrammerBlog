package com.pb.pblog.service;

import com.pb.pblog.config.auth.CustomUserDetails;
import com.pb.pblog.dto.*;
import com.pb.pblog.entity.Board;
import com.pb.pblog.entity.Comment;
import com.pb.pblog.entity.User;
import com.pb.pblog.repository.BoardMapper;
import com.pb.pblog.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    //게시판 mapper
    private final BoardMapper boardMapper;

    //댓글 mapper
    private final CommentMapper commentMapper;

    //이미지 저장 경로
    private static final String BASE_UPLOAD_FOLDER = "C:/image/";
//    private static final String BASE_UPLOAD_FOLDER = "D:/image/";

    //메인
    @Override
    public MainResponseDTO main() {
        List<Board> boards=boardMapper.mainBoard();

        //Entity를 DTO로 형변환
        List<BoardAndUserDTO> boardAndUserDTOS=new ArrayList<>();
        for(Board board:boards){
            if(board!=null) {
                String content = board.getContent();
                Document doc = Jsoup.parse(content);
                Elements images = doc.select("img");
                String firstImgSrc = images.size() > 0 ? images.get(0).attr("src") : "/resources/images/pblogo.png";
                board.setContent(firstImgSrc);

                boardAndUserDTOS.add(boardAndUserEntityToDTO(board));
            }else{
                boardAndUserDTOS.add(BoardAndUserDTO.builder().build());
            }
        }

        //최근 게시글 체크
        boolean nullCheck=false;

        //최근 게시글 5개
        List<BoardAndUserDTO> listRecently=new ArrayList<>();
        //개발동향 게시글 5개
        List<BoardAndUserDTO> listTrend=new ArrayList<>();
        //개발스킬 게시글 5개
        List<BoardAndUserDTO> listSkill=new ArrayList<>();
        //알고리즘 게시글 5개
        List<BoardAndUserDTO> listAlgorithm=new ArrayList<>();

        for (BoardAndUserDTO board:boardAndUserDTOS){
            //null 이면 최근 게시글에 추가 멈춤, 해당하는 카테고리에 추가
            if(board.getBid()==null){
                nullCheck=true;
            }
            //null 이전의 데이터는 최근 게시글에 추가
            if(!nullCheck){
                listRecently.add(board);
            }
            if(board.getBid()!=null) {
                //개발동향 추가
                if(board.getCategory().equals("trend")){
                    listTrend.add(board);
                }
                //개발스킬 추가
                else if(board.getCategory().equals("skill")){
                    listSkill.add(board);
                }
                //알고리즘 추가
                else if(board.getCategory().equals("algorithm")){
                    listAlgorithm.add(board);
                }
            }
        }
        //반환할 DTO로 변환
        MainResponseDTO mainResponseDTO= MainResponseDTO.builder()
                .listRecently(listRecently)
                .listTrend(listTrend)
                .listSkill(listSkill)
                .listAlgorithm(listAlgorithm)
                .build();
        return mainResponseDTO;
    }

    //이미지 저장
    @Override
    public String imageSave(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "No file uploaded.";
            }

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

            String dateFolder = now.format(dateFormatter);
            String timestamp = now.format(timeFormatter);

            Path datePath = Paths.get(BASE_UPLOAD_FOLDER + dateFolder);
            if (!Files.exists(datePath)) {
                Files.createDirectories(datePath);  // 날짜 폴더 생성
            }

            String newFileName = timestamp + "_" + file.getOriginalFilename();  // 파일 이름 설정
            Path filePath = datePath.resolve(newFileName);
            byte[] bytes = file.getBytes();
            Files.write(filePath, bytes);  // 파일 저장


            String fileDownloadUri = "/images/" + dateFolder + "/" + newFileName;  // 웹 접근 경로 반환

            return fileDownloadUri;
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }

    //글 저장
    @Override
    public int boardSave(BoardSaveDTO boardSaveDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            Board board=Board.builder()
                    .user(User.builder()
                            .uid(userDetails.getUid())
                            .build())
                    .title(boardSaveDTO.getTitle())
                    .category(boardSaveDTO.getCategory())
                    .content(boardSaveDTO.getContent())
                    .build();

            boardMapper.boardSave(board);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    //글 수정
    @Override
    public int boardEdit(BoardEditDTO boardEditDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            Board board=Board.builder()
                    .user(User.builder()
                            .uid(userDetails.getUid())
                            .build())
                    .title(boardEditDTO.getTitle())
                    .bid(boardEditDTO.getBid())
                    .content(boardEditDTO.getContent())
                    .category(boardEditDTO.getCategory())
                    .update_date(LocalDateTime.now())
                    .build();
            int result= boardMapper.boardEdit(board);
            return result;
        }catch (Exception e){
            return 0;
        }
    }

    //글 삭제
    @Override
    public int boardDelete(Long bid) {
        int result=boardMapper.boardDelete(bid);
        return result;
    }


    //게시글 조회
    @Override
    public List<BoardAndUserDTO> boardSearch(String category,String title) {
        List<Board>boards=boardMapper.boardSearch(category,title,null);

        //DTO로 형변환
        List<BoardAndUserDTO> boardAndUserDTOS = boards.stream()
                .map(board -> boardAndUserEntityToDTO(board))
                .collect(Collectors.toList());

        return boardAndUserDTOS;
    }

    //게시글 상세 조회
    @Override
    public BoardResponseDTO boardDetails(Long bid) {
        //게시글 정보
        Board board=boardMapper.boardDetail(bid);
        BoardAndUserDTO boardAndUserDTO=boardAndUserEntityToDTO(board);


        //댓글 정보
        List<Comment> comments=commentMapper.boardComment(bid);

        //상위 댓글 먼저 분류
        List<CommentDTO> commentDTOS=comments.stream()
                .filter(comment -> comment.getParent_cid()==null)
                .map(comment -> CommentDTO.builder()
                        .topComment(CommentAndUserDTO.builder()
                                .bid(comment.getBoard().getBid())
                                .cid(comment.getCid())
                                .parent_cid(comment.getParent_cid())
                                .uid(comment.getUser().getUid())
                                .nickname(comment.getUser().getNickname())
                                .comment(comment.getComment())
                                .create_date(comment.getCreate_date())
                                .update_date(comment.getUpdate_date())
                                .build())
                        .build())
                .collect(Collectors.toList());

        //대댓글 분류
        for(CommentDTO commentDTO:commentDTOS){
            List<CommentAndUserDTO> childComments=comments.stream()
                    .filter(comment -> commentDTO.getTopComment().getCid().equals(comment.getParent_cid()))
                    .map(comment -> CommentAndUserDTO.builder()
                        .bid(comment.getBoard().getBid())
                        .cid(comment.getCid())
                        .parent_cid(comment.getParent_cid())
                        .uid(comment.getUser().getUid())
                        .nickname(comment.getUser().getNickname())
                        .comment(comment.getComment())
                        .create_date(comment.getCreate_date())
                        .update_date(comment.getUpdate_date())
                        .build())
                    .collect(Collectors.toList());
            //대댓글 추가
            commentDTO.setChildComment(childComments);
        }

        BoardResponseDTO boardResponseDTO=BoardResponseDTO.builder()
                .board(boardAndUserDTO)
                .comments(commentDTOS)
                .build();

        return boardResponseDTO;
    }

}
