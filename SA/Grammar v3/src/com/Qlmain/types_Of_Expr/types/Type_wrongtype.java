package com.Qlmain.types_Of_Expr.types;

/**
 * Created by sotos on 20/3/2017.
 */
public class Type_wrongtype extends Type {
    public Type_wrongtype(){}

    @Override
    public Type exprTypeChecker() {
        return null;
    }

    @Override
    public Object Evaluator() {
        return null;
    }

    @Override
    public boolean check__bool_type() {return false;}

    @Override
    public boolean check__int_type() {return false;}

    @Override
    public boolean check__mon_type() {return false;}

    @Override
    public boolean check__str_type() {return false;}

    @Override
    public boolean check__wrong_type() {return true;}

    @Override
    public boolean check__no_type() {return false;}
}
