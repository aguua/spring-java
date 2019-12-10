package pl.edu.ug.springdemo.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
	public class Shop implements Card {
	 
	    private List<String> shoppingCard ;
	 
	    @Autowired
	    public Shop(List<String> shoppingCard) {
	        this.shoppingCard = shoppingCard;
	    }
	    public void addToCard(String product) {
	    	shoppingCard.add(product);
	    }
	    public List<String> getCard(){
	    	return shoppingCard;
	    }
	    

	}
