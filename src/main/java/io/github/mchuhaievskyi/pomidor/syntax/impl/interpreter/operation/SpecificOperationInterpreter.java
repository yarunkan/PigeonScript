package io.github.mchuhaievskyi.pomidor.syntax.impl.interpreter.operation;

import io.github.mchuhaievskyi.pomidor.database.variables.VariablesDatabase;
import io.github.mchuhaievskyi.pomidor.database.variables.VariablesDatabaseImpl;
import io.github.mchuhaievskyi.pomidor.syntax.Token;
import io.github.mchuhaievskyi.pomidor.syntax.impl.TokenParserImpl;
import io.github.mchuhaievskyi.pomidor.syntax.impl.interpreter.expression.SpecificExpressionInterpreter;
import io.github.mchuhaievskyi.pomidor.syntax.token.AbstractTokenInterpreter;
import io.github.mchuhaievskyi.pomidor.syntax.token.TokenType;
import java.util.List;

public abstract class SpecificOperationInterpreter<T> extends AbstractTokenInterpreter {

    final VariablesDatabase variablesDatabase = VariablesDatabaseImpl.getInstance();

    private final SpecificExpressionInterpreter<T> specificExpressionTokenInterpreter;
    private final TokenType expressionType;

    protected SpecificOperationInterpreter(SpecificExpressionInterpreter<T> specificExpressionTokenInterpreter, TokenType expressionType) {

        this.specificExpressionTokenInterpreter = specificExpressionTokenInterpreter;
        this.expressionType = expressionType;
    }

    @Override
    public boolean interpret(Token token) {

        final List<Token> subTokens = token.getSubTokens();
        final String assignableVarName = subTokens.get(0).getSourceCode();
        String assignableVarValue = variablesDatabase.getVariable(assignableVarName);

        if (assignableVarValue == null) {

            return false;
        }

        final List<Token> expressionTokens = subTokens.subList(2, subTokens.size());
        final String[] expressionSourceCode = new String[expressionTokens.size()];

        for (int i = 0; i < expressionSourceCode.length; i++) {

            expressionSourceCode[i] = expressionTokens.get(i).getSourceCode();
        }

        final String expressionSourceCodeLine = String.join(" ", expressionSourceCode);
        final Token expressionToken = new TokenParserImpl(expressionSourceCodeLine, expressionType).takeNextToken();

        if (!specificExpressionTokenInterpreter.interpret(expressionToken)) {

            return false;
        }

        try {

            assignableVarValue = specificExpressionTokenInterpreter.getExpressionResult(expressionToken).toString();

        } catch (Exception e) {

            return false;
        }

        variablesDatabase.setVariable(assignableVarName, assignableVarValue);

        return true;
    }
}