package org.undergrad.checkers.game;

public class userID {
    private static int userID = 0;

    public static void setUserID(int newuserID) {
        userID = newuserID;
    }

    public static int getUserID() {
        return userID;
    }
}
