package edu.bionic.sverkunov.com.services;

import java.util.List;

import edu.bionic.sverkunov.com.DAODB3.classes.KitchenList;

public interface KitchenServiceI {

	List<KitchenList> updateKitchenScreen();

	void done(KitchenList kl, int staffId);

}
