package modelDTO.clientNotifies;

import modelDTO.GameDTO;
import modelDTO.gameTableDTO.PlayerDTO;

public class ErrorDTONotify implements ClientNotify{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4180795696157550098L;
	private String message;

	public ErrorDTONotify(String message) {
		this.message=message;
	}

	@Override
	public void act(GameDTO gameDTOtoupdate, PlayerDTO playerDTO) {
		System.out.println(this.message);
		
	}

	
}
