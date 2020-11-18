package message_system;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Use Case class.
 * Stores every Conversation within the program (more suitable for Conversations to be stored in a DB for phase 2).
 */
public class ConversationManager implements Serializable {
    private final Map<UUID, Conversation> allConversations;

    /**
     * Initialize ConversationManager with no conversations
     */
    public ConversationManager() {
        this.allConversations = new HashMap<>();
    }

    /**
     * Creates a new Conversation with a random UUID.
     * @return The conversation id of the newly created Conversation
     */
    public UUID newConversation() {
        UUID conID = UUID.randomUUID();
        Conversation c = new Conversation();
        this.allConversations.put(conID, c);
        return conID;
    }

    /**
     * Gets a conversation by its ID.
     *
     * @param conID The ID of the conversation to be returned
     * @return The conversation with the corresponding conID.
     */
    public Conversation getConversation(UUID conID) {
        return allConversations.get(conID);
    }

    /**
     * @param userID The ID of the user
     * @return All conversations that this user is member of.
     */
    public Conversation[] getUserConversations(UUID userID) {
        ArrayList<Conversation> conversations = new ArrayList<>();

        for (Conversation c : this.allConversations.values())
            for (UUID memberID : c.getMembers())
                if (memberID == userID)
                    conversations.add(c);

        return (Conversation[]) conversations.toArray();
    }
}
