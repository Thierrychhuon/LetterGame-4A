package fr.esiea.chhuondaniere.jeu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import fr.esiea.chhuondaniere.jeu.Jeu;

/**
 * Unit test sample for CommonPot.
 */
public class JeuTest {
	Jeu jeuTest = new Jeu();

    @Before
    public void setup() {
    }

    @Test
    public void testdejaVuWord() {
    	jeuTest.addToFoundWordList("un mot déjà vu");
    	assertFalse(jeuTest.dejaVuWord("un mot inconnu"));
        assertTrue(jeuTest.dejaVuWord("un mot déjà vu"));
    }
    public void testYesOrNo() {
    	assertFalse(jeuTest.yesOrNo("blabla"));
        assertTrue(jeuTest.yesOrNo("yes"));
        assertTrue(jeuTest.yesOrNo("no"));
    }
}
