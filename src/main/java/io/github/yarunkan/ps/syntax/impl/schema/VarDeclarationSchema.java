package io.github.yarunkan.ps.syntax.impl.schema;

import io.github.yarunkan.ps.syntax.token.TokenParser;
import io.github.yarunkan.ps.syntax.token.TokenSchema;
import io.github.yarunkan.ps.syntax.token.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VarDeclarationSchema implements TokenSchema {

    private final TokenValidator varDeclarationValidator;
    private final TokenParser varDeclarationParser;

    @Autowired
    public VarDeclarationSchema(TokenValidator varDeclarationValidator,
                                TokenParser varDeclarationParser) {

        this.varDeclarationValidator = varDeclarationValidator;
        this.varDeclarationParser = varDeclarationParser;
    }

    @Override
    public String getDescription() {
        return "10) <var-declaration> --> var <var-name> = <literal>\n";
    }

    @Override
    public TokenValidator getTokenValidator() {
        return varDeclarationValidator;
    }

    @Override
    public TokenParser getTokenParser() {
        return varDeclarationParser;
    }
}