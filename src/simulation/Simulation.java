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

import generated.SatelliteBaliseLexer;
import generated.SatelliteBaliseParser;
import graphiclayer.GRect;
import graphiclayer.GSpace;
import model.Satellite;
import model.balise.Balise;
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

  public void addSatelitte(Satellite satellite)
  {
    this.manager.addSatellite(satellite);

    GrSatelitte grSat = new GrSatelitte();
    grSat.setModel(satellite);
    this.sky.addElement(grSat);
  }

  public void launch() throws IOException
  {
    InputStream is = System.in;
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String input = br.readLine();
    int line = 1;

    SatelliteBaliseParser parser = new SatelliteBaliseParser(null);
    SatelliteBaliseVisitor visitor = new SatelliteBaliseVisitor(this);

    while (input != null)
    {
      CharStream stream = CharStreams.fromString(input);
      SatelliteBaliseLexer lexer = new SatelliteBaliseLexer(stream);

      lexer.setLine(line);
      lexer.setCharPositionInLine(0);

      TokenStream tokens = new CommonTokenStream(lexer);
      parser.setInputStream(tokens);

      SatelliteBaliseParser.ScriptContext result = parser.script();
      visitor.visit(result);

      input = br.readLine();
      line++;
    }

    this.world.open();
    this.mainLoop();
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

  public static void main(String[] args) throws IOException
  {
    new Simulation().launch();
  }
}
