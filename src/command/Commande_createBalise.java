package command;

import java.awt.Point;

import model.Memoire;
import model.strategie.deplacement.DeplacementHorizontal;
import simulation.Simulation;

public class Commande_createBalise implements ICommande
{

  Simulation simulation;

  public Commande_createBalise(Simulation simulation)
  {
    this.simulation = simulation;
  }

  @Override
  public void execute()
  {
    this.simulation.addBalise(new Memoire(100), new Point(400, 200), new DeplacementHorizontal(50, 750), 5);
  }

}
