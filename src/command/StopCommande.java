package command;

public class StopCommande implements ICommande
{

  @Override
  public void execute()
  {
    System.out.println("STOP");
  }

}
