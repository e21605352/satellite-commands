package command;

import java.util.Map;

public class CommandeBaliseModification implements ICommande
{

  Map<String, String> arguments;

  public CommandeBaliseModification(Map<String, String> arguments)
  {
    this.arguments = arguments;
  }

  @Override
  public void execute()
  {

  }

}
