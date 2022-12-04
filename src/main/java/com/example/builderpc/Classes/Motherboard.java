package com.example.builderpc.Classes;

public class Motherboard extends Detail{
    private String socket;
    private String GCPUtype;
    private String RAMtype;

    public Motherboard(int id, String title, String manufacture, String socket, String GCPUtype, String RAMtype) throws Exception {
        super(id, title, manufacture);
        this.socket = socket;
        this.GCPUtype = GCPUtype;
        this.RAMtype = RAMtype;
    }

    public Motherboard() {
        super();
        this.socket = "socket";
        this.GCPUtype = "GCPUtype";
        this.RAMtype = "RAMtype";
    }

    @Override
    public String toString() {
        return String.format(super.toString()+ """
                Сокет: %s
                Поддерживаемые видеокарты: %s
                Поддерживаемая RAM: %s
                """, socket, GCPUtype, RAMtype);
    }

    @Override
    public String toLineString() {
        return String.format(super.toLineString() + "Сокет: %s  Поддерживаемые видеокарты: %s  Поддерживаемая RAM: %s", socket, GCPUtype, RAMtype);
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getGCPUtype() {
        return GCPUtype;
    }

    public void setGCPUtype(String GCPUtype) {
        this.GCPUtype = GCPUtype;
    }

    public String getRAMtype() {
        return RAMtype;
    }

    public void setRAMtype(String RAMtype) {
        this.RAMtype = RAMtype;
    }
}
