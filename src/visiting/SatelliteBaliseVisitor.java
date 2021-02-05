package visiting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import command.ICommande;
import generated.SatelliteBaliseBaseVisitor;
import generated.SatelliteBaliseParser.ArgContext;
import generated.SatelliteBaliseParser.ArgsContext;
import generated.SatelliteBaliseParser.AssignContext;
import generated.SatelliteBaliseParser.CallContext;
import generated.SatelliteBaliseParser.CommandContext;
import generated.SatelliteBaliseParser.ScriptContext;
import generated.SatelliteBaliseParser.ValueContext;
import model.Script;

public class SatelliteBaliseVisitor extends SatelliteBaliseBaseVisitor<ParserRuleContext>
{

  ParseTreeProperty<Object> values = new ParseTreeProperty<Object>();

  public Object resultFor(ParserRuleContext ctx)
  {
    return values.get(ctx);
  }

  @Override
  public ParserRuleContext visitScript(ScriptContext ctx)
  {
    ParserRuleContext parserRuleContext = super.visitScript(ctx);

    List<ICommande> commandes = new ArrayList<>();
    Iterator<CommandContext> itor = ctx.command().iterator();
    while (itor.hasNext())
    {
      commandes.add((ICommande) this.values.get(itor.next()));
    }

    Script script = new Script();
    script.addAllCommands(commandes);
    this.values.put(ctx, script);

    return parserRuleContext;
  }

  @Override
  public ParserRuleContext visitCommand(CommandContext ctx)
  {
    ParserRuleContext parserRuleContext = super.visitCommand(ctx);
    values.put(ctx, values.get(ctx.getChild(0)));
    return parserRuleContext;
  }

  @Override
  public ParserRuleContext visitCall(CallContext ctx)
  {
    ParserRuleContext parserRuleContext = super.visitCall(ctx);

    String variable = ctx.ID().get(0).getText();
    String action = ctx.ID().get(1).getText();
    Map<String, String> arguments = (Map<String, String>) values.get(ctx.args());

    // Créer une Commande, mais comment vérifier l'action. Switch case ?

    switch (action)
    {
      case "start":
        System.out.println("Créer commande START");
        break;

      case "stop":
        System.out.println("Créer commande START");
        break;

      case "modify":
        System.out.println("Créer commande MODIFY");
        System.out.println("Arguments : " + arguments.toString());
        break;

      default:
        System.out.println("Throw UNDIFINED COMMAND");
        break;
    }
    return parserRuleContext;
  }

  @Override
  public ParserRuleContext visitAssign(AssignContext ctx)
  {
    return visitChildren(ctx);
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

  @Override
  public ParserRuleContext visitValue(ValueContext ctx)
  {
    return visitChildren(ctx);
  }
}
