package modelDTO;

import java.io.Serializable;

public interface ModelDTO<O> extends Serializable{

	public void map(O realObject);
}
