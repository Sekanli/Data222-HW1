abstract class interaction {
    int accountID;
    String username;
    /**
     * superclass for comment and like
     * no parameters
     */
    public interaction() {

    }

    public int getInteractionID() {
        return accountID;
    }

    public String getInteractionName() {
        return username;
    }

}