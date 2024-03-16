package tripdairy.gotrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tripdairy.gotrip.domain.Users;
import tripdairy.gotrip.repository.UsersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UsersRepository usersRepository;

    public Users login(String email, String password) throws NotCorrespondingEmailException {
        Optional<Users> findUsers = usersRepository.findByEmail(email);
        if (!findUsers.orElseThrow(() -> new NotCorrespondingEmailException("해당 이메일이 존재하지 않습니다.")).checkPassword(password)) {
            throw new IllegalStateException("이메일과 비밀번호가 일치하지 않습니다.");
        }
        return findUsers.get();
    }
}