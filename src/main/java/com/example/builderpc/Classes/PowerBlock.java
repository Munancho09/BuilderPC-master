package com.example.builderpc.Classes;

public class PowerBlock extends Detail{
    private int power;

    public PowerBlock(int id, String title, String manufacture, int power) throws Exception {
        super(id, title, manufacture);
        this.power = power;
    }

    public PowerBlock() {
        super();
        this.power = 0;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + """
                Мощность: %s
                """, power);
    }

    @Override
    public String toLineString() {
        return String.format(super.toLineString() + "Мощность: %s", power);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
