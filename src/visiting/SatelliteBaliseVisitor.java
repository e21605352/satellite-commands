package visiting;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import command.CommandeBaliseCreation;
import command.ICommande;
import generated.SatelliteBaliseBaseVisitor;
import generated.SatelliteBaliseParser.ArgContext;
import generated.SatelliteBaliseParser.ArgsContext;
import generated.SatelliteBaliseParser.AssignContext;
import generated.SatelliteBaliseParser.CallContext;
import generated.SatelliteBaliseParser.CommandContext;
import generated.SatelliteBaliseParser.CreateContext;
import generated.SatelliteBaliseParser.ScriptContext;
import model.ElementMobile;
import simulation.Simulation;

public class SatelliteBaliseVisitor extends SatelliteBaliseBaseVisitor<ParserRuleContext>
{

  private Simulation simulation;
//  private final Map<String, Function<Map<String, String>, ICommande>> call_commandes = Map
//      .of("start", args -> new StartCommande(), "stop", args -> new StopCommande(), "modify", ModifyCommande::new);

  private final Map<String, BiFunction<Simulation, Map<String, String>, ICommande<? extends ElementMobile>>> creationCommandes = Map
      .of("balise", CommandeBaliseCreation::new);

  private ParseTreeProperty<Object> values = new ParseTreeProperty<>();

  private Map<String, ElementMobile> variables = new HashMap<>();

  public SatelliteBaliseVisitor(Simulation simulation)
  {
    super();
    this.simulation = simulation;
  }

  @Override
  public ParserRuleContext visitScript(ScriptContext ctx)
  {
//    ParserRuleContext parserRuleContext = super.visitScript(ctx);
//
//    List<ICommande> commandes = new ArrayList<>();
//    Iterator<CommandContext> itor = ctx.command().iterator();
//    while (itor.hasNext())
//    {
//      commandes.add((ICommande) this.values.get(itor.next()));
//    }
//
//    Script script = new Script();
//    script.addAllCommands(commandes);
//    this.values.put(ctx, script);
//
//    return parserRuleContext;
    return visitChildren(ctx);
  }

  @Override
  public ParserRuleContext visitCommand(CommandContext ctx)
  {
//    ParserRuleContext parserRuleContext = super.visitCommand(ctx);
//    values.put(ctx, values.get(ctx.getChild(0)));
//    return parserRuleContext;
    return visitChildren(ctx);
  }

  @Override
  public ParserRuleContext visitCall(CallContext ctx)
  {
//    ParserRuleContext parserRuleContext = super.visitCall(ctx);
//
//    String variableName = ctx.ID().get(0).getText();
//    String commandeName = ctx.ID().get(1).getText();
//    Map<String, String> arguments = (Map<String, String>) values.get(ctx.args());
//
//    ICommande commande = call_commandes.get(commandeName).apply(arguments);
//    this.values.put(ctx, commande);
//
//    return parserRuleContext;
    return visitChildren(ctx);
  }

  @Override
  public ParserRuleContext visitAssign(AssignContext ctx)
  {
    ParserRuleContext parserRuleContext = super.visitAssign(ctx);

    String variableName = ctx.ID().getText();
    ElementMobile elementMobile = (ElementMobile) this.values.get(ctx.getChild(1));

    this.variables.put(variableName, elementMobile);

    return parserRuleContext;
  }

  @Override
  public ParserRuleContext visitCreate(CreateContext ctx)
  {
    ParserRuleContext parserRuleContext = super.visitCreate(ctx);

    String creationName = ctx.ID().getText();
    System.out.println(creationName);
    Map<String, String> arguments = (Map<String, String>) values.get(ctx.args());

    ICommande<? extends ElementMobile> commande = this.creationCommandes.get(creationName).apply(this.simulation,
                                                                                                 arguments);
    this.values.put(ctx, commande.execute());

    return parserRuleContext;
  }

  @Override
  public ParserRuleContext visitArgs(ArgsContext ctx)
  {
    return visitChildren(ctx);
  }

  @Override
  public ParserRuleContext visitArg(ArgContext ctx)
  {
    return visitChildren(ctx);
  }
}
