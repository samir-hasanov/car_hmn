package www.hmn.az.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import www.hmn.az.entity.Mark;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkRepository extends JpaRepository<Mark,Long> {

    Optional<List<Mark>> findByDeletedFalse();
    Mark findByDeletedFalseAndId(Long id);
//    Optional<List<Mark>> findByDeletedFalse();
}
