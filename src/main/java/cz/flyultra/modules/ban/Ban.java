package cz.flyultra.modules.ban;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Ban {

    private String playerName;

    private String bannedBy;

    private String reason;

    private long dateOfCreation;

    private long dateOfExpire;

    private boolean isPerm;

    private boolean isUnBanned;

    private String unBannedBy;

    public boolean isActive() {
        if (isUnBanned) {
            return false;
        }

        if (isPerm) {
            return true;
        }

        if (dateOfExpire > dateOfCreation) {
            return true;
        }

        return false;
    }

}