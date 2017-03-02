package fr.esiea.chhuondaniere.jeu;

import org.junit.Before;
import org.junit.Test;
import fr.esiea.chhuondaniere.jeu.CommonPot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test sample for CommonPot.
 */
public class CommotPotTest {

    private CommonPot initialPot;
    String wordpresent= "arbre"; //Nous récupérons que des string en minuscule !
    String wordabsent= "arbree";

    @Before
    public void setup() {
    	initialPot= new CommonPot();
        initialPot.addLetters("ArBre");
        initialPot.displayPotLetter();  

    }

    @Test
    public void testisPotContains() {
        assertFalse(initialPot.isPotContains(wordabsent));
        assertTrue(initialPot.isPotContains(wordpresent));
    }
}

