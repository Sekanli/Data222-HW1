class Post {
    private String content;
    private int postID;
    private Like[] likearray;
    private Comment[] commentarray;
    private int likeCount, commentCount;
/**
 * Constructor 
 * @param content contents of the post
 * @param postid  unique id of the post
 */
    public Post(String content, int postid) {
        this.content = content;
        likearray = new Like[10];
        commentarray = new Comment[10];
        this.likeCount = 0;
        this.commentCount = 0;
        postID = postid;

    }

    public int getLikeCount() {
        return likeCount;
    }
    /**
     * if the post is liked,add it to the likes array to keep track of who likes the post
     * @param obj like object with the user's id and name who liked the post
     */
    public void addtolikeArray(Like obj) {

        likearray[likeCount] = obj;
        likeCount++;
    }

    public int getCommentCount() {
        return commentCount;
    }
    /**
     * if the post is commented,add it to the likes array to keep track of who likes the post
     * @param obj like object with the user's id and name who commented the post
     */
    public void addtoCommentArray(Comment obj) {

        commentarray[commentCount] = obj;
        commentCount++;
    }

    public Like[] getlikearray() {
        return likearray;
    }

    public Comment[] getCommentarray() {
        return commentarray;
    }

    public String getContent() {
        return content;
    }

    public int getPostID() {
        return postID;
    }
}
