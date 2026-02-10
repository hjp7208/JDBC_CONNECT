package repository;

import java.util.List;
import java.util.Optional;

import domain.users.UserVO;

public interface Users {
    // 레코드 추가
    int userAdd(UserVO user);
    // 레코드 수정
    int userMod(UserVO userVO);
    // 레코드 삭제
    int userDel(UserVO user);
    // 레코드 조회
    // 1. 전체 조회
    List<UserVO> userAll();
    // 2. 조건 조회 - (user_id(UNIQUE), name), user_email(UNIQUE 설정하지 않았지만 원래는 했어야 함.) # UNIQUE 설정 여부
    Optional<UserVO> logIn(String userId, String userPw);
    Optional<UserVO> userSearch(String user_email);

}