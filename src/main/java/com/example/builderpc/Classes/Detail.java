package com.example.builderpc.Classes;

abstract public class Detail {
    protected int id;
    protected String title;
    protected String manufacture;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws Exception {
        if(title.length() == 0){
            throw new Exception("Вы не ввели название");
        }
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) throws Exception {
        if(manufacture.length() == 0){
            throw new Exception("Вы ввели некорректное значение производителя");
        }
        this.manufacture = manufacture;
    }

    public Detail(int id, String title, String manufacture) throws Exception {
        if(title.length() == 0){
            throw new Exception("Вы не ввели название");
        }
        if(manufacture.length() == 0){
            throw new Exception("Вы ввели некорректное значение производителя");
        }
        this.title = title;
        this.manufacture = manufacture;
        this.id = id;
    }

    public Detail() {
        title = "title";
        manufacture = "manufacture";
        id = 0;
    }

    @Override
    public String toString() {
        return String.format("""
                Номер: %s
                Название: %s
                Производитель: %s
                """,id, title, manufacture);
    }
    public String toLineString() {
        return String.format("Название: %s  Производитель: %s  ",title, manufacture);
    }
}
