package fr.esiea.chhuondaniere.dictionary;

import org.junit.Before;
import org.junit.Test;

import fr.esiea.chhuondaniere.dictionary.Dictionnaire;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test sample for Dictionary.
 */
public class DictionaryTest {

    private Dictionnaire dictionary;

    @Before
    public void setup() {
        dictionary= new Dictionnaire();
    }

    @Test
    public void testIsWord() {
        assertTrue(dictionary.isWordValid("maman"));
        assertFalse(dictionary.isWordValid("namam"));
    }
}
