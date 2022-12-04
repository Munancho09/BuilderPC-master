package com.example.builderpc.Classes;

public class CPU extends Detail {
    private float frequency;
    private int power;
    private String socket;
    private String archetype;

    @Override
    public String toString() {
        return String.format( super.toString() + """
                Частота: %s
                Энергопотребление: %s
                Сокет: %s
                Архитектура: %s
                """, frequency, power, socket, archetype);
    }
    public String toLineString() {
        return String.format( super.toLineString() + "Частота: %s  Энергопотребление: %s  Сокет: %s  Архитектура: %s", frequency, power, socket, archetype);
    }
    public CPU(int id, float frequency, int power, String socket, String archetype, String title, String manufacture) throws Exception {
        super(id, title, manufacture);
        this.frequency = frequency;
        this.power = power;
        this.socket = socket;
        this.archetype = archetype;
    }
    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) throws Exception {
        if(frequency <= 0){
            throw new Exception("Вы ввели некорректное значение частоты");
        }
        this.frequency = frequency;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) throws Exception {
        if(power<=0){
            throw new Exception("Вы ввели некорректное значение энергопотребления");
        }
        this.power = power;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) throws Exception {
        if(socket.length() == 0){
            throw new Exception("Вы ввели некорректное значение сокета");
        }
        this.socket = socket;
    }

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) throws Exception {
        if(archetype.length() == 0){
            throw new Exception("Вы ввели некорректное значение архитектуры");
        }
        this.archetype = archetype;
    }


    public CPU() {
        super();
        this.frequency = 0.0f;
        this.power = 0;
        this.socket = "socket";
        this.archetype = "archetype";
    }
}
