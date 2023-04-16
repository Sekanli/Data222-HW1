class Like extends interaction {
    /**
     * subclass of interaction class
     * constructor
     *  when a new like is created,it likes the target post by the given account
     * cant like the same post twice
     * @param p
     * @param acc
     */
    public Like(Post p, Account acc) {

        for (int i = 0; i < p.getLikeCount(); i++) {
            if (p.getlikearray()[i].getInteractionID() == acc.getUserID()) {
                System.out.println("You cant like the same post twice");
                return;
            }
        }
        if (!acc.getLoginStatus()) {
            System.out.println("You can not like a post since you have not logged in.");
            return;
        }
        username = acc.getUsername();
        accountID = acc.getUserID();
        p.addtolikeArray(this);
    }

}