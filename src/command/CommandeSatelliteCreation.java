package command;

import java.awt.Point;

import model.Satellite;
import model.strategie.deplacement.Deplacement;
import model.strategie.deplacement.DeplacementSatellite;
import simulation.Simulation;

public class CommandeSatelliteCreation implements ICommande<Satellite>
{

  Simulation simulation;

  private final Integer vitesse = 1;
  private final Point position = new Point(100, 10);
  private final Deplacement deplacement = new DeplacementSatellite(-10, 1000);

  public CommandeSatelliteCreation(Simulation simulation)
  {
    this.simulation = simulation;
  }

  @Override
  public Satellite execute()
  {
    Satellite satellite = new Satellite(this.vitesse);
    satellite.setPosition(this.position);
    satellite.setDeplacement(this.deplacement);
    this.simulation.addSatelitte(satellite);

    System.out.println(satellite);

    return satellite;
  }

}
