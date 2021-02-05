// Generated from ../language/SatelliteBalise.g4 by ANTLR 4.9.1
package generated;


import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SatelliteBaliseParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SatelliteBaliseVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SatelliteBaliseParser#script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScript(SatelliteBaliseParser.ScriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBaliseParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(SatelliteBaliseParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBaliseParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(SatelliteBaliseParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBaliseParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(SatelliteBaliseParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBaliseParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(SatelliteBaliseParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBaliseParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(SatelliteBaliseParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBaliseParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(SatelliteBaliseParser.ValueContext ctx);
}