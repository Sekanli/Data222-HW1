/**
 * Account class implementation
 * 
 * @author Sekanli
 * 
 */
class Account {
    protected String birthday, username, location;
    protected int accountid;
    Account[] followers = new Account[10];
    Account[] following = new Account[10];
    private Post[] post;
    private Message[] inbox;
    private Message[] outbox;
    protected int post_count = 0;
    protected int follower_count = 0;
    protected int following_count = 0;
    protected int block_count = 0;
    private boolean login_status = false;
    int[] blockedid = new int[10];

    /**
     * 
     * @param accountid
     * @param username
     * @param birthday
     * @param location
     */

    public Account(int accountid, String username, String birthday, String location) {
        this.accountid = accountid;
        this.username = username;
        this.birthday = birthday;
        this.location = location;
        this.inbox = new Message[10];
        this.outbox = new Message[10];
        this.post = new Post[10];
    }

    /**
     * block method allows user to block another user
     * 
     * @param target target account to block
     */
    public void block(Account target) {
        blockedid[block_count] = target.getUserID();
        block_count++;
        target.blockedid[target.block_count] = this.getUserID();
        target.block_count++;
    }

    /**
     * Checks if the target user is blocked
     * 
     * @param target target user for checking if this user blocked him/her
     * @return returns boolean based on the target is blocked or not
     */
    public boolean isBlocked(Account target) {
        for (int i = 0; i < block_count; i++) {
            if (blockedid[i] == target.getUserID()) {
                return true;
            }
        }
        return false;

    }

    /**
     * Allowing users to see how many messages they got in their inbox-outbox
     */
    public void checkInbox() {
        System.out.println("There is/are " + inbox.length + "message(s) in the inbox.");
    }

    public void checkOutbox() {
        System.out.println("There is/are " + outbox.length + "message(s) in the outbox.");
    }

    // Method for logging in to an account
    // User cant login twice
    public void login() {
        if (login_status == false)
            login_status = true;
        else {
            System.out.println("You already did login earlier");
        }
    }

    /*
     * Method for logging out from an account
     * User cant logout twice
     */
    public void logout() {
        if (login_status == true)
            login_status = false;
        else {
            System.out.println("You cant logout from an account you did not login earlier");
        }
    }

    /* Method to check if the user is logged in */
    public boolean getLoginStatus() {
        return login_status;
    }

    /**
     * Allowing user to share a new post
     * 
     * @param p parameter of the show that user wants to share
     */
    public void addPost(Post p) {
        if (!getLoginStatus()) {
            System.out.println("You did not login");
            return;
        }
        if (post_count < 10) {
            post[post_count] = p;
            post_count++;
        } else {
            System.out.println("Cannot add more posts");
        }
    }

    /**
     * allowing user to view the reactions to the post if the user is logged in and
     * is not blocked by the target
     * 
     * @param target target account to see the post reactions
     */
    public void viewPostReactions(Account target) {
        if (!getLoginStatus()) {
            System.out.println("You did not login");
            return;
        }

        if (target.isBlocked(this)) {
            System.out.println("You cant view this targets posts ");
            return;
        }
        target.getPostReactions();
    }

    /**
     * printing the post reactions
     */
    public void getPostReactions() {
        System.out.println("Viewing " + getUsername() + "'s post interactions:\n -------------------- ");

        for (int i = 0; i < post_count; i++) {

            System.out.println("(PostID : " + post[i].getPostID() + ") : " + post[i].getContent());
            if (post[i].getLikeCount() == 0) {
                System.out.println("This post has no likes.");
            } else {
                System.out.print("This post is liked by following accounts : ");

                for (int n = 0; n < post[i].getLikeCount(); n++) {
                    if (post[i].getlikearray()[n] != null)
                        System.out.print(post[i].getlikearray()[n].getInteractionName() + ",");
                }
                System.out.print("\n");
            }
            if (post[i].getCommentCount() == 0) {
                System.out.println("This post has no comments.");
            } else {
                System.out.print("\nThis post is commented by following accounts : ");
                for (int n = 0; n < post[i].getCommentCount(); n++) {
                    if (post[i].getCommentarray()[n] != null)
                        System.out.println(post[i].getCommentarray()[n].getInteractionName());
                }
                for (int a1 = 0; a1 < post[i].getCommentCount(); a1++) {
                    if (post[i].getCommentarray()[a1] != null)
                        System.out.println("Comment " + a1 + 1 + " ==> " + post[i].getCommentarray()[a1].getContent());
                }
            }
        }
        System.out.println(" -------------------- ");
    }

    /**
     * allowing user to view the posts if the user is logged in and
     * is not blocked by the target
     * 
     * @param target target account to see the posts
     */
    public void viewPosts(Account target) {
        if (target.isBlocked(this)) {
            System.out.println("You cant view this targets posts ");
            return;
        }
        target.getPosts();
    }

    /**
     * * allowing user to view the profile if the user is logged in and
     * is not blocked by the target
     * 
     * @param target target account to see the post reactions
     */
    public void viewProfile(Account target) {

        if (target.isBlocked(this)) {
            System.out.println("You cant view this targets profile ");
            return;
        }
        target.getProfile();

    }

