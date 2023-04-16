class Message {
    private String message_content;
    private Account sender;
    private Account receiver;
    /**
     * constructor for message class
     * it takes the data of the sender,receiver and the message content
     * @param sender
     * @param receiver
     * @param message_content
     */
    public Message(Account sender, Account receiver, String message_content) {

        this.sender = sender;
        this.receiver = receiver;
        this.message_content = message_content;
    }

    public String getMessageContent() {
        return ("Sender : " + sender.getUsername() + "\n" + "Receiver : " + receiver.getUsername() + "\nMessage :"
                + message_content);
    }

}