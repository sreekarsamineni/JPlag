package de.jplag.kotlin;

import static de.jplag.kotlin.KotlinTokenConstants.*;

import java.util.Optional;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.jplag.kotlin.grammar.KotlinParser;
import de.jplag.kotlin.grammar.KotlinParserBaseListener;

public class KotlinListener extends KotlinParserBaseListener {
    private final KotlinParserAdapter parserAdapter;

    public KotlinListener(KotlinParserAdapter parserAdapter) {
        this.parserAdapter = parserAdapter;
    }

    /**
     * Passes a token of the given tokenType to the parserAdapter, representing the grammar's token given by token.
     * @param tokenType the custom token type that occurred.
     * @param token the corresponding grammar's token
     */
    private void transformToken(int tokenType, Token token) {
        parserAdapter.addToken(tokenType, token.getLine(), token.getCharPositionInLine() + 1, token.getText().length());
    }

    /**
     * Passes a token of the given tokenType to the parserAdapter, representing the current grammatical context given by
     * start and end.
     * @param tokenType the custom token type that occurred.
     * @param start the first Token of the context
     * @param end the last Token of the context
     */
    private void transformToken(int tokenType, Token start, Token end) {
        parserAdapter.addToken(tokenType, start.getLine(), start.getCharPositionInLine(), end.getStopIndex() - start.getStartIndex() + 1);
    }

    @Override
    public void enterPackageHeader(KotlinParser.PackageHeaderContext context) {
        transformToken(PACKAGE, context.getStart(), context.getStop());
        super.enterPackageHeader(context);
    }

    @Override
    public void enterImportHeader(KotlinParser.ImportHeaderContext context) {
        transformToken(IMPORT, context.getStart(), context.getStop());
        super.enterImportHeader(context);
    }

    @Override
    public void enterClassDeclaration(KotlinParser.ClassDeclarationContext context) {
        transformToken(CLASS_DECLARATION, context.getStart(), context.getStop());
        super.enterClassDeclaration(context);
    }

    @Override
    public void enterObjectDeclaration(KotlinParser.ObjectDeclarationContext context) {
        transformToken(OBJECT_DECLARATION, context.getStart(), context.getStop());
        super.enterObjectDeclaration(context);
    }

    @Override
    public void enterCompanionObject(KotlinParser.CompanionObjectContext context) {
        transformToken(COMPANION_DECLARATION, context.getStart(), context.getStop());
        super.enterCompanionObject(context);
    }

    @Override
    public void enterTypeParameter(KotlinParser.TypeParameterContext context) {
        transformToken(TYPE_PARAMETER, context.getStart(), context.getStop());
        super.enterTypeParameter(context);
    }

    @Override
    public void enterPrimaryConstructor(KotlinParser.PrimaryConstructorContext context) {
        transformToken(CONSTRUCTOR, context.getStart(), context.getStop());
        super.enterPrimaryConstructor(context);
    }

    @Override
    public void enterClassParameter(KotlinParser.ClassParameterContext context) {
        transformToken(PROPERTY_DECLARATION, context.getStart(), context.getStop());
        super.enterClassParameter(context);
    }

    @Override
    public void enterClassBody(KotlinParser.ClassBodyContext context) {
        transformToken(CLASS_BODY_BEGIN, context.getStart());
        super.enterClassBody(context);
    }

    @Override
    public void exitClassBody(KotlinParser.ClassBodyContext context) {
        transformToken(CLASS_BODY_END, context.getStop());
        super.exitClassBody(context);
    }

    @Override
    public void enterEnumClassBody(KotlinParser.EnumClassBodyContext context) {
        transformToken(ENUM_CLASS_BODY_BEGIN, context.getStart());
        super.enterEnumClassBody(context);
    }

    @Override
    public void exitEnumClassBody(KotlinParser.EnumClassBodyContext context) {
        transformToken(ENUM_CLASS_BODY_END, context.getStop());
        super.exitEnumClassBody(context);
    }

