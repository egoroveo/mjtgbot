package com.punchthebag.mjtgbot.request;

public class UpdateRequest {

    private Integer update_id;

    public Integer getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(Integer update_id) {
        this.update_id = update_id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    private Message message;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getName())
                .append("@")
                .append(Integer.toHexString(hashCode()))
                .append(" update_id: ")
                .append(update_id)
                .append(" message: ")
                .append(message);
        return stringBuilder.toString();
    }
}
