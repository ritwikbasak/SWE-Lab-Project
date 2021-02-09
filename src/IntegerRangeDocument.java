/**
 *
 * @author Ritwik Basak
 */
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
class IntegerRangeDocument extends PlainDocument {

    int minimum, maximum;

    int currentValue = 0;

    public IntegerRangeDocument(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getValue() {
        return currentValue;
    }

    public void insertString(int offset, String string, AttributeSet attributes) throws BadLocationException {

        if (string == null) {
            return;
        } else {
            String newValue;
            int length = getLength();
            if (length == 0) {
            newValue = string;
            } else {
                String currentContent = getText(0, length);
                StringBuffer currentBuffer = new StringBuffer(currentContent);
                currentBuffer.insert(offset, string);
                newValue = currentBuffer.toString();
            }
            try {
                currentValue = checkInput(newValue);
                super.insertString(offset, string, attributes);
            } catch (Exception exception) {
                //Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    public void remove(int offset, int length) throws BadLocationException {
        int currentLength = getLength();
        String currentContent = getText(0, currentLength);
        String before = currentContent.substring(0, offset);
        String after = currentContent.substring(length + offset, currentLength);
        String newValue = before + after;
        try {
            currentValue = checkInput(newValue);
            super.remove(offset, length);
        } catch (Exception exception) {
            //Toolkit.getDefaultToolkit().beep();
        }
    }

    public int checkInput(String proposedValue) throws NumberFormatException {
        int newValue = 0;
        boolean flag = true;
        for(int i = 0; i < proposedValue.length(); i ++)
            if(!Character.isDigit(proposedValue.charAt(i)))
                flag = false;
        if (proposedValue.length() > 0) {
            newValue = Integer.parseInt(proposedValue);
        }
        if ((minimum <= newValue) && (newValue <= maximum) && flag == true) {
            return newValue;
        } else {
            throw new NumberFormatException();
        }
    }
}
