package pl.chomiczek.projekt_kosowski_rynski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.chomiczek.projekt_kosowski_rynski.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByLanguageAndAndEmail(String language, String email);

}
