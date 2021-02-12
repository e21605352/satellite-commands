package command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.BiConsumer;

import model.ElementMobile;
import model.Memoire;
import model.balise.Balise;

public class CommandeModification implements ICommande<ElementMobile>
{

  private static final Map<String, BiConsumer<ElementMobile, String>> COMMANDES = Map
      .of("vitesse", new CommandeModification.Setter(ElementMobile.class, "setVitesse"), "posX",
          new CommandeModification.Setter(ElementMobile.class, "setPositionX"), "posY",
          new CommandeModification.Setter(ElementMobile.class, "setPositionY"), "memoire", (elem, arg) -> {
            Balise balise = (Balise) elem;
            balise.setMemoire(new Memoire(Integer.parseInt(arg)));
          });

  ElementMobile elementMobile;
  Map<String, String> arguments;

  public CommandeModification(ElementMobile elementMobile, Map<String, String> arguments)
  {
    this.elementMobile = elementMobile;
    this.arguments = arguments;
  }

  @Override
  public ElementMobile execute()
  {
    this.arguments.forEach((k, v) -> COMMANDES.get(k).accept(this.elementMobile, v));
    return this.elementMobile;
  }

  private static class Setter implements BiConsumer<ElementMobile, String>
  {
    Method method;

    public Setter(final Class<?> cl, final String method)
    {
      try
      {
        this.method = cl.getMethod(method, Integer.class);
      }
      catch (SecurityException | NoSuchMethodException e)
      {
        e.printStackTrace();
      }
    }

    @Override
    public void accept(final ElementMobile e, final String s)
    {
      try
      {
        this.method.invoke(e, Integer.parseInt(s));
      }
      catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1)
      {
        System.err.println(s + " : Bad argument type, expected Integer");
      }
    }

  }
}
