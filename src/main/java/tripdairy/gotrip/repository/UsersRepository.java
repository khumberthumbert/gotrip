package tripdairy.gotrip.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tripdairy.gotrip.domain.Users;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsersRepository {

    private final EntityManager em;

    //유저 등록
    public void save(Users users) {
        em.persist(users);
    }

    //유저 조회
    public Users findOne(Long id) {
        return em.find(Users.class, id);
    }

    //유저 전체 조회
    public List<Users> findAll() {
        //첫 번째 JPQL, 두 번째 반환 타입.
        return em.createQuery("select u from Users u", Users.class)
                .getResultList();
    }

    //이름으로 유저 조회
    public List<Users> findByName(String name) {
        return em.createQuery("select u from Users u where u.name = :name", Users.class)
                .setParameter("name", name)
                .getResultList();
    }

    //유저 삭제
    public Long delete(Users users) {
        em.remove(users);
        return users.getId();
    }

    public Optional<Users> findByEmail(String email){
        return em.createQuery("select u from Users u where u.email = :email",Users.class)
                .setParameter("email", email)
                .getResultList().stream().findAny();
    }
}
