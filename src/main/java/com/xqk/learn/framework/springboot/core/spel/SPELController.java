package com.xqk.learn.framework.springboot.core.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 熊乾坤
 * @date 2020-05-30 17:07
 */
@RestController
@RequestMapping("/spel")
public class SPELController {
    @Value(value = "#{SPELProperties.property1+'_'+SPELProperties.property2+'_'+SPELProperties.property3}")
    private String value;

    @GetMapping("/parse")
    public Object parseSPELExpression(@RequestParam("args") String args) {
        if (!StringUtils.isEmpty(args)) {
            ExpressionParser expressionParser = new SpelExpressionParser();
            Expression expression = expressionParser.parseExpression(args);
            return expression.getValue();
        }
        return "";
    }

    @GetMapping("/getValue")
    public Object getValue() {
        return value;
    }
}
