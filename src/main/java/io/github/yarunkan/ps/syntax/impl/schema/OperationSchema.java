package io.github.yarunkan.ps.syntax.impl.schema;

import io.github.yarunkan.ps.syntax.token.TokenParser;
import io.github.yarunkan.ps.syntax.token.TokenSchema;
import io.github.yarunkan.ps.syntax.token.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationSchema implements TokenSchema {

    private final TokenValidator operationValidator;
    private final TokenParser operationParser;

    @Autowired
    public OperationSchema(TokenValidator operationValidator,
                           TokenParser operationParser) {

        this.operationValidator = operationValidator;
        this.operationParser = operationParser;
    }

    @Override
    public String getDescription() {

        return "<operation> --> <var-name> = <expression>\n";
    }

    @Override
    public TokenValidator getTokenValidator() {

        return operationValidator;
    }

    @Override
    public TokenParser getTokenParser() {

        return operationParser;
    }
}