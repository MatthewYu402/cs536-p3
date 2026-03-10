package madlang;

import java.util.ArrayList;
import java.util.List;

public class ASTBuilder extends MadLangBaseVisitor<Object> {

    // ── Program ──────────────────────────────────────────────────────────────
    @Override
    public List<Stmt> visitProgram(MadLangParser.ProgramContext ctx) {
        List<Stmt> stmts = new ArrayList<>();
        for (MadLangParser.DeclContext decl : ctx.decl()) {
            stmts.add((Stmt) visit(decl));
        }
        return stmts;
    }

    // ── Declarations ─────────────────────────────────────────────────────────
    @Override
    public Stmt visitDecl(MadLangParser.DeclContext ctx) {
        return (Stmt) visit(ctx.getChild(0));
    }

    @Override
    public Stmt.Function visitFunDecl(MadLangParser.FunDeclContext ctx) {
        String name = ctx.IDENT().getText();
        VarType returnType = (VarType) visit(ctx.type());
        List<Stmt.Parameter> params = ctx.params() != null
                ? (List<Stmt.Parameter>) visit(ctx.params())
                : new ArrayList<>();
        Stmt.Block block = (Stmt.Block) visit(ctx.block());
        List<Stmt> body = block.statements;
        return new Stmt.Function(name, returnType, params, body);
    }

    @Override
    public Stmt.Var visitGlobalVarDecl(MadLangParser.GlobalVarDeclContext ctx) {
        String name = ctx.IDENT().getText();
        VarType type = (VarType) visit(ctx.type());
        Expr initializer = ctx.expr() != null ? (Expr) visit(ctx.expr()) : null;
        return new Stmt.Var(name, type, initializer);
    }

    // ── Type ─────────────────────────────────────────────────────────────────
    @Override
    public VarType visitType(MadLangParser.TypeContext ctx) {
        if (ctx.INT() != null) {
            return VarType.INT;
        } else {
            return VarType.BOOL;
        }
    }

    // ── Parameters ───────────────────────────────────────────────────────────
    @Override
    public List<Stmt.Parameter> visitParams(MadLangParser.ParamsContext ctx) {
        List<Stmt.Parameter> params = new ArrayList<>();
        for (MadLangParser.ParamContext param : ctx.param()) {
            params.add((Stmt.Parameter) visit(param));
        }
        return params;
    }

    @Override
    public Stmt.Parameter visitParam(MadLangParser.ParamContext ctx) {
        String name = ctx.IDENT().getText();
        VarType type = (VarType) visit(ctx.type());
        return new Stmt.Parameter(name, type);
    }

    // ── Statements ───────────────────────────────────────────────────────────
    @Override
    public Stmt visitStmt(MadLangParser.StmtContext ctx) {
        return (Stmt) visit(ctx.getChild(0));
    }

    @Override
    public Stmt.Block visitBlock(MadLangParser.BlockContext ctx) {
        List<Stmt> statements = new ArrayList<>();
        for (MadLangParser.StmtContext stmt : ctx.stmt()) {
            statements.add((Stmt) visit(stmt));
        }
        return new Stmt.Block(statements);
    }

    @Override
    public Stmt.Var visitVarDef(MadLangParser.VarDefContext ctx) {
        String name = ctx.IDENT().getText();
        VarType type = (VarType) visit(ctx.type());
        Expr initializer = ctx.expr() != null ? (Expr) visit(ctx.expr()) : null;
        return new Stmt.Var(name, type, initializer);
    }

    @Override
    public Stmt visitFunDef(MadLangParser.FunDefContext ctx) {
        return (Stmt) visit(ctx.funDecl());
    }

    @Override
    public Stmt.Assign visitAssignStmt(MadLangParser.AssignStmtContext ctx) {
        String name = ctx.IDENT().getText();
        Expr value = (Expr) visit(ctx.expr());
        return new Stmt.Assign(name, value);
    }

    @Override
    public Stmt.If visitIfStmt(MadLangParser.IfStmtContext ctx) {
        Expr condition = (Expr) visit(ctx.expr());
        Stmt thenBranch = (Stmt) visit(ctx.stmt(0));
        Stmt elseBranch = ctx.stmt().size() > 1 ? (Stmt) visit(ctx.stmt(1)) : null;
        return new Stmt.If(condition, thenBranch, elseBranch);
    }

    @Override
    public Stmt.While visitWhileStmt(MadLangParser.WhileStmtContext ctx) {
        Expr condition = (Expr) visit(ctx.expr());
        Stmt body = (Stmt) visit(ctx.stmt());
        return new Stmt.While(condition, body);
    }

    @Override
    public Stmt.Return visitReturnStmt(MadLangParser.ReturnStmtContext ctx) {
        Expr value = (Expr) visit(ctx.expr());
        return new Stmt.Return(value);
    }

    @Override
    public Stmt.Expression visitExprStmt(MadLangParser.ExprStmtContext ctx) {
        Expr expr = (Expr) visit(ctx.expr());
        return new Stmt.Expression(expr);
    }

