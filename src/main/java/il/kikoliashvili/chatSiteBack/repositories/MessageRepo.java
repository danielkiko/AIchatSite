package il.kikoliashvili.chatSiteBack.repositories;

import il.kikoliashvili.chatSiteBack.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for messages
 */
@Repository
public interface MessageRepo extends JpaRepository <Message, UUID> {
}
