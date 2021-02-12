package command;

import model.ElementMobile;

public class CommandeElementMobileStartStop implements ICommande<ElementMobile>
{

  ElementMobile elementMobile;
  boolean start;

  public CommandeElementMobileStartStop(ElementMobile elementMobile, boolean start)
  {
    this.elementMobile = elementMobile;
    this.start = start;
  }

  @Override
  public ElementMobile execute()
  {
    this.elementMobile.setStart(this.start);
    return this.elementMobile;
  }
}
