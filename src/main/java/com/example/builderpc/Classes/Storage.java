package com.example.builderpc.Classes;

public class Storage extends Detail{
    private int volume;
    private String type;
    private int speedOfWrite;
    private int speedOfRead;

    public Storage(int id, String title, String manufacture, int volume, String type, int speedOfWrite, int speedOfRead) throws Exception {
        super(id, title, manufacture);
        this.volume = volume;
        this.type = type;
        this.speedOfWrite = speedOfWrite;
        this.speedOfRead = speedOfRead;
    }

    @Override
    public String toString() {
        return String.format(super.toString()+ """
                Объем: %s
                Тип памяти: %s
                Скорость записи: %s
                Скорость чтения: %s
                """, volume, type, speedOfWrite, speedOfRead);
    }

    @Override
    public String toLineString() {
        return String.format(super.toLineString() + "Объем: %s  Тип памяти: %s  Скорость записи: %s  Скорость чтения: %s",
                volume, type, speedOfWrite, speedOfRead);
    }

    public Storage() {
        super();
        this.volume = 0;
        this.type = "type";
        this.speedOfWrite = 0;
        this.speedOfRead = 0;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSpeedOfWrite() {
        return speedOfWrite;
    }

    public void setSpeedOfWrite(int speedOfWrite) {
        this.speedOfWrite = speedOfWrite;
    }

    public int getSpeedOfRead() {
        return speedOfRead;
    }

    public void setSpeedOfRead(int speedOfRead) {
        this.speedOfRead = speedOfRead;
    }
}
