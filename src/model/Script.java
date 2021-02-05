package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import command.ICommande;

public class Script
{

  private ArrayList<ICommande> commandes;

  public Script()
  {
    this.commandes = new ArrayList<>();
  }

  public void addCommand(ICommande commande)
  {
    this.commandes.add(commande);
  }

  public void addAllCommands(List<ICommande> commandes)
  {
    this.commandes.addAll(commandes);
  }

  public Iterator<ICommande> daysIterator()
  {
    return this.commandes.iterator();
  }
}
