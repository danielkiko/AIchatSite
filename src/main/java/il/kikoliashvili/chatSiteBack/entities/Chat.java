package il.kikoliashvili.chatSiteBack.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

/**
 * Class for chat instance
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Chat {
    /** id of the chat */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    /** name of the chat */
    private String name;
    /** messages in the chat */
    private String messages;
    /** user that created the chat */
    @ManyToOne
    private SiteUser siteUser;
    /** date of creation of chat */
    private Date creationDate;
}
