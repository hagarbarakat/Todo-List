package model;

public class List_item {
    private String Title;
    private String subtitle;
    private boolean reminder;

    public List_item(String title, String subtitle, boolean reminder) {
        Title = title;
        this.subtitle = subtitle;
        this.reminder = reminder;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isReminder() {
        return reminder;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }
}
