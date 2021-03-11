package shooter.Menu;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Trieda JTextFieldLimit sluzi na udavanie poctu znakov, ktore mozu byt vlozene do daneho text-fieldu.
 */
public class JTextFieldLimit extends PlainDocument {

    private int limit;

    /**
     * Nastavi limit znakov.
     * @param limit limit
     */
    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    /**
     * Spracuvava pocet znakov v text-fielde.
     * @param offset offset
     * @param str retazec
     * @param attr atribut
     * @throws BadLocationException vynimka
     */
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
