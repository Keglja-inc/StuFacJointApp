package com.example.stufacjoint.test;

import com.example.stufacjoint.GlavnaAktivnost;
import com.example.stufacjoint.LoginAktivnost;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class TestingActivities extends
		ActivityInstrumentationTestCase2<GlavnaAktivnost> {
	
	private Solo solo;

	public TestingActivities() {
		super("com.example.stufacjoint", GlavnaAktivnost.class);
		
	}

	public void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
	
	
	/* testiramo da li UI aplikacije sadr�i gumb "Login" te 
	 * da li postoji event kada se na njega klikne */
	public void testLoginButton() throws Exception {
		solo.waitForActivity(LoginAktivnost.class);
		boolean found = solo.searchButton("Login", true);
		//solo.clickOnButton(0);
		assertTrue(found);		
	}
	
	
	/* testiramo postojanje aktivnosti u aplikaciji */
	public void testingActivities() throws Exception {
		solo.assertCurrentActivity("Pogre�na aktivnost!", GlavnaAktivnost.class);
		//solo.assertCurrentActivity("Pogre�na aktivnost!", ProfilAktivnost.class );
		//solo.assertCurrentActivity("Pogre�na aktivnost!", FakultetiAktivnost.class);
	}
	
	
	/* testiramo pojavljivanje poruke na ekranu kada kliknemo na gumb za dohva�anje liste fakulteta */
	public void testToastMsg() throws Exception {
		//solo.waitForActivity(GlavnaAktivnost.class);
		solo.clickOnImageButton(2);
		assertTrue(solo.waitForText("Dohva�anje podataka...pri�ekajte molim..."));
		

	}

}
