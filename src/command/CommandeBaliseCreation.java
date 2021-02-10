package command;

import java.awt.Point;

import model.Memoire;
import model.balise.Balise;
import model.balise.etats.BaliseCollecte;
import model.strategie.deplacement.Deplacement;
import model.strategie.deplacement.DeplacementHorizontal;
import simulation.Simulation;

public class CommandeBaliseCreation implements ICommande<Balise>
{

  Simulation simulation;

  private final Memoire memoire = new Memoire(100);
  private final Integer vitesse = 5;
  private final Point position = new Point(400, 200);
  private final Deplacement deplacement = new DeplacementHorizontal(50, 750);

  public CommandeBaliseCreation(Simulation simulation)
  {
    this.simulation = simulation;
  }

  @Override
  public Balise execute()
  {
    Balise balise = new Balise(this.memoire, this.vitesse);
    balise.setPosition(this.position);
    balise.setDeplacementCollecte(this.deplacement);
    balise.setProfondeurCollecte(this.position.y);
    balise.setEtat(new BaliseCollecte(balise));
    this.simulation.addBalise(balise);

    System.out.println(balise);

    return balise;
  }
}
