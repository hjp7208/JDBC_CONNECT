package service.usermanage;

import java.util.ArrayList;
import java.util.List;
import domain.users.UserVO;
import dto.UserDTO;
import repository.UserDAOImplOracle;
import repository.Users;
import repository.UsersDAOImplMaridDB;

public class UsermanageImpl implements Usermanage {
    // DB 작업을 할 수 있는 객체를 호출 작업 진행...
    // 인터페이스를 통한 객체 호출...
    // Users usersRepository = new UsersDAOImpl();
    // Users usersRepository = new UserDAOImplOracle();
    Users usersRepository = new UsersDAOImplMaridDB();
    @Override
    public List<UserDTO> searchAll() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<UserVO> userVOList = usersRepository.userAll();
        for (UserVO vo : userVOList) {
            UserDTO dto = UserDTO.toUserDTO(vo);
            userDTOList.add(dto);
        }
        return userDTOList;
    }

    @Override
    public UserDTO searchOne(String userEmail) {
        UserVO vo = usersRepository.userSearch(userEmail).get();
        UserDTO dto = UserDTO.toUserDTO(vo);
        return dto;
    }

    @Override
    public boolean userDelete(UserDTO userDTO) {
        // UserDTO -> UserVO
        UserVO userVO = UserDTO.toUserVO(userDTO);

        if (usersRepository.userDel(userVO) != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean userModify(UserDTO userDTO) {
        UserVO userVO = UserDTO.toUserVO(userDTO);
        if (usersRepository.userMod(userVO) != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean userRegister(UserDTO userDTO) {
        UserVO userVO = UserDTO.toUserVO(userDTO);
        if (usersRepository.userAdd(userVO) != 0)
            return true;
        else
            return false;

    }

    @Override
    public UserDTO logIn(String userId, String userPw) {
        return UserDTO.toUserDTO(usersRepository.logIn(userId, userPw).get());
    }

}
