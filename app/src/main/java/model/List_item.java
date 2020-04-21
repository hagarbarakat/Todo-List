package model;

public class List_item {
    private String Title;
    private String subtitle;
    private boolean reminder;
    private boolean checked;
    private boolean checklist;

    public List_item(String title, String subtitle, boolean reminder, boolean checked, boolean checklist) {
        Title = title;
        this.subtitle = subtitle;
        this.reminder = reminder;
        this.checked = checked;
        this.checklist = checklist;
    }

    public boolean isChecklist() {
        return checklist;
    }

    public void setChecklist(boolean checklist) {
        this.checklist = checklist;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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
