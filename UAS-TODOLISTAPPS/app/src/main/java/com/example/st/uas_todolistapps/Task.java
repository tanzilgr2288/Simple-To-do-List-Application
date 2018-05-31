package com.example.st.uas_todolistapps;

/**
 * Created by ST on 6/13/2016.
 */
public class Task {
    String task_name,notes ;
    int id, category_id,flag_done;

    public Task(int id,String task_name, String notes, int category_id, int flag_done) {
        this.id = id;
        this.task_name = task_name;
        this.notes = notes;
        this.category_id = category_id;
        this.flag_done = flag_done;
    }

    public String getTask_name() {
        return task_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getFlag_done() {
        return flag_done;
    }

    public void setFlag_done(int flag_done) {
        this.flag_done = flag_done;
    }
}
