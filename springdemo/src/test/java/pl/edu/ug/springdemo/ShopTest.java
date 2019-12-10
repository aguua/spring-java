package pl.edu.ug.springdemo;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.ug.springdemo.domain.Shop;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
public class ShopTest {

	@Autowired
    private Shop shop;
	
	@Test
	public void testadding() {
		String owoc = "banan";
		//shop.addToCard(owoc);
		//assertTrue(shop.getCard().contains(owoc));
	}
}
