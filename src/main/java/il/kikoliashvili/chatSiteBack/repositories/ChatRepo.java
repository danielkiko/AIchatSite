package il.kikoliashvili.chatSiteBack.repositories;

import il.kikoliashvili.chatSiteBack.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for chats
 */
@Repository
public interface ChatRepo extends JpaRepository <Chat, UUID> {
}
