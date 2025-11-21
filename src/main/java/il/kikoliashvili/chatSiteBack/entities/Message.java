package il.kikoliashvili.chatSiteBack.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Class for messages in chat
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {
    /** id of the message */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    /** text of the message */
    private String text;
    /** start time of generating the message */
    private LocalDateTime startTime;
    /** end time of generating the message */
    private LocalDateTime endTime;
    /** token count of the message */
    private int tokens;
    /** in what chat the message is */
    @ManyToOne
    private Chat chat;
    /** error of generating the message (if exists) */
    private String error;
}
