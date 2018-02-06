package persistence;

public class Session {

    private int id, userId, activity_id;
    private String date;

    public Session(int id, int userId, int activity_id, String date) {
        this.id = id;
        this.userId = userId;
        this.activity_id = activity_id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
