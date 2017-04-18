package ql.value;

public class IntegerValue extends Value {

	private final Integer value;
	
    public IntegerValue(Integer value) {
        this.value = value;
    }
    
    public IntegerValue() {
    	this.value = 0;
    }

	@Override
	public Value add(Value other) {
		return new IntegerValue(value + ((IntegerValue) other).getValue() );
	}
	
	@Override
	public Value sub(Value other) {
		return new IntegerValue(value - ((IntegerValue) other).getValue() );
	}
	
	@Override
	public Value mul(Value other) {
		return new IntegerValue(value * ((IntegerValue) other).getValue() );
	}

	@Override
	public Value div(Value other) {

		if (((IntegerValue) other).getValue() == 0) {
			return new IntegerValue();
		}
		
		return new IntegerValue(value / ((IntegerValue) other).getValue() );
	}

	@Override
	public BoolValue eq(Value other) {
		return new BoolValue(value.equals(((IntegerValue) other).getValue()) );
	}

	@Override
	public BoolValue notEq(Value other) {
		return new BoolValue(!value.equals(((IntegerValue) other).getValue()) );
	}
	
	@Override
	public BoolValue greaterEq(Value other) {
		return new BoolValue(value >= ((IntegerValue) other).getValue() );
	}

	@Override
	public BoolValue greater(Value other) {
		return new BoolValue(value > ((IntegerValue) other).getValue() );
	}

	@Override
	public BoolValue lessEq(Value other) {
		return new BoolValue(value <= ((IntegerValue) other).getValue() );
	}

	@Override
	public BoolValue less(Value other) {
		return new BoolValue(value < ((IntegerValue) other).getValue() );
	}
	
	@Override
	public Value plus() {
		return new IntegerValue(+ value);
	}

	@Override
	public Value min() {
		return new IntegerValue(- value);
	}

    public Integer getValue() {
        return this.value;
    }

    public String convertToString() {
    	return this.value.toString();
	}

	@Override
	public boolean equals(Value other) {
		return value.equals(((IntegerValue) other).getValue());
	}
	
}
