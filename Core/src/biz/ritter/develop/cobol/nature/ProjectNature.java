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
package biz.ritter.develop.cobol.nature;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

import biz.ritter.develop.cobol.builder.AbstractIncrementalProjectBuilder;
import biz.ritter.develop.cobol.internal.CODEConstants;

/**
 * @author Nerger
 * 
 */
public class ProjectNature implements IProjectNature {
  
  private IProject cobolProject;
  
  /**
	 * 
	 */
  public ProjectNature() {
  }
  
  @Override
  public void configure() throws CoreException {
    System.out.println(new Exception().getStackTrace()[0].getMethodName());
    AbstractIncrementalProjectBuilder.addBuilder(this.getProject(),
        CODEConstants.CODE_FULLQUALIFIED_BUILDER_ID, true, null);
  }
  
  @Override
  public void deconfigure() throws CoreException {
    System.out.println(new Exception().getStackTrace()[0].getMethodName());
    AbstractIncrementalProjectBuilder.removeBuilder(this.getProject(),
        CODEConstants.CODE_FULLQUALIFIED_BUILDER_ID, true, null);
  }
  
  @Override
  public IProject getProject() {
    System.out.println(new Exception().getStackTrace()[0].getMethodName());
    return this.cobolProject;
  }
  
  @Override
  public void setProject(final IProject project) {
    System.out.println(new Exception().getStackTrace()[0].getMethodName());
    this.cobolProject = project;
  }
  
}
