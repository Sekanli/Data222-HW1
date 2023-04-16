class Comment extends interaction {
    private String content;
    /**
     * Constructor for comment
     * when a new comment is created,it comments to the given post by the given account with the given content
     * 
     * @param p post to comment
     * @param acc  account who comments to the post
     * @param cont content of the comment
     */
    public Comment(Post p, Account acc, String cont) {
        if (!acc.getLoginStatus()) {
            System.out.println("You can not like a post since you have not logged in.");
            return;
        }
        username = acc.getUsername();
        accountID = acc.getUserID();
        content = cont;
        p.addtoCommentArray(this);
    }

    public String getContent() {
        return content;
    }
}
