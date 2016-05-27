package modelDTO.marketDTO;

import java.util.ArrayList;
import java.util.List;

import modelDTO.ModelDTO;
import players.Player;
import server.model.market.Market;
import server.model.market.Offer;

public class MarketDTO implements ModelDTO<Market>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6208915629908932963L;
	private List<OfferDTO> offersList;
	private List<String> sellingPlayerList;
	private List<String> buyingPlayerList;

	public MarketDTO(){
		this.offersList=new ArrayList<OfferDTO>();
		this.sellingPlayerList=new ArrayList<String>();
		this.buyingPlayerList=new ArrayList<String>();
	}
	
	
	@Override
	public void map(Market realObject) {
		
		for (Offer realOffer : realObject.getOffersList()) {
			OfferDTO offerDTO=new OfferDTO();
			offerDTO.map(realOffer);
			this.offersList.add(offerDTO);
		}
		for (Player realPlayer : realObject.getSellingPlayerList())
			this.sellingPlayerList.add(realPlayer.getName());
		for (Player realPlayer : realObject.getBuyingPlayerList())
			this.sellingPlayerList.add(realPlayer.getName());
		
	}

	public List<OfferDTO> getOffersList() {
		return this.offersList;
	}

	public void setOffersList(ArrayList<OfferDTO> offersList) {
		this.offersList = offersList;
	}

	public List<String> getSellingPlayerList() {
		return this.sellingPlayerList;
	}

	public void setSellingPlayerList(List<String> sellingPlayerList) {
		this.sellingPlayerList = sellingPlayerList;
	}

	public List<String> getBuyingPlayerList() {
		return this.buyingPlayerList;
	}

	public void setBuyingPlayerList(ArrayList<String> buyingPlayerList) {
		this.buyingPlayerList = buyingPlayerList;
	}



}
