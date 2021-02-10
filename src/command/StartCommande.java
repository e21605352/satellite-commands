package command;

public class StartCommande implements ICommande
{

  @Override
  public void execute()
  {
    System.out.println("START");
  }

}