    @Override
    public void enterEnumEntry(KotlinParser.EnumEntryContext context) {
        transformToken(ENUM_ENTRY, context.getStart());
        super.enterEnumEntry(context);
    }

    @Override
    public void enterSecondaryConstructor(KotlinParser.SecondaryConstructorContext context) {
        transformToken(CONSTRUCTOR, context.getStart(), context.getStop());
        super.enterSecondaryConstructor(context);
    }

    @Override
    public void enterPropertyDeclaration(KotlinParser.PropertyDeclarationContext context) {
        transformToken(PROPERTY_DECLARATION, context.getStart(), context.getStop());
        super.enterPropertyDeclaration(context);
    }

    @Override
    public void enterAnonymousInitializer(KotlinParser.AnonymousInitializerContext context) {
        transformToken(INITIALIZER, context.getStart());
        super.enterAnonymousInitializer(context);
    }

    @Override
    public void enterFunctionDeclaration(KotlinParser.FunctionDeclarationContext context) {
        transformToken(FUNCTION, context.getStart());
        super.enterFunctionDeclaration(context);
    }

    @Override
    public void enterGetter(KotlinParser.GetterContext context) {
        transformToken(GETTER, context.getStart());
        super.enterGetter(context);
    }

    @Override
    public void enterSetter(KotlinParser.SetterContext context) {
        transformToken(SETTER, context.getStart());
        super.enterSetter(context);
    }

    @Override
    public void enterFunctionValueParameter(KotlinParser.FunctionValueParameterContext context) {
        transformToken(FUNCTION_PARAMETER, context.getStart(), context.getStop());
        super.enterFunctionValueParameter(context);
    }

    @Override
    public void enterFunctionBody(KotlinParser.FunctionBodyContext context) {
        transformToken(FUNCTION_BODY_BEGIN, context.getStart());
        super.enterFunctionBody(context);
    }

    @Override
    public void exitFunctionBody(KotlinParser.FunctionBodyContext context) {
        transformToken(FUNCTION_BODY_END, context.getStop());
        super.exitFunctionBody(context);
    }

    @Override
    public void enterFunctionLiteral(KotlinParser.FunctionLiteralContext context) {
        transformToken(FUNCTION_LITERAL_BEGIN, context.getStart());
        super.enterFunctionLiteral(context);
    }

    @Override
    public void exitFunctionLiteral(KotlinParser.FunctionLiteralContext context) {
        transformToken(FUNCTION_LITERAL_END, context.getStop());
        super.exitFunctionLiteral(context);
    }

    @Override
    public void enterBlock(KotlinParser.BlockContext context) {
        transformToken(BLOCK_BEGIN, context.getStart());
        super.enterBlock(context);
    }

    @Override
    public void exitBlock(KotlinParser.BlockContext context) {
        transformToken(BLOCK_END, context.getStop());
        super.exitBlock(context);
    }

    @Override
    public void enterForExpression(KotlinParser.ForExpressionContext context) {
        transformToken(FOR_EXPRESSION_BEGIN, context.getStart());
        super.enterForExpression(context);
    }

    @Override
    public void exitForExpression(KotlinParser.ForExpressionContext context) {
        transformToken(FOR_EXPRESSION_END, context.getStop());
        super.exitForExpression(context);
    }

    @Override
    public void enterIfExpression(KotlinParser.IfExpressionContext context) {
        transformToken(IF_EXPRESSION_START, context.getStart());
        super.enterIfExpression(context);
    }

    @Override
    public void exitIfExpression(KotlinParser.IfExpressionContext context) {
        transformToken(IF_EXPRESSION_END, context.getStop());
        super.exitIfExpression(context);
    }

    @Override
    public void enterWhileExpression(KotlinParser.WhileExpressionContext context) {
        transformToken(WHILE_EXPRESSION_START, context.getStart());
        super.enterWhileExpression(context);
    }

