/**
 * Copyright 2010 Sebastian Nerger
 *
 * Lizenziert unter der EUPL, nur Version 1.1 ("Lizenz");
 * 
 * Sie d�rfen dieses Werk ausschlie�lich gem��
 * dieser Lizenz nutzen.
 * Eine Kopie der Lizenz finden Sie hier:
 *
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Sofern nicht durch anwendbare Rechtsvorschriften
 * gefordert oder in schriftlicher Form vereinbart, wird
 * die unter der Lizenz verbreitete Software "so wie sie
 * ist", OHNE JEGLICHE GEW�HRLEISTUNG ODER BEDINGUNGEN -
 * ausdr�cklich oder stillschweigend - verbreitet.
 * Die sprachspezifischen Genehmigungen und Beschr�nkungen
 * unter der Lizenz sind dem Lizenztext zu entnehmen.
 */
package biz.ritter.develop.cobol.filetypes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;

/**
 * @author Nerger
 * 
 */
public class ContentTypeDescriber implements ITextContentDescriber {
  
  /**
	 * 
	 */
  public ContentTypeDescriber() {
  }
  
  @Override
  public int describe(final InputStream contents,
      final IContentDescription description) throws IOException {
    return this.describe(new InputStreamReader(contents), description);
  }
  
  @Override
  public QualifiedName[] getSupportedOptions() {
    return null;
  }
  
  @Override
  public int describe(final Reader contents,
      final IContentDescription description) throws IOException {
    return INDETERMINATE;
  }
  
}
