package tripdairy.gotrip.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripdairy.gotrip.domain.Users;
import tripdairy.gotrip.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Users users) {
        validateDuplicateUsers(users); //중복 회원 검증
        usersRepository.save(users);
        return users.getId();
    }

    private void validateDuplicateUsers(Users users) {
        Optional<Users> findUsers = usersRepository.findByEmail(users.getEmail());
        if (findUsers.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //유저 전체 조회
    public List<Users> findUsers() {
        return usersRepository.findAll();
    }

    //유저 단건 조회
    public Users findOne(Long usersId) {
        return usersRepository.findOne(usersId);
    }

}
