package visiting;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import command.CommandeBaliseCreation;
import command.CommandeElementMobileStartStop;
import command.CommandeModification;
import command.CommandeSatelliteCreation;
import command.ICommande;
import generated.SatelliteBaliseBaseVisitor;
import generated.SatelliteBaliseParser.ArgContext;
import generated.SatelliteBaliseParser.ArgsContext;
import generated.SatelliteBaliseParser.AssignContext;
import generated.SatelliteBaliseParser.CallContext;
import generated.SatelliteBaliseParser.CreateContext;
import model.ElementMobile;
import simulation.Simulation;

public class SatelliteBaliseVisitor extends SatelliteBaliseBaseVisitor<ParserRuleContext>
{

  /**
   * Map de toutes les commandes possibles pour la création :<br>
   * <br>
   * <b>balise :</b> Création d'une balise.<br>
   * <b>satellite :</b> Création d'un satellite.
   */
  private static final Map<String, Function<Simulation, ICommande<? extends ElementMobile>>> CREATION_COMMANDES = Map
      .of("balise", CommandeBaliseCreation::new, "satellite", CommandeSatelliteCreation::new);

  /**
   * Map de toutes les commandes possibles pour les appels de fonctions :<br>
   * <br>
   * <b>set :</b> Modifie les propriétés de l'ElementMobile.<br>
   * <b>start :</b> Démarre l'élement mobile.<br>
   * <b>stop :</b> Stop l'élément mobile.<br>
   */
  private static final Map<String, BiFunction<ElementMobile, Map<String, String>, ICommande<? extends ElementMobile>>> CALL_COMMANDES = Map
      .of("set", CommandeModification::new, "start", (e, arg) -> new CommandeElementMobileStartStop(e, true), "stop",
          (e, arg) -> new CommandeElementMobileStartStop(e, false));

  private Simulation simulation;

  private Map<String, ElementMobile> variables = new HashMap<>();

  private ParseTreeProperty<Object> values = new ParseTreeProperty<>();

  public SatelliteBaliseVisitor(Simulation simulation)
  {
    super();
    this.simulation = simulation;
  }

  @Override
  public ParserRuleContext visitCall(CallContext ctx)
  {
    ParserRuleContext parserRuleContext = super.visitCall(ctx);

    String variableName = ctx.ID().get(0).getText();
    String commandeName = ctx.ID().get(1).getText();
    Map<String, String> arguments = (Map<String, String>) values.get(ctx.args());

    if (this.variables.containsKey(variableName))
    {
      ElementMobile elementMobile = this.variables.get(variableName);
      var commande = CALL_COMMANDES.get(commandeName).apply(elementMobile, arguments);
      commande.execute();
    }
    else
    {
      System.err.println(variableName + " is not a known variable");
    }

    return parserRuleContext;
  }

  @Override
  public ParserRuleContext visitAssign(AssignContext ctx)
  {
    ParserRuleContext parserRuleContext = super.visitAssign(ctx);

    String variableName = ctx.ID().getText();
    ElementMobile elementMobile = (ElementMobile) this.values.get(ctx.create());

    this.variables.put(variableName, elementMobile);
    return parserRuleContext;
  }

  @Override
  public ParserRuleContext visitCreate(CreateContext ctx)
  {
    ParserRuleContext parserRuleContext = super.visitCreate(ctx);

    String creationName = ctx.ID().getText();

    if (CREATION_COMMANDES.containsKey(creationName))
    {
      var commande = CREATION_COMMANDES.get(creationName).apply(this.simulation);
      this.values.put(ctx, commande.execute());
    }
    else
    {
      System.err.println(creationName + " is not a known object");
    }

    return parserRuleContext;
  }

  @Override
  public ParserRuleContext visitArgs(ArgsContext ctx)
  {
    ParserRuleContext parserRuleContext = super.visitArgs(ctx);

    HashMap<String, String> arguments = new HashMap<>();
    var itor = ctx.arg().iterator();

    while (itor.hasNext())
    {
      arguments.putAll((Map<String, String>) this.values.get(itor.next()));
    }
    this.values.put(ctx, arguments);

    return parserRuleContext;
  }

  @Override
  public ParserRuleContext visitArg(ArgContext ctx)
  {
    ParserRuleContext parserRuleContext = super.visitArg(ctx);

    String argumentName = ctx.ID().getText();
    String argumentValue = ctx.VALUE().getText();

    Map<String, String> argument = Collections.singletonMap(argumentName, argumentValue);
    this.values.put(ctx, argument);

    return parserRuleContext;
  }
}