    // ── Expressions ──────────────────────────────────────────────────────────
    @Override
    public Expr visitExpr(MadLangParser.ExprContext ctx) {
        return (Expr) visit(ctx.orExpr());
    }

    @Override
    public Expr visitOrExpr(MadLangParser.OrExprContext ctx) {
        Expr left = (Expr) visit(ctx.andExpr(0));
        for (int i = 1; i < ctx.andExpr().size(); i++) {
            Expr right = (Expr) visit(ctx.andExpr(i));
            left = new Expr.Binary(left, Operator.OR, right);
        }
        return left;
    }

    @Override
    public Expr visitAndExpr(MadLangParser.AndExprContext ctx) {
        Expr left = (Expr) visit(ctx.eqExpr(0));
        for (int i = 1; i < ctx.eqExpr().size(); i++) {
            Expr right = (Expr) visit(ctx.eqExpr(i));
            left = new Expr.Binary(left, Operator.AND, right);
        }
        return left;
    }

    @Override
    public Expr visitEqExpr(MadLangParser.EqExprContext ctx) {
        Expr left = (Expr) visit(ctx.relExpr(0));
        for (int i = 1; i < ctx.relExpr().size(); i++) {
            // Operators are at child positions 1, 3, 5, ...  (interleaved with operands)
            String op = ctx.getChild(2 * i - 1).getText();
            Expr right = (Expr) visit(ctx.relExpr(i));
            left = new Expr.Binary(left, op.equals("==") ? Operator.EQUAL : Operator.NOT_EQUAL, right);
        }
        return left;
    }

    @Override
    public Expr visitRelExpr(MadLangParser.RelExprContext ctx) {
        Expr left = (Expr) visit(ctx.addExpr(0));
        for (int i = 1; i < ctx.addExpr().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            Expr right = (Expr) visit(ctx.addExpr(i));
            left = new Expr.Binary(left, toRelOperator(op), right);
        }
        return left;
    }

    @Override
    public Expr visitAddExpr(MadLangParser.AddExprContext ctx) {
        Expr left = (Expr) visit(ctx.mulExpr(0));
        for (int i = 1; i < ctx.mulExpr().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            Expr right = (Expr) visit(ctx.mulExpr(i));
            left = new Expr.Binary(left, op.equals("+") ? Operator.PLUS : Operator.MINUS, right);
        }
        return left;
    }

    @Override
    public Expr visitMulExpr(MadLangParser.MulExprContext ctx) {
        Expr left = (Expr) visit(ctx.unaryExpr(0));
        for (int i = 1; i < ctx.unaryExpr().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            Expr right = (Expr) visit(ctx.unaryExpr(i));
            left = new Expr.Binary(left, toMulOperator(op), right);
        }
        return left;
    }

    @Override
    public Expr visitUnaryExpr(MadLangParser.UnaryExprContext ctx) {
        if (ctx.primary() != null) {
            // No unary operator — pass through to primary
            return (Expr) visit(ctx.primary());
        }
        Operator op = ctx.MINUS() != null ? Operator.MINUS : Operator.NOT;
        Expr operand = (Expr) visit(ctx.unaryExpr());
        return new Expr.Unary(op, operand);
    }

    @Override
    public Expr visitPrimary(MadLangParser.PrimaryContext ctx) {
        // INT_LIT
        if (ctx.INT_LIT() != null) {
            return new Expr.Literal(Integer.parseInt(ctx.INT_LIT().getText()));
        }
        // true / false
        if (ctx.TRUE() != null) {
            return new Expr.Literal(true);
        }
        if (ctx.FALSE() != null) {
            return new Expr.Literal(false);
        }
        // Parenthesised expression
        if (ctx.expr() != null) {
            return (Expr) visit(ctx.expr());
        }
        // IDENT — either a variable or a function call
        if (ctx.IDENT() != null) {
            String name = ctx.IDENT().getText();
            if (ctx.LEFT_PAREN() != null) {
                // Function call: IDENT ( args? )
                List<Expr> args = new ArrayList<>();
                if (ctx.args() != null) {
                    for (MadLangParser.ExprContext arg : ctx.args().expr()) {
                        args.add((Expr) visit(arg));
                    }
                }
                return new Expr.Call(name, args);
            } else {
                // Plain variable reference
                return new Expr.Variable(name);
            }
        }
        throw new IllegalStateException("Unhandled primary: " + ctx.getText());
    }

    // ── Operator helpers ─────────────────────────────────────────────────────
    private Operator toRelOperator(String sym) {
        switch (sym) {
            case "<":
                return Operator.LESS;
            case "<=":
                return Operator.LESS_EQUAL;
            case ">":
                return Operator.GREATER;
            case ">=":
                return Operator.GREATER_EQUAL;
            default:
                throw new IllegalArgumentException("Unknown relational operator: " + sym);
        }
    }

    private Operator toMulOperator(String sym) {
        switch (sym) {
            case "*":
                return Operator.MULTIPLY;
            case "/":
                return Operator.DIVIDE;
            case "%":
                return Operator.MODULO;
            default:
                throw new IllegalArgumentException("Unknown multiplicative operator: " + sym);
        }
    }
}
