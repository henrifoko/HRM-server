package com.frsummit.HRM.api.rmi.model;

import java.io.Serializable;

public class Message implements Serializable {

    private int messageId;
    private String messageUserId;
    private String leaveId;
    private String message;
    private String messageCheck;

    public Message() {
    }

    public Message(String leaveId, String message, String messageCheck) {
        this.leaveId = leaveId;
        this.message = message;
        this.messageCheck = messageCheck;
    }

    public int getMessageId() {

        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageUserId() {
        return messageUserId;
    }

    public void setMessageUserId(String messageUserId) {
        this.messageUserId = messageUserId;
    }

    public String getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(String leaveId) {
        this.leaveId = leaveId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageCheck() {
        return messageCheck;
    }

    public void setMessageCheck(String messageCheck) {
        this.messageCheck = messageCheck;
    }

	public com.frsummit.HRM.model.Message toLocalModel() {
		com.frsummit.HRM.model.Message message = new com.frsummit.HRM.model.Message();
		message.setLeaveId(this.getLeaveId());
		message.setMessage(this.getMessage());
		message.setMessageCheck(this.getMessageCheck());
		message.setMessageId(this.getMessageId());
		message.setMessageUserId(this.getMessageUserId());

		return message;
	}
}
