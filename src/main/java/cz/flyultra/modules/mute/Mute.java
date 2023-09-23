package cz.flyultra.modules.mute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Mute {

    private String playerName;

    private String mutedBy;

    private String reason;

    private long dateOfCreation;

    private long dateOfExpire;

    private boolean isPerm;

    private boolean isUnMuted;

    private String unMutedBy;

    public boolean isActive() {
        if (isUnMuted) {
            return false;
        }

        if (isPerm) {
            return true;
        }

        if (dateOfExpire < System.currentTimeMillis()) {
            return true;
        }

        return false;
    }

}
