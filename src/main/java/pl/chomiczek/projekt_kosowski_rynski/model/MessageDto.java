package pl.chomiczek.projekt_kosowski_rynski.model;

public class MessageDto {
    private String text;
    private String email;

    public MessageDto() {
    }

    public MessageDto(String text, String email) {
        this.text = text;
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
