/**
 *
 * @author Ritwik Basak
 */
import javax.swing.text.*;
class LongRangeDocument extends PlainDocument {

  private long minimum, maximum;

  long currentValue = 0;

  public LongRangeDocument(long minimum, long maximum) {
    this.minimum = minimum;
    this.maximum = maximum;
  }

  public long getValue() {
    return currentValue;
  }

  public void insertString(int offset, String string, AttributeSet attributes)
      throws BadLocationException {

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
        //System.out.println("Error");
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
      //System.out.println("Error");
    }
  }

  public long checkInput(String proposedValue) throws NumberFormatException {
    long newValue = 0;
    if (proposedValue.length() > 0) {
      newValue = Long.parseLong(proposedValue);
    }
    if ((minimum <= newValue) && (newValue <= maximum)) {
      return newValue;
    } else {
      throw new NumberFormatException();
    }
  }
}
