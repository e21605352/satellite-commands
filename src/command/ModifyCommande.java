package command;

import java.util.Map;

public class ModifyCommande implements ICommande
{

  Map<String, String> arguments;

  public ModifyCommande(Map<String, String> arguments)
  {
    this.arguments = arguments;
  }

  @Override
  public void execute()
  {
    System.out.println(arguments);
  }

}
