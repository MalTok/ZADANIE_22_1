package pl.mt.task;

public class Message {
    private String sender;
    private String senderEmail;
    private String text;

    public Message() {
    }

    public Message(String sender, String senderEmail, String text) {
        this.sender = sender;
        this.senderEmail = senderEmail;
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
