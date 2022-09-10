package sk.mmarcincin.chorder2.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sk.mmarcincin.chorder2.models.Member;

import java.util.Optional;


@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

    Member findMemberById(String id);

    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);
}
