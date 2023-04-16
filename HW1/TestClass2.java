public class TestClass2 {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        int useridcount = 1 ,postidcount = 1;
        Account gizemsungu = new Account(useridcount++, "gizemsungu", "01.01.2001", "Adana");
        Account sibelgulmez = new Account(useridcount++, "sibelgulmez", "03.03.2003", "Kayseri");
        Account gokhankaya = new Account(useridcount++, "gokhankaya", "05.05.2005", "Zonguldak");
        sibelgulmez.login();
        Post p1 = new Post("Weather is great", postidcount++);
        Post p2 = new Post("Homework is annoying", postidcount++);
        sibelgulmez.addPost(p2);
        sibelgulmez.addPost(p1);
        sibelgulmez.follow(gokhankaya);
        sibelgulmez.follow(gizemsungu);
        sibelgulmez.logout();

        gokhankaya.login();
        gokhankaya.viewProfile(sibelgulmez);
        gokhankaya.viewPosts(sibelgulmez);
        new Like (p1,gokhankaya);
        new Comment(p2, gokhankaya, "This is sparta.");
        gokhankaya.follow(sibelgulmez);
        gokhankaya.follow(gizemsungu);
        gokhankaya.sendMessage(gizemsungu,"Hello there.");
        gokhankaya.logout();

        gizemsungu.login();
        gizemsungu.checkInbox();
        gizemsungu.checkOutbox();
        gizemsungu.getInbox();
        gizemsungu.viewProfile(sibelgulmez);
        gizemsungu.viewPosts(sibelgulmez);
        gizemsungu.viewPostReactions(sibelgulmez);
        new Like(p1,gizemsungu);
        gizemsungu.viewPostReactions(sibelgulmez);

        Post p3 = new Post("I bip bap bup ",postidcount++ );
        gizemsungu.addPost(p3);
        Post p4 = new Post("I dont know ",postidcount++);
        gizemsungu.addPost(p4);
        gizemsungu.logout();
        
        sibelgulmez.login();
        sibelgulmez.viewProfile(gizemsungu);
        new Like (p3,sibelgulmez);
        sibelgulmez.logout();

        gokhankaya.login();
        gokhankaya.viewProfile(gizemsungu);
        new Comment(p4,gokhankaya,"Nice!");
        gokhankaya.sendMessage(gizemsungu,"Hello!");
        gokhankaya.logout();

        gizemsungu.login();
        gizemsungu.viewProfile(gizemsungu);
        gizemsungu.getInbox();
        long end=System.currentTimeMillis();
        System.out.println("time is " + (end-start));
    }
}