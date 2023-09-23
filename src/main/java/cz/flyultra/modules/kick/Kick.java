package cz.flyultra.modules.kick;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Kick {

    private String playerName;

    private String kickedBy;

    private String reason;

    private long dateOfKick;



}