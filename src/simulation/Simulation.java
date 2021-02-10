package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import generated.SatelliteBaliseLexer;
import generated.SatelliteBaliseParser;
import graphiclayer.GRect;
import graphiclayer.GSpace;
import model.Satellite;
import model.balise.Balise;
import model.strategie.deplacement.DeplacementSatellite;
import simulation.graphic.GrBalise;
import simulation.graphic.GrSatelitte;
import visiting.SatelliteBaliseVisitor;

public class Simulation
{
  Manager manager;
  GSpace world;
  GRect sky;
  GRect sea;

  public Simulation()
  {
    this.manager = new Manager();
    this.initWorld();
  }

  public void mainLoop()
  {
    while (true)
    {
      manager.tick();
      try
      {
        Thread.sleep(50);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  public void addBalise(Balise balise)
  {
    this.manager.addBalise(balise);

    GrBalise grbal = new GrBalise();
    grbal.setModel(balise);
    this.sea.addElement(grbal);
  }

  public void addSatelitte(Point startPos, int vitesse)
  {
    Satellite sat = new Satellite(vitesse);
    sat.setPosition(startPos);
    sat.setDeplacement(new DeplacementSatellite(-10, 1000));
    manager.addSatellite(sat);
    GrSatelitte grSat = new GrSatelitte();
    grSat.setModel(sat);
    this.sky.addElement(grSat);
  }

  public void launch()
  {
//    this.addSatelitte(new Point(10, 50), 2);
//    this.addSatelitte(new Point(100, 10), 1);
//    this.addSatelitte(new Point(400, 90), 3);
//    this.addSatelitte(new Point(500, 140), 4);
//    this.addSatelitte(new Point(600, 10), 1);
//    this.addBalise(new Memoire(100), new Point(400, 200), new DeplacementHorizontal(50, 750), 5);
//    this.addBalise(new Memoire(400), new Point(100, 100), new DeplacementVertical(50, 200), 5);
//    this.addBalise(new Memoire(200), new Point(0, 160), new DeplacementHorizontal(0, 800), 5);
//    this.addBalise(new Memoire(500), new Point(200, 100), new DeplacementVertical(130, 270), 5);
//    this.addBalise(new Memoire(150), new Point(300, 100), new DeplacementHorizontal(200, 600), 5);

    InputStream is = System.in;
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    try
    {
      String input = br.readLine();

      SatelliteBaliseParser parser = parserFor(input);
      ParseTree tree = parser.script();
      SatelliteBaliseVisitor mat = new SatelliteBaliseVisitor(this);
      mat.visit(tree);
      this.world.open();
      this.mainLoop();
//      Script script = (Script) mat.resultFor((ParserRuleContext) tree);
//
//      Iterator<ICommande> itor = script.scriptIterator();
//      while (itor.hasNext())
//      {
//        itor.next().execute();
//      }
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } // get first expression

//    while (input != null)
//    { // while we have more expressions
//      CharStream stream = CharStreams.fromString(input);
//      Hello1Lexer lexer = new Hello1Lexer(stream);
//      lexer.setLine(line); // notify lexer of input position
//      lexer.setCharPositionInLine(0);
//      TokenStream tokens = new CommonTokenStream(lexer);
//      parser.setInputStream(tokens);
//      Hello1Parser.MeetingContext result = parser.meeting();
//      ParseTree tree = result;
//      visitor.visit(tree);
//
//      input = br.readLine(); // see if there's another line
//      line++;
//    }

//    ICommande createBalise = new Commande_createBalise(this);
//    createBalise.execute();
//
//    this.world.open();
//    this.mainLoop();
  }

  private void initWorld()
  {
    this.world = new GSpace("Satellite & Balises", new Dimension(800, 600));

    this.sky = new GRect();
    this.sky.setColor(Color.WHITE);
    this.sky.setDimension(new Dimension(800, 300));
    this.world.addElement(this.sky);

    this.sea = new GRect();
    this.sea.setColor(Color.blue);
    this.sea.setDimension(new Dimension(800, 300));
    this.sea.setPosition(new Point(0, 300));
    this.world.addElement(this.sea);
  }

  protected static SatelliteBaliseParser parserFor(String input)
  {
    CharStream stream = CharStreams.fromString(input);
    TokenStream tokens = new CommonTokenStream(new SatelliteBaliseLexer(stream));
    SatelliteBaliseParser parser = new SatelliteBaliseParser(tokens);
    return parser;
  }

//  public static String prettyPrinted(MMEntity entity)
//  {
//    PrettyPrinter pp = new PrettyPrinter();
//    entity.accept(pp);
//    return pp.result();
//  }

  public static void main(String[] args)
  {
    new Simulation().launch();
  }

}
