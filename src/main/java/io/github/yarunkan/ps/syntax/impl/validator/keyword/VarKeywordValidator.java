package io.github.yarunkan.ps.syntax.impl.validator.keyword;

import org.springframework.stereotype.Component;

@Component
public class VarKeywordValidator implements SpecificKeywordValidator {

    @Override
    public String getKeywordConstant() {
        return "var";
    }
}