    @Override
    public void exitWhileExpression(KotlinParser.WhileExpressionContext context) {
        transformToken(WHILE_EXPRESSION_END, context.getStop());
        super.exitWhileExpression(context);
    }

    @Override
    public void enterDoWhileExpression(KotlinParser.DoWhileExpressionContext context) {
        transformToken(DO_WHILE_EXPRESSION_START, context.getStart());
        super.enterDoWhileExpression(context);
    }

    @Override
    public void exitDoWhileExpression(KotlinParser.DoWhileExpressionContext context) {
        transformToken(DO_WHILE_EXPRESSION_END, context.getStop());
        super.exitDoWhileExpression(context);
    }

    @Override
    public void enterTryExpression(KotlinParser.TryExpressionContext context) {
        transformToken(TRY_EXPRESSION_START, context.getStart());
        super.enterTryExpression(context);
    }

    @Override
    public void exitTryExpression(KotlinParser.TryExpressionContext context) {
        transformToken(TRY_EXPRESSION_END, context.getStop());
        super.exitTryExpression(context);
    }

    @Override
    public void enterCatchBlock(KotlinParser.CatchBlockContext context) {
        transformToken(CATCH, context.getStart());
        super.enterCatchBlock(context);
    }

    @Override
    public void enterFinallyBlock(KotlinParser.FinallyBlockContext context) {
        transformToken(FINALLY, context.getStart());
        super.enterFinallyBlock(context);
    }

    @Override
    public void enterWhenExpression(KotlinParser.WhenExpressionContext context) {
        transformToken(WHEN_EXPRESSION_START, context.getStart());
        super.enterWhenExpression(context);
    }

    @Override
    public void exitWhenExpression(KotlinParser.WhenExpressionContext context) {
        transformToken(WHEN_EXPRESSION_END, context.getStop());
        super.exitWhenExpression(context);
    }

    @Override
    public void enterWhenCondition(KotlinParser.WhenConditionContext context) {
        transformToken(WHEN_CONDITION, context.getStart(), context.getStop());
        super.enterWhenCondition(context);
    }

    @Override
    public void enterControlStructureBody(KotlinParser.ControlStructureBodyContext context) {
        transformToken(DO, context.getStart(), context.getStop());
        super.enterControlStructureBody(context);
    }

    @Override
    public void enterVariableDeclaration(KotlinParser.VariableDeclarationContext context) {
        transformToken(VARIABLE_DECLARATION, context.getStart());
        super.enterVariableDeclaration(context);
    }

    @Override
    public void enterConstructorInvocation(KotlinParser.ConstructorInvocationContext context) {
        transformToken(CREATE_OBJECT, context.getStart(), context.getStop());
        super.enterConstructorInvocation(context);
    }

    @Override
    public void enterCallSuffix(KotlinParser.CallSuffixContext context) {
        transformToken(FUNCTION_INVOCATION, context.getStart(), context.getStop());
        super.enterCallSuffix(context);
    }

    @Override
    public void enterAssignmentOperator(KotlinParser.AssignmentOperatorContext context) {
        transformToken(ASSIGNMENT, context.getStart());
        super.enterAssignmentOperator(context);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        Token token = node.getSymbol();
        String tokenText = token.getText();
        if (tokenText.contains("@")) {
            tokenText = tokenText.substring(0, tokenText.indexOf("@"));
        }
        Optional<Integer> type = switch (tokenText) {
            case "throw" -> Optional.of(THROW);
            case "return", "return@" -> Optional.of(RETURN);
            case "continue", "continue@" -> Optional.of(CONTINUE);
            case "break", "break@" -> Optional.of(BREAK);
            case "++" -> Optional.of(INCR);
            case "--" -> Optional.of(DECR);
            default -> Optional.empty();
        };

        type.ifPresent(tokenType -> transformToken(tokenType, token));
    }

}
