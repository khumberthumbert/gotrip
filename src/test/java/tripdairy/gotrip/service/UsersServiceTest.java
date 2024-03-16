package tripdairy.gotrip.service;

import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tripdairy.gotrip.domain.Users;
import tripdairy.gotrip.repository.UsersRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UsersServiceTest {

    @Autowired UsersService usersService;
    @Autowired UsersRepository usersRepository;

    @Test
    //@Rollback(value = false)
    public void 회원가입() throws Exception {
        //given
        Users users = new Users();
        users.setName("kim");
        users.setPassword("1234");
        //when
        Long saveId = usersService.join(users);

        //then
        //em.flush();
        assertEquals(users, usersRepository.findOne(users.getId()));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_유저_예외() throws Exception {
        //given
        Users users1 = new Users();
        users1.setName("kim");

        Users users2 = new Users();
        users2.setName("kim");

        //when
        usersService.join(users1);
        usersService.join(users2);//예외

        //then
        fail("예외가 발생해야 한다.");
    }
}