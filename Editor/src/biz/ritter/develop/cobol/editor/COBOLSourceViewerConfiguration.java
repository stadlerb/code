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
package biz.ritter.develop.cobol.editor;

import biz.ritter.develop.cobol.oc.COBOLWordCompletionProcessor;
import biz.ritter.develop.cobol.oc.KeywordScanner;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

/* Overwrite SourceViewerConfiguration methods for nice editor functions like
 * syntax highlightning or code assists. 
 */
public class COBOLSourceViewerConfiguration extends SourceViewerConfiguration {
  
  private ITokenScanner keywordScanner;
  private ITokenScanner commentScanner;

  public String[] getConfiguredContentTypes( final ISourceViewer sv ) {
    return new String[] { IDocument.DEFAULT_CONTENT_TYPE, 
                          COBOLSourcePartitionScanner.COMMENT };
  }
  
  /** IContentAssistant open a simple list when CTRL+SPACE is pressing
   * (completion proposals). 
   */
  public IContentAssistant getContentAssistant( final ISourceViewer sv ) {
    ContentAssistant result = new ContentAssistant();
    result.setContentAssistProcessor( new COBOLWordCompletionProcessor(),
                                      IDocument.DEFAULT_CONTENT_TYPE );
    result.setProposalPopupOrientation( IContentAssistant.PROPOSAL_OVERLAY );
    return result;
  }
  
  
  /** PresentationReconciler for  syntax highlightning .
   * @param sv 
   */
  public IPresentationReconciler getPresentationReconciler( 
                                                      final ISourceViewer sv ) {
    PresentationReconciler reconciler = new PresentationReconciler();
    
    // F�r jeden Typ der vom PartitionScanner zur�ckgebenen Tokens wird ein
    // Damager und ein Repairer verwendet (ggf. in einer Klasse zusammengefa�t)
    DefaultDamagerRepairer defaultDr 
      = new DefaultDamagerRepairer( getKeywordScannerInstance() );
    reconciler.setDamager( defaultDr, IDocument.DEFAULT_CONTENT_TYPE );
    reconciler.setRepairer( defaultDr, IDocument.DEFAULT_CONTENT_TYPE );
    
    DefaultDamagerRepairer commentDr = new DefaultDamagerRepairer( getCommentScannerInstance() );
    reconciler.setDamager( commentDr, COBOLSourcePartitionScanner.COMMENT );
    reconciler.setRepairer( commentDr, COBOLSourcePartitionScanner.COMMENT );

    return reconciler;
  }
  
  
  
  private ITokenScanner getKeywordScannerInstance() {
    if( null == keywordScanner ) {
      keywordScanner = new KeywordScanner();
    }
    return keywordScanner;
  }
  
  private ITokenScanner getCommentScannerInstance() {
    if( null == commentScanner ) {
      commentScanner = new CommentScanner();
    }
    return commentScanner;
  }
}