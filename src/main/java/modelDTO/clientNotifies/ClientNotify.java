package modelDTO.clientNotifies;

import java.io.Serializable;

import modelDTO.GameDTO;

@FunctionalInterface
public interface ClientNotify extends Serializable{

	public void act(GameDTO gameDTOtoupdate);
}