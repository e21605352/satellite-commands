// Generated from ../language/SatelliteBalise.g4 by ANTLR 4.9.1
package generated;


import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SatelliteBaliseParser}.
 */
public interface SatelliteBaliseListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SatelliteBaliseParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(SatelliteBaliseParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBaliseParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(SatelliteBaliseParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBaliseParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(SatelliteBaliseParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBaliseParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(SatelliteBaliseParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBaliseParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(SatelliteBaliseParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBaliseParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(SatelliteBaliseParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBaliseParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(SatelliteBaliseParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBaliseParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(SatelliteBaliseParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBaliseParser#create}.
	 * @param ctx the parse tree
	 */
	void enterCreate(SatelliteBaliseParser.CreateContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBaliseParser#create}.
	 * @param ctx the parse tree
	 */
	void exitCreate(SatelliteBaliseParser.CreateContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBaliseParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(SatelliteBaliseParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBaliseParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(SatelliteBaliseParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBaliseParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(SatelliteBaliseParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBaliseParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(SatelliteBaliseParser.ArgContext ctx);
}