    /*
     * Printing the profile
     */
    public void getProfile() {
        System.out.println("Viewing " + getUsername() + "'s profile..."
                + "\nUser ID: " + accountid + "\nUsername: " + username
                + "\nLocation: " + location + "\nBirthday: " + birthday
                + "\n" + getUsername() + " is following " + following_count + " account(s) and has "
                + follower_count + " follower(s)");
        getFollowers();
        getFollowing();
        System.out.println(username + " has " + post_count + " post(s)");

    }

    // Reach user inbox - outbox
    // Cant reach if user has not logged in
    public void getInbox() {
        if (!getLoginStatus()) {
            System.out.println("You did not login");
            return;
        }
        System.out.println("Accessing the inbox of " + getUsername());
        for (int n = 0; n < 10; n++) {
            if (inbox[n] != null)
                System.out.println(n + 1 + ":\n" + inbox[n].getMessageContent() + "\n");
        }
    }

    public void getOutbox() {
        if (!getLoginStatus()) {
            System.out.println("You did not login");
            return;
        }
        System.out.println("Accessing the outbox of " + getUsername());
        for (int n = 0; n < 10; n++) {
            if (outbox[n] != null)
                System.out.println(n + 1 + ":\n" + outbox[n].getMessageContent() + "\n");
        }
    }

    /**
     * 
     * @param account target account to follow
     */
    // Follow a target account => User following list updated,target follower list
    // updated
    // Cant follow if the user has not logged in
    public void follow(Account account) {
        if (!getLoginStatus()) {
            System.out.println("You did not login");
            return;
        }
        // Check if the account is already being followed
        for (int i = 0; i < following.length; i++) {
            if (following[i] != null && following[i].equals(account)) {
                System.out.println("You are already following " + account.getUsername());
                return;
            }
        }

        // Find an empty slot in the following array
        int emptyIndex = -1;
        for (int i = 0; i < following.length; i++) {
            if (following[i] == null) {
                emptyIndex = i;
                break;
            }
        }

        // Add the account to the following array if an empty slot is found
        if (emptyIndex != -1) {
            following[emptyIndex] = account;
            account.addFollower(this);
            this.addFollowing(account);
            System.out.println("You are now following " + account.getUsername());

        } else {
            System.out.println("You cannot follow more accounts. Maximum capacity reached.");
        }
    }

    // Check if the user is following the target account (for send messages)
    public boolean isFollowing(Account receiver) {
        boolean isFollowing = false;
        for (Account account : this.following) {
            if (account != null && account.getUserID() == receiver.getUserID()) {
                isFollowing = true;
                break;
            }
        }
        if (!isFollowing) {
            return false;
        }
        return true;
    }

    /**
     * Send message to another user with its content if the user is logged in,is not
     * blocked by the target user
     * and is following the target user
     * 
     * @param receiver        the user to take the message
     * @param message_content the content to deliver
     */
    public void sendMessage(Account receiver, String message_content) {
        if (!getLoginStatus()) {
            System.out.println("You did not login");
            return;
        }
        if (receiver.isBlocked(this)) {
            System.out.println("You cant send message to this target");
            return;
        }
        // Check the follow status
        if (!isFollowing(receiver)) {
            System.out.println("Cannot send message to " + receiver.getUsername() + ". You are not following them.");
            return;
        }
        // Send message , receiver receives the message to her/his inbox

        Message message = new Message(this, receiver, message_content);
        receiver.receiveMessage(message);
        addMessageToOutbox(message);
    }

    /**
     * A message was sent from another user,receive the message and add it to your
     * inbox
     * 
     * @param message
     */
    public void receiveMessage(Message message) {
        addMessageToInbox(message);
    }

    private void addMessageToInbox(Message message) {
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] == null) {
                inbox[i] = message;
                break;
            }
        }
    }

    /**
     * Add the sent message to your outbox
     * 
     * @param message
     */
    private void addMessageToOutbox(Message message) {
        for (int i = 0; i < outbox.length; i++) {
            if (outbox[i] == null) {
                outbox[i] = message;
                break;
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public int getUserID() {
        return accountid;
    }

    public void getPosts() {

        System.out.println("Accessing the posts of " + getUsername());
        for (int n = 0; n < 10; n++) {
            if (post[n] != null)
                System.out.println("(PostID : " + post[n].getPostID() + "):" + post[n].getContent());
        }

    }

    /**
     * If a user follows a target user,add the user as a follower to the target
     * account
     * 
     * @param follower target user
     */
    public void addFollower(Account follower) {
        if (follower_count < 10) {
            followers[follower_count] = follower;
            follower_count++;
        }
    }

    /*
     * print followers if it isnt 0
     */
    public void getFollowers() {
        if (follower_count == 0)
            return;

        System.out.print(username + " is being followed by ");
        for (int n = 0; n < follower_count - 1; n++) {
            System.out.print(followers[n].getUsername() + " , ");
        }
        System.out.println(followers[follower_count - 1].getUsername());
    }

    /**
     * if the user starts to follow target user,add the target user to the following
     * array of the user
     * 
     * @param following
     */
    public void addFollowing(Account following) {
        if (following_count < 10) {
            this.following[following_count] = following;
            following_count++;
        }
    }

    /**
     * print
     */
    public void getFollowing() {
        if (following_count == 0)
            return;

        System.out.print(username + " is following ");
        for (int n = 0; n < following_count - 1; n++) {
            System.out.print(following[n].getUsername() + " , ");
        }
        System.out.println(following[following_count - 1].getUsername());
    }
}
