package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;

/**
 * This class represents a notification from the server that the client disconnected successfully
 * @author cg31
 *
 */
public class QuitNotify implements ClientNotify {

	private static final long serialVersionUID = 6262244568457800663L;

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		return;
	}

}
