package edu.mum.cs.auctioneer.session;

import edu.mum.cs.auctioneer.models.Person;

public class UserSession {

    private static Person loggedInPerson;

    public static Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public static void setLoggedInPerson(Person loggedInPerson) {
        UserSession.loggedInPerson = loggedInPerson;
    }
}
