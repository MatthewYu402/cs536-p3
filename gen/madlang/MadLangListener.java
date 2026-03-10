// Generated from src/madlang/MadLang.g4 by ANTLR 4.13.2
package madlang;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MadLangParser}.
 */
public interface MadLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MadLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MadLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MadLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MadLangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MadLangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(MadLangParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(MadLangParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#globalVarDecl}.
	 * @param ctx the parse tree
	 */
	void enterGlobalVarDecl(MadLangParser.GlobalVarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#globalVarDecl}.
	 * @param ctx the parse tree
	 */
	void exitGlobalVarDecl(MadLangParser.GlobalVarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#funDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunDecl(MadLangParser.FunDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#funDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunDecl(MadLangParser.FunDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(MadLangParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(MadLangParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(MadLangParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(MadLangParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(MadLangParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(MadLangParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MadLangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MadLangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(MadLangParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(MadLangParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#funDef}.
	 * @param ctx the parse tree
	 */
	void enterFunDef(MadLangParser.FunDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#funDef}.
	 * @param ctx the parse tree
	 */
	void exitFunDef(MadLangParser.FunDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(MadLangParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(MadLangParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MadLangParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MadLangParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(MadLangParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(MadLangParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(MadLangParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(MadLangParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(MadLangParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(MadLangParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(MadLangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(MadLangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(MadLangParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(MadLangParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(MadLangParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(MadLangParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#eqExpr}.
	 * @param ctx the parse tree
	 */
	void enterEqExpr(MadLangParser.EqExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#eqExpr}.
	 * @param ctx the parse tree
	 */
	void exitEqExpr(MadLangParser.EqExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelExpr(MadLangParser.RelExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelExpr(MadLangParser.RelExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(MadLangParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(MadLangParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#mulExpr}.
	 * @param ctx the parse tree
	 */
	void enterMulExpr(MadLangParser.MulExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#mulExpr}.
	 * @param ctx the parse tree
	 */
	void exitMulExpr(MadLangParser.MulExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(MadLangParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(MadLangParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(MadLangParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(MadLangParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MadLangParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(MadLangParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MadLangParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(MadLangParser.ArgsContext ctx);
}