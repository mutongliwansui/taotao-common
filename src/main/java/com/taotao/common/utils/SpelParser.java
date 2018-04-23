package com.taotao.common.utils;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 *  EL表达式解析工具类
 *  @author mutongli
 *  @date 2018-04-23
 */
public class SpelParser {
    private static ExpressionParser parser = new SpelExpressionParser();

    /**
     * 解析EL表达式
     * @param key 表达式
     * @param paramName 参数名数组
     * @param args 参数值数组
     * @return EL表达式运算结果
     */
    public static String parseEl(String key,String [] paramName,Object[] args){
        Expression exp = parser.parseExpression(key); //将key解析为EL表达式
        EvaluationContext context = new StandardEvaluationContext(exp); //创建赋值上下文
        if(args.length <= 0){
            return null;
        }
        //将形参和形参值以配对的方式配置到赋值上下文中
        for (int i=0;i<args.length;i++){
            context.setVariable(paramName[i],args[i]);
        }
        //根据赋值上下文运算EL表达式
        return exp.getValue(context,String.class);
    }
}
