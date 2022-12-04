package com.example.builderpc.Classes;

public class VideoCard extends Detail {
    private String GCPU;
    private int volumeMemory;
    private String typeMemory;
    private int FrequencyMemory;
    private int power;

    @Override
    public String toString() {
        return String.format(super.toString() + """
                Графический процессор: %s
                Объем памяти: %s
                Тип памяти: %s
                Частота памяти: %s
                Мощность: %s
                """, GCPU, volumeMemory, typeMemory, FrequencyMemory, power);
    }

    @Override
    public String toLineString() {
        return String.format(super.toLineString()+"Графический процессор: %s  Объем памяти: %s  Тип памяти: %s  Частота памяти: %s  Мощность: %s",
                GCPU, volumeMemory, typeMemory, FrequencyMemory, power);
    }

    public VideoCard() {
        super();
        this.GCPU = "GCPU";
        this.volumeMemory = 0;
        this.typeMemory = "typeMemory";
        FrequencyMemory = 0;
        this.power = 0;
    }

    public VideoCard(int id, String title, String manufacture, String GCPU, int volumeMemory, String typeMemory, int frequencyMemory, int power) throws Exception {
        super(id, title, manufacture);
        this.GCPU = GCPU;
        this.volumeMemory = volumeMemory;
        this.typeMemory = typeMemory;
        FrequencyMemory = frequencyMemory;
        this.power = power;
    }

    public String getGCPU() {
        return GCPU;
    }

    public void setGCPU(String GCPU) throws Exception {
        this.GCPU = GCPU;
    }

    public int getVolumeMemory() {
        return volumeMemory;
    }

    public void setVolumeMemory(int volumeMemory) {
        this.volumeMemory = volumeMemory;
    }

    public String getTypeMemory() {
        return typeMemory;
    }

    public void setTypeMemory(String typeMemory) {
        this.typeMemory = typeMemory;
    }

    public int getFrequencyMemory() {
        return FrequencyMemory;
    }

    public void setFrequencyMemory(int frequencyMemory) {
        FrequencyMemory = frequencyMemory;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
