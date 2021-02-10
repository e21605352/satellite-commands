package command;

import java.awt.Point;
import java.util.Map;

import model.Memoire;
import model.balise.Balise;
import model.balise.etats.BaliseCollecte;
import model.strategie.deplacement.DeplacementHorizontal;
import simulation.Simulation;

public class CommandeBaliseCreation implements ICommande<Balise>
{

  Simulation simulation;
  Map<String, String> arguments;

  public CommandeBaliseCreation(Simulation simulation, Map<String, String> arguments)
  {
    this.simulation = simulation;
    this.arguments = arguments;
  }

  @Override
  public Balise execute()
  {
    Balise balise = new Balise(new Memoire(100), 5);
    balise.setPosition(new Point(400, 200));
    balise.setDeplacementCollecte(new DeplacementHorizontal(50, 750));
    balise.setProfondeurCollecte(200);
    balise.setEtat(new BaliseCollecte(balise));
    this.simulation.addBalise(balise);

    return balise;
  }
}
